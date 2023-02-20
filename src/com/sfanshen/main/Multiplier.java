package com.sfanshen.main;

import com.sfanshen.currency.Currency;
import com.sfanshen.upgrade.BoostUpgrade;

import java.util.ArrayList;

public class Multiplier {
    public BigNum finalMultiplier;
    BigNum additiveMultiplier;
    BigNum multiplicativeMultiplier;
    BigNum exponentialMultiplier;

    public ArrayList<BoostUpgrade> additiveUpgrades;
    public ArrayList<BoostUpgrade> multiplicativeUpgrades;
    public ArrayList<BoostUpgrade> exponentialUpgrades;

    public Multiplier(){
        this.finalMultiplier = new BigNum(1);
        this.additiveMultiplier = new BigNum(0);
        this.multiplicativeMultiplier = new BigNum(1);
        this.exponentialMultiplier = new BigNum(1);

        additiveUpgrades = new ArrayList<>();
        multiplicativeUpgrades = new ArrayList<>();
        exponentialUpgrades = new ArrayList<>();
    }

    public void calculateMultiplier(Currency currency) {
        this.calculateAdditiveMultiplier(currency);
        this.calculateMultiplicativeMultiplier(currency);
        this.calculateExponentialMultiplier(currency);
        this.finalMultiplier.set(1);

        this.finalMultiplier.add(this.additiveMultiplier);
        this.finalMultiplier.multiply(this.multiplicativeMultiplier);
        this.finalMultiplier.pow(this.exponentialMultiplier);
    }

    public void calculateAdditiveMultiplier(Currency currency) {
        this.additiveMultiplier.set(0);
        for (BoostUpgrade additiveUpgrade : this.additiveUpgrades) {
            if (additiveUpgrade.level > 0) {
                this.additiveMultiplier.add(additiveUpgrade.calculateMultiplier(currency));
            }
        }
    }

    public void calculateMultiplicativeMultiplier(Currency currency) {
        this.multiplicativeMultiplier.set(1);
        for (BoostUpgrade multiplicativeUpgrade : multiplicativeUpgrades) {
            if (multiplicativeUpgrade.level > 0) {
                this.multiplicativeMultiplier.multiply(multiplicativeUpgrade.calculateMultiplier(currency));
            }
        }
    }

    public void calculateExponentialMultiplier(Currency currency) {
        this.exponentialMultiplier.set(1);
        for (BoostUpgrade exponentialUpgrade : exponentialUpgrades) {
            if (exponentialUpgrade.level > 0) {
                this.exponentialMultiplier.multiply(exponentialUpgrade.calculateMultiplier(currency));

            }
        }
    }
}
