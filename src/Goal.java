import javax.swing.ImageIcon;
import java.awt.Image;

public class Goal extends WarehouseObject{

	private Image image;
	
	/**
	 * Constructor for Goal object.
	 * Takes in x and y position and creates a Goal in that coordinate.
	 * @param x
	 * @param y
	 */
	public Goal (int x, int y){
		super(x, y);
		image = new ImageIcon(this.getClass().getResource("/images/goal.png")).getImage();
		this.setImage(image);
	}
	
	/**
	 * Clones Goal object.
	 * 
	 */
	@Override
	public Goal clone() {
		return (Goal) super.clone();
	}
}