package com.sfanshen.upgrade;

import com.sfanshen.main.Const;
import com.sfanshen.main.Global;

import java.util.ArrayList;

import java.awt.*;
import java.util.Arrays;

public class UpgradesFrame{

  int x, y, width, height, numPerRow;
  public ArrayList<Upgrade> upgrades;

  public UpgradesFrame(int x, int y, int width, int height, ArrayList<Upgrade> upgrades){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.upgrades = upgrades;
    this.numPerRow = (int)(width / (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));
  }
  public UpgradesFrame(String name, int x, int y, int width, int height, Upgrade[] upgrades){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;    this.upgrades = new ArrayList<>(Arrays.asList(upgrades));
    this.numPerRow = (int)(width / (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));
  }
  

public void draw(Graphics g){
    for (int i = 0; i < this.upgrades.size(); i ++){
      Upgrade upgrade = this.upgrades.get(i);
      int x = calculateCoords(i)[0];
      int y = calculateCoords(i)[1];

      //If upgrade is unlocked
      if (upgrade.isUnlocked){
        upgrade.upgradeButton.x = x;
        upgrade.upgradeButton.y = y;

        //Draws upgrade
        if (upgrade instanceof BoostUpgrade){
          upgrade.upgradeButton.draw(g, Global.boostUpgradeIcon, upgrade.isPurchasable());
        }
        else if (upgrade instanceof FeatureUpgrade){
          upgrade.upgradeButton.draw(g, Global.featureUpgradeIcon, upgrade.isPurchasable());
        }
      }

      //If the upgrade is not unlocked, draw a blank upgrade instead, indicating there is an upgrade to be unlocked by the player
      else{
        drawBlank(g, x, y);
      }
    }
  }
  
  
  
  public static void drawBlank(Graphics g, int x, int y){
    g.setColor(Const.BLACK);
    g.drawRect(x, y, Const.UPGRADE_WIDTH, Const.UPGRADE_HEIGHT);
  }

  public int[] calculateCoords(int i){
    int x, y, row, column, sideOffset;
    
    row = i / this.numPerRow;
    column = i % this.numPerRow;
    
    if (this.upgrades.size() - this.numPerRow <= 5){
      int amtOnRow = this.upgrades.size() - i - 1;
      sideOffset = (int)(this.width - amtOnRow * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET) - Const.UPGRADE_OFFSET) / 2; 
    }
    else{
      sideOffset = (int)(this.width - this.numPerRow * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET) - Const.UPGRADE_OFFSET) / 2;
    }
    
    x = (int)(this.x + sideOffset + column * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET)); 
    y = (int)(this.y + row * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));

    return new int[]{x, y};
  }
}
