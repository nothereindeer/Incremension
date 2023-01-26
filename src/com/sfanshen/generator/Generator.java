package com.sfanshen.generator;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;
import com.sfanshen.main.Formula;
import com.sfanshen.main.Global;

public class Generator {

    public String name;
    BigNum baseProduction;
    public int tier;
    public int purchasedAmountInTier;
    BigNum productionMultiplier;
    BigNum production;

    public BigNum price;
    BigNum[] tierPrices;
    Formula priceFormula;
    public Currency purchaseCurrency;

    public int purchasedAmount;
    public Currency generatedAmount;
    public BigNum amount;

    Currency produce;

    public GeneratorFrame generatorFrame;
    public boolean isUnlocked;

    public Generator(String name, String currencyProduced, String baseProduction, String purchaseCurrency, String priceFormula, String[] tierPrices) {
        this.name = name;
        instantiateTierPrices(tierPrices);
        this.priceFormula = new Formula(priceFormula);
        this.purchaseCurrency = Global.findCurrency(purchaseCurrency);

        this.amount = new BigNum(0);
        this.purchasedAmount = 0;
        this.generatedAmount = new Currency(name, new Picture(0, 0, Const.GENERATOR_ICON_WIDTH, Const.GENERATOR_ICON_HEIGHT, Global.programDirectory + "Images/Icons/Generator/" + name + ".png"));
        this.purchasedAmountInTier = 0;

        this.produce = Global.findCurrency(currencyProduced);
        this.baseProduction = BigNum.divide(new BigNum(baseProduction), Global.ticksPerSec);
        this.productionMultiplier = new BigNum(1);
        this.tier = 1;

        this.generatorFrame = new GeneratorFrame(Global.programDirectory + "Images/Icons/Generator/" + name + ".png", this);

        this.isUnlocked = true;

        Global.generators.put(this.name, this);

        this.calculateProduction();
        this.calculatePrice();
    }

    public void instantiateTierPrices(String[] tierPrices) {
        BigNum[] convertedTierPrices = new BigNum[tierPrices.length];
        for (int i = 0; i < tierPrices.length; i++) {
            String tierPrice = tierPrices[i];
            convertedTierPrices[i] = new BigNum(tierPrice);
        }

        this.tierPrices = convertedTierPrices;
    }

    public void increaseProductionMultiplier() {
        this.tier += 1;
        this.purchasedAmountInTier = 0;
        this.calculateProductionMultiplier();
        this.calculateProduction();
    }

    public void calculateProductionMultiplier() {
        this.productionMultiplier = new BigNum(Math.pow(Const.GENERATOR_MULTIPLIER_VALUE, this.tier - 1));
    }

    public boolean isPurchasable() {
        return this.purchaseCurrency.amount.isGreaterEqualTo(this.price);
    }

    public void calculatePrice() {
        if (this.tier > 1)
            this.price = BigNum.multiply(this.priceFormula.calculate(this.purchasedAmount), this.tierPrices[this.tier - 1]);
        else
            this.price = this.priceFormula.calculate(this.purchasedAmount);
    }

    public void calculateProduction() {
        this.production = BigNum.multiply(baseProduction, productionMultiplier);
    }

    public void calculateAmount() {
        this.amount = BigNum.add(this.generatedAmount.amount, this.purchasedAmount);
    }

    public void calculateTier(){
        this.tier = this.purchasedAmount / Const.GENERATOR_MULTIPLIER_INTERVAL + 1;
        this.purchasedAmountInTier = this.purchasedAmount % Const.GENERATOR_MULTIPLIER_INTERVAL;
    }

    public void buy() {
        if (this.isPurchasable()) {
            this.purchaseCurrency.amount.subtract(this.price);

            this.purchasedAmount = this.purchasedAmount + 1;
            this.purchasedAmountInTier = this.purchasedAmountInTier + 1;

            if (this.purchasedAmountInTier >= Const.GENERATOR_MULTIPLIER_INTERVAL) {
                increaseProductionMultiplier();
            }

            this.calculatePrice();
            this.calculateAmount();
        }
    }

    public void generate() {
        calculateAmount();
        this.produce.increase(BigNum.multiply(BigNum.multiply(this.amount, BigNum.multiply(this.production, this.productionMultiplier)), Const.MULTIPLIER));
    }
}
