import DLibX.*;
import java.awt.Color;
import java.util.Random;

public class Dino extends baseGame {

	private DConsole dc;
	private Player player1;
	private Player player2;

	public Dino(DConsole dc, Player p1, Player p2) {
		this.player1 = p1;
		this.player2 = p2;
		this.dc = dc;
	}

	public void initialize() {
		this.player1.setX(100);
		this.player2.setX(100);
		this.player1.setY(250);
		this.player2.setY(550);
		this.run();
	}

	public void run() {

		dc.setOrigin(DConsole.ORIGIN_CENTER);
		dc.setPaint(new Color(58, 206, 250));
		this.dc.fillRect(450, 300, 1000, 700);
	}
}