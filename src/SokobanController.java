import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
/**
 * A key listener that handles the maze key input and frame delay.
 *
 */
public class SokobanController implements KeyListener {
     
    private SokobanGame sb;
    
    public SokobanController(SokobanGame sb) {
        this.sb = sb;
    }
 
    @Override
    /**
     * Handle any key pressed down.
     */
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
		Player p = sb.getPlayer();
		Grid g = sb.getGrid();
		Box b = sb.getBox();
		Direction up = Direction.UP;
		Direction down = Direction.DOWN;
		Direction left = Direction.LEFT;
		Direction right = Direction.RIGHT;
		
		if (key == KeyEvent.VK_UP) {
			if (p.checkValidMove(up, g)){
		    	p.move(up, g);
		    	if(sb.canPush(p, sb.b, up)){
		    		sb.getBox().move(up, g);
		    	} 
		    	if(sb.isPushing(p, b)){
		    		p.move(down, g);
		    	}
			}
		} else if (key == KeyEvent.VK_RIGHT) {
			if (p.checkValidMove(right, g)){
		    	p.move(right, g);
		    	if(sb.canPush(p,b,right)){
		    		b.move(right, g);
		    	} 
		    	if(sb.isPushing(p, b)){
		    		p.move(left, g);
		    	}
			}
		} else if (key == KeyEvent.VK_LEFT) {
			if (p.checkValidMove(left, g)) {
		    	p.move(left, g);
		    	if(sb.canPush(p,b,left)){
		    		b.move(left, g);
		    	} 
		    	if(sb.isPushing(p, b)){
		    		p.move(right, g);
		    	}
			}
		} else if (key == KeyEvent.VK_DOWN) {
			if (p.checkValidMove(down, g)){
		    	p.move(down, g);
		    	if(sb.canPush(p,b,down)){
		    		b.move(down, g);
		    	} 
		    	if(sb.isPushing(p, b)){
		    		p.move(up, g);
		    	}
			}
		}
		sb.getSView().printGame();
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