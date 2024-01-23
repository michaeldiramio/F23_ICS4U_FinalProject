import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.awt.*;
public class Coin {
Random rangen = new Random();
  int x = rangen.nextInt(800)+ 50;
  int y = rangen.nextInt(500)+ 50;
  int touched = 0;
  private DConsole DC;

  
  public Coin(DConsole DC) {
   this.DC = DC;
  }
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }

  public void draw() {
    if(touched == 0){
    DC.setPaint(new Color(255, 255, 0));
    DC.fillEllipse(x, y, 50, 50);
    }
  }
  public void touched() {
    touched = 1;

  }
  
}