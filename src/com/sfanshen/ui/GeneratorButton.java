package com.sfanshen.ui;

import com.sfanshen.generator.Generator;
import com.sfanshen.graphics.GameFrame;
import com.sfanshen.main.BigNum;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;
import com.sun.prism.paint.Gradient;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class GeneratorButton extends GameButton {
    Generator purchasedGenerator;

    public GeneratorButton(int x, int y, int width, int height, Generator purchasedGenerator) {
        super(x, y, width, height);
        this.purchasedGenerator = purchasedGenerator;
    }

    public void click() {
        this.purchasedGenerator.buy();
    }

    public void draw(Graphics2D g) {
        this.drawFill(g);
        this.drawBorder(g);
        this.drawCost(g);
    }

    public void drawFill(Graphics2D g) {
        if (this.purchasedGenerator.isPurchasable() && this.isMouseHovering)
            g.setColor(Const.DEPRESSED_GOOGLE_HIGHLIGHT);
        else if (this.isMouseHovering)
            g.setColor(Const.DARK_GRAY);
        else if (this.purchasedGenerator.isPurchasable())
            g.setColor(Const.GRAY);
        else
            g.setColor(Const.LESS_DARK_GRAY);

        g.fillRoundRect(this.x, this.y, this.width, this.height, Const.GENERATOR_BUTTON_ROUND_CORNER_OFFSET, Const.GENERATOR_BUTTON_ROUND_CORNER_OFFSET);
    }

    public void drawBorder(Graphics2D g){
        double progress = this.purchasedGenerator.purchasedAmountInTier / (double)(Const.GENERATOR_MULTIPLIER_INTERVAL);
        GradientPaint paint = new GradientPaint((float) (this.x + this.width * progress - 1), this.y + this.height / 2, Const.SUN_YELLOW, (float) (this.x + this.width * progress), this.y + this.height / 2, Const.GOOGLE_HIGHLIGHT, false);
        g.setPaint(paint);
        g.drawRoundRect(this.x, this.y, this.width, this.height, Const.GENERATOR_BUTTON_ROUND_CORNER_OFFSET, Const.GENERATOR_BUTTON_ROUND_CORNER_OFFSET);
    }

    public void drawCost(Graphics2D g){
        int x = this.x + this.width / 2;
        int y = this.y + this.height / 2;
        g.setColor(Const.GOOGLE_HIGHLIGHT);
        GameFrame.drawCenteredString(g, "Buy For " + this.purchasedGenerator.cost.toSuffixVersion() + " " + Global.capitalizeFirstLetters(this.purchasedGenerator.purchaseCurrency.name), x, y, Const.BUY_BUTTON_FONT);
    }
}
