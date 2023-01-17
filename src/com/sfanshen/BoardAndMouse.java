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