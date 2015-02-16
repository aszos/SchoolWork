/*
Created by Alexander Szostek
Created on December 17th, 2012
Data Structures

*** Program Description ***
This program is designed to take two given doubles of any finite size, add them together using doubly linked lists, then print out the result.

*** Variable Dictionary ***
boolean carryOver - stores whether or not there is a carryover for a digit
int counter - stores for loop repetitions
SingleDigit creationPointer - stores a created node for creation of a DLL (general)
SingleDigit createDigit - stores a created node for creation of a DLL (result)
SingleDigit finalHead - stores the head of a list when being created
SingleDigit finalResult - stores the final result list
String finalPrintNumber - stores the final result when printing
SingleDigit firstNumber - stores the entire first number list
SingleDigit firstNumberDecimal - contains the decimal point for the first number
SingleDigit firstNumberPointer - a temporary pointer for modification of a list
SingleDigit firstNumberTail - stores the tail of the first number
SingleDigit finalResultDecimal - stores the decimal place of the result
SingleDigit finalResultTail - stores the tail of the result
int getDecimalPoint - stores the value for a decimal
String getEnteredNumber - stores the user's entered number
int getResult - stores the result of adding the first number and the second number
SingleDigit placementPointer - a temporary pointer for a node for creation
SingleDigit printNumberPointer - a temporary pointer for a node for printing
SingleDigit resultNumberPointer - a temporary pointer for modifying the result list
SingleDigit secondNumber - stores the entire second number list
SingleDigit secondNumberDecimal - stores the decimal place of the second number
SingleDigit secondNumberPointer - a modifying pointer for the second number 
SingleDigit secondNumberTail - stores the tail of the second number list
*/
import javax.swing.JOptionPane;
public class CalculatorMain
{
    public static void main(String[] args)
    {       
        SingleDigit firstNumber = createNumber();
        SingleDigit secondNumber = createNumber(); 
        //creates the first and second numbers by calling the creation method
        displayNumber(firstNumber);
        displayNumber(secondNumber);
        //displays both numbers that were created
        SingleDigit finalResult = combineNumbers(firstNumber,secondNumber);
        //create the final result list, which contains the combination of the first number and the second number
        displayNumber(finalResult);       
        //display the final result
    }
    
    public static SingleDigit createNumber()
    {
        String getEnteredNumber = JOptionPane.showInputDialog("Enter in a number.");
        //get the user input for a single number
        SingleDigit finalHead;
        //create a head for creation of the DLL
        if(getEnteredNumber.substring(0,1).equals("."))
        //if the first digit entered is a decimal
        {
            finalHead = new SingleDigit(100, null, null);
            //make the head of the list a decimal (which we will reference as 100)
        }
        else
        //otherwise, we can assume that it's an integer
        {
            finalHead = new SingleDigit(Integer.parseInt(getEnteredNumber.substring(0,1)), null, null);
            //make the head of the list the first digit that was entered
        }

        SingleDigit finalTail;
        //create a tail, we'll we returning this later
        SingleDigit placementPointer = finalHead;
        //make a pointer for modifying this list
        SingleDigit creationPointer;
        //make a pointer used for creating new nodes
        
        for(int counter = 1; counter < getEnteredNumber.length(); counter++)
        //while keeping track of a counter, continue looping while this counter is less than the length of the entered number, and add one to the counter after every repetition
        {
            if(getEnteredNumber.substring(counter,counter+1).equals("."))
            //if the current digit is a decimal
            {
                int getDecimalPoint = 100;
                //we're gonna use 100 to represent a decimal
                creationPointer = new SingleDigit(getDecimalPoint, placementPointer, null);
                //create a node containing the decimal, and the previous pointer to our placement pointer, and make the next pointer point to nothing
                placementPointer.nextDigit = creationPointer;
                //the placement next pointer will point to the newly created node
                placementPointer = creationPointer;
                //the placement pointer now becomes the node we created, so we can start in the proper place in the next loop
            }
            else
            //if the digit isn't a decimal
            {
                int getSingleDigit = Integer.parseInt(getEnteredNumber.substring(counter, counter+1));
                //get the single digit by using parseInt and substrings
                creationPointer = new SingleDigit(getSingleDigit, placementPointer, null);
                //create a new node containing the single digit, and the previous pointer is the placement pointer
                placementPointer.nextDigit = creationPointer;
                //the placement pointer's next pointer will point to what we created
                placementPointer = creationPointer;
                //the placement pointer will now be the creation pointer, so we can start in the proper place in the next loop  
            }
        }
        finalTail = placementPointer; 
        //after we're all done, the tail of the created node should be at the placement pointer   
        return finalTail;
        //return the tail of the created number
    }
    
