package SummerWork;
import javax.swing.*;

public class MethodsWithParameters {
	public static void main(String [] args){
	String InputName = JOptionPane.showInputDialog("What is your name?");
	MethodsWithParametersExample MethodsWithParametersExampleObject = new MethodsWithParametersExample();
	MethodsWithParametersExampleObject.simpleMessage(InputName);
	}
}
//Created by Alexander Szostek on August 10th, 2011\\