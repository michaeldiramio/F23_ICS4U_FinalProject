import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class Player {
  DConsole dc;
  char controlW = 'W';
  char controlA = 'A';
  char controlS = 'S';
  char controlD = 'D';
  char controlSelect = 'C';
  int x;
  int y;

  public Player(DConsole dc) {
    this.x = dc.getWidth() / 2;
    this.y = dc.getHeight() / 2;
    this.dc = dc;
  }

  public void setW(char ctrl) {
    this.controlW = ctrl;
  }

  public void setA(char ctrl) {
    this.controlA = ctrl;
  }

  public void setS(char ctrl) {
    this.controlS = ctrl;
  }

  public void setD(char ctrl) {
    this.controlD = ctrl;
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

  public boolean wPressed() {
    if(dc.isKeyPressed(controlW)) {
      return true;
    }
    return false;
  }

  public boolean aPressed() {
    if(dc.isKeyPressed(controlA)) {
      return true;
    }
    return false;
  }

  public boolean sPressed() {
    if(dc.isKeyPressed(controlS)) {
      return true;
    }
    return false;
  }

  public boolean dPressed() {
    if(dc.isKeyPressed(controlD)) {
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

  public char getWCtrl() {
    return this.controlW;
  }

  public char getACtrl() {
    return this.controlA;
  }

  public char getSCtrl() {
    return this.controlS;
  }

  public char getDCtrl() {
    return this.controlD;
  }

  public char getSelectCtrl() {
    return this.controlSelect;
  }
}