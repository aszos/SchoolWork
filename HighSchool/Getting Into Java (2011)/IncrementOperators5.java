package SummerWork;

import java.util.Scanner;

public class IncrementOperators5 {
public static void main(String [] args){
	Scanner Scanner = new Scanner(System.in);
	double b = 6;
	
	System.out.println("Enter in an increment. Note that the integer is 6.");
	int t = Scanner.nextInt();
	System.out.println("Enter in an operator. + = 1, - = 2, * = 3, / = 4, % = 5");
	int r = Scanner.nextInt();
	
	switch(r){
	case 1:
		b = b + t;
		System.out.println("The answer is " + b);
		break;
	case 2:
		b = b - t;
		System.out.println("The answer is " + b);
		break;
	case 3:
		b = b * t;
		System.out.println("The answer is " + b);
		break;
	case 4:
		b = b / t;
		System.out.println("The answer is " + b);
		break;
	case 5:
		b = b % t;
		System.out.println("The answer is " + b);
		break;
	default:
		System.out.println("Something went wrong.");
	}
	
	
	
}
}
//Created by Alexander Szostek on July 27, 2011 // 