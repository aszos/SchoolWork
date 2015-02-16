package SummerWork;
import java.util.Scanner;

public class SimpleCalculator4 {
	public static void main(String args[]){
		Scanner Alex = new Scanner(System.in);
		int x , y , z, answer;
		
		System.out.println("Enter in the first number.");
		x = Alex.nextInt();
		System.out.println("Enter in the second number.");
		y = Alex.nextInt();
		System.out.println("Enter in the operator. + = 1, - = 2, * = 3, / = 4 ");
		z = Alex.nextInt();
		
		switch (z){	
		case 1:
			answer = x+y;
			System.out.println("The answer is " +answer);
			break;
		case 2:
			answer = x-y;
			System.out.println("The answer is " +answer);
			break;
		case 3: 
			answer = x*y;
			System.out.println("The answer is " +answer);
			break;
		case 4:
			answer = x/y;
			System.out.println("The answer is " +answer);
			break;
		default:
			System.out.println("Something went wrong.");
			break;
		
	}
}
}


// Created by Alexander Szostek on July 27, 2011 // 