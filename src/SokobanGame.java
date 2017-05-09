import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.JPanel;

public class SokobanGame {
	
	private ArrayList<SokobanObject> walls;
	private ArrayList<SokobanObject> boxes;
	private ArrayList<SokobanObject> goals;
	private ArrayList<SokobanObject> all;
	private Direction up = Direction.UP;
	private Direction down = Direction.DOWN;
	private Direction left = Direction.LEFT;
	private Direction right = Direction.RIGHT;
	private Player p;
	private SokobanView sv;
	private boolean isComplete;
	private int height;
	private int width;
	
	private String level1 =
	          "   ###   \n"
			+ "  #####  \n"
	        + " ##  ##  \n"
	        + " #@$ ##  \n"
	        + " ##$ ### \n"
	        + " ## $ ## \n"
	        + " #.$  ## \n"
	        + " #.. .#  \n"
	        + " ######  \n";
	
	public SokobanGame() {
		walls = new ArrayList<SokobanObject>();
		boxes = new ArrayList<SokobanObject>();
		goals = new ArrayList<SokobanObject>();
		all = new ArrayList<SokobanObject>();
		isComplete = false;
		height = 0;
		width = 0;
		sv = new SokobanView(this);
		generateLevel(level1);
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
			}
			x++;
		}
		height = y;
		all.addAll(goals);
		all.addAll(walls);
		all.add(p);
		all.addAll(boxes);
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
}
