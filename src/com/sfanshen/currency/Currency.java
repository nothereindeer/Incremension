package com.sfanshen.currency;

import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Global;
import com.sfanshen.ui.ResetButton;
import com.sfanshen.upgrade.BoostUpgrade;

import java.util.ArrayList;

public class Currency {
    public String name;
    public BigNum amount;
    public Currency[] resetCurrencies;
    public ResetButton resetButton;


    //Additive boosts are applied according to math rules (BEDMAS duh)
    public BigNum finalBoost;
    BigNum additiveBoost;
    BigNum multiplicativeBoost;
    BigNum exponentialBoost;

    //All upgrades that boost this specific currency are stored based on how they boost the currency (additive/multiplicative/exponential)
    public ArrayList<BoostUpgrade> additiveUpgrades;
    public ArrayList<BoostUpgrade> multiplicativeUpgrades;
    public ArrayList<BoostUpgrade> exponentialUpgrades;


    //Every currency has its own icon, stored here for convenience
    public Picture icon;


    //-------------------------------------------------------Constructors-----------------------------------------------------------------\\
    public Currency(String name, Picture icon, String[] resetCurrencies) {
        this.name = name;
        this.amount = new BigNum(0);

        //These array lists are filled in the Global class' method initialize for more efficient code
        additiveUpgrades = new ArrayList<>();
        multiplicativeUpgrades = new ArrayList<>();
        exponentialUpgrades = new ArrayList<>();

        this.finalBoost = new BigNum(1);
        this.additiveBoost = new BigNum(0);
        this.multiplicativeBoost = new BigNum(1);
        this.exponentialBoost = new BigNum(1);

        this.icon = icon;

        this.resetButton = new ResetButton(this);
        instantiateResetCurrencies(resetCurrencies);
    }

    public Currency(String name, Picture icon) {
        this.name = name;
        this.amount = new BigNum(0);

        //These array lists are filled in the Global class' method initialize for more efficient code
        additiveUpgrades = new ArrayList<>();
        multiplicativeUpgrades = new ArrayList<>();
        exponentialUpgrades = new ArrayList<>();

        this.finalBoost = new BigNum(1);
        this.additiveBoost = new BigNum(0);
        this.multiplicativeBoost = new BigNum(1);
        this.exponentialBoost = new BigNum(1);

        this.icon = icon;

        this.resetCurrencies = new Currency[0];
    }

    //-------------------------------------------------------Methods-----------------------------------------------------------------\\

    //----------Main Methods----------\\

    public void set(String amount) {
        this.amount.set(new BigNum(amount));
    }
    public void set(int amount) {
        this.amount.set(amount);
    }

    public void increase(BigNum n) {
        n.multiply(this.finalBoost);
        this.amount.add(n);
    }
    public void increase(int n) {
        this.amount.add(BigNum.multiply(this.finalBoost, new BigNum(n)));
    }
    public void increase() {
        this.amount.add(this.finalBoost);
    }


    //----------Calculation Methods----------\\

    public void calculateBoost() {
        this.calculateAdditiveBoost();
        this.calculateMultiplicativeBoost();
        this.calculateExponentialBoost();
        this.finalBoost.set(1);

        this.finalBoost.add(this.additiveBoost);
        this.finalBoost.multiply(this.multiplicativeBoost);
        this.finalBoost.pow(this.exponentialBoost);
    }

    public void calculateAdditiveBoost() {
        this.additiveBoost.set(0);
        for (BoostUpgrade additiveUpgrade : this.additiveUpgrades) {
            if (additiveUpgrade.level > 0) {
                this.additiveBoost.add(additiveUpgrade.calculateBoost(this));
            }
        }
    }

    public void calculateMultiplicativeBoost() {
        this.multiplicativeBoost.set(1);
        for (BoostUpgrade multiplicativeUpgrade : multiplicativeUpgrades) {
            if (multiplicativeUpgrade.level > 0) {
                this.multiplicativeBoost.multiply(multiplicativeUpgrade.calculateBoost(this));
            }
        }
    }

    public void calculateExponentialBoost() {
        this.exponentialBoost.set(1);
        for (BoostUpgrade exponentialUpgrade : exponentialUpgrades) {
            if (exponentialUpgrade.level > 0) {
                this.exponentialBoost.multiply(exponentialUpgrade.calculateBoost(this));

            }
        }
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
