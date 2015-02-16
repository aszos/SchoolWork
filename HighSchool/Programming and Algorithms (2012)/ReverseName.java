package IntroToAlgos;

import javax.swing.*;

class ReverseName

{
public static void main (String args [])
{
// Enter a string

String name = JOptionPane.showInputDialog ("Enter First & Last Name");

System.out.println ("Entered name is " + name);

//simple routine to reverse names in variable or array element

String first=""; String last="";

int i = 0;
while (name.charAt(i) != ' ') //loop until blank is found
   {
    first=first + name.charAt(i); //concatenate characters before blank
    i = i + 1; // move to next character
   }

System.out.println ("First name is " + first);

while (i < name.length()) //start at char after blank and move to end of name
   {
    last=last + name.charAt(i); //concatenate characters after blank
    i = i + 1; //move to end of string
   }
System.out.println("Last name is " + last);

name = last + " " + first; //reverse names inserting blank between

/* if this is run again, it will re-reverse the name (omitting the input) */

System.out.println ("Name reversed is " + name);

}//end main method
}//end class ReverseName