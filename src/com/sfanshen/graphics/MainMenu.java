package com.sfanshen.graphics;

import java.awt.Graphics;
import java.util.ArrayList;


public class MainMenu{
  ArrayList<Picture> images = new ArrayList<Picture>();
  
  images.add(new Picture(200, 100, "pictures/Title.png"))
    
  images.add(new Picture(200, 300, "pictures/loading.png"))
    
  images.add(new Picture(300, 450, "pictures/playbutton.png"))
    
  public void drawTitle(){
     
  }
  
  public void drawLoading(){
    
  }
  
  public void drawPlayButton(){
     
  }

  public void draw(Graphics g){
    drawTitle();
      
  }
  
}
