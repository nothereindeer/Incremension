package com.sfanshen.ui;

import com.sfanshen.graphics.GameFrame;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;

import java.awt.*;

public class TabSwitchButton extends GameButton {
    public String switchedTab;

    public TabSwitchButton(int x, int y, int w, int h, String switchedTab) {
        super(x, y, w, h);
        this.switchedTab = switchedTab;

    }

    public void click() {
        Global.currentTab = this.switchedTab;
    }

    public void draw(Graphics2D g) {
        if (this.isMouseHovering)
            g.setColor(Const.GOOGLE_HIGHLIGHT);
        else
            g.setColor(Const.DEPRESSED_GOOGLE_HIGHLIGHT);

        g.drawRect(this.x, this.y, this.width, this.height);

        int x = this.x + this.width / 2;
        int y = this.y + this.height / 2;
        GameFrame.drawCenteredString(g, Global.capitalizeFirstLetters(this.switchedTab), x, y, Const.CURRENCY_FONT);

    }
}
