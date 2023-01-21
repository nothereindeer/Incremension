package com.sfanshen.ui;

public class TabSwitchButton extends GameButton {
    String switchedTab;

    public TabSwitchButton(int x, int y, int w, int h, String switchedTab) {
        super(x, y, w, h);
        this.switchedTab = switchedTab;

    }
}
