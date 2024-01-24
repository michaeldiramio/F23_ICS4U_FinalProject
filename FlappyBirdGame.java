import DLibX.DConsole;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class FlappyBirdGame extends BaseGame {
  // dc with players
  private DConsole dc;
  private Player p1;
  private Player p2;

  // constructor
  public FlappyBirdGame(DConsole dc, Player p1, Player p2) {
    this.dc = dc;
    this.p1 = p1;
    this.p2 = p2;
  }

  // game initializing
  public void initialize() {
    System.out.println("Flappy Bird is running...");

    // font setup
    dc.setFont(new Font("Arial", Font.BOLD, 20));

    // start screen display
    dc.clear();
    dc.drawString("Flappy Bird Game", 450, 150);
    dc.drawString("Two players game: to jump press W(player 1) and Arrow(player 2)", 450, 200);
    dc.drawString("Press 'w' or up arrow to start", 450, 250);
    dc.redraw();

    // starting game input
    while (!dc.isKeyPressed('w') && !p2.upPressed()) {
      dc.pause(20);
    }

    run();
  }

  // Run the game for multiple rounds
  public void run() {
    int player1Wins = 0;
    int player2Wins = 0;

    for (int run = 1; run <= 5; run++) {
      System.out.println("Run " + run);
      boolean player1Won = runRound();
      if (player1Won) {
        player1Wins++;
      } else {
        player2Wins++;
      }
    }

    // Determine the overall winner
    int overallWinner = (player1Wins > player2Wins) ? 1 : 2;

    // font setup
    dc.setFont(new Font("Arial", Font.BOLD, 20));

    // displaying
    dc.clear();
    dc.drawString("Game Over!", 450, 190);
    dc.drawString("Player " + overallWinner + " wins!", 450, 240);
    dc.redraw();
    dc.pause(5000);
  }

  // Run a single game
  private boolean runRound() {
    // variables
    boolean playing = true;
    int bird1Y = dc.getHeight() / 2;
    int bird1Velocity = 0;
    int bird2Y = dc.getHeight() / 2;
    int bird2Velocity = 0;
    int gravity = 1;
    int jumpStrength = -12;
    int obstacleX = dc.getWidth();
    int obstacleWidth = 50;
    int gapHeight = 300;
    int bird1Radius = 20;
    int bird2Radius = 20;
    int obstacleSpeed = 6; // Adjust obstacle speed

    // text font setting
    dc.setFont(new Font("Dialog", Font.BOLD, 30));

    while (playing) {
      // Player input
      if (dc.isKeyPressed('w')) {
        bird1Velocity = jumpStrength;
      }

      if (p2.upPressed()) {
        bird2Velocity = jumpStrength;
      }

      // Game logic
      bird1Velocity += gravity;
      bird1Y += bird1Velocity;

      bird2Velocity += gravity;
      bird2Y += bird2Velocity;

      obstacleX -= obstacleSpeed; // Adjust obstacle speed

      // Check collision
      int obstacleHeight = dc.getHeight() - gapHeight;

      if (bird1Y < 0 || bird1Y + bird1Radius > dc.getHeight()) {
        playing = false; // Game over if bird1 hits top or bottom
      }

      if (bird2Y < 0 || bird2Y + bird2Radius > dc.getHeight()) {
        playing = false; // Game over if bird2 hits top or bottom
      }

      if ((obstacleX < bird1Radius && obstacleX + obstacleWidth > 0)) {
        playing = false;
      }

      if ((obstacleX < bird2Radius && obstacleX + obstacleWidth > 0)) {
        playing = false;
      }

      // Drawing
      dc.clear();
      dc.setPaint(Color.RED);
      dc.fillEllipse(50, bird1Y, 40, 40); // Bird1
      dc.setPaint(Color.BLUE);
      dc.fillEllipse(50, bird2Y, 40, 40); // Bird2
      dc.setPaint(Color.BLACK);
      dc.fillRect(obstacleX, 0, obstacleWidth, obstacleHeight); // Upper part of obstacle
      dc.fillRect(obstacleX, obstacleHeight + gapHeight, obstacleWidth, dc.getHeight()); // Lower part of obstacle
      dc.fillRect(obstacleX + 500, 0, obstacleWidth, obstacleHeight); // Upper part of obstacle
      dc.fillRect(obstacleX + 500, obstacleHeight + gapHeight, obstacleWidth, dc.getHeight()); // Lower part of obstacle

      // Update display
      dc.redraw();
      dc.pause(20);
    }

    // Determine the winner of this game
    int a = bird1Y + bird1Radius;
    int b = bird2Y + bird2Radius;
    boolean player1Won = (a < b);

    if (player1Won) {
      System.out.println("Player 1 wins!");
      super.winner = 1;
    } else {
      System.out.println("Player 2 wins!");
      super.winner = 2;
    }

    dc.pause(1000);

    return player1Won;
  }
}
