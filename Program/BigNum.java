


public class BigNum{
  
  String bigNum;
  double coefficient;
  int exponent;
  
  
  BigNum(String bigNum){
    this.bigNum = bigNum;
    this.coefficient = Double.parseDouble(bigNum.split("e")[0]);
    this.exponent = Integer.parseInt(bigNum.split("e")[1]);
  }
  
  BigNum(int number){ 
    this.exponent = (int)Math.log10(number);
    this.coefficient = Math.round(number / Math.pow(10, exponent - Const.ROUNDING_ERROR)) / Math.pow(10, Const.ROUNDING_ERROR);
    this.updateBigNum();
  }

  BigNum(double number){
    this.exponent = (int)Math.log10(number);
    this.coefficient = Math.round(number / Math.pow(10, exponent - Const.ROUNDING_ERROR)) / Math.pow(10, Const.ROUNDING_ERROR);
    this.updateBigNum();
  }
  
  void updateBigNum(){
    if (this.coefficient >= 10){
      int extraDigits = (int)Math.log10(this.coefficient);
      this.coefficient = this.coefficient / Math.pow(10, extraDigits);
      this.exponent = this.exponent + extraDigits;
    }
    else if(this.coefficient < 1){
      int lackingDigits = (int)Math.abs(Math.floor(Math.log10(this.coefficient)));
      this.coefficient = this.coefficient * Math.pow(10, lackingDigits);
      this.exponent = this.exponent - lackingDigits;
    }
    
    this.coefficient = Math.round(this.coefficient * Math.pow(10, Const.ROUNDING_ERROR)) / Math.pow(10, Const.ROUNDING_ERROR);
    this.bigNum = Double.toString(this.coefficient) + "e" + Integer.toString(this.exponent);
  }
  
  
  
  double bigNumToNum(){
    //Checks if the number is actually below integer limit
    if (this.exponent <= 9){
      if (this.coefficient < 2.14748){
        return Math.pow(this.coefficient, this.exponent);
      }
    }
    
    return -1;
  }
  
  
  
  void set(int number){
    this.coefficient = number;
    this.updateBigNum(); 
  }
  
  void set(BigNum number){
    this.coefficient = number.coefficient;
    this.exponent = number.exponent;
    this.bigNum = number.bigNum;
  }
  
  boolean isGreaterThan(BigNum number){
    if(this.exponent > number.exponent){
      return true;
    }
    else if(this.exponent == number.exponent && this.coefficient > number.coefficient){
      return true;
    }
    return false;
  }
  
  boolean isGreaterThan(int number){
    int digits = (int)Math.floor(Math.log10(number));
    if(this.exponent > digits){
      return true;
    }
    else if(this.exponent == digits && this.coefficient > number / Math.pow(10, digits)){
      return true;
    }
    return false;
  }
  
  
  boolean isGreaterEqualTo(BigNum number){
    if(this.exponent > number.exponent){
      return true;
    }
    else if(this.exponent == number.exponent && this.coefficient >= number.coefficient){
      return true;
    }
    return false;
  }
  
  boolean isGreaterEqualTo(int number){
    int digits = (int)Math.floor(Math.log10(number));
    if(this.exponent > digits){
      return true;
    }
    else if(this.exponent == digits && this.coefficient >= number / Math.pow(10, digits)){
      return true;
    }
    return false;
  }
    
    
  boolean isEqualTo(BigNum number){
    if(this.coefficient == number.coefficient && this.exponent == number.exponent){
      return true;
    }
    return false;
  }
  
  boolean isEqualTo(int number){
    int digits = (int)Math.floor(Math.log10(number));
    if(this.coefficient == number / Math.pow(10, digits) && this.exponent == digits){
      return true;
    }
    return false;
  }
    
  
  boolean isLessEqualTo(BigNum number){
    if(this.exponent < number.exponent){
      return true;
    }
    else if(this.exponent == number.exponent && this.coefficient <= number.coefficient){
      return true;
    }
    return false;
  }
  
  boolean isLessEqualTo(int number){
    int digits = (int)Math.floor(Math.log10(number));
    if(this.exponent < digits){
      return true;
    }
    else if(this.exponent == digits && this.coefficient <= number / Math.pow(10, digits)){
      return true;
    }
    return false;
  }
  
  
  boolean isLessThan(BigNum number){
    if(this.exponent < number.exponent){
      return true;
    }
    else if(this.exponent == number.exponent && this.coefficient < number.coefficient){
      return true;
    }
    return false;
  }
  
  boolean isLessThan(int number){
    int digits = (int)Math.floor(Math.log10(number));
    if(this.exponent < digits){
      return true;
    }
    else if(this.exponent == digits && this.coefficient < number / Math.pow(10, digits)){
      return true;
    }
    return false;
  }
  
