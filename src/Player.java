import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends SokobanObject {
	
	private boolean flag = false;

	public Player (Grid g, Point currentLocation,BufferedImage image){
		super(g,currentLocation,image,"PLAYER");	
	}
	public void move (Direction dir){
		flag = checkValidMove(dir);
		int x = super.getCurrentLocation().x;
		int y = super.getCurrentLocation().y;
		
		if (flag == true){
			switch(dir){
				case UP:
					super.setCurrentLocation(x,y--);
					break;
				case DOWN:
					super.setCurrentLocation(x,y++);
					break;
				case LEFT:
					super.setCurrentLocation(x--,y);
					break;
				case RIGHT:
					super.setCurrentLocation(x++,y);
					break;
			}
		}
	}
	public boolean checkValidMove(Direction dir){
		int currentX = super.getCurrentLocation().x;
		int currentY = super.getCurrentLocation().y;
		Grid grid = super.getG();
		switch(dir){
		case UP:
			if (!grid.isWall(currentX , currentY--)){
				return true;
			}
			break;
		case DOWN:
			if (!grid.isWall(currentX , currentY++)){
				return true;
			}
			break;
		case LEFT:
			if (!grid.isWall(currentX-- , currentY)){
				return true;
			}
			break;
		case RIGHT:
			if (!grid.isWall(currentX++ , currentY--)){
				return true;
			}
			break;
		}
		return false;
	}
	
}
