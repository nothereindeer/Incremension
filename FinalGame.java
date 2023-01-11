
import java.util.Scanner;
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
  
  
  
  public static void updateSaveVersion() throws Exception{
    File saveVersionFile = new File("Save Version.txt");
    Scanner sc = new Scanner(saveVersionFile);
    
    Global.saveVersion = sc.nextInt(); 
  }
  
  public static void loadProgress() throws Exception{
    updateSaveVersion();
    File saveFile = new File("../Saves/test" + Global.saveVersion + ".txt");
    Scanner sc = new Scanner(System.in);
    
    String line = "";
    
    while (line != "Currencies"){
      line = sc.nextLine();
    }
    
    line = sc.nextLine();
    
    //Reached start of currency saves
    while (line != ""){
      Currency currency = Global.currencies.get(line.split(":")[0]);
      currency.amount.set(Integer.parseInt(line.split(":")[1]));
      line = sc.nextLine();
    }
    
    sc.nextLine();
    line = sc.nextLine();
    //Reached start of upgrade saves
    while (line != ""){
      Upgrade upgrade = Global.upgrades.get(line.split(":")[0]);
      if (upgrade instanceof BoostUpgrade){
        BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
        boostUpgrade.level.set(Integer.parseInt(line.split(":")[1]));  
      }
      
      else if (upgrade instanceof FeatureUpgrade){
        FeatureUpgrade featureUpgrade = (FeatureUpgrade) upgrade;
        featureUpgrade.isBought = (Integer.parseInt(line.split(":")[1]) > 0);
      }
    }
  }
  
  public static void saveProgress() throws Exception{
    updateSaveVersion();
    File saveFile = new File("../Saves/test" + (Global.saveVersion + 1) + ".txt");
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