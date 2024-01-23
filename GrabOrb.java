import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.Random;
public class GrabOrb extends baseGame {
  
  DConsole dc;
  private Player player1;
  private Player player2;
  
  public int winner;
  Random r = new Random();
  int x = r.nextInt(600) + 100;
  int y = r.nextInt(300) + 100;
  int points = 0;
  int points2 = 0;
  int playx = 450;
  int playy = 300;
  int playx2 = 450;
  int playy2 = 300;
  int bloop = 0;
  public GrabOrb(DConsole dc, Player p1, Player p2) {
   this.dc = dc;
    this.player1 = p1;
    this.player2 = p2;
  
  }

  public int getWinner() {
    return winner; //super.winner to set winner
  }

  public void initialize (){
    x = r.nextInt(600) + 100;
    y = r.nextInt(300) + 100;
    points = 0;
    points2 = 0;
    playx = 450;
    playy = 300;
    playx2 = 450;
    playy2 = 300;
    bloop = 0;
    this.run();
  }

  public void run() {
    System.out.println("GrabOrb Started");
    while (bloop == 0) { 
      dc.clear();
     //orb 
      dc.fillEllipse(x,y,50,50);
      //players
      dc.fillEllipse(playx,playy,25,25);
      dc.setPaint(Color.BLUE);
      dc.fillEllipse(playx2,playy2,25,25);
      dc.setPaint(Color.BLACK);
      dc.drawString(points, 100, 40);
      dc.drawString(points2, 500, 40);
     //controlls 
      if (player1.upPressed()) {
        playy -= 5;
      }
      if(player1.downPressed()) {
        playy += 5;
      }
      if(player1.leftPressed()) {
        playx -= 5;
      }
      if(player1.rightPressed()) {
        playx += 5;
      }
      if (player2.upPressed()) {
        playy2 -= 5;
      }
      if(player2.downPressed()) {
        playy2 += 5;
      }
      if(player2.leftPressed()) {
        playx2 -= 5;
      }
      if(player2.rightPressed()) {
        playx2 += 5;
      }
      //Orb Grab Check
      if (this.playy >= (this.y - 25) && this.playy <= (this.y + 25)) {
        if (this.playx >= (this.x - 25) && this.playx <= (this.x + 25)) {
          x = r.nextInt(600) + 100;
          y = r.nextInt(300) + 100;
          points += 1;
        }
      }
      if (this.playy2 >= (this.y - 25) && this.playy2 <= (this.y + 25)) {
        if (this.playx2 >= (this.x - 25) && this.playx2 <= (this.x + 25)) {
          x = r.nextInt(600) + 100;
          y = r.nextInt(300) + 100;
          points2 += 1;
        }
      }
      if (points >= 5) {
        dc.drawString("Player One Won!!!!", 450, 300);
        super.winner = 1;
        System.out.println("Player One Won!!!!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {      e.printStackTrace();
        }
        bloop = 1;
        
        } else if (points2 >= 5) {
          dc.drawString("Player Two Won!!!!", 450, 300); 
        super.winner = 2;
        System.out.println("Player Two Won!!!!");
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {      e.printStackTrace();
        }
        bloop = 1;
      }
      dc.redraw();
      dc.pause(20);
    }
  }
}