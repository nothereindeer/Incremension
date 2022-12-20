

public class LeveledUpgrade extends Upgrade{
  
  String name;
  int maxLevel;
  
  String boostFormula;
  String boostedCurrency;
  String priceFormula;
  
  boolean isUnlocked;
  int level;
  
  LeveledUpgrade(String name, int price, Currency purchaseCurrency, String boostFormula, String boostedCurrency, int maxLevel, int x, int y, int width, int height, String displayScreen){
    
    super(name, price, purchaseCurrency, x, y, width, height, displayScreen);

    this.boostFormula = boostFormula;
    this.boostedCurrency = boostedCurrency;
    this.maxLevel = maxLevel;
    
    this.isUnlocked = false;
    this.level = 0;
  }
  
  void buy(int levelAmount){
    if (this.level < this.maxLevel && this.purchaseCurrency.amount >= this.price){
      this.purchaseCurrency.amount = this.purchaseCurrency.amount - this.price;
      this.level = this.level + 1;
    }
  }
  
  void reset(boolean isUnlocked){
    this.level = 0;
    this.isUnlocked = isUnlocked;
  }
  
}