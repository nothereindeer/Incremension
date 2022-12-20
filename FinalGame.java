
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;



public class FinalGame{
  public static String currentScreen;
  
  public static void main(String[] args){
    
    boolean isRunning = true;
    currentScreen = "main menu";
    
    Currency coins = Global.CURRENCIES.get("coins");
    Currency prestigePoints = Global.CURRENCIES.get("prestige points");
        
    GameFrame gameFrame = new GameFrame();
    while(isRunning){
      if (currentScreen == "main menu"){
        
      }
      else if (currentScreen == "main"){
        
      }
      else if (currentScreen == "upgrades"){
        
      }
      gameFrame.updateFrame();
      
      coins.amount = coins.amount + 1;
    }
  }
  
  
}