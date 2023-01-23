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
    GeneratorFrame(int x, int y, String iconDirectory, Generator parentGenerator){
        this.x = x;
        this.y = y;
        this.width = Const.GENERATOR_FRAME_WIDTH;
        this.height = Const.GENERATOR_FRAME_HEIGHT;

        this.parentGenerator = parentGenerator;
        this.button = new GeneratorButton(this.x + Const.GENERATOR_BUTTON_X, this.y + Const.GENERATOR_FRAME_HEIGHT / 2 - Const.GENERATOR_BUTTON_HEIGHT / 2, Const.GENERATOR_BUTTON_WIDTH, Const.GENERATOR_BUTTON_HEIGHT, parentGenerator);
        this.icon = new Picture(0, 0, Const.GENERATOR_ICON_WIDTH, Const.GENERATOR_ICON_HEIGHT, iconDirectory);
    }

    GeneratorFrame(String iconDirectory, Generator parentGenerator){
        this.width = Const.GENERATOR_FRAME_WIDTH;
        this.height = Const.GENERATOR_FRAME_HEIGHT;

        this.parentGenerator = parentGenerator;
        this.button = new GeneratorButton(0, 0, 0, 0, parentGenerator);
        this.icon = new Picture(0, 0, Const.GENERATOR_ICON_WIDTH, Const.GENERATOR_ICON_HEIGHT, iconDirectory);
    }


    public void updateButtonDims(){
        this.button.x = this.x + Const.GENERATOR_BUTTON_X;
        this.button.y = (int) (this.y + Const.GENERATOR_FRAME_HEIGHT / 2 - Const.GENERATOR_BUTTON_HEIGHT / 2);
        this.button.width = Const.GENERATOR_BUTTON_WIDTH;
        this.button.height = Const.GENERATOR_BUTTON_HEIGHT;
    }
    public void draw(Graphics2D g){
        g.setColor(Const.GOOGLE_HIGHLIGHT);
        g.drawRoundRect(this.x, this.y, this.width, this.height, Const.GENERATOR_FRAME_ROUND_CORNER_OFFSET, Const.GENERATOR_FRAME_ROUND_CORNER_OFFSET);

        //Draw progress to next bonus
        g.setColor(Const.GOOGLE_HIGHLIGHT);
        //Draw Frame and Image
        this.drawIcon(g);
        //Draw Description
        this.drawDesc(g);
        //Draw Button
        this.button.draw(g);

    }

    public void drawDesc(Graphics2D g){
        int x = this.icon.x + this.icon.width + Const.GENERATOR_DESC_LEFT_OFFSET;
        int x2 = this.button.x + this.button.width + (this.x + this.width - this.button.x - this.button.width) / 2 ;
        int y = (this.y + this.height / 2);
        String string;

        string = Global.capitalizeFirstLetters(this.parentGenerator.name) + ": " + this.parentGenerator.purchasedAmount.toSuffixVersion() + " [+" + this.parentGenerator.generatedAmount.toSuffixVersion() + "]";
        GameFrame.drawYCenteredString(g, string, x, y - Const.GENERATOR_FRAME_LINE_SPACING / 2 - g.getFontMetrics(Const.DESC_FONT).getHeight() / 2, Const.DESC_FONT);

        string = "(+" + BigNum.multiply(BigNum.multiply(this.parentGenerator.production, this.parentGenerator.amount.amount), Global.ticksPerSec).toSuffixVersion() + " " + this.parentGenerator.produce.name + " per sec)";
        GameFrame.drawYCenteredString(g, string, x, y + Const.GENERATOR_FRAME_LINE_SPACING / 2 + g.getFontMetrics(Const.DESC_FONT).getHeight() / 2, Const.DESC_FONT);

        string = "(×" + this.parentGenerator.productionMultiplier.toSuffixVersion() + ")";
        GameFrame.drawCenteredString(g, string, x2, y , Const.DESC_FONT);
    }
    public void drawIcon(Graphics2D g){
        int y = this.y + this.height / 2 - this.icon.height / 2;
        int x = this.x + this.height / 2 - this.icon.height / 2;
        g.drawRoundRect(x - (Const.GENERATOR_ICON_FRAME_SIZE - Const.GENERATOR_ICON_WIDTH) / 2, y - (Const.GENERATOR_ICON_FRAME_SIZE - Const.GENERATOR_ICON_HEIGHT) / 2, Const.GENERATOR_ICON_FRAME_SIZE, Const.GENERATOR_ICON_FRAME_SIZE, Const.GENERATOR_ICON_ROUND_CORNER_OFFSET, Const.GENERATOR_ICON_ROUND_CORNER_OFFSET);

        this.icon.move(x, y, false);
        this.icon.draw(g);
    }
}
