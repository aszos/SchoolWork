/*
Created by Alexander Szostek
Created on May 14th, 2012
Programming and Algorithms 
Period F

||||||||||Variable Dictionary||||||||||
String studentName - stores the inputted student name
double studentHeight - stores the inputted student height
int studentWeight - stores the inputted student weight
int studentID - stores the generated student ID

*/

import javax.swing.JOptionPane;
public class HealthInfo
{
	private String studentName;
	private double studentHeight;
	private int studentWeight;	
	private int studentID;

	public HealthInfo(int studentID)
	{
		studentName = JOptionPane.showInputDialog(null,"What is the student's name?");
		studentHeight = Double.parseDouble(JOptionPane.showInputDialog(null,"What is the student's height?"));
		studentWeight = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the student's weight?"));
		studentID = studentID;	
	}
	
	public String getStudentName()
	{
		return studentName;
	}
	
	public int getStudentWeight()
	{
		return studentWeight;
	}
	
	public double getStudentHeight()
	{
		return studentHeight;	
	}
	
	public int getStudentID()
	{
		return studentID;
	}
}