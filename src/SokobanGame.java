import java.awt.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.JPanel;

public class SokobanGame {
	
	private Grid grid;
	private Player p;
	private Box b;
	private Goal g;
	private SokobanView sv;
	private boolean isComplete;
	private int SIZE = 60;
	
	public SokobanGame(Grid grid) {
		this.grid = grid;
		p = new Player(3,1);
		b = new Box(3,2);
		g = new Goal(2,8);
		isComplete = false;
		sv = new SokobanView(this);
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
	
	public Player getPlayer() {
		return p;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public Box getBox() {
		return b;
	}
	
	public SokobanView getSView() {
		return sv;
	}
	
	public Goal getGoal() {
		return g;
	}
	
	public void changeIsComplete(boolean b) {
		isComplete = b;
	}
	
	public boolean isComplete() {
		return isComplete;
	}
}
