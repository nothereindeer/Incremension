import javax.swing.*;
import java.awt.*;


abstract class Upgrade{
  
  String name;
  BigNum price;
  Currency purchaseCurrency;
  UpgradeButton upgradeButton;
  boolean isUnlocked;
  
  Upgrade(String name, BigNum price, Currency purchaseCurrency, int x, int y, int width, int height, String displayScreen){
    this.name = name;
    this.price = price;
    this.purchaseCurrency = purchaseCurrency;
    this.upgradeButton = new UpgradeButton(x, y, width, height, displayScreen);
    this.isUnlocked = false;
  }
  
  void draw(Graphics g, int x, int y){
    this.upgradeButton.draw(g, x, y, this.purchaseCurrency.icon);
  }
}