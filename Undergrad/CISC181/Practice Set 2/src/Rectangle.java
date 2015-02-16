/*
	Created by Alexander Szostek
	Created on February 24th, 2014
	CISC 181, Lab Section 041
	Practice Set #2
*/
public class Rectangle 
{
	private double height;
	private double width;
	
	public Rectangle()
	{
		this.height = 1.0;
		this.width = 1.0;
	}
	
	public Rectangle(double height, double width)
	{
		this.height = height;
		this.width = width;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getArea()
	{
		return (width * height);
	}
	
	public double getPerimeter()
	{
		return (2 * width) + (2 * height);
	}
	
	public static Rectangle makeGoldenRectangle(double height)
	{
		return new Rectangle(height, (height * (.5 + (Math.sqrt(5) / 2))));
	}
}
