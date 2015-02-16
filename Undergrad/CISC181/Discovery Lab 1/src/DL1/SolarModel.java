package DL1;

/*
 Created by Alexander Szostek and Jeremy Ake
 Created on 3/8/2014
*/

public class SolarModel 
{
	private Sun theSun;
	private Planet planetA;
	private Planet planetB;
	private double tickLength;
		
	public SolarModel(double tickLength, Sun theSun, Planet planetA) 
	{
		this(tickLength, theSun, planetA, null);
	}
	
	public SolarModel(double tickLength, Sun theSun, Planet planetA, Planet planetB)
	{
		this.tickLength = tickLength;
		this.theSun = theSun;
		this.planetA = planetA;
		this.planetB = planetB;
	}

	public void onTick()
	{
		this.planetA.updatePosition(this.tickLength);
		this.planetA.updateDirectionFromSun(theSun, tickLength);
		if(planetB != null)
		{
			this.planetB.updatePosition(this.tickLength);
			this.planetB.updateDirectionFromSun(theSun, tickLength);
		}
	}

	public Sun getSun() 
	{
		return theSun;
	}

	public void setSun(Sun theSun) 
	{
		this.theSun = theSun;
	}

	public Planet getPlanet() 
	{
		return planetA;
	}

	public void setPlanet(Planet planet) 
	{
		this.planetA = planet;
	}
	
	public Planet getOtherPlanet() 
	{
		return planetB;
	}

	public void setOtherPlanet(Planet planet) 
	{
		this.planetB = planet;
	}
	

	public double getTickLength() 
	{
		return tickLength;
	}

	public void setTickLength(double tickLength) 
	{
		this.tickLength = tickLength;
	}

}
