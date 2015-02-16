package Part1;

public class Snowberry extends Fruit
{

	public Snowberry() 
	{
		super("White", 0.117);
	}
	
	public String toString()
	{
		return "Snowberry";
	}
	
	double getCalories()
	{
		return 1;
	}
}
