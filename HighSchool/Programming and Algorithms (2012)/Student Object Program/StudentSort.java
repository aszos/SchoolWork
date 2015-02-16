/*
Created by Alexander Szostek
Created on May 14th, 2012
Programming and Algorithms 
Period F

||||||||||Variable Dictionary||||||||||
counter - used to keep track of loop repititions
dynamicPointer - a constantly moving pointer used in the simple sort
finalPrint - prints out the sorted names
getNameArray - stores the names of all the students, is later sorted
staticPointer - a generally static pointer used in the simple sort
*/

import javax.swing.JOptionPane;
public class StudentSort
{
	public void sortEverything(Student[] sortLibrary)
	{
		String getNameArray[] = new String[sortLibrary.length];
		for(int counter = 1; counter < sortLibrary.length; counter++)
		{
			getNameArray[counter] = sortLibrary[counter].getStudentName();
		}
		//create an array to store the names of each student, then store them via for loop
		
		for(int staticPointer = 1; staticPointer < getNameArray.length-1; staticPointer++)
		{
			for(int dynamicPointer = staticPointer+1; dynamicPointer < sortLibrary.length; dynamicPointer++)
			{
				if(getNameArray[staticPointer].compareTo(getNameArray[dynamicPointer]) > 0)
				{
					getNameArray[0] = getNameArray[staticPointer];
					getNameArray[staticPointer] = getNameArray[dynamicPointer];
					getNameArray[dynamicPointer] = getNameArray[0];
				}
			}
		}
		//sort the name array in alphabetical order
		
		String finalPrint = "";
		for(int counter = 1; counter < getNameArray.length; counter++)
		{
			finalPrint = finalPrint + getNameArray[counter] + "\n";
		}
		JOptionPane.showMessageDialog(null,"Sorted Names \n" + finalPrint);
		//print out the sorted names
	}	
}


