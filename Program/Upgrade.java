import javax.swing.*;
import java.awt.*;


abstract class Upgrade{
  
  String name;
  Formula priceFormula;
  BigNum price;
  Currency purchaseCurrency;
  UpgradeButton upgradeButton;
  boolean isUnlocked;
  
  Upgrade(String name, Formula priceFormula, Currency purchaseCurrency, String displayScreen){
    this.name = name;
    this.priceFormula = priceFormula;
    this.purchaseCurrency = purchaseCurrency;
    this.upgradeButton = new UpgradeButton();
    this.isUnlocked = false;
  }


  void calculatePrice() {

  }
  void draw(Graphics g, int x, int y){
    this.upgradeButton.draw(g, x, y, this.purchaseCurrency.icon);
  }
}
