package com.sfanshen.upgrade;

import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;

import java.awt.*;

public class UpgradeButton{
  
  int x, y;
  Image icon;

  public UpgradeButton(){
    this.x = 0;
    this.y = 0;
  }

  public void draw(Graphics g, int x, int y, Picture icon, Picture currencyIcon){
    g.drawRect(x, y, Const.UPGRADE_WIDTH, Const.UPGRADE_HEIGHT);
    
  }
}
