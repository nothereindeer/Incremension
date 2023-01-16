package com.sfanshen.graphics;

import com.sfanshen.main.Const;
import com.sfanshen.currency.Currency;
import com.sfanshen.main.FinalGame;
import com.sfanshen.main.Global;
import com.sfanshen.upgrade.UpgradesFrame;

import javax.swing.*;
import java.awt.*;


public class GameFrame{
  JFrame frame;  
  GraphicsPanel currentGraphicsPanel;

  //-------------------------------------------------------Constructor-----------------------------------------------------------------\\
  public GameFrame(){
    frame = new JFrame("test");
    frame.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    currentGraphicsPanel = new GraphicsPanel();
    
    frame.add(currentGraphicsPanel);
    frame.setVisible(true);
    currentGraphicsPanel.setBackground(Const.NOT_WHITE);
  }

  //-------------------------------------------------------Graphics Rendering-----------------------------------------------------------------\\

  public class GraphicsPanel extends JPanel{
    public void paint(Graphics g) {
      super.paint(g);
      drawEverything(g);
    }
  }



  //-------------------------------------------------------Graphical Methods-----------------------------------------------------------------\\

  //Updates frame
  public void updateFrame(){
    frame.repaint();
    try {Thread.sleep(5);} catch(Exception e){System.out.println("Something went wrong!!!!");}
  }


  public void drawEverything(Graphics g){
    Graphics2D g2 = (Graphics2D) g;
    switch (Global.currentScreen) {
      case ("main menu"):
        drawMenu(g2);

      case("main"):
        drawCurrencies(g2);
        drawUpgrades(g2);
        drawFrame(g2);

      case("settings"):
    }
  }
  public void drawFrame(Graphics2D g){
    g.setColor(Const.DARKISH_BROWN);
    Stroke oldStroke = g.getStroke();
    g.setStroke(new BasicStroke(Const.FRAME_BORDER_THICKNESS));
    g.drawRoundRect(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_WIDTH, Const.FRAME_HEIGHT, 50, 50);
    g.setStroke(oldStroke);
  }


  //Renders everything currency related - icons, amounts
  public void drawCurrencies(Graphics2D g){
    int i = 0;
    for (Currency currency : Global.currencies.values()){
      int xPosition = (i + 1) * Const.SCREEN_WIDTH / (Global.currencies.size() + 1);

      currency.icon.move(xPosition, Const.CURRENCY_OFFSET_FROM_TOP, false);
      currency.icon.draw(g);
      g.setFont(Const.currencyFont);
      g.setColor(Const.SUN_YELLOW);
      g.drawString(currency.amount.bigNum, xPosition + currency.icon.width + Const.TEXT_ICON_OFFSET, Const.CURRENCY_OFFSET_FROM_TOP + Const.CURRENCY_TEXT_SIZE + currency.icon.height / 2 - Const.CURRENCY_TEXT_SIZE / 2);
      i = i + 1;
    }
  }


  //Renders everything upgrade related - currently visible upgrade buttons
  public void drawUpgrades(Graphics2D g){
    for (GameTab tab: Global.gameTabs.values()){
      if (tab.name.equals(Global.currentUpgradeFrame)){
        tab.draw(g);
      }
    }
  }

  public void drawMenu(Graphics2D g){



    Global.playButton.draw(g);
    Global.titleImage.draw(g);
    Global.loadingBar.draw(g);
  }
}
