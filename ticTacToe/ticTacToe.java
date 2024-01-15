import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class ticTacToe extends baseGame {

  DConsole dc;
  Random randGen;
  private Player player1;
  private Player player2;
  private int activePlayer = 0;
  private boolean gameOver = false;
  private int timeout = 0;

  private gridSquare[][] gameBoard = new gridSquare[3][3];
  
  public ticTacToe(DConsole dc, Random rng, Player p1, Player p2){
    this.dc = dc;
    this.randGen = rng;
    this.player1 = p1;
    this.player2 = p2;
  }
  
  public void initialize (){
    System.out.println("Game initialized -- Running tictactoe");
    
    this.activePlayer = this.randGen.nextInt(2);
    int xPosition = 150;
    int yPosition = 0;
    
    // Create new game board
    for(int i = 0; i < gameBoard.length; i++){
      xPosition += 150;
      for(int j = 0; j < gameBoard[i].length; j++){
        yPosition += 150;
        gameBoard[i][j] = new gridSquare(dc, xPosition, yPosition, 100);
        System.out.println("new Square");
      }
      yPosition = 0;
    }

    this.run();
  }
  
  public void run(){

    while(!gameOver){

      dc.clear();
      
      int mouseX = dc.getMouseXPosition();
      int mouseY = dc.getMouseYPosition();

      // Draw ticTacToe grid
      dc.setPaint(Color.BLACK);
      dc.fillRect(375, 300, 20, 450);
      dc.fillRect(525, 300, 20, 450);
      dc.fillRect(450, 225, 450, 20);
      dc.fillRect(450, 375, 450, 20);
      
      for(int i = 0; i < gameBoard.length; i++){
        for(int j = 0; j < gameBoard[i].length; j++){
          
          //Check for mouse location
          if(mouseX >= (gameBoard[i][j].getXPosition() - gameBoard[i][j].getSize()/2) 
             && mouseX <= (gameBoard[i][j].getXPosition() + gameBoard[i][j].getSize()/2) 
             && mouseY >= (gameBoard[i][j].getYPosition() - gameBoard[i][j].getSize()/2) 
             && mouseY <= (gameBoard[i][j].getYPosition() + gameBoard[i][j].getSize()/2)){

            // Change opacity to indicate selected square
            gameBoard[i][j].setOpacity(25);
            
            // Set square to active player / switch active player
            if(dc.isMouseButton(1) && timeout <= 0 && gameBoard[i][j].getState() == 0){
              gameBoard[i][j].setState(activePlayer);
              if(activePlayer == 0){
                activePlayer = 1;
              } else {
                activePlayer = 0;
              }
              timeout = 50;
            }
          } else if (gameBoard[i][j].getState() == 0){
            gameBoard[i][j].setOpacity(0);
          }
        }
      }  

      // Draw graphics for squares
      for(int i = 0; i < gameBoard.length; i++){
        for(int j = 0; j < gameBoard[i].length; j++){
          gameBoard[i][j].drawSquare();
        }
      }  

      this.checkWinner();

      timeout--;
      dc.redraw();
      dc.pause(10);
    }
    
  }

  public void checkWinner(){

    int p1Squares = 0;
    int p2Squares = 0;
    
    // --- Check Columns ---
    for(int i = 0; i < gameBoard.length; i++){
      p1Squares = 0;
      p2Squares = 0;
      
      for (int j = 0; j < gameBoard[i].length; j++){
        if(gameBoard[i][j].getState() == 1){
          p1Squares++;
        } else if(gameBoard[i][j].getState() == 2){
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
    for(int i = 0; i < gameBoard.length; i++){
      p1Squares = 0;
      p2Squares = 0;
      
      for (int j = 0; j < gameBoard[i].length; j++){
        if(gameBoard[j][i].getState() == 1){
          p1Squares++;
        } else if(gameBoard[j][i].getState() == 2){
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
    for(int i = 0; i < gameBoard.length; i++){
      if(gameBoard[i][i].getState() == 1){
        p1Squares++;
      } else if(gameBoard[i][i].getState() == 2){
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
    for(int i = 0, j = 2; i < gameBoard.length; i++, j--){
      if(gameBoard[i][j].getState() == 1){
        p1Squares++;
      } else if(gameBoard[i][j].getState() == 2){
        p2Squares++;
      }
    }

    // Check for 3 in a row
    if(p1Squares == 3){
      super.winner = 1;
    } else if (p2Squares == 3){
      super.winner = 2;
    }

    
    // Set game winner / End game
    if(super.winner != 0){
      System.out.println(super.winner);
      gameOver = true;
    }
  }  
  
}