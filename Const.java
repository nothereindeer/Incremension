import java.util.HashMap;

public class Const{
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;
  
  public static final int CURRENCY_TEXT_SIZE= 14;
  
  public static final HashMap<String, Currency> CURRENCIES;
  
  static{
    CURRENCIES = new HashMap<String, Currency>();
    CURRENCIES.put("coins", (new Currency("coins")));
    CURRENCIES.put("prestige points", (new Currency("prestige points")));
  }
}