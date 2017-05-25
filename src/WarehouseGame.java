import java.util.*;
/**
 * WarehouseGame stores the current state of the game and 
 * performs collision detection every time the player wishes to move.
 */
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
	
	/**
	 * Constructor for WarehouseGame.
	 */
	public WarehouseGame() {
		entireMap = new GameMap();
		isComplete = false;
		height = 0;
		width = 0;
		sv = new WarehouseView(this);
		gen = new WarehouseGenerator(this);
		gen.generateLevel();
	}
	
	/**
	 * Returns the current player.
	 * 
	 * @return The current player
	 */
	public Player getPlayer(){
		return entireMap.getPlayer();
	}
	
	/**
	 * Returns the map as a GameMap
	 * 
	 * @return The map
	 */
	public GameMap getGameMap() {
		return entireMap;
	}
	
	/**
	 * Returns whether the game is sleeping.
	 * 
	 * @return true if game is sleeping, false otherwise.
	 */
	public boolean isSleeping() {
		return sleep;
	}
	
	/**
	 * Returns the current level of the game.
	 * 
	 * @return The level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Sets the sleep variable to true/false.
	 * 
	 * @param b True, if the game is sleeping, false otherwise.
	 */
	public void setSleeping(boolean b) {
		sleep = b;
	}
	
	/**
	 * Returns the game's WarehouseView.
	 * 
	 * @return The game's view
	 */
	public WarehouseView getView() {
		return sv;
	}
	
	/**
	 * Returns whether the level is complete.
	 * 
	 * @return If level is complete or not.
	 */
	public boolean isComplete() {
		return isComplete;
	}
	
	/**
	 * Returns the current board width.
	 * 
	 * @return The board width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the current board height.
	 * 
	 * @return The board height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets the board width.
	 * 
	 * @param width The new board width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Sets the board height
	 * 
	 * @param height The new board height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Returns whether the given object has encountered a wall.
	 * 
	 * @param obj The current warehouse object to check
	 * @param d The direction which the object wishes to move
	 * @return True if the object will hit a wall, false otherwise.
	 */
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
	
	/**
	 * Checks if the player has encountered a box, and if so,
	 * checks if the box will hit another box. If it doesn't, move the box.
	 * The level is checked if it's complete every time a box moves.
	 * 
	 * @param d The direction the player wishes to move
	 * @return true if the player has encountered a box, false otherwise.
	 */
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
	
	/**
	 * Restarts the level.
	 */
	public void restartLevel() {
		moves = 0;
		gen.restartLevel();
	}
	
	/**
	 * Returns the number of moves that have been taken.
	 * 
	 * @return The number of moves
	 */
	public int moveNum() {
		return moves;
	}
	
	/**
	 * Checks if all the boxes are on top of goals.
	 */
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
	
	/**
	 * Generates a new level.
	 */
	public void newLevel() {
		if (isComplete) {
			isComplete = false;
		}
		moves = 0;
		gen.newLevel();
	}
	
	/**
	 * Checks to see if a box is on any of the goals.
	 * 
	 * @param obj The box object
	 * @return true if it is on a goal, false otherwise.
	 */
	public boolean isBoxOnGoal(WarehouseObject obj) {
		for (Goal goal: entireMap.getGoals()){
			if (obj.x() == goal.x() && obj.y() == goal.y()) {
				return true;
			}
		}
		return false;
	}
}