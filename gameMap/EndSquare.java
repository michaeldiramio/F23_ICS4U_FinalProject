//last square in GameBoard, used to determine when a player has won

import DLibX.*;
import java.awt.*;
import java.util.*;

public class EndSquare extends mapSquare {

  private DConsole dc;
  private int x;
  private int y;
  private int size = 60;

  public EndSquare(DConsole dc, int x, int y) {
    this.dc = dc;
    this.x = x;
    this.y = y;
  }

  public void draw() {
    this.dc.setPaint(Color.RED);
    this.dc.drawRect(this.x, this.y, this.size, this.size);
  }
  
}