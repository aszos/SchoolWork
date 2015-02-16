package edu.udel.jatlas.tictactoe5x5;

import edu.udel.jatlas.gameframework.ConsoleListener;
import edu.udel.jatlas.gameframework.Game;

/**
 * A basic TicTacToe game model that has been modified to allow play on a 5x5
 * board where the winner must get 4 in a row. In addition, pieces may be
 * specified to be empty, x, o, or blocked (neither player can place a piece
 * there).
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
	public void changeTurn() {
		char current = turn;
		turn = notTurn;
		notTurn = current;
	}

	/**
	 * A simple setter for board at a row, column
	 */
	public void setPiece(int row, int column, Piece piece) {
		board[row][column] = piece;
	}

	public String getStatus() {
		if (isEnd()) {
			if (isWinner(getTurn())) {
				return "Player " + getTurn() + " wins!";
			} else if (isWinner(getNotTurn())) {
				return "Player " + getNotTurn() + " wins!";
			} else {
				return "It is a draw.";
			}
		} else {
			return "Player " + turn + "'s turn";
		}
	}

	/**
	 * Returns a "visual" representation of a TicTacToe5x5 board
	 */
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(getStatus() + "\n");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				buffer.append(board[i][j]);
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public boolean isWithinBounds(int row, int column) {
		return row >= 0 && column >= 0 && row < getBoard().length
				&& column < getBoard()[row].length;
	}

	public int getMaxSequence(int row, int column, int dr, int dc, char symbol) {
		int maxSequence = 0;
		int currentSequence = 0;
		while (isWithinBounds(row, column)) {
			// we are looping down the sequence looking for chars == symbol
			if (board[row][column].getSymbol() == symbol) {
				currentSequence++;
			} else {
				// the square does not belong to player
				currentSequence = 0;
			}
			maxSequence = Math.max(maxSequence, currentSequence);

			row += dr;
			column += dc;
		}
		return maxSequence;
	}

	public int getScore(char symbol) {
		int maxScore = 0;
		for (int i = 0; i < 5; i++) {
			// for each row (left to right)
			maxScore = Math.max(maxScore, getMaxSequence(i, 0, 0, 1, symbol));
			// for each column (top to bottom)
			maxScore = Math.max(maxScore, getMaxSequence(0, i, 1, 0, symbol));
			// for each diagonal (top-left to bottom-right)
			maxScore = Math.max(maxScore, getMaxSequence(0, i, 1, 1, symbol));
			maxScore = Math.max(maxScore, getMaxSequence(i, 0, 1, 1, symbol));
			// for each diagonal (top-right to bottom-left)
			// for each diagonal (top-left to bottom-right)
			maxScore = Math.max(maxScore, getMaxSequence(0, i, 1, -1, symbol));
			maxScore = Math.max(maxScore,
					getMaxSequence(i, board[0].length - 1, 1, -1, symbol));
		}
		return maxScore;
	}

	public boolean isWinner(char symbol) {
		return getScore(symbol) >= 4;
	}

	/**
	 * Returns true if there is any empty space in the board. Uses the isEmpty()
	 * method on Piece.
	 * 
	 * @return
	 */
	public boolean hasEmptySpace() {
		for (Piece[] row : board) {
			for (Piece value : row) {
				if (value.isEmpty()) {
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
	 * Method does not work right now because it requires code from DL2.
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

		Piece[][] board = new Piece[][] { { E, E, E, E, E }, { E, E, E, E, E },
				{ E, E, B, E, E }, { E, E, E, E, E }, { E, E, E, E, E } };
		return new TicTacToe5x5Game(board, turn, notTurn);
	}

	public static void main(String[] args) {
		TicTacToe5x5Game game = makeStartGame('x', 'o');
		game.addGameListener(new ConsoleListener());
		game.addGameListener(new TicTacToe5x5AI(game, "x", 1000));
		game.addGameListener(new TicTacToe5x5AI(game, "o", 1000));
		// replace the above lines with these to see the game run "instantly"
		// game.addGameListener(new TicTacToe5x5AI(game, "x", 1));
		// game.addGameListener(new TicTacToe5x5AI(game, "o", 1));
		game.start();
	}
}
