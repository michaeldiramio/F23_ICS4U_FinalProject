import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
    DConsole dc = new DConsole(900, 600);
    ArrayList<baseGame> games = new ArrayList<baseGame>();
    ArrayList<Player> players = new ArrayList<Player>();
    Random rnd = new Random();
		dc.setOrigin(DConsole.ORIGIN_CENTER);

    
    players.add(new Player(dc, 87, 65, 83, 68, 69));
    players.add(new Player(dc, 38, 37, 40, 39, 13));


    CircleGame c = new CircleGame (dc, players.get(0), players.get(1));
		dc.fillEllipse(225, 150, 50, 50);
		dc.redraw();

    c.run();
    

    
	}
	
}