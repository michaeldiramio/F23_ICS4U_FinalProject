import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class ClickGame extends baseGame {

  DConsole dc;
  private Player player1;
  private Player player2;
  
  public ClickGame(DConsole dc, Player p1, Player p2){
    player1 = p1;
    player2 = p2;
    this.dc = dc;
  }
  
  public void initialize (){
    
  }
  
  public void run(){
    dc.drawEllipse(100,300,50,50);
    dc.drawEllipse(300,300,50,50);
    dc.redraw();
  }
  
}