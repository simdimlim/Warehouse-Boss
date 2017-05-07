import java.io.FileNotFoundException;
import java.io.FileReader;

public class SokobanGame {
	
	public SokobanGame() {
		
	}
	
	public static void main(String[] args) {
		Grid g = new Grid(3, 7);
		try {
			g.generateLevel(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.printGrid(g.getGrid());
	}
}
