package com.sfanshen.graphics;

import java.awt.Graphics;
import java.util.ArrayList;


public class MainMenu{

  public ArrayList<Picture> images = new ArrayList<>();


  MainMenu(){
    images.add(new Picture(0, 0, 100, 50, "pictures/Title.png"));
    images.add(new Picture(0, 0, 100, 300, "pictures/loading.png"));
    images.add(new Picture(0, 0, 100, 50, "pictures/playbutton.png"));
  }




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
