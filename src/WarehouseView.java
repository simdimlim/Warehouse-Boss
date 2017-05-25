import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * WarehouseView is responsible for displaying the current state of the game.
 * It updates the display every time the player moves.
 */
public class WarehouseView extends JPanel implements ActionListener {
	private int size = 75;
	private int currSize = 0;
	private WarehouseGame whg;
	private Image boxOnGoal;
	private Image levelComplete;
	private Image moves;
	private JButton restart;
	private JButton newLevel;
	
	/**
	 * Constructor for WarehouseView.
	 * 
	 * @param whg The warehouse game to display
	 */
	public WarehouseView(WarehouseGame whg) {
		this.whg = whg;
		addKeyListener(new WarehouseController(whg, this));
		setFocusable(true);
		boxOnGoal = new ImageIcon(this.getClass().getResource("/images/box_on_goal.jpg")).getImage();
		levelComplete = new ImageIcon(this.getClass().getResource("/images/level_complete.png")).getImage();
		moves = new ImageIcon(this.getClass().getResource("/images/moves.png")).getImage();
		addButtons();
	}
	
	/**
	 * Updates the board graphics.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintLevel(g);
	}
	
	/**
	 * Updates the board graphics by going through the objects stored in
	 * the entire game map and painting the specific object in its current
	 * position.
	 * 
	 * @param g The graphics used to paint
	 */
	public void paintLevel(Graphics g) {
		if (currSize == 0) {
			currSize = size*whg.getWidth();
		}
		
		ArrayList<WarehouseObject> all = whg.getGameMap().getMap();
		
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
				if (whg.isBoxOnGoal(obj)) {
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
		
		// paints the move counter
		g.drawImage(moves, currSize-150, 10, moves.getWidth(null), moves.getHeight(null), this);
		g.setFont(new Font("Arial", Font.BOLD, 22));
		g.setColor(Color.WHITE);
		g.drawString("" + whg.moveNum(), currSize-65, 31);
		
		// paints a message if the player completes the game
		if (whg.isComplete()) {
			g.drawImage(levelComplete, currSize/5, currSize/3+20, levelComplete.getWidth(null), levelComplete.getHeight(null), this);
			// if the level is complete, pauses the display for 1.5 seconds before displaying the newly generated level
			whg.setSleeping(true);
			Thread newLevelDelay = new Thread(new Runnable() {
			    public void run() {
		            try {
		                Thread.sleep(1500);
		                whg.newLevel();
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
			    }
			});
			
			newLevelDelay.start();
		}
	}
	
	/**
	 * Returns the current warehouse game.
	 * 
	 * @return The warehouse game
	 */
	public WarehouseGame getGame() {
		return whg;
	}
	
	/**
	 * Changes the size of the board's tile.
	 * 
	 * @param width The board's new width
	 */
	public void scale(int width) {
		size = currSize/width;
	}
	
	/**
	 * Returns the size of the tile.
	 * 
	 * @return The tile size
	 */
	public int getTileSize() {
		return size;
	}
	
	/**
	 * If the player presses the "restart level" button or "new level" button,
	 * the view calls the warehouse game to run the appropriate function.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("restart")) {
			whg.restartLevel();
		} else if (action.equals("new")) {
			whg.newLevel();
		}
	}
	
	/**
	 * Adds the buttons to this panel.
	 */
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
