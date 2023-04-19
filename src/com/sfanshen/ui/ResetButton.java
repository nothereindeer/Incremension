package com.sfanshen.ui;

import com.sfanshen.currency.Currency;
import com.sfanshen.currency.ResetCurrency;
import com.sfanshen.graphics.GameFrame;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.BigNum;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;
import com.sfanshen.upgrade.Upgrade;

import java.awt.*;
import java.util.ArrayList;

public class ResetButton extends GameButton{

    public ResetCurrency gainedCurrency;
    public Picture icon;

    public ResetButton(int x, int y, ResetCurrency gainedCurrency){
        super(x, y, Const.RESET_BUTTON_SIZE, Const.RESET_BUTTON_SIZE);

        this.gainedCurrency = gainedCurrency;
        this.icon = gainedCurrency.icon.createClone();

    }

    public ResetButton(ResetCurrency gainedCurrency){
        super(0, 0, Const.RESET_BUTTON_SIZE, Const.RESET_BUTTON_SIZE);

        this.gainedCurrency = gainedCurrency;
        this.icon = gainedCurrency.icon.createClone();
        this.icon.resize(Const.RESET_BUTTON_ICON_SIZE, Const.RESET_BUTTON_ICON_SIZE, false);
    }

    public void click() {
        this.gainedCurrency.increase();
    }

    public void draw(Graphics2D g){
        this.drawFill(g);

        if (this.isMouseHovering) {
            g.setColor(Const.GOOGLE_HIGHLIGHT);
            this.drawInfo(g);
        }
        else
            g.setColor(this.gainedCurrency.color);
        g.drawRect(this.x - Const.RESET_BUTTON_FRAME_SIZE / 2, this.y - Const.RESET_BUTTON_FRAME_SIZE / 2, Const.RESET_BUTTON_FRAME_SIZE + Const.RESET_BUTTON_SIZE, Const.RESET_BUTTON_FRAME_SIZE + Const.RESET_BUTTON_SIZE);

        this.icon.move(this.x + this.width / 2, this.y + this.height / 2, true);
        this.icon.draw(g);
    }

    public void drawFill(Graphics2D g){
        if (this.isMouseHovering)
            g.setColor(Const.DEPRESSED_GOOGLE_HIGHLIGHT);
        else
            g.setColor(Const.DARKER_SUN_YELLOW);
        Rectangle rect;
        if (this.gainedCurrency.dependentCurrency.amount.isGreaterEqualTo(this.gainedCurrency.minResetAmt)){
            rect = new Rectangle(this.x, this.y, this.width, this.height);
            g.fill(rect);
            g.draw(rect);
        }
        else{
            int height = (int) (BigNum.multiply(BigNum.divide(BigNum.log10(this.gainedCurrency.dependentCurrency.amount), BigNum.log10(this.gainedCurrency.minResetAmt)), this.height).toNum());

            if (height > 0){
                rect = new Rectangle(this.x, this.y + this.height - height, this.width, height);
                g.fill(rect);
                g.draw(rect);
            }
        }
    }

    public void drawInfo(Graphics2D g){
        int x = Const.RESET_BUTTON_X;
        int y = this.y + this.height + Const.RESET_BUTTON_FRAME_SIZE;
        int width = Const.RESET_INFO_WIDTH;
        int height = Const.RESET_INFO_HEIGHT;

        g.drawRect(x, y, width, height);
        //Draw Gains
        this.icon.move(x + Const.RESET_INFO_ICON_LEFT_OFFSET, y + Const.RESET_INFO_LINE_SPACING, false);
        this.icon.draw(g);

        g.setFont(Const.DESC_FONT);
        String str;
        int stringX = x + Const.RESET_INFO_ICON_LEFT_OFFSET + Const.RESET_BUTTON_ICON_SIZE;
        str = "+ " + this.gainedCurrency.calculateGain().toSuffixVersion();
        GameFrame.drawYCenteredString(g, str, stringX, y + Const.RESET_INFO_LINE_SPACING + this.icon.height / 2);
        //Draw Requirement
        str = this.gainedCurrency.dependentCurrency.amount.toSuffixVersion() + "/" + this.gainedCurrency.minResetAmt.toSuffixVersion();
        g.drawString(str, stringX, y + 2 * Const.RESET_INFO_LINE_SPACING + this.icon.height);
        //Draw Description
    }
}
