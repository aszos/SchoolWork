/*
Created by Alexander Szostek
Created on May 14th, 2012
Programming and Algorithms 
Period F

||||||||||Variable Dictionary||||||||||
String studentName - stores the inputted student name
double studentGPA - stores the inputted student GPA
int studentID - stores the generated student ID
*/
import javax.swing.JOptionPane;
public class StudentEducationInfo
{
	private String studentName;
	private double studentGPA;
	private int studentID;
	
	public StudentEducationInfo(int studentID, String inputName)
	{
		studentName = inputName;
		studentGPA = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the student's GPA?"));
		studentID = studentID;
	}
	
	public String getStudentName()
	{
		return studentName;
	}
	
	public double getStudentGPA()
	{
		return studentGPA;
	}

	public int getStudentID()
	{
		return studentID;
	}
}





