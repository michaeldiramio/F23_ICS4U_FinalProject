import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main {
  
  private DConsole dc = new DConsole(900, 600);
  private ArrayList<baseGame> games = new ArrayList<baseGame>();
  private ArrayList<Player> players = new ArrayList<Player>();
  private Random rnd = new Random();
  
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
    this.games.add(new GrabOrb (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new ClickGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new pickKey (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new DragRace (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new pickKey (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new RPS (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new ticTacToe (this.dc, this.rnd, this.players.get(0), this.players.get(1))); 
    this.games.add(new DontGrabOrbGame (this.dc, this.players.get(0), this.players.get(1)));
    
    //run game loop
    System.out.println("Game initialized -- Running main loop");
    this.runGame();
  }
  
  public void runGame() {
    Scanner sc = new Scanner(System.in);
    while(true) {
      this.dc.clear();
      this.dc.fillEllipse(225, 150, 50, 50); //temp visual
  
      //temporary way to access controls screen
      if(this.dc.isKeyPressed('C')) {
        this.controlsMenu();
      } else if(this.dc.isKeyPressed('F')) { //Type in arraySlot into console
        this.games.get(sc.nextInt()).initialize();
      } else if(this.dc.isKeyPressed('R')) {
        this.pickGame();
      }
      
      this.dc.redraw();
      this.dc.pause(20);
    }
  }

  //Pick random number, run the minigame, determine winner and increase score
  //need to add displayed win screen
  //not entirely sure if the winner is being stored correctly, replit is dumb and wont load.
  public void pickGame() {
    int gameNum = rnd.nextInt(this.games.size());
    this.games.get(gameNum).initialize();
    int winner = this.games.get(gameNum).getWinner();
    if(winner == 1) {
      System.out.println("Player 1 won");
      this.players.get(0).scoreUp();
    } else {
      System.out.println("Player 2 won");
      this.players.get(1).scoreUp();
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

/*

IGNORE THIS

dc.drawLine(400, 60, 400, 260);
if(mouseX <= 420
  && mouseX >= 380
  && mouseY >= 60
  && mouseY <= 260
  && dc.isMouseButton(1)) {
    ballSliderY = mouseY;
  }
dc.fillEllipse(400, ballSliderY, 20, 20);
dc.drawString(ballCount, 430, ballSliderY);
ballCount = (ballSliderY - 40) / 20;  //1-11
*/