import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends SokobanObject {
	
	private Image image;
	public Player (int x, int y){
		super(x, y);
		image = new ImageIcon(this.getClass().getResource("/images/player.png")).getImage();
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
	
	public Player clone() {
		return (Player) super.clone();
	}
}
