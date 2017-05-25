import java.awt.Image;
import javax.swing.ImageIcon;

public class Box extends WarehouseObject{
	
	private Image image;
	public Box (int x, int y){
		super(x, y);
		image = new ImageIcon(this.getClass().getResource("/images/box.jpg")).getImage();
		this.setImage(image);
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
