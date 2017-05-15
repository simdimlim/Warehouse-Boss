import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends SokobanObject {
	
	public Player (int x, int y){
		super(x, y);	
	}
	public void move (Direction dir){
		
		switch(dir){
		case UP:
			setY(this.y()-1);
			break;
		case DOWN:
			setY(this.y()+1);
			break;
		case LEFT:
			setX(this.x()-1);
			break;
		case RIGHT:
			setX(this.x()+1);
			break;
		}
	}
	
	public Player clone() {
		return (Player) super.clone();
	}
}
