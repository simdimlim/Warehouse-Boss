import java.util.*;
import java.awt.*;
import java.io.*;

public class Grid {
	private SokobanObject[][] grid;
	private int numCol;
	private int numRow;
	
	Grid (int xSize,int ySize){
		grid = new SokobanObject[xSize][ySize];
		numCol = ySize;
		numRow = xSize;
	}
	
	public Boolean isWall(int x, int y){
		if(this.grid[x][y].getType().equals("W")) {
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
						SokobanObject wall = new Wall(new Point(x,y));
						grid[x][y] = wall;
					} else if (character.equals(" ")) {
						SokobanObject floor = new Floor(new Point(x,y));
						grid[x][y] = floor;
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
	
	public void printGrid(SokobanObject[][] g) {
		for (int i = 0; i < numRow; i++) {
			for (int j = 0; j < numCol; j++) {
				System.out.print(g[i][j].getType());
			}
			System.out.println();
		}
	}
	
	public SokobanObject[][] getGrid() {
		return grid;
	}
}