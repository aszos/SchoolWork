/*
Created by Alexander Szostek
Created on February 24th, 2012
Programming and Algorithms Class (D Period)

//**********Program Description**********\\
This program's intention is to take 5 people's inputed name, age, and salary and sort the information, using the n^2 algorithm, 
according to their first name in alphabetical order, then prints out the initial inputed information and the sorted information 
onto one Swing window. 

//**********Variable Dictionary**********\\
counter - keeps track of loop counts
dynamicPointer - stores the dynamic pointer position within an array during the comparison to allow active 
firstName[] - stores the inputed first name
initialInformation[] - stores all of the concatenated inputs (first name, last name, salary) by the user
initialOut - stores all the inputed information for convenient printing
lastName[] - stores the inputed last name
salary[] - stores the initial inputed salaries
salaryParse - stores the inputed string and used to parse for integers within the inputed string
sortedOut - prints out the concatenated and sorted information
staticPointer - stores the value for the static pointer for comparing with the dynamic pointer
*/

import javax.swing.JOptionPane;

public class MultipleArraySort
{
	public static void main (String [] args)
	{	
		String firstName[] = new String[6];
		String lastName[] = new String[6];
		String initialInformation[] = new String[6];
		String initialOut = "";
		String sortedOut = "";
		int salary[] = new int[6];
		String salaryParse = "";
		int counter;
		int dynamicPointer;
		int staticPointer;
	
		for(counter = 1; counter < 6; counter++)
		{		
			firstName[counter] = JOptionPane.showInputDialog(null,"What is the person's first name?");
			lastName[counter] = JOptionPane.showInputDialog(null,"What is the person's last name?");
		    salaryParse = JOptionPane.showInputDialog(null,"What is the person's salary?");
			salary[counter] = Integer.parseInt(salaryParse);
			//looks for integer within the inputed string
			initialInformation[counter] = ("\nThe person's full name is "+ lastName[counter] + ", " + firstName[counter] +  ". \nTheir wage is "+ salary[counter]+ ". \n");	
			//stores the inputed information into one line for later storage
			initialOut = "The initial inputted information was: \n" + (initialInformation[1] + initialInformation[2] + initialInformation[3] + initialInformation[4] + initialInformation[5]);	
			//stores all of the inputed lines into one string for later printing
		}
			
		for(staticPointer = 1; staticPointer <=4; staticPointer++)
		{
			for(dynamicPointer = staticPointer + 1; dynamicPointer <=5; dynamicPointer++)
			{	
						if(((firstName[staticPointer]).compareTo(firstName[dynamicPointer]) > 0))
						{
							initialInformation[0] = initialInformation[staticPointer];
							//stores the data from the current value of the static pointer location into element 0
							initialInformation[staticPointer] = initialInformation[dynamicPointer];
							//replaces what the current value is in static pointer location and replaces it with dynamic pointer
							initialInformation[dynamicPointer] = initialInformation[0];
							//replaces what current value is in element 0 into the dynamic pointer location
						}
						else
							{
							initialInformation[staticPointer] = initialInformation[staticPointer];
							initialInformation[dynamicPointer] = initialInformation[dynamicPointer];
							//stay frosty
							}
						//ends compareTo loop
			sortedOut = "\nThe sorted information is: \n" + (initialInformation[1] + initialInformation[2] + initialInformation[3] + initialInformation[4] + initialInformation[5]);
			}
			//ends static VS dynamic if statement
		}
		//ends dynamic pointer for loop
		JOptionPane.showMessageDialog(null,(initialOut + "\n" + sortedOut));
	}
	//ends main Method
}
//ends MultipleArraySort class


