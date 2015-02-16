package Part1;

public class NavalOrange extends Orange
{

	public NavalOrange(double weight)
	{
		super(weight);
	}
	
	public String toString()
	{
		if(this.getWeight() >= 100)
		{
			return "ReallyBigNavalOrange";
		}
		
		return "NavalOrange";
	}
	
	public double getCalories() 
	{
		return (super.getCalories() * 1.1);
	}

}
