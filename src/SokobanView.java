import java.awt.*;
import java.util.*;

import javax.swing.*;

public class SokobanView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int SIZE = 60;
	private SokobanGame sg;
	private Image boxOnGoal;
	
	public SokobanView(SokobanGame sg) {
		this.sg = sg;
		addKeyListener(new SokobanController(sg, this));
		setFocusable(true);
		boxOnGoal = new ImageIcon(this.getClass().getResource("/images/box_on_goal.jpg")).getImage();
		boxOnGoal = new ImageIcon(boxOnGoal.getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH)).getImage();
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
				if (sg.isBoxOnGoal(obj)) {
					g.drawImage(boxOnGoal, x, y, SIZE, SIZE, this);
				} else {
					g.drawImage(obj.getImage(), x, y, this);
				}
			} else if (obj instanceof Goal) {
				g.drawImage(obj.getImage(), x, y, this);
			} else if (obj instanceof Wall) {
				g.drawImage(obj.getImage(), x, y, this);
			}
		}
		
		if (sg.isComplete()) {
			g.setColor(Color.WHITE);
			g.drawString("Congratulations! ", 30, 30);
			int countdown = 1;
			try{
		        while (countdown < 5){
		            ++countdown;
					Thread.sleep(1000);
				}
			}
			catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
				
			sg.newLevel();	
	        
			
		}
	}
	
	public SokobanGame getGame() {
		return sg;
	}
}
