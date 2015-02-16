/*
Created by Alexander Szostek
Created at 11:53 PM, March 26th, 2012
Programming and Algorithms 
Period F

//**********Program Description**********\\
The intention of this program is to allow one to input any amount of numbers with any range, randomly generate the numbers
relative to the parameters entered, and allow the user to select which measure of variablity the user desires. 

//**********Variable Dictionary**********\\
int counter - keeps track of amount of loops completed
int dynamicChecker - used as the dynamic pointer in the insertion sort
int findLargest - stores the most occurring number, used if only one mode
int findLargestLocation - stores the location of the most occurring number in the getModeArray array
int inputDataRange - stores the inputted range of the data inside the generated array
int inputOption - stores the option chosen by the user 
array generatedArray[] - temporarily stores the freshly generated arrray, later stored back into randomArray[]
String printLargest - stores all of the modes for printing, used only if more than one mode
array randomArray[] - stores the generated numbers, used in most methods
int staticChecker - used as the static pointer in the insertion sort
array timesOccurred[] - stores the amount of times a number occurred
int timesOccurredCounter - keeps track of which element the times occurred are stored
*/
import javax.swing.JOptionPane;

public class DataWithMethods
{
	public static void main (String[] args)
	{
	getInput();
	}
	
	public static void getInput()	
	{
	int randomArray[] = new int[Integer.parseInt(JOptionPane.showInputDialog(null,"How many numbers do you want to use?"))];
	int inputDataRange = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the largest number you want to use? (1~?)"));
	Object[] inputOptionChoices = {"Mean", "Median" ,"Mode" , "Exit"};
	int inputOption = JOptionPane.showOptionDialog(null,"What do you want to do with these numbers?", "Input", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, inputOptionChoices,inputOptionChoices[0]);
		switch(inputOption)	
		{ 
			case 0:
				getNumbers(inputDataRange,randomArray);
				getMean(randomArray);
			break;
			case 1:
				getNumbers(inputDataRange,randomArray);
				getMedian(randomArray);
			break;
			case 2:
				getNumbers(inputDataRange,randomArray);
				getMode(randomArray);
			break;
			case 3:
				JOptionPane.showMessageDialog(null,"Thank you for using the Skynet™ Data Measures of Variablity. \nGoodbye.");
			break;
		}
	}

//a method where the actual array and the range of the numbers in the array is passed, and returns the generated array	
	public static int[] getNumbers(int range, int[] generatedArray)
	{ 
		for(int counter = 0; counter<generatedArray.length; counter++)
		{
			generatedArray[counter] = ((int)(Math.random()*range)+1);
		}
	return generatedArray;
	}
	
//a method where the actual array and the range of the numbers in the array is passed, finds the mean of the array, prints it out, then runs the getInput method
	public static void getMean(int getMeanArray[])
	{
	int tempMean = 0;
	for(int counter = 1; counter < getMeanArray.length; counter++)
		{
		tempMean = tempMean + getMeanArray[counter];
		}
		JOptionPane.showMessageDialog(null,"The mean is "+ ((int)(tempMean/getMeanArray.length))+".");
		getInput();
	}

//a method where the actual array is passed, finds the median of the array, prints it out, then runs the getInput method
	public static void getMedian(int getMedianArray[])
	{
//uses the insertion sort to organize the array
	for (int staticChecker = 1; staticChecker < getMedianArray.length; staticChecker++)
		{
			int temp = getMedianArray[staticChecker];
			for(int dynamicChecker = staticChecker; dynamicChecker >= 0; dynamicChecker--)
			{
			if(dynamicChecker == 0 || getMedianArray[dynamicChecker - 1]<=temp)
				{
					getMedianArray[dynamicChecker] = temp;
					break;
				}
				else
				{
						getMedianArray[dynamicChecker] = getMedianArray[dynamicChecker - 1]; 
				}
			}
		}		
		
//checks if the array length is even or odd, prints the median accordingly
	if (getMedianArray.length%2 == 0)
		{
		JOptionPane.showMessageDialog(null,"The median is " + getMedianArray[(int)(getMedianArray.length/2)] +".");
		getInput();
		}	
	else
		{
		JOptionPane.showMessageDialog(null,"The median is " + getMedianArray[getMedianArray.length/2] +"."); 
		getInput();
		}
	}

//a method where the actual array is passed, finds the mode(s) of the array, prints it/them out, then runs the getInput method	
	public static void getMode(int getModeArray[])
	{
	int timesOccurred[] = new int[getModeArray.length]; 
	int timesOccurredCounter = 1;
	
//uses the insertion sort algorithm to first organize the data	
	for (int staticChecker = 1; staticChecker < getModeArray.length; staticChecker++)
		{
			int temp = getModeArray[staticChecker];
			for(int dynamicChecker = staticChecker; dynamicChecker >= 0; dynamicChecker--)
			{
			if(dynamicChecker == 0 || getModeArray[dynamicChecker - 1]<=temp)
				{
					getModeArray[dynamicChecker] = temp;
					break;
				}
				else
				{
						getModeArray[dynamicChecker] = getModeArray[dynamicChecker - 1]; 
				}
			}
		}
		
//then uses simple sort to find the most occurring number within the data set
	for (int staticCounter = 1; staticCounter <= timesOccurred.length; staticCounter++ )
	{
		timesOccurred[staticCounter-1] = timesOccurredCounter;
		timesOccurredCounter = 1;
		for(int dynamicCounter = staticCounter + 1; dynamicCounter <= getModeArray.length-1; dynamicCounter++)
		{
			if(getModeArray[staticCounter] == getModeArray[dynamicCounter])
			{
			timesOccurredCounter++;
			}
		}
	}
	
//use a simple for loop to find the mode(s), store them into findLargest if there's only one mode, store into printLargest if there's more than one
	int findLargestLocation = 1;
	int findLargest = 0;
	String printLargest = "";
		for(int counter = 0; counter < timesOccurred.length; counter++)
		{
			if(findLargest == timesOccurred[counter])
			{
			printLargest = printLargest + ", " + getModeArray[counter];
			}
			else if (findLargest < timesOccurred[counter])
			{ 
			printLargest = "";
			findLargest = timesOccurred[counter];
			findLargestLocation = counter;
			}
	}

	if(findLargest == 1)
		{
		JOptionPane.showMessageDialog(null,"There is no mode.");
		}
	else
		{
		JOptionPane.showMessageDialog(null,"The mode(s) is/are " + (getModeArray[findLargestLocation]) + "" + printLargest + ". ");
		}
		getInput();
	}
}
