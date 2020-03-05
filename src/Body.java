

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Body {
	
	private Rectangle2D _body;
	private Color _fillColor;
	private double _xVelocity, _yVelocity;
	private double _mass;
	private int _bodyID;

	public Body(double x, double y, double width, double height, double xVelocity, double yVelocity, int bodyID, Color color, double mass) {
		
		_body = new Rectangle2D.Double();
		_body.setFrame(x, y, width, height);
		
		_xVelocity = ((xVelocity * 1000000) / BodyHandler.MetersToPixels);
		_yVelocity = ((yVelocity * 1000000) / BodyHandler.MetersToPixels);
		
		_bodyID = bodyID;
		_mass = mass;
		
		_fillColor = color;
		
	}
	
	public void calculateAccelerationOfFall(double objectX, double objectY, double objectMass, int objectID) {
		
		double distanceX = Math.abs(objectX - (getX() + (getWidth() / 2)));
		double distanceY = Math.abs(objectY - (getY() + (getHeight() / 2)));
		double distance = Math.sqrt((Math.pow(Math.abs(distanceX), 2) + Math.pow(Math.abs(distanceY), 2))) * BodyHandler.MetersToPixels;
		
		double force = BodyHandler.G * ((_mass * objectMass) / Math.pow(distance, 2));
		
		double accelerationOfFall = (force / _mass);
		
		double angleToObject = Math.atan(distanceX/distanceY);
		
		double moveX = (Math.sin(angleToObject) * accelerationOfFall * 1000000) / BodyHandler.MetersToPixels;
		if(getX() + (getWidth()/2) >= objectX)
			moveX *= -1;
		double moveY = (Math.cos(angleToObject) * accelerationOfFall * 1000000) / BodyHandler.MetersToPixels;
		if(getY() + (getHeight()/2) >= objectY)
			moveY *= -1;
		
		_xVelocity += moveX;
		_yVelocity += moveY;
		
	}
	
	public void move() {
		
		_body.setFrame(getX() + _xVelocity, getY() + _yVelocity, _body.getWidth(), _body.getHeight());
		
	}
	
	public double getX() {
		
		return _body.getX();
		
	}
	
	public double getY() {
		
		return _body.getY();
		
	}
	
	public double getWidth() {
		
		return _body.getWidth();
		
	}
	
	public double getHeight() {
		
		return _body.getHeight();
		
	}
	
	public double getMass() {
		
		return _mass;
		
	}
	
	public int getBodyID() {
		
		return _bodyID;
		
	}
	
	public Rectangle2D getRec() {
		
		return _body;
		
	}
	
	public boolean contains(Rectangle2D r) {
		if (_body.contains(r))
			return true;
		else
			return false;
	}
	
	public void paint(Graphics2D brush) {
		
		brush.setColor(_fillColor);
		brush.fillOval((int)getX(), (int)getY(), (int)_body.getWidth(), (int)_body.getHeight());
		
	}

}
