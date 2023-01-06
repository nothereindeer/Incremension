

abstract class Generator{
    
  String name; 
  BigNum production; 
  BigNum amount;
  
  Generator(String name, BigNum baseProduction){
    this.name = name; 
    this.production = baseProduction;
  }  
  
  void addProduction(){
    this.produce.amount.add(this.produce.finalBoost.multiply(this.production));
  }
}