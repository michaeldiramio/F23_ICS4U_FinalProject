import DLibX.DConsole;
import java.io.File;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MasherGame extends baseGame {
  //dc and 2 players
  DConsole dc;
  private Player p1;
  private Player p2;

  //Constructor
  public MasherGame(DConsole dc, Player p1, Player p2){
    this.p1 = p1;
    this.p2 = p2;
    this.dc = dc;
  }

  //initialize the game
  public void initialize (){
    System.out.println("running");
    this.run();
  }

  //Game code begins
  public void run(){
    dc.clear();
    //fonts
    String fonts[] =   GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    for (int i = 0; i < fonts.length; i++) {
      System.out.println(fonts[i]);
    }
    dc.setFont(new Font("Dialog", Font.ITALIC, 20));

    //variables      
    int ctDown = 1000;//countdown
    int game = 1;//game
    int befplay = 0;//before play
    int p1Score = 0;//player 1 score
    int p2Score = 0;//player 2 score

    //game
    while(game == 1){  
      //instructions
      while(befplay == 0){
        ctDown = 1000;
        p1Score = 0;
        p2Score = 0;
        dc.drawString(("Mash your respectful buttons but be careful of holding them down for too long ;)"), 450, 200);
        dc.drawString(("When ready to play, press 'b'"), 450, 400);
        if(dc.isKeyPressed(66)){
          dc.clear();
          befplay = -1;
        }
  
  
        dc.pause(20);
        dc.redraw();
      }
      //Game has begun/countdown is going down
      dc.setFont(new Font("Dialog", Font.ITALIC, 15));
      //draw the scores
      dc.clear();
      dc.drawString(("Player 1: W"), 170, 50);
      dc.drawString(("Player 2: UA"), 730, 50);
      dc.drawString(("Player 1 score: " + p1Score), 170, 80);
      dc.drawString(("Player 2 score: " + p2Score), 730, 80);

      //if 'w' is pressed
      int ctDownRec = ctDown;//record the countdwn
      while(this.p1.upPressed()) {
        ctDown --;
        p1Score ++;
        if(ctDown == ctDownRec - 50) { //if they hold it for too long, other player wins
          dc.clear();
          dc.drawString(("Player 2 wins!"), 450, 300);
          dc.redraw();
          dc.pause(1500);
          super.winner = 2;
          game = -1;
        }
        dc.pause(20);
        dc.redraw();
      }

      //if up arrow is pressed
      int ctDownRec2 = ctDown;//recorded countdown
      while(this.p2.upPressed()) {
        ctDown --;
        p2Score++;
        if(ctDown == ctDownRec2 - 50) {//if they hold it down for too long, other player wins
          dc.clear();
          dc.drawString(("Player 1 wins!"), 450, 300);
          dc.redraw();
          dc.pause(1500);
          super.winner = 1;
          game = -1;
        }
        dc.redraw();
        dc.pause(20);
      }

      //if countdown is equal to or less than 0, clear and find who won/tied
      if(ctDown <= 0){
        dc.clear();
        if(p1Score > p2Score) {
          dc.drawString(("Player 1 won!"), 450, 300);
          dc.redraw();
          dc.pause(1500);
          super.winner = 1;
          game = -1;
        } else if(p2Score > p1Score) {
          dc.drawString(("Player 2 won!"), 450, 300);
          dc.redraw();
          dc.pause(1500);
          super.winner = 2;
          game = -1;
        } else {
          dc.drawString(("Tie"), 450, 300);
          dc.redraw();
          dc.pause(1500);
          dc.clear();
          befplay = 0;
        }
      }
  
      System.out.println(ctDown);
    
      ctDown --;
      dc.pause(20);
      dc.redraw();
    }
  }
}