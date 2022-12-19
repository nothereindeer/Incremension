


abstract class Upgrade{
  
  String name;
  int price;
  Currency purchaseCurrency;
  
  Upgrade(String name, int price, Currency purchaseCurrency){
    this.name = name;
    this.price = price;
    this.purchaseCurrency = purchaseCurrency;
  }
  
  abstract void buy(int levelAmount);
  
  abstract void reset(boolean isUnlocked);
}