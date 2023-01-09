

class Generator{
    
  String name; 
  BigNum production; 
  Currency amount;
  Currency produce;
 
  Generator(String name, Currency currencyProduced, BigNum baseProduction){
    this.name = name; 
    this.production = baseProduction;
    this.amount = new Currency(name);
    this.produce = currencyProduced;
  }
    
  void generate(){
    this.produce.amount.add(BigNum.multiply(this.amount.amount, production));
  }
}