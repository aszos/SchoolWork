package Part1;

public class Banana extends Fruit implements Edible
{
	public Banana(double weight)
	{
		super("Yellow", weight);
	}
	
	public double getCalories() 
	{
		return this.getWeight() * 10;
	}

	public String toString()
	{
		return "Banana";
	}
	
}
