/*
Created by Alexander Szostek
Created on September 5th, 2012
Data Structures 

Purpose - 
The purpose of this program is to create a link list the length of 5 objects, then be able to append more objects on the end of the list.

Variable Dictionary -
int noOfChickens - argument for incoming number of chickens
private int numberofChickens - hold the number of chickens in the group
public FindTheChicken objectChickens - pointer for linked lists creation

*/


public class FindTheChicken
{
	private int numberofChickens;
	//is stored in each object, primary target for searches
	public FindTheChicken objectChickens;
	//establish a pointer to another node, allows the current node to be connected to another node
	public FindTheChicken(int noOfChickens)
		{
		numberofChickens = noOfChickens;
		//takes the number of chickens from the parameter list and stores it within the object
		}	
		
	public int returnNumberofChickens()
	{
		return numberofChickens;
	}
	//returns the number of chickens for checking and searching
	
	public FindTheChicken returnChickenObject()
	{
		return objectChickens;
	}
}	//returns the node that the pointer is pointing to

//insertion routines due next tuesday 





