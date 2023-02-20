package com.sfanshen.Formula;

import com.sfanshen.main.BigNum;

public class LogFormula implements  Formula{
    int logBase;
    BigNum minimum;

    public LogFormula(String terms){
        this.logBase = Integer.parseInt((terms.split(","))[0].trim());
        this.minimum = new BigNum(terms.split(",")[1]);
    }
    public BigNum calculate(BigNum x) {
        return BigNum.log(this.logBase, x);
    }

    public BigNum calculate(int x) {
        return calculate(new BigNum(x));
    }
}
