package SummerWork;
import javax.swing.*;
public class ElseIfStatements {
	public static void main(String [] args){
		String InputAge = JOptionPane.showInputDialog("How old are you?");
		int Age = Integer.parseInt(InputAge);	
		if (Age <= 13){
			System.out.println("You are a child.");
		}else if(Age < 18 && Age > 13){
			System.out.println("You are a teenager.");
		}else{
			System.out.println("You are an adult.");
		}
	}
}
