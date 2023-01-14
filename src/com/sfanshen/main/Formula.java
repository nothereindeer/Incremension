package com.sfanshen.main;

public class Formula{
  
  String formula;
  String operation;


  //-------------------------------------------------------Constructor-----------------------------------------------------------------\\

  public Formula(String formula){
    this.formula = formula.substring(0, formula.length() - 1);
    this.operation = Character.toString(formula.charAt(formula.length() - 1));
  }


  //-------------------------------------------------------Methods-----------------------------------------------------------------\\

  //Runs formula, returns value based off of inputted value as a BigNum
  public BigNum calculate(BigNum x){
    if (this.formula.equals("(x^2)*"))
      return BigNum.pow(x, 2);
    else if (this.formula.equals("(1+x)*"))
      return BigNum.add(x, 1);
    else
      return new BigNum(1);
  }
  public BigNum calculate(int w){
    BigNum x = new BigNum(w);
    if (this.formula.equals("(x^2)*"))
      return BigNum.pow(x, 2);
    else if (this.formula.equals("(1+x)*"))
      return BigNum.add(x, 1);
    else
      return new BigNum(1);
  }


}
