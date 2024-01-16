import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.awt.*;

public class pickKey extends baseGame {

  private DConsole dc;
  private Player p1;
  private Player p2;
  Random rnd = new Random();

  public pickKey(DConsole dc, Player p1, Player p2) {
    this.dc = dc;
    this.p1 = p1;
    this.p2 = p2;
  }

  public void initialize() {
    this.run();
  }

  public void run() {
    // Variables to track whether the game is being played, the randomly generated number that determines the direction the players must input, and the winner of the game
    boolean playing = true;
    int num = rnd.nextInt(4);
    int winner = 0;

    // Setting the font of the text for the game
    dc.setFont(new Font("Dialog", Font.BOLD, 60));

    // Game loop to track input from players
    while (playing) {
      
      // Clearing the DConsole and setting a background
      dc.clear();
      dc.setPaint(Color.BLUE);
      dc.fillRect(450, 300, 900, 600);
      dc.setPaint(Color.WHITE);
      
      switch (num) { // Print which key must be pressed in order to win the mini game (Up, Down, Left, Right) as well as checking to see what player presses the correct key first, and determines the winner afterwards
        case 0:
          dc.drawString("UP", 450, 275);
          dc.redraw();
          if (p1.upPressed()) {
            super.winner = 1;
            winner = 1;
            playing = false;
          } else if (p2.upPressed()) {
            super.winner = 2;
            winner = 2;
            playing = false;
          }
          break;
        case 1:
          dc.drawString("RIGHT", 450, 275);
          dc.redraw();
          if (p1.rightPressed()) {
            super.winner = 1;
            winner = 1;
            playing = false;
          } else if (p2.rightPressed()) {
            super.winner = 2;
            winner = 2;
            playing = false;
          }
          break;
        case 2:
          dc.drawString("DOWN", 450, 275);
          dc.redraw();
          if (p1.downPressed()) {
            super.winner = 1;
            winner = 1;
            playing = false;
          } else if (p2.downPressed()) {
            super.winner = 2;
            winner = 2;
            playing = false;
          }
          break;
        case 3:
          dc.drawString("LEFT", 450, 275);
          dc.redraw();
          if (p1.leftPressed()) {
            super.winner = 1;
            winner = 1;
            playing = false;
          } else if (p2.leftPressed()) {
            super.winner = 2;
            winner = 2;
            playing = false;
          }
          break;
      }
    }

    // Clearing the DConsole and setting a background
    dc.clear();
    dc.setPaint(Color.BLUE);
    dc.fillRect(450, 300, 900, 600);
    dc.setPaint(Color.WHITE);

    // Checking which player is the winner and printing the results on the screen, before briefly waiting, then returning to main screen
    if (winner == 1) {
      dc.drawString("PLAYER 1 WINS!", 450, 275);
    } else if (winner == 2) {
      dc.drawString("PLAYER 2 WINS!", 450, 275);
    }
    dc.redraw();
    dc.pause(2500);
  }
}