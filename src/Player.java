import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends SokobanObject {
	
	private boolean flag = false;

	public Player (Point currentLocation){
		super(currentLocation,"P");	
	}
	public void move (Direction dir, Grid grid){
		flag = checkValidMove(dir, grid);
		int x = super.getCurrentLocation().x;
		int y = super.getCurrentLocation().y;
		if (flag == true){
			switch(dir){
			case UP:
				super.setCurrentLocation(--x,y);
				break;
			case DOWN:
				super.setCurrentLocation(++x,y);
				break;
			case LEFT:
				super.setCurrentLocation(x,--y);
				break;
			case RIGHT:
				super.setCurrentLocation(x,++y);
				break;
			}
		}
	}	
}
