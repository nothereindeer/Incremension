
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;



public class FinalGame{
  public static void main(String[] args){
    
    boolean isRunning = true;
    
    Currency coins = Const.CURRENCIES.get("coins");
    Currency prestigePoints = Const.CURRENCIES.get("prestige points");
        
    GameFrame gameFrame = new GameFrame();
    while(isRunning){
      gameFrame.updateFrame();
      
      coins.amount = coins.amount + 1;
    }
  }
  
  
}