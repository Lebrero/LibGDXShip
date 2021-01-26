package com.libgdx.lebmorda.Games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.lebmorda.main.BasicGame;

public class GameScene2D_UI_2 extends ScreenAdapter {

	private static final float BUTTON_SPACE = 10f;
	private static final float BUTTON_ALTO = 50f;
	private static final float ANCHO_BUTTON = 200f;

	private Stage stage;
	private BasicGame game;
	private Skin skin;

	private TextButton _newGame;
	private TextButton _loadGame;
	private TextButton _settings;
	private TextButton _quit;

	private OrthographicCamera gamecam;
	private Viewport gameport;

	public GameScene2D_UI_2(BasicGame game) {
		this.game = game;
		gamecam = new OrthographicCamera();
		gameport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
		stage = new Stage(gameport, this.game.batch);

		// gameport= new ExtendViewport(800, 600, gamecam);
		// gameport= new StretchViewport(800, 600, gamecam);
		// gameport= new ScreenViewport(gamecam);

		skin = new Skin(Gdx.files.internal("uiskin.json"));

		_newGame = new TextButton("New Game", skin);
		_loadGame = new TextButton("Load Game", skin);
		_settings = new TextButton("Settings", skin);
		_quit = new TextButton("Quit", skin);

		Table table = new Table();
		table.setFillParent(true);

		table.add(_newGame).width(ANCHO_BUTTON).height(BUTTON_ALTO).space(BUTTON_SPACE).row();
		table.add(_loadGame).width(ANCHO_BUTTON).height(BUTTON_ALTO).space(BUTTON_SPACE).row();
		table.add(_settings).width(ANCHO_BUTTON).height(BUTTON_ALTO).space(BUTTON_SPACE).row();
		table.add(_quit).width(ANCHO_BUTTON).height(BUTTON_ALTO).space(BUTTON_SPACE).row();

		_newGame.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				_newGame.addAction(Actions.parallel(Actions.sequence(Actions.delay(0f), Actions.moveBy(1200f, 0, 0.4f)),
						Actions.sequence(Actions.delay(0f), Actions.fadeOut(0.2f))));
				_loadGame.addAction(Actions.parallel(Actions.sequence(Actions.delay(0.2f), Actions.moveBy(1200f, 0, 0.4f)),
						Actions.sequence(Actions.delay(0.2f), Actions.fadeOut(0.2f))));
				_settings.addAction(Actions.parallel(Actions.sequence(Actions.delay(0.4f), Actions.moveBy(1200f, 0, 0.4f)),
						Actions.sequence(Actions.delay(0.4f), Actions.fadeOut(0.2f))));
				_quit.addAction(Actions.parallel(Actions.sequence(Actions.delay(0.6f), Actions.moveBy(1200f, 0, 0.4f)),
						Actions.sequence(Actions.delay(0.6f), Actions.fadeOut(0.2f))));

			}
		});

		// table.debug();

		stage.addActor(table);
	}

	@Override
	public void show() {
		init();

	}

	/**
	 * Inicializamos todo lo necesario
	 */
	private void init() {
		configInput();
	}

	/**
	 * Configura entrada
	 */
	private void configInput() {
		// Asignamos los inputMultiplexer
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gamecam.update();

		stage.act();
		stage.draw();

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		gameport.update(width, height);

	}

}
