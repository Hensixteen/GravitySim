

import javax.swing.*;

public class App extends JFrame {
	
	private MainPanel _mainPanel;
	
	public App() {
		
		super("Gravity");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		_mainPanel = new MainPanel(this);
		this.add(_mainPanel);
		this.pack();
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		App app = new App();
		
	}

}
