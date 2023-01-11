import java.util.HashMap;
import java.util.ArrayList;

import java.io.File;
import java.io.PrintWriter;

public class Global{
  public static int saveVersion;
  
  public static HashMap<String, Currency> currencies;
  public static HashMap<String, Upgrade> upgrades;
  
  public static ArrayList<UpgradesFrame> upgradesFrames;
  
  
    
  public static void initialize(){
    saveVersion = 0;
    
    String imageDirectory = "../Images";
    Image coinIcon = new Image(0, 0, imageDirectory + "/coinIcon.png");
    Image rubyIcon = new Image(0, 0, imageDirectory + "/rubyIcon.png");
    
    currencies = new HashMap<String, Currency>();
    currencies.put("coins", (new Currency("coins", coinIcon)));
    currencies.put("rubies", (new Currency("rubies", rubyIcon)));
    

    
    //String name, int price, Currency purchaseCurrency, Currency boostedCurrency, String boostFormula, int maxLevel, int x, int y, int width, int height, String displayScreen
    //upgrades.add(new LeveledUpgrade("Better Pickaxes", 100, Global.currencies.get("coins"), Global.currencies.get("coins"), "(1+x)*", 10, 100, 100, 50, 50, "upgrades"));
    
  }
  
  
  public static void organizeUpgrades(){
    for (Upgrade upgrade : upgrades.values()){
      if (upgrade instanceof BoostUpgrade){
        BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
        if (boostUpgrade.boostedCurrencies.containsKey(currencies.get("coins"))){
          organizeUpgradesByOperators(boostUpgrade, currencies.get("coins"));
        }
        if (boostUpgrade.boostedCurrencies.containsKey(currencies.get("rubies")))
          organizeUpgradesByOperators(boostUpgrade, currencies.get("rubies"));
      }
    } 
  }
  public static void organizeUpgradesByOperators(BoostUpgrade boostUpgrade, Currency currency){
    Formula formula = boostUpgrade.boostedCurrencies.get(currency);
    if (formula.operation == "+" || formula.operation == "-") 
      currencies.get("coins").additiveUpgrades.add(boostUpgrade);
    else if (formula.operation == "*" || formula.operation == "/")
      currencies.get("coins").multiplicativeUpgrades.add(boostUpgrade);
  }
}