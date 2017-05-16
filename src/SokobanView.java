import java.awt.*;
import java.util.*;

import javax.swing.*;

public class SokobanView extends JPanel {
	private int SIZE = 60;
	private SokobanGame sg;
	
	public SokobanView(SokobanGame sg) {
		this.sg = sg;
		addKeyListener(new SokobanController(sg, this));
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintLevel(g);
	}
	
	public void paintLevel(Graphics g) {
		ArrayList<SokobanObject> all = sg.getAll();
		
		for (int i = 0; i < all.size(); i++) {
			SokobanObject obj = all.get(i);
			int x = obj.x()*SIZE;
			int y = obj.y()*SIZE;
			
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
			} else if (obj instanceof Wall) {
				g.setColor(Color.DARK_GRAY);
				g.fillRect(x, y, SIZE, SIZE);
			}
		}
		
		if (sg.isComplete()) {
			System.out.println("Congratulations!");
		}
	}
	
	public SokobanGame getGame() {
		return sg;
	}
}
