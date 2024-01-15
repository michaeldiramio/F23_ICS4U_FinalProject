import DLibX.DConsole;
import java.io.File;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;

//Artem's RPS
public class RPS extends baseGame {
    DConsole dc;
    private Player player1;
    private Player player2;
    
    //Constructor
    public RPS(DConsole dc, Player p1, Player p2){
      player1 = p1;
      player2 = p2;
      this.dc = dc;
    }

    //Initialize
    public void initialize (){
      System.out.println("running");
      this.run();
    }

    //Run the game code
    public void run(){
    String fonts[] =   GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    for (int i = 0; i < fonts.length; i++) {
      System.out.println(fonts[i]);
    }
    dc.setFont(new Font("Dialog", Font.ITALIC, 20));
    //score resets
    int game = 0;
    int befplay = 0;
    int p1Score = 0;
    int p2Score = 0;

    //before game starts
    while(game == 0){
      dc.clear();
      dc.setPaint(new Color(15, 190, 209));
      dc.fillRect(225, 150, 900, 600);
      dc.setPaint(Color.BLUE);
      dc.drawString("Rock-Paper-Scissors (Best of 3)", 225, 75);
      dc.drawString("Press space to start", 225, 175);
      dc.setFont(new Font("Dialog", Font.BOLD, 20));
      if(dc.isKeyPressed(32)){
        game = 1;
        dc.clear();
      }
      dc.redraw();
      dc.pause(20);
    }

    //Game begins
    while(game == 1){

    //Instructions
    while(befplay == 0){
      dc.drawString(("Close your eyes and put your finger on the weapon key of your choice!"), 450, 200);
      dc.drawString(("When ready to play, press 'b'"), 450, 400);
      if(dc.isKeyPressed(66)){
        dc.clear();
        befplay = 1;
      }

      dc.pause(20);
      dc.redraw();
    }

    //RPS for Player 1 and 2
    int r1 = 82;
    int p1 = 80;
    int s1 = 83;
    int r2 = 37;
    int p2 = 38;
    int s2 = 39;
    dc.setFont(new Font("Dialog", Font.ITALIC, 15));
    dc.drawString(("Player 1 - Rock: 'r', Paper: 'p', Scissors: 's'"), 170, 50);
    dc.drawString(("Player 2 - Rock: LA, Paper: UA, Scissors: RA"), 730, 50);
    dc.drawString(("Player 1 score: " + p1Score), 170, 80);
    dc.drawString(("Player 2 score: " + p2Score), 730, 80);
    

    //If any of the RPS keys for players are pressed at the same time, find the winner
    if(dc.isKeyPressed(r1) && dc.isKeyPressed(r2)){
      dc.drawString(("ROCK"), 300, 300);
      dc.drawString(("ROCK"), 600, 300);
      dc.drawString(("TIE"), 450, 300);
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    } else if(dc.isKeyPressed(r1) && dc.isKeyPressed(p2)){
      dc.drawString(("ROCK"), 300, 300);
      dc.drawString(("PAPER"), 600, 300);
      dc.drawString(("Player 2 wins"), 450, 300);
      p2Score ++;
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    } else if(dc.isKeyPressed(r1) && dc.isKeyPressed(s2)){
      dc.drawString(("ROCK"), 300, 300);
      dc.drawString(("SCISSORS"), 600, 300);
      dc.drawString(("Player 1 wins"), 450, 300);
      p1Score ++;
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    } else if(dc.isKeyPressed(p1) && dc.isKeyPressed(r2)){
      dc.drawString(("PAPER"), 300, 300);
      dc.drawString(("ROCK"), 600, 300);
      dc.drawString(("Player 1 wins"), 450, 300);
      p1Score ++;
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    } else if(dc.isKeyPressed(p1) && dc.isKeyPressed(p2)){
      dc.drawString(("PAPER"), 300, 300);
      dc.drawString(("PAPER"), 600, 300);
      dc.drawString(("Tie"), 450, 300);
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    } else if(dc.isKeyPressed(p1) && dc.isKeyPressed(s2)){
      dc.drawString(("PAPER"), 300, 300);
      dc.drawString(("SCISSORS"), 600, 300);
      dc.drawString(("Player 2 wins"), 450, 300);
      p2Score ++;
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    } else if(dc.isKeyPressed(s1) && dc.isKeyPressed(r2)){
      dc.drawString(("SCISSORS"), 300, 300);
      dc.drawString(("ROCK"), 600, 300);
      dc.drawString(("Player 2 wins"), 450, 300);
      p2Score ++;
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    } else if(dc.isKeyPressed(s1) && dc.isKeyPressed(p2)){
      dc.drawString(("SCISSORS"), 300, 300);
      dc.drawString(("PAPER"), 600, 300);
      dc.drawString(("Player 1 wins"), 450, 300);
      p1Score ++;
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    } else if(dc.isKeyPressed(s1) && dc.isKeyPressed(s2)){
      dc.drawString(("SCISSORS"), 300, 300);
      dc.drawString(("SCISSORS"), 600, 300);
      dc.drawString(("TIE"), 450, 300);
      dc.redraw();
      dc.pause(1000);
      befplay = 0;
      dc.clear();
    }

    //If any of the players have a score of 2, end the game
    if(p1Score == 2 || p2Score == 2){
      dc.clear();
      game = -1; 
    }

    dc.pause(20);
    dc.redraw();
  }

    //if a player has a score of 2, make them the winner and go back to board
    if(p1Score == 2) {
      dc.drawString(("Player 1 is the winner!"), 450, 300);
      super.winner = 1;
    } else if(p2Score == 2) {
      dc.drawString(("Player 2 is the winner!"), 450, 300);
      super.winner = 2;
    }

    dc.pause(20);
    dc.redraw();
  }
}