package edu.udel.jatlas.tictactoe5x5;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

/**
 * This view extends the basic android.view.View to provide implementations for
 * drawing directly to the Canvas.
 * 
 * @author jatlas
 * 
 */
@SuppressLint({ "DrawAllocation", "ViewConstructor" })
public class TicTacToe5x5View2D extends View {
	// the activity
	protected TicTacToe5x5Activity activity;

	// the width and height of the current game view
	private int width;
	private int height;

	// the scale of the game board grid, how many pixels per col (x) and row (y)
	private float scale_x;
	private float scale_y;

	private Bitmap aszostek;
	private Bitmap jatlas;

	private RectF aszostekBounds;
	private RectF jatlasBounds;

	public TicTacToe5x5View2D(TicTacToe5x5Activity context) {
		super(context);
		activity = context;
		setBackgroundColor(Color.BLACK);
		setFocusable(true);
		setFocusableInTouchMode(true);

		aszostekBounds = new RectF();
		jatlasBounds = new RectF();

		aszostek = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.aszostek);
		jatlas = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.jatlas);
	}

	/**
	 * This method is called by the Android platform when the app window size
	 * changes. We store the initial setting of these so that we can compute the
	 * exact locations to draw the components of our View.
	 */
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		width = w;
		height = h;

		updateScaling();
	}

	private void updateScaling() {
		if (activity.getCurrentGame() != null) {
			scale_x = (float) width
					/ activity.getCurrentGame().getBoard()[0].length;
			scale_y = (float) height
					/ activity.getCurrentGame().getBoard().length;
		}
	}

	/**
	 * Provides specific implementation for a TicTacToe5x5View.
	 */
	@SuppressLint("DrawAllocation")
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint gridPaint = new Paint();
		gridPaint.setStrokeWidth(4);
		gridPaint.setStyle(Style.FILL_AND_STROKE);

		Piece[][] board = activity.getCurrentGame().getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getSymbol() == 'x') {
					aszostekBounds.set(scale_x * j, scale_y * i, scale_x
							* (j + 1) - 1, scale_y * (i + 1) - 1);
					canvas.drawBitmap(aszostek, null, aszostekBounds, null);

				} else if (board[i][j].getSymbol() == 'o') {
					jatlasBounds.set(scale_x * j, scale_y * i, scale_x
							* (j + 1) - 1, scale_y * (i + 1) - 1);
					canvas.drawBitmap(jatlas, null, jatlasBounds, null);

				}
			}
		}
		drawGrid(canvas);
	}

	/**
	 * Draws a 5x5 grid with the middle square filled in.
	 * 
	 * @param canvas
	 */
	protected void drawGrid(Canvas canvas) {
		Paint gridPaint = new Paint();
		gridPaint.setColor(Color.WHITE);
		gridPaint.setStrokeWidth(4); // the "weight" of the lines
		gridPaint.setStyle(Style.FILL_AND_STROKE);

		// draw horizontal lines for each row
		Piece[][] board = activity.getCurrentGame().getBoard();
		for (int i = 0; i <= board.length; i++) {
			canvas.drawLine(0, i * scale_y, width, i * scale_y, gridPaint);
		}
		// draw vertical lines for each row
		for (int i = 0; i <= board.length; i++) {
			canvas.drawLine(i * scale_x, 0, i * scale_x, height, gridPaint);
		}

		// draw filled middle square
		canvas.drawRect(scale_x * 2, scale_y * 2, scale_x * 3 - 1,
				scale_y * 3 - 1, gridPaint);
	}
}
