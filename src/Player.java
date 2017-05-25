import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends WarehouseObject {
	
	private Image image;
	private Image imageR;
	private Image imageU;

	/**
	 * Constructor for Player object.
	 * Takes in x and y position and creates a Player in that coordinate.
	 * @param x
	 * @param y
	 */
	public Player (int x, int y){
		super(x, y);
		image = new ImageIcon(this.getClass().getResource("/images/player.png")).getImage();
		imageR = new ImageIcon(this.getClass().getResource("/images/playerR.png")).getImage();
		imageU = new ImageIcon(this.getClass().getResource("/images/playerU.png")).getImage();
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
			setImage(imageU);
			setY(this.y()-1);
			break;
		case DOWN:
			setImage(image);
			setY(this.y()+1);
			break;
		case LEFT:
			setImage(image);
			setX(this.x()-1);
			break;
		case RIGHT:
			setImage(imageR);
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
