public class Goal extends SokobanObject{

	public Goal (int x, int y){
		super(x, y);
	}
	
	@Override
	public Goal clone() {
		return (Goal) super.clone();
	}
}