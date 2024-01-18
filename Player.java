import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class Player {
  private DConsole dc;
  private int controlUp;
  private int controlLeft;
  private int controlDown;
  private int controlRight;
  private int controlSelect;
  private int x;
  private int y;
  private int size;
  private int score;
  private int boardX = 60;
  private int boardY = 120;
  private int boardPos = 0;

  public Player(DConsole dc, int up, int left, int down, int right, int select) {
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


  //getCtrls either return the ASCII reference if it exists (num > 40) or converts it (convertCtrl())
  public String getUpCtrl() {
    if(this.controlUp > 40) {
      return Character.toString((char)this.controlUp);
    }
    return(this.convertCtrl(controlUp));
  }

  public String getLeftCtrl() {
    if(this.controlLeft > 40) {
      return Character.toString((char)this.controlLeft);
    }
    return(this.convertCtrl(controlLeft));
  }

  public String getDownCtrl() {
    if(this.controlDown > 40) {
      return Character.toString((char)this.controlDown);
    }
    return(this.convertCtrl(controlDown));
  }

  public String getRightCtrl() {
    if(this.controlRight > 40) {
      return Character.toString((char)this.controlRight);
    }
    return(this.convertCtrl(controlRight));
  }

  public String getSelectCtrl() {
    if(this.controlSelect > 40) {
      return Character.toString((char)this.controlSelect);
    }
    return(this.convertCtrl(controlSelect));
  }

  //Converts keycodes that do not have a proper ASCII representation
  public String convertCtrl(int ctrl) {
    switch(ctrl) {
      case 38:
        return "UP ARROW";

      case 37:
        return "LEFT ARROW";

      case 39:
        return "RIGHT ARROW";

      case 40:
        return "UP ARROW";

      case 16:
        return "SHIFT";

      case 13:
        return "ENTER";

      case 8:
        return "BACKSPACE";

      case 17:
        return "CONTROL";

      case 18:
        return "ALT";

      case 9:
        return "TAB";

      case 20:
        return "CAPSLOCK";

      default: //keycode not accounted for
        return "ERROR CODE: " + ctrl;
    }
  }

  public void scoreUp() {
    this.score++;
  }

  //Draw full size on gameBoard -- NOT FOR MINIGAMES
  public void draw() {
    //this.dc.drawImage(this.icon, this.boardX, this.boardY);
    this.dc.fillEllipse(this.boardX, this.boardY, this.size, this.size);
  }

  //Draw half-size on gameBoard -- NOT FOR MINIGAMES
  //val == offset in square (-15 for p1, +15 for p2)
  public void drawShared(int val) {
    //this.dc.drawImage(this.icon, this.boardX + val);
    this.dc.fillEllipse(this.boardX + val, this.boardY + val, this.size / 2, this.size / 2);
  }

  public void setBoardPos(int val) {
    this.boardPos = val;
  }

  public void setBoardX(int val) {
    this.boardX = val;
  }

  public void setBoardY(int val) {
    this.boardY = val;
  }

  public int getBoardX() {
    return this.boardX;
  }

  public int getBoardY() {
    return this.boardY;
  }

  public int getBoardPos() {
    return this.boardPos;
  }
}