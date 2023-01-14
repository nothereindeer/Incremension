package com.sfanshen.upgrade;

import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;

import java.awt.*;

public class UpgradeButton{
  
  int x, y, width, height;
  Picture icon;

  public UpgradeButton(Picture icon){
    this.x = 0;
    this.y = 0;
    this.width = Const.UPGRADE_WIDTH;
    this.height = Const.UPGRADE_HEIGHT;
    this.icon = icon;
  }

  public void draw(Graphics g, Picture upgradeTypeIcon, boolean canBePurchased){
    g.setColor(Const.GOOGLE_HIGHLIGHT);
    g.drawRect(this.x, this.y, Const.UPGRADE_WIDTH, Const.UPGRADE_HEIGHT);
    if (canBePurchased){
      g.setColor(Const.SUN_YELLOW);
    }
    else{
      g.setColor(Const.B2B_GRAY);
    }
    g.drawRect(this.x, this.y, (int)(Const.UPGRADE_WIDTH - Const.UPGRADE_BORDER_WIDTH), (int)(Const.UPGRADE_HEIGHT - Const.UPGRADE_BORDER_WIDTH));

    icon.move(this.x + this.width / 2, this.y + this.height / 2, true);
    icon.draw(g);

    upgradeTypeIcon.move(this.x + this.width / 2, this.y + this.height / 2 + icon.height / 2, true);
    upgradeTypeIcon.draw(g);
  }
}
