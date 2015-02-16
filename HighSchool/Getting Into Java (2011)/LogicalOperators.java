package SummerWork;

import javax.swing.*;

public class LogicalOperators {
	public static void main (String[] args){
		String iAge = JOptionPane.showInputDialog ("How old are you?");
		int age = Integer.parseInt(iAge);
		
		if (age < 18 || age > 60){
			System.out.println("You are either too old or too young.(Ages 18-60 can qualify.)");
					}			
		else{
			System.out.println("You fit in the perfect age margin. Welcome!");
				
	}	
}		
}


// Created by Alexander Szostek on August 10th, 2011\\