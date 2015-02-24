package com.aszostek.SimpleSimon;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;


public class GameScreen implements Screen
{
	final SimpleSimon game;
	Texture gameBackground;
	List<Sound> tapSounds;
	
	Music tickingSound;
	OrthographicCamera camera;
	int tapCounter;
	int numberOfTaps;
	int correctNumberOfTaps;
	int startTime;
	int endTime;
	int timeElapsed;
	
	
	public GameScreen(final SimpleSimon gam)
	{
		this.game = gam;
		gameBackground = new Texture(Gdx.files.internal("bombview.png"));
		
		tapSounds = new ArrayList<Sound>();
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap1.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap2.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap3.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap4.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap5.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap6.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap7.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap8.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap9.mp3")));
		tapSounds.add(Gdx.audio.newSound(Gdx.files.internal("tap/tap10.mp3")));		
		
	    tickingSound = Gdx.audio.newMusic(Gdx.files.internal("ticking10.mp3"));
	    
	    camera = new OrthographicCamera();
        camera.setToOrtho(false, 1980, 1080);
		
        tapCounter = -1;
        correctNumberOfTaps = (int)(Math.random() * 10);   
        startTime = (int)System.currentTimeMillis();
        endTime = (int)(Math.random() * 5) + 5;   
	}
	
	@Override
	public void show() 
	{
		tapSounds.get(correctNumberOfTaps).play();
		tickingSound.play();
	}

	@Override
	public void render(float delta)
	{			     
		game.batch.begin();
	    game.batch.draw(gameBackground, 0, 0);
	    game.batch.end();

		if (Gdx.input.justTouched()) 
	    {
			tapCounter = (tapCounter >= 9)? (0):(tapCounter + 1);
			tapSounds.get(tapCounter).play();
	    }
		
		timeElapsed = (int)(System.currentTimeMillis() - startTime) / 1000;
		
		if(timeElapsed >= endTime)
		{
			tickingSound.stop();
			game.setScreen(new EndScreen(game, (tapCounter == correctNumberOfTaps)));
            dispose();	
		}
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
		gameBackground.dispose();
		tickingSound.dispose();
		
		for(Sound s: tapSounds)
		{
			s.dispose();
		}

	}

}
