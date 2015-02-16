/*
	Created by Alexander Szostek
	Created on February 24th, 2014
	CISC 181, Lab Section 041
	Practice Set #2
*/

import junit.framework.TestCase;

public class TestRectangle extends TestCase {

	public void test_getArea() {
		Rectangle testRect = new Rectangle(5, 10);
		assertEquals(50, testRect.getArea(), 0.1);
	}

	public void test_getPerimeter() {
		Rectangle testRect = new Rectangle(5, 10);
		assertEquals(30, testRect.getPerimeter(), 0.1);
	}
	
	public void test_makeGoldenRectangle()
	{
		double testHeight = 1.618;
		Rectangle testRect = Rectangle.makeGoldenRectangle(testHeight); 
		assertEquals(8.472, testRect.getPerimeter(), 0.1);
		assertEquals(2.618, testRect.getWidth(), 0.1);
		
		double secondTestHeight = 1;
		Rectangle secondTestRect = Rectangle.makeGoldenRectangle(secondTestHeight);
		assertEquals(5.236, secondTestRect.getPerimeter(), 0.1);
		assertEquals(1.618, secondTestRect.getWidth(), 0.1);
	}
	
}
