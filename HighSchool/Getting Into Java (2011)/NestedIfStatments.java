package SummerWork;
import javax.swing.*;
public class NestedIfStatments {
	public static void main(String[] args){
		String Input = JOptionPane.showInputDialog("How old are you?");
		int InputAge = Integer.parseInt(Input);
		
		if(InputAge <18){
			System.out.println("You're young.");
		}else{
			System.out.println("You are an adult.");
			if (InputAge > 50 ){
				System.out.println("You are old.");
			}else{
				System.out.println("You are an average adult. ");
			
		}
	}
	}
}
//Created by Alexander Szostek on August 16th, 2011\\