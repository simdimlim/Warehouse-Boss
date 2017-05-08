import javax.swing.*;

public class SokobanView {
	
	SokobanGame sg;
	
	public SokobanView(SokobanGame sg) {
		this.sg = sg;
		printGame();
	}
	
	public void printGame() {
		if (sg.isComplete()) {
			return;
		}
		SokobanObject[][] g = sg.getGrid().getGrid();
		for (int i = 0; i < sg.getGrid().getNumRow(); i++) {
			for (int j = 0; j < sg.getGrid().getNumCol(); j++) {
				SokobanObject type = g[i][j];
				SokobanObject obj = sg.getObjectAtPoint(i, j);
				if (obj instanceof Player) {
					System.out.print("P");
					continue;
				} else if (obj instanceof Box) {
					System.out.print("B");
					continue;
				} else if (obj instanceof Goal) {
					System.out.print("G");
					continue;
				}
				
				if (type instanceof Wall) {
					System.out.print("#");
				} else if (type instanceof Floor) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		if ((sg.getBox().x()==sg.getGoal().x()) && (sg.getBox().y()==sg.getGoal().y())){
	    	System.out.println("You win!");
	    	sg.changeIsComplete(true);
	    }
	}
}
