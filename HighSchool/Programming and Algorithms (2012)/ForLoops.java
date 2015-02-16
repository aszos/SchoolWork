/*
Created by Alexander Szostek
Created on February 13th, 2012
Programming and Algorithims Class (D Period)
This program's intention is to show the student how to properly use the for loop for whatever means necessary.

*/
import javax.swing.JOptionPane;
public class SimpleForLoops {
	public static void main (String[] args){
		String out = "";
		//make a string out for printing and storing later
		for (int i = 1; i<5; i++;){
		//i is 1, when i is less than 5, add 1 to i and add do the following
		System.out.println(i);
		//print i
		out = out + i + "/n";
		//concatenation  
		//Note: Whenever you put an int into a string, it becomes a part of said string.
		}//ends for loop
		JOptionPane.showMessageDialog(null, out);
		//print out whatever out is
		
	}//ends main method
	
	
	public NestedLoops(void){
		for (int i = 2; i <=5; i++){
		//i is 2, when i is less than or equal to 5, add one to i and do the following
			for(j = i+1; j<=4; j++){
			//j is whatever i is plus 1, when j is less than or equal to 4, add one to j and do the following
				System.out.println(i+ " " + j)
			
			}//ends second for loop

		}//ends first for loop

	}//ends NestedLoops method
	
	public NSquaredSort(void){
	//this method of sorting (n^2 sort) is okay for less than 100 elements in an array, really inefficient, may be ineffective for more than 100 elements
		for (int stat = 1; stat <= 5; stat++){
		//stat is 1, when stat is less than or equal to 5, add one to stat and do the following
			for(int dyn = stat+1; dyn <=6; dyn++){
			//dyn is what stat was plus 1, when dyn is less than or equal to 6, add one and do the following
				if(num[stat] > num[dyn]){
				//while the current number the element stat is greater than the current element dynamic, do the following
				num[0] = num[stat];
				//whatever stat is, put it into element 0
				num[stat] = num[dyn];
				//overwrite stat with what dyn is
				num[dyn] = num[0];
				//put dyn in element 0
				
				}//ends if condition
			
			}//ends the second for loop		
		
		}//ends the first for loop

	}//ends NSquaredSort method

} //ends class




