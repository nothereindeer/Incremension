package com.sfanshen.graphics;

import com.sfanshen.main.Const;
import com.sfanshen.currency.Currency;
import com.sfanshen.main.Global;
import com.sfanshen.ui.BoardAndMouse;
import com.sfanshen.ui.GameButton;
import com.sfanshen.ui.TabSwitchButton;

import javax.swing.*;
import java.awt.*;


public class GameFrame {
    JFrame frame;
    GraphicsPanel currentGraphicsPanel;

    BoardAndMouse.MouseListen mouseListener;
    BoardAndMouse.MouseMotionListen mouseMotionListener;
    BoardAndMouse.KeyListen keyListener;

    //-------------------------------------------------------Constructor-----------------------------------------------------------------\\
    public GameFrame() {
        frame = new JFrame("test");
        frame.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentGraphicsPanel = new GraphicsPanel();

        mouseListener = new BoardAndMouse.MouseListen();
        frame.addMouseListener(mouseListener);
        mouseMotionListener = new BoardAndMouse.MouseMotionListen();
        frame.addMouseMotionListener(mouseMotionListener);
        keyListener = new BoardAndMouse.KeyListen();
        frame.addKeyListener(keyListener);

        frame.add(currentGraphicsPanel);
        frame.setVisible(true);
        currentGraphicsPanel.setBackground(Const.NOT_WHITE);
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
            Thread.sleep(5);
        } catch (Exception e) {
            System.out.println("Something went wrong!!!!");
        }
    }


    public void drawEverything(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        GameIcon gameIcon = new GameIcon(100, 100, 500, 500);
        switch (Global.currentScreen) {
            case ("main menu"):
                drawMenu(g2);
                break;
            case ("main"):
                drawCurrencies(g2);
                drawTab(g2);
                drawFrame(g2);

            case ("settings"):
        }
    }

    public void drawFrame(Graphics2D g) {
        g.setColor(Const.DEPRESSED_GOOGLE_HIGHLIGHT);
        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke(Const.FRAME_BORDER_THICKNESS));
        g.drawRect(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_WIDTH, Const.FRAME_HEIGHT);

        int y = Const.FRAME_Y + Const.FRAME_HEIGHT;
        int height = Const.TAB_SELECTION_HEIGHT;

        int i = 0;
        for (GameTab gameTab : Global.gameTabs.values()) {
            int width = (Const.FRAME_WIDTH - 2 * Const.TAB_SELECTION_OFFSET) / Global.gameTabs.size();
            int x = Const.FRAME_X + Const.TAB_SELECTION_OFFSET + (i * width);
            g.drawRect(x, y, width, height);

            //write text
            g.setFont(Const.CURRENCY_FONT);
            int fontWidth = g.getFontMetrics(Const.CURRENCY_FONT).stringWidth(gameTab.name);
            int x2 = x + width / 2 - fontWidth / 2;
            int y2 = y + height / 2 + Const.CURRENCY_TEXT_SIZE / 2 - 3;
            g.drawString(Global.capitalizeFirstLetters(gameTab.name), x2, y2);


            TabSwitchButton tabSelection = new TabSwitchButton(x, y, width, height, gameTab.name);
            Global.tabSwitchButtons.add(tabSelection);
            i = i + 1;
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
            g.setFont(Const.CURRENCY_FONT);
            g.setColor(Const.SUN_YELLOW);
            g.drawString(currency.amount.bigNum, xPosition + currency.icon.width + Const.TEXT_ICON_OFFSET, Const.CURRENCY_OFFSET_FROM_TOP + currency.icon.height / 2 + Const.CURRENCY_TEXT_SIZE / 2);
            i = i + 1;
        }
    }


    //Renders everything upgrade related - currently visible upgrade buttons
    public void drawTab(Graphics2D g) {
        for (GameTab tab : Global.gameTabs.values()) {
            if (tab.name.equals(Global.currentTab)) {
                tab.draw(g);
            }
        }
    }

    public void drawMenu(Graphics2D g) {


        Global.playButton.move(800, 600, true);
        Global.playButton.draw(g);
        Global.titleImage.draw(g);
        Global.loadingBar.draw(g);
    }
}
