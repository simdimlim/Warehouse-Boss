import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
/**
 * A key listener that handles the maze key input and frame delay.
 *
 */
public class SokobanController implements KeyListener, ActionListener {
     
    private GridProgram p;
     
    /**
     * Creates a new key listener..
     * @param p
     * The main maze program.
     */
    public SokobanController(GridProgram p) {
        this.p = p;
    }
 
    @Override
    /**
     * Handle any key pressed down.
     */
    public void keyPressed(KeyEvent e) {
        p.keyPress(e);
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
        p.updateFrame(e);
    }
 
}