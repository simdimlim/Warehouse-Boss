import java.awt.*;
import javax.swing.*;

public class Warehouse extends JFrame{
	
	/**
	 * Constructor for the Warehouse object
	 * Handles GUI operations.
	 */
	public Warehouse() {
		setLayout(new BorderLayout());
		WarehouseGame sg = new WarehouseGame();
		WarehouseView sv = sg.getSView();
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
        Warehouse Warehouse = new Warehouse();
        Warehouse.setVisible(true);
    }
}
