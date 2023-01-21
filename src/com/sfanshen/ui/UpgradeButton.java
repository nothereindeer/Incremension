package com.sfanshen.ui;

import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;

import java.awt.*;

public class UpgradeButton extends GameButton {
    Picture icon;

    public UpgradeButton(Picture icon) {
        super(0, 0, Const.UPGRADE_WIDTH, Const.UPGRADE_HEIGHT);
        this.icon = icon;
    }

    public void draw(Graphics2D g, Picture upgradeTypeIcon, boolean canBePurchased, boolean isMaxed, double boughtPercentage) {
        drawBorder(g, isMaxed, canBePurchased);
        drawFill(g, boughtPercentage);

        icon.move(this.x + this.width / 2, this.y + this.height / 2, true);
        icon.draw(g);

        upgradeTypeIcon.move(this.x + this.width / 2, this.y + this.height / 2 + icon.height / 2, true);
        upgradeTypeIcon.draw(g);
    }

    public void drawBorder(Graphics2D g, boolean isMaxed, boolean canBePurchased) {
        if (isMaxed) {
            g.setColor(Const.MARIO_GREEN);
        } else if (canBePurchased) {
            g.setColor(Const.SUN_YELLOW);
        } else {
            g.setColor(Const.B2B_GRAY);
        }

        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke((float) Const.UPGRADE_BORDER_WIDTH));
        Rectangle border = new Rectangle(this.x, this.y, Const.UPGRADE_WIDTH, Const.UPGRADE_HEIGHT);
        g.draw(border);
        g.setStroke(oldStroke);
    }

    public void drawFill(Graphics2D g, double boughtPercentage) {
        g.setColor(Const.MORE_SUN_YELLOW);

        int width = (int) (Const.UPGRADE_WIDTH - 2 * Const.UPGRADE_BORDER_WIDTH);
        int height = (int) ((Const.UPGRADE_HEIGHT - 2 * Const.UPGRADE_BORDER_WIDTH) * boughtPercentage);
        int x = (int) (this.x + Const.UPGRADE_BORDER_WIDTH);
        int y = (int) (this.y + Const.UPGRADE_HEIGHT - Const.UPGRADE_BORDER_WIDTH - height);
        if (height > 0) {
            Rectangle fill = new Rectangle(x, y, width, height);
            g.fill(fill);
            g.draw(fill);
        }
    }
}
