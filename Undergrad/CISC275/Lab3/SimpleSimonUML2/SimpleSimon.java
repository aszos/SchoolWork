package com.aszostek.SimpleSimon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
User Story
"As a mobile application gamer, I want to play a simple bomb diffusing 
game so that I can learn to play it quickly without it taking away from the 
action."

The mobile application gamer would first open up the application from the home 
screen and wait for it to load. Once it is loaded, the user is presented with a
main menu with an introduction screen. Once the user taps the screen, the experiment
begins. The user hears a distinct blip sound and the bomb timer begins ticking.
The user taps the screen and hears another blip, and learns that touching the screen will
cause a similar blip sound. The user taps again, and hears the blip sound again, but at a 
lower pitch. The user continues to tap the screen until the they notice that the last blip
they heard matches the blip they heard at the start of the game. The user stops tapping the screen
and waits. A few seconds pass and the user is presented with a Success screen and a fanfare plays.
*/

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
