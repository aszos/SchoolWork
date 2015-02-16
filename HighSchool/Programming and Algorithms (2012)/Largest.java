import javax.swing.JOptionPane;
/*
Created by Alexander Szostek
Created on February 9th, 2012
Programming and Algorithims Class (D Period)
This program's intention is to generate 10 numbers within the range of 1-100, and print out the largest generate number.

*/

public class Largest {
//create class Largest
	public static void main(String [] args){
	//create main method
		int counter = 0;
		int largest = 0;
		while (counter != 10){
		//do this until number is 10
		int number = ((int)(Math.random()*100)+1);
		//generate a number between 1-100
		if (number > largest){
		//if the current number is larger than the current largest number
		largest = number;
		//replace it
		}//ends if
		counter++;
		}//ends while
	JOptionPane.showMessageDialog(null,"Largest Value is " + largest);
	//print out the largest number
	} //ends main
} //ends class

