package com.sfanshen.main;

import com.sfanshen.currency.Currency;
import com.sfanshen.graphics.GameFrame;
import com.sfanshen.graphics.GameTab;
import com.sfanshen.graphics.UpgradeTab;
import com.sfanshen.ui.BoardAndMouse;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.FeatureUpgrade;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;


//Main Program
public class FinalGame {
    public static void main(String[] args) {

        //Temporary variable
        boolean isRunning = true;


        //Default Screens
        Global.currentScreen = "main menu";
        Global.currentTab = "coin upgrades";

        //Initializes all global variables(variables are assigned values)
        Global.initialize();

        //Quick access
        Currency coins = Global.currencies.get("coins");
        Currency prestigePoints = Global.currencies.get("prestige points");

        //UI
        GameFrame gameFrame = new GameFrame();


        //-------------------------------------------------------Game Loop-----------------------------------------------------------------\\
        while (isRunning) {
            //Determines which screen's information to display
            BoardAndMouse bAndM = new BoardAndMouse();
            switch (Global.currentScreen) {
                case "main menu":

                case "main":

                case "settings":

            }

            //Updates graphics
            gameFrame.updateFrame();
            //Temp
            coins.increase(1);
        }
    }


    //-------------------------------------------------------Methods-----------------------------------------------------------------\\


    //-----------------------File Saving & Loading-----------------------\\
    public static void updateSaveVersion() throws Exception {
        File saveVersionFile = new File("Save Version.txt");
        Scanner sc = new Scanner(saveVersionFile);

        Global.saveVersion = sc.nextInt();
    }

    public static void loadProgress() throws Exception {
        updateSaveVersion();
        File saveFile = new File("../Saves/test" + Global.saveVersion + ".txt");
        Scanner sc = new Scanner(saveFile);

        String line = "";

        while (!line.equals("Currencies")) {
            line = sc.nextLine();
        }

        line = sc.nextLine();

        //Reached start of currency saves
        while (!line.equals("")) {
            Currency currency = Global.currencies.get(line.split(":")[0]);
            currency.amount.set(Integer.parseInt(line.split(":")[1]));
            line = sc.nextLine();
        }

        sc.nextLine();
        line = sc.nextLine();
        //Reached start of upgrade saves
        while (!line.equals("")) {
            Upgrade upgrade = Global.upgrades.get(line.split(":")[0]);
            if (upgrade instanceof BoostUpgrade) {
                BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
                boostUpgrade.level = Integer.parseInt(line.split(":")[1]);
            } else if (upgrade instanceof FeatureUpgrade) {
                FeatureUpgrade featureUpgrade = (FeatureUpgrade) upgrade;
                featureUpgrade.isBought = (Integer.parseInt(line.split(":")[1]) > 0);
            }

            line = sc.nextLine();
        }
    }

    public static void saveProgress() throws Exception {
        updateSaveVersion();
        File saveFile = new File("../Saves/test" + (Global.saveVersion + 1) + ".txt");
        if (saveFile.createNewFile())
            System.out.println("New save file created");
        else
            System.out.println("File already exists");
        PrintWriter output = new PrintWriter(saveFile);


        output.println("Currencies:");

        for (Currency currency : Global.currencies.values()) {
            output.println(currency.name + ":" + currency.amount.bigNum);
        }

        output.println("\nUpgrades:");

        for (GameTab gameTab : Global.gameTabs.values()) {
            if (gameTab instanceof UpgradeTab) {
                UpgradeTab upgradeTab = (UpgradeTab) gameTab;

                for (UpgradesFrame upgradesFrame : upgradeTab.upgradesFrames) {
                    for (Upgrade upgrade : upgradesFrame.upgrades) {
                        output.print(upgrade.name + ":");
                        if (upgrade instanceof BoostUpgrade) {
                            BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
                            output.println(boostUpgrade.level);
                        } else if (upgrade instanceof FeatureUpgrade) {
                            FeatureUpgrade featureUpgrade = (FeatureUpgrade) upgrade;
                            if (featureUpgrade.isBought) {
                                output.println("1");
                            } else {
                                output.println("0");
                            }
                        } else {
                            System.out.println("Error while saving files: Attempted looping through upgrades, found illegal upgrade type");
                        }
                    }
                }
            }
        }

    }


}
