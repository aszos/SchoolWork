package Part2;

import java.util.ArrayList;

public class ArrayStaticMethods 
{
	public static double mean(double[] data)
	{
		double total = 0;
		
		for(double n : data)
		{
			total += n;
		}
		
		return total / data.length;
	}
	
	public static int countWords(char[] data)
	{
		int total = 0;
		
		for(char a : data)
		{
			total += (a == ' ' || a == '.')? (1):(0);
		}
		
		return total;
	}
	
	public static int[] replace(int[] values, int value1, int value2)
	{
		
		for(int i = 0; i < values.length; i++)
		{
			values[i] = (values[i] == value1)? (value2):(values[i]);
		}
		
		return values;
	}
	
	public static int[] evenFront(int[] values)
	{
		ArrayList<Integer> evens = new ArrayList<Integer>();		
		ArrayList<Integer> odds = new ArrayList<Integer>();

		for(int i = 0; i < values.length; i++)
		{
			if(values[i] % 2 == 0)
			{ 
				evens.add(values[i]);
			}
			else 
			{
				odds.add(values[i]);
			}
		}
		
		evens.addAll(odds);
		
		int[] evensFirst = new int[values.length];
		for(int i = 0; i < evens.size(); i++)
		{
			evensFirst[i] = evens.get(i).intValue();
		}
		
		return evensFirst;
	}
	
	public static boolean surroundedCharacter(char[] data)
	{
		for(int i = 1; i < data.length - 1;  i++)
		{
			if(data[i] != data[i-1] && data[i-1] == data[i+1])
			{
				return true;
			}
		}
		
		return false;
	}
}
