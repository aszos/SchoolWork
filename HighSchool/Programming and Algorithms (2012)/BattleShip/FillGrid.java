import javax.swing.JOptionPane;
public class FillGrid
{
	
int left;
int right;
int up;
int down;
int response;
boolean inBoundsLeft = true;
boolean inBoundsRight = true;
boolean inBoundsUp = true;
boolean inBoundsDown = true;

public int[] placeBoats(Square x[][], Ship typeOfBoat)
{
	JOptionPane.showMessageDialog(null, "This ship is " + typeOfBoat.shipLength + " squares long.");
	int rowLoc = Integer.parseInt(JOptionPane.showInputDialog("Which row would you like?"));
	int colLoc = Integer.parseInt(JOptionPane.showInputDialog("Which column would you like?"));
		
	left = 	colLoc - typeOfBoat.shipLength;
	right = colLoc + typeOfBoat.shipLength;
	up = rowLoc - typeOfBoat.shipLength;
	down = rowLoc + typeOfBoat.shipLength;
	
	int places[] = new int[9]; //[1]..[8]
		places[1] = rowLoc;
		places[2] = left;
		places[3] = rowLoc;
		places[4] = right;
		places[5] = up;
		places[6] = colLoc;
		places[7] = down;
		places[8] = colLoc;
	
	//OUT-OF-BOUNDS PROCEDURE
	if (left < 0) //piece goes off the left side of the board
		{
		inBoundsLeft = false;
		}
	
	else if (right > 11) //piece goes off the right side of the board
		{
		inBoundsRight = false;
		}

	else if (up < 0) //piece goes off the top side of the board
		{
		inBoundsUp = false;
		}

	else if (down > 11) //piece goes off the bottom side of the board
		{
		inBoundsDown = false;
		}
	
	return places;
}

public void listChoices(Square x[][], int places[], Ship typeOfBoat)
{
	boolean chooseAnOptionAgain = true;
	while (chooseAnOptionAgain)
	{
	chooseAnOptionAgain = false;
	response = Integer.parseInt(JOptionPane.showInputDialog("Place: \n1. Facing left \n2. Facing right \n3. Facing up \n4. Facing down"));
	
	//OPTION 1 (LEFT)
	if (response == 1 && inBoundsLeft) //place ship to the left of the original point
		{
		if (x[places[1]][places[6]].status != 2) //if the pivot point is unoccupied
				x[places[1]][places[6]].status = 2; //set its status to "unhit ship"
		for (int counter = 0; counter <= typeOfBoat.shipLength - 1; counter++)
			{
			if (x[places[1]][places[6 - counter]].status == 2)
				{
				JOptionPane.showMessageDialog(null, "You can't overlap boats!");
				chooseAnOptionAgain = true;
				}
			}	
				//x[places[1]][places[6 - counter]].status = 2;
		}
	else if (response == 1 && !inBoundsLeft) //if out-of-bounds
		{
		JOptionPane.showMessageDialog(null, "You can't go off the board!");
		chooseAnOptionAgain = true;
		}
	
	//OPTION 2 (RIGHT)
	else if (response == 2 && inBoundsRight) //place ship to the right of the original point
		{
		for (int counter = 0; counter <= typeOfBoat.shipLength - 1; counter++)
			if (x[places[3]][places[4] - typeOfBoat.shipLength + counter].status != 0)
				{
				JOptionPane.showMessageDialog(null, "You can't overlap boats!");
				chooseAnOptionAgain = true;
				}
			else
				{
				x[places[1]][places[6]].status = 2;
				x[places[3]][places[4] - typeOfBoat.shipLength + counter].status = 2;
				}
		}
	else if (response == 2 && !inBoundsRight)
	{
		JOptionPane.showMessageDialog(null, "You can't go off the board!");
		chooseAnOptionAgain = true;
	}
	
	//OPTION 3 (UP)
	else if (response == 3 && inBoundsUp) //place ship above the original point
		{
		for (int counter = 0; counter <= typeOfBoat.shipLength - 1; counter++)
			if (x[places[5] + typeOfBoat.shipLength - counter][places[6]].status != 0)
				{
				JOptionPane.showMessageDialog(null, "You can't overlap boats!");
				chooseAnOptionAgain = true;
				}
			else
				{
				x[places[1]][places[6]].status = 2;
				x[places[5] + typeOfBoat.shipLength - counter][places[6]].status = 2;
				}
		}
	else if (response == 3 && !inBoundsUp)
	{
		JOptionPane.showMessageDialog(null, "You can't go off the board!");
		chooseAnOptionAgain = true;
	}
	
	//OPTION 4 (DOWN)
	else if (response == 4 && inBoundsDown) //place ship below the original point
		{
		for (int counter = 0; counter <= typeOfBoat.shipLength - 1; counter++)
			if (x[places[7] - typeOfBoat.shipLength + counter][places[8]].status != 0)
				{
				JOptionPane.showMessageDialog(null, "You can't overlap boats!");
				chooseAnOptionAgain = true;
				}
			else
				{
				x[places[1]][places[6]].status = 2;
				x[places[7] - typeOfBoat.shipLength + counter][places[8]].status = 2; //down - length --> down
				}
		}
	else if (response == 4 && !inBoundsDown)
	{
		JOptionPane.showMessageDialog(null, "You can't go off the board!");
		chooseAnOptionAgain = true;
	}
	
	}
}

public static void printGrid(Square x[][])
{ //ARRAYS FILL HORIZONTALLY
	String print = ""; //declare and initialize the print String
	for (int printHor = 1; printHor <= 10; printHor++) //row number
		{
			for (int printVert = 1; printVert <= 10; printVert++ ) //column number
			{
				if (printVert == 10) //if the column number is equal to the number of columns (right-hand boundary of the matrix), insert a line break.
					print = print + x[printHor][printVert].status + "\n";
				else //otherwise place the next value to the right of the previous value
					print = print + x[printHor][printVert].status + "   ";
			} //ends printVert loop
		} //ends printHor loop
	
	JOptionPane.showMessageDialog(null, "The grid is: \n \n" + print + "\nKEY\n0: Unhit water, 1: Hit water \n2: Unhit ship, 3: Hit ship"); //print the matrix (really printing the String).

} //ends printMatrix() method

}