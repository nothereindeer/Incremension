package com.sfanshen.main;

import java.awt.*;

//A class for all the constant variables found in the game
public class Const {

    //-------------------------------------------------------UI Constants-----------------------------------------------------------------\\


    public static final int MULTIPLIER = 1000;
    public static final int FPS = 100;
    //Screen size
    public static final int SCREEN_WIDTH = 1600;
    public static final int SCREEN_HEIGHT = 1200;

    //Main Menu stuff
    public static final int PLAY_BUTTON_X = (int) (SCREEN_WIDTH / 2);
    public static final int PLAY_BUTTON_Y = (int) (0.7 * SCREEN_HEIGHT);

    public static final int TITLE_IMAGE_X = (int) (SCREEN_WIDTH / 2);
    public static final int TITLE_IMAGE_Y = (int) (0.4 * SCREEN_HEIGHT);

    //Uh frame stuff I guess
    public static final int FRAME_X = (int) (0.15 * SCREEN_WIDTH);
    public static final int FRAME_Y = (int) (0.12 * SCREEN_HEIGHT);
    public static final int FRAME_WIDTH = (int) (0.7 * SCREEN_WIDTH);
    public static final int FRAME_HEIGHT = (int) (0.7 * SCREEN_HEIGHT);
    public static final int FRAME_BORDER_THICKNESS = SCREEN_HEIGHT / 240;

    public static final int TAB_SELECTION_OFFSET = SCREEN_WIDTH / 320;
    public static final int TAB_SELECTION_HEIGHT = SCREEN_HEIGHT / 24;

    //Currency display information
    public static final int CURRENCY_TEXT_SIZE = SCREEN_HEIGHT / 75;
    public static final int CURRENCY_ICON_SIZE = SCREEN_HEIGHT / 60;
    public static final int CURRENCY_OFFSET_FROM_TOP = SCREEN_HEIGHT / 60;
    public static final int TEXT_ICON_OFFSET = SCREEN_HEIGHT / 120;

    //Reset button information
    public static final int RESET_BUTTON_SIZE = SCREEN_HEIGHT / 50;


    //Upgrade buttons scaling

    public static final int UPGRADE_FRAME_INFO_OFFSET_X = SCREEN_HEIGHT / 60;
    public static final int UPGRADE_FRAME_INFO_OFFSET_Y = SCREEN_HEIGHT / 60;
    public static final int UPGRADE_SIZE = (int) (FRAME_WIDTH / 15);
    public static final double UPGRADE_OFFSET = UPGRADE_SIZE * 0.1;
    public static final double UPGRADE_BORDER_WIDTH = 0.05 * UPGRADE_SIZE;
    public static final int UPGRADE_ICON_SIZE = (int) (0.71 * UPGRADE_SIZE);
    public static final int UPGRADE_TYPE_ICON_SIZE = UPGRADE_SIZE / 7;


    //Upgrade Description
    public static final int UPG_DESC_FONT_SIZE = SCREEN_HEIGHT / 75;
    public static final int UPG_DESC_TITLE_FONT_SIZE = SCREEN_HEIGHT / 60;
    public static final int UPG_DESC_Y = (int) (0.75 * FRAME_HEIGHT + FRAME_Y);
    public static final int UPG_DESC_HEIGHT = FRAME_Y + FRAME_HEIGHT - UPG_DESC_Y;
    public static final int UPG_DESC_LINE_SPACING = FRAME_HEIGHT / 168;

    //Generator
    public static final int GENERATOR_FRAME_WIDTH = (int) (0.45 * FRAME_WIDTH);
    public static final int GENERATOR_FRAME_HEIGHT = (int) (0.1 * FRAME_HEIGHT);
    public static final int GENERATOR_FRAME_X_OFFSET = (int) ((FRAME_WIDTH - 2 * GENERATOR_FRAME_WIDTH) / 3);
    public static final int GENERATOR_FRAME_Y_OFFSET = (int) (0.05 * FRAME_HEIGHT);
    public static final int GENERATOR_FRAME_LINE_SPACING = FRAME_HEIGHT / 168;
    public static final int GENERATOR_FRAME_ROUND_CORNER_OFFSET = (int) (0.2 * GENERATOR_FRAME_HEIGHT);


