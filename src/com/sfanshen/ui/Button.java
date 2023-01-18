import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button{
  
  int x, y, width, height; 
    
  String action;   
    
  boolean isHovering; 
  
  Button(int x, int y, int w, int h, String action){
    this.x = x;
    this.y = y;
    this.width = w; 
    this.height = h; 
    this.action = action; 
    this.isHovering = false; 
  }

  
  
 
}