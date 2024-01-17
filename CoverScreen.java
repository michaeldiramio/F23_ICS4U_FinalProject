import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.awt.*;

public class CoverScreen extends baseGame {
// variablle
  DConsole dc;
  private Player player1;
  private Player player2;
  int player1count = 0;
  int player2count = 0;
  int keycount1 = 0;
  int keycount2 = 0;

  public CoverScreen(DConsole dc, Player p1, Player p2) {
    player1 = p1;
    player2 = p2;
    this.dc = dc;
  }

  public void initialize() {
    System.out.println("running");
    player1.setX(100);
    player1.setY(550);
    player2.setX(800);
    player2.setY(550);
    this.run();
  }

  public void run() {
    //setup stuff
    Font f = dc.getFont(); // the writing setup
    dc.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
    long startTime = System.currentTimeMillis();
    long elapsedTime = 0;

    while (elapsedTime - startTime < 10000) {
// draw the map
      dc.setPaint(new Color(73, 68, 67));
      dc.fillRect(450, 300, 900, 600);
      dc.fillEllipse(player1.getX(), player1.getY(), 50, 50);
      dc.fillEllipse(player2.getX(), player2.getY(), 50, 50);
      elapsedTime = System.currentTimeMillis();

      if (player1.upPressed()) {
        player1.changeY(5);
      }
      if (player1.downPressed()) {
          player1.changeY(-5);
        }
      if (player1.leftPressed()) {
        player1.changeX(-5);
      }
      if (player1.rightPressed()) {
        player1.changeX(-5);
      }
      }
    if (Y < 0) {
      changeY= 310; 
    }

    if (Y > 275) { //top and bottem walls
      changeY= -3; 
    }

    if (Y < 15) { 
      changeY= 3; 

    }

    if (X > 425) { // for wall and points
      changeY= -3; 
    }

    if (X < 15) { 
      changeY= 3; 
    }
    
    
    //winner conditions
   // if (player1count > player2count) {
  //    super.winner = 1;
   // }
    //if (player2count > player1count) {
     // super.winner = 2;
  //  }
  }

}