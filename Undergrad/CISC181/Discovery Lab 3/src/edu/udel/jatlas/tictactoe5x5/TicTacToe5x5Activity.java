package edu.udel.jatlas.tictactoe5x5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jatlas.gameframework.Game;
import edu.udel.jatlas.gameframework.GameListener;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TicTacToe5x5Activity extends Activity implements
		GameListener<TicTacToe5x5Game> {

	private TextView status;
	private TicTacToe5x5Game game;
	private TicTacToe5x5View2D gameView;
	private LinearLayout gameLayout;

	private static final int GAMETYPE_HUMAN_AI = 0;
	private static final int GAMETYPE_HUMAN_HUMAN = 1;
	private static final int GAMETYPE_AI_AI = 2;
	private int gameType;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		status = new TextView(this);
		gameView = new TicTacToe5x5View2D(this);
		gameLayout = new LinearLayout(this);

		status.setTypeface(Typeface.MONOSPACE);

		gameLayout.setOrientation(LinearLayout.VERTICAL);
		gameLayout.addView(status);
		gameLayout.addView(gameView);

		startGame();
		setContentView(gameLayout);
	}

	@Override
	public void onPerformActionEvent(Action<TicTacToe5x5Game> action,
			TicTacToe5x5Game game) {
		updateViews();
	}

	@Override
	public void onTickEvent(TicTacToe5x5Game game) {

	}

	@Override
	public void onStartEvent(TicTacToe5x5Game game) {
		updateViews();
	}

	@Override
	public void onEndEvent(TicTacToe5x5Game game) {
	}

	@Override
	public void onEvent(String event, TicTacToe5x5Game game) {

	}

	public void updateViews() {
		status.setText(game.getStatus());
		gameView.invalidate();
	}

	public TicTacToe5x5Game getCurrentGame() {
		return game;
	}

	private void startGame() {
		game = TicTacToe5x5Game.makeStartGame('x', 'o');

		if (Math.random() > 0.5) {
			game.changeTurn();
		}

		game.addGameListener(this);

		List<Character> humanPlayers = new ArrayList<Character>();

		if (gameType == GAMETYPE_HUMAN_HUMAN || gameType == GAMETYPE_HUMAN_AI) {
			humanPlayers.add('x');

		} else {
			game.addGameListener(new TicTacToe5x5AI(game, "x", 1000));
		}

		if (gameType == GAMETYPE_AI_AI || gameType == GAMETYPE_HUMAN_AI) {
			game.addGameListener(new TicTacToe5x5AI(game, "o", 1000));
		} else {
			humanPlayers.add('o');
		}

		game.start();
		gameView.setOnTouchListener(new TicTacToe5x5Human(this,
				new HashSet<Character>(humanPlayers)));
	}

	public void restartGame() {
		if (game != null && game.getLifecycle() != Game.ENDED) {
			game.end();
		}
		startGame();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("AI Game");
		menu.add("1 Player Game");
		menu.add("2 Player Game");
		menu.add("Restart");
		menu.add("Quit");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().equals("AI Game")) {
			gameType = GAMETYPE_AI_AI;
			restartGame();
		} else if (item.getTitle().equals("1 Player Game")) {
			gameType = GAMETYPE_HUMAN_AI;
			restartGame();
		} else if (item.getTitle().equals("2 Player Game")) {
			gameType = GAMETYPE_HUMAN_HUMAN;
			restartGame();
		} else if (item.getTitle().equals("Restart")) {
			restartGame();
		} else if (item.getTitle().equals("Quit")) {
			finish();
		}

		gameView.invalidate();
		return true;
	}

}
