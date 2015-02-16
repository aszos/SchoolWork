/*
Created by Alexander Szostek
Completed on November 8th, 2012
Data Structures 

Program Description:
This program is designed to manage four lists, all containing employee information, oriented relative to employee name(string), age(integer), 
and salary(double). First, the user is asked how many employees they are given, they then enter in information about each employee. From there, 
the program creates four lists oriented in chronological order, alphabetical order, numerical (age and salary) order. Once this is completed,
the user can add another employee to the lists, display either one of the lists, or exit the program.

Variable Dictionary:
Employee headPointerByAge - stores the head pointer for the age list
Employee headPointerByName - stores the head pointer for the name list
Employee headPointerBySalary - stores the head pointer for the salary list
Employee headPointerChronological - stores the head pointer for the chronological list
Employee masterPointerByAge - stores the head pointer for the age list within the object
Employee masterPointerByName - stores the head pointer for the name list within the object
Employee masterPointerBySalary - stores the head pointer for the salary list within the object
Employee masterPointerChronological - stores the head pointer for the chronological list within the object
*/

public class MasterEmployeeNode
{
	Employee masterPointerChronological;
	Employee masterPointerByName;
	Employee masterPointerByAge;
	Employee masterPointerBySalary;
	
	public MasterEmployeeNode(Employee headPointerChronological, Employee headPointerByName, Employee headPointerByAge, Employee headPointerBySalary)
	{
		masterPointerChronological = headPointerChronological;
		masterPointerByName = headPointerByName;
		masterPointerByAge = headPointerByAge;
		masterPointerBySalary = headPointerBySalary;
	}
	
	public Employee returnHeadNodeChronological()
	{
		return masterPointerChronological;
	}
	
	public Employee returnHeadNodeByName()
	{
		return masterPointerByName;
	}
	
	public Employee returnHeadNodeByAge()
	{
		return masterPointerByAge;
	}

	public Employee returnHeadNodeBySalary()
	{
		return masterPointerBySalary;
	}
}