import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class ClickGame extends baseGame {

  DConsole dc;
  private Player player1;
  private Player player2;
  int player1count = 0;
  int player2count = 0;
  
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
    dc.setPaint(new Color(30, 144, 255));
    dc.fillRect(450, 300, 900, 600);

    dc.setPaint(new Color(73, 68, 67));
    dc.fillRect(450, 300, 300, 600);

    dc.setPaint(new Color(12, 11, 11));
    dc.fillRect(450, 550, 80, 90);

    dc.fillEllipse(275,300,50,50);
    dc.fillEllipse(675,300,50,50);
    
    dc.redraw();

    while(true){


  
    }
    
  }
  
}