    public static SingleDigit combineNumbers(SingleDigit firstNumberTail, SingleDigit secondNumberTail)
    {
       int getResult;
       int counter;
       boolean carryOver = false;
       SingleDigit firstNumberPointer;
       SingleDigit secondNumberPointer;
       SingleDigit resultNumberPointer;
       SingleDigit firstNumberDecimal = firstNumberTail;
       SingleDigit secondNumberDecimal = secondNumberTail;
       SingleDigit finalResultTail;
       SingleDigit finalResultDecimal = new SingleDigit(100, null, null);
       SingleDigit createDigit = null;
       
       for(counter = 0; firstNumberDecimal != null && firstNumberDecimal.getDigit() != 100; counter++)
       //start a counter at 0, while we aren't at the end of the number and the current digit isn't a decimal, increment after each repetition
       {
            firstNumberDecimal = firstNumberDecimal.previousDigit;
            //traverse down the first number
       }
       
        
       for(counter = 0; secondNumberDecimal != null && secondNumberDecimal.getDigit() != 100; counter++)
       //start a counter at 0, while we aren't at the end of the number and the current digit isn't a decimal, increment after each repetition
       {   
            secondNumberDecimal = secondNumberDecimal.previousDigit;
            //traverse down the second number
       }
       //the idea in these two for loops is to find the decimals in both numbers and stop       


       if(firstNumberDecimal == null && secondNumberDecimal == null)
       //if both numbers don't contain a decimal
       {
            firstNumberDecimal = firstNumberTail;
            //go back to the tail
            firstNumberDecimal.nextDigit = new SingleDigit(100, firstNumberDecimal, null);
            //create a decimal right of the tail, because we can assume that it belongs there
            secondNumberDecimal = secondNumberTail;
            //go back to the tail
            secondNumberDecimal.nextDigit = new SingleDigit(100, secondNumberDecimal, null);
            //create a decimal right of the tail, because we can assume it belongs there
            firstNumberDecimal = firstNumberDecimal.nextDigit;  
            secondNumberDecimal = secondNumberDecimal.nextDigit;
            //we're going to be working from the decimal, so place the pointer at the decimal
       }
       else if(firstNumberDecimal == null)
       {
            firstNumberDecimal = firstNumberTail;
            //go back to the tail
            firstNumberDecimal.nextDigit = new SingleDigit(100, firstNumberDecimal, null);
            //create a decimal right of the tail, because we can assume that it belongs there
            firstNumberDecimal = firstNumberDecimal.nextDigit;
            //we're going to be working from the decimal, so place the pointer at the decimal
       }
       else if(secondNumberDecimal == null)
       {
            secondNumberDecimal = secondNumberTail;
            //go back to the tail
            secondNumberDecimal.nextDigit = new SingleDigit(100, secondNumberDecimal, null);
            //create a decimal right of the tail, because we can assume it belongs there
            secondNumberDecimal = secondNumberDecimal.nextDigit;
            //we're going to be working from the decimal, so place the pointer at the decimal
       }
  
       firstNumberPointer = firstNumberDecimal;
       secondNumberPointer = secondNumberDecimal;
       resultNumberPointer = finalResultDecimal;
       //start the modification pointer at the decimal for all numbers
       
       while(firstNumberPointer.previousDigit != null && secondNumberPointer.previousDigit != null)
       //while we're not at the leftmost part of either number
       {
           firstNumberPointer = firstNumberPointer.previousDigit;
           secondNumberPointer = secondNumberPointer.previousDigit;
           //traverse down both numbers by one digit
           getResult = firstNumberPointer.getDigit() + secondNumberPointer.getDigit();
           //add the digits together
           createDigit = new SingleDigit(getResult, null, resultNumberPointer);
           //create a new node containing the result of the addition, and the next pointer points to the result modification pointer
           resultNumberPointer.previousDigit = createDigit;
           //the right pointer will point to what we created
           resultNumberPointer = createDigit;
           //make our modificaition pointer what we created for looping purposes
       }
       
       if(firstNumberPointer.previousDigit == null && secondNumberPointer.previousDigit != null)
       //if we got to the end of the first number but not the second
       {
           resultNumberPointer.previousDigit = secondNumberPointer.previousDigit;
           secondNumberPointer.nextDigit = resultNumberPointer;
           //tack on the rest of the second number to the result
       }
       else if (secondNumberPointer.previousDigit == null && firstNumberPointer.previousDigit != null)
       //if we got to the end of the second number but not the first
       {
           resultNumberPointer.previousDigit = firstNumberPointer.previousDigit;
           firstNumberPointer.nextDigit = resultNumberPointer;
           //tack on the rest of the first number to the result
       }
     
       firstNumberPointer = firstNumberDecimal;
       secondNumberPointer = secondNumberDecimal;
       resultNumberPointer = finalResultDecimal;
       //start at the decimal point again
       
       while((firstNumberPointer.nextDigit != null) && (secondNumberPointer.nextDigit != null))
       //while we're not at the rightmost end of either number
       {
           firstNumberPointer = firstNumberPointer.nextDigit;
           secondNumberPointer = secondNumberPointer.nextDigit;
           //traverse down the first and second number
           getResult = firstNumberPointer.getDigit() + secondNumberPointer.getDigit();
           //add the two together
           createDigit = new SingleDigit(getResult, resultNumberPointer, null);
           //create a new digit containing the result of the addition, and the previous pointer will point to our modification pointer
           resultNumberPointer.nextDigit = createDigit;
           //the modification pointer's next pointer will point to the created node
           resultNumberPointer = createDigit;
           //make our modificaition pointer what we created for looping purposes
           }   
       
       if(firstNumberPointer.nextDigit == null && secondNumberPointer.nextDigit != null)
       //if we're at the end of the first number but not the second
       {
           secondNumberPointer = secondNumberPointer.nextDigit;
           resultNumberPointer.nextDigit = secondNumberPointer.nextDigit;
           secondNumberPointer.previousDigit = resultNumberPointer;
           //tack on the rest of the second number
       }
       else if (secondNumberPointer.nextDigit == null && firstNumberPointer.nextDigit != null)
       //if we're at the end of the second number but not the first
       {
           firstNumberPointer = firstNumberPointer.nextDigit;
           resultNumberPointer.nextDigit = firstNumberPointer.nextDigit;
           firstNumberPointer.previousDigit = resultNumberPointer;
           //tack on the rest of the first number
       }
      
       while(resultNumberPointer.nextDigit != null)
       {
           resultNumberPointer = resultNumberPointer.nextDigit;
       }
       //traverse down the rest of the list

       finalResultTail = resultNumberPointer;
        //because we're at the end of the list, we can make the tail pointer point to the tail  

       while(resultNumberPointer != null)
       //while we're not at the end of the list
       {
           int getDigit = resultNumberPointer.getDigit();
           //get the current digit
           
           if(carryOver)
           //if carryover is true from a previous loop
           {
               getDigit++;
               resultNumberPointer.changeDigit(getDigit);
               //add one to the current digit
           }
 
           if(resultNumberPointer.getDigit() > 9 && resultNumberPointer.getDigit() != 100)  
           //if the current number is greater than 9, but isn't a decimal
           {
               if(resultNumberPointer.previousDigit == null)
               //if the next number is null
               {
                   resultNumberPointer.previousDigit = new SingleDigit(1, null, resultNumberPointer);
                   //create a node containing a number 1 digit, pointing to the next digit
                   resultNumberPointer.changeDigit(getDigit - 10);
                   //subtract 10 from the current digit because we just carried over the 10
                   carryOver = false;
                   //there is no carry over here, officer
               }
               else
               //if the next node isn't null
               {
                   resultNumberPointer.changeDigit(getDigit - 10);
                   //subtract 10 from the current digit
                   carryOver = true;
                   //there is a carryover here, officer
               }
           }
           else
           //if the number is less than 10
           {
               carryOver = false;
               //no carryover here officer
           }
           resultNumberPointer = resultNumberPointer.previousDigit;
           //we need to move on
       }
       return finalResultTail;
       //return the tail of the final result once we're all done
    }       
   
    public static void displayNumber(SingleDigit getNumber)
    {
        SingleDigit printNumberPointer;
        //create a temporary pointer for printing
        String finalPrintNumber = "";
        //we're gonna be concatenating a string here
        for(printNumberPointer = getNumber; printNumberPointer != null; printNumberPointer = printNumberPointer.previousDigit)
        //we'll be working with the given number, while we're not at the end of the number, traverse down the number by one digit every repetition
        {
            if(printNumberPointer.getDigit() > 20)
            //if the number is a decimal
            {
                finalPrintNumber =  "." + finalPrintNumber;
            //shove a decimal in there
            }
            else
            //otherwise we can assume it's a number
            {
                finalPrintNumber =  printNumberPointer.getDigit() + finalPrintNumber;
            //shove the number in there
            }       
        }
        System.out.println(finalPrintNumber + "\n");
        //print out said numeber
    }
}