package com.sfanshen.currency;

import com.sfanshen.Formula.BaseFormula;
import com.sfanshen.Formula.ResetFormula;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.BigNum;
import com.sfanshen.main.Global;
import com.sfanshen.main.Multiplier;
import com.sfanshen.ui.ResetButton;
import com.sfanshen.upgrade.Upgrade;

import java.awt.*;

public class ResetCurrency extends Currency {

    public Currency dependentCurrency;
    public ResetFormula gainFormula;

    public ResetButton resetButton;
    public BigNum minResetAmt;
    public Currency[] resetCurrencies;
    public ResetCurrency(String name, Picture icon, Color color, String[] resetCurrencies, String dependentCurrency, String gainFormula, String minResetAmt) {
        super(name, icon, color);

        this.dependentCurrency = Global.findCurrency(dependentCurrency);
        this.gainFormula = new ResetFormula(gainFormula);
        this.minResetAmt = new BigNum(minResetAmt);

        this.resetButton = new ResetButton(this);
        instantiateResetCurrencies(resetCurrencies);
    }

    public void increase() {
        if (this.dependentCurrency.amount.isGreaterEqualTo(minResetAmt)) {
            this.amount.add(this.calculateGain());

            for (Currency currency : resetCurrencies){
                currency.set(0);
                for (Upgrade upgrade: Global.upgrades.values()){
                    if (upgrade.resetTier.equals(currency.name)){
                        upgrade.reset(true);
                    }
                }
            }
        }
    }
    public void increase(int n) {
        this.increase(new BigNum(n));
    }


    public BigNum calculateGain(){
        BigNum x = this.gainFormula.calculate(this.dependentCurrency.amount);
        return BigNum.multiply(x, this.multi.finalMultiplier);
    }
    //----------Instantiation----------\\
    public void instantiateResetCurrencies(String[] currencyNames){
        Currency[] currencies = new Currency[currencyNames.length];
        for (int i = 0; i < currencyNames.length; i ++){
            currencies[i] = Global.findCurrency(currencyNames[i]);
        }
        this.resetCurrencies = currencies;
    }
}