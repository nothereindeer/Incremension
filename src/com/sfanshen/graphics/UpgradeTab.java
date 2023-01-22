package com.sfanshen.graphics;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.Const;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.FeatureUpgrade;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;

import java.awt.*;

public class UpgradeTab extends GameTab {
    public UpgradesFrame[] upgradesFrames;

    public UpgradeTab(String name, Upgrade[][] upgrades) {
        super(name);

        upgradesFrames = new UpgradesFrame[upgrades.length];

        for (int i = 0; i < upgrades.length; i++) {
            int totalFrames = upgrades.length;
            int height = this.height - 2 * Const.FRAME_INFO_OFFSET_Y;
            int width = this.width / totalFrames - Const.FRAME_INFO_OFFSET_X;
            int x = this.x + ((this.width / (totalFrames + 1)) * (i + 1)) + Const.FRAME_INFO_OFFSET_X / 2 - width / 2;
            int y = this.y + Const.FRAME_INFO_OFFSET_Y;

            UpgradesFrame upgradesFrame = new UpgradesFrame(x, y, width, height, upgrades[i]);
            this.upgradesFrames[i] = upgradesFrame;
        }
    }

    void draw(Graphics2D g) {
        for (UpgradesFrame upgradeFrame : this.upgradesFrames) {
            upgradeFrame.draw(g);
        }
    }



    public static void drawUpgDesc(Graphics2D g, Upgrade upgrade){
        g.setColor(Const.DEPRESSED_GOOGLE_HIGHLIGHT);
        g.drawRect(Const.FRAME_X, (int) Const.UPG_DESC_Y, Const.FRAME_WIDTH, (int) (Const.FRAME_HEIGHT - (Const.UPG_DESC_Y - Const.FRAME_Y)));

        g.setColor(Const.MORE_SUN_YELLOW);
        //Draw Upgrade Name
        drawUpgName(g, upgrade);
        //Draw Icon?

        //Draw Price
        drawPrice(g, upgrade);
        //Draw Level/MaxLevel
        drawLevel(g, upgrade);
        //Draw Description
        drawDesc(g, upgrade);
        drawBoost(g, upgrade);
        //Draw Description?
    }

    static void drawUpgName(Graphics2D g, Upgrade upgrade){
        String string = upgrade.name;
        int x = Const.FRAME_X + Const.FRAME_WIDTH / 2;
        int y = (int) Const.UPG_DESC_Y + 4 * Const.UPG_DESC_LINE_SPACING;

        GameFrame.drawXCenteredString(g, string, x, y, Const.DESC_TITLE_FONT);
    }

    static void drawPrice(Graphics2D g, Upgrade upgrade){
        String string = "Cost: " + upgrade.price.bigNum + " " + upgrade.purchaseCurrency.name;
        int x = Const.FRAME_X + Const.FRAME_WIDTH / 2;
        int y = (int) (Const.UPG_DESC_Y + Const.UPG_DESC_HEIGHT / 2 - g.getFontMetrics(Const.DESC_FONT).getHeight() - Const.UPG_DESC_LINE_SPACING);
        GameFrame.drawCenteredString(g, string, x, y, Const.DESC_FONT);
    }

    static void drawLevel(Graphics2D g, Upgrade upgrade){
        String string;
        if (upgrade instanceof BoostUpgrade){
            BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;
            if (boostUpgrade.level >= boostUpgrade.maxLevel)
                g.setColor(Const.MARIO_GREEN);
            string = "Level: " + ((BoostUpgrade) upgrade).level + "/" + ((BoostUpgrade) upgrade).maxLevel;
        }
        else if (upgrade instanceof FeatureUpgrade){
            if (((FeatureUpgrade) upgrade).isBought) {
                string = "Purchased";
                g.setColor(Const.MARIO_GREEN);
            }
            else
                string = "Not Purchased";
        }
        else{
            System.out.println("Attempted to draw level of neither a Boost or Feature Upgrade");
            string = "";
        }

        int x = Const.FRAME_X + Const.FRAME_WIDTH / 2;
        int y = (int) (Const.UPG_DESC_Y + Const.UPG_DESC_HEIGHT / 2);
        GameFrame.drawCenteredString(g, string, x, y, Const.DESC_FONT);
        g.setColor(Const.SUN_YELLOW);
    }

    static void drawDesc(Graphics2D g, Upgrade upgrade){
        String string = upgrade.description;
        int x = Const.FRAME_X + Const.FRAME_WIDTH / 2;
        int y = (int) (Const.UPG_DESC_Y + Const.UPG_DESC_HEIGHT / 2 + g.getFontMetrics(Const.DESC_FONT).getHeight() + Const.UPG_DESC_LINE_SPACING);
        GameFrame.drawXCenteredString(g, string, x, y, Const.DESC_FONT);
    }

    static void drawBoost(Graphics2D g, Upgrade upgrade){
        String string = "";
        if (upgrade instanceof BoostUpgrade){
            BoostUpgrade boostUpgrade = (BoostUpgrade) upgrade;

            for (Currency boostedCurrency: boostUpgrade.boostedCurrencies.keySet())
                string = "Effect: " + boostUpgrade.boostedCurrencies.get(boostedCurrency).operation + boostUpgrade.calculateBoost(boostedCurrency).bigNum + " " + boostedCurrency.name;

        }
        else if (upgrade instanceof FeatureUpgrade){
            string = "Effect: Unlocked";
        }

        int x = Const.FRAME_X + Const.FRAME_WIDTH / 2;
        int y = (int) (Const.UPG_DESC_Y + Const.UPG_DESC_HEIGHT / 2 + 2 * g.getFontMetrics(Const.DESC_FONT).getHeight() + 2 * Const.UPG_DESC_LINE_SPACING);
        GameFrame.drawXCenteredString(g, string, x, y, Const.DESC_FONT);
    }
}
