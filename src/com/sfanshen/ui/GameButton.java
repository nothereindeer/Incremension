package com.sfanshen.ui;

import com.sfanshen.main.Global;

public class GameButton {

    public int x;
    public int y;
    public int width;
    public int height;

    String action;

    public boolean isMouseHovering;

    public GameButton(int x, int y, int w, int h, String displayTab) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.isMouseHovering = false;
        Global.gameTabs.get(displayTab).buttons.add(this);
    }

    public GameButton(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.isMouseHovering = false;
    }

    boolean isMouseInFrame(int mouseX, int mouseY) {
        return (mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height);

    }


}