  void add(BigNum addend){
    if (Math.abs(this.exponent - addend.exponent) <= Const.ROUNDING_ERROR){
      int roundingAmt = Math.abs(this.exponent - addend.exponent);
      
      if (this.exponent > addend.exponent){
        this.coefficient = this.coefficient + addend.coefficient / Math.pow(10, roundingAmt);
      }
      else if (addend.exponent > this.exponent){
        this.coefficient = addend.coefficient + this.coefficient / Math.pow(10, roundingAmt);
      }
      else{
        this.coefficient = this.coefficient + addend.coefficient;
      } 
    }
    
    this.updateBigNum();
  }
  
  void add(int addend){
    this.add(new BigNum(addend));
  }
  
  
  void subtract(BigNum subtrahend){
    if (Math.abs(this.exponent - subtrahend.exponent) <= Const.ROUNDING_ERROR){
      int roundingAmt = Math.abs(this.exponent - subtrahend.exponent);
      
      if (this.exponent > subtrahend.exponent){
        this.coefficient = this.coefficient - subtrahend.coefficient / Math.pow(10, roundingAmt);
      }
      else if (subtrahend.exponent > this.exponent){
        this.coefficient = 0 - subtrahend.coefficient - this.coefficient / Math.pow(10, roundingAmt);
        this.exponent = subtrahend.exponent;
      }
      else{
        this.coefficient = this.coefficient - subtrahend.coefficient;
      } 
    }
    
    this.updateBigNum();
  }
  
  void subtract(int subtrahend){
    this.subtract(new BigNum(subtrahend));
  }
  
  
  void multiply(BigNum multiplier){
    
    this.coefficient = this.coefficient * multiplier.coefficient;
    this.exponent = this.exponent + multiplier.exponent;
      
    this.updateBigNum();
  }
  
  void multiply(int multiplier){
    this.multiply(new BigNum(multiplier));
  }
  
  
  
  void divide(BigNum divisor){
    System.out.println(this.coefficient + " + " + divisor.coefficient);
    this.coefficient = this.coefficient / divisor.coefficient;
    System.out.println(this.coefficient);
    this.exponent = this.exponent - divisor.exponent;
    
    this.updateBigNum();
  }
  
  void divide(int divisor){
    this.divide(new BigNum(divisor));
  }
  
  
  void pow(int exponent){
    
    this.coefficient = Math.pow(this.coefficient, exponent);
    this.exponent = this.exponent * exponent;
    
    this.updateBigNum();
  }
  
  void pow(BigNum exponent){
    this.pow((int)exponent.bigNumToNum());
  }
  
  
  
  
  
  public static BigNum add(BigNum bigNum1, BigNum bigNum2){
    BigNum result = new BigNum(bigNum1.bigNum);
    result.add(bigNum2);
    
    return result;
  }
  public static BigNum add(BigNum bigNum1, int number2){
    BigNum result = new BigNum(bigNum1.bigNum);
    result.add(number2);
    
    return result;
  }
  
  
  
  public static BigNum subtract(BigNum bigNum1, BigNum bigNum2){
    BigNum result = new BigNum(bigNum1.bigNum);
    result.subtract(bigNum2);
    
    return result;
  }
  public static BigNum subtract(BigNum bigNum1, int number2){
    BigNum result = new BigNum(bigNum1.bigNum);
    result.subtract(number2);
    
    return result;
  }
  
  
  
  public static BigNum multiply(BigNum bigNum1, BigNum bigNum2){
    BigNum result = new BigNum(bigNum1.bigNum);
    result.multiply(bigNum2);
    
    return result;
  } 
  public static BigNum multiply(BigNum bigNum1, int number2){
    BigNum result = new BigNum(bigNum1.bigNum);
    result.multiply(number2);
    
    return result;
  }
  
  
  
  public static BigNum divide(BigNum bigNum1, BigNum bigNum2){
    BigNum result = new BigNum(bigNum1.bigNum);
    result.divide(bigNum2);
    
    return result;
  }
  public static BigNum divide(BigNum bigNum1, int number2){
    BigNum result = new BigNum(bigNum1.bigNum);
    result.divide(number2);
    
    return result;
  }
  
  
  
  public static BigNum pow(BigNum base, int exponent){
    BigNum result = new BigNum(base.bigNum);
    result.pow(exponent);
    
    return new BigNum(result.bigNum);
  }
  public static BigNum pow(BigNum base, BigNum exponent){
    BigNum result = new BigNum(base.bigNum);
    result.pow(exponent);
    
    return new BigNum(result.bigNum);
  }
  
  
  public static BigNum log10(BigNum base){
    return (new BigNum(Math.log10(base.coefficient) + base.exponent));
  }
  
  
}
