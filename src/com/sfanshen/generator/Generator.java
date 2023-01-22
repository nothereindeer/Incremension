package com.sfanshen.generator;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;
import com.sfanshen.main.Formula;
import com.sfanshen.main.Global;

public class Generator {

    String name;
    String description;

    BigNum baseProduction;
    int productionMultiplierTier;
    public BigNum purchasedAmountInTier;
    BigNum productionMultiplier;
    BigNum production;

    public BigNum price;
    Formula priceFormula;
    public Currency purchaseCurrency;

    BigNum purchasedAmount;
    BigNum generatedAmount;
    Currency amount;

    Currency produce;

    public GeneratorFrame generatorFrame;
    public boolean isUnlocked;

    public Generator(String name, Currency currencyProduced, BigNum baseProduction, Formula priceFormula, Currency purchaseCurrency, String iconPictureDirectory, String description) {
        this.name = name;
        this.description = description;
        this.priceFormula = priceFormula;
        this.purchaseCurrency = purchaseCurrency;

        this.amount = new Currency(name, new Picture(0, 0, Const.GENERATOR_ICON_WIDTH, Const.GENERATOR_ICON_HEIGHT, iconPictureDirectory));
        this.produce = currencyProduced;
        this.isUnlocked = true;

        this.purchasedAmount = new BigNum(0);
        this.purchasedAmountInTier = new BigNum(0);


        this.baseProduction = baseProduction;
        this.productionMultiplier = new BigNum(1);
        this.productionMultiplierTier = 1;

        this.generatorFrame = new GeneratorFrame(iconPictureDirectory, this);

        this.calculateProduction();
        this.calculatePrice();
    }

    public Generator(String name, Currency currencyProduced, BigNum baseProduction, Formula priceFormula, Currency purchaseCurrency) {
        this.name = name;
        this.description = "";
        this.priceFormula = priceFormula;
        this.purchaseCurrency = purchaseCurrency;

        this.amount = new Currency(name, new Picture(0, 0, Const.GENERATOR_ICON_WIDTH, Const.GENERATOR_ICON_HEIGHT, Global.programDirectory + "Images/Icons/Generator/" + name));
        this.produce = currencyProduced;
        this.generatedAmount = new BigNum(0);
        this.purchasedAmount = new BigNum(0);
        this.purchasedAmountInTier = new BigNum(0);

        this.baseProduction = baseProduction;
        this.productionMultiplier = new BigNum(1);
        this.productionMultiplierTier = 1;

        this.generatorFrame = new GeneratorFrame(Global.programDirectory + "Images/Icons/Generator/" + name, this);

        this.isUnlocked = true;

        this.calculateProduction();
        this.calculatePrice();
    }

    public void increaseProductionMultiplier(){
        this.productionMultiplier.multiply(Const.GENERATOR_MULTIPLIER_VALUE);
        this.productionMultiplierTier += 1;
        this.purchasedAmountInTier.set(0);
        this.calculateProduction();
    }

    public BigNum calcAmtInTier(){
        return Const.GENERATOR_MULTIPLIER_INTERVAL.calculate(this.productionMultiplierTier);
    }

    public void calculatePrice(){
        this.price = this.priceFormula.calculate(this.purchasedAmount);
    }
    public void calculateProduction(){
        this.production = BigNum.multiply(baseProduction, productionMultiplier);
    }
    public void calculateAmount(){
        this.amount.amount = BigNum.add(this.purchasedAmount, this.generatedAmount);
    }

    public void buy(){
        if (this.purchaseCurrency.amount.isGreaterEqualTo(this.price)) {
            this.purchaseCurrency.amount.subtract(this.price);

            this.purchasedAmount.add(1);
            this.purchasedAmountInTier.add(1);

            if (this.purchasedAmountInTier.isGreaterEqualTo(Const.GENERATOR_MULTIPLIER_INTERVAL.calculate(this.productionMultiplierTier))){
                increaseProductionMultiplier();
            }

            this.calculatePrice();
            calculateAmount();
        }
    }
    public void generate() {
        calculateAmount();
        this.produce.amount.add(BigNum.multiply(this.amount.amount, BigNum.multiply(this.production, this.productionMultiplier)));
    }
}
