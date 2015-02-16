/*
Created by Alexander Szostek
Created on September 5th, 2012
Data Structures 

Purpose of the Program
The purpose of this program is to create a link list the length of 5 objects, then be able to append more objects on the end of the list.

Variable Dictionary:
FindTheChicken chickenList - stores the chicken node list for the print method
int counter - keeps track of loop repetitions
FindTheChicken endList - stores the location of the head of the pointer, returned at the end of the function
String finalPrintLine1 - stores the first line of the final print box, contains group names
String finalPrintLine2 - stores the second line of the final print box, contains the contents of the groups
int finalReturn - stores the location of the number of chicken
FindTheChicken frontList - used to generate the linked list
int getChickenNumber - stores the location of the desired chicken number
FindTheChicken objectChicken - stores the head for the append routine
int numberOfChicken - stores the chicken number for the new node created in the append routine

*/

import javax.swing.JOptionPane;

public class FindChicken
{
	public static void main(String args[])
	{
		int finalReturn = findTheChicken(createChickens(),findWhichChicken());
		JOptionPane.showMessageDialog(null,"The chicken was in group #" + finalReturn + ".");
		printChickens(createChickens());
	
	}
	 
	public static FindTheChicken createChickens()
	//creates all of the objects and returns the location of the top of the list 
	{
		FindTheChicken frontList = new FindTheChicken(1);
		//create the head of the list, call it front list
		FindTheChicken endList = frontList;
		//contains the pointer for the head of the list, returned at the end of the function
		for(int counter = 2; counter <= 5; counter++)
		{
			frontList.objectChickens = new FindTheChicken(counter);
			//make the node's pointer equal to the next node created
			frontList = frontList.objectChickens;
			//make the current node equal to the next node on the list
		}
		return endList;
	}
	
	public static int findTheChicken(FindTheChicken frontList, int chickenNumber)
	{
	//searches for the number of chickens given within the list
		int counter = 1;
		while(frontList != null)
		//while the current object is not null, or not at the end of the list
		{
			if(frontList.returnNumberofChickens() == chickenNumber)
			//if the current object's chicken number is equal to the desired number
			{
				return counter;
				//return the location of the object within the list
			}
			else
			{
				counter++;
				//decrease the counter by one, since we're moving down the stack
				frontList = frontList.returnChickenObject();
				//the top of the stack is now the next item on the list
			}
		}
		return -1;
		//if the number was not found, return -1 for debugging reasons
	}

	public static int findWhichChicken()
	{
	//prompts the user for which chicken number the user is searching for
		int getChickenNumber = Integer.parseInt(JOptionPane.showInputDialog(null,"Which chicken are you looking for?"));
		return getChickenNumber;
	}
	
	public static void appendChicken(FindTheChicken objectChicken, int numberOfChicken)
	{
	//a method where the user can append more objects onto the list
		do
		{
			if(objectChicken == null)
			//if the current object is null
			{
				objectChicken = new FindTheChicken(numberOfChicken);
				//place the new object instead of null
			}	
			else
			{
				objectChicken = objectChicken.returnChickenObject();
				//otherwise, continue moving along the list
			}
		} while(objectChicken != null);
		//continue doing while the object is not null
	}
	
	public static void printChickens(FindTheChicken chickenList)
	{
	//prints out all the links of the list along with the contents
		String finalPrintLine1 = "";
		String finalPrintLine2 = "";
		//empty strings to allow truncation within the while
			while(chickenList != null)
			{
			//while we're looking at a node
			finalPrintLine1 = finalPrintLine1 + " Group " + chickenList.returnNumberofChickens();
			finalPrintLine2 = finalPrintLine2 + "            " + chickenList.returnNumberofChickens();
			//truncate the previous finalPrintLine1 and finalPrintLine2 with new finalPrintLine, then add new content
			chickenList = chickenList.objectChickens;
			//move the pointer along the list
			}
		JOptionPane.showMessageDialog(null, finalPrintLine1 + "\n" + finalPrintLine2);
		//print everything we just did
	}
}

