/*
Alexander Szostek
Dr. Brown
Data Structures
December 27th, 2012

***Program Description***
The purpose of this program is to simulate a Reverse Polish notation calculator by utilizing the stack (FILO) structure.

***Variable Dictionary***
Node createNode - stores the result of the operation, this becomes the top of the stack
Node firstNumber - stores the first number of operation in terms of the stack
double getResult - stores the result of operation in double form
String getOperation - stores the operation that will be done
Node newTopStack - stores the new top of the stack when adding a number to the stack
Node topOfStack - stores the top of the stack
Node secondNumber - stores the second number of operation in terms of the stack
String stackInString - concatenated form of the stack
Node stackPointer - a temporary pointer used for traversal
double value - stores the value of one node
*/

import javax.swing.JOptionPane;

public class CalculatorMain
{
    public static void main(String[] args)
    {
        String getOperation = "";
        Node topOfStack = null; 
        while(true)
        //just keep looping, there's a quit later on
        {
            Node stackPointer;
            String stackInString = "";
            for(stackPointer = topOfStack; stackPointer != null; stackPointer = stackPointer.pointer)
            //start at the top of the stack, while we're not at the end of the stack, traverse down one node after each repetition
            {
                stackInString = "\n" + stackPointer.value + stackInString;
                //concatenate the stack so that we can print it through one string
            } 
            getOperation = JOptionPane.showInputDialog(null,"Enter in a number or operation, or enter 'q' to quit.\n" + stackInString);
            //simple JOptionPane asking for input, usually a number or an operator
            topOfStack = checkOperation(getOperation, topOfStack);
            //checkOperation is going to do something with the operation that was entered and the top of the stack, and will return the new top of stack
        }
    }
    
    public static Node checkOperation(String getOperation, Node topOfStack)
    {
        if(getOperation == null || getOperation.equals("q"))
        //if the user entered nothing or they entered "q"
        {
            System.exit(0);
            //exit the program
        } 
        else if(getOperation.equals("+"))
        //if we're gonna add 
        {
            topOfStack = additionOperation(topOfStack);
            //call the addition method by sending the top of the stack, and later will return the new top of the stack
        }
        else if(getOperation.equals("-"))
        //if we're gonna subtract
        {
            topOfStack = subtractionOperation(topOfStack);
            //call the subtraction method by sending the top of the stack, and later will return the new top of the stack
        }
        else if(getOperation.equals("*"))
        //if we're gonna multiply
        {
            topOfStack = multiplicationOperation(topOfStack); 
            //call the multiplication method by sending the top of the stack, and later will return the new top of the stack
        }
        
        else if(getOperation.equals("/"))
        //if we're gonna divide
        {
            topOfStack = divisionOperation(topOfStack);
            //call the division method by sending the top of the stack, and later will return the new top of the stack
        }
        else
        //if we're not quitting or doing an opertation, assume it's a number or some other junk
        {
            try
            //see if we can get a double out of what was entered
            {
            double value = Double.parseDouble(getOperation);
            //find the value that was entered
            topOfStack = numberOperation(topOfStack, value);  
            //call the numberOperation method, which will return the new top of the stack
            } 
            catch(NumberFormatException e)
            //if the user enters in something other than an operator or a number
            {
                JOptionPane.showMessageDialog(null,"Enter in a number or an operation, nothing else!");
                //tell them their mistake
            }
        }
        return topOfStack;
        //once we're all done, we gotta return the top of the stack
    }
    
    public static Node additionOperation(Node topOfStack)
    {
       Node stackPointer = topOfStack;
       if(stackPointer == null || stackPointer.pointer == null)
       //if we're adding with zero or one number
       {/*do nothing*/}
       else
       //if we're operating with 2 numbers
       {
           Node firstNumber = stackPointer;
           Node secondNumber = stackPointer.pointer;
           //get the first and second numbers of operation
           
           if(((firstNumber.value == Double.POSITIVE_INFINITY)&&(secondNumber.value == Double.NEGATIVE_INFINITY)) || ((secondNumber.value == Double.POSITIVE_INFINITY)&&(firstNumber.value == Double.NEGATIVE_INFINITY)))
           //if any of the following cases are true: (∞ + -∞), (-∞ + ∞)
           {
               JOptionPane.showMessageDialog(null, "The result is indeterminate."); 
               //tell the user that you can't do that
               topOfStack = secondNumber.pointer;
               //make the top of the stack two nodes down the stack, this will prevent errors and weird numbers
           }
           else
           {
               double getResult = firstNumber.value + secondNumber.value;
               //do the operation
               Node createNode = new Node(secondNumber.pointer, getResult);
               //make a new node that contains the result and make it point to the second number's pointer
               topOfStack = createNode;
               //make the new top of stack what was just created
           }
       }
       return topOfStack;  
       //return the top of the stack when we're done
    }
    
