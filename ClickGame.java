import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.awt.*;

public class ClickGame extends baseGame {

  DConsole dc;
  private Player player1;
  private Player player2;
  int player1count = 0;
  int player2count = 0;
  int keycount1 =0;
  int keycount2 =0;
  
  public ClickGame(DConsole dc, Player p1, Player p2){
    player1 = p1;
    player2 = p2;
    this.dc = dc;
  }
  
  public void initialize (){
    System.out.println("running");
    this.run();
  }
  
  public void run(){
    Font f = dc.getFont(); //the writing setup
    dc.setFont(new Font("DejaVu Sans", Font.BOLD, 25));
    while(true){
    dc.setPaint(new Color(30, 144, 255));
    dc.fillRect(450, 300, 900, 600);

    dc.setPaint(new Color(73, 68, 67));
    dc.fillRect(450, 300, 300, 600);

    dc.setPaint(new Color(12, 11, 11));
    dc.fillRect(450, 550, 80, 90);

    dc.fillEllipse(275,300,50,50);
    dc.fillEllipse(675,300,50,50);
      dc.drawString("Player Two Click Count"+player2count, 675,300);
      dc.drawString("Player One Click Count "+player1count, 275,300);
      
      if(this.dc.isKeyPressed('1')&&keycount1==0) {
        player1count = player1count + 1;
        keycount1 = 1;
      }
      if(keycount1 ==1 && !this.dc.isKeyPressed('1')){
        keycount1 = 0;
      }
      if(this.dc.isKeyPressed('0')&&keycount2==0) {
        player2count = player2count + 1;
        keycount2 = 1;
      }
      if(keycount2 ==1 && !this.dc.isKeyPressed('0')){
        keycount2 = 0;
      }
      

      dc.redraw();
    }
    
  }
  
}