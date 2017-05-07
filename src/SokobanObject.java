import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SokobanObject {

	private Point currentLocation;
	private String type; 
	
	/**
	 * Creates a new Object
	 * @param g grid
	 * @param currentLocation 
	 * @param image
	 */
	public SokobanObject (Point currentLocation, String type){
		this.setCurrentLocation(currentLocation.x,currentLocation.y);
		this.setType(type);
		
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(int x, int y) {
		Point temp = new Point(x,y);
		this.currentLocation = temp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public boolean checkValidMove(Direction dir, Grid grid){
		int currentX = this.getCurrentLocation().x;
		int currentY = this.getCurrentLocation().y;
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
