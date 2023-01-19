package com.sfanshen.upgrade;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;
import com.sfanshen.main.Formula;
import com.sfanshen.main.Global;


public abstract class Upgrade {

    public String name;
    Formula priceFormula;
    BigNum price;
    Currency purchaseCurrency;
    public UpgradeButton upgradeButton;
    boolean isUnlocked;

    public Upgrade(String name, Formula priceFormula, Currency purchaseCurrency) {
        this.name = name;
        this.priceFormula = priceFormula;
        this.purchaseCurrency = purchaseCurrency;
        this.upgradeButton = new UpgradeButton(new Picture(0, 0, Const.UPGRADE_ICON_WIDTH, Const.UPGRADE_ICON_HEIGHT, Global.programDirectory + "Images/Icons/Upgrade/" + this.name + ".png"));
        this.isUnlocked = false;
    }


    public void calculatePrice() {

    }

    public boolean isPurchasable() {
        return this.purchaseCurrency.amount.isGreaterEqualTo(this.price);
    }

    public abstract void buy();
}
