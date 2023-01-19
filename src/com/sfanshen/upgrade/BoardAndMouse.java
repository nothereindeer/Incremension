package com.sfanshen.ui;

import com.sfanshen.main.Const; 
import com.sfanshen.graphics.GameTab;
import com.sfanshen.upgrade.Upgrade;
import com.sfanshen.upgrade.UpgradesFrame;
import com.sfanshen.graphics.UpgradeTab;
import com.sfanshen.main.Global;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.FeatureUpgrade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardAndMouse{
  
  KeyListen kListener;
  MouseListen mListener; 
  MouseMotionListener mmListener; 
  
   public class KeyListen implements KeyListener{   
        public void keyPressed(KeyEvent e){
           int key = e.getKeyCode();    
        } 
        
        public void keyReleased(KeyEvent e){
           int key = e.getKeyCode(); 
        }
        
        public void keyTyped(KeyEvent e){
            char keyChar = e.getKeyChar();
                         
        }           
    }
   
   public class MouseListen implements MouseListener{
     public void mouseClicked(MouseEvent e){
       int mouseX = e.getX();
       int mouseY = e.getY();
       MouseListen mListen = new MouseListen(); 
       if (mouseX > Const.FRAME_X && mouseX < Const.FRAME_X + Const.FRAME_WIDTH && mouseY > Const.FRAME_Y && mouseY < Const.FRAME_Y + Const.FRAME_HEIGHT){
         for (GameTab tab: Global.gameTabs.values()){
           if (tab.name.equals(Global.currentUpgradeFrame)){
             for(UpgradesFrame upgradeFrame: ((UpgradeTab)tab).upgradesFrames){
               for(Upgrade upgrade: upgradeFrame.upgrades){
                 checkClickCoords(mouseX, mouseY, upgrade);
               }
             }
           }
         } 
       }
       
     }
   
      
     public void checkClickCoords(int mX, int mY, Upgrade upgrade){
       if (mX > upgrade.upgradeButton.x && mX < upgrade.upgradeButton.x + upgrade.upgradeButton.width){
         if (mY > upgrade.upgradeButton.y && mY < upgrade.upgradeButton.y + upgrade.upgradeButton.height){
           upgrade.buy();           
         }
       } 
     }
   
        public void mousePressed(MouseEvent e){   
            int mouseX = e.getX();
            int mouseY = e.getY();
        }
        public void mouseReleased(MouseEvent e){ 
            System.out.println("A");
        }
        public void mouseEntered(MouseEvent e){
            System.out.println("Mouse entered at X:"+e.getX() + " Y:"+e.getY());
        }
        public void mouseExited(MouseEvent e){
            System.out.println("Mouse exited at X:"+e.getX() + " Y:"+e.getY());
        }
        
        
    }
   
   public class MouseMotionListen implements MouseMotionListener{
        public void mouseMoved(MouseEvent e){
          int mouseX = e.getX();
          int mouseY = e.getY(); 
        }
        public void mouseDragged(MouseEvent e){ 
          int mouseX = e.getX();
          int mouseY = e.getY();      
        }         
    }
  
}