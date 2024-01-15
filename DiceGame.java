import DLibX.*;
import java.awt.Color;
import java.util.Random;

public class DiceGame extends baseGame {

	private DConsole dc;
	private Player player1;
	private Player player2;

	public DiceGame (DConsole dc, Player p1, Player p2) {
		this.player1 = p1;
		this.player2 = p2;
		this.dc = dc;
	}

	public void initialize() {
		this.player1.setX(0);
		this.player2.setX(0);
		this.player1.setY(0);
		this.player2.setY(0);
		this.run();
	}

	public void run() {
		boolean start = true;
		while(start){

			int ballCount = 3;
			int ballSliderY = 60;
			
			dc.drawLine(400, 60, 400, 260);
			if(mouseX <= 420
					&& mouseX >= 380
					&& mouseY >= 60
					&& mouseY <= 260
					&& dc.isMouseButton(1)) {
							ballSliderY = mouseY;
					}
			dc.fillEllipse(400, ballSliderY, 20, 20);
			dc.drawString(ballCount, 430, ballSliderY);
			ballCount = (ballSliderY - 40) / 20;  //1-11

			dc.setOrigin(DConsole.ORIGIN_CENTER);
		dc.setPaint(new Color(58, 206, 250));
		this.dc.fillRect(450, 300, 1000, 700);

			dc.setOrigin(DConsole.ORIGIN_LEFT);
			dc.setPaint(Color.BLACK);
			int[] xs = {400, 450, 500};
			int[] ys = {450, 325, 450};
			dc.fillPolygon(xs, ys);

			int[] x2s = {450, 525, 525, 450, 375, 375};
			int[] y2s = {225, 280, 395, 450, 395, 280};
			dc.drawPolygon(x2s, y2s);

			int[] x3s = {450, 500, 525};
			int[] y3s = {264, 387, 280};
			dc.drawPolygon(x3s, y3s);

			int[] x4s = {375, 400, 450};
			int[] y4s = {280, 387, 264};
			dc.drawPolygon(x4s, y4s);

			int[] x5s = {400, 500, 450};
			int[] y5s = {387, 387, 450};
			dc.drawPolygon(x5s, y5s);

			int[] x6s = {400, 375};
			int[] y6s = {387, 395};
			dc.drawPolygon(x6s, y6s);

			int[] x7s = {500, 525};
			int[] y7s = {387, 395};
			dc.drawPolygon(x7s, y7s);

			int[] x8s = {450, 450};
			int[] y8s = {225, 300};
			dc.drawPolygon(x8s, y8s);

			

			
		dc.redraw();
		}
	}

}