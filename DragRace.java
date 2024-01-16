import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.Scanner;
import java.util.Random;

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

      //Max and Min Car Speeds
      int min = 10;
      int max = 15;
      
      Random randomNum = new Random();

      //Grass
      dc.setPaint(new Color(13, 122, 5));
      this.dc.fillRect(450, 300, 900, 600);

      //Pavements
      dc.setPaint(new Color(91, 92, 91));
      this.dc.fillRect(450, 200, 900, 40);

      //Pavements
      dc.setPaint(new Color(91, 92, 91));
      this.dc.fillRect(450, 400, 900, 40);

      //Finish Line
      dc.setPaint(new Color(131, 1, 1));
      this.dc.fillRect(850, 300, 30, 250);

      //Red Car
      dc.setPaint(new Color(255, 0, 0));
      this.dc.fillRect(p1.getX(), 200, 30, 20);

      //Green Car
      dc.setPaint(new Color(0, 255, 50));
      this.dc.fillRect(p2.getX(), 400, 30, 20);

      //Player 1 Movement
      if(p1.upPressed()) {
        p1.setX(p1.getX() + randomNum.nextInt(3) + 1);
      }

      //Player 2 Movement
      if(p2.downPressed()) {
        p2.setX(p2.getX() + randomNum.nextInt(3) + 1);
      }

      //Winner
      if (p1.getX() == 835){
        super.winner = 1;
        break;
      } else if (p2.getX() == 835){
        super.winner = 2;
        break;
      }


      this.dc.redraw();
      this.dc.pause(20);
    }
  }

  public void initialize() {
    
    
    p1.setX(30);
    p2.setX(30);
    this.run();
  }
  
    
}