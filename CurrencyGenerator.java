


class CurrencyGenerator extends Generator{
    Currency produce; 
    
    CurrencyGenerator(String name, Currency currencyMade, BigNum baseProduction){
      super(name, baseProduction);
      this.produce = currencyMade;
    }
}