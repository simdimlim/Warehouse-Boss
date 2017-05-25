import java.awt.*;
import javax.swing.*;

public class WarehouseBoss extends JFrame{
	
	/**
	 * Constructor for the Warehouse object
	 * Handles GUI operations.
	 */
	public WarehouseBoss() {
		setLayout(new BorderLayout());
		WarehouseGame sg = new WarehouseGame();
		WarehouseView sv = sg.getSView();
		add(sv, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Warehouse Boss");
		int size = sv.getTileSize();
		getContentPane().setPreferredSize(new Dimension(sg.getWidth()*size, sg.getHeight()*size));
		pack();
	}
	
	/**
	 * Main function for WarehouseBoss sets off rest of the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
        WarehouseBoss warehouse = new WarehouseBoss();
        warehouse.setVisible(true);
    }
}
