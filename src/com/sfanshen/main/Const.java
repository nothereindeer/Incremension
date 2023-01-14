package com.sfanshen.main;

import java.awt.*;

//A class for all the constant variables found in the game
public class Const{

  //-------------------------------------------------------UI Constants-----------------------------------------------------------------\\

  //Screen size
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;

  //Currency display information
  public static final int CURRENCY_TEXT_SIZE= 14;
  public static final int CURRENCY_ICON_WIDTH = 5;
  public static final int CURRENCY_ICON_HEIGHT = 5;

  //Upgrade buttons scaling
  public static final int UPGRADE_WIDTH = 20;
  public static final int UPGRADE_HEIGHT = UPGRADE_WIDTH;
  public static final double UPGRADE_OFFSET = 0.1 * UPGRADE_WIDTH;
  public static final double UPGRADE_BORDER_WIDTH = 0.05 * UPGRADE_WIDTH;
  public static final int UPGRADE_ICON_WIDTH = 10;
  public static final int UPGRADE_ICON_HEIGHT = 10;

  //Colour theme
  public static final Color BLACK = new Color(13,31,45);
  public static final Color NOT_WHITE = new Color(13,31,45);
  public static final Color SUN_YELLOW = new Color(230,241,74);
  public static final Color BAGE_OR_SMTHNNN = new Color(239,188,155);
  public static final Color PINK_RED = new Color(229, 83, 129);
  public static final Color PLAIN_BLUE = new Color(37, 110, 255);
  public static final Color B2B_GRAY = new Color(178,176,155);
  public static final Color DARKISH_BROWN = new Color(107,39,55);
  public static final Color DARK_TURQUOISE = new Color(21,112,122);
  public static final Color GOOGLE_HIGHLIGHT = new Color(175, 203, 255);
  public static final Color TINTED_GOLD = new Color(155,137,49);

  //Fonts
  public static final Font currencyFont = new Font("Baskerville", Font.BOLD, Const.CURRENCY_TEXT_SIZE);



  //-------------------------------------------------------Game Constants-----------------------------------------------------------------\\


  //BigNum rounding error
  public static final int ROUNDING_ERROR = 5;

}
