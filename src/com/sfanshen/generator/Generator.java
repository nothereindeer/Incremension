package com.sfanshen.generator;

import com.sfanshen.currency.Currency;
import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.main.Const;
import com.sfanshen.main.Formula;
import com.sfanshen.main.Global;

public class Generator {

    public String name;

    public BigNum cost;
    Formula costFormula;
    BigNum[] tierCosts;
    public Currency purchaseCurrency;

    public int tier, purchasedAmountInTier;
    public BigNum baseProduction, productionMultiplier, production;
    Currency produce;

    public int purchasedAmount;
    public Currency generatedAmount;
    public BigNum amount;

    public GeneratorFrame generatorFrame;

    public boolean isUnlocked;


    //-------------------------------------------------------Constructor-----------------------------------------------------------------\\
    public Generator(String name, String currencyProduced, String baseProduction, String purchaseCurrency, String costFormula, String[] tierPrices) {
        this.name = name;
        instantiateTierCosts(tierPrices);
        this.costFormula = new Formula(costFormula);
        this.purchaseCurrency = Global.findCurrency(purchaseCurrency);

        this.amount = new BigNum(0);
        this.purchasedAmount = 0;
        this.generatedAmount = new Currency(name, new Picture(0, 0, Const.GENERATOR_ICON_WIDTH, Const.GENERATOR_ICON_HEIGHT, Global.programDirectory + "Images/Icons/Generator/" + name + ".png"));
        this.purchasedAmountInTier = 0;

        this.produce = Global.findCurrency(currencyProduced);
        this.baseProduction = BigNum.divide(new BigNum(baseProduction), Global.ticksPerSec);
        this.productionMultiplier = new BigNum(1);
        this.tier = 1;

        this.generatorFrame = new GeneratorFrame(this);

        this.isUnlocked = true;

        Global.generators.put(this.name, this);

        this.calculateProduction();
        this.calculatePrice();
    }

    //-------------------------------------------------------Methods-----------------------------------------------------------------\\

    //----------Main Methods----------\\
    public void buy() {
        if (this.isPurchasable()) {
            this.purchaseCurrency.amount.subtract(this.cost);

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

    public boolean isPurchasable() {
        return this.purchaseCurrency.amount.isGreaterEqualTo(this.cost);
    }

    public void increaseProductionMultiplier() {
        this.tier += 1;
        this.purchasedAmountInTier = 0;
        this.calculateProductionMultiplier();
        this.calculateProduction();
    }


    //----------Calculations----------\\

    public void calculateProductionMultiplier() {
        this.productionMultiplier = new BigNum(Math.pow(Const.GENERATOR_MULTIPLIER_VALUE, this.tier - 1));
    }

    public void calculatePrice() {
        if (this.tier > 1)
            this.cost = BigNum.multiply(this.costFormula.calculate(this.purchasedAmount), this.tierCosts[this.tier - 1]);
        else
            this.cost = this.costFormula.calculate(this.purchasedAmount);
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


    //----------Instantiation----------\\
    public void instantiateTierCosts(String[] tierCosts) {
        BigNum[] convertedTierCosts = new BigNum[tierCosts.length];
        for (int i = 0; i < tierCosts.length; i++) {
            String tierPrice = tierCosts[i];
            convertedTierCosts[i] = new BigNum(tierPrice);
        }
        this.tierCosts = convertedTierCosts;
    }
}
