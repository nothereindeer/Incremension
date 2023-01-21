package com.sfanshen.main;


public class BigNum {

    public String bigNum;
    double coefficient;
    int exponent;

    //-------------------------------------------------------Constructors-----------------------------------------------------------------\\
    public BigNum(String bigNum) {
        this.bigNum = bigNum;
        this.coefficient = Double.parseDouble(bigNum.split("e")[0]);
        this.exponent = Integer.parseInt(bigNum.split("e")[1]);
    }

    public BigNum(int number) {
        if (number == 0) {
            this.exponent = 0;
            this.coefficient = 0;
        } else {
            this.exponent = (int) Math.log10(number);
            this.coefficient = Math.round(number / Math.pow(10, exponent - Const.ROUNDING_ERROR)) / Math.pow(10, Const.ROUNDING_ERROR);
        }

        this.updateBigNum();
    }

    public BigNum(double number) {
        if (number == 0) {
            this.exponent = 0;
            this.coefficient = 0;
        } else {
            this.exponent = (int) Math.log10(number);
            this.coefficient = Math.round(number / Math.pow(10, exponent - Const.ROUNDING_ERROR)) / Math.pow(10, Const.ROUNDING_ERROR);
        }

        this.updateBigNum();
    }


    //-------------------------------------------------------Methods-----------------------------------------------------------------\\


    //Makes sure BigNum is still in scientific notation, rounds to a specified amount of digits(found in Const file, currently at 5) to prevent overuse of data
    public void updateBigNum() {
        if (this.coefficient >= 10) {
            int extraDigits = (int) Math.log10(this.coefficient);
            this.coefficient = this.coefficient / Math.pow(10, extraDigits);
            this.exponent = this.exponent + extraDigits;
        } else if (this.coefficient < 1 && this.coefficient > 0) {
            int lackingDigits = (int) Math.abs(Math.floor(Math.log10(this.coefficient)));
            this.coefficient = this.coefficient * Math.pow(10, lackingDigits);
            this.exponent = this.exponent - lackingDigits;
        }

        this.coefficient = Math.round(this.coefficient * Math.pow(10, Const.ROUNDING_ERROR)) / Math.pow(10, Const.ROUNDING_ERROR);
        this.bigNum = this.coefficient + "e" + this.exponent;
    }


    //Converts BigNum to a double number
    public double toNum() {
        //Checks if the number is actually below integer limit
        if (this.exponent < 9) {
            return this.coefficient * Math.pow(10, this.exponent);
        } else if (this.exponent == 9) {
            if (this.coefficient < 2.14748) {
                return this.coefficient * Math.pow(10, this.exponent);
            }
        }
        System.out.println("Attempted to convert a BigNum that exceeds the number limit to a regular number");
        return -1;
    }


    //Set to number
    public void set(int number) {
        if (number == 0) {
            this.exponent = 0;
            this.coefficient = 0;
        } else {
            this.exponent = (int) Math.log10(number);
            this.coefficient = Math.round(number / Math.pow(10, exponent - Const.ROUNDING_ERROR)) / Math.pow(10, Const.ROUNDING_ERROR);
        }
        this.updateBigNum();
    }

    public void set(BigNum number) {
        this.coefficient = number.coefficient;
        this.exponent = number.exponent;
        this.bigNum = number.bigNum;
    }


    //-----------------------Comparisons-----------------------\\
    public boolean isGreaterThan(BigNum number) {
        if (this.exponent > number.exponent)
            return true;
        else return this.exponent == number.exponent && this.coefficient > number.coefficient;
    }

    public boolean isGreaterThan(int number) {
        int digits = (int) Math.floor(Math.log10(number));
        if (this.exponent > digits)
            return true;
        else return this.exponent == digits && this.coefficient > number / Math.pow(10, digits);
    }

    public boolean isGreaterEqualTo(BigNum number) {
        if (this.exponent > number.exponent)
            return true;
        else return this.exponent == number.exponent && this.coefficient >= number.coefficient;
    }

    public boolean isGreaterEqualTo(int number) {
        int digits = (int) Math.floor(Math.log10(number));
        if (this.exponent > digits)
            return true;
        else return this.exponent == digits && this.coefficient >= number / Math.pow(10, digits);
    }

    public boolean isEqualTo(BigNum number) {
        return this.coefficient == number.coefficient && this.exponent == number.exponent;
    }

    public boolean isEqualTo(int number) {
        int digits = (int) Math.floor(Math.log10(number));
        return this.coefficient == number / Math.pow(10, digits) && this.exponent == digits;
    }

    public boolean isLessEqualTo(BigNum number) {
        if (this.exponent < number.exponent)
            return true;
        else return this.exponent == number.exponent && this.coefficient <= number.coefficient;
    }

    public boolean isLessEqualTo(int number) {
        int digits = (int) Math.floor(Math.log10(number));
        if (this.exponent < digits) {
            return true;
        } else return this.exponent == digits && this.coefficient <= number / Math.pow(10, digits);
    }

