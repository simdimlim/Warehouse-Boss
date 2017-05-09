import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
 
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
		ArrayList<SokobanObject> all = sb.getAll();
		Direction up = Direction.UP;
		Direction down = Direction.DOWN;
		Direction left = Direction.LEFT;
		Direction right = Direction.RIGHT;
		
		if (key == KeyEvent.VK_UP) {
//			System.out.println("up");
			if (!sb.hitWall(p, up)) {
				if (!sb.hitBox(up)) {
					p.move(up);
				}
			}
		} else if (key == KeyEvent.VK_RIGHT) {
//			System.out.println("right");
			if (!sb.hitWall(p, right)) {
				if (!sb.hitBox(right)) {
					p.move(right);
				}
			}
		} else if (key == KeyEvent.VK_LEFT) {
//			System.out.println("left");
			if (!sb.hitWall(p, left)) {
				if (!sb.hitBox(left)) {
					p.move(left);
				}
			}
		} else if (key == KeyEvent.VK_DOWN) {
//			System.out.println("down");
			if (!sb.hitWall(p, down)) {
				if (!sb.hitBox(down)) {
					p.move(down);
				}
			}
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
 
}