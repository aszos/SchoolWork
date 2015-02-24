package com.aszostek.SimpleSimon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class EndScreen implements Screen
{
	final SimpleSimon game;
	OrthographicCamera camera;
	boolean success;
	Music explosionSound;
	Music fanfare;	
	
	public EndScreen(final SimpleSimon gam, boolean success)
	{
		this.game = gam;
		this.success = success;
		explosionSound = Gdx.audio.newMusic(Gdx.files.internal("Explosion.mp3"));
		fanfare = Gdx.audio.newMusic(Gdx.files.internal("fanfare.mp3"));
	}
	
	public void render(float delta) 
	{
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, 1980, 1080);
		camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        if(success)
        {
        	fanfare.play();
        	fanfare.setLooping(false);
        	game.batch.begin();	
            game.font.draw(game.batch, "Success!", game.getCenteredFontx("Success!"), 680);
            game.font.draw(game.batch, "Play Again?" , game.getCenteredFontx("Play Again?"), 580);
            game.batch.end();
        }
        else
        {
        	Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        	explosionSound.play();
        	game.batch.begin();
            game.font.draw(game.batch, "Failure" , game.getCenteredFontx("Failure"), 680);
            game.font.draw(game.batch, "Try Again?" , game.getCenteredFontx("Try Again?"), 580);
            game.batch.end();
        }
        
        if (Gdx.input.justTouched()) 
	    {
        	game.setScreen(new GameScreen(game));
            dispose();
	    }
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		explosionSound.dispose();
		fanfare.dispose();
		
	}

}
