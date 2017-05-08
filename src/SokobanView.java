import java.awt.*;
import javax.swing.*;

public class SokobanView extends JPanel{
	private int SIZE = 60;
	private SokobanGame sg;
	
	public SokobanView(SokobanGame sg) {
		this.sg = sg;
		addKeyListener(new SokobanController(this));
		setFocusable(true);
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
				} else {
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
	
	public void paint(Graphics g) {
		super.paint(g);
		paintLevel(g);
	}
	
	public void paintLevel(Graphics g) {
		if (sg.isComplete()) {
			return;
		}
		Grid grid = sg.getGrid();
		SokobanObject[][] board = grid.getGrid();
		int width = grid.getNumCol();
		int height = grid.getNumRow();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				SokobanObject boardObj = board[i][j];
				SokobanObject obj = sg.getObjectAtPoint(i, j);
				if (obj != null) {
					int y = obj.x()*SIZE;
					int x = obj.y()*SIZE;
					
					if (obj instanceof Player) {
						g.setColor(Color.CYAN);
						g.fillOval(x, y, SIZE, SIZE);
					} else if (obj instanceof Box) {
						g.setColor(Color.ORANGE);
						g.fillRect(x, y, SIZE, SIZE);
						g.setColor(Color.BLACK);
						g.drawRect(x, y, SIZE, SIZE);
						g.drawLine(x, y, x+SIZE, y+SIZE);
						g.drawLine(x+SIZE, y, x, y+SIZE);
					} else if (obj instanceof Goal) {
						g.setColor(Color.GREEN);
						g.fillRect(x, y, SIZE, SIZE);
					}
				}
				
				int x = boardObj.y()*SIZE;
				int y = boardObj.x()*SIZE;
				if (boardObj instanceof Wall) {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(x, y, SIZE, SIZE);
				}
			}
		}
	}
	
	public SokobanGame getGame() {
		return sg;
	}
}
