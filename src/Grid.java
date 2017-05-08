import java.util.*;
import java.awt.*;
import java.io.*;

public class Grid {
	private SokobanObject[][] grid;
	private int width;
	private int height;
	
	Grid (int x, int y){
		grid = new SokobanObject[x][y];
		width = y;
		height = x;
	}
	
	public Boolean isWall(int x, int y){
		if(this.grid[x][y] instanceof Wall) {
			return true;
		}
		return false;
	}
	
	public Grid generateLevel(FileReader fr) {
		Scanner sc = null;
		Scanner ls = null;
		int x = 0;
		int y = 0;
		try {
			sc = new Scanner(fr);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				ls = new Scanner(line);
				ls.useDelimiter("");
				while (ls.hasNext()) {
					String character = ls.next();
					if (character.equals("#")) {
						SokobanObject wall = new Wall(x, y);
						grid[x][y] = wall;
					} else if (character.equals(" ")) {
						grid[x][y] = new Floor(x, y);
					}
					y++;
				}
				y = 0;
				x++;
			}
		}
		finally {
			if (sc != null)
				sc.close();
			if (ls != null)
				ls.close();
		}
		return null;
	}
	
	public SokobanObject[][] getGrid() {
		return grid;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}