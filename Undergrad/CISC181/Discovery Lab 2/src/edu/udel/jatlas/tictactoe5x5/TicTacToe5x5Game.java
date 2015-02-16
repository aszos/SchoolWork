package edu.udel.jatlas.tictactoe5x5;

import java.util.Arrays;

import edu.udel.jatlas.gameframework.ConsoleListener;
import edu.udel.jatlas.gameframework.Game;

/**
 * A basic TicTacToe game model that has been modified to allow
 * play on a 5x5 board where the winner must get 4 in a row.
 * In addition, pieces may be specified to be empty, x, o, or
 * blocked (neither player can place a piece there).
 * 
 * @author jatlas
 */
public class TicTacToe5x5Game extends Game {
    private Piece[][] board;
    private char turn;
    private char notTurn;
    
    
    public TicTacToe5x5Game(Piece[][] board, char turn, char notTurn) {
        this.board = board;
        this.turn = turn;
        this.notTurn = notTurn;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public char getTurn() {
        return turn;
    }

    public char getNotTurn() {
        return notTurn;
    }
    
    /**
     * Swaps the current turn with the notTurn
     */
    public void changeTurn() 
    {
    	char turnHolder = turn;
    	this.turn = notTurn;
    	notTurn = turnHolder;
    }
    
    /**
     * A simple setter for board at a row, column
     */
    public void setPiece(int row, int column, Piece piece) {
        board[row][column] = piece;
    }
    
    public String getStatus() {
        if (isEnd()) 
        	{
            if (isWinner(getTurn())) {
                return "Player " + getTurn() + " wins!\n";
            }
            else if (isWinner(getNotTurn())) {
                return "Player " + getNotTurn() + " wins!\n";
            }
            else {
                return "It is a draw.\n";
            }
        }
        else {
            return "Player " + turn + "'s turn\n";
        }
    }
    
    /**
     * Returns a "visual" representation of a TicTacToe5x5 board
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getStatus());
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                buffer.append(board[i][j]);
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
    
    /**
     * Checkes to see if the row and column are within the bounds of
     * the board.
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean isWithinBounds(int row, int column) {
        return row >= 0 && column >= 0 && 
                row < getBoard().length && 
                column < getBoard()[row].length;
    }
        
    /**
     * Gets the maximum sequential times that the given symbol appears
     * on a Piece in the board. Starts at row, column and iterates by
     * dr, dc each loop iteration as long as row, column are within
     * bounds. 
     */
    public int getMaxSequence(int row, int column, int dr, int dc, char symbol) 
    {
    	int maxSequence = 0;
    	
    	for(int i = row, j = column; isWithinBounds(i, j); i += dr , j += dc)
    	{
    		if(board[i][j].getSymbol() == symbol)
    		{
    			maxSequence = (maxSequence == 0)? (1):(maxSequence);
    			maxSequence += (isWithinBounds(i - dr, j - dc) && board[i - dr][j - dc].getSymbol() == symbol)? (1):(0);
    		}
    	}
    	
    	return maxSequence;
    }   
    
    /**
     * Gets the score for the given symbol in the board. The score is
     * the max value from all results returned from calling getMaxSequence
     * for each unique left-to-right, top-to-bottom, down-right-diagonal, 
     * and down-left-diagonal path.
     */
    public int getScore(char symbol) 
    {         	
    	//determine the max sequence going from left to right, starting at 0,0
    	int leftToRight = 0;
    	for(int i = 0; i < board.length; i++)
    	{
    		leftToRight = Math.max(leftToRight, getMaxSequence(i, 0, 0, 1, symbol));
    	}
    	
    	//determine the max sequence going from top to bottom, starting at 0,0
    	int topToBottom = 0;
    	for(int i = 0; i < board.length; i++)
    	{
    		topToBottom = Math.max(topToBottom, getMaxSequence(0, i, 1, 0, symbol));
    	}
    	
    	//determine the max diagonal sequence going from the top left to the bottom right, starting at the bottom left
    	int downRightDiagonal = 0;
    	for(int i = board.length - 1; i >= 0; i--)
    	{
    		for(int j  = 0; isWithinBounds(i, j); j++)
    		{
    			downRightDiagonal = Math.max(downRightDiagonal, getMaxSequence(i, j, 1, 1, symbol));
    		}
    	}
    
    	//determine the max diagonal sequence going from the top right to the bottom left, starting at the bottom right
        int downLeftDiagonal = 0;
        for(int i = board.length - 1; i >= 0; i--)
    	{
    		for(int j  = board.length - 1; isWithinBounds(i, j); j--)
    		{
    			downLeftDiagonal = Math.max(downLeftDiagonal, getMaxSequence(i, j, -1, -1, symbol));
    		}
    	}
    
        //store all the maximum scores into an array
        int[] scores = {leftToRight, topToBottom, downRightDiagonal, downLeftDiagonal};
        
        //sort the scores in ascending order
        Arrays.sort(scores);
        
        //return the largest value
        return scores[scores.length - 1];
    }
    
    /**
     * Checks to see if the player represented by the given symbol is
     * a winner.
     * 
     * @param symbol
     * @return
     */
    public boolean isWinner(char symbol) {
        return getScore(symbol) >= 4;
    }
    
    /**
     * Returns true if there is any empty space in the board.
     * Uses the isEmpty() method on Piece.
     * 
     * @return
     */
    public boolean hasEmptySpace() 
    {
        for(int i = 0; i < board.length; i++)
        {
        	for(int j = 0; j < board[i].length; j++)
        	{
        		if(board[i][j].isEmpty())
        		{
        			return true;
        		}
        	}
        }
        return false;
    }
    
    /**
     * Determines if the game is at an end state (i.e. no further moves can be
     * made). For TicTacToe this method returns true if there are no empty
     * spaces on the board or if either player has won.
     * 
     * @return
     */
    public boolean isEnd() {
        return !hasEmptySpace() || isWinner(turn) || isWinner(notTurn);
    }
    
    
    public static TicTacToe5x5Game makeStartGame(char turn, char notTurn) {
        // creates a starting board
        Piece E = new Piece(Piece.EMPTY);
        Piece B = new Piece(Piece.BLOCKED);
        
        Piece[][] board = new Piece[][]
                {{E, E, E, E, E},
                 {E, E, E, E, E},
                 {E, E, B, E, E},
                 {E, E, E, E, E},
                 {E, E, E, E, E}};
        return new TicTacToe5x5Game(board, turn, notTurn);
    }
    
    
    public static void main(String[] args) {
        TicTacToe5x5Game game = makeStartGame('x', 'o');
        game.addGameListener(new ConsoleListener());
        game.addGameListener(new TicTacToe5x5AI(game, "x", 1000));
        game.addGameListener(new TicTacToe5x5AI(game, "o", 1000));
        // replace the above lines with these to see the game run "instantly"
//        game.addGameListener(new TicTacToe5x5AI(game, "x", 1));
//        game.addGameListener(new TicTacToe5x5AI(game, "o", 1));
        game.start();
        game.getMaxSequence(0, 0, 0, 1, 'o');
        game.getMaxSequence(0, 0, 0, 1, 'x');
    }
}
