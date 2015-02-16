package DL1;

import edu.udel.jatlas.gameframework.DirectionVector;
import edu.udel.jatlas.gameframework.Position;

public class Sun extends CelestialBody
{
	final static Position SUN_POSITION = new Position(0, 0);
	final static DirectionVector SUN_DIRECTION = new DirectionVector(0, 0);
	final static double SUN_MASS = 1.9891E30;
	
	public Sun() 
	{
		super(SUN_MASS, SUN_POSITION, SUN_DIRECTION);
	}

	public Position getPosition()
	{
		return SUN_POSITION;
	}

	public DirectionVector getDirection() 
	{
		return SUN_DIRECTION;
	}

	public double getMass()
	{
		return SUN_MASS;
	}
}
