package SummerWork;

import javax.swing.*;

public class SwitchStatement {
	public static void main(String [] args){
	String iAge = JOptionPane.showInputDialog ("How old are you?");
	int InputAge = Integer.parseInt(iAge);

		switch (InputAge){
		case 1: 
			System.out.println("You are able to crawl.");
			break;
		case 2:
			System.out.println("You can talk.");
			break;
		case 3:
			System.out.println("You can get into trouble.");
			break;
		default:
			System.out.println("Something went wrong.");
			break;
			
		
		}
	}
}
