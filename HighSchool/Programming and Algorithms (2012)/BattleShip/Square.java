import javax.swing.JOptionPane;
public class Square
{
	//Ship boat = new Ship();
	int status; //0 = unhit water, 1 = hit water, 2 = unhit ship, 3 = hit ship
	int rowCoord; //row coordinate of the square
	int colCoord; //column coordinate of the square
	public Square(int status, int rowCoord, int colCoord)
	{
		this.status = status;
		this.rowCoord = rowCoord;
		this.colCoord = colCoord;
	}
public int getStatus()
{
return status;
}
	
public void setStatus(int newStatus)
{
status = newStatus;
}	
	
public void attackSquare(Square original[][], Square map[][])
{

boolean needInput = true; //the method needs to know which square to attack
while (needInput)
{
int chosenRow = Integer.parseInt(JOptionPane.showInputDialog("Which row would you like to attack?")); //row location of the square being attacked.
int chosenCol = Integer.parseInt(JOptionPane.showInputDialog("Which column would you like to attack?")); //column location of the square being attacked.

boolean validChoice = true; //the square being attacked is legitimately able to be attacked.
while (validChoice)
{
if (original[chosenRow][chosenCol].getStatus() == 0) //attacking unhit water --> results in a MISS
	{
	map[chosenRow][chosenCol].setStatus(1);
	needInput = false;
	break;
	}
else if (original[chosenRow][chosenCol].getStatus() == 1) //attacking hit water --> illegal move
	{
	validChoice = false;
	break;
	}
else if (original[chosenRow][chosenCol].getStatus() == 2) //attacking unhit ship --> results in a HIT
	{
	map[chosenRow][chosenCol].setStatus(3);
	needInput = false;
	break;
	}
else if (original[chosenRow][chosenCol].getStatus() == 3) //attacking hit ship --> illegal move
	{
	validChoice = false;
	break;
	}
}
}
	
}	
	
}