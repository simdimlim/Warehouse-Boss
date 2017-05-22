import java.awt.*;
import java.util.*;

import javax.swing.*;

public class SokobanView extends JPanel {
	/**
	 * 
	 */
	private int size = 75;
	private int currSize = 0;
	private SokobanGame sg;
	private Image boxOnGoal;
	private Image levelComplete;
	private Image moves;
	
	public SokobanView(SokobanGame sg) {
		this.sg = sg;
		addKeyListener(new SokobanController(sg, this));
		setFocusable(true);
		boxOnGoal = new ImageIcon(this.getClass().getResource("/images/box_on_goal.jpg")).getImage();
		levelComplete = new ImageIcon(this.getClass().getResource("/images/level_complete.png")).getImage();
		moves = new ImageIcon(this.getClass().getResource("/images/moves.png")).getImage();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintLevel(g);
	}
	
	public void paintLevel(Graphics g) {
		if (currSize == 0) {
			currSize = size*sg.getWidth();
		}
		
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
		
//		g.setColor(Color.WHITE);
//        g.fillRect(sg.getWidth()*size-105, 15, 75, 20);
//        g.setColor(Color.BLACK);
//		g.drawString("Moves: " + sg.moveNum(), sg.getWidth()*size-100, 30);
		
		g.drawImage(moves, currSize-150, 10, moves.getWidth(null), moves.getHeight(null), this);
		g.setFont(new Font("Arial", Font.PLAIN, 22));
		g.setColor(Color.WHITE);
		g.drawString("" + sg.moveNum(), currSize-65, 31);
		
		if (sg.isComplete()) {
			g.drawImage(levelComplete, currSize/5, currSize/3+20, levelComplete.getWidth(null), levelComplete.getHeight(null), this);
			
			sg.setSleeping(true);
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
	
	public void scale() {
		size = currSize/sg.getWidth();
	}
	
	public int getTileSize() {
		return size;
	}
}
