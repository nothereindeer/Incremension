
import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;




public class Image{
  
  int x, y;
  int width, height;
  BufferedImage image;
  
  int scale;
  
  Image(int x, int y, String imageDirectory){

    try{
      this.image = ImageIO.read(new File(imageDirectory));
    }
    catch(IOException ex){System.out.println("Image doesn't exist.");}
    
    this.width = this.image.getWidth();
    this.height = this.image.getHeight();
    this.x = x;
    this.y = y;
  }
  
  Image(int x, int y, String imageDirectory, boolean centerAtCoords){
        
    try{
      this.image = ImageIO.read(new File(imageDirectory));
    }
    catch(IOException ex){System.out.println("Image doesn't exist.");}
    
    this.width = this.image.getWidth();
    this.height = this.image.getHeight();
    this.x = x - this.width / 2;
    this.y = y - this.width / 2;
        
  }
}