import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.awt.*;

public class pong extends baseGame {

  private DConsole dc;
  private Player p1;
  private Player p2;

  public pong (DConsole dc, Player p1, Player p2) {
    this.dc = dc;
    this.p1 = p1;
    this.p2 = p2;
  }

  public void initialize() {
    this.run();
  }

  public void run() {

    System.out.println("running pong");
    boolean playing = true;
    boolean firstTime = true;
    int ballX = 450;
    int ballXChange = 4;
    int ballY = 300;
    int ballYChange = 4;
    int paddleLY = 300;
    int paddleRY = 300;
    int winner = 0;

    // Setting the font of the text for the game
    dc.setFont(new Font("Dialog", Font.BOLD, 60));

    dc.clear();

    while (playing) {
       // -------- DRAW STUFF -----------
      dc.clear();
      dc.setPaint(Color.BLACK);
      dc.fillRect(450, 300, 894, 594);
      dc.setPaint(new Color(249, 252, 220));
      dc.fillEllipse(ballX, ballY, 40, 40);
      dc.fillRect(44, paddleLY, 20, 120);
      dc.fillRect(866, paddleRY, 20, 120);

      // -------- MOVE STUFF -----------
      ballX = ballX + ballXChange;
      ballY = ballY + ballYChange;

      // ------- CHECK STUFF -----------
      if (p1.upPressed()) {
        paddleLY = paddleLY - 10;
      }
      if (p1.downPressed()) {
        paddleLY = paddleLY + 10;
      } // Moving the left paddle up and down
      if (paddleLY <= 64) {
        paddleLY = paddleLY + 10;
      }
      if (paddleLY >= 534) {
        paddleLY = paddleLY - 10;
      } // Stopping the left paddle from going off the screen

      if (p2.upPressed()) {
        paddleRY = paddleRY - 10;
      }
      if (p2.downPressed()) {
        paddleRY = paddleRY + 10;
      } // Moving the right paddle up and down
      if (paddleRY <= 64) {
        paddleRY = paddleRY + 10;
      }
      if (paddleRY >= 534) {
        paddleRY = paddleRY - 10;
      } // Stopping the right paddle from going off the screen

      if (ballX >= 878) {
       ballXChange = -4;
     }
     if (ballX <= 24) {
        ballXChange = 4;
      }
      if (ballY >= 578) {
        ballYChange = -4;
     }
     if (ballY <= 24) {
       ballYChange = 4;
     } // Stopping the ball from going off screen

      if (ballX >= 856 &&
          ballY >= paddleRY - 60 &&
          ballY <= paddleRY + 60) {
        ballXChange = -4;
     } // Right paddle collision
     if (ballX <= 54 &&
          ballY >= paddleLY - 60 &&
          ballY <= paddleLY + 60) {
        ballXChange = 4;
      } // Left paddle collision

      if (ballX >= 876) {
        super.winner = 1;
        winner = 1;
        playing = false;
      }
      if (ballX <= 24) {
        super.winner = 2;
        winner = 2;
        playing = false;
      } // Winner tracker

      dc.redraw();
      dc.pause(10);
      if (firstTime) {
        dc.pause(2500);
        firstTime = false;
      }
    }

    // Printing the winner on the screen, before returning to the game board
    dc.clear();
    dc.setPaint(Color.BLACK);
    dc.fillRect(450, 300, 894, 594);
    dc.setPaint(new Color(249, 252, 220));
    if (winner == 1) {
      dc.drawString("PLAYER 1 WINS!", 450, 275);
    } else if (winner == 2) {
      dc.drawString("PLAYER 2 WINS!", 450, 275);
    }
    dc.redraw();
    dc.pause(2500);
   
  }
}