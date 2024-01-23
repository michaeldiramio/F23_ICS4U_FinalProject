import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.awt.*;

public class ClickGame extends baseGame {
// variablle
  DConsole dc;
  private Player player1;
  private Player player2;
  int player1count = 0;
  int player2count = 0;
  int keycount1 = 0;
  int keycount2 = 0;

  public ClickGame(DConsole dc, Player p1, Player p2) {
    player1 = p1;
    player2 = p2;
    this.dc = dc;
  }

  public void initialize() {
    player1count = 0;
    player2count = 0;
    System.out.println("running");
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
      dc.setPaint(new Color(30, 144, 255));
      dc.fillRect(450, 300, 900, 600);

      dc.setPaint(new Color(73, 68, 67));
      dc.fillRect(450, 300, 300, 600);

      dc.setPaint(new Color(12, 11, 11));
      dc.fillRect(450, 550, 80, 90);

      dc.fillEllipse(225, 360, 50, 50);
      dc.fillEllipse(675, 360, 50, 50);
      dc.drawString("Player Two Click Count" + player2count, 675, 300);
      dc.drawString("Player One Click Count " + player1count, 225, 300);
      dc.drawString("who ever can click the most wins player 1 click 1 player 2 click 0", 450, 100);
      // clcik points for each player
      if (this.dc.isKeyPressed('1') && keycount1 == 0) {
        player1count = player1count + 1;
        keycount1 = 1;
      }
      if (keycount1 == 1 && !this.dc.isKeyPressed('1')) {
        keycount1 = 0;
      }
      if (this.dc.isKeyPressed('0') && keycount2 == 0) {
        player2count = player2count + 1;
        keycount2 = 1;
      }
      if (keycount2 == 1 && !this.dc.isKeyPressed('0')) {
        keycount2 = 0;
      }
      elapsedTime = System.currentTimeMillis();

      dc.redraw();
    }
    //winner conditions
    if (player1count > player2count) {
      super.winner = 1;
    }
    if (player2count > player1count) {
      super.winner = 2;
    }
  }

}