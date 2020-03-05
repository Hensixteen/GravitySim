

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class AppTimer extends Timer {
	
	//Instance variables
	private MainPanel mainPanel;
	
	//Constructor
	public AppTimer(int delay, MainPanel panel) {
		
		super(delay, null);
		mainPanel = panel;
		this.addActionListener(new TimerListener());
		
	}
	
	//Calls the MainPanel's moveAll method each time the timer goes off
	private class TimerListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			mainPanel.moveAll();
			
		}
		
	}
}
