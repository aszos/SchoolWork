package Part1;

public abstract class Fruit 
{
	private String color;
	private double weight;
	
	abstract double getCalories();
	
	public Fruit(String color, double weight)
	{
		this.color = color;
		this.weight = weight;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public double getWeight()
	{
		return weight;
	}	
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Fruit)
		{
			return
			(this.getColor().equals(((Fruit)obj).getColor()) 
					&& 
			(this.getWeight() == ((Fruit)obj).getWeight()));
		}
		return false;
	}
}
