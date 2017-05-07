import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SokobanObject {

	private Point currentLocation;
	private Grid g;
	private BufferedImage image;
	
	/**
	 * Creates a new Object
	 * @param g grid
	 * @param currentLocation 
	 * @param image
	 */
	public SokobanObject (Grid g, Point currentLocation,BufferedImage image){
		this.setG(g);
		this.setCurrentLocation(currentLocation);
		this.setImage(image);
		
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Point currentLocation) {
		this.currentLocation = currentLocation;
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
}
