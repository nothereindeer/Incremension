package com.sfanshen.upgrade;

import com.sfanshen.Formula.BaseFormula;
import com.sfanshen.main.BigNum;
import com.sfanshen.Formula.Formula;
import com.sfanshen.currency.Currency;
import com.sfanshen.main.Global;


import java.util.ArrayList;
import java.util.HashMap;

public class BoostUpgrade extends Upgrade {

    public int maxLevel;

    public HashMap<String, Boost> boosts;

    public int level;

    public BoostUpgrade(String name, String priceFormula, String purchaseCurrency, String[] boostedCurrencies, String[] boostFormulas, int maxLevel, String resetTier) {

        super(name, priceFormula, purchaseCurrency, resetTier);

        this.maxLevel = maxLevel;

        this.level = 0;

        this.boosts = new HashMap<>();
        createBoosts(boostedCurrencies, boostFormulas);
    }

    public BoostUpgrade(String name, String priceFormula, String purchaseCurrency, String boostedCurrency, String boostFormula, int maxLevel, String resetTier) {

        super(name, priceFormula, purchaseCurrency, resetTier);

        this.maxLevel = maxLevel;

        this.level = 0;

        this.boosts = new HashMap<>();
        this.boosts.put(boostedCurrency, new Boost(Global.findCurrency(boostedCurrency), boostFormula.substring(0, 1), new BaseFormula(boostFormula.substring(1))));
    }

    public BoostUpgrade(String name, String priceFormula, String purchaseCurrency, String boostedCurrency, String boostFormula, int maxLevel, String description, String resetTier) {

        super(name, priceFormula, purchaseCurrency, description, resetTier);

        this.maxLevel = maxLevel;

        this.level = 0;

        this.boosts = new HashMap<>();
        this.boosts.put(boostedCurrency, new Boost(Global.findCurrency(boostedCurrency), boostFormula.substring(0, 1), new BaseFormula(boostFormula.substring(1))));
    }

    void createBoosts(String[] boostedCurrencies, String[] boostFormulas){
        for (int i = 0; i < boostedCurrencies.length; i++) {
            this.boosts.put(boostedCurrencies[i], new Boost(Global.findCurrency(boostedCurrencies[i]), boostFormulas[i].substring(0, 1), new BaseFormula(boostFormulas[i].substring(1))));
        }
    }

    public void buy() {
        if (this.level < this.maxLevel && this.purchaseCurrency.amount.isGreaterEqualTo(this.price)) {
            this.purchaseCurrency.amount.subtract(this.price);
            this.level = this.level + 1;
            this.calculatePrice();
            this.calculateCurrencyBoosts();
        }
    }

    public void reset(boolean isUnlocked) {
        this.level = 0;
        this.isUnlocked = isUnlocked;
    }

    public BigNum calculateMultiplier(Currency currency) {
        BaseFormula formula = boosts.get(currency.name).formula;
        return formula.calculate(this.level);
    }

    public void calculateCurrencyBoosts() {
        for (String currencyName : this.boosts.keySet()) {
            Currency currency = Global.findCurrency(currencyName);
            currency.multi.calculateMultiplier(currency);
        }
    }

    public void calculatePrice() {
        this.price = this.priceFormula.calculate(this.level);
    }
}
