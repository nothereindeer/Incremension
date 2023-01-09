
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;



public class FinalGame{
  public static String currentScreen;
  public static UpgradesFrame displayedUpgrades;
  
  public static void main(String[] args){
    
    boolean isRunning = true;
    currentScreen = "main";
    
    Global.initialize();
    Currency coins = Global.currencies.get("coins");
    Currency prestigePoints = Global.currencies.get("prestige points");
        
    GameFrame gameFrame = new GameFrame();

    while(isRunning){
      if (currentScreen == "main menu"){
        
      }
      else if (currentScreen == "main"){
        
      }
      else if (currentScreen == "upgrades"){
        
      }
      gameFrame.updateFrame();
      
      coins.amount.add(1);
    }

  }
  
  
  public static void loadProgress(File saveFile){
    
  }
  
  public static void saveProgress() throws Exception{
    File saveFile = new File("../Saves/test" + Global.saveVersion + ".txt");
    saveFile.createNewFile();
    PrintWriter output = new PrintWriter(saveFile);
    
    output.println("Currencies:");
    
    for (Currency currency: Global.currencies.values()){
      output.println(currency.name + ":" + currency.amount.bigNum);
    }
    
    output.println("\nUpgrades:");
    
    for (UpgradesFrame upgradesFrame: Global.upgradesFrames){
      for (Upgrade upgrade: upgradesFrame.upgrades){
        output.print(upgrade.name + ":");
        if (upgrade instanceof BoostUpgrade){
          BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
          output.println(boostUpgrade.level);
        }
        else if (upgrade instanceof FeatureUpgrade){
          FeatureUpgrade featureUpgrade = (FeatureUpgrade) upgrade;
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