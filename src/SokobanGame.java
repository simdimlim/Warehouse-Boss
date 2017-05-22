import java.util.*;


public class SokobanGame {
	
	private ArrayList<SokobanObject> walls;
	private ArrayList<SokobanObject> boxes;
	private ArrayList<SokobanObject> goals;
	private ArrayList<SokobanObject> all;
	private ArrayList<SokobanObject> free;
	private Direction up = Direction.UP;
	private Direction down = Direction.DOWN;
	private Direction left = Direction.LEFT;
	private Direction right = Direction.RIGHT;
	private Player p;
	private Player initialP;
	private SokobanView sv;
	private boolean isComplete;
	private int height;
	private int width;
	private ArrayList<SokobanObject> initialBoxes;
	private int level = 0;
	private int moves = 0;
	private boolean sleep = false;
	private int RNG_BOUND = 1000;
	
	
	public SokobanGame() {
		walls = new ArrayList<SokobanObject>();
		boxes = new ArrayList<SokobanObject>();
		goals = new ArrayList<SokobanObject>();
		all = new ArrayList<SokobanObject>();
		free = new ArrayList<SokobanObject>();
		initialBoxes = new ArrayList<SokobanObject>();
		isComplete = false;
		height = 0;
		width = 0;
		sv = new SokobanView(this);
		generateLevel();
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
	
	public Player getPlayer() {
		return p;
	}
	
	public SokobanView getSView() {
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
	
	public ArrayList<SokobanObject> getAll() {
		return all;
	}
	
	public boolean hitWall(SokobanObject obj, Direction d) {
		for (int i = 0; i < walls.size(); i++) {
			Wall wall = (Wall) walls.get(i);
			if (d == Direction.UP) {
				if (obj.x() == wall.x() && obj.y()-1 == wall.y()) {
					return true;
				}
			} else if (d == Direction.DOWN) {
				if (obj.x() == wall.x() && obj.y()+1 == wall.y()) {
					return true;
				}
			} else if (d == Direction.LEFT) {
				if (obj.x()-1 == wall.x() && obj.y() == wall.y()) {
					return true;
				}
			} else if (d == Direction.RIGHT) {
				if (obj.x()+1 == wall.x() && obj.y() == wall.y()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hitBox(Direction d) {
		for (int i = 0; i < boxes.size(); i++) {
			Box box = (Box) boxes.get(i);
			if (p.collidesWith(box, left) && d == left) {
				for (int j = 0; j < boxes.size(); j++) {
					Box otherBox = (Box) boxes.get(j);
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
			} else if (p.collidesWith(box, right) && d == right) {
				for (int j = 0; j < boxes.size(); j++) {
					Box otherBox = (Box) boxes.get(j);
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
			} else if (p.collidesWith(box, up) && d == up) {
				for (int j = 0; j < boxes.size(); j++) {
					Box otherBox = (Box) boxes.get(j);
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
			} else if (p.collidesWith(box, down) && d == down) {
				for (int j = 0; j < boxes.size(); j++) {
					Box otherBox = (Box) boxes.get(j);
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
		
		all.clear();
		all.addAll(walls);
		all.addAll(goals);
		all.add(p);
		all.addAll(boxes);
		
		sv.repaint();
		level++;
		sleep = false;
	}
	
	public void decrementLevel() {
		if (level > 0) {
			level--;
		}
	}
	
	private void placeTemplates(int prototype){
		for(int i=0;i<3;i++){
			int x = getRandArrayElement(); 
			if(x==1) placeTemplate1(prototype);
			else if(x==2) placeTemplate2(prototype);
			else if(x==3) placeTemplate3(prototype);
		}
//		placeTemplate1();
//		placeTemplate2();
//		placeTemplate3();
	}

    private int[] items = new int[]{1,2,3};
    private Random rand = new Random();
    public int getRandArrayElement(){
        return items[rand.nextInt(items.length)];
    }
    
	public void restartLevel() {
		all.clear();
		boxes.clear();
		
		p = initialP.clone();
		
		for (int i = 0; i < initialBoxes.size(); i++) {
			Box box = (Box) initialBoxes.get(i);
			boxes.add(box.clone());
		}
		
		all.addAll(walls);
		all.addAll(goals);
		all.add(p);
		all.addAll(boxes);
		moves = 0;
		sv.repaint();
	}
	
	public int moveNum() {
		return moves;
	}
	
	public void isFinished() {
		int goalsReached = 0;
		int numGoals = goals.size();
		for (int i = 0; i < boxes.size(); i++) {
			Box box = (Box) boxes.get(i);
			for (int j = 0; j < goals.size(); j++) {
				Goal goal = (Goal) goals.get(j);
				if (box.x() == goal.x() && box.y() == goal.y()) {
					goalsReached++;
				}
			}
		}
		if (goalsReached == numGoals) {
			isComplete = true;
		}
	}
	
	public void newLevel() {
		all.clear();
		walls.clear();
		boxes.clear();
		free.clear();
		initialBoxes.clear();
		if (isComplete) {
			isComplete = false;
		}
		moves = 0;
		generateLevel();
	}
	
	public boolean isBoxOnGoal(SokobanObject obj) {
		for (int i = 0; i < goals.size(); i++) {
			Goal g = (Goal) goals.get(i);
			if (obj.x() == g.x() && obj.y() == g.y()) {
				return true;
			}
		}
		return false;
	}
	
	public void placePlayer() {
		Random rng = new Random();
		SokobanObject freeSpace = free.get(rng.nextInt(free.size()));
		free.remove(freeSpace);
		p = new Player(freeSpace.x(), freeSpace.y());
		initialP = p.clone();
	}
	
	public void placeGoals(int n) {
		goals.clear();
		
		int goalNum = 0;
		int i = 0;
		Random rng = new Random();
		
		while (goalNum < n && i < RNG_BOUND) {
			SokobanObject freeSpace = free.get(rng.nextInt(free.size()));
			if (hitWall(freeSpace, up) && hitWall(freeSpace, down)) {
			
			} else if (hitWall(freeSpace, right) && hitWall(freeSpace, left)) {
				
			} else {
				free.remove(freeSpace);
				Goal g = new Goal(freeSpace.x(), freeSpace.y());
				goals.add(g);
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
		Random rng = new Random();
		
		while (boxNum < n && i < RNG_BOUND) {
			SokobanObject freeSpace = free.get(rng.nextInt(free.size()));
			
			if (hitWall(freeSpace, up)) {
				
			} else if (hitWall(freeSpace, right)) {
				
			} else if (hitWall(freeSpace, down)) {
				
			} else if (hitWall(freeSpace, left)) {
				
			} else {
				free.remove(freeSpace);
				Box b = new Box(freeSpace.x(), freeSpace.y());
				boxes.add(b);
				initialBoxes.add(b.clone());
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
				walls.add(wall);
			} else if (element == ' ') {
				SokobanObject freeSpace = new SokobanObject(x, y);
				free.add(freeSpace);
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
				walls.add(wall);
			} else if (element == ' ') {
				SokobanObject freeSpace = new SokobanObject(x, y);
				free.add(freeSpace);
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
				walls.add(wall);
			} else if (element == ' ') {
				SokobanObject freeSpace = new SokobanObject(x, y);
				free.add(freeSpace);
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
				for (Iterator<SokobanObject> iterator = walls.iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	free.add((SokobanObject) wall);
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
				for (Iterator<SokobanObject> iterator = walls.iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	free.add((SokobanObject) wall);
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
				for (Iterator<SokobanObject> iterator = walls.iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	free.add((SokobanObject) wall);
				    	iterator.remove();
				    }
				}
				rX++;
			} else if (element == '#') {
				walls.add(new Wall(rX, rY));
				for (Iterator<SokobanObject> iterator = free.iterator(); iterator.hasNext();) {
				    SokobanObject freeObj = iterator.next();
				    if (freeObj.x() == rX && freeObj.y() == rY) {
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
}
