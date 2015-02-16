/*
Created by Alexander Szostek
Created on May 14th, 2012
Programming and Algorithms 
Period F
 
||||||||||Variable Dictionary||||||||||
String studentName - stores the inputted student name
int socialSecurityNumber - stores the inputted social security number 
int studentID - stores the generated student ID
*/
import javax.swing.JOptionPane;
public class PersonalInfo
{
	private String studentName;
	private int socialSecurityNumber;
	private int studentID;
	
	public PersonalInfo(int studentID, String inputName)
	{
		studentName = inputName;
		socialSecurityNumber = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the student's social security number?"));
		studentID = studentID;
	}
	
	public String getStudentName()
	{
		return studentName;
	}
	
	public int getSocialSecurityNumber()
	{
		return socialSecurityNumber;
	}
	
	public int getStudentID()
	{
		return studentID;
	}
}

