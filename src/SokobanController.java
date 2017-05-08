import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
/**
 * A key listener that handles the maze key input and frame delay.
 *
 */
public class SokobanController implements KeyListener, ActionListener {
     
    private SokobanGame p;
    private int playerUp;
    private int playerRight;
    private int playerDown;
    private int playerLeft;
    
    /**
     * Creates a new key listener..
     * @param p
     * The main maze program.
     */
    public SokobanController(SokobanGame p) {
        this.p = p;
        playerUp = KeyEvent.VK_UP;
        playerRight = KeyEvent.VK_RIGHT;
        playerDown = KeyEvent.VK_DOWN;
        playerLeft = KeyEvent.VK_LEFT;
    }
 
    @Override
    /**
     * Handle any key pressed down.
     */
    public void keyPressed(KeyEvent e) {
//        p.keyPress(e);
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
 
    @Override
    /**
     * Handles refresh updates.
     */
    public void actionPerformed(ActionEvent e) {
//        p.updateFrame(e);
    }
 
}