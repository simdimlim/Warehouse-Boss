import java.util.*;

public class Box extends SokobanObject{
		
	public Box (int x, int y){
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
	
	@Override
	public Box clone() {
		return (Box) super.clone();
	}
}
