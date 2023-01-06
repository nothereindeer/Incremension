


abstract class Upgrade{
  
  String name;
  BigNum price;
  Currency purchaseCurrency;
  UpgradeButton upgradeButton;
  
  Upgrade(String name, BigNum price, Currency purchaseCurrency, int x, int y, int width, int height, String displayScreen){
    this.name = name;
    this.price = price;
    this.purchaseCurrency = purchaseCurrency;
    this.upgradeButton = new UpgradeButton(x, y, width, height, displayScreen);
  }
}