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
    playerAScore = 0;
    playerLScore = 0;
    this.run(); 
  }
  public void run() {
    // variable for the game loop 
    boolean start = true;
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);

    while(start) {
      this.dc.clear();
      // draw the lines for each team 
      this.dc.setPaint(Color.BLUE);
      this.dc.fillRect(120, 300, 5, 700);
      this.dc.setPaint(Color.GREEN);
      this.dc.fillRect(780, 300, 5, 700);

      // draw the rope 
      this.dc.setPaint(Color.BLACK);
      this.dc.fillRect(centerX, centerY, 890, 5);
      this.dc.setPaint(Color.RED);
      this.dc.fillRect(centerX, centerY, 5, 30);

      // Display scores
      this.dc.setPaint(Color.BLACK);
      this.dc.drawString("Player A: " + playerAScore, 160, 20);
      this.dc.drawString("Player L: " + playerLScore, 740, 20);

      if (this.dc.isKeyPressed('A')) {
        centerX = centerX - 2;  // move to left
      }
       // Check for player input
      if (this.dc.isKeyPressed('L')) {
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
          this.winner = 2; 
        }
      }

      // Draw updated state
      this.dc.redraw();
      this.dc.pause(20); // Adjust the speed of the game by changing the pause duration
    }
  }
}