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
  int p1Count = 3;
		int ballSliderY = 60;
		int p2Count = 3;
		int ballSliderY2 = 60;
		Random luck = new Random();
		int random = luck.nextInt(20) + 1;
		boolean playing = true;
		while(start){
			
   int mouseX = dc.getMouseXPosition();
		 int mouseY = dc.getMouseYPosition();
			
			dc.setOrigin(DConsole.ORIGIN_CENTER);
		dc.setPaint(new Color(58, 206, 250));
		this.dc.fillRect(450, 300, 1000, 700);

			dc.setOrigin(DConsole.ORIGIN_LEFT);

			dc.setPaint(Color.WHITE);
			int[] bgx = {450, 525, 525, 450, 375, 375};
			int[] bgy = {338, 390, 505, 560, 505, 390};
			dc.fillPolygon(bgx, bgy);
		
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
			dc.setOrigin(DConsole.ORIGIN_CENTER);



			dc.drawLine(150, 60, 150, 460);
			if(mouseX <= 200
					&& mouseX >= 100
					&& mouseY >= 60
					&& mouseY <= 460
					&& dc.isMouseButton(1)) {
							ballSliderY = mouseY;
					}
			dc.fillEllipse(150, ballSliderY, 20, 20);
			dc.drawString(p1Count, 180, ballSliderY);
			p1Count = (ballSliderY - 40) / 20 ;  //1-20

			dc.drawLine(750, 60, 750, 460);
			if(mouseX <= 780
					&& mouseX >= 650
					&& mouseY >= 60
					&& mouseY <= 460
					&& dc.isMouseButton(1)) {
							ballSliderY2 = mouseY;
					}
			dc.fillEllipse(750, ballSliderY2, 20, 20);
			dc.drawString(p2Count, 720, ballSliderY2);
			p2Count = (ballSliderY2 - 40) / 20 ;  //1-20

		 dc.drawString("Player One", 150, 30);
			dc.drawString("Player Two", 750, 30);

			dc.setPaint(Color.GREEN);
			dc.fillRect(450, 100, 100, 50);
			dc.setPaint(Color.BLACK);
			dc.drawString("Start", 450, 100);
			if(mouseX <= 550
					&& mouseX >= 350
					&& mouseY >= 50
					&& mouseY <= 150
					&& dc.isMouseButton(1)){
				p1Count = Math.abs(p1Count - random);
				p2Count = Math.abs(p2Count - random);
				while(playing){
					dc.setPaint(Color.WHITE);
		  	dc.drawString(random, 450,350);
					dc.redraw();
					dc.setPaint(Color.BLACK);
					if(p1Count == random){
						super.winner = 1;
						dc.drawString("Player One Wins", 450 , 200);
						dc.redraw();
						dc.pause(5000);
						start = false;
						playing = false;
					} else if (p2Count == random){
						super.winner = 2;
						dc.drawString("Player Two Wins", 450, 200);
						dc.redraw();
						dc.pause(5000);
						start = false;
						playing = false;
					} else {
						if(p1Count > p2Count){
							super.winner = 2;
							dc.drawString("Player Two Wins", 450, 200);
							dc.redraw();
							dc.pause(5000);
							start = false;
							playing = false;
						} else if (p1Count < p2Count){
							super.winner = 1;
							dc.drawString("Player One Wins", 450, 200);
							dc.redraw();
							dc.pause(5000);
							start = false;
							playing = false;
						}
					}
				}
				dc.redraw();
			}

			

			
			

			
		dc.redraw();
		}
	}

}