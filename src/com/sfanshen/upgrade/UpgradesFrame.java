package com.sfanshen.upgrade;

import com.sfanshen.graphics.UpgradeTab;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;

import java.util.ArrayList;

import java.awt.*;
import java.util.Arrays;

public class UpgradesFrame {

    int x, y, width, height, numPerRow;
    public ArrayList<Upgrade> upgrades;

    public UpgradesFrame(int x, int y, int width, int height, ArrayList<Upgrade> upgrades) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.upgrades = upgrades;
        this.numPerRow = (int) (width / (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));
    }

    public UpgradesFrame(int x, int y, int width, int height, Upgrade[] upgrades) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.upgrades = new ArrayList<>(Arrays.asList(upgrades));
        this.numPerRow = (int) (width / (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));
    }


    public void draw(Graphics2D g) {
        for (Upgrade upgrade : this.upgrades) {
            if (upgrade.upgradeButton.isMouseHovering){
                UpgradeTab.drawUpgDesc(g, upgrade);
            }
            //If upgrade is unlocked
            if (upgrade.isUnlocked) {
                //Draws upgrade
                if (upgrade instanceof BoostUpgrade)
                   drawBoostUpgrade(g, upgrade);
                else if (upgrade instanceof FeatureUpgrade)
                   drawFeatureUpgrade(g, upgrade);

            }

            //If the upgrade is not unlocked, draw a blank upgrade instead, indicating there is an upgrade to be unlocked by the player
            else {
                drawBlank(g, x, y);
            }
        }
    }

    public void drawBoostUpgrade(Graphics2D g, Upgrade upgrade){
        BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
        double boughtPercentage = Math.round(boostUpgrade.level / (double) (boostUpgrade).maxLevel * 100.0) / 100.0;
        upgrade.upgradeButton.draw(g, Global.boostUpgradeIcon, upgrade.isPurchasable(), boostUpgrade.level >= boostUpgrade.maxLevel, boughtPercentage);
    }

    public void drawFeatureUpgrade(Graphics2D g, Upgrade upgrade){
        FeatureUpgrade featureUpgrade = (FeatureUpgrade) upgrade;
        double boughtPercentage = featureUpgrade.isBought ? 1 : 0;
        upgrade.upgradeButton.draw(g, Global.featureUpgradeIcon, upgrade.isPurchasable(), ((FeatureUpgrade) upgrade).isBought, boughtPercentage);
    }


    public static void drawBlank(Graphics2D g, int x, int y) {
        g.setColor(Const.DARKISH_BROWN);
        g.drawRect(x, y, Const.UPGRADE_WIDTH, Const.UPGRADE_HEIGHT);
    }

    public int[] calculateCoords(int i) {
        int x, y, row, column, sideOffset;

        row = i / this.numPerRow;
        column = i % this.numPerRow;

        int lastRowStartingIndex = (this.upgrades.size() / this.numPerRow) * this.upgrades.size();

        if (i >= lastRowStartingIndex) {
            int amtOnRow = this.upgrades.size() - lastRowStartingIndex;
            sideOffset = (int) (this.width - amtOnRow * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET) - Const.UPGRADE_OFFSET) / 2;
        } else {
            sideOffset = (int) (this.width - this.numPerRow * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET) - Const.UPGRADE_OFFSET) / 2;
        }

        x = (int) (this.x + sideOffset + column * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));
        y = (int) (this.y + row * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));

        return new int[]{x, y};
    }
}
