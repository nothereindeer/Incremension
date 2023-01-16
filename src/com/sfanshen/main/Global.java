package com.sfanshen.main;

import com.sfanshen.currency.Currency;
import com.sfanshen.graphics.GameTab;
import com.sfanshen.graphics.Picture;
import com.sfanshen.graphics.UpgradeTab;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;

import java.util.HashMap;
import java.util.ArrayList;

public class Global{

  //-------------------------------------------------------Variables-----------------------------------------------------------------\\

  public static int saveVersion;

  //Easy-access libraries
  public static HashMap<String, Currency> currencies;
  public static HashMap<String, Upgrade> upgrades;
  public static HashMap<String, GameTab> gameTabs;

  //Displayed screen
  public static String currentScreen;
  public static String currentUpgradeFrame;

  //Icons
  public static Picture boostUpgradeIcon, featureUpgradeIcon;
  public static Picture playButton, titleImage, loadingBar;

  public static String programDirectory;


  //-------------------------------------------------------Methods-----------------------------------------------------------------\\


  //Initializes all variables
  public static void initialize(){

    gameTabs = new HashMap<>();
    currencies = new HashMap<>();
    upgrades = new HashMap<>();

    saveVersion = 0;

    programDirectory = "src/com/sfanshen/"; //"../"
    String iconImageDirectory = programDirectory + "Images/Icons/";
    String mainMenuDirectory = programDirectory + "Images/Title screen/";
    Picture coinIcon = new Picture(0, 0, Const.CURRENCY_ICON_WIDTH,  Const.CURRENCY_ICON_HEIGHT, iconImageDirectory + "Currency/Coin.png");
    Picture rubyIcon = new Picture(0, 0, Const.CURRENCY_ICON_WIDTH, Const.CURRENCY_ICON_HEIGHT,  iconImageDirectory + "Currency/Ruby.png");

    boostUpgradeIcon = new Picture(0, 0, Const.CURRENCY_ICON_WIDTH, Const.CURRENCY_ICON_HEIGHT, iconImageDirectory + "Upgrade Type/Boost Upgrade.png");
    featureUpgradeIcon = new Picture(0, 0, Const.CURRENCY_ICON_WIDTH, Const.CURRENCY_ICON_HEIGHT, iconImageDirectory + "Upgrade Type/Feature Upgrade.png");

    playButton =  new Picture(0, 0, Const.CURRENCY_ICON_WIDTH, Const.CURRENCY_ICON_HEIGHT, mainMenuDirectory + "Play button.png");
    titleImage = new Picture(0, 0, Const.CURRENCY_ICON_WIDTH, Const.CURRENCY_ICON_HEIGHT, mainMenuDirectory + "Title.png");
    loadingBar = new Picture(0, 0, Const.CURRENCY_ICON_WIDTH, Const.CURRENCY_ICON_HEIGHT, mainMenuDirectory + "Title.png");


    currencies.put("coins", (new Currency("coins", coinIcon)));
    currencies.put("rubies", (new Currency("rubies", rubyIcon)));



    //String name, com.sfanshen.main.BigNum price, com.sfanshen.currency.Currency purchaseCurrency, com.sfanshen.currency.Currency[] boostedCurrencies, String[] boostFormulas, int maxLevel, String displayScreen


    UpgradesFrame coinUpgrades = new UpgradesFrame("coin upgrades", 0, 0, 400, 400, new Upgrade[]{
            new BoostUpgrade("Better pickaxes", new Formula("(100x)*"), currencies.get("coins"), currencies.get("coins"), "(1+x)*", 10),
            new BoostUpgrade("Drills", new Formula("(1000x)*"), currencies.get("coins"), currencies.get("coins"), "(3x)*", 1)
    });

    addUpgToList();
  }

  //Crams all upgrades into the upgrade dictionary
  public static void addUpgToList() {
    for (GameTab gameTab: gameTabs.values()){
      if (gameTab instanceof UpgradeTab){
        UpgradeTab upgradeTab = (UpgradeTab) gameTab;
        for (UpgradesFrame upgradeFrame: upgradeTab.upgradesFrames){
          for (Upgrade upgrade: upgradeFrame.upgrades){
            upgrades.put(upgrade.name, upgrade);
          }
        }
      }
    }

  }

  //Stores all related upgrades under each currency
  public static void organizeUpgrades(){
    for (Upgrade upgrade : upgrades.values()){
      if (upgrade instanceof BoostUpgrade){
        BoostUpgrade boostUpgrade = (BoostUpgrade)upgrade;
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
    if (formula.operation.equals("+") || formula.operation.equals("-"))
      currencies.get("coins").additiveUpgrades.add(boostUpgrade);
    else if (formula.operation.equals("*") || formula.operation.equals("/"))
      currencies.get("coins").multiplicativeUpgrades.add(boostUpgrade);
  }
}
