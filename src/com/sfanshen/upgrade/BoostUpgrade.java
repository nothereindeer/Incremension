package com.sfanshen.upgrade;

import com.sfanshen.main.BigNum;
import com.sfanshen.main.Formula;
import com.sfanshen.currency.Currency;
import com.sfanshen.main.Global;


import java.util.HashMap;

public class BoostUpgrade extends Upgrade {

    public int maxLevel;

    public HashMap<Currency, Formula> boostedCurrencies;

    public int level;

    public BoostUpgrade(String name, String priceFormula, String purchaseCurrency, String[] boostedCurrencies, String[] boostFormulas, int maxLevel) {

        super(name, priceFormula, purchaseCurrency);

        this.maxLevel = maxLevel;

        this.level = 0;

        this.boostedCurrencies = new HashMap<>();
        for (int i = 0; i < boostedCurrencies.length; i++) {
            this.boostedCurrencies.put(Global.findCurrency(boostedCurrencies[i]), new Formula(boostFormulas[i]));
        }
    }

    public BoostUpgrade(String name, String priceFormula, String purchaseCurrency, String boostedCurrency, String boostFormula, int maxLevel) {

        super(name, priceFormula, purchaseCurrency);

        this.maxLevel = maxLevel;

        this.level = 0;

        this.boostedCurrencies = new HashMap<>();
        this.boostedCurrencies.put(Global.findCurrency(boostedCurrency), new Formula(boostFormula));
    }

    public BoostUpgrade(String name, String priceFormula, String purchaseCurrency, String boostedCurrency, String boostFormula, int maxLevel, String description) {

        super(name, priceFormula, purchaseCurrency, description);

        this.maxLevel = maxLevel;

        this.level = 0;

        this.boostedCurrencies = new HashMap<>();
        this.boostedCurrencies.put(Global.findCurrency(boostedCurrency), new Formula(boostFormula));
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

    public BigNum calculateBoost(Currency currency) {
        Formula formula = boostedCurrencies.get(currency);
        return formula.calculate(this.level);
    }

    public void calculateCurrencyBoosts() {
        for (Currency currency : this.boostedCurrencies.keySet()) {
            currency.calculateBoost();
        }
    }

    public void calculatePrice() {
        this.price = this.priceFormula.calculate(this.level);
    }
}
