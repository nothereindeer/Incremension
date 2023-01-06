

class BoostingCurrency extends Currency{
  Formula formula;
  Currency boostedCurrency;
  
  BoostingCurrency(String name, Currency boostedCurrency, Formula formula){
    super(name);
    this.boostedCurrency = boostedCurrency;
    this.formula = formula;
  }
}