import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;

public class Sokoban extends JFrame{
	private int SIZE = 60;
	public Sokoban() {
		Grid g = new Grid(6, 15);
		try {
			g.generateLevel(new FileReader("level1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SokobanGame sg = new SokobanGame(g);
		add(sg.getSView());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Warehouse Boss");
		getContentPane().setPreferredSize(new Dimension(sg.getGrid().getNumCol()*SIZE, sg.getGrid().getNumRow()*SIZE));
		pack();
	}
	
	public static void main(String[] args) {
        Sokoban sokoban = new Sokoban();
        sokoban.setVisible(true);
    }
}
