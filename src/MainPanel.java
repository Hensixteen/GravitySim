

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class MainPanel extends JPanel {
	
	private AppTimer _timer;
	private JFrame _frame;
	private BodyHandler _bodyHandler;
	
	private double objectMass = 5.000*Math.pow(10, 24);
	private double objectWidth = 30;
	private DecimalFormat df = new DecimalFormat("0.###E0");
	

	public MainPanel(JFrame frame) {
		
		super(null, true);
		this.setBackground(new Color(9, 0, 54));
		this.setSize(1000, 1000);
		this.setPreferredSize(new Dimension(1000, 1000));
		
		Listener listener = new Listener();
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.addKeyListener(listener);
		this.setFocusable(true);
		
		_frame = frame;
		
		_bodyHandler = new BodyHandler(5);
		
		_timer = new AppTimer(1, this);
		_timer.start();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		_bodyHandler.paintAll(brush);
		brush.setColor(Color.WHITE);
		brush.setFont(new Font("SansSerif", Font.PLAIN, 15));
		brush.drawString("Press 'H' for help", 880, 20);
		brush.drawString("Mass: " + df.format(objectMass) + " kg", 10, 20);
		
	}
	
	public void moveAll() {
		
  		repaint();
  		_bodyHandler.moveAll();
  		
  	}
	
	private class Listener implements MouseListener, MouseMotionListener, KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			
				case 38:
					objectMass *= 1.05;
					objectWidth *= 1.0025;
					break;
					
				case 40:
					objectMass *= 0.95;
					objectWidth *= 0.9975;
					break;
					
				case 72:
					JOptionPane.showMessageDialog(_frame, "Gravity Sandbox\nClick to create a cosmic body\n"
							+ "Use arrow keys up and down to adjust the body's mass\nUse number keys to open different scenarios:\n"
							+ "	  1 - Inner solar system\n  2 - Binary star system\n  3 - Earth and moon\nPress 'R' to reset");
					break;
				
				case 82:
					_bodyHandler.removeAll();
					BodyHandler.MetersToPixels = 5000000;
					break;
			
				case 49:
					_bodyHandler.removeAll();
					BodyHandler.MetersToPixels = 500000000;
					_bodyHandler.addBody(500, 500, 70, 70, 0, 0, Color.YELLOW, 1.9891*Math.pow(10, 30));
					_bodyHandler.addBody(592, 500, 20, 20, 0, -58.98, Color.ORANGE, 3.33011*Math.pow(10, 23));
					_bodyHandler.addBody(714.96, 500, 25, 25, 0, -35.26, Color.YELLOW, 4.8675*Math.pow(10, 24));
					_bodyHandler.addBody(794.18, 500, 30, 30, 0, -30.29, Color.BLUE, 5.9723*Math.pow(10, 24));
					_bodyHandler.addBody(913.24, 500, 30, 30, 0, -26.50, Color.RED, 6.4171*Math.pow(10, 23));
					break;
				
				case 50:
					_bodyHandler.removeAll();
					BodyHandler.MetersToPixels = 500000000;
					_bodyHandler.addBody(450, 500, 70, 70, 0, -35, Color.YELLOW, 1.9891*Math.pow(10, 30));
					_bodyHandler.addBody(550, 500, 70, 70, 0, 35, Color.YELLOW, 1.9891*Math.pow(10, 30));
					break;
			
				case 51:
					_bodyHandler.removeAll();
					BodyHandler.MetersToPixels = 900000;
					_bodyHandler.addBody(500, 500, 70, 70, 0, 0.013, Color.BLUE, 5.9723*Math.pow(10, 24));
					_bodyHandler.addBody(903.66666, 500, 20, 20, 0, -1.082, Color.WHITE, 7.346*Math.pow(10, 22));
					break;
			
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {

			_bodyHandler.addBody(e.getX(), e.getY(), objectWidth, objectWidth, 0, 0, Color.RED, objectMass);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
