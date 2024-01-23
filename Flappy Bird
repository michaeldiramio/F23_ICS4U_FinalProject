import DLibX.DConsole;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class FlappyBirdGame extends baseGame {
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
  public void in() {
    System.out.println("Running");
    this.run();
  }

  // code
  public void run() {
    // variables
    boolean playing = true;
    int birdY = dc.getHeight() / 2;
    int birdVelocity = 0;
    int gravity = 1;
    int jumpStrength = -12;
    int obstacleX = dc.getWidth();
    int obstacleWidth = 50;
    int obstacleGap = 150;

    // text font setting
    dc.setFont(new Font("Dialog", Font.BOLD, 30));

    while (playing) {
      // Player input
      if (dc.isKeyPressed(KeyEvent.VK_SPACE)) {
        birdVelocity = jumpStrength;
      }

      // Game logic
      birdVelocity += gravity;
      birdY += birdVelocity;

      obstacleX -= 5;

      // Check collision
      int birdRadius = 20;
      int obstacleHeight = dc.getHeight() - obstacleGap;

      if (birdY < 0 || birdY + birdRadius > dc.getHeight()) {
        playing = false; // Game over if bird hits top or bottom
      }

      if (obstacleX + obstacleWidth < 0) {
        obstacleX = dc.getWidth();
      }

      if (birdY < obstacleHeight || birdY + birdRadius > obstacleHeight + obstacleGap) {
        if (obstacleX < birdRadius && obstacleX + obstacleWidth > 0) {
          playing = false; // Game over if bird hits obstacle
        }
      }

      // Drawing
      dc.clear();
      dc.setPaint(Color.BLACK);
      dc.fillEllipse(50, birdY, 40, 40); // Bird
      dc.fillRect(obstacleX, 0, obstacleWidth, dc.getHeight() - obstacleGap); // Obstacle
      dc.fillRect(obstacleX, dc.getHeight() - obstacleGap + obstacleGap, obstacleWidth, dc.getHeight());

      // Update display
      dc.redraw();
      dc.pause(20);
    }

    // Game over screen
    dc.clear();
    dc.drawString("Game Over!", dc.getWidth() / 2, dc.getHeight() / 2);
    dc.redraw();
    dc.pause(1000);
  }
}
