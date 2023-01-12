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
  
  Image icon;
  
  Currency(String name, Image icon){
    this.name = name;
    this.amount = new BigNum(0);
    additiveUpgrades = new ArrayList<BoostUpgrade>();
    multiplicativeUpgrades = new ArrayList<BoostUpgrade>();
    exponentialUpgrades = new ArrayList<BoostUpgrade>();
    
    this.finalBoost = new BigNum(1);
    this.additiveBoost = new BigNum(1);
    this.multiplicativeBoost = new BigNum(1);
    this.exponentialBoost = new BigNum(1);
    
    this.icon = icon;
  }
  
  void set(BigNum amount){
    this.amount.set(amount);
  }
  
  void increase(BigNum n){
    n.multiply(this.finalBoost);
    this.amount.add(n);
  }
  void increase(int n){
    this.amount.add(BigNum.multiply(this.finalBoost, new BigNum(n)));
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
       if (additiveUpgrade.level > 0){
         this.additiveBoost.add(additiveUpgrade.calculateBoost(this));
       }
    }
  }
  
  void calculateMultiplicativeBoost(){
    for (BoostUpgrade multiplicativeUpgrade: multiplicativeUpgrades){
      if (multiplicativeUpgrade.level > 0){
        multiplicativeBoost.multiply(multiplicativeUpgrade.calculateBoost(this));
      }
    }
  }
  
  void calculateExponentialBoost(){
    for (BoostUpgrade exponentialUpgrade: exponentialUpgrades){
      if (exponentialUpgrade.level > 0){
        exponentialBoost.multiply(exponentialUpgrade.calculateBoost(this));
      
      }
    }
  }
}