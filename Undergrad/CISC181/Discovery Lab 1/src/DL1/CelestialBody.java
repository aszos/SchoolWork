package DL1;

import edu.udel.jatlas.gameframework.DirectionVector;
import edu.udel.jatlas.gameframework.Position;

public class CelestialBody 
{
	private Position bodyPosition;
	private DirectionVector bodyDirection;
	private double mass;
	
	public CelestialBody(double mass, Position bodyPosition, DirectionVector bodyDirection) 
	{
		this.bodyPosition = bodyPosition;
		this.bodyDirection = bodyDirection;
		this.mass = mass;
	}
	
	public Position getPosition() 
	{
		return bodyPosition;
	}
	
	public void setPosition(Position bodyPosition) 
	{
		this.bodyPosition = bodyPosition;
	}
	
	public DirectionVector getDirection() 
	{
		return bodyDirection;
	}
	
	public void setDirection(DirectionVector bodyDirection) 
	{
		this.bodyDirection = bodyDirection;
	}
	
	public double getMass() 
	{
		return mass;
	}
	
	public void setMass(double mass) 
	{
		this.mass = mass;
	}
}
