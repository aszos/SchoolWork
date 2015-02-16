/*
 * Created by Alexander Szostek
 * Created on March 14th, 2014
 * Problem Set #3
 * */

public class ExactNumber 
{
	private long leftDigits;
	private long rightDigits;
	 
	public ExactNumber(long leftDigits, long rightDigits)
	{
		this.leftDigits = leftDigits;
		this.rightDigits = rightDigits;
	}
	
	public long getLeftDigits()
	{
		return leftDigits;		
	}
	
	public long getRightDigits()
	{
		return rightDigits;
	}
	
	public String toString()
	{
		return String.valueOf(doubleValue());
	}
	
	public double doubleValue()
	{
		return (double)((leftDigits) + (rightDigits / 1E16));
	}
	
	public int compareTo(ExactNumber n)
	{
		if((this.getLeftDigits() == n.getLeftDigits()) && (this.getRightDigits() == n.getRightDigits()))
		{
			return 0;
		}
		else if((this.getLeftDigits() > n.getLeftDigits()) || (this.getLeftDigits() == n.getLeftDigits() && this.getRightDigits() > n.getRightDigits()))
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
	public boolean equals(Object n)
	{
		if(n instanceof ExactNumber)
		{
			return (this.compareTo((ExactNumber) n)  == 0);
		}
		else
		{
			return false;
		}	
	}
	
	public ExactNumber add(ExactNumber n)
	{
		long sumLeftDigits = this.getLeftDigits() + n.getLeftDigits();
		long sumRightDigits = this.getRightDigits() + n.getRightDigits();
		
		if(sumRightDigits >= 1E16)
		{
			return new ExactNumber(sumLeftDigits + 1, sumRightDigits - (long)1E16);
			
		}
		else
		{
			return new ExactNumber(sumLeftDigits, sumRightDigits);
		}
	}
}
