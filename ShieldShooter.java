import DLibX.DConsole;
import java.io.File;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;

//ShieldShooter game
public class ShieldShooter extends baseGame {
  DConsole dc;
  private Player p1;
  private Player p2;

  //Constructor
  public ShieldShooter(DConsole dc, Player p1, Player p2){
    this.p1 = p1;
    this.p2 = p2;
    this.dc = dc;
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

    //Variables
    int game = 1;
    int ctDown = 1500;
    int befplay = 0;
    int p1Score = 0;
    int p2Score = 0;
    double paddley = 300; //Left paddle
    double paddley2 = 300;//Right paddle
    double paddleyH = 75; //Paddle 1 height
    double paddley2H = 75; //Paddle 2 height
    long startTime1 = 0;
    long elapsedTime1 = 0;
    long startTime2 = 0;
    long elapsedTime2 = 0;


    //Game begins
    while(game == 1){
      dc.clear();

      //before play instructions
      while(befplay == 0) {
        dc.clear();
        ctDown = 1500;
        p1Score = 0;
        p2Score = 0;
        dc.drawString(("Shield Shooter. Hit enemy wall to gain score"), 450, 120);
        dc.drawString(("Deflect with your own shield"), 450, 190);
        dc.drawString(("IMPORTANT: avoid staying in the gas (alone) for too long!"), 450, 260);
        dc.drawString(("Press 'space' to play"), 450, 330);
        if(dc.isKeyPressed(32)) {
          befplay = 1;
        }
        dc.redraw();
        dc.pause(20);
      }
      
      //Background
      dc.setPaint(new Color(0, 5, 135));
      dc.fillRect(450, 300, 900, 600);

      //first paddle (neon blue)
      dc.setPaint(new Color(16, 168, 255)); //Color for the paddles, ball and scores. 
      
      //Player 1 score
      dc.drawString("Player 1: " + p1Score, 150, 20); 
      //Player 2 score
      dc.drawString("Player 2: " + p2Score, 750, 20); 
    
      dc.fillRect(20, paddley, 15, paddleyH);//Paddle 1
      dc.fillRect(450, paddley, 860, 15);//beam 1
      
      dc.setPaint(Color.RED);//change to red
      
      dc.fillRect(880, paddley2, 15, paddley2H); //Paddle 2
      dc.fillRect(450, paddley2, 860, 15);

      dc.setPaint(Color.GREEN); //switch to green
      dc.drawLine(0, 200, 900, 200);
      dc.drawLine(0, 400, 900, 400);

      dc.setPaint(new Color(118, 223, 73, 155));
      dc.fillRect(450, 100, 900, 200);
      dc.fillRect(450, 500, 900, 200);

      dc.setPaint(new Color(16, 168, 255)); //back to og colour
  
  
        // -------- MOVE STUFF -----------
  
      //Player 1 paddle movement up
      if(this.p1.upPressed()){ 
        paddley = paddley - 2;
      }
  
      //Player 1 paddle movement down
      if(this.p1.downPressed()){ 
        paddley = paddley +2;
      }
  
      //paddle 2 movement up
      if(this.p2.upPressed()){
        paddley2 = paddley2 - 2;
      }

      //paddle 2 movement down
      if(this.p2.downPressed()){
        paddley2 = paddley2 +2;
      }

      //Check the boundaries for both paddles
      
      if(paddley2 < 40){ //Boundaries
        paddley2 = 40;
      }
  
      if(paddley2 > 560){ //Boundaries
        paddley2 = 560; 
      } 
      
      if (paddley < 40){ //Boundries
        paddley = 40;
      }
  
      if (paddley > 560){ //Boundries
        paddley = 560;
      }

      //if in safe zone
      if(paddley > 200 && paddley < 400) {
        startTime1 = System.currentTimeMillis();
        elapsedTime1 = 0;
      }

      //if outside of safe zone
      if((paddley < paddley2 - 37.5 || paddley > paddley2 + 37.5) && (paddley < 200 || paddley > 400)) {
        p1Score ++;
        ctDown --;
        elapsedTime1 = System.currentTimeMillis();
        
        //if in gas for too long (7 secs)
        if(elapsedTime1 >= startTime1 + 7000){
          dc.clear();
          dc.drawString(("Player 2 wins!"), 450, 300);
          dc.redraw();
          dc.pause(1500);
          super.winner = 2;
          game = -1;
        }
        dc.redraw();
        dc.pause(20);
      }

      //if in safe zone
      if(paddley2 > 200 && paddley2 < 400) {
        startTime2 = System.currentTimeMillis();
        elapsedTime2 = 0;
      }

      //if outside of safe zone
      if((paddley2 < paddley - 37.5 || paddley2 > paddley + 37.5) && (paddley2 < 200 || paddley2 > 400)) {
        p2Score ++;
        ctDown --;
        elapsedTime2 = System.currentTimeMillis();
        
        //if in gas for too long (7 secs)
        if(elapsedTime2 >= startTime2 + 7000){
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


      //when countdown ends
      if(ctDown <= 0) {
        //Find Winner
        if(p1Score > p2Score){ 
          game = -1;
          dc.clear();
          dc.drawString("Player 1 won!", 450, 300);
          dc.redraw();
          dc.pause(1500);
          super.winner = 1;
        } else if (p2Score >= 10){
          game = -1;
          dc.clear();
          dc.drawString("Player 2 won!", 450, 300);
          dc.redraw();
          dc.pause(1500);
          super.winner = 2;
        } else {
          dc.clear();
          dc.drawString("Tie", 450, 300);
          dc.redraw();
          dc.pause(1500);
          befplay = 0;
        }
      }

      //countdown subtracts
      ctDown --;
      dc.redraw();
      dc.pause(20);
    }

    dc.redraw();
    dc.pause(20);
  }
} 