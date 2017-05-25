import java.awt.Image;

public class WarehouseObject implements Cloneable {

	private int x;
	private int y;
	private Image img;
	
	/**
	 * Constructor for a WarehouseObject.
	 * @param x The object's x coordinate
	 * @param y The object's y coordinate
	 */
	public WarehouseObject (int x, int y){
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
	
	/**
	 * Returns whether the current object will collide with the object
	 * taken in as a parameter in the specific direction.
	 * 
	 * @param obj The warehouse object to check against
	 * @param d The direction the current object wishes to move
	 * @return true if there is a collision, false otherwise.
	 */
	public boolean collidesWith(WarehouseObject obj, Direction d) {
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
	public WarehouseObject clone() {
		try {
			return (WarehouseObject) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean equals (Object o) {
		WarehouseObject s = (WarehouseObject) o;
		return this.x == s.x && this.y == s.y;
	}
}
