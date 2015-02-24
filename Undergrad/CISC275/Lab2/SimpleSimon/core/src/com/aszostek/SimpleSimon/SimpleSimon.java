package com.aszostek.SimpleSimon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleSimon extends Game
{
    public SpriteBatch batch;
    public BitmapFont font;
	final int X_MAX = 1980;
	final int Y_MAX = 1080; 

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainScreenMenu(this));
    }

	public void render() {
        super.render(); 
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
    
    public int getCenteredFontx(String word)
	{
		return (X_MAX / 2) - ((int) font.getBounds(word).width / 2);
	}
    
}
