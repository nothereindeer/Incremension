package com.sfanshen.main;

import com.sfanshen.currency.Currency;
import com.sfanshen.music.Audio; 
import com.sfanshen.generator.Generator;
import com.sfanshen.graphics.*;
import com.sfanshen.ui.TabSwitchButton;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.awt.*;
import javax.swing.*; 

public class Global {

    //-------------------------------------------------------Variables-----------------------------------------------------------------\\

    public static int saveVersion;
    
    //Music
    public static Audio backMusic; 
    public static Audio startGame; 

    //Easy-access libraries
    public static LinkedHashMap<String, Currency> currencies;
    public static HashMap<String, Upgrade> upgrades;
    public static HashMap<String, Generator> generators;
    public static LinkedHashMap<String, GameTab> gameTabs;


    //BUTTON
    public static ArrayList<TabSwitchButton> tabSwitchButtons;

    //Displayed screen
    public static String currentScreen;
    public static String currentTab;

    //Icons
    public static Picture boostUpgradeIcon, featureUpgradeIcon;
    public static Picture playButton, playButtonDark, titleImage, loadingBar;

    public static String programDirectory;

    //Mouse Coordinates
    public static double mouseX, mouseY;

    //Tickspeed
    public static int ticksPerSec;

    //-------------------------------------------------------Methods-----------------------------------------------------------------\\


    //Initializes all variables
    public static void initialize() {

        ticksPerSec = 20;

        mouseX = 0;
        mouseY = 0;

        gameTabs = new LinkedHashMap<>();
        currencies = new LinkedHashMap<>();
        generators = new HashMap<>();
        upgrades = new HashMap<>();

        saveVersion = 0;

        programDirectory = "src/com/sfanshen/"; //"../"
        String iconImageDirectory = programDirectory + "Images/Icons/";
        String mainMenuDirectory = programDirectory + "Images/Title screen/";
        Picture coinIcon = new Picture(0, 0, Const.CURRENCY_ICON_SIZE, Const.CURRENCY_ICON_SIZE, iconImageDirectory + "Currency/Coin.png");
        Picture rubyIcon = new Picture(0, 0, Const.CURRENCY_ICON_SIZE, Const.CURRENCY_ICON_SIZE, iconImageDirectory + "Currency/Ruby.png");

        boostUpgradeIcon = new Picture(0, 0, Const.UPGRADE_TYPE_ICON_SIZE, Const.UPGRADE_TYPE_ICON_SIZE, iconImageDirectory + "Upgrade Type/Boost Upgrade.png");
        featureUpgradeIcon = new Picture(0, 0, Const.UPGRADE_TYPE_ICON_SIZE, Const.UPGRADE_TYPE_ICON_SIZE, iconImageDirectory + "Upgrade Type/Feature Upgrade.png");

        playButtonDark = new Picture(0, 0, mainMenuDirectory + "Play button dark.png", true);
        playButton = new Picture(0, 0, mainMenuDirectory + "Play button.png", false);
        playButtonDark.resize(0.6);
        playButton.resize(0.6);

        titleImage = new Picture(0, 0, mainMenuDirectory + "Title.png");
        loadingBar = new Picture(0, 0, mainMenuDirectory + "Title.png");
        titleImage.resize(1.5);


        currencies.put("Coins", (new Currency("Coins", coinIcon)));
        currencies.put("Rubies", (new Currency("Rubies", rubyIcon)));

        backMusic = new Audio(programDirectory + "music/musicFiles/Background music.wav");
        startGame = new Audio(programDirectory + "music/musicFiles/Play button.wav"); 
        tabSwitchButtons = new ArrayList<>();



        /* To create upgrades:
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
                createCoinUpgrade("Better pickaxes", "*100x + 100", "*1x + 1", 10),
                createCoinUpgrade("Drills", "*5000x + 5000", "*3x + 1", 1),
                createCoinUpgrade("Bulldozers", "*5e4x^2 + 5e5x + 1e5", "*0.5x^2 + 1.5x + 1", 3),
                createCoinUpgrade("Mines", "*7.5e6x^2 + 2e7x + 1.5e7", "*0.25x^2 + 0.5x + 1", 5),
                createCoinUpgrade("Energy drinks", "*2e10x^2 + 8.5e10x + 7.5e10", "+0.05x^2 + 0.35x + 0", 5)
        };

        /* To create generators:
            String name, Currency currencyProduced, BigNum baseProduction, Formula priceFormula, Currency purchaseCurrency

            Note: currencyProduced can be a different generator. Every generator has a respective currency class. If a generator generator is wished to be created simply input otherGenerator.amount in the currencyProduced parameter
            baseProduction is production per second

            Formula rules apply as in upgrade creation
         */
        Generator[] coinGenerators = {
                new Generator("Miner", "Coins","1","Coins","*0.5x^2 + 3x + 5", new String[]{
                        "5e4",
                        "1e8",
                        "5e14"
                }),
                new Generator("Foreman", "Miner", "0.9", "Coins", "*2.5e3x^2 + 8e3x + 3e4", new String[]{
                        "5e12",
                        "5e19"
                }),
                new Generator("A.I miner", "Foreman", "0.8", "Coins", "*2e7x^2 + 7.5e7x + 1e8", new String[]{
                        "5e15",
                        "5e24"
                }),
                new Generator("Jumbo drill", "A.I miner", "0.7", "Coins", "*3e9x + 5e9", new String[]{
                        "5e18",
                        "5e28"
                }),
                new Generator("Dynamiter", "Jumbo drill", "0.6", "Coins", "*7e10x + 6e10", new String[]{
                        "5e21",
                        "5e33"
                }),
                new Generator("Crystal miner", "Dynamiter", "0.5", "Coins", "*1e12x + 5e11", new String[]{
                        "5e24",
                        "5e39"
                }),
                new Generator("A.I mine", "Crystal miner", "0.4", "Coins", "*5e13x + 1e13", new String[]{
                        "5e27",
                        "5e45"
                }),
                new Generator("Excavator", "A.I mine", "0.3", "Coins", "*8e14x + 1e15", new String[]{
                        "5e30",
                        "5e56"
                })
        };



        new GeneratorTab("coin generators", coinGenerators);
        new UpgradeTab("coin upgrades", new Upgrade[][]{coinUpgrades});

        determineUISize();
        organizeUpgrades();
        createTabSwitchButtons();

        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        
       
        
    }


