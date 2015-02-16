package IntroToAlgos;
import javax.swing.*;


	//*******************************************************
	//*********************Program Description***************
	//*******************************************************
	//
	/* Simulation of a two-sided coin being flipped by a user-defined number
	of times with each flip of the coin being counted. A final swing window
	will provide the number of simulated heads, and the number of simulated
	tails. The Math.random() method is utilized to produce the required sides
	of the coin by establishing the numbers 1 and 2 randomly. */

	//*******************************************************
	//*********************Variable Dictionary***************
	//*******************************************************
	/* All variables listed alphabetically and defined as to purpose



	*/

	public class CoinFlip

	 {

	 public static void main (String args [])

	 {

	 int headCount, tailCount, flipCount, userFlips;

	 String inputtedUserFlips = JOptionPane.showInputDialog ("Number of Flips of the Coin?");
	 userFlips = Integer.parseInt (inputtedUserFlips); //window String to usable Integer

	 flipCount = 0; headCount = 0; tailCount = 0;

	 int headOrTail;


	 while (flipCount < userFlips)
	 {

	 headOrTail = (int) (Math.random() * 2) + 1; //random integer 1 or 2

	 flipCount = flipCount + 1; //count total coint tosses

	 if (headOrTail == 1)
	 headCount = headCount + 1; //count heads
	 else
	 tailCount = tailCount + 1; //count tails


	 }//end while flipCount

	 JOptionPane.showMessageDialog (null,"Total Flips = " + flipCount + "\n\nHeads = " + headCount +
	 "\n\nTails = " + tailCount);

	 }//end of main method
	 }//end of class Coin

