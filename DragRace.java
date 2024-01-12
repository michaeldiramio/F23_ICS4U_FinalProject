import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class DragRace extends baseGame{

  DConsole dc;
  private Player p1;
  private Player p2;

 public DragRace(DConsole dc, Player p1, Player p2){
  System.out.println("DragRace added");
   this.p1 = p1;
   this.p2 = p2;
   this.dc = dc;
 }
  
  public void run() {
    while(true) {
      this.dc.clear();
      this.dc.fillRect(225, 150, 50, 50); //temp visual

      this.dc.redraw();
    }
  }

  public void initialize() {
    this.run();
  }
  
    
}