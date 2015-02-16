/*
Created by Alexander Szostek
Created on April 2nd, 2012 at 11:54.23 PM
Programming and Algorithms 
Period F


/**********Program Description**********\
The user inputs a variable that they would like to factorialize (n!), and the program finds the factorial of the inputted number recursively and iteratively, then prints
the answers out onto one swing window.



/**********Variable Dictionary**********\
counter - keeps track of loop iterations
finalIterativeFactorial - returned as the final answer found using iteration
findIterativeFactorial - used for finding the factorial via iteration, stores inputted number
findRecursionFactorial - used for finding the factorial via recursion, stores inputted number
inputFactorial - stores the inputted number for factorializing, allocated to other methods

*/
import javax.swing.JOptionPane;

public class RecursionOrIteration
{
//gets the number that the user wants to factorialize
	public static void main (String [] args)
	{
	int inputFactorial = Integer.parseInt(JOptionPane.showInputDialog(null,"What number do you want to factorialize?"));
	JOptionPane.showMessageDialog(null,"Using recursion, the answer is " + viaRecursion(inputFactorial) + ".\nUsing iteration, the answer is " + viaIteration(inputFactorial) + ".");
	//concatenate everything into one swing window, print it out
	}

	public static long viaIteration(int findIterativeFactorial)
	{
		long finalIterativeFactorial = 1;
		for(int counter = 1; counter <= findIterativeFactorial; counter++)
		//the counter starts at 1, while it's less than or equal to the inputted option, add one to counter every loop
		{
			finalIterativeFactorial = finalIterativeFactorial  * counter;
			//the answer is equal to itself times the current counter
		}
	return finalIterativeFactorial;
	//return the final answer
	}
	
	public static long viaRecursion(long findRecursionFactorial)
	{
		if(findRecursionFactorial <= 1)
		//if the inputted option is 1 or 0
		{
			return 1;
		}
		else
		{
			return findRecursionFactorial * viaRecursion(findRecursionFactorial-1);	
		//if anything else, return itself times the same method with the parameter, the inputted option - 1
		}
	}
}
