package com.sfanshen.currency;

import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Formula;

public class BoostingCurrency extends Currency{
  Formula formula;
  Currency boostedCurrency;
  
  public BoostingCurrency(String name, Picture icon, Currency boostedCurrency, Formula formula){
    super(name, icon);
    this.boostedCurrency = boostedCurrency;
    this.formula = formula;
  }
}
