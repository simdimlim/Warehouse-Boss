import javax.swing.ImageIcon;
import java.awt.Image;

public class Goal extends SokobanObject{

	private Image image;
	public Goal (int x, int y){
		super(x, y);
		image = new ImageIcon(this.getClass().getResource("/images/goal.png")).getImage();
		this.setImage(image);
	}
	
	@Override
	public Goal clone() {
		return (Goal) super.clone();
	}
}