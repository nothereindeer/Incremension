package com.sfanshen.Formula;

import com.sfanshen.main.BigNum;

public class BaseFormula implements Formula{
    int termCount;
    BigNum[] terms;
    public BaseFormula(BigNum[] terms){
        this.termCount = terms.length;
        this.terms = terms;
    }
    public BaseFormula(String terms){
        this.termCount = terms.split(",").length;
        convertTerms(terms.split(","));
    }

    public void convertTerms(String[] terms){
        BigNum[] convertedTerms = new BigNum[terms.length];
        for (int i = 0; i < terms.length; i ++){
            convertedTerms[i] = new BigNum(terms[i].trim());
        }

        this.terms = convertedTerms;
    }
    public BigNum calculate(BigNum x){
        BigNum value = new BigNum(0);
        for (int i = 0; i < this.terms.length; i ++){
            int j = terms.length - i - 1;
            value.add(BigNum.multiply(this.terms[j], BigNum.pow(x, i)));
        }

        return value;
    }

    public BigNum calculate(int intX){
        return calculate(new BigNum(intX));
    }
}
