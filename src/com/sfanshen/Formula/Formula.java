package com.sfanshen.Formula;

import com.sfanshen.main.BigNum;

public interface Formula {
    BigNum calculate(BigNum x);
    BigNum calculate(int x);
}
