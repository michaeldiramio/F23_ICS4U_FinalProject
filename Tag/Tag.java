import DLibX.*;
import java.awt.geom.AffineTransform; // need this for rotation
import java.awt.*; // need this to change stroke size or gradient
import java.awt.Color;

public class Tag extends baseGame {

  //variables
  private DConsole dc;
  private Player player1;
  private Player player2;
  int centerX = 450;
  int centerY = 300;
  int player1Score = 0;
  int player2Score = 0;
  int player1Y = 300; 
  int player1X = 150;
  int player2Y = 300;
  int player2X = 750;
  int ballwidth = 50; 
  // timer 
  long startTime = System.currentTimeMillis();
  long elapsedTime;

  public Tag(DConsole dc, Player p1, Player p2) {
    this.player1 = p1; 
    this.player2 = p2;
    this.dc = dc; 
  }

  public void initialize() {
    this.player1.setX(0);
    this.player2.setX(0);
    this.player1.setY(0);
    this.player2.setY(0);
    player1Score = 0;
    player2Score = 0;
    this.run(); 
  }
  public void run() {
    boolean isTagged = false; 
    // timer 
    long startTime = System.currentTimeMillis();
    long elapsedTime;
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);// Dc console 
    while (!isTagged) {
      this.dc.clear();
      // DRAWING CODE GOES HERE 
      this.dc.setPaint(Color.BLUE);
      this.dc.fillEllipse(player1X, player1Y, ballwidth, ballwidth);
      this.dc.drawString("Player 1 - TAGGER", 150, 20);
      // player 2 
      this.dc.setPaint(Color.RED);
      this.dc.fillEllipse(player2X , player2Y, ballwidth, ballwidth);
      this.dc.drawString("Player 2 - TAG", 750, 20);


      // ANIMATION CODE GOES HERE
      // player one comends 
      if (dc.isKeyPressed('w')) {
        player1Y = player1Y - 4;  // move to left
      } else if (this.dc.isKeyPressed('s')) {
        player1Y = player1Y + 4; // move to right
      } else if (this.dc.isKeyPressed('d')) {
        player1X = player1X + 4; // move to right)
      } else if (this.dc.isKeyPressed('a')) {
        player1X = player1X - 4; // move to right)
      }
      if (player1X == 50) {
        player1X = player1X + 5;
      } else if (player1X == 850) {
          player1X = player1X - 5;
        } 

      // player 2 comends 
      if (this.dc.isKeyPressed(38)) {
        player2Y = player2Y - 4; // move to right
      } else if (dc.isKeyPressed(40)) {
        player2Y = player2Y + 4; // move to right
      } else if (dc.isKeyPressed(39)) {
        player2X = player2X + 4; // move to right
      } else if (dc.isKeyPressed(37)) {
        player2X = player2X - 4; // move to right
      }

      // checking bondaries 
      // X intersection 
      if (player1X <= 25) {
        player1X = 25;
      } else if (player1X >= 875) {
        player1X = 875;
      }
      // player 2 
      if (player2X <= 25) {
        player2X = 25;
      } else if (player2X >= 875) {
        player2X = 875;
      }
      // y intersections 
      if (player1Y <= 25) {
        player1Y = 25;
      } else if (player1Y>= 575) {
        player1Y = 575;
      }
      // player 2 
      if (player2X <= 25) {
        player2Y = 25;
      } else if (player2Y>= 575) {
        player2Y = 575;
      }

      // checking if player 1 won 
      double distance = Math.sqrt(Math.pow(player1X - player2X, 2) + Math.pow(player1Y - player2Y, 2));
      double radiusSum = ballwidth; // assuming both ellipses have the same radius

      if (distance < radiusSum) {
        isTagged = true;
        this.winner = 1; 
      }

      // Draw updated state
      this.dc.redraw();
      this.dc.pause(20); // Adjust the speed of the game by changing the pause duration

      elapsedTime = System.currentTimeMillis() - startTime;
      if (elapsedTime >= 10000) { // 10000 milliseconds (10 seconds) as an example
        this.winner = 2; 
        isTagged = true; // set isTagged to true after 5 seconds
      } 
    }
  }
}
