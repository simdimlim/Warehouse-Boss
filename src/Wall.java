import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends SokobanObject{

	public Wall (int x, int y){
		super(x, y);
		Image image = new ImageIcon(this.getClass().getResource("/images/wall.png")).getImage();
		this.setImage(image);
	}
	
	@Override
	public Wall clone() {
		return (Wall) super.clone();
	}
}
