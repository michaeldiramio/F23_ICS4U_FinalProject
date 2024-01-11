import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class TicTacToe extends baseGame {

  DConsole dc;
  Random randGen;
  private Player player1;
  private Player player2;
  private int activePlayer = 0;
  private boolean gameOver = false;

  private int[][] gameBoardValues = new int[3][3];
  private Color[][] gameBoardColor = new Color[3][3];
  
  
  public TicTacToe(DConsole dc, Random rng, Player p1, Player p2){
    this.dc = dc;
    this.randGen = rng;
    this.player1 = p1;
    this.player2 = p2;
    this.activePlayer = this.randGen.nextInt(2);
  }
  
  public void initialize (Player p1, Player p2){
    
  }
  
  public void run(){
    this.drawBackground();

    while(!gameOver){
     
      mouseX = dc.getMousePosition();
      mouseY = dc.getMousePosition();


      


      
      
    }
    
    
    
    
    
    dc.redraw();
  }

  public void checkForWinner(){
    
  }

  public void drawBackground(){
    
  }
  
}