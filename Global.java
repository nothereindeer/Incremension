import java.util.HashMap;
import java.util.ArrayList;

public class Global{
  public static HashMap<String, Currency> currencies;
  
  public static ArrayList<Upgrade> upgrades;
  
  public static void main(String[] args){
    currencies = new HashMap<String, Currency>();
    currencies.put("coins", (new Currency("coins")));
    currencies.put("rubies", (new Currency("rubies")));
    
    //String name, int price, Currency purchaseCurrency, Currency boostedCurrency, String boostFormula, int maxLevel, int x, int y, int width, int height, String displayScreen
    upgrades.add(new LeveledUpgrade("Better Pickaxes", 100, Global.currencies.get("coins"), Global.currencies.get("coins"), "(1+x)*", 10, 100, 100, 50, 50, "upgrades"));
  }
}