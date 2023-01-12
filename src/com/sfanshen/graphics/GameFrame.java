package com.sfanshen.graphics;

import com.sfanshen.main.Const;
import com.sfanshen.currency.Currency;
import com.sfanshen.main.FinalGame;
import com.sfanshen.main.Global;

import javax.swing.*;
import java.awt.*;


public class GameFrame{
  JFrame frame;  
  GraphicsPanel currentGraphicsPanel;
  

  
  
  Font currencyFont = new Font("Baskerville", Font.BOLD, Const.CURRENCY_TEXT_SIZE);
  
  public GameFrame(){
    frame = new JFrame("test");
    frame.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    currentGraphicsPanel = new GraphicsPanel();
    
    frame.add(currentGraphicsPanel);
    frame.setVisible(true);    
  }
  
  public void updateFrame(){
    frame.repaint();
    try {Thread.sleep(5);} catch(Exception e){System.out.println("Something went wrong!!!!");}
  }
  
  public void drawCurrencies(Graphics g){
    int i = 0;
    for (Currency currency : Global.currencies.values()){
      int fontXPosition = (i + 1) * Const.SCREEN_WIDTH / (Global.currencies.size() + 1);
      g.setFont(currencyFont);
      g.drawString(currency.amount.bigNum, fontXPosition, 10);
      i = i + 1;
    }
  }
  
  public void drawUpgrades(Graphics g){
    {
      
    }
  }
  
  public class GraphicsPanel extends JPanel{
    
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      switch (FinalGame.currentScreen) {
        case ("main menu"):

        case("main"):
          drawCurrencies(g);
          drawUpgrades(g);
        case("settings"):
     }
    }
      
      
  }
}
