

public class Global{
  public static HashMap<String, Currency> CURRENCIES;
  
  static{
    CURRENCIES = new HashMap<String, Currency>();
    CURRENCIES.put("coins", (new Currency("coins")));
    CURRENCIES.put("rubies", (new Currency("rubies")));
  }
}