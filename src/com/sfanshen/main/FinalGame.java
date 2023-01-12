package com.sfanshen.main;

import com.sfanshen.currency.Currency;
import com.sfanshen.graphics.GameFrame;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.FeatureUpgrade;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;



public class FinalGame{
  public static String currentScreen;
  public static String currentUpgradeFrame;
  public static void main(String[] args){
    
    boolean isRunning = true;
    currentScreen = "main";
    currentUpgradeFrame = "coinUpgrades";
    
    Global.initialize();
    Currency coins = Global.currencies.get("coins");
    Currency prestigePoints = Global.currencies.get("prestige points");
        
    GameFrame gameFrame = new GameFrame();

    while(isRunning){
      switch(currentScreen) {
        case "main menu":

        case "main":

        case "settings":

      }
      gameFrame.updateFrame();
      
      coins.amount.add(1);
    }

  }
  
  
  
  public static void updateSaveVersion() throws Exception{
    File saveVersionFile = new File("Save Version.txt");
    Scanner sc = new Scanner(saveVersionFile);
    
    Global.saveVersion = sc.nextInt(); 
  }
  
  public static void loadProgress() throws Exception{
    updateSaveVersion();
    File saveFile = new File("../Saves/test" + Global.saveVersion + ".txt");
    Scanner sc = new Scanner(saveFile);
    
    String line = "";
    
    while (!line.equals("Currencies")){
      line = sc.nextLine();
    }
    
    line = sc.nextLine();
    
    //Reached start of currency saves
    while (!line.equals("")){
      Currency currency = Global.currencies.get(line.split(":")[0]);
      currency.amount.set(Integer.parseInt(line.split(":")[1]));
      line = sc.nextLine();
    }
    
    sc.nextLine();
    line = sc.nextLine();
    //Reached start of upgrade saves
    while (!line.equals("")){
      Upgrade upgrade = Global.upgrades.get(line.split(":")[0]);
      if (upgrade instanceof BoostUpgrade boostUpgrade){
        boostUpgrade.level = Integer.parseInt(line.split(":")[1]);
      }
      
      else if (upgrade instanceof FeatureUpgrade featureUpgrade){
        featureUpgrade.isBought = (Integer.parseInt(line.split(":")[1]) > 0);
      }

      line = sc.nextLine();
    }
  }
  
  public static void saveProgress() throws Exception{
    updateSaveVersion();
    File saveFile = new File("../Saves/test" + (Global.saveVersion + 1) + ".txt");
    if (saveFile.createNewFile())
      System.out.println("New save file created");
    else
      System.out.println("File already exists");
    PrintWriter output = new PrintWriter(saveFile);
    
    
    
    output.println("Currencies:");
    
    for (Currency currency: Global.currencies.values()){
      output.println(currency.name + ":" + currency.amount.bigNum);
    }
    
    output.println("\nUpgrades:");
    
    for (UpgradesFrame upgradesFrame: Global.upgradeFrames){
      for (Upgrade upgrade: upgradesFrame.upgrades){
        output.print(upgrade.name + ":");
        if (upgrade instanceof BoostUpgrade boostUpgrade){
          output.println(boostUpgrade.level);
        }
        else if (upgrade instanceof FeatureUpgrade featureUpgrade){
          if (featureUpgrade.isBought){
            output.println("1");
          }
          else{
            output.println("0");
          }
        }
        
        else{
         System.out.println("Error while saving files: Attempted looping through upgrades, found illegal upgrade type"); 
        }
      }
    }
  }
  

  
  
}
