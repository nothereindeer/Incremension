package com.sfanshen.Formula;

import com.sfanshen.main.BigNum;

public class ResetFormula {
    double expBase;
    double logBase;
    BigNum minimum;

    public ResetFormula(String terms){
        //1.5
        this.expBase = Double.parseDouble((terms.split(","))[0].trim());
        //10
        this.logBase = Double.parseDouble(terms.split(",")[1].trim());
        //1e15
        this.minimum = new BigNum(terms.split(",")[2]);
    }
    public BigNum calculate(BigNum x) {
        return BigNum.divide(BigNum.divide(x, this.minimum), BigNum.pow(this.expBase, BigNum.log(this.logBase, BigNum.divide(x, this.minimum))));
    }

    public BigNum calculate(int x) {
        return calculate(new BigNum(x));
    }
}
