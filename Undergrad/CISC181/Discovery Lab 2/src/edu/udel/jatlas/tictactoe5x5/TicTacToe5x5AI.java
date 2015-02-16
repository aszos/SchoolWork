package edu.udel.jatlas.tictactoe5x5;


import java.util.ArrayList;
import java.util.List;

import edu.udel.jatlas.gameframework.AI;
import edu.udel.jatlas.gameframework.Action;

public class TicTacToe5x5AI extends AI<TicTacToe5x5Game> {
    public TicTacToe5x5AI(TicTacToe5x5Game game, String symbol) {
        super(game, symbol);
    }
    public TicTacToe5x5AI(TicTacToe5x5Game game, String symbol, long turnTimeLength) {
        super(game, symbol, turnTimeLength);
    }
    
    // convenience method
    private char getOurSymbol() {
        return getIdentifier().charAt(0);
    }

    protected boolean isMyTurn() {
        return getGame().getTurn() == getOurSymbol();
    }

    /**
     * Get all possible valid actions for the TicTacToe game.
     * 
     * @param game
     * @return
     */
    public List<Action<TicTacToe5x5Game>> getAllValidActions(TicTacToe5x5Game game) {
        List<Action<TicTacToe5x5Game>> validMoves = new ArrayList<Action<TicTacToe5x5Game>>();
        
        for(int i = 0; getGame().isWithinBounds(i, 0); i++)
        {
        	for(int j = 0; getGame().isWithinBounds(i, j); j++)
        	{
        		PlacePieceAction ppa = new PlacePieceAction(getOurSymbol(), i, j);
        		if(ppa.isValid(game))
        		{
        			validMoves.add(ppa);
        		}
        	}
        }
        return validMoves;
    }

    /**
     * Uses a simple heuristic that computes the score for this AI player
     * if the AI was to perform the given action.
     */
    public double getHeuristicScore(Action<TicTacToe5x5Game> action, TicTacToe5x5Game game) 
    { 
    	PlacePieceAction castedAction = (PlacePieceAction)action;
        game.setPiece(castedAction.getRow(), castedAction.getColumn(), new Piece(getOurSymbol()));
    	double heuristicScore = game.getScore(getOurSymbol());
    	game.setPiece(castedAction.getRow(), castedAction.getColumn(), new Piece(Piece.EMPTY));
        return heuristicScore;	
    }
}
