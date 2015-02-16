/*
Created by Alexander Szostek
Created on April 16, 2012
Programming and Algorithms 
Period F

//**********Program Description**********\\
The intention of this program is to generate an array with 650 elements filled with numbers 1~750, then allow the user to either
generate a new array or to search a number within the currently generated array with the binary search algorithm and it will return
the location of the desired number. The program will continuously loop until broken by the user.

//**********Variable Dictionary**********\\
checkifUsed - a boolean that checks whether or not the menu has been used more than once
counter - used to keep track of loop interations
dynamicChecker - used as the dynamic pointer in the insertion sort
generatedArray - stores he generated array within the generateAndSortArray method
inputOption - used to determine which button was pressed
inputOptionChoices - stores the names of the buttons after the program has been used at least once
inputOptionChoicesFirst - stores the names of the buttons before it has run through it once
lowerBound - stores the lower bound of the binary search
middleBound - stores the middle of the lower bound and the upper bound
printArray - used to print the required elements and the searched elements
randomArray[] - stores the generated list of numbers
searchArray - modifies the randomArray within the binarySearch method
searchElement - stores the value that needs to be searched in the startMenu method
searchValue - stores the value that needs to be searched in the binarySearch method
sortingChecker - checks whether or not the array is sorted and does not have duplicates
staticChecker - used as the static pointer in the insertion sort
temp - used in the insertion sort for comparison
upperBound - stores the upper bound for the binary search
*/
import javax.swing.JOptionPane;

public class BinarySearch
{
	public static void main(String [] args)
	{
		int randomArray[] = new int[651];
		boolean checkIfUsed = true;
		generateAndSortArray(randomArray);
		startMenu(randomArray, checkIfUsed);
	}
	
	public static void startMenu(int[] printArray, boolean ifUsed)
	{
	Object[] inputOptionChoicesFirst = {"Search the Array",};
	Object[] inputOptionChoices = {"Search the Previous Array", "Generate a New Array"};
	int inputOption = 0;
		if(ifUsed)
		{
			JOptionPane.showMessageDialog(null,"Welcome to the Binary Search Program!\nNew Array Generated!");
			ifUsed = false;
		}
		else
		{
			inputOption = JOptionPane.showOptionDialog(null,"What do you want to do?", "Required User Input", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, inputOptionChoices,inputOptionChoices[0]);
		}

		if(inputOption == 0)
		{
		    int searchElement = Integer.parseInt(JOptionPane.showInputDialog(null,"The 1st element was " + printArray[1] + ".\nThe 249th element was " + printArray[249] + ".\nThe 500th element was " + printArray[500] + ".\nThe 559th element was " + printArray[559] + ".\nThe 650th element was " +printArray[650] +  ".\nWhat would you like to search for?" ));	
		    if(binarySearch(printArray,searchElement) == 0 )
			{
				JOptionPane.showMessageDialog(null,"The 1st element was " + printArray[1] + ".\nThe 249th element was " + printArray[249] + ".\nThe 500th element was " + printArray[500] + ".\nThe 559th element was " + printArray[559] + ".\nThe 650th element was " +printArray[650] +  ".\nThe location of "+ searchElement +" does not exist.");	
				startMenu(printArray,ifUsed);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"The 1st element was " + printArray[1] + ".\nThe 249th element was " + printArray[249] + ".\nThe 500th element was " + printArray[500] + ".\nThe 559th element was " + printArray[559] + ".\nThe 650th element was " +printArray[650] +  ".\nThe location of " + searchElement +" is in element " +  binarySearch(printArray,searchElement) + ".");	
				startMenu(printArray, ifUsed);
			}
		}
		else
		{
			generateAndSortArray(printArray);
			ifUsed = true;
			startMenu(printArray,ifUsed);
		}
	}
	
	public static int[] generateAndSortArray(int[] generatedArray)
	{
		for(int counter = 1; counter < generatedArray.length; counter++)
		{
			generatedArray[counter] = (int)((Math.random()*750)+1);
//generates any number between 1~750, stores it into the generatedArray
		}
		
		int staticChecker = 0;
		int dynamicChecker = 0;
	
		boolean sortingChecker = true;
		while(sortingChecker)
		{
			//organize the array via insertion sort
			for (staticChecker = 1; staticChecker < generatedArray.length; staticChecker++)
			{
				int temp = generatedArray[staticChecker];
				for(dynamicChecker = staticChecker; dynamicChecker >= 0; dynamicChecker--)
				{
					if(dynamicChecker == 0 || generatedArray[dynamicChecker - 1]<=temp)
					{
						generatedArray[dynamicChecker] = temp;
						break;
					}
					else
					{
							generatedArray[dynamicChecker] = generatedArray[dynamicChecker - 1];
					}
				}
			}
			//prevents replacement if all the elements are unique
			if(!sortingChecker)
			{
			break;
			}
			sortingChecker = false;
			//duplicate replacing system via simple sort
			for (staticChecker = 1; staticChecker < generatedArray.length; staticChecker++)
			{
				for(dynamicChecker = staticChecker+1; dynamicChecker <= generatedArray.length-1; dynamicChecker++)
				{
					if(generatedArray[dynamicChecker] == generatedArray[staticChecker])
					{
						generatedArray[staticChecker] = ((int)(Math.random()*650)+1);
						sortingChecker = true;
					}
				}
			}
		}
		return generatedArray;
	}
	
	public static int binarySearch(int[] searchArray, int searchValue)
	{
		int middleBound = 0;
		int lowerBound = 0;
		int upperBound = searchArray.length-1;		
		while(lowerBound <= upperBound)
		//standard binary sort
		{
			middleBound = (lowerBound + upperBound)/2;
			if(searchArray[middleBound] == searchValue)
			{
				return middleBound;
			}
			else if(searchValue > searchArray[middleBound])
			{
				lowerBound = middleBound + 1;
			}
			else
			{
				upperBound = middleBound - 1;
			}
		}
	JOptionPane.showMessageDialog(null,"The number was not found.");
	return 0;
	}
}