    public static final int GENERATOR_ICON_HEIGHT = (int) (0.4 * GENERATOR_FRAME_HEIGHT);
    public static final int GENERATOR_ICON_WIDTH = GENERATOR_ICON_HEIGHT;
    public static final int GENERATOR_ICON_FRAME_SIZE = (int) (1.4 * GENERATOR_ICON_WIDTH);
    public static final int GENERATOR_ICON_ROUND_CORNER_OFFSET = (int) (0.1 * GENERATOR_ICON_FRAME_SIZE);

    public static final int GENERATOR_DESC_LEFT_OFFSET = (int) (0.07 * GENERATOR_FRAME_WIDTH);

    public static final int GENERATOR_BUTTON_X = (int) (0.525 * GENERATOR_FRAME_WIDTH);
    public static final int GENERATOR_BUTTON_WIDTH = (int) (0.35 * GENERATOR_FRAME_WIDTH);
    public static final int GENERATOR_BUTTON_HEIGHT = (int) (0.5 * GENERATOR_FRAME_HEIGHT);
    public static final int GENERATOR_BUTTON_ROUND_CORNER_OFFSET = (int) (0.1 * GENERATOR_BUTTON_WIDTH);

    public static final int BUY_BUTTON_FONT_SIZE = SCREEN_HEIGHT / 92;


    //Colour theme
    public static final Color BLACK = new Color(13, 31, 45);
    public static final Color MARIO_GREEN = new Color(44, 176, 26);
    public static final Color LIGHT_MARIO_GREEN = new Color(90, 220, 60);

    public static final Color NOT_WHITE = new Color(13, 31, 45);
    public static final Color CREAM_WHITE = new Color(255, 255, 200);
    public static final Color SUN_YELLOW = new Color(230, 241, 74);
    public static final Color DARK_SUN_YELLOW = new Color(204, 204, 30);

    public static final Color MORE_SUN_YELLOW = new Color(255, 255, 100);
    public static final Color DARKER_SUN_YELLOW = new Color(150, 150, 30);
    public static final Color BAGE_OR_SMTHNNN = new Color(239, 188, 155);
    public static final Color PINK_RED = new Color(229, 83, 129);
    public static final Color PLAIN_BLUE = new Color(37, 110, 255);
    public static final Color B2B_GRAY = new Color(178, 176, 155);
    public static final Color DARKISH_BROWN = new Color(107, 39, 55);
    public static final Color DARK_TURQUOISE = new Color(21, 112, 122);
    public static final Color GOOGLE_HIGHLIGHT = new Color(175, 203, 255);
    public static final Color DEPRESSED_GOOGLE_HIGHLIGHT = new Color(100, 120, 150);
    public static final Color DEPRESSEDER_GOOGLE_HIGHLIGHT = new Color(70, 90, 115);

    public static final Color GRAY = new Color(50, 60, 60);
    public static final Color LIGHT_GRAY = new Color(80, 80, 80);
    public static final Color LESS_DARK_GRAY = new Color(40, 40, 40);
    public static final Color DARK_GRAY = new Color(30, 30, 30);

    public static final Color TINTED_GOLD = new Color(155, 137, 49);

    //Fonts

    public static final Font CURRENCY_FONT = new Font("Century Gothic", Font.BOLD, Const.CURRENCY_TEXT_SIZE);
    public static final Font DESC_FONT = new Font("Century Gothic", Font.BOLD, Const.UPG_DESC_FONT_SIZE);
    public static final Font DESC_TITLE_FONT = new Font("Century Gothic", Font.BOLD, Const.UPG_DESC_TITLE_FONT_SIZE);
    public static final Font BUY_BUTTON_FONT = new Font("Century Gothic", Font.BOLD, Const.BUY_BUTTON_FONT_SIZE);


    public static final int MOUSE_Y_OFFSET = 30;
    public static final int MOUSE_X_OFFSET = 10;


    //-------------------------------------------------------Game Constants-----------------------------------------------------------------\\


    //BigNum rounding error
    public static final int ROUNDING_ERROR = 6;
    public static final int DISPLAYED_DIGITS = 3;


    //Generators
    public static final int GENERATOR_MULTIPLIER_INTERVAL = 10;
    public static final int GENERATOR_MULTIPLIER_VALUE = 2;
}
