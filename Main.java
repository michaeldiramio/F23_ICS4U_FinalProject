import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main {

  private DConsole dc = new DConsole(900, 600);
  private ArrayList<BaseGame> games = new ArrayList<BaseGame>();
  private ArrayList<Player> players = new ArrayList<Player>();
  private Random rnd = new Random();
  private int gameWinner = 0;

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
    this.games.add(new pong (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new GrabOrb (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new NineCoins (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new ClickGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new CoverScreen (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new pickKey (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new DragRace (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new MasherGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new RPS (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new ticTacToe (this.dc, this.players.get(0), this.players.get(1))); 
    this.games.add(new DontGrabOrbGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new SpeedWord (this.dc, this.players.get(0), this.players.get(1), this.rnd));
    this.games.add(new TugOfWar (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new GuessingGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new ShieldShooter (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new DiceGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new FlappyBirdGame (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new Dino (this.dc, this.players.get(0), this.players.get(1)));
    this.games.add(new Tag (this.dc, this.players.get(0), this.players.get(1)));

    //run game loop
    System.out.println("Game initialized -- Running main loop");
    System.out.println("Playing with " + this.games.size() + " minigames!");
    this.runGame();
  }

  public void runGame() {
    Scanner sc = new Scanner(System.in);
    while(true) {
      this.dc.clear();
      this.dc.setFont(new Font("Dialog", Font.PLAIN, 12));
      this.dc.setPaint(Color.BLACK);
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);

      this.dc.setPaint(new Color(229, 204, 255));
      this.dc.fillRect(450, 300, 900, 600);

      this.dc.setPaint(Color.black);
      dc.setFont(new Font("Serif", Font.BOLD, 42));
      this.dc.drawString("WARIO WAREHOUSE", 450, 100);

      this.dc.setPaint(new Color(127, 0, 255));
      this.dc.fillRect(450, 300, 250, 100);

      this.dc.setPaint(Color.WHITE);
      this.dc.drawString("E to PLAY", 450, 290);

      if(this.players.get(0).selectPressed() || this.players.get(1).selectPressed()){
        this.playGame();
        this.endScreen();
      }

      this.dc.redraw();
      this.dc.pause(20);
    }
  }

  //Pick random number, run the minigame, determine winner and increase score
  //need to add displayed win screen
  //not entirely sure if the winner is being stored correctly, replit is dumb and wont load.
  public int pickGame() {
    //Reset DConsole settings
    this.dc.clear();
    this.dc.setFont(new Font("Dialog", Font.PLAIN, 12));
    this.dc.setPaint(Color.BLACK);
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);

    //roll & play random game
    int gameNum = rnd.nextInt(this.games.size());
    this.games.get(gameNum).initialize();
    int winner = this.games.get(gameNum).getWinner();
    System.out.println("Player " + winner + " won");
    return winner;
  }

  public void controlsMenu() {
    //initialize variables
    int keyCounter = 20;
    int cursorX = 120;
    int cursorY = 540;
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
          if(tmp.leftPressed() && cursorX > 200) {
            cursorX -= 400;
            keyCounter = 8;
          } if(tmp.rightPressed() && cursorX < 40 + 400 * (this.players.size() - 1)) {
            cursorX += 400;
            keyCounter = 8;
          } if(tmp.selectPressed()) {
            if(cursorY == 540) { //"EXIT" selected -- back to main menu
              done = true;
              System.out.println("exit controls");
              this.dc.pause(200); //prevent double press
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
    int cursorPos = 0;
    int keyCounter = 20;
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
      if(i == 55) { //last square
        mapSquares.add(new EndSquare(this.dc, 840 - counter * 60, rowNum * 120 + 120));
        break;
      } else if(rowNum % 2 == 0)  {//add squares left->right
        mapSquares.add(new mapSquare(this.dc, counter * 60 + 60, rowNum * 120 + 120));
      } else { //add squares right->left
        mapSquares.add(new mapSquare(this.dc, 840 - counter * 60, rowNum * 120 + 120));
      }
      counter++;
    }

    while(playing) {
      this.dc.clear();
      this.drawGameBoard(mapSquares);
      this.resetDConsole();

      this.dc.setOrigin(DConsole.ORIGIN_LEFT);
      this.dc.setFont(new Font("Dialog", Font.PLAIN, 20));
      //labels
      this.dc.drawString("Controls", 100, 550);
      this.dc.drawString("Start", 400, 550);
      this.dc.drawString("How To Play", 700, 550);
      this.dc.drawString("Press E or SHIFT to Select", 330, 50);
      this.dc.drawString("P1:", 40, 45);
      this.dc.setPaint(Color.RED);
      this.dc.fillEllipse(80, 50, 20, 20);
      this.dc.setPaint(Color.BLACK);
      this.dc.drawString("P2:", 820, 45);
      this.dc.setPaint(Color.BLUE);
      this.dc.fillEllipse(860, 50, 20, 20);

      //detect left/right keypresses
      if(keyCounter == 0) {
        if((p1.rightPressed() || p2.rightPressed()) && cursorPos < 2) {
          cursorPos++;
          keyCounter = 15;
        } else if((p1.leftPressed() || p2.leftPressed()) && cursorPos > 0) {
          cursorPos--;
          keyCounter = 15;
        } else if(this.dc.isKeyPressed('l')) { //temp move player for testing /.
          p1.setBoardPos(p1.getBoardPos() + 1);
          p1.setBoardX(mapSquares.get(p1.getBoardPos()).getX());
          p1.setBoardY(mapSquares.get(p1.getBoardPos()).getY());
          System.out.println(p1.getBoardPos());
          keyCounter = 4;
        }
      }

      if(keyCounter > 0) { //delaying repeat key presses
        keyCounter--;
      }

      //draw cursor on labels at bottom of screen && check for keypresses to select hovered
      this.dc.setPaint(Color.BLACK);
      switch(cursorPos) {
        case 0:
          this.dc.fillRect(75, 550, 20, 3);
          if((p1.selectPressed() || p2.selectPressed()) && keyCounter == 0) {
            this.controlsMenu();
            keyCounter = 4;
          }
          break;

        case 1:
          this.dc.fillRect(375, 550, 20, 3);
          if((p1.selectPressed() || p2.selectPressed()) && keyCounter == 0) {

            int minigameWinner = this.pickGame();

            if(minigameWinner == 1) { //play random game and move winning player forward
              p1.setBoardPos(p1.getBoardPos() + this.diceRoll(mapSquares, p1)); //increase board position
              p1.setBoardX(mapSquares.get(p1.getBoardPos()).getX()); //new X
              p1.setBoardY(mapSquares.get(p1.getBoardPos()).getY()); //new Y

            } else if(minigameWinner == 2) {
              p2.setBoardPos(p2.getBoardPos() + this.diceRoll(mapSquares, p2)); //increase bord position
              p2.setBoardX(mapSquares.get(p2.getBoardPos()).getX()); //new X
              p2.setBoardY(mapSquares.get(p2.getBoardPos()).getY()); //new Y

            }
            keyCounter = 4;
          }
          if(p1.getBoardPos() == 58) { //p1 wins
            playing = false;
            this.gameWinner = 1;
            this.drawGameBoard(mapSquares); //display final gameBoard state before ending
            this.dc.pause(2000);
          } else if(p2.getBoardPos() == 58) { //p2 wins
            playing = false;
            this.gameWinner = 2;
            this.drawGameBoard(mapSquares); //display final gameBoard state before ending
            this.dc.pause(2000);
          }
          break;

        case 2:
          this.dc.fillRect(675, 550, 20, 3);
          if((p1.selectPressed() || p2.selectPressed()) && keyCounter == 0) {
            this.instructionsMenu();
            keyCounter = 4;
          }
          break;
      }
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);


      this.dc.redraw();
      this.dc.pause(40);
    }
  }

  public int diceRoll(ArrayList<mapSquare> mapSquares, Player plr) {
    int cd = 0; //increase to slowdown
    int diceNum = 1;
    int result = this.rnd.nextInt(6) + 1;

    while (cd < 400 || diceNum != result) { //until correct dice showed after cd reached 400
      //rotate through 6 dice
      diceNum++;
      if(diceNum == 7) {
        diceNum = 1;
      }
      this.dc.clear();
      this.drawGameBoard(mapSquares);
      //uses https://iconduck.com/sets/css-gg-icon-set for dice images
      this.dc.drawImage("Dice/" + diceNum + ".png", 450, 300);
      if(cd > 30 && diceNum != result) { // start heavily slowing down
        cd += 100;
      }
      if(cd > 500) { //slowest speed is 500ms
        cd = 500;
      }
      cd++;
      this.dc.redraw();
      this.dc.pause(5 + cd);
    }

    if(plr.getBoardPos() + diceNum > 58) {
      diceNum = 58 - plr.getBoardPos();
    }
    return diceNum;

  }

  public void drawGameBoard(ArrayList<mapSquare> mapSquares) {
    this.resetDConsole();
    Player p1 = this.players.get(0);
    Player p2 = this.players.get(1);
    //draw image origin middle of screen
    this.dc.drawImage("gameMap/background.png", 450, 300);
    //draw game squares
    for(int i = 0; i < mapSquares.size(); i++) {
      mapSquares.get(i).draw();
    }

    //draw player icon full size if different coordinates, otherwise p1 in top-left corner p2 bottom-right at half size
    if(p1.getBoardPos() == p2.getBoardPos()) { //same position
      this.dc.setPaint(Color.RED);
      p1.drawShared(-15);
      this.dc.setPaint(Color.BLUE);
      p2.drawShared(15);
    } else { //different positions
      this.dc.setPaint(Color.RED);
      p1.draw();
      this.dc.setPaint(Color.BLUE);
      p2.draw();
    }
    this.dc.redraw();
  }

  public void resetDConsole() { //reset DConsole settings
    this.dc.setFont(new Font("Dialog", Font.PLAIN, 12));
    this.dc.setPaint(Color.BLACK);
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);
  }

  public void instructionsMenu() { 
    this.dc.pause(200); //prevent double press
    System.out.println("Running instructionsMenu");
    this.resetDConsole();
    this.dc.setFont(new Font("Dialog", Font.PLAIN, 20));
    boolean waiting = true;
    while(waiting) {
      this.dc.clear();

      this.dc.setPaint(new Color(229, 204, 255));
      this.dc.fillRect(0, 300, 900, 600);
      this.dc.setPaint(Color.BLACK);
      this.dc.setFont(new Font("Serif", Font.BOLD, 17));
      this.dc.drawString("How To Play", 385, 50);
      this.dc.setOrigin(DConsole.ORIGIN_LEFT);
      this.dc.drawString("Return", 420, 550);
      this.dc.fillRect(400, 555, 10, 5); //Cursor

      this.dc.fillEllipse(180, 155, 8, 8);
      this.dc.drawString("Win short competitive minigames to earn dice rolls!", 200, 150); 
      this.dc.fillRect(200, 185, 10, 5);
      this.dc.drawString("You can use these dice roll to roll the dice and move forward!", 220, 180);
      this.dc.fillRect(200, 215, 10, 5);
      this.dc.drawString("The first player to reach the end wins of the board wins", 220, 210);
       this.dc.drawString("and gains bragging rights!", 220, 240);

      if(this.players.get(0).selectPressed() || this.players.get(1).selectPressed()) {
        waiting = false;
        this.dc.pause(200); //prevent double press
      }

      this.dc.redraw();
    }
  }

  public void endScreen() { //make cool ./
    System.out.println("Running endScreen");
    int buttonSize = 0;

    while(true) {
      this.dc.clear();
      this.resetDConsole();

      int mouseX = this.dc.getMouseXPosition();
      int mouseY = this.dc.getMouseYPosition();

      this.dc.drawImage("gameMap/background.png", 450, 300);
      // Game Over Text
      this.dc.setPaint(Color.BLACK);
      this.dc.setFont(new Font("Dialog", Font.BOLD, 125));
      this.dc.drawString("Game Over", 450, 60);

      // Player Wins Text
      this.dc.setFont(new Font("Dialog", Font.BOLD, 90)); 
      if(this.gameWinner == 1) {
        this.dc.setPaint(Color.RED);
        this.dc.drawString("Player 1 Wins", 450, 165);
      } else {
        this.dc.setPaint(Color.BLUE);
        this.dc.drawString("Player 2 Wins", 450, 165);
      }

      // Restart Button Graphics
      this.dc.setPaint(Color.BLACK);
      this.dc.fillRect(450, 450, 245 + buttonSize, 105 + buttonSize);
      this.dc.setPaint(Color.GRAY);
      this.dc.fillRect(450, 450, 240 + buttonSize, 100 + buttonSize);
      this.dc.setPaint(Color.WHITE);
      this.dc.setFont(new Font("DialogInput", Font.BOLD, 25 + (buttonSize / 3)));
      this.dc.drawString("Click Here To", 450, 430);
      this.dc.drawString("Restart", 450, 470);

      // Restart Button Interaction
      if (mouseX >= 330 && mouseX <= 570 &&
         mouseY >= 400 && mouseY <= 500) {

        buttonSize = 15;
        // Reset arrays and intialize again
        if (this.dc.isMouseButton(1) ) {
          this.games = new ArrayList<BaseGame>();
          this.players = new ArrayList<Player>();
          this.gameWinner = 0;
          this.initialize();
        }
      } else {
        buttonSize = 0;
      }

      this.dc.redraw();
      this.dc.pause(20);
    }
  }

}