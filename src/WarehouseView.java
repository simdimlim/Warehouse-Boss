import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class WarehouseView extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private int size = 75;
	private int currSize = 0;
	private WarehouseGame sg;
	private Image boxOnGoal;
	private Image levelComplete;
	private Image moves;
	private JButton restart;
	private JButton newLevel;
	
	public WarehouseView(WarehouseGame sg) {
		this.sg = sg;
		addKeyListener(new WarehouseController(sg, this));
		setFocusable(true);
		boxOnGoal = new ImageIcon(this.getClass().getResource("/images/box_on_goal.jpg")).getImage();
		levelComplete = new ImageIcon(this.getClass().getResource("/images/level_complete.png")).getImage();
		moves = new ImageIcon(this.getClass().getResource("/images/moves.png")).getImage();
		addButtons();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintLevel(g);
	}
	
	public void paintLevel(Graphics g) {
		if (currSize == 0) {
			currSize = size*sg.getWidth();
		}
		
		ArrayList<WarehouseObject> all = sg.getEntireMap();
		
		for (int i = 0; i < all.size(); i++) {
			WarehouseObject obj = all.get(i);
			if (obj == null) {
				continue;
			}
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
		
		g.drawImage(moves, currSize-150, 10, moves.getWidth(null), moves.getHeight(null), this);
		g.setFont(new Font("Arial", Font.BOLD, 22));
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
	
	public WarehouseGame getGame() {
		return sg;
	}
	
	public void scale(int width) {
		size = currSize/width;
	}
	
	public int getTileSize() {
		return size;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("restart")) {
			sg.restartLevel();
		} else if (action.equals("new")) {
			sg.newLevel();
		}
	}
	
	public void addButtons() {
		restart = new JButton("restart level");		
		newLevel = new JButton("new level");
		
		restart.setActionCommand("restart");
		newLevel.setActionCommand("new");
		
		restart.addActionListener(this);
		newLevel.addActionListener(this);
		
		restart.setFocusable(false);
		newLevel.setFocusable(false);
				
		add(restart);
		add(newLevel);
	}
}