    public boolean isLessThan(BigNum number) {
        if (this.exponent < number.exponent) {
            return true;
        } else return this.exponent == number.exponent && this.coefficient < number.coefficient;
    }

    public boolean isLessThan(int number) {
        int digits = (int) Math.floor(Math.log10(number));
        if (this.exponent < digits) {
            return true;
        } else return this.exponent == digits && this.coefficient < number / Math.pow(10, digits);
    }


    //-----------------------Operations-----------------------\\

    public void add(BigNum addend) {
        if (Math.abs(this.exponent - addend.exponent) <= Const.ROUNDING_ERROR) {
            int roundingAmt = Math.abs(this.exponent - addend.exponent);

            if (this.exponent > addend.exponent) {
                this.coefficient = this.coefficient + addend.coefficient / Math.pow(10, roundingAmt);
            } else if (addend.exponent > this.exponent) {
                this.coefficient = addend.coefficient + this.coefficient / Math.pow(10, roundingAmt);
            } else {
                this.coefficient = this.coefficient + addend.coefficient;
            }
        }
        this.updateBigNum();
    }

    public void add(int addend) {
        this.add(new BigNum(addend));
    }

    public void subtract(BigNum subtrahend) {
        if (Math.abs(this.exponent - subtrahend.exponent) <= Const.ROUNDING_ERROR) {
            int roundingAmt = Math.abs(this.exponent - subtrahend.exponent);

            if (this.exponent > subtrahend.exponent) {
                this.coefficient = this.coefficient - subtrahend.coefficient / Math.pow(10, roundingAmt);
            } else if (subtrahend.exponent > this.exponent) {
                this.coefficient = 0 - subtrahend.coefficient - this.coefficient / Math.pow(10, roundingAmt);
                this.exponent = subtrahend.exponent;
            } else {
                this.coefficient = this.coefficient - subtrahend.coefficient;
            }
        }
        this.updateBigNum();
    }

    public void subtract(int subtrahend) {
        this.subtract(new BigNum(subtrahend));
    }

    public void multiply(BigNum multiplier) {

        this.coefficient = this.coefficient * multiplier.coefficient;
        this.exponent = this.exponent + multiplier.exponent;

        this.updateBigNum();
    }

    public void multiply(int multiplier) {
        this.multiply(new BigNum(multiplier));
    }

    public void divide(BigNum divisor) {
        this.coefficient = this.coefficient / divisor.coefficient;
        this.exponent = this.exponent - divisor.exponent;

        this.updateBigNum();
    }

    public void divide(int divisor) {
        this.divide(new BigNum(divisor));
    }

    public void pow(int exponent) {

        this.coefficient = Math.pow(this.coefficient, exponent);
        this.exponent = this.exponent * exponent;

        this.updateBigNum();
    }

    public void pow(BigNum exponent) {
        this.pow((int) exponent.toNum());
    }


    //-----------------------Static Operations-----------------------\\
    //These operation methods return a new BigNum object

    public static BigNum add(BigNum bigNum1, BigNum bigNum2) {
        BigNum result = new BigNum(bigNum1.bigNum);
        result.add(bigNum2);

        return result;
    }

    public static BigNum add(BigNum bigNum1, int number2) {
        BigNum result = new BigNum(bigNum1.bigNum);
        result.add(number2);

        return result;
    }

    public static BigNum subtract(BigNum bigNum1, BigNum bigNum2) {
        BigNum result = new BigNum(bigNum1.bigNum);
        result.subtract(bigNum2);

        return result;
    }

    public static BigNum subtract(BigNum bigNum1, int number2) {
        BigNum result = new BigNum(bigNum1.bigNum);
        result.subtract(number2);

        return result;
    }

    public static BigNum multiply(BigNum bigNum1, BigNum bigNum2) {
        BigNum result = new BigNum(bigNum1.bigNum);
        result.multiply(bigNum2);

        return result;
    }

    public static BigNum multiply(BigNum bigNum1, int number2) {
        BigNum result = new BigNum(bigNum1.bigNum);
        result.multiply(number2);

        return result;
    }

    public static BigNum divide(BigNum bigNum1, BigNum bigNum2) {
        BigNum result = new BigNum(bigNum1.bigNum);
        result.divide(bigNum2);

        return result;
    }

    public static BigNum divide(BigNum bigNum1, int number2) {
        BigNum result = new BigNum(bigNum1.bigNum);
        result.divide(number2);

        return result;
    }

    public static BigNum pow(BigNum base, int exponent) {
        BigNum result = new BigNum(base.bigNum);
        result.pow(exponent);

        return new BigNum(result.bigNum);
    }

    public static BigNum pow(BigNum base, BigNum exponent) {
        BigNum result = new BigNum(base.bigNum);
        result.pow(exponent);

        return new BigNum(result.bigNum);
    }

    public static BigNum log10(BigNum base) {
        return (new BigNum(Math.log10(base.coefficient) + base.exponent));
    }
}
