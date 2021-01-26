package com.libgdx.lebmorda.Games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.lebmorda.main.BasicGame;

public class GameScene2D_UI_1 extends ScreenAdapter {

	private Stage stage;
	private BasicGame game;
	private Skin skin;

	private OrthographicCamera gamecam;
	private Viewport gameport;

	public GameScene2D_UI_1(BasicGame game) {
		this.game = game;
		gamecam = new OrthographicCamera();
		gameport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
		stage = new Stage(gameport, this.game.batch);

		// gameport= new ExtendViewport(800, 600, gamecam);
		// gameport= new StretchViewport(800, 600, gamecam);
		// gameport= new ScreenViewport(gamecam);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));

		Table table = new Table();
		table.setFillParent(true);
		
		//Elementos de tipo Label
		Label labelNombre = new Label("Nombre: ",skin);
		Label labelPuntuacion = new Label("Puntuacion: ",skin);
		Label fieldmensaje  = new Label("", skin);
		
		//Elementos de tipo TextField
		TextField fieldNombre  = new TextField("", skin);
		TextField fieldPuntuacion  = new TextField("500", skin);
		
		//Elementos de tipo TextButton
		TextButton sendPuntuacion = new TextButton("Enviar", skin);

		//Listener
		sendPuntuacion.addListener(new ChangeListener() {		
			@Override
			public void changed(ChangeEvent event, Actor actor) {			
				fieldmensaje.setText("Enviado!");			
			}
		});

		//Operaciones sobre la tabla
		table.add(labelNombre).right();
		table.add(fieldNombre).width(300).height(100);
		table.row();
		table.add(labelPuntuacion);
		table.add(fieldPuntuacion).width(300);
		table.row();
		table.add(sendPuntuacion).colspan(2).fill();
		table.row();
		table.add(fieldmensaje).colspan(2).fill();
		

		//table.debug();
		
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
