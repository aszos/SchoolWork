package IntroToAlgos;
import javax.swing.*;
public class Flip {
public static void main(String[] args){
	
	String UserInput = JOptionPane.showInputDialog("How many times would you like to flip a coin?");
	int UserInputFlips = Integer.parseInt(UserInput);
	int Heads = 0, Tails = 0;
	for (int x=0; x <= UserInputFlips; x++){
		double HeadsOrTails = (Math.random() * 2);	
		if (HeadsOrTails >= 1){
			Heads++;
		}else{ 
			Tails++;
			}
		}
	JOptionPane.showMessageDialog (null,"Heads: " + Heads + "\n\nTails: " + Tails + "\n\nUser Input Flips: " + UserInputFlips);
	}
}