    public static BoostUpgrade createCoinUpgrade(String name, String costFormula, String boostFormula, int maxLevel){
        return new BoostUpgrade(name, costFormula, "Coins", "Coins", boostFormula, maxLevel, "Rubies");

    }

    public static void createTabSwitchButtons() {

        int height = Const.TAB_SELECTION_HEIGHT;
        int i = 0;
        for (GameTab gameTab : Global.gameTabs.values()) {
            int width = (Const.FRAME_WIDTH - 2 * Const.TAB_SELECTION_OFFSET) / Global.gameTabs.size();

            int x = Const.FRAME_X + Const.TAB_SELECTION_OFFSET + (i * width);
            int y = Const.FRAME_Y + Const.FRAME_HEIGHT;

            tabSwitchButtons.add(new TabSwitchButton(x, y, width, height, gameTab.name));
            i = i + 1;
        }
    }

    //Crams all upgrades into the upgrade dictionary
    public static void determineUISize() {
        for (GameTab gameTab : gameTabs.values()) {
            if (gameTab instanceof UpgradeTab) {
                UpgradeTab upgradeTab = (UpgradeTab) gameTab;
                for (UpgradesFrame upgradeFrame : upgradeTab.upgradesFrames) {
                    determineUpgradeDimensions(upgradeFrame);
                }
            } else if (gameTab instanceof GeneratorTab) {
                GeneratorTab generatorTab = (GeneratorTab) gameTab;
                determineGeneratorDimensions(generatorTab);
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

    public static void determineUpgradeDimensions(UpgradesFrame upgradesFrame) {
        for (int i = 0; i < upgradesFrame.upgrades.size(); i++) {
            Upgrade upgrade = upgradesFrame.upgrades.get(i);

            upgrades.put(upgrade.name, upgrade);

            int x = upgradesFrame.calculateCoords(i)[0];
            int y = upgradesFrame.calculateCoords(i)[1];

            //If upgrade is unlocked
            upgrade.upgradeButton.x = x;
            upgrade.upgradeButton.y = y;
            upgrade.upgradeButton.width = Const.UPGRADE_SIZE;
            upgrade.upgradeButton.height = Const.UPGRADE_SIZE;
        }
    }

    public static void determineGeneratorDimensions(GeneratorTab generatorTab) {
        int amtOnColumn = (Const.FRAME_HEIGHT - Const.GENERATOR_FRAME_Y_OFFSET) / (Const.GENERATOR_FRAME_HEIGHT + Const.GENERATOR_FRAME_Y_OFFSET);
        for (int i = 0; i < generatorTab.generators.size(); i++) {
            Generator generator = generatorTab.generators.get(i);

            generators.put(generator.name, generator);

            int x;
            int y;
            if (i >= amtOnColumn) {
                x = Const.FRAME_X + Const.GENERATOR_FRAME_WIDTH + Const.GENERATOR_FRAME_X_OFFSET * 2;
                y = Const.FRAME_Y + Const.GENERATOR_FRAME_Y_OFFSET + (i - amtOnColumn) * (Const.GENERATOR_FRAME_HEIGHT + Const.GENERATOR_FRAME_Y_OFFSET);
            } else {
                x = Const.FRAME_X + Const.GENERATOR_FRAME_X_OFFSET;
                y = Const.FRAME_Y + Const.GENERATOR_FRAME_Y_OFFSET + i * (Const.GENERATOR_FRAME_HEIGHT + Const.GENERATOR_FRAME_Y_OFFSET);
            }

            generator.generatorFrame.y = y;
            generator.generatorFrame.x = x;
            generator.generatorFrame.updateButtonDims();
        }
    }

    public static Currency findCurrency(String currency){
        if (Global.currencies.containsKey(currency))
            return Global.currencies.get(currency);
        else if (Global.generators.containsKey(currency))
            return Global.generators.get(currency).generatedAmount;
        else{
            System.out.println("Error while finding currency: Nonexistent currency: " + currency);
            return null;
        }
    }
}
