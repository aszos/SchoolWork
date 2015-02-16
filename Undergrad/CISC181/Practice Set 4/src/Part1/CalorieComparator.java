package Part1;
import java.util.Comparator;

public class CalorieComparator implements Comparator<Object>
{

	public int compare(Edible a, Edible b) 
	{
		if(a.getCalories() == b.getCalories())
		{
			return 0;
		}
		else if(a.getCalories() > b.getCalories())
		{
			return 1;
			
		}
		return -1;
	}

	public int compare(Object o1, Object o2) 
	{
		return compare((Edible) o1, (Edible) o2);
	}
}
