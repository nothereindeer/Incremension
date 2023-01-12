

class Generator{
    
  String name; 
  BigNum production; 
  Currency amount;
  Currency produce;
 
  Generator(String name, Picture icon, Currency currencyProduced, BigNum baseProduction){
    this.name = name; 
    this.production = baseProduction;
    this.amount = new Currency(name, icon);
    this.produce = currencyProduced;
  }
    
  void generate(){
    this.produce.amount.add(BigNum.multiply(this.amount.amount, this.production));
  }
}
