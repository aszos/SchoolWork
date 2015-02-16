package Part1;

public class Orange extends Fruit implements Edible
{
	
	public Orange(double weight)
	{
		super("Orange", weight);
	}
	
	public String toString()
	{
		return "Orange";
	}
	
	public double getCalories()
	{
		return this.getWeight() * 5;
	}
}
