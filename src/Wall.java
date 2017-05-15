import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Wall extends SokobanObject{

	public Wall (int x, int y){
		super(x, y);
	}
	
	@Override
	public Wall clone() {
		return (Wall) super.clone();
	}
}
