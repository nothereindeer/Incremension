package com.sfanshen.currency;

import com.sfanshen.Formula.BaseFormula;
import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;
import com.sfanshen.main.Multiplier;
import com.sfanshen.ui.ResetButton;
import com.sfanshen.upgrade.BoostUpgrade;

import java.awt.*;
import java.util.ArrayList;

public class Currency {
    public String name;
    public BigNum amount;

    //Additive boosts are applied according to math rules (BEDMAS duh)
    public Multiplier multi;


    //Every currency has its own icon, stored here for convenience
    public Picture icon;
    public Color color;

    //-------------------------------------------------------Constructors-----------------------------------------------------------------\\
    public Currency(String name, Picture icon, Color color) {
        this.name = name;
        this.amount = new BigNum(0);
        this.multi = new Multiplier();

        this.icon = icon;
        this.color = color;
    }
    public Currency(String name, Picture icon) {
        this.name = name;
        this.amount = new BigNum(0);
        this.multi = new Multiplier();

        this.icon = icon;
        this.color = Const.BLACK;
    }

    //-------------------------------------------------------Methods-----------------------------------------------------------------\\

    //----------Main Methods----------\\

    public void set(String amount) {
        this.amount.set(new BigNum(amount));
    }
    public void set(int amount) {
        this.amount.set(amount);
    }

    public void increase(BigNum n) {
        n.multiply(this.multi.finalMultiplier);
        this.amount.add(n);
    }
    public void increase(int n) {
        this.amount.add(BigNum.multiply(this.multi.finalMultiplier, new BigNum(n)));
    }
    public void increase() {
        this.amount.add(this.multi.finalMultiplier);
    }

}
