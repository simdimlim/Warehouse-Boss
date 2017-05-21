import java.awt.*;
import java.util.*;

import javax.swing.*;

public class SokobanView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int size = 60;
	private SokobanGame sg;
	private Image boxOnGoal;
	
	public SokobanView(SokobanGame sg) {
		this.sg = sg;
		addKeyListener(new SokobanController(sg, this));
		setFocusable(true);
		boxOnGoal = new ImageIcon(this.getClass().getResource("/images/box_on_goal.jpg")).getImage();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintLevel(g);
	}
	
	public void paintLevel(Graphics g) {
		ArrayList<SokobanObject> all = sg.getAll();
		
		for (int i = 0; i < all.size(); i++) {
			SokobanObject obj = all.get(i);
			int x = obj.x()*size;
			int y = obj.y()*size;
			
			if (obj instanceof Player) {
				g.drawImage(obj.getImage(), x, y, size, size, this);
			} else if (obj instanceof Box) {
				if (sg.isBoxOnGoal(obj)) {
					g.drawImage(boxOnGoal, x, y, size, size, this);
				} else {
					g.drawImage(obj.getImage(), x, y, size, size, this);
				}
			} else if (obj instanceof Goal) {
				g.drawImage(obj.getImage(), x, y, size, size, this);
			} else if (obj instanceof Wall) {
				g.drawImage(obj.getImage(), x, y, size, size, this);
			}
		}
		
		if (sg.isComplete()) {
			g.setColor(Color.WHITE);
			g.drawString("Congratulations! ", 30, 30);
			
			Thread newLevelDelay = new Thread(new Runnable() {
			    public void run() {
		            try {
		                Thread.sleep(1500);
		                sg.newLevel();
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
			    }
			});
			
			newLevelDelay.start();
		}
	}
	
	public SokobanGame getGame() {
		return sg;
	}
	
	public void scale () {
		size -= 10;
	}
}
