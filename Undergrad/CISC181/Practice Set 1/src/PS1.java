/*
	Created by Alexander Szostek
	Created on February 17th, 2014
	CISC 181, Lab 41
	Practice Set #1
*/
public class PS1 
{
	public static void main(String[] args)
	{}

	/*
	 windChillTemperature - (double), (double)
	 Computes the wind chill temperature with respect to the given ambient air temperature and the given wind speed. 
	 */
	public static double windChillTemperature(double airTemperature, double windSpeed)
	{
		double windSpeedPower = Math.pow(windSpeed, 0.16);
		return (35.74 + (0.6215 * airTemperature) - (35.75 * windSpeedPower) + ( 0.4275 * airTemperature *  windSpeedPower));
	}
	
	/*
	 teaParty - (int), (int) 
	 Determines whether the amount of given tea and candy present results in a bad(0), good(1), or great(2) tea party.
	 */
	public static int teaParty(int tea, int candy)
	{
		if(tea < 5 || candy < 5)
		{
			return 0;
		}
		else if((tea >= (candy * 2) || candy >= (tea * 2)))
		{
			
			return 2;
		}
		else
		{
			return 1;
		}
	}
	
	/*
	 shareDigit - (int), (int)
	 Determines whether the two integers given (ranged 10 - 99) have a "2" in either the tens or single digits place.  
	 */
	public static boolean shareDigit(int a, int b)
	{
		int firstA = (a / 10);
		int firstB = (b / 10); 
		int secondA = (a % 10);
		int secondB = (b % 10);
		return (firstA == firstB) || (firstA == secondB) || (secondA == firstB) || (secondA == secondB);
	}
	
	/*
	 closestFactorToSqrt - (int)
	 Determines the smallest factor to the square root of a given number
	*/
	public static int closestFactorToSqrt(int n)
	{
		int counter = (int)Math.sqrt(n);
		
		while(counter > 0 && !(n % counter == 0))
		{
			counter--;
		}
		return counter;
	}
	
	/*
		oddParity - (int)
		Determines whether or not the given number has an odd parity (the sum of the digits are odd) 
	 */
	public static boolean oddParity(long n)
	{
		int digitSum = 0;
		while(n > 0)
		{
			digitSum += (n % 10);
			n /= 10;
		}
		
		return !(digitSum % 2  == 0);
	}
	
	
	/*
	 score - (int), (int), (int)
	 Scoring  rule  for  a  simple  dice  game.
	 3 of a kind = score  20
	 2 of a kind = score  10
	 Otherwise, score is the largest die roll
	 */
	public static int score(int d1, int  d2, int  d3)  
	{
	     if  (d1  ==  d2  &&  d2  ==  d3)  
	     {
	    	 return  20;
	     }
	     else  if  (d1  ==  d2  ||  d2  ==  d3  ||  d1  ==  d3) 
	     {
	    	  return  10;
	     }
	     else  
	     {
	    	 return  Math.max(Math.max(d1,  d2),  d3);
	     }
	}
	
	/*
	 scoreTurn - (int)
	 Produces a sum from 3 random rolls of an n sided die.
	*/
	public static int scoreTurn(int n)
	{
		int firstRoll = (int)(Math.random() * n) + 1;
		int secondRoll = (int)(Math.random() * n) + 1;
		int thirdRoll = (int)(Math.random() * n) + 1;
		return score(firstRoll, secondRoll, thirdRoll);
	}
	
	/*
	simulate - (int)
	Determines the average total score over 1,000,000 turns of rolling an n sided die. (3 rolls per turn)
	*/
	public static double simulate(int n)
	{
		int counter = 0;
		double totalScore = 0;
		int numberOfTurns = 1000000;
		while(counter < numberOfTurns)
		{
			totalScore += scoreTurn(n);
			counter++;
		}
		return totalScore / numberOfTurns;
	}
	
}
