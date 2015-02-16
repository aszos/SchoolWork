package IntroToAlgos;



	//ideas for menu
	import javax.swing.*;

public class Menu
	{
		public static void main (String args [])
		{
		
			String menuOptions = "Select an Option \n\n 1. Enter Data \n 2. Sort Data \n 3. Search Data \n 4. Output Data \n 5. Exit\n\n";
			boolean menuExit = false;
			
			while (!menuExit)
			{
				menuExit = callMenu(menuOptions);
			}
		}//end main method
		
		public static boolean callMenu (String out)
		{
			String response = JOptionPane.showInputDialog (out);
			
			//use of switch statement rather than series of if statements
			
			char choice = response.charAt(0); //pick off first character of menu choice
			switch (choice)
				{
					case '1': {System.out.println("enter data method call goes here"); return false;}
					case '2': {System.out.println("enter sort data method call here"); return false;}
					case '3': {System.out.println("enter search data method call here"); return false;}
					case '4': {System.out.println("enter output data method call here"); return false;}
					case '5': {
								System.out.println("enter exit here"); return true;
							  }
					default: return false;
				}
		} //end method callMenu
		
	}//end class Menu


