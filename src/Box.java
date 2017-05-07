import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Box extends SokobanObject{
	
	private boolean finished;
	public Box (Grid g, Point currentLocation,BufferedImage image){
		super(g,currentLocation,image);
		this.finished = false;
	}
}
