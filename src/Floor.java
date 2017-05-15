public class Floor extends SokobanObject {
	public Floor(int x, int y) {
		super(x, y);
	}
	
	@Override
	public Floor clone() {
		return (Floor) super.clone();
	}
}
