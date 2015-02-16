/*
Created by Alexander Szostek
Created on March 12, 2012
Programming and Algorithms 
Period F

//**********Program Description**********\\
The objective of this program is to create 2 arrays filled with 20 random integers ranged 1-100 and merges these two arrays into a third array. 
It later ends up being sorted in ascending order via Insertion Sort and is searched for duplicate elements, replacing those duplicate elements with newly generated ones. It finally outputs the original arrays 
of 20 integers before acting upon them in any way; then prints out the third array after its sort and removing of duplications.
It is printed out in the same Swing Window with the data arranged horizontally and appropriately labeled.

//**********Variable Dictionary**********\\
int brownCounter - stores the number of sorting loops occured
int combiningCounter - stores the amount of times the concatenation of groupA and groupB into groupC occurred
int dynamicChecker - acts as the dynamic pointer and is constantly compared with staticChecker for whether it is a duplicated element or an if it is lower than staticChecker
String finalOutGroupA - stores the untouched groupA array, used for printing
String finalOutGroupB - stores the untouched groupB array, used for printing
String finalOutGroupC - stores the sorted and nonduplicate groupC array, used for printing
int generatingCounter - stores the amount of times numbers are randomly generated for arrays groupA[] and groupB[]
int groupA[] - stores the first group of 20 numbers that were randomly generated
int groupB[] - stores the second group of 20 numbers that were randomly generated
int groupC[] - stores the concatenated group of groupA[] and groupB[]
int printCounter - a counter for storing groupA and groupB into strings finalOutGroupA and finalOutGroupB respectively
int printCounterGroupC - a counter for storing groupC into the string finalOutGroupC
boolean sortingChecker - checks whether or not the data is sorted and all elements are unique
int staticChecker - acts as the static pointer and is constantly compared with dynamicChecker for whether it is a duplicated element or an if it is higher than staticChecker
int temp - the middle man for comparing between staticChecker and a dynamicChecker in the insertion sort
*/

import javax.swing.JOptionPane;

public class TripleArrayFiasco
{
	public static void main (String [] args)
	{	
	int groupA[] = new int[21];
	int groupB[] = new int[21];
	int groupC[] = new int[41];
	int brownCounter =0;
	int generatingCounter;
	int combiningCounter;
	int printCounter;
	int staticChecker=0;
	int dynamicChecker=0;
	int temp; 
	int printCounterGroupC;
	String finalOutGroupA = "";
	String finalOutGroupB = "";
	String finalOutGroupC = "";
		//generate the numbers and put them into their respective arrays
		for (generatingCounter = 1; generatingCounter <=20; generatingCounter++)
		{
			int generatedNumberA = ((int)(Math.random()*100)+1);
			int generatedNumberB = ((int)(Math.random()*100)+1);
			groupA[generatingCounter] = generatedNumberA;
			groupB[generatingCounter] = generatedNumberB;
		}
	
		//concatenate groupA and groupB into groupC 
		for (combiningCounter = 1; combiningCounter <=20; combiningCounter++)
		{
			groupC[combiningCounter] =  groupA[combiningCounter];
			groupC[combiningCounter+20] = groupB[combiningCounter];	
		}
	
		//begin the sorting and duplicate checking
		boolean sortingChecker = true;
		while(sortingChecker)
		{
			brownCounter++;
			//organize the array via insertion sort
			for (staticChecker = 1; staticChecker < groupC.length; staticChecker++)
			{
				temp = groupC[staticChecker];
				for(dynamicChecker = staticChecker; dynamicChecker >= 0; dynamicChecker--)
				{
					if(dynamicChecker == 0 || groupC[dynamicChecker - 1]<=temp)
					{
						groupC[dynamicChecker] = temp;
						break;
					}
					else
					{
							groupC[dynamicChecker] = groupC[dynamicChecker - 1];
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
			for (staticChecker = 1; staticChecker < groupC.length; staticChecker++)
			{
				for(dynamicChecker = staticChecker+1; dynamicChecker <= groupC.length-1; dynamicChecker++)
				{
					if(groupC[dynamicChecker] == groupC[staticChecker])
					{
						groupC[staticChecker] = ((int)(Math.random()*100)+1);
						sortingChecker = true;
					}
				}
			}
		}
			for(printCounter = 1; printCounter <=20; printCounter++)
			{
				finalOutGroupA = finalOutGroupA +""+ groupA[printCounter]+ " | ";
				finalOutGroupB = finalOutGroupB +""+ groupB[printCounter]+ " | ";
			}
	
			for(printCounterGroupC = 1; printCounterGroupC <=40; printCounterGroupC++)
			{
				finalOutGroupC = finalOutGroupC + groupC[printCounterGroupC] + " | ";
			}
	JOptionPane.showMessageDialog(null, "The original arrays were \n| " + finalOutGroupA + "\n| " + finalOutGroupB + "\nThe final sorted array is \n| " + finalOutGroupC + "\nThe number of sorting loops occured is\n" + brownCounter);
	}
}

