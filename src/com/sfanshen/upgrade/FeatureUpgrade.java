package com.sfanshen.upgrade;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.Formula;

public class FeatureUpgrade extends Upgrade {

    public boolean isBought;
    public String effect;

    FeatureUpgrade(String name, String priceFormula, String purchaseCurrency, boolean isPermanent, String description, String resetTier) {

        super(name, priceFormula, purchaseCurrency, description, resetTier);

        this.isBought = false;
    }

    FeatureUpgrade(String name, String priceFormula, String purchaseCurrency, boolean isPermanent, String resetTier) {

        super(name, priceFormula, purchaseCurrency, resetTier);

        this.isBought = false;
    }

    public void buy() {
        if (!this.isBought && this.purchaseCurrency.amount.isGreaterEqualTo(this.price)) {
            this.purchaseCurrency.amount.subtract(this.price);
            this.isBought = true;
        }
    }

    public void reset(boolean isUnlocked) {
        this.isBought = false;
        this.isUnlocked = isUnlocked;
    }

    public void calculatePrice() {
        this.price = this.priceFormula.calculate(1);
    }
}
