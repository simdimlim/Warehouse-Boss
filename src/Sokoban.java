import java.awt.*;
import javax.swing.*;

public class Sokoban extends JFrame{
	
	/**
	 * Constructor for the Sokoban object
	 * Handles GUI operations.
	 */
	public Sokoban() {
		setLayout(new BorderLayout());
		SokobanGame sg = new SokobanGame();
		SokobanView sv = sg.getSView();
		add(sv, BorderLayout.CENTER);
		ButtonPanel bp = new ButtonPanel(sg);
		add(bp, BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Warehouse Boss");
		int size = sv.getTileSize();
		getContentPane().setPreferredSize(new Dimension(sg.getWidth()*size+110, sg.getHeight()*size));
		pack();
	}
	
	/**
	 * Main function for WarehouseBoss sets off rest of the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
        Sokoban sokoban = new Sokoban();
        sokoban.setVisible(true);
    }
}
