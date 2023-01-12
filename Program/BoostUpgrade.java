import java.util.HashMap;

class BoostUpgrade extends Upgrade{
  
  int maxLevel;
  
  HashMap<Currency, Formula> boostedCurrencies;
  Formula priceFormula;
  
  int level;
  
  BoostUpgrade(String name, BigNum price, Currency purchaseCurrency, Currency[] boostedCurrencies, String[] boostFormulas, int maxLevel, String displayScreen){
    
    super(name, price, purchaseCurrency, displayScreen);

    this.maxLevel = maxLevel;
    
    this.isUnlocked = false;
    this.level = 0;
    
    for (int i = 0; i < boostedCurrencies.length; i ++){
      this.boostedCurrencies.put(boostedCurrencies[i], new Formula(boostFormulas[i]));
    }
  }
  
  BoostUpgrade(String name, BigNum price, Currency purchaseCurrency, Currency boostedCurrency, String boostFormula, int maxLevel, String displayScreen){
    
    super(name, price, purchaseCurrency, displayScreen);

    this.maxLevel = maxLevel;
    
    this.isUnlocked = false;
    this.level = 0;
    
    this.boostedCurrencies.put(boostedCurrency, new Formula(boostFormula));
  }
  
  void buy(int levelAmount){
    if (this.level < this.maxLevel && this.purchaseCurrency.amount.isGreaterEqualTo(this.price)){
      this.purchaseCurrency.amount.subtract(this.price);
      this.level = this.level + 1;
    }
  }
  
  void reset(boolean isUnlocked){
    this.level = 0;
    this.isUnlocked = isUnlocked;
  }
  
  BigNum calculateBoost(Currency currency){
    Formula formula = boostedCurrencies.get(currency);
    return formula.calculate(this.level);
  }
  
}
