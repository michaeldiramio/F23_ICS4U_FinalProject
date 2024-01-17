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
    this.games.add(new CoverScreen (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new pickKey (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new DragRace (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new MasherGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new RPS (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new ticTacToe (this.dc, this.rnd, this.players.get(0), this.players.get(1))); 
    this.games.add(new DontGrabOrbGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new TugOfWar (this.dc, this.players.get(0), this.players.get(1)));
    
    //run game loop
    System.out.println("Game initialized -- Running main loop");
    this.runGame();
  }
  
  public void runGame() {
    Scanner sc = new Scanner(System.in);
    while(true) {
      this.dc.clear();
      this.dc.setFont(new Font("Dialog", Font.PLAIN, 12));
      this.dc.setPaint(Color.BLACK);
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);
      this.dc.fillEllipse(225, 150, 50, 50); //temp visual
  
      //temporary way to access controls screen
      if(this.dc.isKeyPressed('C')) {
        this.controlsMenu();
      } else if(this.dc.isKeyPressed('F')) { //Type in arraySlot into console
        this.games.get(sc.nextInt()).initialize();
      } else if(this.dc.isKeyPressed('R')) {
        this.pickGame();
      } else if(this.dc.isKeyPressed('B')) {
        this.playGame();
      }
      
      this.dc.redraw();
      this.dc.pause(20);
    }
  }

  //Pick random number, run the minigame, determine winner and increase score
  //need to add displayed win screen
  //not entirely sure if the winner is being stored correctly, replit is dumb and wont load.
  public int pickGame() {
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
    return winner;
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


  //game in-progress
  public void playGame() {
    boolean playing = true;
    
  
    //make the squares for gameboard
    int counter = 0;
    int rowNum = 0;
    Player p1 = this.players.get(0);
    Player p2 = this.players.get(1);
    ArrayList<mapSquare> mapSquares = new ArrayList<mapSquare>();
 
    //draw game squares
    //draws 4 rows of 14 with a connecting square to go downwards
    //squares are stored in order in ArrayList mapSquares to allow for easy movement
    //counter represents square # in row (0-13)
    for(int i = 0; i < 13 * 4 + 4; i++) {
      if(counter >= 14 && rowNum % 2 == 0) { //right-side down connecting square
        mapSquares.add(new mapSquare(this.dc, 840, rowNum * 120 + 180));
        counter = 0;
        rowNum++;
      } else if(counter >= 14) { //left-side down connecting square
        mapSquares.add(new mapSquare(this.dc, 60, rowNum * 120 + 180));
        counter = 0;
        rowNum++;
      }
      //draw average square
      mapSquares.add(new mapSquare(this.dc, counter * 60 + 60, rowNum * 120 + 120));
      counter++;
    }
    
    while(playing) {
      this.dc.clear();
      this.dc.setFont(new Font("Dialog", Font.PLAIN, 12));
      this.dc.setPaint(Color.BLACK);
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);

      //draw image origin middle of screen
      this.dc.drawImage("gameMap/background.png", 450, 300);
      //draw game squares
      for(int i = 0; i < mapSquares.size(); i++) {
        mapSquares.get(i).draw();
      }

      //draw player icon full size if different coordinates, otherwise p1 in top-left corner p2 bottom-right at half size
      if(this.players.get(0).getBoardX() == this.players.get(1).getBoardX() && this.players.get(0).getBoardY() == this.players.get(1).getBoardY()) { //same spot
        this.dc.setPaint(Color.RED);
        this.players.get(0).drawShared(-15);
        this.dc.setPaint(Color.BLUE);
        this.players.get(1).drawShared(15);
      } else {
        this.dc.setPaint(Color.RED);
        this.players.get(0).draw();
        this.dc.setPaint(Color.BLUE);
        this.players.get(1).draw();
      }

      if(p1.selectPressed() || p2.selectPressed()) { //maybe put some dice rollhere

        //Play random game and move players afterwards
        if(this.pickGame() == 1) {

          p1.setBoardPos(p1.getBoardPos() + 1);
          p1.setBoardX(mapSquares.get(p1.getBoardPos()).getX());
          p1.setBoardY(mapSquares.get(p1.getBoardPos()).getY());

        } else {

          p2.setBoardPos(p2.getBoardPos() + 1);
          p2.setBoardX(mapSquares.get(p2.getBoardPos()).getX());
          p2.setBoardY(mapSquares.get(p2.getBoardPos()).getY());

        }
      }
      
      this.dc.redraw();
      this.dc.pause(20);
    }
  }

  
}