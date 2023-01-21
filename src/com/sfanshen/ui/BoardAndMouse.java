package com.sfanshen.ui;

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
            int mouseX = e.getX();
            int mouseY = e.getY() - 40;


            switch (Global.currentScreen) {
                case ("main menu"):
                    clickMainMenu(mouseX, mouseY);

                case ("main"):
                    clickMainGame(mouseX, mouseY);
            }
        }


        public void clickMainMenu(int mouseX, int mouseY) {
            if (Global.playButton.isMouseInPicture(mouseX, mouseY)) {
                Global.currentScreen = "main";
            }
        }

        public void clickMainGame(int mouseX, int mouseY) {
            if (isMouseInFrame(mouseX, mouseY, Const.FRAME_X + Const.TAB_SELECTION_OFFSET, Const.FRAME_Y + Const.FRAME_HEIGHT, (Const.FRAME_WIDTH - 2 * Const.TAB_SELECTION_OFFSET) / Global.gameTabs.size(), Const.TAB_SELECTION_HEIGHT)) {
                TabSwitchButton clickedButton = Global.tabSwitchButtons.get(mouseX / (Const.FRAME_WIDTH - 2 * Const.TAB_SELECTION_OFFSET) / Global.gameTabs.size());
                Global.currentTab = clickedButton.switchedTab;
            }
            if (isMouseInFrame(mouseX, mouseY, Const.FRAME_X, Const.FRAME_Y, Const.FRAME_WIDTH, Const.FRAME_HEIGHT)) {
                GameTab currentTab = Global.gameTabs.get(Global.currentTab);
                if (currentTab instanceof UpgradeTab) {
                    clickUpgradeTab(mouseX, mouseY, currentTab);
                }
            }
        }

        public void clickUpgradeTab(int mouseX, int mouseY, GameTab tab) {
            UpgradeTab currentTab = (UpgradeTab) tab;
            for (UpgradesFrame upgradesFrame : currentTab.upgradesFrames) {
                for (Upgrade upgrade : upgradesFrame.upgrades) {
                    UpgradeButton upgBut = upgrade.upgradeButton;
                    if (isMouseInFrame(mouseX, mouseY, upgBut.x, upgBut.y, upgBut.width, upgBut.height)) {
                        upgrade.buy();
                    }
                }
            }
        }


        public boolean isMouseInFrame(int x, int y, int frameX, int frameY, int frameWidth, int frameHeight) {
            if (x > frameX && x < frameX + frameWidth) {
                if (y > frameY && y < frameY + frameHeight) {
                    return true;
                }
            }
            return (x > frameX && x < frameX + frameWidth && y > frameY && y < frameY + frameHeight);
        }

        public void mousePressed(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }


    }

    public static class MouseMotionListen implements MouseMotionListener {
        public void mouseMoved(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();
        }

        public void mouseDragged(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();
        }
    }

}