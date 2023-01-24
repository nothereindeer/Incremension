package com.sfanshen.graphics;

import com.sfanshen.main.Const;
import com.sfanshen.currency.Currency;
import com.sfanshen.main.FinalGame;
import com.sfanshen.main.Global;
import com.sfanshen.ui.BoardAndMouse;
import com.sfanshen.ui.TabSwitchButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;


public class GameFrame {
    public JFrame frame;
    GraphicsPanel currentGraphicsPanel;

    BoardAndMouse.MouseListen mouseListener;
    BoardAndMouse.KeyListen keyListener;

    //-------------------------------------------------------Constructor-----------------------------------------------------------------\\
    public GameFrame() {

        ;

        frame = new JFrame("test");
        frame.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentGraphicsPanel = new GraphicsPanel();

        mouseListener = new BoardAndMouse.MouseListen();
        frame.addMouseListener(mouseListener);

        keyListener = new BoardAndMouse.KeyListen();
        frame.addKeyListener(keyListener);

        frame.add(currentGraphicsPanel);


        frame.setVisible(true);
        currentGraphicsPanel.setBackground(Const.NOT_WHITE);


        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    FinalGame.saveProgress();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //-------------------------------------------------------Graphics Rendering-----------------------------------------------------------------\\

    public class GraphicsPanel extends JPanel {
        public GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }

        public void paint(Graphics g) {
            super.paint(g);
            drawEverything(g);
        }
    }


    //-------------------------------------------------------Graphical Methods-----------------------------------------------------------------\\

    //Updates frame
    public void updateFrame() {
        frame.repaint();
        try {
            Thread.sleep(1000 / Const.FPS);
        } catch (Exception e) {
            System.out.println("Something went wrong!!!!");
        }
    }


    public void drawEverything(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        switch (Global.currentScreen) {
            case ("main menu"):
                drawMenu(g2);
                break;
            case ("main"):
                ;
                drawTabSelection(g2);
                drawFrame(g2);
                drawCurrencies(g2);
                drawTab(g2);
            case ("settings"):
        }
    }


    public void drawMenu(Graphics2D g) {
        Global.playButton.move(Const.PLAY_BUTTON_X, Const.PLAY_BUTTON_Y, true);
        Global.playButtonDark.move(Const.PLAY_BUTTON_X, Const.PLAY_BUTTON_Y, true);
        Global.playButtonDark.draw(g);
        Global.playButton.draw(g);


        Global.titleImage.move(Const.TITLE_IMAGE_X, Const.TITLE_IMAGE_Y, true);
        Global.titleImage.draw(g);
//        Global.loadingBar.draw(g);
    }


    public void drawFrame(Graphics2D g) {

        g.setColor(Const.DEPRESSED_GOOGLE_HIGHLIGHT);
        Stroke oldStroke = g.getStroke();

        g.setStroke(new BasicStroke(Const.FRAME_BORDER_THICKNESS));
        g.drawRect(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_WIDTH, Const.FRAME_HEIGHT);

        g.setStroke(oldStroke);
    }

    public void drawTabSelection(Graphics2D g) {

        g.setColor(Const.DEPRESSED_GOOGLE_HIGHLIGHT);
        Stroke oldStroke = g.getStroke();

        for (TabSwitchButton button : Global.tabSwitchButtons) {
            button.draw(g);
        }

        g.setStroke(oldStroke);
    }

    //Renders everything currency related - icons, amounts
    public void drawCurrencies(Graphics2D g) {

        int i = 0;

        for (Currency currency : Global.currencies.values()) {
            int xPosition = (i + 1) * Const.SCREEN_WIDTH / (Global.currencies.size() + 1);

            currency.icon.move(xPosition, Const.CURRENCY_OFFSET_FROM_TOP, false);
            currency.icon.draw(g);

            g.setColor(Const.SUN_YELLOW);
            int x = xPosition + currency.icon.width + Const.TEXT_ICON_OFFSET;
            int y = Const.CURRENCY_OFFSET_FROM_TOP + currency.icon.height / 2;
            drawYCenteredString(g, currency.amount.toSuffixVersion(), x, y, Const.CURRENCY_FONT);
            i = i + 1;
        }
    }


    //Renders everything upgrade related - currently visible upgrade buttons
    public void drawTab(Graphics2D g) {
        GameTab tab = Global.gameTabs.get(Global.currentTab);
        tab.draw(g);
    }


    public static void drawCenteredString(Graphics2D g, String text, int centerX, int centerY, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = centerX - metrics.stringWidth(text) / 2;
        int y = centerY - metrics.getHeight() / 2 + metrics.getAscent();

        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void drawXCenteredString(Graphics2D g, String text, int centerX, int topY, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = centerX - metrics.stringWidth(text) / 2;
        int y = topY + metrics.getAscent();

        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void drawYCenteredString(Graphics2D g, String text, int topX, int centerY, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = topX;
        int y = centerY - metrics.getHeight() / 2 + metrics.getAscent();

        g.setFont(font);
        g.drawString(text, x, y);
    }
}
