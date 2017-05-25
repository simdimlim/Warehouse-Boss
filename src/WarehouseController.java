import java.awt.event.*;
import java.io.*;
import sun.audio.*;

/**
 * A key listener that handles the maze key input and frame delay.
 *
 */
public class WarehouseController implements KeyListener {
     
    private WarehouseGame sb;
    private WarehouseView sv;
    
    public WarehouseController(WarehouseGame sb, WarehouseView sv) {
        this.sb = sb;
        this.sv = sv;
    }
 
    @Override
    /**
     * Handle any key pressed down.
     */
    public void keyPressed(KeyEvent e) {
    	if (sb.isSleeping()) {
    		return;
    	}
    	int key = e.getKeyCode();
		Player p = sb.getPlayer();
		Direction up = Direction.UP;
		Direction down = Direction.DOWN;
		Direction left = Direction.LEFT;
		Direction right = Direction.RIGHT;
		if (key == KeyEvent.VK_UP) {
			if (!sb.hitWall(p, up) && !sb.hitBox(up)) {
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
			if (!sb.hitWall(p, right) && !sb.hitBox(right)) {
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
			if (!sb.hitWall(p, left) && !sb.hitBox(left)) {
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
			if (!sb.hitWall(p, down) && !sb.hitBox(down)) {
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
			sb.restartLevel();
		} else if (key == KeyEvent.VK_N) {
			sb.newLevel();
		}
		sv.repaint();
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
    
    public void playCollisionSound() throws Exception{
        String gongFile = "src/sounds/collisionSound.wav";
        InputStream in = new FileInputStream(gongFile);
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }
    public void playMoveSound() throws Exception{
        String gongFile = "src/sounds/tap.wav";
        InputStream in = new FileInputStream(gongFile);
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

 
}