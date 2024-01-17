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
    dc.setPaint(new Color(73, 68, 67));
    dc.fillRect(450, 300, 900, 600);

    while (elapsedTime - startTime < 10000) {
// draw the map
      dc.setPaint(new Color(255, 0, 0));
      dc.fillEllipse(player1.getX(), player1.getY(), 50, 50);
      dc.setPaint(new Color(0, 255, 0));
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
      //player 2
      if (player2.upPressed()) {
        player2.changeY(5);
      }
      if (player2.downPressed()) {
          player2.changeY(-5);
        }
      if (player2.leftPressed()) {
        player2.changeX(-5);
      }
      if (player2.rightPressed()) {
        player2.changeX(-5);
      }
 //player 1
    if ( player1.getY() > 575) { //top and bottem walls
       player1.changeY(-3); 
    }

    if ( player1.getY() < 15) { 
       player1.changeY(3); 

    }

    if ( player1.getX() > 925) { // for wall and points
       player1.changeY(-3); 
    }

    if ( player1.getX() < 15) { 
       player1.changeY(3); 
    }
 // player 2
      if ( player2.getY() > 575) { //top and bottem walls
         player2.changeY(-3); 
      }

      if ( player2.getY() < 15) { 
         player2.changeY(3); 

      }

      if ( player2.getX() > 925) { // for wall and points
         player2.changeY(-3); 
      }

      if ( player2.getX() < 15) { 
         player2.changeY(3); 
      }
    
    dc.redraw();
    //winner conditions
   // if (player1count > player2count) {
  //    super.winner = 1;
   // }
    //if (player2count > player1count) {
     // super.winner = 2;
  //  }
  }
  }
}