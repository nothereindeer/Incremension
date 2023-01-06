
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;



public class FinalGame{
  public static String currentScreen;
  
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
  

  
  
}