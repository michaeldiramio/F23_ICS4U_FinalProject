import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

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
    //set default x & ys
    this.run();
  }

  public void run() {
    boolean playing = true;
    int num = rnd.nextInt(4);
    while (playing) {
      switch (num) { //print which key
        case 0:
          //code goes here
      }

      //afterwards, loop with another switch inside that checks for the correct key press
      //when key pressed, super.winner = 1 or super.winner = 2.
      
      
    }
  }
}