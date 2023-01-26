package com.sfanshen.ui;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;

import java.util.ArrayList;

public class ResetButton extends GameButton{

    public Currency gainedCurrency;
    Currency[] resetCurrencies;


    ResetButton(int x, int y, String gainedCurrency){
        super(x, y, Const.RESET_BUTTON_SIZE, Const.RESET_BUTTON_SIZE);

        this.gainedCurrency = Global.findCurrency(gainedCurrency);

    }

    public void click() {
        for (Currency currency : Global.currencies.values()){
            if (currency == this.gainedCurrency){
                break;
            }

        }
            this.gainedCurrency.increase();
    }
}
