import java.awt.*;
import javax.swing.*;

public class WarehouseBoss extends JFrame{
	
	/**
	 * Constructor for the Warehouse object
	 * Handles GUI operations.
	 */
	public WarehouseBoss() {
		setLayout(new BorderLayout());
		WarehouseGame g = new WarehouseGame();
		WarehouseView view = g.getView();
		add(view, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Warehouse Boss");
		int size = view.getTileSize();
		getContentPane().setPreferredSize(new Dimension(g.getWidth()*size, g.getHeight()*size));
		pack();
	}
	
	/**
	 * Main function for WarehouseBoss sets off rest of the program.
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
        WarehouseBoss warehouse = new WarehouseBoss();
        warehouse.setVisible(true);
    }
}
