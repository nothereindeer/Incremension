package com.sfanshen.ui;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;
import com.sfanshen.upgrade.Upgrade;

import java.awt.*;
import java.util.ArrayList;

public class ResetButton extends GameButton{

    public Currency gainedCurrency;

    public ResetButton(int x, int y, Currency gainedCurrency){
        super(x, y, Const.RESET_BUTTON_SIZE, Const.RESET_BUTTON_SIZE);

        this.gainedCurrency = gainedCurrency;

    }

    public ResetButton(Currency gainedCurrency){
        super(0, 0, Const.RESET_BUTTON_SIZE, Const.RESET_BUTTON_SIZE);

        this.gainedCurrency = gainedCurrency;

    }

    public void click() {
        for (Currency currency : Global.currencies.values()){
            if (currency == this.gainedCurrency){
                break;
            }
            currency.set(0);
            for (Upgrade upgrade: Global.upgrades.values()){
                if (upgrade.resetTier.equals(currency.name)){
                    upgrade.reset(true);
                }
            }
        }
        this.gainedCurrency.increase();
    }

    public void draw(Graphics2D g){
        g.setColor(Const.GOOGLE_HIGHLIGHT);
        g.drawRect(this.x, this.y, this.width, this.height);
    }
}
