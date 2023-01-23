package com.sfanshen.upgrade;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;
import com.sfanshen.main.Formula;
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

    public Upgrade(String name, Formula priceFormula, Currency purchaseCurrency, String description) {
        this.name = name;
        this.priceFormula = priceFormula;
        this.purchaseCurrency = purchaseCurrency;
        this.upgradeButton = new UpgradeButton(new Picture(0, 0, Const.UPGRADE_ICON_SIZE, Const.UPGRADE_ICON_SIZE, Global.programDirectory + "Images/Icons/Upgrade/" + this.name + ".png"), this);
        this.description = description;

        this.isUnlocked = true;
        this.calculatePrice();
    }

    public Upgrade(String name, Formula priceFormula, Currency purchaseCurrency) {
        this.name = name;
        this.priceFormula = priceFormula;
        this.purchaseCurrency = purchaseCurrency;
        this.upgradeButton = new UpgradeButton(new Picture(0, 0, Const.UPGRADE_ICON_SIZE, Const.UPGRADE_ICON_SIZE, Global.programDirectory + "Images/Icons/Upgrade/" + this.name + ".png"), this);
        this.description = "";

        this.isUnlocked = true;
        this.calculatePrice();
    }

    abstract public void calculatePrice();

    public boolean isPurchasable() {
        return this.purchaseCurrency.amount.isGreaterEqualTo(this.price);
    }

    public abstract void buy();
}
