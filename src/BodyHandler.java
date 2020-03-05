

import java.awt.Color;
import java.awt.Graphics2D;

public class BodyHandler {
	
	public static double G = 6.67430*(Math.pow(10, -11));
	public static double MetersToPixels = 5000000;
	
	private Body _bodies[];
	private int _bodiesNum;
	private int _size;
	private int _bodyToBeRemoved = 999;

	public BodyHandler(int size) {
		
		_size = size;
		_bodies = new Body[_size];
		_bodiesNum = 0;
		
	}
	
	public void addBody(double x, double y, double width, double height, double xVelocity, double yVelocity, Color color, double mass) {
		
		_bodies[_bodiesNum] = new Body(x - (width / 2), y - (height / 2), width, height, xVelocity, yVelocity, _bodiesNum, color, mass);
		_bodiesNum++;
			
		if(_bodiesNum == _size) {
				
			_size = _bodiesNum + _bodiesNum/2;
			Body _tempBodies[] = new Body[_size];
				
			for(int i = 0; i < _bodiesNum; i++) {
					
				_tempBodies[i] = _bodies[i];
					
			}
				
			_bodies = _tempBodies;
			
		}
		
	}
	
	public void removeBody(int bodyID) {
		
		if(bodyID != 999) {
			
			_bodyToBeRemoved = bodyID;
			_bodies[bodyID] = null;
			
			for(int i = bodyID; i < _bodies.length; i++) {
			
				if(i < _bodies.length-1)
					_bodies[i] = _bodies[i+1];
				
				else
					_bodies[i] = null;
			
			}
			
			_bodyToBeRemoved = 999;
			
		}
		
	}
	
	public void removeAll() {
		
		_size = 5;
		_bodies = new Body[_size];
		_bodiesNum = 0;
		
	}
	
	public void moveAll() {
		
		for(int i = 0; i < _bodiesNum; i++) {
			
			for(int j = 0; j < _bodiesNum; j++) {
				
				if(i != j && i != _bodyToBeRemoved && j != _bodyToBeRemoved && _bodies[i] != null && _bodies[j] != null) {
					
					_bodies[i].calculateAccelerationOfFall(_bodies[j].getX() + (_bodies[j].getWidth() / 2), _bodies[j].getY() + (_bodies[j].getHeight() / 2), _bodies[j].getMass(), _bodies[j].getBodyID());
					
					if (_bodies[i].contains(_bodies[j].getRec())) {
						
						if(_bodies[i].getMass() > _bodies[j].getMass())
							removeBody(j);
						
						else
							removeBody(i);
						
					}
					
				}
			
			}
			
		}
		
		for(int i = 0; i < _bodiesNum; i++) {
		
			if(_bodies[i] != null)
				
				_bodies[i].move();
			
		}
		
	}
	
	public void paintAll(Graphics2D brush) {
		
		for(int i = 0; i < _bodiesNum; i++) {
			
			if(i!=_bodyToBeRemoved && _bodies[i] != null)
				
				_bodies[i].paint(brush);
			
		}
		
	}

}
