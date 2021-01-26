package com.libgdx.lebmorda.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.libgdx.lebmorda.screens.TitleScreen;
import com.libgdx.lebmorda.utils.LoaderManager;

public class BasicGame extends Game {

	public static float CENTER_POSITION_Y;
	public static float CENTER_POSITION_X;
	
	public static float GAME_WIDTH_X;
	public static float GAME_HEIGHT_Y;

	public SpriteBatch batch;
	public Skin gameSkin;

	@Override
	public void create() {
		init();

		batch = new SpriteBatch();
		this.setScreen(new TitleScreen(this));
	}

	private void init() {
		
		GAME_HEIGHT_Y = Gdx.graphics.getHeight();
		GAME_WIDTH_X = Gdx.graphics.getWidth();
		
		CENTER_POSITION_Y = Gdx.graphics.getHeight() / 2;
		CENTER_POSITION_X = Gdx.graphics.getWidth() / 2;
		
		LoaderManager.load();
		gameSkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
	}

	@Override
	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		screen.dispose();
	}

}
