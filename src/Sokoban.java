import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;

public class Sokoban extends JFrame{
	public Sokoban() {
		Grid g = new Grid(6, 15);
		try {
			g.generateLevel(new FileReader("level1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SokobanGame sg = new SokobanGame(g);
		add(sg);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Warehouse Boss");
		getContentPane().setPreferredSize(new Dimension(500, 500));
		pack();
	}
	
	public static void main(String[] args) {
        Sokoban sokoban = new Sokoban();
        sokoban.setVisible(true);
    }
}
