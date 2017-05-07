import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Box extends SokobanObject{
	
	private boolean flag = false;
	private boolean goalReached;
	
	public Box (Point currentLocation){
		super(currentLocation,"B");
		this.goalReached = false;
		
	}
	public void move (Direction dir, Grid grid){
		flag =  checkValidMove(dir, grid);
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
