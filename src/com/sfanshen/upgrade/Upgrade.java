package com.sfanshen.upgrade;

import com.sfanshen.Formula.BaseFormula;
import com.sfanshen.currency.Currency;
import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;
import com.sfanshen.Formula.Formula;
import com.sfanshen.main.Global;
import com.sfanshen.ui.UpgradeButton;


public abstract class Upgrade {

    public String name;
    Formula priceFormula;
    public BigNum price;
    public Currency purchaseCurrency;
    public UpgradeButton upgradeButton;
    public boolean isUnlocked;
    public String description;
    public Currency resetTier;

    public Upgrade(String name, String priceFormula, String purchaseCurrency, String description, String resetTier) {
        this.name = name;
        this.priceFormula = new BaseFormula(priceFormula);
        this.purchaseCurrency = Global.findCurrency(purchaseCurrency);
        this.upgradeButton = new UpgradeButton(new Picture(0, 0, Const.UPGRADE_ICON_SIZE, Const.UPGRADE_ICON_SIZE, Global.programDirectory + "Images/Icons/Upgrade/" + this.name + ".png"), this);
        this.description = description;
        this.resetTier = Global.findCurrency(resetTier);

        this.isUnlocked = true;
        this.calculatePrice();
    }

    public Upgrade(String name, String priceFormula, String purchaseCurrency, String resetTier) {
        this.name = name;
        this.priceFormula = new BaseFormula(priceFormula);
        this.purchaseCurrency = Global.findCurrency(purchaseCurrency);
        this.upgradeButton = new UpgradeButton(new Picture(0, 0, Const.UPGRADE_ICON_SIZE, Const.UPGRADE_ICON_SIZE, Global.programDirectory + "Images/Icons/Upgrade/" + this.name + ".png"), this);
        this.description = "";
        this.resetTier = Global.findCurrency(resetTier);

        this.isUnlocked = true;
        this.calculatePrice();
    }

    abstract public void calculatePrice();

    public boolean isPurchasable() {
        return this.purchaseCurrency.amount.isGreaterEqualTo(this.price);
    }

    public abstract void buy();

    public abstract void reset(boolean isUnlocked);
}
