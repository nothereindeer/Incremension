

class BoostingCurrency extends Currency{
  Formula formula;
  Currency boostedCurrency;
  
  BoostingCurrency(String name, Image icon, Currency boostedCurrency, Formula formula){
    super(name, icon);
    this.boostedCurrency = boostedCurrency;
    this.formula = formula;
  }
}