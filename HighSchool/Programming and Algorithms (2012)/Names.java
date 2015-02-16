package IntroToAlgos;
import javax.swing.*;
class Names {
public static void main (String args []){
String InputName = JOptionPane.showInputDialog ("Enter in your first and last name.");;
int x = 0;
String Last = "", First = "", ReversedName = "";
while (InputName.charAt(x) != ' '){	
	First=First + InputName.charAt(x); 
	x++; 
	}
while (x < InputName.length()){	
    Last=Last + InputName.charAt(x);
    x++; 
   }
ReversedName = Last + " " + First; 
JOptionPane.showMessageDialog(null,"Your first name is " + First + "\n\nYour last name is" + Last + "\n\nYour full name is " + InputName + "\n\nYour name reversed is" + ReversedName);
	
	}
}



