package com.sfanshen.currency;

import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;

public class Generator{
    
  String name; 
  BigNum production;
  Currency amount;
  Currency produce;
 
  public Generator(String name, Picture icon, Currency currencyProduced, BigNum baseProduction){
    this.name = name; 
    this.production = baseProduction;
    this.amount = new Currency(name, icon);
    this.produce = currencyProduced;
  }
    
  public void generate(){
    this.produce.amount.add(BigNum.multiply(this.amount.amount, this.production));
  }
}
