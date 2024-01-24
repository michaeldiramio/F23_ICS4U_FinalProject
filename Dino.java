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
		boolean start = true;
		double[] cactus = new double[4];// add cactus
		cactus[0] = 800;
		cactus[1] = 1600;
		cactus[2] = 2400;
		cactus[3] = 3200;
		int counter = 3;
		Random random = new Random();// for random catus placement
		long time = System.currentTimeMillis();// timmer
		double speed = 2;
		boolean jump = false;// to jump
		double dinoY = 230;// make dino go up
		double YChange = 0;

		double[] cactus2 = new double[4];// add cactus
		cactus2[0] = 700;
		cactus2[1] = 1500;
		cactus2[2] = 2300;
		cactus2[3] = 3100;
		int counter2 = 3;
		Random random2 = new Random();// for random catus placement
		long time2 = System.currentTimeMillis();// timmer
		boolean jump2 = false;// to jump
		double dinoY2 = 560;// make dino go up
		double YChange2 = 0;

		while (start) {
			dc.setOrigin(DConsole.ORIGIN_CENTER);
			this.dc.drawImage("gameMap/background.png", 450, 300);// background
			dc.setPaint(Color.BLACK);// divider line
			dc.fillRect(450, 275, 1000, 10);
			dc.setPaint(new Color(57, 97, 40));
			dc.fillRect(450, 265, 1000, 10);// top grass
			dc.fillRect(450, 595, 1000, 10);// bottom grass
			dc.setPaint(new Color(6, 84, 27));// dino
			dc.fillRect(120, dinoY, 20, 60);// dino

			if (time >= 5000 + System.currentTimeMillis()) {// timmer
				speed += 0.5;// counter to up the speed
				time = System.currentTimeMillis();
			}

			dc.setPaint(new Color(57, 97, 40));// many cactus
			for (int i = 0; i <= cactus.length - 1; i++) {
				dc.fillRect(cactus[i], 245, 10, 30);
				cactus[i] -= speed;
			}

			if (cactus[0] == -20) { // setting cactus back to emd without having them to close to eachother
				cactus[0] = cactus[counter] + random.nextInt(500) + 250;
				counter = 0;
			}
			if (cactus[1] == -20) {
				cactus[1] = cactus[counter] + random.nextInt(500) + 250;
				counter = 1;
			}
			if (cactus[2] == -20) {
				cactus[2] = cactus[counter] + random.nextInt(500) + 250;
				counter = 2;
			}
			if (cactus[3] == -20) {
				cactus[3] = cactus[counter] + random.nextInt(500) + 250;
				counter = 3;
			}
			if (counter > 3) {
				counter = 0;
			}

			if (!jump && player1.upPressed()) {// code to jump
				jump = true;
				YChange = -15;
			}
			if (YChange > 0) {
				YChange -= 0.5;
			} else if (YChange < 0) {
				YChange += 0.5;
			} else if (YChange <= 1.5 && YChange > -1.5 && dinoY < 230) {
				YChange = 14.5;
			} else {
				jump = false;
			}
			dinoY += YChange;

			if (start = true) {
				for (int i = 0; i < cactus.length - 1; i++) {// code for game end
					if (cactus[i] <= 130 && cactus[i] >= 110 && dinoY + 30 > 230) {
						super.winner = 2;
						dc.setPaint(Color.BLACK);
						dc.clear();
						dc.drawString("Player 2 Wins", 450, 300);
						dc.redraw();
						dc.pause(5000);
						start = false;
					}
				}
			}

			dc.setPaint(new Color(6, 84, 27));// dino
			dc.fillRect(120, dinoY2, 20, 60);// dino

			dc.setPaint(new Color(57, 97, 40));// many cactus
			for (int i = 0; i <= cactus2.length - 1; i++) {
				dc.fillRect(cactus2[i], 575, 10, 30);
				cactus2[i] -= speed;
			}

			if (cactus[0] == -20) { // setting cactus back to emd without having them to close to eachother
				cactus2[0] = cactus2[counter2] + random.nextInt(500) + 250;
				counter2 = 0;
			}
			if (cactus[1] == -20) {
				cactus2[1] = cactus2[counter2] + random.nextInt(500) + 250;
				counter2 = 1;
			}
			if (cactus[2] == -20) {
				cactus2[2] = cactus2[counter2] + random.nextInt(500) + 250;
				counter2 = 2;
			}
			if (cactus[3] == -20) {
				cactus2[3] = cactus2[counter2] + random.nextInt(500) + 250;
				counter2 = 3;
			}
			if (counter2 > 3) {
				counter2 = 0;
			}

			if (!jump2 && player2.upPressed()) {// code to jump
				jump2 = true;
				YChange2 = -15;
			}
			if (YChange2 > 0) {
				YChange2 -= 0.5;
			} else if (YChange2 < 0) {
				YChange2 += 0.5;
			} else if (YChange2 <= 1.5 && YChange2 > -1.5 && dinoY2 < 560) {
				YChange2 = 14.5;
			} else {
				jump2 = false;
			}
			dinoY2 += YChange2;

			if (start = true) {
				for (int i = 0; i < cactus.length - 1; i++) {// code for game end
					if (cactus2[i] <= 130 && cactus2[i] >= 110 && dinoY2 + 30 > 560) {
						dc.clear();
						super.winner = 1;
						dc.setPaint(Color.BLACK);
						dc.drawString("Player 1 Wins", 450, 300);
						dc.redraw();
						dc.pause(5000);
						start = false;
					}
				}
			}

			dc.pause(20);
			dc.redraw();
		}

	}
}