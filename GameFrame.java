import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GameFrame{
  JFrame frame;  
  GraphicsPanel currentGraphicsPanel;
  

  
  
  Font currencyFont = new Font("Baskerville", Font.BOLD, Const.CURRENCY_TEXT_SIZE);
  
  GameFrame(){
    frame = new JFrame("test");
    frame.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    currentGraphicsPanel = new GraphicsPanel();
    
    frame.add(currentGraphicsPanel);
    frame.setVisible(true);    
  }
  
  void updateFrame(){
    frame.repaint();
    try {Thread.sleep(5);} catch(Exception e){}
  }
  
  void drawCurrencies(Graphics g){
    int i = 0;
    for (Currency currency : Global.currencies.values()){
      int fontXPosition = (i + 1) * Const.SCREEN_WIDTH / (Global.currencies.size() + 1);
      g.setFont(currencyFont);
      g.drawString(currency.amount.bigNum, fontXPosition, 10);
      i = i + 1;
    }
  }
  
  void drawUpgrades(Graphics g){
    
  }
  
  public class GraphicsPanel extends JPanel{
    
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      if (FinalGame.currentScreen == "main menu"){
        
      }
      else if (FinalGame.currentScreen == "main"){
        drawCurrencies(g);
        
      }
      else if (FinalGame.currentScreen == "upgrades"){
        drawUpgrades(g);
      }
      
      
    }
  }  
}