import java.awt.*;
import javax.swing.*;

public class Sokoban extends JFrame{
	private int SIZE = 60;
	public Sokoban() {
		setLayout(new BorderLayout());
		SokobanGame sg = new SokobanGame();
		add(sg.getSView(), BorderLayout.CENTER);
		ButtonPanel bp = new ButtonPanel(sg);
		add(bp, BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Warehouse Boss");
		getContentPane().setPreferredSize(new Dimension(sg.getWidth()*SIZE+110, sg.getHeight()*SIZE));
		pack();
	}
	
	public static void main(String[] args) {
        Sokoban sokoban = new Sokoban();
        sokoban.setVisible(true);
    }
}
