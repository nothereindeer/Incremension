package com.sfanshen.main;

public class Formula {

    public String formula;
    public String operation;
    String formulaType;


    //-------------------------------------------------------Constructor-----------------------------------------------------------------\\

    public Formula(String formula) {
        int length = formula.split(" ").length;
        int termCount = length / 2 + 1;
        switch (termCount){
            case 2:
                this.formulaType = "linear";
                break;
            case 3:
                this.formulaType = "quadratic";
                break;
            case 4:
                this.formulaType = "quartic";
                break;
            default:
                System.out.println("too many terms? " + termCount + "terms");
        }

        this.formula = formula.substring(1);
        this.operation = Character.toString(formula.charAt(0));
    }


    //-------------------------------------------------------Methods-----------------------------------------------------------------\\

    //Runs formula, returns value based off of inputted value as a BigNum
    public BigNum calculate(BigNum x) {
        switch (this.formulaType) {
            case "linear":
                return calculateLinear(x);
            case "quadratic":
                return calculateQuadratic(x);
            case "quartic":
                return calculateQuartic(x);
            default:
                System.out.println("Error while parsing formula: " + this.formula);
                return new BigNum(-1);
        }
    }

    public BigNum calculate(int w) {
        BigNum x = new BigNum(w);

        return calculate(x);
    }


    public BigNum calculateLinear(BigNum x){
        String[] terms = this.formula.split(" ");
        BigNum m = new BigNum(Integer.parseInt(terms[0].substring(0, terms[0].length() - 1)));
        BigNum b = new BigNum(Integer.parseInt(terms[2]));

        return BigNum.add(BigNum.multiply(m, x), b);
    }

    public BigNum calculateQuadratic(BigNum x){
        String[] terms = this.formula.split(" ");
        BigNum a = new BigNum(Integer.parseInt(terms[0].substring(0, terms[0].length() - 3)));
        BigNum b = new BigNum(Integer.parseInt(terms[2].substring(0, terms[2].length() - 1)));
        BigNum c = new BigNum(Integer.parseInt(terms[4]));

        return BigNum.add(BigNum.add(BigNum.multiply(a, BigNum.pow(x, 2)), BigNum.multiply(b, x)), c);
    }

    public BigNum calculateQuartic(BigNum x){
        String[] terms = this.formula.split(" ");
        BigNum a = new BigNum(Integer.parseInt(terms[0].substring(0, terms[0].length() - 3)));
        BigNum b = new BigNum(Integer.parseInt(terms[2].substring(0, terms[2].length() - 3)));
        BigNum c = new BigNum(Integer.parseInt(terms[4].substring(0, terms[4].length() - 1)));
        BigNum d = new BigNum(Integer.parseInt(terms[6]));

        return BigNum.add(BigNum.add(BigNum.add(BigNum.multiply(a, BigNum.pow(x, 3)), BigNum.multiply(b, BigNum.pow(x, 2))), BigNum.multiply(c, x)), d);
    }


}
