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
		p = new Player(new Point(3,1));
		b = new Box(new Point(3,2));
		g = new Goal(new Point(2,8));
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
	public boolean canPush(SokobanObject p,SokobanObject b, Direction dir){
		if (p.getCurrentLocation().equals(b.getCurrentLocation()) && b.checkValidMove(dir, grid)){
			return true;
		}
		return false;
	}
	public boolean isPushing(SokobanObject p,SokobanObject b){
		if (p.getCurrentLocation().equals(b.getCurrentLocation())){
			return true;
		}
		return false;
	}
}
