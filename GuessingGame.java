import java.util.*;
import java.util.Scanner;
import java.util.Random;
import DLibX.DConsole;

public class GuessingGame extends baseGame {
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

      Scanner userIn = new Scanner(System.in);

      System.out.println("Player 2 look away while Player 1 picks the key");

      String Key = userIn.nextLine();

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