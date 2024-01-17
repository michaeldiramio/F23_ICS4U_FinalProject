import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class TugOfWar extends baseGame {

  //variables
  private DConsole dc;
  private Player player1;
  private Player player2;
  //private variables for the game 
  int centerX = 450;
  int centerY = 300;
  int playerAScore = 0;
  int playerLScore = 0;

  public TugOfWar(DConsole dc, Player p1, Player p2) {
    this.player1 = p1; 
    this.player2 = p2;
    this.dc = dc; 
  }

  public void initialize() {
    this.player1.setX(0);
    this.player2.setX(0);
    this.player1.setY(0);
    this.player2.setY(0);
    this.run(); 
  }
  public void run() {
    // variable for the game loop 
    boolean start = true;
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);

    while(start) {
      dc.clear();
      // draw the lines for each team 
      dc.setPaint(Color.BLUE);
      dc.fillRect(120, 300, 5, 700);
      dc.setPaint(Color.GREEN);
      dc.fillRect(780, 300, 5, 700);

      // draw the rope 
      dc.setPaint(Color.BLACK);
      dc.fillRect(centerX, centerY, 890, 5);
      dc.setPaint(Color.RED);
      dc.fillRect(centerX, centerY, 5, 30);

      // Display scores
      dc.setPaint(Color.BLACK);
      dc.drawString("Player A: " + playerAScore, 160, 20);
      dc.drawString("Player L: " + playerLScore, 740, 20);

      if (dc.isKeyPressed('A')) {
        centerX = centerX - 2;  // move to left
      }
       // Check for player input
      if (dc.isKeyPressed('L')) {
        centerX = centerX + 2; // move to right
      }

      // Check if the red thingy has passed any of the player's line
      if (centerX <= 110) {
        playerAScore++; 
        if (playerAScore == 1) {
          start = false; 
          centerX = 450;
          this.winner = 1; 
        }
      } else if (centerX >= 790) {
        playerLScore++;
        centerX = 250;
        if (playerLScore == 1) {
          start = false;
          centerX = 450;
          this.winner = 1; 
        }
      }

      // Draw updated state
      dc.redraw();
      dc.pause(20); // Adjust the speed of the game by changing the pause duration
    }
  }
}