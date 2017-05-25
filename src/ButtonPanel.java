import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener {
	
	private JButton restart;
	private JButton newLevel;
	private WarehouseGame sg;
	
	public ButtonPanel(WarehouseGame sg) {
		this.sg = sg;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		
		restart = new JButton("restart level");		
		newLevel = new JButton("new level");
		
		restart.setActionCommand("restart");
		newLevel.setActionCommand("new");
		
		restart.addActionListener(this);
		newLevel.addActionListener(this);
		
		restart.setFocusable(false);
		newLevel.setFocusable(false);
		
		add(restart, gbc);
		add(newLevel, gbc);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("restart")) {
			sg.restartLevel();
		} else if (action.equals("new")) {
			sg.newLevel();
		}
	}
}
