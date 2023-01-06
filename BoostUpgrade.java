import java.util.HashMap;

class BoostUpgrade extends Upgrade{
  
  String name;
  BigNum maxLevel;
  
  HashMap<Currency, Formula> boostedCurrencies;
  Formula priceFormula;
  
  boolean isUnlocked;
  BigNum level;
  
  BoostUpgrade(String name, BigNum price, Currency purchaseCurrency, Currency[] boostedCurrencies, String[] boostFormulas, BigNum maxLevel, int x, int y, int width, int height, String displayScreen){
    
    super(name, price, purchaseCurrency, x, y, width, height, displayScreen);

    this.maxLevel = maxLevel;
    
    this.isUnlocked = false;
    this.level.set(0);
    
    for (int i = 0; i < boostedCurrencies.length; i ++){
      this.boostedCurrencies.put(boostedCurrencies[i], new Formula(boostFormulas[i]));
    }
  }
  
  void buy(int levelAmount){
    if (this.level.isLessThan(this.maxLevel) && this.purchaseCurrency.amount.isGreaterEqualTo(this.price)){
      this.purchaseCurrency.amount.subtract(this.price);
      this.level.add(1);
    }
  }
  
  void reset(boolean isUnlocked){
    this.level.set(0);
    this.isUnlocked = isUnlocked;
  }
  
  BigNum calculateBoost(Currency currency){
    Formula formula = boostedCurrencies.get(currency);
    return formula.calculate(this.level);
  }
  
}