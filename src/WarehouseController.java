import java.awt.event.*;
import java.io.*;
import sun.audio.*;

/**
 * A key listener that detects key presses and updates the game accordingly.
 */
public class WarehouseController implements KeyListener {
     
    private WarehouseGame g;
    private WarehouseView view;
    
    /**
     * Constructor for WarehouseController.
     * 
     * @param g The warehouse game
     * @param view The warehouse view
     */
    public WarehouseController(WarehouseGame g, WarehouseView view) {
        this.g = g;
        this.view = view;
    }
 
    @Override
    /**
     * Handle any key pressed down.
     */
    public void keyPressed(KeyEvent e) {
    	if (g.isSleeping()) {
    		return;
    	}
    	int key = e.getKeyCode();
		Player p = g.getPlayer();
		Direction up = Direction.UP;
		Direction down = Direction.DOWN;
		Direction left = Direction.LEFT;
		Direction right = Direction.RIGHT;
		if (key == KeyEvent.VK_UP) {
			if (!g.hitWall(p, up) && !g.hitBox(up)) {
				try {
					playMoveSound();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.move(up);
			} else
				try {
					playCollisionSound();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} else if (key == KeyEvent.VK_RIGHT) {
			if (!g.hitWall(p, right) && !g.hitBox(right)) {
				try {
					playMoveSound();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.move(right);
			} else
				try {
					playCollisionSound();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} else if (key == KeyEvent.VK_LEFT) {
			if (!g.hitWall(p, left) && !g.hitBox(left)) {
				try {
					playMoveSound();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.move(left);
			} else
				try {
					playCollisionSound();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} else if (key == KeyEvent.VK_DOWN) {
			if (!g.hitWall(p, down) && !g.hitBox(down)) {
				try {
					playMoveSound();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.move(down);
			} else
				try {
					playCollisionSound();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} else if (key == KeyEvent.VK_R) {
			g.restartLevel();
		} else if (key == KeyEvent.VK_N) {
			g.newLevel();
		}
		view.repaint();
    }
 
    @Override
    /**
     * Handle any key let up.
     */
    public void keyReleased(KeyEvent e) {
        // Add key up stuff here.
    }
 
    @Override
    /**
     * Handle when a key has been "typed".
     */
    public void keyTyped(KeyEvent e) {
         
    }
    
    /**
     * Plays the collision sound when the player cannot move.
     * 
     * @throws Exception
     */
    public void playCollisionSound() throws Exception{
        String gongFile = "src/sounds/collisionSound.wav";
        InputStream in = new FileInputStream(gongFile);
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }
    
    /**
     * Plays a sound every time the player moves.
     * 
     * @throws Exception
     */
    public void playMoveSound() throws Exception{
        String gongFile = "src/sounds/tap.wav";
        InputStream in = new FileInputStream(gongFile);
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

 
}