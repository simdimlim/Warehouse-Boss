import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends SokobanObject{
	
	private Image image;
	public Wall (int x, int y){
		super(x, y);
		image = new ImageIcon(this.getClass().getResource("/images/wall.jpg")).getImage();
		this.setImage(image);
	}
	
	@Override
	public Wall clone() {
		return (Wall) super.clone();
	}
}
