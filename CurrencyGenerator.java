


class CurrencyGenerator extends Generator{
    Currency produce; 
    
    CurrencyGenerator(String name, Currency currencyProduced, BigNum baseProduction){
      super(name, baseProduction);
      this.produce = currencyProduced;
    }
    
    void generate(){
      this.produce.amount.add(production);
    }
}