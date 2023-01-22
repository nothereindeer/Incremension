package com.sfanshen.ui;

import com.sfanshen.main.Global;

public class TabSwitchButton extends GameButton {
    public String switchedTab;

    public TabSwitchButton(int x, int y, int w, int h, String switchedTab) {
        super(x, y, w, h);
        this.switchedTab = switchedTab;

    }

    public void click(){
        Global.currentTab = this.switchedTab;
    }
}
