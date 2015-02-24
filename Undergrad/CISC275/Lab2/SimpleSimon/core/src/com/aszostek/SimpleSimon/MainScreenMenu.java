package com.aszostek.SimpleSimon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainScreenMenu implements Screen
{
	 final SimpleSimon game;
	 OrthographicCamera camera;
	 Texture titleScreenBackground;
	 
	    public MainScreenMenu(final SimpleSimon gam) {
	        game = gam;
	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, game.X_MAX, game.Y_MAX);
	        titleScreenBackground = new Texture(Gdx.files.internal("title.png"));
	    }
	
	@Override
	 public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        String title = "Simon - The Simple Bomb Difuser";
        String tapToBegin = "Tap to Begin";
        
        int title_x = game.getCenteredFontx(title);
        int tap_x = game.getCenteredFontx(tapToBegin);
        
        game.batch.begin();
        game.batch.draw(titleScreenBackground, 0, 0);
        game.font.draw(game.batch, title , title_x, 680);
        game.font.draw(game.batch, tapToBegin , tap_x, 580);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

	@Override
	public void show() {
		
		
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
		// TODO Auto-generated method stub
		
	}

}
