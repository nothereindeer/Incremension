package com.sfanshen.Formula;

import com.sfanshen.main.BigNum;

public class ExponentFormula implements Formula{
    int termCount;
    BigNum[] terms;
    public ExponentFormula(BigNum[] terms){
        this.termCount = terms.length;
        this.terms = terms;
    }

    public BigNum calculate(BigNum x){
        BigNum value = new BigNum(1);
        for (int i = 0; i < this.terms.length; i ++){
            if (i == 0)
                value.multiply(this.terms[i]);
            else
                value.multiply(BigNum.pow(this.terms[i], x));
        }

        return value;
    }

    public BigNum calculate(int intX){
        return calculate(new BigNum(intX));
    }
}
