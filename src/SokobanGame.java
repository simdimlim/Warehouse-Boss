import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class SokobanGame {
	
	Grid grid;
	Player p;
	Box b;
	Goal g;
	
	public SokobanGame(Grid grid) {
		this.grid = grid;
		p = new Player(new Point(1,1));
		b = new Box(new Point(1,2));
		g = new Goal(new Point(1,5));
	}
	public static void main(String[] args) throws java.io.IOException {
		Grid g = new Grid(3, 7);
		try {
			g.generateLevel(new FileReader("level1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SokobanGame game = new SokobanGame(g);
		game.printGame(g);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
	        String n = scan.next();
	        System.out.println(n);
	        switch(n){
			case "w":
				Direction up = Direction.UP;
		    	game.p.move(up, g);
		    	game.b.move(up, g);
		    	break;
			case "s":
				Direction down = Direction.DOWN;
		    	game.p.move(down, g);
		    	game.b.move(down, g);
		    	break;
			case "a":
				Direction left = Direction.LEFT;
		    	game.p.move(left, g);
		    	game.b.move(left, g);
		    	break;
			case "d":
				Direction right = Direction.RIGHT;
		    	game.p.move(right, g);
		    	game.b.move(right, g);
		    	break;
			}
	        game.printGame(g);
		    if ((game.b.getCurrentLocation().getX()==game.g.getCurrentLocation().getX()) && (game.b.getCurrentLocation().getY()==game.g.getCurrentLocation().getY())){
		    	System.out.println("You win!");
		    	break;
		    }
		}
	}
	
	public void printGame(Grid grid) {
		SokobanObject[][] g = grid.getGrid();
		for (int i = 0; i < grid.getNumRow(); i++) {
			for (int j = 0; j < grid.getNumCol(); j++) {
				String type = g[i][j].getType();
				String obj = getObjectAtPoint(new Point(i,j));
				if (obj != null) {
					System.out.print(obj);
					continue;
				}
				if (type.equals("W")) {
					System.out.print("#");
				} else if (type.equals("F")) {
					System.out.print(" ");
				}
				
			}
			System.out.println();
		}
	}
	
	public String getObjectAtPoint(Point p) {
		if (this.p.getCurrentLocation().equals(p)) {
			return this.p.getType();
		} else if (this.b.getCurrentLocation().equals(p)) {
			return this.b.getType();
		} else if (this.g.getCurrentLocation().equals(p)) {
			return this.g.getType();
		}
		return null;
	}
}
