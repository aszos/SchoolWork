/*
Created by Alexander Szostek
Created on May 14th, 2012
Programming and Algorithms 
Period F


||||||||||Variable Dictionary||||||||||
StudentBinarySearch BinarySearchReference - an object used to reference the binary search, which is in another class
int counter - keeps track of loops 
int getIDNumber - stores the location of the desired ID number
int initialInputIDNumber - stores the desired inputted student ID
int initialInputOption - stores the option selected by the user
Object[] inputInformationType - stores the button options for what information the user requires
Object[] inputSortorSearchSelection  - stores the button options for whether the user would like to sort or search 
StudentEducationInfo StudentEducationInfoArray[] - stores objects type StudentEducationInfo, used for printing, sorting, storing
int studentNumber - stores the number of students that the user will input
StudentSort StudentSortReference - used to reference a sort within the class StudentSort
double tempGPA - stores the inputted GPA for a student
double tempHeight - stores the inputted height of a student
int tempID - stores the student ID for a student
String tempName - stores the inputted student name
int tempSSNumber - stores the inputted social security number
int tempWeight - stores the inputted student weight

||||||||||Program Summary||||||||||


*/
import javax.swing.JOptionPane;
public class StudentMain
{
	public static void main(String[] args)
	{
		int studentNumber = Integer.parseInt(JOptionPane.showInputDialog(null,"How many students?")); 
		//gets the number of students from the user
		
		StudentSort StudentSortReference = new StudentSort();
		StudentBinarySearch BinarySearchReference = new StudentBinarySearch();
		Student StudentLibrary[] = new Student[studentNumber+1];
		//makes objects for the ability to reference the StudentBinarySearch class, StudentSort class, and creates an object array to store student information

		Object[] inputInformationType = {"Health Information","Personal Information","Schedule Information", "Cancel"};
		Object[] inputSortorSearchSelection = {"Show Sorted Names","Search","Cancel"};
		//creates objects that contain the selections for buttons
		
		for(int counter = 1; counter <= studentNumber; counter++)
		{
			StudentLibrary[counter] = new Student(counter);
		}	
		//declares and instatiates objects, type Student, then stores them into the object array StudentLibrary
		
		int inputSortOrSearch = JOptionPane.showOptionDialog(null,"What would you like to do?", "Input", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, inputSortorSearchSelection, inputSortorSearchSelection[0]);
		//prompts user if they want to search for a specific student, or get a list of names that is sorted in alphabetical order
		if(inputSortOrSearch == 0)
		{
			StudentSortReference.sortEverything(StudentLibrary);
		}
		else if(inputSortOrSearch == 1)
		{
			int initialInputIDNumber;
			int getIDNumber = -1;
			while(getIDNumber == -1)
			{
				initialInputIDNumber = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter in the student's ID number."));
				getIDNumber = BinarySearchReference.findStudent(StudentLibrary,initialInputIDNumber);
			}
			//prompts user for ID number of the student, searches for user, loops until found
		
			int initialInputOption = 0;
			while(initialInputOption !=3)
			{	
				initialInputOption = JOptionPane.showOptionDialog(null, "Which information do you require?","Input",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, inputInformationType,inputInformationType[0]);
				//prompts user on which type of information the user requires (Personal Information, Health Information, and Education Information)
				if(initialInputOption == 0)
				{
					JOptionPane.showMessageDialog(null, "Name: "+ StudentLibrary[getIDNumber].getStudentName() + "\nHeight: " + StudentLibrary[getIDNumber].getHealthInfo().getStudentHeight() + "\nWeight: " + StudentLibrary[getIDNumber].getHealthInfo().getStudentWeight());
				}
				else if(initialInputOption == 1)
				{
					JOptionPane.showMessageDialog(null, "Name: " + StudentLibrary[getIDNumber].getStudentName() + "\nSocial Security Number: " + StudentLibrary[getIDNumber].getPersonalInfo().getSocialSecurityNumber());
				}
				else if(initialInputOption == 2)
				{
				JOptionPane.showMessageDialog(null, "Name: " + StudentLibrary[getIDNumber].getStudentName() + "\nGPA: " + StudentLibrary[getIDNumber].getEducationInfo().getStudentGPA());
				}
			}	
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Goodbye!");
			//if the user breaks the program or quits at any time, bid them good day
		}		
	}
}
