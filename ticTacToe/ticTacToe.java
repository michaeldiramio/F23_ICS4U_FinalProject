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

  private gridSquare[][] gameBoard = new gridSquare[3][3];
  
  public ticTacToe(DConsole dc, Random rng, Player p1, Player p2){
    this.dc = dc;
    this.randGen = rng;
    this.player1 = p1;
    this.player2 = p2;
  }
  
  public void initialize (){
    this.activePlayer = this.randGen.nextInt(2);
    int xPosition = 200;
    int yPosition = 200;
    for(int i = 0; i < gameBoard.length; i++){
      for(int j = 0; j < gameBoard[i].length; j++){
        yPosition += (j * yPosition);
        gameBoard[i][j] = new gridSquare(dc, xPosition, yPosition, 200);
      }
      xPosition += (i * xPosition);
    }

    this.run();
  }
  
  public void run(){
    this.drawBackground();

    while(!gameOver){
     
      int mouseX = dc.getMouseXPosition();
      int mouseY = dc.getMouseYPosition();

      for(int i = 0; i < gameBoard.length; i++){
        for(int j = 0; j < gameBoard[i].length; j++){
          if(mouseX >= (gameBoard[i][j].getXPosition() - gameBoard[i][j].getSize()/2) 
             && mouseX <= (gameBoard[i][j].getXPosition() + gameBoard[i][j].getSize()/2) 
             && mouseY >= (gameBoard[i][j].getYPosition() - gameBoard[i][j].getSize()/2) 
             && mouseY <= (gameBoard[i][j].getYPosition() + gameBoard[i][j].getSize()/2)){
            gameBoard[i][j].changeOpacity();
            if(dc.isMousePressed){
              gameBoard[i][j].setState(activePlayer);
            }
          }
        }
      }      
      
    }
    
    
    
    
    
    dc.redraw();
  }

  public void checkWinVertical(){
    
  }

  public void checkWinHorizontal(){

  }

  public void checkWinDiagonal(){

  }

  public void drawBackground(){
    
  }
  
  
}