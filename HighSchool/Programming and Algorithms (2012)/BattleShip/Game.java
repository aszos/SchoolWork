public class Game
{
public static void main(String args[])
{
	Grid accessToGrid = new Grid();
	FillGrid accessToFillGrid = new FillGrid();
	Square accessToSquare = new Square(0, 0, 0);
	
	Square player1[][] = new Square[11][11];
		accessToGrid.fillCoords(player1);
	Square player2[][] = new Square[11][11];
		accessToGrid.fillCoords(player2);
		
	Ship aircraftCarrier = new Ship(5); //object of the Ship class has a length of (shipLength)
	Ship battleship = new Ship(4);
	Ship submarine = new Ship(3);
	Ship destroyer = new Ship(3);
	Ship patrolBoat = new Ship(2);	
		
	Square player2Blank[][] = new Square[11][11];
		accessToGrid.fillCoords(player2Blank); //Player 1's board for attacking Player 2
		accessToGrid.battleGrid(player1, player2Blank);
	Square player1Blank[][] = new Square[11][11];
		accessToGrid.fillCoords(player1Blank);//Player 2's board for attacking Player 1
		accessToGrid.battleGrid(player2, player1Blank);	
		
////////////////////////***PLAYER 1 FILLS BOARD***///////////////////

	int placeAircraftCarrierP1[] = accessToFillGrid.placeBoats(player1, aircraftCarrier);
		accessToFillGrid.listChoices(player1, placeAircraftCarrierP1, aircraftCarrier);
		accessToFillGrid.printGrid(player1);
		
	int placeBattleshipP1[] = accessToFillGrid.placeBoats(player1, battleship);
		accessToFillGrid.listChoices(player1, placeBattleshipP1, battleship);
		accessToFillGrid.printGrid(player1);
		
	int placeSubmarineP1[] = accessToFillGrid.placeBoats(player1, submarine);
		accessToFillGrid.listChoices(player1, placeSubmarineP1, submarine);
		accessToFillGrid.printGrid(player1);
		
	int placeDestroyerP1[] = accessToFillGrid.placeBoats(player1, destroyer);
		accessToFillGrid.listChoices(player1, placeDestroyerP1, destroyer);
		accessToFillGrid.printGrid(player1);
		
	int placePatrolBoatP1[] = accessToFillGrid.placeBoats(player1, patrolBoat);
		accessToFillGrid.listChoices(player1, placePatrolBoatP1, patrolBoat);
		accessToFillGrid.printGrid(player1);
	
	accessToGrid.battleGrid(player1, player2Blank);
	
////////////////////////***PLAYER 2 FILLS BOARD***///////////////////
		
	int placeAircraftCarrierP2[] = accessToFillGrid.placeBoats(player2, aircraftCarrier);
		accessToFillGrid.listChoices(player2, placeAircraftCarrierP2, aircraftCarrier);
		accessToFillGrid.printGrid(player2);
		
	int placeBattleshipP2[] = accessToFillGrid.placeBoats(player2, battleship);
		accessToFillGrid.listChoices(player2, placeBattleshipP2, battleship);
		accessToFillGrid.printGrid(player2);
		
	int placeSubmarineP2[] = accessToFillGrid.placeBoats(player2, submarine);
		accessToFillGrid.listChoices(player2, placeSubmarineP2, submarine);
		accessToFillGrid.printGrid(player2);
		
	int placeDestroyerP2[] = accessToFillGrid.placeBoats(player2, destroyer);
		accessToFillGrid.listChoices(player2, placeDestroyerP2, destroyer);
		accessToFillGrid.printGrid(player2);
		
	int placePatrolBoatP2[] = accessToFillGrid.placeBoats(player2, patrolBoat);
		accessToFillGrid.listChoices(player2, placePatrolBoatP2, patrolBoat);
		accessToFillGrid.printGrid(player2);
		
	accessToGrid.battleGrid(player2, player1Blank);
	
////////////////////////***ATTACKING COMMENCES***///////////////////
	
	
	
	boolean gameDone = false; //the game is still going on
	while (!gameDone)
	{
	accessToSquare.attackSquare(player2, player2Blank);
		accessToFillGrid.printGrid(player2Blank);
		accessToGrid.winCondition(player2);
	accessToSquare.attackSquare(player1, player1Blank);
		accessToFillGrid.printGrid(player1Blank);
		accessToGrid.winCondition(player1);
	}



}
}