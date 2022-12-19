import javax.swing.*;
import java.awt.*;


public class GameFrame{
  JFrame frame;  
  GraphicsPanel currentGraphicsPanel;
  

  
  
  Font currencyFont = new Font("Baskerville", Font.BOLD, Const.CURRENCY_TEXT_SIZE);
  
  GameFrame(){
    frame = new JFrame("test");
    frame.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    currentGraphicsPanel = new GraphicsPanel();
    
    frame.add(currentGraphicsPanel);
    frame.setVisible(true);    
  }
  
  void updateFrame(){
    frame.repaint();
    try {Thread.sleep(5);} catch(Exception e){}
  }
  
  public class GraphicsPanel extends JPanel{
    
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      int i = 0;
      for (Currency currency : Const.CURRENCIES.values()){
        int fontXPosition = (i + 1) * Const.SCREEN_WIDTH / (Const.CURRENCIES.size() + 1);
        g.setFont(currencyFont);
        g.drawString(Integer.toString(currency.amount), fontXPosition, 10);
        i = i + 1;
      }
    }
  }  
}