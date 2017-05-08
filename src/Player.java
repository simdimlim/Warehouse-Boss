import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends SokobanObject {
	
	private boolean flag = false;

	public Player (int x, int y){
		super(x, y);	
	}
	public void move (Direction dir, Grid grid){
		flag =  checkValidMove(dir, grid);
		
		if (flag == true){
			switch(dir){
			case UP:
				setX(this.x()-1);
//				setY(this.y());
				break;
			case DOWN:
				setX(this.x()+1);
//				super.setCurrentLocation(++x,y);
				break;
			case LEFT:
				setY(this.y()-1);
				break;
			case RIGHT:
//				super.setCurrentLocation(x,++y);
				setY(this.y()+1);
				break;
			}
		}
	}	
}
