package com.sfanshen.upgrade;

import com.sfanshen.main.BigNum;
import com.sfanshen.main.Formula;
import com.sfanshen.currency.Currency;


import java.util.HashMap;

public class BoostUpgrade extends Upgrade{
  
  int maxLevel;
  
  public HashMap<Currency, Formula> boostedCurrencies;

  public int level;
  
  public BoostUpgrade(String name, Formula priceFormula, Currency purchaseCurrency, Currency[] boostedCurrencies, String[] boostFormulas, int maxLevel){
    
    super(name, priceFormula, purchaseCurrency);

    this.maxLevel = maxLevel;
    
    this.isUnlocked = false;
    this.level = 0;
    
    for (int i = 0; i < boostedCurrencies.length; i ++){
      this.boostedCurrencies = new HashMap<>();
      this.boostedCurrencies.put(boostedCurrencies[i], new Formula(boostFormulas[i]));
    }
  }
  
  public BoostUpgrade(String name, Formula priceFormula, Currency purchaseCurrency, Currency boostedCurrency, String boostFormula, int maxLevel){
    
    super(name, priceFormula, purchaseCurrency);

    this.maxLevel = maxLevel;
    
    this.isUnlocked = false;
    this.level = 0;

    this.boostedCurrencies = new HashMap<>();
    this.boostedCurrencies.put(boostedCurrency, new Formula(boostFormula));
  }

  public void buy(){
    if (this.level < this.maxLevel && this.purchaseCurrency.amount.isGreaterEqualTo(this.price)){
      this.purchaseCurrency.amount.subtract(this.price);
      this.level = this.level + 1;
    }
  }

  public void reset(boolean isUnlocked){
    this.level = 0;
    this.isUnlocked = isUnlocked;
  }
  
  public BigNum calculateBoost(Currency currency){
    Formula formula = boostedCurrencies.get(currency);
    return formula.calculate(this.level);
  }
  
}
