package com.sfanshen.generator;

import com.sfanshen.graphics.GameFrame;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.BigNum;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;
import com.sfanshen.ui.GeneratorButton;

import java.awt.*;

public class GeneratorFrame {

    public int x, y, width, height;
    public GeneratorButton button;
    Generator parentGenerator;
    Picture icon;

    //-------------------------------------------------------Constructor-----------------------------------------------------------------\\
    GeneratorFrame(Generator parentGenerator) {
        this.width = Const.GENERATOR_FRAME_WIDTH;
        this.height = Const.GENERATOR_FRAME_HEIGHT;

        this.parentGenerator = parentGenerator;
        this.button = new GeneratorButton(0, 0, 0, 0, parentGenerator);
        this.icon = parentGenerator.generatedAmount.icon;
    }


    //-------------------------------------------------------Methods-----------------------------------------------------------------\\

    //----------Draw Methods----------\\
    public void draw(Graphics2D g) {

        //Draws Frame
        g.setColor(Const.GOOGLE_HIGHLIGHT);
        g.drawRoundRect(this.x, this.y, this.width, this.height, Const.GENERATOR_FRAME_ROUND_CORNER_OFFSET, Const.GENERATOR_FRAME_ROUND_CORNER_OFFSET);

        g.setColor(Const.GOOGLE_HIGHLIGHT);
        this.drawIcon(g);
        this.drawDesc(g);
        this.button.draw(g);
    }

    public void drawDesc(Graphics2D g) {
        int x = this.icon.x + this.icon.width + Const.GENERATOR_DESC_LEFT_OFFSET;
        int y = (this.y + this.height / 2);
        String string;

        string = Global.capitalizeFirstLetters(this.parentGenerator.name) + ": " + this.parentGenerator.purchasedAmount + " [+" + this.parentGenerator.generatedAmount.amount.toSuffixVersion() + "]";
        GameFrame.drawYCenteredString(g, string, x, y - Const.GENERATOR_FRAME_LINE_SPACING / 2 - g.getFontMetrics(Const.DESC_FONT).getHeight() / 2, Const.DESC_FONT);

        string = "(+" + BigNum.multiply(BigNum.multiply(BigNum.multiply(this.parentGenerator.production, this.parentGenerator.produce.finalBoost), this.parentGenerator.amount), Global.ticksPerSec).toSuffixVersion() + " " + this.parentGenerator.produce.name + "/s)";
        GameFrame.drawYCenteredString(g, string, x, y + Const.GENERATOR_FRAME_LINE_SPACING / 2 + g.getFontMetrics(Const.DESC_FONT).getHeight() / 2, Const.DESC_FONT);

        x = this.button.x + this.button.width + (this.x + this.width - this.button.x - this.button.width) / 2;
        string = "(×" + this.parentGenerator.productionMultiplier.toSuffixVersion() + ")";
        GameFrame.drawCenteredString(g, string, x, y, Const.DESC_FONT);
    }

    public void drawIcon(Graphics2D g) {
        int y = this.y + this.height / 2 - this.icon.height / 2;
        int x = this.x + this.height / 2 - this.icon.height / 2;
        g.drawRoundRect(x - (Const.GENERATOR_ICON_FRAME_SIZE - Const.GENERATOR_ICON_WIDTH) / 2, y - (Const.GENERATOR_ICON_FRAME_SIZE - Const.GENERATOR_ICON_HEIGHT) / 2, Const.GENERATOR_ICON_FRAME_SIZE, Const.GENERATOR_ICON_FRAME_SIZE, Const.GENERATOR_ICON_ROUND_CORNER_OFFSET, Const.GENERATOR_ICON_ROUND_CORNER_OFFSET);

        this.icon.move(x, y, false);
        this.icon.draw(g);
    }
}
