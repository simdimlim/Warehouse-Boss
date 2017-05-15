import java.awt.Dimension;

import javax.swing.JFrame;

public class Sokoban extends JFrame{
	private int SIZE = 60;
	public Sokoban() {
		SokobanGame sg = new SokobanGame();
		add(sg.getSView());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Warehouse Boss");
		getContentPane().setPreferredSize(new Dimension(sg.getWidth()*SIZE, sg.getHeight()*SIZE));
		pack();
	}
	
	public static void main(String[] args) {
        Sokoban sokoban = new Sokoban();
        sokoban.setVisible(true);
    }
}
