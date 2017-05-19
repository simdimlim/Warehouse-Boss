import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener {
	
	private JButton restart;
	private JButton newLevel;
	private SokobanGame sg;
	
	public ButtonPanel(SokobanGame sg) {
		this.sg = sg;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		restart = new JButton("restart level");		
		newLevel = new JButton("new level");
		
		newLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
		restart.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		restart.setActionCommand("restart");
		newLevel.setActionCommand("new");
		
		restart.addActionListener(this);
		newLevel.addActionListener(this);
		
		restart.setFocusable(false);
		newLevel.setFocusable(false);
		
		add(restart);
		add(newLevel);
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
