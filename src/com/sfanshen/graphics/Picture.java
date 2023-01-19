package com.sfanshen.graphics;

import sun.awt.image.ToolkitImage;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;




public class  Picture{
  int x, y;
  public int  width, height;

  String imageDirectory;
  BufferedImage image;


  //-------------------------------------------------------Constructors-----------------------------------------------------------------\\

  public Picture(int x, int y, int width, int height, String imageDirectory){

    this.imageDirectory = imageDirectory;
    this.x = x;
    this.y = y;

    //Attempts to render image
    try{
      this.image = ImageIO.read(new File(imageDirectory));
    }
    catch(IOException ex){System.out.println("Image doesn't exist. " + imageDirectory);}

    this.width = width;
    this.height = height;
  }
  public Picture(int x, int y, String imageDirectory){

    this.imageDirectory = imageDirectory;
    this.x = x;
    this.y = y;

    //Attempts to render image
    try{
      this.image = ImageIO.read(new File(imageDirectory));
    }
    catch(IOException ex){System.out.println("Image doesn't exist. " + imageDirectory);}

    this.width = image.getWidth();
    this.height = image.getHeight();
  }
  public Picture(int x, int y, int width, int height, String imageDirectory, boolean centerAtCoords){

    //Attempts to render image
    try{
      this.image = ImageIO.read(new File(imageDirectory));
    }
    catch(IOException ex){System.out.println("Image doesn't exist. " + imageDirectory);}

    this.width = width;
    this.height = height;
    this.x = x - this.width / 2;
    this.y = y - this.width / 2;
  }



  //-------------------------------------------------------Methods-----------------------------------------------------------------\\

  //Draws image
  public void draw(Graphics g) {
    Image tmp = this.image.getScaledInstance(this.width, this.height, BufferedImage.SCALE_FAST);
    g.drawImage(tmp, this.x, this.y, null);
  }


  //Moves image, has option to be centered around given coordinates
  public void move(int x, int y, boolean centeredAtCoords) {
    if (centeredAtCoords){
      this.x = x - this.width / 2;
      this.y = y - this.height / 2;
    }
    else{
      this.x = x;
      this.y = y;
    }
  }


  //Creates a new Picture instance with same parameters, unless specified otherwise
  public Picture clone() {
    return new Picture(this.x, this.y, this.width, this.height, this.imageDirectory);
  }
  public Picture clone(int x, int y) {
    return new Picture(x, y, this.width, this.height, this.imageDirectory);
  }


  public void resize(int scaleX, int scaleY, boolean isScaled){
    if (isScaled) {
      this.width = this.width * scaleX;
      this.height = this.height * scaleY;
    }
    else {
      this.width = scaleX;
      this.height = scaleY;
    }
  }
}
