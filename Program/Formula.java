

class Formula{
  
  String formula;
  String operation;
  
  Formula(String formula){
    this.formula = formula.substring(0, formula.length() - 1);
    this.operation = Character.toString(formula.charAt(formula.length() - 1));
  }
  
  BigNum calculate(BigNum x){
    if (this.formula == "(x^2)*")
      return BigNum.pow(x, 2);
    else if (this.formula == "(1+x)*")
      return BigNum.add(x, 1);
    else
      return new BigNum(1);
  }
  
  BigNum calculate(int w){
    BigNum x = new BigNum(w);
    if (this.formula == "(x^2)*")
      return BigNum.pow(x, 2);
    else if (this.formula == "(1+x)*")
      return BigNum.add(x, 1);
    else
      return new BigNum(1);
  }
  
  

}
