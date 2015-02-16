package SummerWork;
import javax.swing.*;
public class ManyMethods {
	public static void main(String [] args){
	ManyMethodsInstance ManyMethodsInstanceObject = new ManyMethodsInstance();
		String Input = JOptionPane.showInputDialog("Enter name of first girlfriend.");
		ManyMethodsInstanceObject.SetName(Input);
		ManyMethodsInstanceObject.saying();
	}
}
//Created by Alexander Szostek on August 11th, 2011\\