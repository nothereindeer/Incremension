

public class OneTimeUpgrade extends Upgrade{

  boolean isPermanent;
  
  String name;
  String boostFormula;
  String boostedCurrency;
  
  boolean isUnlocked;
  boolean isBought;
  
  OneTimeUpgrade(String name, int price, Currency purchaseCurrency, String boostFormula, String boostedCurrency, boolean isPermanent, int x, int y, int width, int height, String displayScreen){
    
    super(name, price, purchaseCurrency, x, y, width, height, displayScreen);
    
    this.boostFormula = boostFormula;
    this.boostedCurrency = boostedCurrency;
    this.isPermanent = isPermanent;
    
    this.isBought = false;
    this.isUnlocked = false;
  }
  
  OneTimeUpgrade(boolean unlocksFeature, String name, int price, Currency purchaseCurrency, boolean isPermanent, int x, int y, int width, int height, String displayScreen){
 
    super(name, price, purchaseCurrency, x, y, width, height, displayScreen);
    
    this.boostedCurrency = "none";
    this.boostFormula = "none";
   
    this.isPermanent = isPermanent;
    
    this.isBought = false;
    this.isUnlocked = false;
  }
  
  void buy(int levelAmount){
    if (this.isBought== false && this.purchaseCurrency.amount >= this.price){
      this.purchaseCurrency.amount = this.purchaseCurrency.amount - this.price;
      this.isBought = true;
    }
  }
  
  void reset(boolean isUnlocked){
    this.isBought = false;
    this.isUnlocked = isUnlocked;
  }
}