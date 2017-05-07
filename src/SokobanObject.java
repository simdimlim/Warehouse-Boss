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
}
