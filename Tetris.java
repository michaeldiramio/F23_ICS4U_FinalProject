/*import DLibX.*;
import java.awt.Color;
import java.util.Scanner;

public class Tetris extends baseGame {
	
	DConsole dc;
	private Player player1;
	private Player player2;

	public Tetris(DConsole dc, Player p1, Player p2){
			this.player1 = p1;
			this.player2 = p2;
			this.dc = dc;
	}
	Scanner userIn = new Scanner(System.in);

	public void initialize(){
		this.player1.setX(0);
		this.player2.setX(0);
		this.player1.setY(0);
		this.player2.setY(0);
		this.run();
	}

	
	


public void run(){
	dc.setPaint(Color.BLACK);
this.dc.filllRect(1000,700,450,300);
	
	Color[] random = new Color[6];
	Color Red = new Color(255,0,0);
	Color Green = new Color(0,255,0);
	Color Blue = new Color(0,0,255);
	Color Yellow = new Color(209,203,13);
	Color White = new Color(255,255,255);
	Color Black = new Color(0,0,0);

	random [0] = Red;
	random [1] = Green;
	random [2] = Blue;
	random [3] = Yellow;
	random [4] = White;
	random [5] = Black;
	dc.redraw();
}

}*/