import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class gridSquare {

  DConsole dc;
  private int x;
  private int y;
  private int size;
  private int state;
  private int opacity;

  public gridSquare(DConsole dc, int x, int y, int size){
    this.dc = dc;
    this.x = x;
    this.y = y;
    this.size = size;
    this.state = 0;
  }

  public void drawSquare() {
    dc.setPaint(new Color(255, 255, 255, opacity));
    dc.fillRect(x, y, size, size);
  }

  public void setOpacity(int val) {
    this.opacity = val;
  }

  public void setState(int activePlayer){
    if(activePlayer == 0){
      dc.setPaint(Color.RED);
      dc.fillEllipse(x, y, size, size);
      this.state = 1;
    } else{
      dc.setPaint(Color.BLUE);
      dc.fillRect(x, y, size, size);
      this.state = 2;
    }
  
  }

  public int getXPosition() {
    return this.x;
  }

  public int getYPosition() {
    return this.y;
  }

  public int getSize() {
    return this.size;
  }
}