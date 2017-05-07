import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Wall extends SokobanObject{

	private String type = "WALL";
	public Wall (Grid g, Point currentLocation,BufferedImage image){
		super(g,currentLocation,image,"WALL");
	}
}
