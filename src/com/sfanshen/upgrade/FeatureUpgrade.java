package com.sfanshen.upgrade;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.Formula;

public class FeatureUpgrade extends Upgrade {
  
  public boolean isBought;
  
  FeatureUpgrade(String name, Formula priceFormula, Currency purchaseCurrency, boolean isPermanent){
 
    super(name, priceFormula, purchaseCurrency);
    
    this.isBought = false;
    this.isUnlocked = false;
  }

  public void buy(){
    if (!this.isBought && this.purchaseCurrency.amount.isGreaterEqualTo(this.price)){
      this.purchaseCurrency.amount.subtract(this.price);
      this.isBought = true;
    }
  }

  public void reset(boolean isUnlocked){
    this.isBought = false;
    this.isUnlocked = isUnlocked;
  }
}
