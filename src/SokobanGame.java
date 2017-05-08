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
		p = new Player(3,1);
		b = new Box(3,2);
		g = new Goal(2,8);
	}
	public static void main(String[] args) throws java.io.IOException {
		Direction up = Direction.UP;
		Direction down = Direction.DOWN;
		Direction left = Direction.LEFT;
		Direction right = Direction.RIGHT;
		
		Grid g = new Grid(6, 15);
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
				if (game.p.checkValidMove(up, game.grid)){
			    	game.p.move(up, g);
			    	if(game.canPush(game.p,game.b,up)){
			    		game.b.move(up, g);
			    	} 
			    	if(game.isPushing(game.p,game.b)){
			    		game.p.move(down, g);
			    	}
				}
		    	break;
			case "s":
				if (game.p.checkValidMove(down, game.grid)){
			    	game.p.move(down, g);
			    	if(game.canPush(game.p,game.b,down)){
			    		game.b.move(down, g);
			    	} 
			    	if(game.isPushing(game.p,game.b)){
			    		game.p.move(up, g);
			    	}
				}
		    	break;
			case "a":
				if (game.p.checkValidMove(left, game.grid)){
			    	game.p.move(left, g);
			    	if(game.canPush(game.p,game.b,left)){
			    		game.b.move(left, g);
			    	} 
			    	if(game.isPushing(game.p,game.b)){
			    		game.p.move(right, g);
			    	}
				}
		    	break;
			case "d":
				if (game.p.checkValidMove(right, game.grid)){
			    	game.p.move(right, g);
			    	if(game.canPush(game.p,game.b,right)){
			    		game.b.move(right, g);
			    	} 
			    	if(game.isPushing(game.p,game.b)){
			    		game.p.move(left, g);
			    	}
				}
		    	break;
			}
	        game.printGame(g);
		    if ((game.b.x()==game.g.x()) && (game.b.y()==game.g.y())){
		    	System.out.println("You win!");
		    	break;
		    }
		}
	}
	
	public void printGame(Grid grid) {
		SokobanObject[][] g = grid.getGrid();
		for (int i = 0; i < grid.getNumRow(); i++) {
			for (int j = 0; j < grid.getNumCol(); j++) {
				SokobanObject type = g[i][j];
				SokobanObject obj = getObjectAtPoint(i, j);
				
				if (obj instanceof Player) {
					System.out.print("P");
					continue;
				} else if (obj instanceof Box) {
					System.out.print("B");
					continue;
				} else if (obj instanceof Goal) {
					System.out.print("G");
					continue;
				}
				
				if (type instanceof Wall) {
					System.out.print("#");
				} else if (type instanceof Floor) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	public SokobanObject getObjectAtPoint(int x, int y) {
		if (p.x() == x && p.y() == y) {
			return new Player(x, y);
		} else if (b.x() == x && b.y() == y) {
			return new Box(x, y);
		} else if (g.x() == x && g.y() == y) {
			return new Goal(x, y);
		}
		return null;
	}
	public boolean canPush(SokobanObject p,SokobanObject b, Direction dir){
		if (p.x() == b.x() && p.y() == b.y() && b.checkValidMove(dir, grid)){
			return true;
		}
		return false;
	}
	public boolean isPushing(SokobanObject p,SokobanObject b){
		if (p.x() == b.x() && p.y() == b.y()){
			return true;
		}
		return false;
	}
}
