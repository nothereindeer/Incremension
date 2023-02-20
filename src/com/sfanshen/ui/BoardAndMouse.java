package com.sfanshen.ui;

import com.sfanshen.currency.Currency;
import com.sfanshen.currency.ResetCurrency;
import com.sfanshen.generator.Generator;
import com.sfanshen.graphics.GeneratorTab;
import com.sfanshen.main.Const;
import com.sfanshen.graphics.GameTab;
import com.sfanshen.upgrade.*;
import com.sfanshen.graphics.UpgradeTab;
import com.sfanshen.main.Global;

import java.awt.event.*;

public class BoardAndMouse {

    KeyListen kListener;
    MouseListen mListener;
    MouseMotionListener mmListener;

    public static class KeyListen implements KeyListener {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
        }

        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
        }

        public void keyTyped(KeyEvent e) {
            char keyChar = e.getKeyChar();
        }
    }

    public static class MouseListen implements MouseListener {
        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
            switch (Global.currentScreen) {
                case ("main menu"):
                    clickMainMenu();

                case ("main"):
                    clickMainGame();
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }


        public void clickMainMenu() {
            if (Global.playButton.isMouseInPicture()) {
                Global.currentScreen = "main";
            }
        }

        public void clickMainGame() {
            if (isMouseInFrame(Const.FRAME_X + Const.TAB_SELECTION_OFFSET, Const.FRAME_Y + Const.FRAME_HEIGHT, (Const.FRAME_WIDTH - 2 * Const.TAB_SELECTION_OFFSET), Const.TAB_SELECTION_HEIGHT)) {
                TabSwitchButton clickedButton = Global.tabSwitchButtons.get((int) (Global.mouseX - Const.FRAME_X - Const.TAB_SELECTION_OFFSET) / ((Const.FRAME_WIDTH - 2 * Const.TAB_SELECTION_OFFSET) / Global.gameTabs.size()));
                clickedButton.click();
            }
            if (isMouseInFrame(Const.RESET_BUTTON_X, Const.RESET_BUTTON_Y, Const.SCREEN_WIDTH - Const.RESET_BUTTON_X, 3 * (Const.RESET_BUTTON_SIZE + Const.RESET_BUTTON_OFFSET))) {
                for (Currency currency : Global.currencies.values()){
                    if (currency instanceof ResetCurrency){
                        ResetCurrency resetCurrency = (ResetCurrency) currency;
                        if (resetCurrency.resetButton.isMouseInFrame())
                            resetCurrency.resetButton.click();
                    }
                }
            }
            if (isMouseInFrame(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_WIDTH, Const.FRAME_HEIGHT)) {
                GameTab currentTab = Global.gameTabs.get(Global.currentTab);
                if (currentTab instanceof UpgradeTab) {
                    clickUpgradeTab(currentTab);
                } else if (currentTab instanceof GeneratorTab) {
                    clickGeneratorTab(currentTab);
                }
            }
        }

        public void clickUpgradeTab(GameTab tab) {
            UpgradeTab upgradeTab = (UpgradeTab) tab;
            for (UpgradesFrame upgradesFrame : upgradeTab.upgradesFrames) {
                for (Upgrade upgrade : upgradesFrame.upgrades) {
                    UpgradeButton upgBut = upgrade.upgradeButton;
                    if (upgBut.isMouseInFrame()) {
                        upgBut.click();
                    }
                }
            }
        }

        public void clickGeneratorTab(GameTab tab) {
            GeneratorTab generatorTab = (GeneratorTab) tab;
            for (Generator gen : generatorTab.generators) {
                GeneratorButton genBut = gen.generatorFrame.button;
                if (genBut.isMouseInFrame()) {
                    genBut.click();
                }
            }
        }


    }

    public static void checkMousePosition() {
        switch (Global.currentScreen) {
            case "main menu":
                mouseHoveringTitleScreen();
            case "main":
                mouseHoveringMainScreen();
            case "settings":
        }

    }

    public static void mouseHoveringTitleScreen() {
        Global.playButtonDark.isVisible = Global.playButton.isMouseInPicture();
        Global.playButton.isVisible = !Global.playButton.isMouseInPicture();
    }

    public static void mouseHoveringMainScreen() {
        if (isMouseInFrame(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_WIDTH, Const.FRAME_HEIGHT)) {
            GameTab currentTab = Global.gameTabs.get(Global.currentTab);
            if (currentTab instanceof UpgradeTab) {
                mouseHoveringUpgradeTab(currentTab);
            } else if (currentTab instanceof GeneratorTab)
                mouseHoveringGeneratorTab(currentTab);
        }

        for (TabSwitchButton button : Global.tabSwitchButtons) {
            button.isMouseHovering = button.isMouseInFrame();
        }
        for (Currency currency : Global.currencies.values()){
            if (currency instanceof ResetCurrency){
                ResetButton button = ((ResetCurrency)currency).resetButton;
                button.isMouseHovering = button.isMouseInFrame();
            }
        }
    }

    public static void mouseHoveringUpgradeTab(GameTab currentTab) {
        UpgradeTab upgradeTab = (UpgradeTab) currentTab;

        for (UpgradesFrame upgradesFrame : upgradeTab.upgradesFrames) {
            for (Upgrade upgrade : upgradesFrame.upgrades) {
                UpgradeButton upgBut = upgrade.upgradeButton;
                upgBut.isMouseHovering = upgBut.isMouseInFrame();
            }
        }
    }

    public static void mouseHoveringGeneratorTab(GameTab currentTab) {
        GeneratorTab generatorTab = (GeneratorTab) currentTab;

        for (Generator generator : generatorTab.generators) {
            GeneratorButton genBut = generator.generatorFrame.button;
            genBut.isMouseHovering = genBut.isMouseInFrame();
        }
    }


    public static boolean isMouseInFrame(int frameX, int frameY, int frameWidth, int frameHeight) {
        return (Global.mouseX > frameX && Global.mouseX < frameX + frameWidth && Global.mouseY > frameY && Global.mouseY < frameY + frameHeight);
    }
}