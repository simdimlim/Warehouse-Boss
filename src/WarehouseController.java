import java.awt.event.*;
import java.io.*;
import sun.audio.*;
import javax.swing.Timer;

/**
 * A key listener that detects key presses and updates the game accordingly.
 */
public class WarehouseController implements KeyListener,ActionListener {
     
    private WarehouseGame g;
    private WarehouseView view;
    Timer t = new Timer(5,this);
    int x=0,y=0,velx=0,vely=0;
    /**
     * Constructor for WarehouseController.
     * 
     * @param g The warehouse game
     * @param view The warehouse view
     */
    public WarehouseController(WarehouseGame g, WarehouseView view) {
        this.g = g;
        this.view = view;
        t.start();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(x < 0)
    	{
    		velx=0;
    		x = 0;		
    	}

    	if(x > view.getWidth())
    	{
    		velx=0;
    		x = view.getWidth();		
    	}

    	if(y < 0)
    	{
    		vely=0;
    		y = 0;		
    	}

    	if(y > view.getHeight())
    	{
    		vely=0;
    		y = view.getHeight();		
    	}


    	x += velx;
    	y += vely;
//    	view.repaint();
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
				vely = -1;
				velx = 0;
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
				vely = 0;
				velx = 1;
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
				vely = 0;
				velx = -1;
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
				vely = 1;
				velx = 0;
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
		if(g.boxCornered()){
			try {
				playCorneredSound();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		view.repaint();
    }
    

	@Override
    /**
     * Handle any key let up.
     */
    public void keyReleased(KeyEvent e) {
        // Add key up stuff here.
    	velx=0;
    	vely=0;
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
    /**
     * Plays a sound when the box is in a corner and not on goal
     * 
     * @throws Exception
     */
    public void playCorneredSound() throws Exception{
        String gongFile = "src/sounds/aww.wav";
        InputStream in = new FileInputStream(gongFile);
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

 
}