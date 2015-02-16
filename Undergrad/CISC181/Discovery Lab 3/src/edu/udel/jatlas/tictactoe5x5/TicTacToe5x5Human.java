package edu.udel.jatlas.tictactoe5x5;

import java.util.Set;

import android.view.MotionEvent;
import android.view.View;

public class TicTacToe5x5Human implements View.OnTouchListener {

	private TicTacToe5x5Activity activity;
	private Set<Character> humanPlayers;

	public TicTacToe5x5Human(TicTacToe5x5Activity activity,
			Set<Character> humanPlayers) {
		this.activity = activity;
		this.humanPlayers = humanPlayers;
	}

	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		TicTacToe5x5Game game = activity.getCurrentGame();
		if (game != null) {
			// is the game ended? if so restart it!
			if (game.isEnd()) {
				activity.restartGame();
			} else if (humanPlayers.contains(game.getTurn())) {
				// where did they click on the board?
				int row = (int) ((event.getY() / v.getHeight()) * game
						.getBoard().length);
				int col = (int) ((event.getX() / v.getWidth()) * game
						.getBoard()[0].length);

				if (action == MotionEvent.ACTION_DOWN) {
					game.perform(new PlacePieceAction(game.getTurn(), row, col));
				}
			}
		}

		// we don't need any more events in this sequence
		return false;
	}
}