    public static Node subtractionOperation(Node topOfStack)
    {
       Node stackPointer = topOfStack;
       if(stackPointer == null || stackPointer.pointer == null)
       //if we're dealing with zero or one number
       {/*do nothing*/}
       else
       //if we're dealing with 2 numbers
       {
           Node firstNumber = stackPointer;
           Node secondNumber = stackPointer.pointer;
           //get the first and second numbers of operation
           if(((firstNumber.value == Double.POSITIVE_INFINITY)||(firstNumber.value == Double.NEGATIVE_INFINITY)) && ((secondNumber.value == Double.POSITIVE_INFINITY)||(secondNumber.value == Double.NEGATIVE_INFINITY)))
           //to prevent any indeteminate results, check if either number is positive or negative infinity and if they are
           {
                JOptionPane.showMessageDialog(null, "The result is indeterminate."); 
                //tell the user that it's not possible
                topOfStack = secondNumber.pointer;
                //make the top of the stack two nodes down the stack, this will prevent errors and weird numbers
           }
           else
           //if we're not dealing with weird numbers
           {
               double getResult = firstNumber.value - secondNumber.value;
               //get the result of the operation
               Node createNode = new Node(secondNumber.pointer, getResult);
               //make a new node that contains the result and make it point to the second number's pointer
               topOfStack = createNode;
               //make the new top of stack what was just created
           }
       }
       return topOfStack;  
       //return the top of the stack when we're done
    }  
    
    public static Node multiplicationOperation(Node topOfStack)
    {
       Node stackPointer = topOfStack;
       if(stackPointer == null || stackPointer.pointer == null)
       //if we're dealing with zero or one number
       {/*do nothing*/}
       else
       //if we're dealing with 2 numbers
       {
           Node firstNumber = stackPointer;
           Node secondNumber = stackPointer.pointer;
           //get the first and second numbers of operation
           if(firstNumber.value == 0 && ((secondNumber.value == Double.POSITIVE_INFINITY) || (secondNumber.value == Double.NEGATIVE_INFINITY)))
           //if the operation done would be (0 * ∞)
           {
                JOptionPane.showMessageDialog(null,"The result is indeterminate.");
                //tell the user that the result would destroy everything
                topOfStack = secondNumber.pointer;
                //make the top of the stack two nodes down the stack, this will prevent errors and weird numbers
           }
           else
           //if we're not dealing with weird numbers
           {
               double getResult = firstNumber.value * secondNumber.value;
               //get the result of the operation
               Node createNode = new Node(secondNumber.pointer, getResult);
               //make a new node that contains the result and make it point to the second number's pointer
               topOfStack = createNode;
               //make the new top of stack what was just created
           }
       }
       return topOfStack;  
       //return the top of the stack when we're done    
    }
    
    public static Node divisionOperation(Node topOfStack)
    {
       Node stackPointer = topOfStack;
       if(stackPointer == null || stackPointer.pointer == null)
       //if we're dealing with zero or one number
       {/*do nothing*/}
       else
       //if we're dealing with 2 numbers
       { 
           Node firstNumber = stackPointer;
           Node secondNumber = stackPointer.pointer;
           //get the first and second numbers of operation
           if(firstNumber.value == 0 && secondNumber.value == 0)
           //if the operation done would be (0 / 0)
           {
                JOptionPane.showMessageDialog(null,"The result is indeterminate.");
                //tell them that math doesn't work like that
                topOfStack = secondNumber.pointer;
                //make the top of the stack two nodes down the stack, this will prevent errors and weird numbers
           }
           else if(((firstNumber.value == Double.POSITIVE_INFINITY)||(firstNumber.value == Double.NEGATIVE_INFINITY)) && ((secondNumber.value == Double.POSITIVE_INFINITY)||(secondNumber.value == Double.NEGATIVE_INFINITY)))
           //if the operation would be any of the following: (∞/∞), (-∞/∞), (∞/-∞), (-∞/-∞)
           {
                JOptionPane.showMessageDialog(null,"The result is indeterminate.");
                //tell the user that the result is indeterminate
                topOfStack = secondNumber.pointer;
                //make the top of the stack two nodes down the stack, this will prevent errors and weird numbers
           }
           else
           //if we're not dealing with weird numbers
           {
                double getResult = secondNumber.value / firstNumber.value;
                //get the result of the operation
                Node createNode = new Node(secondNumber.pointer, getResult);
                //make a new node that contains the result and make it point to the second number's pointer
                topOfStack = createNode;
                //make the new top of stack what was just created
           }   
       }
       return topOfStack;  
       //return the top of the stack when we're done   
    }
    
    public static Node numberOperation(Node topOfStack, double value)
    { 
        Node newTopStack = new Node(topOfStack, value);    
        //create a new node that points to the current top of the stack
        return newTopStack;
        //return the new node that was created, which will now become the new top of the stack
    }
}