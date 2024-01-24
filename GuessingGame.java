import java.util.*;
import java.util.Scanner;
import java.util.Random;
import DLibX.DConsole;

public class GuessingGame extends BaseGame {
  DConsole dc;
  private Player p1;
  private Player p2;

  public GuessingGame(DConsole dc, Player p1, Player p2){
     this.p1 = p1;
     this.p2 = p2;
     this.dc = dc;
  }
  
  public void run(){
    int loop = 1;
    while (loop == 1){
      this.dc.clear();
      this.dc.drawString("Look at the Console Below!", 450, 300);
      this.dc.redraw();
      System.out.print("\033[H\033[2J");
      System.out.flush();

      Scanner userIn = new Scanner(System.in);

      System.out.println("Player 2 look away while Player 1 picks the key");

      String Key = userIn.nextLine();
      System.out.print("\033[H\033[2J");
      System.out.flush();

      System.out.println("Player 1, inform Player 2 that it is his turn to guess");

      String Guess = userIn.nextLine();

      if(Key.equals(Guess)){
        System.out.println("Player 2 Wins");
        super.winner = 2;
        loop = 0;
      }

      if(!Key.equals(Guess)){
        System.out.println("Player 1 Wins");
        super.winner = 1;
        loop = 0;
    }
      
  }
  }

  public void initialize() {
    
    this.run();
  }
}