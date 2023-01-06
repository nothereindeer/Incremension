import java.util.ArrayList;


class Currency{
  String name;
  BigNum amount;
  //Additive boosts are applied before multiplicative ones in the form of baseBoost and finalBoost
  BigNum finalBoost;
  BigNum additiveBoost;
  BigNum multiplicativeBoost;
  BigNum exponentialBoost;
  
  ArrayList<BoostUpgrade> additiveUpgrades;
  ArrayList<BoostUpgrade> multiplicativeUpgrades;
  ArrayList<BoostUpgrade> exponentialUpgrades;
  
  Currency(String name){
    this.name = name;
    this.amount = new BigNum(0);
    additiveUpgrades = new ArrayList<BoostUpgrade>();
    multiplicativeUpgrades = new ArrayList<BoostUpgrade>();
    exponentialUpgrades = new ArrayList<BoostUpgrade>();
    
    this.finalBoost = new BigNum(1);
    this.additiveBoost = new BigNum(1);
    this.multiplicativeBoost = new BigNum(1);
    this.exponentialBoost = new BigNum(1);
  }
  
  void set(BigNum amount){
    this.amount.set(amount);
  }
  
  void increase(){
    this.amount.add(this.finalBoost);
  }
  
  
  void calculateBoost(){
    this.finalBoost.set(1);
    
    this.finalBoost.add(this.additiveBoost);
    this.finalBoost.multiply(this.multiplicativeBoost);
    this.finalBoost.pow(this.exponentialBoost);
  }
  
  void calculateAdditiveBoost(){
     for (BoostUpgrade additiveUpgrade : this.additiveUpgrades){
       this.additiveBoost.add(additiveUpgrade.calculateBoost(this));
    }
  }
  
  void calculateMultiplicativeBoost(){
    for (BoostUpgrade multiplicativeUpgrade: multiplicativeUpgrades){
      multiplicativeBoost.multiply(multiplicativeUpgrade.calculateBoost(this));
    }
  }
  
  void calculateExponentialBoost(){
    for (BoostUpgrade exponentialUpgrade: exponentialUpgrades){
      exponentialBoost.multiply(exponentialUpgrade.calculateBoost(this));
    }
  }
}