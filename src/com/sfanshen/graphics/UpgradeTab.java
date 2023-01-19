package com.sfanshen.graphics;

import com.sfanshen.main.Const;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;

import java.awt.*;

public class UpgradeTab extends GameTab{
    public UpgradesFrame[] upgradesFrames;
    public UpgradeTab(String name, Upgrade[][] upgrades) {
        super(name);

        upgradesFrames = new UpgradesFrame[upgrades.length];

        for (int i = 0; i < upgrades.length; i ++){
            int totalFrames = upgrades.length;
            System.out.println(this.width);
            int height = this.height - 2 * Const.FRAME_INFO_OFFSET_Y;
            int width = this.width / totalFrames - Const.FRAME_INFO_OFFSET_X;
            int x = this.x + ((this.width / (totalFrames + 1)) * (i + 1)) + Const.FRAME_INFO_OFFSET_X / 2 - width / 2;
            System.out.println(x);
            int y = this.y + Const.FRAME_INFO_OFFSET_Y;

            UpgradesFrame upgradesFrame = new UpgradesFrame(x, y, width, height, upgrades[i]);
            this.upgradesFrames[i] = upgradesFrame;
        }
    }

    void draw(Graphics2D g){
        for (UpgradesFrame upgradeFrame: this.upgradesFrames){
            upgradeFrame.draw(g);
        }
    }
}
