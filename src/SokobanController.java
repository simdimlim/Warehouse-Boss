import java.awt.event.*;
 
/**
 * A key listener that handles the maze key input and frame delay.
 *
 */
public class SokobanController implements KeyListener {
     
    private SokobanGame sb;
    private SokobanView sv;
    
    public SokobanController(SokobanGame sb, SokobanView sv) {
        this.sb = sb;
        this.sv = sv;
    }
 
    @Override
    /**
     * Handle any key pressed down.
     */
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
		Player p = sb.getPlayer();
		Direction up = Direction.UP;
		Direction down = Direction.DOWN;
		Direction left = Direction.LEFT;
		Direction right = Direction.RIGHT;
		if (key == KeyEvent.VK_UP) {
			if (!sb.hitWall(p, up)) {
				if (!sb.hitBox(up)) {
					p.move(up);
					sb.turns++;
				}
			}
		} else if (key == KeyEvent.VK_RIGHT) {
			if (!sb.hitWall(p, right)) {
				if (!sb.hitBox(right)) {
					p.move(right);
					sb.turns++;
				}
			}
		} else if (key == KeyEvent.VK_LEFT) {
			if (!sb.hitWall(p, left)) {
				if (!sb.hitBox(left)) {
					p.move(left);
					sb.turns++;
				}
			}
		} else if (key == KeyEvent.VK_DOWN) {
			if (!sb.hitWall(p, down)) {
				if (!sb.hitBox(down)) {
					p.move(down);
					sb.turns++;
				}
			}
		} else if (key == KeyEvent.VK_R) {
			sb.restartLevel();
			sb.count--;
		} else if (key == KeyEvent.VK_N) {
			sb.newLevel();
			sb.count--;
		}
		sb.checkY();
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
 
}