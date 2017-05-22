import java.awt.Image;

public class SokobanObject implements Cloneable {

	private int x;
	private int y;
	private Image img;
	
	/**
	 * Creates a new Object
	 * @param g grid
	 * @param currentLocation 
	 * @param image
	 */
	public SokobanObject (int x, int y){
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return img;
	}
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public boolean collidesWith(SokobanObject obj, Direction d) {
		if (x-1 == obj.x() && y == obj.y() && d == Direction.LEFT) {
			return true;
		} else if (x+1 == obj.x() && obj.y() == y && d == Direction.RIGHT) {
			return true;
		} else if (x == obj.x() && y-1 == obj.y() && d == Direction.UP) {
			return true;
		} else if (x == obj.x() && y+1 == obj.y() && d == Direction.DOWN) {
			return true;
		}
		return false;
	}
	
	@Override
	public SokobanObject clone() {
		try {
			return (SokobanObject) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean equals (Object o) {
		SokobanObject s = (SokobanObject) o;
		return this.x == s.x && this.y == s.y;
	}
}
