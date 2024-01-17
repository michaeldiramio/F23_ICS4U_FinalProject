import DLibX.*;
import java.awt.*;
import java.util.*;

public class mapSquare {

  private DConsole dc;
  private int x;
  private int y;
  private int size = 60;

  public mapSquare() {
    
  }

  public mapSquare(DConsole dc, int x, int y) {
    this.dc = dc;
    this.x = x;
    this.y = y;
  }

  public void setX(int val) {
    this.x = val;
  }

  public void setY(int val) {
    this.y = val;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void draw() {
    this.dc.drawRect(this.x, this.y, this.size, this.size);
  }
}