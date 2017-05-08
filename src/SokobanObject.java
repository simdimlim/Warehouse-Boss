import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SokobanObject {

	private int x;
	private int y;
	
	/**
	 * Creates a new Object
	 * @param g grid
	 * @param currentLocation 
	 * @param image
	 */
	public SokobanObject (int x, int y){
		this.x = x;
		this.y = y;
		
	}

	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
	public boolean checkValidMove(Direction dir, Grid grid){
		int currentX = this.x;
		int currentY = this.y;
		switch(dir){
		case UP:
			if (!grid.isWall(--currentX , currentY)){
				return true;
			}
			break;
		case DOWN:
			if (!grid.isWall(++currentX , currentY)){
				return true;
			}
			break;
		case LEFT:
			if (!grid.isWall(currentX , --currentY)){
				return true;
			}
			break;
		case RIGHT:
			if (!grid.isWall(currentX , ++currentY)){
				return true;
			}
			break;
		}
		return false;
	}
}
