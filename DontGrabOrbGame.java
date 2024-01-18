import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class DontGrabOrbGame extends baseGame {
    DConsole dc;
    private Player player1;
    private Player player2;
  int x = 2;
  int y = 580;
    int playx = 450;
    int playy = 500;
    int playx2 = 450;
    int playy2 = 500;
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
    this.run();
    }

    public void run() {
      System.out.println("JumpingGame Started");
      while (true) { 
        dc.clear();
        //Thing to Jump Over
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
        
        //The Redraw
        dc.redraw();
        dc.pause(20);
    }
}
}