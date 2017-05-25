import java.util.*;



public class WarehouseGame {
	private GameMap entireMap;
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
	private WarehouseGenerator gen;
	
	
	public WarehouseGame() {
		entireMap = new GameMap();
		isComplete = false;
		height = 0;
		width = 0;
		sv = new WarehouseView(this);
		gen = new WarehouseGenerator(this);
		gen.generateLevel();
	}
	
	public Player getPlayer(){
		return entireMap.getPlayer();
	}
	
	public ArrayList<WarehouseObject> getEntireMap(){
		return entireMap.getEntireMap();
	}
	
	public GameMap getGameMap() {
		return entireMap;
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
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean hitWall(WarehouseObject obj, Direction d) {
		for (Wall w: entireMap.getWalls()){
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
		for (Box box: entireMap.getBoxes()){
			if (entireMap.getPlayer().collidesWith(box, left) && d == left) {
				for (Box otherBox: entireMap.getBoxes()){
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
			} else if (entireMap.getPlayer().collidesWith(box, right) && d == right) {
				for (Box otherBox: entireMap.getBoxes()){
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
			} else if (entireMap.getPlayer().collidesWith(box, up) && d == up) {
				for (Box otherBox: entireMap.getBoxes()){
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
			} else if (entireMap.getPlayer().collidesWith(box, down) && d == down) {
				for (Box otherBox: entireMap.getBoxes()){
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
	
	public void restartLevel() {
		moves = 0;
		gen.restartLevel();
	}
	
	public int moveNum() {
		return moves;
	}
	
	public void isFinished() {
		int goalsReached = 0;
		int numGoals = entireMap.goalsSize();
		
		for (Box box: entireMap.getBoxes()){
			for (Goal goal: entireMap.getGoals()){
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
		if (isComplete) {
			isComplete = false;
		}
		moves = 0;
		gen.newLevel();
	}
	
	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
//	public void resetMoves() {
//		moves = 0;
//	}
	
	public boolean isBoxOnGoal(WarehouseObject obj) {
		for (Goal goal: entireMap.getGoals()){
			if (obj.x() == goal.x() && obj.y() == goal.y()) {
				return true;
			}
		}
		return false;
	}
}