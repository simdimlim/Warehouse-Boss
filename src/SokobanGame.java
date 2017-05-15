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
	private ArrayList<SokobanObject> initWalls;
	private ArrayList<SokobanObject> initBoxes;
	private ArrayList<SokobanObject> initGoals;
	
//	private String level1 =
//	          "   ###   \n"
//			+ "  #####  \n"
//	        + " ##  ##  \n"
//	        + " #@$ ##  \n"
//	        + " ##$ ### \n"
//	        + " ## $ ## \n"
//	        + " #.$  ## \n"
//	        + " #.. .#  \n"
//	        + " ######  \n";
	
	public SokobanGame() {
		walls = new ArrayList<SokobanObject>();
		boxes = new ArrayList<SokobanObject>();
		goals = new ArrayList<SokobanObject>();
		all = new ArrayList<SokobanObject>();
		free = new ArrayList<SokobanObject>();
		initWalls = new ArrayList<SokobanObject>();
		initBoxes = new ArrayList<SokobanObject>();
		initGoals = new ArrayList<SokobanObject>();
		isComplete = false;
		height = 0;
		width = 0;
		sv = new SokobanView(this);
		generatePrototype();
//		generateLevel(level1);
	}
	
	public void generateLevel(String level) {
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < level.length(); i++) {
			char element = level.charAt(i);
			
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
			} else if (element == '$') {
				Box box = new Box(x, y);
				boxes.add(box);
			} else if (element == '.') {
				Goal goal = new Goal(x, y);
				goals.add(goal);
			} else if (element == '@') {
				p = new Player(x, y);
			} else if (element == ' ') {
				SokobanObject freeSpace = new SokobanObject(x, y);
				free.add(freeSpace);
			}
			x++;
		}
		height = y;
//		all.addAll(goals);
//		all.addAll(walls);
//		all.add(p);
//		all.addAll(boxes);
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public SokobanView getSView() {
		return sv;
	}
	
	public void changeIsComplete(boolean b) {
		isComplete = b;
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
	
	public boolean canPush(SokobanObject p,SokobanObject b, Direction dir){
		if (p.x() == b.x() && p.y() == b.y()){
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
			}
		}
		return false;
	}
	
	private void generatePrototype() {
		String prototype =
		          "########\n"
				+ "#  ##  #\n"
		        + "#      #\n"
		        + "#  ##  #\n"
		        + "#      #\n"
		        + "########\n"
		        + "########\n"
		        + "########\n";
		
		String template1 = 
				  "  \n"
				+ "  \n"
		        + "  \n";
		int t1Width = 2;
		int t1Height = 3;
		generateLevel(prototype);
		
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
		
		String template2 = 
				  "  \n"
				+ "  \n";
		int t2Width = 2;
		int t2Height = 2;
		
		rX = 0;
		rY = 0;
		
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
		}
		
		initRX = rX;
		
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
		
		String template3 =
				  "   \n"
				+ " # \n"
				+ "   \n";
		
		int t3Width = 3;
		int t3Height = 3;
		
		rX = 0;
		rY = 0;
		
		while (rX == 0) {
			rX = rng.nextInt(width-t3Width);
		}
		
		if (rX == 1 || rX == 4) {
			while (rY < 1 || rY > 4) {
				rY = rng.nextInt(height-t3Height);
			}
		} else if (rX > 1 && rX < 5) {
			rY = 4;
		}
		
		initRX = rX;
		
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
				initWalls.add(new Wall(rX, rY));
				for (Iterator<SokobanObject> iterator = free.iterator(); iterator.hasNext();) {
				    SokobanObject freeObj = iterator.next();
				    if (freeObj.x() == rX && freeObj.y() == rY) {
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
		
		rX = 0;
		rY = 0;
		
		SokobanObject freeSpace = free.get(rng.nextInt(free.size()));
		free.remove(freeSpace);
		p = new Player(freeSpace.x(), freeSpace.y());
		initialP = p.clone();
		
		boolean validPos = false;
		int boxNum = 0;
		
		while (boxNum < 3) {
			while (!validPos) {
				freeSpace = free.get(rng.nextInt(free.size()));
				if (hitWall(freeSpace, up)) {
					
				} else if (hitWall(freeSpace, right)) {
					
				} else if (hitWall(freeSpace, down)) {
					
				} else if (hitWall(freeSpace, left)) {
					
				} else {
					validPos = true;
					free.remove(freeSpace);
					Box b = new Box(freeSpace.x(), freeSpace.y());
					boxes.add(b);
					initBoxes.add(b.clone());
					boxNum++;
				}
			}
			validPos = false;
		}
		
		int goalNum = 0;
		
		while (goalNum < 3) {
			while (!validPos) {
				freeSpace = free.get(rng.nextInt(free.size()));
				if (hitWall(freeSpace, up) && hitWall(freeSpace, down)) {
				
				} else if (hitWall(freeSpace, right) && hitWall(freeSpace, left)) {
					
				} else {
					validPos = true;
					free.remove(freeSpace);
					Goal g = new Goal(freeSpace.x(), freeSpace.y());
					goals.add(g);
					initGoals.add(g.clone());
					goalNum++;
				}
			}
			validPos = false;
		}
		
		all.clear();
		all.addAll(walls);
		all.addAll(goals);
		all.add(p);
		all.addAll(boxes);
		
		for (int i = 0; i < walls.size(); i++) {
			Wall wall = (Wall) walls.get(i);
			initWalls.add(wall.clone());
		}
	}
	
	public void restartLevel() {
		all.clear();
		walls.clear();
		goals.clear();
		boxes.clear();
		
		p = initialP.clone();
		
		for (int i = 0; i < initWalls.size(); i++) {
			Wall wall = (Wall) initWalls.get(i);
			walls.add(wall.clone());
		}
		
		for (int i = 0; i < initGoals.size(); i++) {
			Goal goal = (Goal) initGoals.get(i);
			goals.add(goal.clone());
		}
		
		for (int i = 0; i < initBoxes.size(); i++) {
			Box box = (Box) initBoxes.get(i);
			boxes.add(box.clone());
		}
		
		walls.addAll(walls);
		goals.addAll(goals);
		boxes.addAll(boxes);
		all.addAll(walls);
		all.addAll(goals);
		all.add(p);
		all.addAll(boxes);
		
	}
}
