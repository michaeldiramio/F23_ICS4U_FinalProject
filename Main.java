import DLibX.*;

public class Main {

	public static void main(String[] args) {
		DConsole dc = new DConsole(450, 300);
		dc.setOrigin(DConsole.ORIGIN_CENTER);
		
		dc.fillEllipse(225, 150, 50, 50);
		dc.redraw();
		
	}
	
}