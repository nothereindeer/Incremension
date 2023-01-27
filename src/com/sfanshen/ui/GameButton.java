package com.sfanshen.ui;

import com.sfanshen.main.Global;

import java.awt.*;

abstract public class GameButton {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean isMouseHovering;

    public GameButton(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.isMouseHovering = false;
    }

    abstract public void click();
    abstract public void draw(Graphics2D g);

    boolean isMouseInFrame() {
        return (Global.mouseX > this.x && Global.mouseX < this.x + this.width && Global.mouseY > this.y && Global.mouseY < this.y + this.height);

    }


}