import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public abstract class baseGame {

  public int winner;

  public baseGame() {

  }

  public int getWinner() {
    return winner; //super.winner to set winner
  }

  public abstract void initialize();

  public abstract void run();

}