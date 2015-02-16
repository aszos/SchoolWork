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
        for (int i = 0; i < 25; i++) {
            PlacePieceAction action = new PlacePieceAction(getOurSymbol(), i / 5, i % 5);
            if (action.isValid(game)) {
                validMoves.add(action);
            }
        }
        return validMoves;
    }

    /**
     * Uses a simple heuristic that computes the score for a speculative action.
     * To do this, the method must mutate the board, check the game's score,
     * and then undo the mutation.
     */
    public double getHeuristicScore(Action<TicTacToe5x5Game> action, TicTacToe5x5Game game) {
        // must pretend to make the move by setting the piece directly
        PlacePieceAction ppa = (PlacePieceAction)action;
        game.getBoard()[ppa.getRow()][ppa.getColumn()] = new Piece(getOurSymbol());
        
        // compute score for ourself only
        double heuristic = game.getScore(getOurSymbol());
        
        // now must undo the move -- this is important otherwise the AI will not work
        game.getBoard()[ppa.getRow()][ppa.getColumn()] = new Piece(Piece.EMPTY);
        
        return heuristic;
    }
    
//    public double getHeuristicScore(Action<TicTacToe5x5Game> action, TicTacToe5x5Game game) {
//        game.perform(action);
//        game.changeTurn();
//        int score = game.getScore(game.getTurn());
//        PlacePieceAction act = (PlacePieceAction) action;
//        game.setPiece(act.getRow(), act.getColumn(), new Piece(Piece.EMPTY));
//        return score;
//    }
}
