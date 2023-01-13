package com.sfanshen.upgrade;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;
import com.sfanshen.main.Formula;

import java.awt.*;


public abstract class Upgrade{
  
  public String name;
  Formula priceFormula;
  BigNum price;
  Currency purchaseCurrency;
  UpgradeButton upgradeButton;
  Picture icon;
  boolean isUnlocked;

  public Upgrade(String name, Formula priceFormula, Currency purchaseCurrency){
    this.name = name;
    this.priceFormula = priceFormula;
    this.purchaseCurrency = purchaseCurrency;
    this.upgradeButton = new UpgradeButton();
    this.isUnlocked = false;
    this.icon = new Picture(0, 0, "../Images/UpgradeIcon" + this.name);
  }


  public void calculatePrice() {

  }
  public void draw(Graphics g, int x, int y){
    g.setColor(Const.GOOGLE_HIGHLIGHT);
    this.upgradeButton.draw(g, x, y, this.icon, this.purchaseCurrency.icon);
  }
}
