import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class WarehouseGame {
	private GameMap EntireMap;
	private Direction up = Direction.UP;
	private Direction down = Direction.DOWN;
	private Direction left = Direction.LEFT;
	private Direction right = Direction.RIGHT;
	private WarehouseView sv;
	private boolean isComplete;
	private int height;
	private int width;
	private int level = 0;
	private int moves = 0;
	private boolean sleep = false;
	private int RNG_BOUND = 1000;
	
	
	public WarehouseGame() {
		EntireMap = new GameMap();
		isComplete = false;
		height = 0;
		width = 0;
		sv = new WarehouseView(this);
		generateLevel();
	}
	
	public Player getPlayer(){
		return EntireMap.getPlayer();
	}
	
	public ArrayList<WarehouseObject> getEntireMap(){
		return EntireMap.getEntireMap();
	}
	
	public boolean isSleeping() {
		return sleep;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setSleeping(boolean b) {
		sleep = b;
	}
	
	public WarehouseView getSView() {
		return sv;
	}
	
	public boolean isComplete() {
		return isComplete;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean hitWall(WarehouseObject obj, Direction d) {
		for (Wall w: EntireMap.getWalls()){
			if (d == Direction.UP) {
				if (obj.x() == w.x() && obj.y()-1 == w.y()) {
					return true;
				}
			} else if (d == Direction.DOWN) {
				if (obj.x() == w.x() && obj.y()+1 == w.y()) {
					return true;
				}
			} else if (d == Direction.LEFT) {
				if (obj.x()-1 == w.x() && obj.y() == w.y()) {
					return true;
				}
			} else if (d == Direction.RIGHT) {
				if (obj.x()+1 == w.x() && obj.y() == w.y()) {
					return true;
				}
			}			
		}
		return false;
	}
	
	public boolean hitBox(Direction d) {
		for (Box box: EntireMap.getBoxes()){
			if (EntireMap.getPlayer().collidesWith(box, left) && d == left) {
				for (Box otherBox: EntireMap.getBoxes()){
					if (!box.equals(otherBox)) {
						if (box.collidesWith(otherBox, left)) {
							return true;
						}
					}
					if (hitWall(box, left)) {
						return true;
					}					
				}
				box.move(left);
				moves++;
				isFinished();
			} else if (EntireMap.getPlayer().collidesWith(box, right) && d == right) {
				for (Box otherBox: EntireMap.getBoxes()){
					if (!box.equals(otherBox)) {
						if (box.collidesWith(otherBox, right)) {
							return true;
						}
					}
					if (hitWall(box, right)) {
						return true;
					}
				}
				box.move(right);
				moves++;
				isFinished();
			} else if (EntireMap.getPlayer().collidesWith(box, up) && d == up) {
				for (Box otherBox: EntireMap.getBoxes()){
					if (!box.equals(otherBox)) {
						if (box.collidesWith(otherBox, up)) {
							return true;
						}
					}
					if (hitWall(box, up)) {
						return true;
					}
				}
				box.move(up);
				moves++;
				isFinished();
			} else if (EntireMap.getPlayer().collidesWith(box, down) && d == down) {
				for (Box otherBox: EntireMap.getBoxes()){
					if (!box.equals(otherBox)) {
						if (box.collidesWith(otherBox, down)) {
							return true;
						}
					}
					if (hitWall(box, down)) {
						return true;
					}
				}
				box.move(down);
				moves++;
				isFinished();
			}			
		}
		return false;
	}
	
	private void generateLevel() {	
		
		if (level < 5) {
			initialisePrototype1();
			placeTemplates(1);
			placePlayer();
			placeBoxes(3);
			placeGoals(3);
		}
		else if (level >= 5 && level < 10) {
			initialisePrototype2();
			placeTemplates(2);
			placePlayer();
			placeBoxes(4);
			placeGoals(4);
			sv.scale();
		}
		else if (level >= 10) {
			initialisePrototype3();
			placeTemplates(3);
			placePlayer();
			placeBoxes(5);
			placeGoals(5);
			sv.scale();
		}
		
		EntireMap.clearMap();
		EntireMap.allAllToMap();
		
		sv.repaint();
		sleep = false;
	}
	
	private void placeTemplates(int prototype){
		for(int i=0;i<3;i++){
			int x = getRandArrayElement(); 
			if(x==1) placeTemplate1(prototype);
			else if(x==2) placeTemplate2(prototype);
			else if(x==3) placeTemplate3(prototype);
		}
	}

    private int[] items = new int[]{1,2,3};
    private Random rand = new Random();
    public int getRandArrayElement(){
        return items[rand.nextInt(items.length)];
    }
    
	public void restartLevel() {
		EntireMap.restartMap();

		moves = 0;
		sv.repaint();
	}
	
	public int moveNum() {
		return moves;
	}
	
	public void isFinished() {
		int goalsReached = 0;
		int numGoals = EntireMap.GoalsSize();
		
		for (Box box: EntireMap.getBoxes()){
			for (Goal goal: EntireMap.getGoals()){
				if (box.x() == goal.x() && box.y() == goal.y()) {
					goalsReached++;
				}
			}
		}
		if (goalsReached == numGoals) {
			isComplete = true;
			level++;
		}
	}

	public void newLevel() {
		EntireMap.newMap();
		
		if (isComplete) {
			isComplete = false;
		}
		moves = 0;
		generateLevel();
	}
	
	public boolean isBoxOnGoal(WarehouseObject obj) {
		for (Goal goal: EntireMap.getGoals()){
			if (obj.x() == goal.x() && obj.y() == goal.y()) {
				return true;
			}
		}
		return false;
	}
	
	public void placePlayer() {
		WarehouseObject freeSpace = EntireMap.getRandomFree();
		EntireMap.removeFree(freeSpace);
		Player p = new Player(freeSpace.x(), freeSpace.y());
		EntireMap.setInitialPlayer(p);
		EntireMap.setPlayer(p);
	}
	
	public void placeGoals(int n) {
		EntireMap.clearGoals();
		
		int goalNum = 0;
		int i = 0;

		while (goalNum < n && i < RNG_BOUND) {
			WarehouseObject freeSpace = EntireMap.getRandomFree();
			if (hitWall(freeSpace, up) && hitWall(freeSpace, down)) {
			
			} else if (hitWall(freeSpace, right) && hitWall(freeSpace, left)) {
				
			} else {
				EntireMap.removeFree(freeSpace);
				Goal g = new Goal(freeSpace.x(), freeSpace.y());
				EntireMap.addToGoals(g);
				goalNum++;
				if (goalNum == n) {
					break;
				}
			}
			i++;
		}
		
		if (goalNum < n) {
			newLevel();
		}
	}
	
	public void placeBoxes(int n) {
		int boxNum = 0;
		int i = 0;

		while (boxNum < n && i < RNG_BOUND) {
			WarehouseObject freeSpace = EntireMap.getRandomFree();
			
			if (hitWall(freeSpace, up)) {
				
			} else if (hitWall(freeSpace, right)) {
				
			} else if (hitWall(freeSpace, down)) {
				
			} else if (hitWall(freeSpace, left)) {
				
			} else {
				EntireMap.removeFree(freeSpace);
				Box b = new Box(freeSpace.x(), freeSpace.y());
				EntireMap.addToBoxes(b);
				EntireMap.addToInitialBoxes(b.clone());
				boxNum++;
				if (boxNum == n) {
					break;
				}
			}
			i++;
		}
		
		if (boxNum < n) {
			newLevel();
		}
	}
	
	public void initialisePrototype1() {
		String prototype =
		          "########\n"
				+ "#  ##  #\n"
		        + "#      #\n"
		        + "#  ##  #\n"
		        + "#      #\n"
		        + "########\n"
		        + "########\n"
		        + "########\n";
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < prototype.length(); i++) {
			char element = prototype.charAt(i);
			
			if (element == '\n') {
				y++;
				if (width < x) {
					width = x;
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				EntireMap.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!EntireMap.freeContains(freeSpace)) {
					EntireMap.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
	}
	public void initialisePrototype2() {
		String prototype =
		          "##########\n"
				+ "#  ##   ##\n"
		        + "#        #\n"
		        + "#  #  ####\n"
		        + "#        #\n"
		        + "##########\n"
		        + "##########\n"
		        + "##########\n"
		        + "##########\n"
		        + "##########\n";
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < prototype.length(); i++) {
			char element = prototype.charAt(i);
			
			if (element == '\n') {
				y++;
				if (width < x) {
					width = x;
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				EntireMap.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!EntireMap.freeContains(freeSpace)) {
					EntireMap.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
	}
	public void initialisePrototype3() {
		String prototype =
		          "###############\n"
		  		+ "###############\n"
				+ "#  ##        ##\n"
		        + "#             #\n"
		        + "#  ##     #####\n"
		        + "#    ##       #\n"
		        + "#             #\n"
		        + "####   ###    #\n"
		        + "###    ###### #\n"
		        + "####          #\n"
		        + "###############\n"
		        + "###############\n"
		        + "###############\n"
		        + "###############\n"
		        + "###############\n"
		        + "###############\n";
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < prototype.length(); i++) {
			char element = prototype.charAt(i);
			
			if (element == '\n') {
				y++;
				if (width < x) {
					width = x;
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				EntireMap.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!EntireMap.freeContains(freeSpace)) {
					EntireMap.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
	}
	
	public void placeTemplate1(int prototype) {
		String template1 = 
				  "  \n"
				+ "  \n"
		        + "  \n";
		int t1Width = 2;
		int t1Height = 3;
		int boundY = 0;
		
		Random rng = new Random();
		int rX = 0;
		int rY = 0;
		while (rX == 0) {
			rX = rng.nextInt(width-t1Width);
		}
		
		if (rX == 1 || rX == 5) {
			while (rY < 1 || rY > 4) {
				rY = rng.nextInt(height-t1Height);
			}
		} else if (rX > 1 && rX < 5) {
			rY = 4;
		} else {
			if (prototype == 2) {
				boundY = 3;
			} else if (prototype == 3) {
				boundY = 8;
			}
			while (rY < 1 || rY > boundY) {
				rY = rng.nextInt(height-t1Height);
			}
		}
		
		int initRX = rX;
		
		for (int i = 0; i < template1.length(); i++) {
			char element = template1.charAt(i);
			if (element == '\n') {
				rX = initRX;
				rY++;
			} else if (element == ' ') {
				for (Iterator<Wall> iterator = EntireMap.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!EntireMap.freeContains((WarehouseObject) wall)) {
				    		EntireMap.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
	
	public void placeTemplate2(int prototype) {
		String template2 = 
				  "  \n"
				+ "  \n";
		int t2Width = 2;
		int t2Height = 2;
		
		Random rng = new Random();
		int rX = 0;
		int rY = 0;
		int boundY = 0;
		
		while (rX == 0) {
			rX = rng.nextInt(width-t2Width);
		}
		
		if (rX == 1 || rX == 5) {
			while (rY < 1 || rY > 4) {
				rY = rng.nextInt(height-t2Height);
			}
		} else if (rX > 1 && rX < 5) {
			while (rY < 4 || rY > 5) {
				rY = rng.nextInt(height-t2Height);
			}
		} else {
			if (prototype == 2) {
				boundY = 3;
			} else if (prototype == 3) {
				boundY = 8;
			}
			while (rY < 1 || rY > boundY) {
				rY = rng.nextInt(height-t2Height);
			}
		}
		
		int initRX = rX;
		
		for (int i = 0; i < template2.length(); i++) {
			char element = template2.charAt(i);
			if (element == '\n') {
				rX = initRX;
				rY++;
			} else if (element == ' ') {
				for (Iterator<Wall> iterator = EntireMap.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!EntireMap.freeContains((WarehouseObject) wall)) {
				    		EntireMap.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
	
	public void placeTemplate3(int prototype) {
		String template3 =
				  "   \n"
				+ " # \n"
				+ "   \n";
		
		int t3Width = 3;
		int t3Height = 3;
		
		Random rng = new Random();
		int rX = 0;
		int rY = 0;
		int boundY = 0;
		
		while (rX == 0) {
			rX = rng.nextInt(width-t3Width);
		}
		
		if (rX == 1 || rX == 4) {
			while (rY < 1 || rY > 4) {
				rY = rng.nextInt(height-t3Height);
			}
		} else if (rX > 1 && rX < 5) {
			rY = 4;
		} else {
			if (prototype == 2) {
				boundY = 4;
			} else if (prototype == 3) {
				boundY = 8;
			}
			while (rY < 1 || rY > boundY) {
				rY = rng.nextInt(height-t3Height);
			}
		}
		
		int initRX = rX;
		
		for (int i = 0; i < template3.length(); i++) {
			char element = template3.charAt(i);
			if (element == '\n') {
				rX = initRX;
				rY++;
			} else if (element == ' ') {
				for (Iterator<Wall> iterator = EntireMap.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!EntireMap.freeContains((WarehouseObject) wall)) {
				    		EntireMap.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			} else if (element == '#') {
				EntireMap.addToWalls(new Wall(rX, rY));
				for (Iterator<WarehouseObject> iterator = EntireMap.getFree().iterator(); iterator.hasNext();) {
				    WarehouseObject freeObj = iterator.next();
				    if (freeObj.x() == rX && freeObj.y() == rY) {
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
}
