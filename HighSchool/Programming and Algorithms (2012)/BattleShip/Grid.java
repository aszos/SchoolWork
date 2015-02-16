import javax.swing.JOptionPane;
public class Grid
{

public void fillCoords(Square x[][]) //sets all elements equal to a status of 0
{
	for (int row = 1; row < 11; row++)
		for (int column = 1; column < 11; column++)
			x[row][column] = new Square(0, row, column);
} //ends method

public void battleGrid(Square original[][], Square copy[][])
{
for (int row = 1; row < 11; row++)
		for (int column = 1; column < 11; column++)
			{
			copy[row][column] = original[row][column];
			if (copy[row][column].getStatus() == 2)
				copy[row][column].setStatus(0);
			}
}

public void winCondition(Square original[][])
{
int count2s = 0;
for (int row = 1; row < 11; row++)
	{
	for (int column = 1; column < 11; column++)
		{
		if (original[row][column].getStatus() == 2)
			{
			count2s++;
			}		
		}
	}
if (count2s == 0)
	{
	JOptionPane.showMessageDialog(null, "You won!");
	}	
}


} //ends Grid class