
import java.util.ArrayList;

class UpgradesFrame{
  
  String displayScreen;
  int x, y, width, height;
  ArrayList<Upgrade> upgrades;
  
  UpgradesFrame(String displayScreen, int x, int y, int width, int height, ArrayList<Upgrade> upgrades){
    this.displayScreen = displayScreen;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.upgrades = upgrades;
  }
  
//  void sort(String sortOrder){
//    
//  }
//  
//  void draw(Graphics g){
//    
//  }
}