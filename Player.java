import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class Player {
  DConsole dc;
  char controlUp;
  char controlLeft;
  char controlDown;
  char controlRight;
  char controlSelect;
  int x;
  int y;
  int size;

  public Player(DConsole dc, char up, char left, char down, char right, char select) {
    this.size = 40;
    this.x = dc.getWidth() / 2;
    this.y = dc.getHeight() / 2;
    this.controlUp = up;
    this.controlLeft = left;
    this.controlDown = down;
    this.controlRight = right;
    this.controlSelect = select;
    this.dc = dc;
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

  public boolean upPressed() {
    if(dc.isKeyPressed(controlUp)) {
      return true;
    }
    return false;
  }

  public boolean leftPressed() {
    if(dc.isKeyPressed(controlLeft)) {
      return true;
    }
    return false;
  }

  public boolean downPressed() {
    if(dc.isKeyPressed(controlDown)) {
      return true;
    }
    return false;
  }

  public boolean rightPressed() {
    if(dc.isKeyPressed(controlRight)) {
      return true;
    }
    return false;
  }

  public boolean selectPressed() {
    if(dc.isKeyPressed(controlSelect)) {
      return true;
    }
    return false;
  }

  public void changeX(int val) {
    this.x += val;
  }

  public void changeY(int val) {
    this.y += val;
  }

  public char getUpCtrl() {
    return this.controlUp;
  }

  public char getLeftCtrl() {
    return this.controlLeft;
  }

  public char getDownCtrl() {
    return this.controlDown;
  }

  public char getRightCtrl() {
    return this.controlRight;
  }

  public char getSelectCtrl() {
    return this.controlSelect;
  }
}