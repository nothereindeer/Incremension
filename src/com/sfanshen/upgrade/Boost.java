package com.sfanshen.upgrade;

import com.sfanshen.Formula.BaseFormula;
import com.sfanshen.Formula.Formula;
import com.sfanshen.currency.Currency;
import com.sfanshen.main.Multiplier;

public class Boost {
    Currency boostedCurrency;
    public String operation;
    BaseFormula formula;
    Boost(Currency boostedCurrency, String operation, BaseFormula formula){
        this.boostedCurrency = boostedCurrency;
        this.operation = operation;
        this.formula = formula;
    }
}
