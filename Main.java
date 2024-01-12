import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main {
  
  DConsole dc = new DConsole(900, 600);
  ArrayList<baseGame> games = new ArrayList<baseGame>();
  ArrayList<Player> players = new ArrayList<Player>();
  Random rnd = new Random();
  
	public static void main(String[] args) {

    Main m = new Main();
    
		m.initialize();
	}

  //first time setup
  public void initialize() {
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);

    //add players
    this.players.add(new Player(this.dc, 87, 65, 83, 68, 69));
    this.players.add(new Player(this.dc, 38, 37, 40, 39, 16));

    //add games
    this.games.add(new DragRace (this.dc, this.players.get(0), this.players.get(1))); 
    
    //run game loop
    System.out.println("Game initialized -- Running main loop");
    this.runGame();
  }
  
  public void runGame() {
    while(true) {
      this.dc.clear();
      this.dc.fillEllipse(225, 150, 50, 50); //temp visual
  
      //temporary way to access controls screen
      if(this.dc.isKeyPressed('C')) {
        this.controlsMenu();
      }
      
      this.dc.redraw();
    }
  }

  public void controlsMenu() {
    //initialize variables
    int keyCounter = 0;
    int cursorX = 120;
    int cursorY = 140;
    boolean done = false;
    
    while(!done) {
      //menu background
      this.dc.setPaint(Color.black);
      this.dc.fillRect(450, 300, 880, 585);

      //drawing player controls text
      this.dc.setOrigin(DConsole.ORIGIN_LEFT);
      this.dc.setPaint(Color.white);
      for(int i = 0; i < this.players.size(); i++) {
        Player tmp = this.players.get(i);
        //box seperated in half -- one for each player
        //1st half begins at x=150
        //start of 2nd half is 400 pixels away (400 * i)
        this.dc.drawString("Player " + (i + 1), 150 + 400 * i, 60);
        this.dc.drawString("Up: " + tmp.getUpCtrl(), 150 + 400 * i, 140);
        this.dc.drawString("Down: " + tmp.getDownCtrl(), 150 + 400 * i, 220);
        this.dc.drawString("Left: " + tmp.getLeftCtrl(), 150 + 400 * i, 300);
        this.dc.drawString("Right: " + tmp.getRightCtrl(), 150 + 400 * i, 380);
        this.dc.drawString("Select: " + tmp.getSelectCtrl(), 150 + 400 * i, 460);
        this.dc.drawString("EXIT", 150 + 400 * i, 540);

        //selection cursor
        this.dc.fillRect(cursorX, cursorY, 20, 3);

        //key inputs (move cursor and select options)
        if(keyCounter == 0) {
          if(tmp.upPressed() && cursorY > 140) {
            cursorY -= 80;
            keyCounter = 8;
          } if(tmp.downPressed() && cursorY < 540) {
            cursorY += 80;
            keyCounter = 8;
          } if(tmp.leftPressed() && cursorX > 200) {
            cursorX -= 400;
            keyCounter = 8;
          } if(tmp.rightPressed() && cursorX < 40 + 400 * (this.players.size() - 1)) {
            cursorX += 400;
            keyCounter = 8;
          } if(tmp.selectPressed()) {
            //TODO -- select control being hovered and take new input? -- May be scrapped
            if(cursorY == 540) { //"EXIT" selected -- back to main menu
              done = true;
            }
          }
        }
  
      }
      if(keyCounter > 0) { //delaying repeat key presses
        keyCounter--;
      }

      //reset DC brush
      this.dc.setPaint(Color.black);
      this.dc.redraw();
      this.dc.pause(20);
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);
    }
  }
}