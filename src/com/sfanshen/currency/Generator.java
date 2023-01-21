package com.sfanshen.currency;

import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;

public class Generator {

    String name;
    BigNum production;
    Currency amount;
    Currency produce;

    public Generator(String name, Currency currencyProduced, BigNum baseProduction, String iconPictureDirectory) {
        this.name = name;
        this.production = baseProduction;
        this.amount = new Currency(name, new Picture(0, 0, iconPictureDirectory));
        this.produce = currencyProduced;
    }

    public void generate() {
        this.produce.amount.add(BigNum.multiply(this.amount.amount, this.production));
    }
}
