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
				g.drawImage(obj.getImage(), x, y, this);
			} else if (obj instanceof Box) {
				g.drawImage(obj.getImage(), x, y, this);
			} else if (obj instanceof Goal) {
				g.drawImage(obj.getImage(), x, y, this);
			} else if (obj instanceof Wall) {
				g.drawImage(obj.getImage(), x, y, this);
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
