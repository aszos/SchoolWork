/*
Created by Alexander Szostek
Created on November 9th, 2012
Data Structures

Program Description
This program is designed to take two given doubles of any finite size, add them together using doubly linked lists, then print out the result.

Variable Dictionary   
int changeToThisDigit - the paramenter entered when the user wants to change the number
int digit - stores the digit in a node
SingleDigit previousDigit - pointer for the previous digit
SingleDigit nextDigit - pointer for the next digit

*/
public class SingleDigit
{
    private int digit;
    SingleDigit previousDigit, nextDigit; 
    public SingleDigit(int digit, SingleDigit previousDigit, SingleDigit nextDigit)
    {
        this.digit = digit;
        this.previousDigit = previousDigit;
        this.nextDigit = nextDigit;
    }
    
    public int getDigit()
    {
        return digit;
    }
    
    public void changeDigit(int changeToThisDigit)
    {
        this.digit = changeToThisDigit;
    }
    

}