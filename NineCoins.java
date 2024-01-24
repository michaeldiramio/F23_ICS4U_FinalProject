import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.awt.*;

public class NineCoins extends baseGame {
  // variablle
  DConsole dc;
  private Player player1;
  private Player player2;
  int player1count = 0;
  int player2count = 0;
  int keycount1 = 0;
  int keycount2 = 0;
  int score1 = 0;
  int score2 = 0;

  public NineCoins(DConsole dc, Player p1, Player p2) {
    player1 = p1;
    player2 = p2;
    this.dc = dc;
  }

  public void initialize() {
    System.out.println("running");
    player1count = 0;
    player2count = 0;
    score1 = 0;
    score2 = 0;
    player1.setX(100);
    player1.setY(550);
    player2.setX(800);
    player2.setY(550);
    this.run();
  }

  public void run() {
    // setup stuff
    
    Font f = dc.getFont(); // the writing setup
    dc.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
    long startTime = System.currentTimeMillis();
    long elapsedTime = 0;
    dc.setPaint(new Color(73, 68, 67));
    dc.fillRect(450, 300, 900, 600);
    Coin[]coins = new Coin[11];
      for(int i = 0; i < 11; i++){
       coins[i] = new Coin(dc);
      
      }
    while (elapsedTime - startTime < 10000) {
      // draw the map
      dc.clear();
      dc.setPaint(new Color(255, 0, 0));
      dc.fillEllipse(player1.getX(), player1.getY(), 50, 50);
      dc.setPaint(new Color(0, 255, 0));
      dc.fillEllipse(player2.getX(), player2.getY(), 50, 50);
      elapsedTime = System.currentTimeMillis();

      if (player1.upPressed()) {
        player1.changeY(-1);
        
      }
      if (player1.downPressed()) {
        player1.changeY(1);
       
      }
      if (player1.leftPressed()) {
        player1.changeX(-1);
        
      }
      if (player1.rightPressed()) {
        player1.changeX(1);
        
      }
      // player 2
      if (player2.upPressed()) {
        player2.changeY(-1);
        
      }
      if (player2.downPressed()) {
        player2.changeY(1);
        
      }
      if (player2.leftPressed()) {
        player2.changeX(-1);
        
      }
      if (player2.rightPressed()) {
        player2.changeX(1);
        
      }
      // player 1
      if (player1.getY() > 575) { // top and bottem walls
        player1.changeY(-1);
      }

      if (player1.getY() < 25) {
        player1.changeY(1);

      }

      if (player1.getX() > 875) { // for wall and points
        player1.changeX(-1);
      }

      if (player1.getX() < 25) {
        player1.changeX(1);
      }
      // player 2
      if (player2.getY() > 575) { // top and bottem walls
        player2.changeY(-1);
      }

      if (player2.getY() < 25) {
        player2.changeY(1);

      }

      if (player2.getX() > 875) { // for wall and points
        player2.changeX(-1);
      }

      if (player2.getX() < 25) {
        player2.changeX(1);
      }

      

      // winner conditions
      for(int i=0; i < 11; i++){
        coins[i].draw();
        int coinx = coins[i].getX();
        int coiny = coins[i].getY();
        if (this.player1.getY() >= (coiny - 25) && this.player1.getY() <= (coiny + 25)) {
          if (this.player1.getX() >= (coinx - 25) && this.player1.getX() <= (coinx + 25)) {
            score1 = score1 + 1;
            coins[i].touched();
          }
        }
        if (this.player2.getY() >= (coiny - 25) && this.player2.getY() <= (coiny + 25)) {
          if (this.player2.getX() >= (coinx - 25) && this.player2.getX() <= (coinx + 25)) {
            score2 = score2 + 1;
            coins[i].touched();
          }
        }
      }
      dc.redraw();
    }
    if (score1 > score2) {
      super.winner = 1;
      dc.clear();
      dc.setPaint(new Color(255, 153, 204));
      dc.fillRect(450, 300, 900, 600);
      dc.setPaint(new Color(0, 0, 0));
      dc.drawString("player 1 wins", 450, 100);
      
      dc.redraw();
      dc.pause(2000);
    }
    if (score2 > score1) {
      super.winner = 2;
      dc.clear();
      dc.setPaint(new Color(255, 153, 204));
      dc.fillRect(450, 300, 900, 600);
      dc.setPaint(new Color(0, 0, 0));
      dc.drawString("player 2 wins", 450, 100);
      dc.redraw();
      dc.pause(2000);
    }
  }
}