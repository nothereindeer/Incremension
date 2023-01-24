package com.sfanshen.main;

import com.sfanshen.currency.Currency;
import com.sfanshen.generator.Generator;
import com.sfanshen.graphics.GameFrame;
import com.sfanshen.graphics.GameTab;
import com.sfanshen.graphics.GeneratorTab;
import com.sfanshen.graphics.UpgradeTab;
import com.sfanshen.ui.BoardAndMouse;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.FeatureUpgrade;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;


//Main Program
public class  FinalGame {
    public static boolean isRunning;

    public static void main(String[] args) throws Exception {

        //Temporary variable
        isRunning = true;


        //Default Screens
        Global.currentScreen = "main menu";
        Global.currentTab = "coin generators";

        //Initializes all global variables(variables are assigned values) and thread
        Global.initialize();
        GenerationThread generators = new GenerationThread();
        generators.start();

        //Quick access
        Currency coins = Global.currencies.get("Coins");
        Currency prestigePoints = Global.currencies.get("prestige points");

        //UI
        GameFrame gameFrame = new GameFrame();
        loadProgress();
        //updateSaveVersion();

        //-------------------------------------------------------Game Loop-----------------------------------------------------------------\\
        while (isRunning) {
            //Determines which screen's information to display
            BoardAndMouse bAndM = new BoardAndMouse();
            switch (Global.currentScreen) {
                case "main menu":

                case "main":
                  
                case "settings":
                  

            }
            
            if(Global.currentScreen.equals("main")){
              Global.backMusic.start();
              Global.backMusic.loop();  
            }

            //Updates graphics
            gameFrame.updateFrame();
            //Temp
            updateMousePosition(gameFrame);
            BoardAndMouse.checkMousePosition();

            try {
                Thread.sleep(1000 / Const.FPS);
            } catch (Exception e) {
            }

        }
    }

    //-------------------------------------------------------Methods-----------------------------------------------------------------\\


    public static void updateMousePosition(GameFrame gameFrame) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(point, gameFrame.frame);
        Global.mouseX = point.getX() - Const.MOUSE_X_OFFSET;
        Global.mouseY = point.getY() - Const.MOUSE_Y_OFFSET;
    }


    //-----------------------File Saving & Loading-----------------------\\

    public static void updateSaveVersion() throws Exception {
        File saveVersionFile = new File(Global.programDirectory + "Save Version.txt");
        Scanner sc = new Scanner(saveVersionFile);

        try {
            Global.saveVersion = sc.nextInt();
        } catch (Exception e) {
            PrintWriter output = new PrintWriter(saveVersionFile);
            output.println(0);
            output.close();
        }
    }

    public static void loadProgress() throws Exception {
        updateSaveVersion();
        File saveFile = new File(Global.programDirectory + "Saves/save" + Global.saveVersion + ".txt");
        Scanner sc = new Scanner(saveFile);

        String line = "";

        while (!line.equals("Currencies:")) {
            line = sc.nextLine();
        }

        line = sc.nextLine();

        //Reached start of currency saves
        while (!line.equals("")) {
            Currency currency = Global.currencies.get(line.split(":")[0]);
            currency.amount.set(new BigNum(line.split(":")[1]));
            currency.calculateBoost();
            line = sc.nextLine();
        }

        line = sc.nextLine();
        line = sc.nextLine();
        //Reached start of upgrade saves
        while (!line.equals("")) {
            Upgrade upgrade = Global.upgrades.get(line.split(":")[0]);
            if (upgrade instanceof BoostUpgrade) {
                BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
                boostUpgrade.level = Integer.parseInt(line.split(":")[1]);
            } else if (upgrade instanceof FeatureUpgrade) {
                FeatureUpgrade featureUpgrade = (FeatureUpgrade) upgrade;
                featureUpgrade .isBought = (Integer.parseInt(line.split(":")[1]) > 0);
            }

            line = sc.nextLine();
        }



        line = sc.nextLine();
        line = sc.nextLine();
        //Reached start of generator saves
        while (!line.equals("") && sc.hasNext()) {
            Generator generator = Global.generators.get(line.split(":")[0]);
            generator.purchasedAmount = Integer.parseInt((line.split(":")[1]));
            generator.generatedAmount.set(line.split(":")[2]);
            generator.calculateTier();
            generator.calculateProductionMultiplier();
            generator.calculatePrice(); 

            line = sc.nextLine();
        }
        
    }

    public static void saveProgress() throws Exception {
        updateSaveVersion();
        //Global.saveVersion = Global.saveVersion + 1;
        File saveFile = new File(Global.programDirectory + "Saves/save" + (Global.saveVersion) + ".txt");
        PrintWriter output = new PrintWriter(saveFile);

        output.println(Global.saveVersion);

        output.println("Currencies:");

        for (Currency currency : Global.currencies.values()) {
            output.println(currency.name + ":" + currency.amount.bigNum);
        }

        output.println("\nUpgrades:");

        for (Upgrade upgrade : Global.upgrades.values()) {
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

        output.println("\nGenerators:");

        for (Generator generator : Global.generators.values()){
            output.println(generator.name + ":" + generator.purchasedAmount + ":" + generator.generatedAmount.amount.bigNum);
        }

        output.println();
        output.close();
    }

}
