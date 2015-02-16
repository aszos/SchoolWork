/*
Created by Alexander Szostek
Created on May 14th, 2012
Programming and Algorithms 
Period F
 
||||||||||Variable Dictionary||||||||||
int middleBound - stores the middle bound in the binary search 
int lowerBound - stores the lower bound in the binary search
StudentEducationInfo[] searchID - temporary stores the array of StudentEducationInfo Objects
int searchValue - represents the inputted desired student ID
int upperBound - stores the upper bound in the binary searchmcms



*/

public class StudentBinarySearch 
{
	public int findStudent(Student[] searchID, int searchValue)
	{
		int lowerBound = 0;
		int upperBound = searchID.length-1;		
		while(lowerBound <= upperBound)
		{
			int middleBound = (lowerBound + upperBound)/2;
		    if(searchValue == 0)
			{
				return -1;
			}
			else if(searchValue < searchID[middleBound].getStudentID())
			{
				upperBound = middleBound - 1;
			}
			else if(searchValue > searchID[middleBound].getStudentID())
			{
				lowerBound = middleBound + 1;
			}
			else
			{
				return middleBound;
			}
		}
	return -1;
	}
	//standard binary search within the array searchID
}
