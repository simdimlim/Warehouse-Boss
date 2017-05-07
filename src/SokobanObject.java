import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SokobanObject {

	private Point currentLocation;
	private Grid g;
	private BufferedImage image;
	private String type; 
	
	/**
	 * Creates a new Object
	 * @param g grid
	 * @param currentLocation 
	 * @param image
	 */
	public SokobanObject (Grid g, Point currentLocation,BufferedImage image, String type){
		this.setG(g);
		this.setCurrentLocation(currentLocation.x,currentLocation.y);
		this.setImage(image);
		this.setType(type);
		
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(int x, int y) {
		Point temp = new Point(x,y);
		this.currentLocation = temp;
	}

	public Grid getG() {
		return g;
	}

	public void setG(Grid g) {
		this.g = g;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
