import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class DontGrabOrbGame extends baseGame {
    DConsole dc;
    private Player player1;
    private Player player2;
  Random r = new Random();
    int x = r.nextInt(800) + 30;
    int y = 100;
    int n = 10;
    int morey = r.nextInt(2) + n;
    int playx = 450;
    int playy = 500;
    int playx2 = 450;
    int playy2 = 500;
    int check = 0;
    int i = 0;
    public int winner;

    public DontGrabOrbGame(DConsole dc, Player p1, Player p2) {
      this.dc = dc;
      this.player1 = p1;
      this.player2 = p2;
    }

    public int getWinner() {
      return winner; //super.winner to set winner
    }

    public void initialize() {
      x = r.nextInt(800) + 30;
      y = 100;
      n = 10;
      morey = r.nextInt(2) + n;
      playx = 450;
      playy = 500;
      playx2 = 450;
      playy2 = 500;
      check = 0;
      i = 0;
    this.run();
    }

    public void run() {
      System.out.println(" Don'tGrabOrbGame Started");
      while (true) { 
        dc.clear();
        //Orb
        dc.fillEllipse(x,y,50,50);
        //Players
        dc.fillEllipse(playx,playy,25,25);
        dc.fillEllipse(playx2,playy2,25,25);
        //Controlls 
        if(player1.leftPressed()) {
          playx -= 5;
        }
        if(player1.rightPressed()) {
          playx += 5;
        }
        if(player2.leftPressed()) {
          playx2 -= 5;
        }
        if(player2.rightPressed()) {
          playx2 += 5;
        }
        //Orb Move
       if (i < 25) { 
        y = y + morey;
        } else {
          if (check == 0) { 
          y = 500; 
          x = 0;
          check = 1;
          }
          x = x + morey;
        }

        if (y > 700) {
          x = r.nextInt(800) + 30;
          n = n + 1;
          morey = r.nextInt(2) + n;
          y = 100;
          i++;
        }

        //Orb Grab Check
        if (this.playy >= (this.y - 25) && this.playy <= (this.y + 25)) {
          if (this.playx >= (this.x - 25) && this.playx <= (this.x + 25)) {
            playy = -10000;
            playx = -10000;
            super.winner = 2;
            System.out.println("Player Two Won!!!!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {      e.printStackTrace();
            }
            break;
          }
        }
        if (this.playy2 >= (this.y - 25) && this.playy2 <= (this.y + 25)) {
          if (this.playx2 >= (this.x - 25) && this.playx2 <= (this.x + 25)) {
            playy2 = -10000;
            playx2 = -10000;
            super.winner = 1;
            System.out.println("Player One Won!!!!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {      e.printStackTrace();
            }
            break;
          }
        }

        //The Redraw
        dc.redraw();
        dc.pause(40);
    }
}
}
