package com.sfanshen.main;

import com.sfanshen.currency.Currency;
import com.sfanshen.currency.Generator;
import com.sfanshen.graphics.GameTab;
import com.sfanshen.graphics.GeneratorTab;
import com.sfanshen.graphics.Picture;
import com.sfanshen.graphics.UpgradeTab;
import com.sfanshen.ui.TabSwitchButton;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;

import java.util.HashMap;
import java.util.ArrayList;

public class Global {

    //-------------------------------------------------------Variables-----------------------------------------------------------------\\

    public static int saveVersion;

    //Easy-access libraries
    public static HashMap<String, Currency> currencies;
    public static HashMap<String, Upgrade> upgrades;
    public static HashMap<String, GameTab> gameTabs;


    //BUTTON
    public static ArrayList<TabSwitchButton> tabSwitchButtons;

    //Displayed screen
    public static String currentScreen;
    public static String currentTab;

    //Icons
    public static Picture boostUpgradeIcon, featureUpgradeIcon;
    public static Picture playButton, titleImage, loadingBar;

    public static String programDirectory;

    //Mouse Coordinates
    public static double mouseX, mouseY;

    //-------------------------------------------------------Methods-----------------------------------------------------------------\\


    //Initializes all variables
    public static void initialize() {
        mouseX = 0;
        mouseY = 0;

        gameTabs = new HashMap<>();
        currencies = new HashMap<>();
        upgrades = new HashMap<>();

        saveVersion = 0;

        programDirectory = "src/com/sfanshen/"; //"../"
        String iconImageDirectory = programDirectory + "Images/Icons/";
        String mainMenuDirectory = programDirectory + "Images/Title screen/";
        Picture coinIcon = new Picture(0, 0, Const.CURRENCY_ICON_WIDTH, Const.CURRENCY_ICON_HEIGHT, iconImageDirectory + "Currency/Coin.png");
        Picture rubyIcon = new Picture(0, 0, Const.CURRENCY_ICON_WIDTH, Const.CURRENCY_ICON_HEIGHT, iconImageDirectory + "Currency/Ruby.png");

        boostUpgradeIcon = new Picture(0, 0, Const.UPGRADE_TYPE_ICON_WIDTH, Const.UPGRADE_TYPE_ICON_HEIGHT, iconImageDirectory + "Upgrade Type/Boost Upgrade.png");
        featureUpgradeIcon = new Picture(0, 0, Const.UPGRADE_TYPE_ICON_WIDTH, Const.UPGRADE_TYPE_ICON_HEIGHT, iconImageDirectory + "Upgrade Type/Feature Upgrade.png");

        playButton = new Picture(0, 0, mainMenuDirectory + "Play button.png");
        titleImage = new Picture(0, 0, mainMenuDirectory + "Title.png");
        loadingBar = new Picture(0, 0, mainMenuDirectory + "Title.png");


        currencies.put("coins", (new Currency("coins", coinIcon)));
        currencies.put("rubies", (new Currency("rubies", rubyIcon)));




        /*To create upgrades:
           String name, Formula priceFormula, Currency buyCurrency, Currency boostCurrency, Formula boostFormula, int maxLevel

           Note: boostCurrency and boostFormula may be replaced with arrays of Currencies and Formulas respectively should the upgrade boost multiple different currencies

           The first character in a formula string will determine its boost to its respective currency(* means multiplicative, + means additive, ^ means exponential)
           Additionally, formulas must be written in one of the following three formats (coefficients of 1 must be included, spaces must be included as well):
           mx + b
           ax^2 + bx + c
           ax^3 + bx^2 + cx + d
           More types of formulas may be added in the future should the need arise
         */

        Upgrade[] coinUpgrades = {
                new BoostUpgrade("Better pickaxes", new Formula("*100x + 100"), currencies.get("coins"), currencies.get("coins"), "*1x + 1", 10),
                new BoostUpgrade("Drills", new Formula("*5000x + 5000"), currencies.get("coins"), currencies.get("coins"), "*3x + 0", 1)
        };

        Generator[] coinGenerators = {
                new Generator("miner", currencies.get("coins"), new BigNum(1), iconImageDirectory + "Generator/Miner.png")
        };

        new UpgradeTab("coin upgrades", new Upgrade[][]{coinUpgrades});
        new GeneratorTab("coin generators", coinGenerators);
        addUpgToList();
        organizeUpgrades();
        tabSwitchButtons = new ArrayList<>();
    }

    //Crams all upgrades into the upgrade dictionary
    public static void addUpgToList() {
        for (GameTab gameTab : gameTabs.values()) {
            if (gameTab instanceof UpgradeTab) {
                UpgradeTab upgradeTab = (UpgradeTab) gameTab;
                for (UpgradesFrame upgradeFrame : upgradeTab.upgradesFrames) {
                    determineUpgradeDimesions(upgradeFrame);
                    for (Upgrade upgrade : upgradeFrame.upgrades) {
                        upgrades.put(upgrade.name, upgrade);
                    }
                }
            }
        }

    }

    //Stores all related upgrades under each currency
    public static void organizeUpgrades() {
        for (Upgrade upgrade : upgrades.values()) {
            if (upgrade instanceof BoostUpgrade) {
                BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
                for (Currency currency : currencies.values()) {
                    if (boostUpgrade.boostedCurrencies.containsKey(currency)) {
                        organizeUpgradesByOperators(boostUpgrade, currency);
                    }
                }
            }
        }
    }

    public static void organizeUpgradesByOperators(BoostUpgrade boostUpgrade, Currency currency) {
        Formula formula = boostUpgrade.boostedCurrencies.get(currency);
        if (formula.operation.equals("+") || formula.operation.equals("-"))
            currency.additiveUpgrades.add(boostUpgrade);
        else if (formula.operation.equals("*") || formula.operation.equals("/"))
            currency.multiplicativeUpgrades.add(boostUpgrade);
        else if (formula.operation.equals("^"))
            currency.exponentialUpgrades.add(boostUpgrade);
    }


    public static String capitalizeFirstLetters(String str) {
        String[] strings = str.split(" ");
        StringBuilder returnString = new StringBuilder();
        for (String string : strings) {
            returnString.append(" ").append(Character.toUpperCase(string.charAt(0))).append(string.substring(1));
        }

        return returnString.toString().trim();
    }

    public static void determineUpgradeDimesions(UpgradesFrame upgradesFrame) {
        for (int i = 0; i < upgradesFrame.upgrades.size(); i++) {
            Upgrade upgrade = upgradesFrame.upgrades.get(i);

            int x = upgradesFrame.calculateCoords(i)[0];
            int y = upgradesFrame.calculateCoords(i)[1];

            //If upgrade is unlocked
            upgrade.upgradeButton.x = x;
            upgrade.upgradeButton.y = y;
            upgrade.upgradeButton.width = Const.UPGRADE_WIDTH;
            upgrade.upgradeButton.height = Const.UPGRADE_HEIGHT;
        }
    }


}
