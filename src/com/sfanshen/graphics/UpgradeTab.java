package com.sfanshen.graphics;

import com.sfanshen.upgrade.UpgradesFrame;

import java.awt.*;

public class UpgradeTab extends GameTab{
    public UpgradesFrame[] upgradesFrames;
    public UpgradeTab(String name, UpgradesFrame[] upgradesFrames) {
        super(name);
        this.upgradesFrames = upgradesFrames;
    }

    void draw(Graphics g){
        for (UpgradesFrame upgradeFrame: this.upgradesFrames){
            upgradeFrame.draw(g);
        }
    }
}
