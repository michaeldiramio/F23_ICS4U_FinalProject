import DLibX.DConsole;
import java.awt.*;
import java.util.*;

public class ticTacToe extends BaseGame {

  DConsole dc;
  Random randGen;
  private Player player1;
  private Player player2;
  private int activePlayer = 0;
  private boolean gameOver = false;
  private int timeout = 0;
  private int turns = 0;

  private gridSquare[][] gameBoard = new gridSquare[3][3];

  public ticTacToe(DConsole dc, Player p1, Player p2){
    this.dc = dc;
    this.randGen = new Random();
    this.player1 = p1;
    this.player2 = p2;
  }

  public void initialize (){
    System.out.println("Initializing tictactoe");

    super.winner = 0;
    this.activePlayer = this.randGen.nextInt(2);
    this.gameOver = false;
    this.turns = 0;
    int xPosition = 150;
    int yPosition = 0;

    // Create new game board
    for(int i = 0; i < this.gameBoard.length; i++){
      xPosition += 150;
      for(int j = 0; j < this.gameBoard[i].length; j++){
        yPosition += 150;
        this.gameBoard[i][j] = new gridSquare(dc, xPosition, yPosition, 100);
      }
      yPosition = 0;
    }

    this.run();
  }

  public void run(){

    while(!gameOver){

      this.dc.clear();

      int mouseX = dc.getMouseXPosition();
      int mouseY = dc.getMouseYPosition();

      // Draw ticTacToe grid
      this.dc.setPaint(Color.BLACK);
      this.dc.setFont(new Font("DialogInput", Font.BOLD, 25)); 
      if(this.activePlayer == 0) {
        this.dc.drawString("Player One's Turn", 450, 40);
      } else {
        this.dc.drawString("Player Two's Turn", 450, 40);
      }
      this.dc.fillRect(375, 300, 20, 450);
      this.dc.fillRect(525, 300, 20, 450);
      this.dc.fillRect(450, 225, 450, 20);
      this.dc.fillRect(450, 375, 450, 20);

      for(int i = 0; i < this.gameBoard.length; i++){
        for(int j = 0; j < this.gameBoard[i].length; j++){

          //Check for mouse location
          if(mouseX >= (this.gameBoard[i][j].getXPosition() - this.gameBoard[i][j].getSize()/2) 
             && mouseX <= (this.gameBoard[i][j].getXPosition() + this.gameBoard[i][j].getSize()/2) 
             && mouseY >= (this.gameBoard[i][j].getYPosition() - this.gameBoard[i][j].getSize()/2) 
             && mouseY <= (this.gameBoard[i][j].getYPosition() + this.gameBoard[i][j].getSize()/2)){

            // Change opacity to indicate selected square
            this.gameBoard[i][j].setOpacity(25);

            // Set square to active player / switch active player
            if(this.dc.isMouseButton(1) && timeout <= 0 && this.gameBoard[i][j].getState() == 0){
              this.gameBoard[i][j].setState(this.activePlayer);
              if(this.activePlayer == 0){
                this.activePlayer = 1;
              } else {
                this.activePlayer = 0;
              }
              this.timeout = 50;
              this.turns++;
            }
          } else if (this.gameBoard[i][j].getState() == 0){
            this.gameBoard[i][j].setOpacity(0);
          }
        }
      }  

      // Draw graphics for squares
      for(int i = 0; i < this.gameBoard.length; i++){
        for(int j = 0; j < this.gameBoard[i].length; j++){
          this.gameBoard[i][j].drawSquare();
        }
      }  

      this.checkWinner();

      this.timeout--;
      this.dc.redraw();
      this.dc.pause(10);
    }

  }

  public void checkWinner(){

    int p1Squares = 0;
    int p2Squares = 0;

    // --- Check Columns ---
    for(int i = 0; i < this.gameBoard.length; i++){
      p1Squares = 0;
      p2Squares = 0;

      for (int j = 0; j < this.gameBoard[i].length; j++){
        if(this.gameBoard[i][j].getState() == 1){
          p1Squares++;
        } else if(this.gameBoard[i][j].getState() == 2){
          p2Squares++;
        }
      }

      // Check for 3 in a row
      if(p1Squares == 3){
        super.winner = 1;
      } else if (p2Squares == 3){
        super.winner = 2;
      }
    }

    // --- Check Rows ---
    for(int i = 0; i < this.gameBoard.length; i++){
      p1Squares = 0;
      p2Squares = 0;

      for (int j = 0; j < this.gameBoard[i].length; j++){
        if(this.gameBoard[j][i].getState() == 1){
          p1Squares++;
        } else if(this.gameBoard[j][i].getState() == 2){
          p2Squares++;
        }
      }

      // Check for 3 in a row
      if(p1Squares == 3){
        super.winner = 1;
      } else if (p2Squares == 3){
        super.winner = 2;
      }
    }

    // --- Check Diagonal 1 \ ---
    p1Squares = 0;
    p2Squares = 0;
    for(int i = 0; i < this.gameBoard.length; i++){
      if(this.gameBoard[i][i].getState() == 1){
        p1Squares++;
      } else if(this.gameBoard[i][i].getState() == 2){
        p2Squares++;
      }

    }

    // Check for 3 in a row
    if(p1Squares == 3){
      super.winner = 1;
    } else if (p2Squares == 3){
      super.winner = 2;
    } 

    // --- Check Diagonal 2 / ---
    p1Squares = 0;
    p2Squares = 0;
    for(int i = 0, j = 2; i < this.gameBoard.length; i++, j--){
      if(this.gameBoard[i][j].getState() == 1){
        p1Squares++;
      } else if(this.gameBoard[i][j].getState() == 2){
        p2Squares++;
      }
    }

    // Check for 3 in a row
    if(p1Squares == 3){
      super.winner = 1;
    } else if (p2Squares == 3){
      super.winner = 2;
    }


    if(this.turns == 9 && super.winner == 0){
      super.winner = -1;
    }
    // Set game winner / End game
    if(super.winner != 0){
      gameOver = true;
    }

  }  

}