import DLibX.DConsole;
import java.io.File;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;

//SpeedWord game
public class SpeedWord extends baseGame {
  DConsole dc;
  Random RNG = new Random();
  private Player player1;
  private Player player2;

  //Constructor
  public SpeedWord(DConsole dc, Player p1, Player p2, Random RNG){
    player1 = p1;
    player2 = p2;
    this.dc = dc;
    this.RNG = RNG;
  }

  //Initialize
  public void initialize (){
    System.out.println("running");
    this.run();
  }
  
  //Run the game code
  public void run(){
    
    //Import Fonts
    String fonts[] =   GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    //font
    for (int i = 0; i < fonts.length; i++) {
      System.out.println(fonts[i]);
    }
    dc.setFont(new Font("Dialog", Font.ITALIC, 20));
    
    //score/game resets
    int game = 1;
    int befplay = 0;
    int p1Score = 0;
    int p2Score = 0;
    
    //2D array of chars
    char[][] chrArr = new char[6][3];
    chrArr[0][0] = 'A';
    chrArr[0][1] = 'M';
    chrArr[0][2] = 'Y';
    chrArr[1][0] = 'G';
    chrArr[1][1] = 'S';
    chrArr[1][2] = 'E';
    chrArr[2][0] = 'B';
    chrArr[2][1] = 'N';
    chrArr[2][2] = 'Z';
    chrArr[3][0] = 'H';
    chrArr[3][1] = 'T';
    chrArr[3][2] = 'F';
    chrArr[4][0] = 'C';
    chrArr[4][1] = 'O';
    chrArr[4][2] = 'P';
    chrArr[5][0] = 'I';
    chrArr[5][1] = 'U';
    chrArr[5][2] = 'V';

    //randomly generate from the first three columns
    int p1chr = RNG.nextInt(3);
    //randomly generate from the 4, 5 and 6th column
    int p2chr = RNG.nextInt(3) + 3;
    

    //Game begins
    while(game == 1){
    dc.clear();
      
      //Instructions
      while(befplay == 0){
        p1chr = RNG.nextInt(3);
        p2chr = RNG.nextInt(3) + 3;
        dc.drawString(("Be the first one to hold down all of the letters of your word."), 450, 200);
         dc.drawString(("Best of 3"), 450, 300);
        dc.drawString(("When ready to play, press 'b'"), 450, 400);
        if(dc.isKeyPressed(66)){
          dc.clear();
          befplay = 1;
        }

        dc.pause(20);
        dc.redraw();
      }

      //6 letters Drawn
      dc.drawString((chrArr[p1chr][0]), 100, 50);
      dc.drawString((chrArr[p1chr][1]), 125, 50);
      dc.drawString((chrArr[p1chr][2]), 150, 50);
      dc.drawString((chrArr[p2chr][0]), 750, 50);
      dc.drawString((chrArr[p2chr][1]), 775, 50);
      dc.drawString((chrArr[p2chr][2]), 800, 50);

      //draw the player scores
      dc.drawString(("Player 1 score: " + p1Score), 125, 80);
      dc.drawString(("Player 2 score: " + p2Score), 775, 80);

      //if player 1 presses their char keys first, add score
      if(dc.isKeyPressed(chrArr[p1chr][0]) && dc.isKeyPressed(chrArr[p1chr][1]) && dc.isKeyPressed(chrArr[p1chr][2])) {
        dc.drawString(("Player 1 wins round"), 450, 300);
        p1Score ++;
        dc.redraw();
        dc.pause(1500);
        befplay = 0;
      } 
      
      if(dc.isKeyPressed(chrArr[p2chr][0]) && dc.isKeyPressed(chrArr[p2chr][1]) && dc.isKeyPressed(chrArr[p2chr][2])) { //if player 2 presses their char keys, add score
        dc.drawString(("Player 2 wins round"), 450, 300);
        p2Score ++;
        dc.redraw();
        dc.pause(1500);
        befplay = 0;
      }

      //if a player has a score of 2, make them the winner and go back to board
      if(p1Score == 2) {
        dc.clear();
        game = -1;
        dc.drawString(("Player 1 is the winner!"), 450, 300);
        dc.redraw();
        dc.pause(1500);
        super.winner = 1;
      } else if(p2Score == 2) {
        dc.clear();
        game = -1;
        dc.drawString(("Player 2 is the winner!"), 450, 300);
        dc.redraw();
        dc.pause(1500);
        super.winner = 2;
      }

      dc.pause(20);
      dc.redraw();
    }


    dc.pause(20);
    dc.redraw();
  }
}