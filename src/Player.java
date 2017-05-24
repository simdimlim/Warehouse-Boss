import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends SokobanObject {
	
	private Image image;
	

	/**
	 * Constructor for Player object.
	 * Takes in x and y position and creates a PlayerS in that coordinate.
	 * @param x
	 * @param y
	 */
	public Player (int x, int y){
		super(x, y);
		image = new ImageIcon(this.getClass().getResource("/images/player.png")).getImage();
		this.setImage(image);
	}
	
	/**
	 * Function that handles movement of player.
	 * Takes in desired direction and moves player there.
	 * 
	 * @param dir
	 */
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

	/**
	 * Clones the player object.
	 */
	public Player clone() {
		return (Player) super.clone();
	}
}
