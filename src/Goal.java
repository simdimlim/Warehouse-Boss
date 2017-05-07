import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Goal extends SokobanObject{

	private String type = "GOAL";
	public Goal (Grid g, Point currentLocation,BufferedImage image){
		super(g,currentLocation,image);
	}
}