package com.libgdx.lebmorda.IA;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.lebmorda.InputListeners.InputListenerActor;
import com.libgdx.lebmorda.InputListeners.InputListenerGlobalIA;
import com.libgdx.lebmorda.InputListeners.VirtualListener;
import com.libgdx.lebmorda.main.BasicGame;
import com.libgdx.lebmorda.scene2D.actors.PlayerActor;
import com.libgdx.lebmorda.scene2D.actors.SpriteActor;
import com.libgdx.lebmorda.scene2D.shape.SquareActor;
import com.libgdx.lebmorda.utils.Constants;

public class GameBasicIA extends ScreenAdapter {

	private static final int ZERO_POSITION_Y = 0;

	private static final int ZERO_POSITION_X = 0;

	private ShapeRenderer shaper;

	private Stage stage;
	private BasicGame game;
	private SpriteActor nave;
	private SquareActor centro;

	private VirtualListener vController;
	private OrthographicCamera gamecam;
	private Viewport gameport;

	public GameBasicIA(BasicGame game) {
		this.game = game;

		gamecam = new OrthographicCamera();
		gameport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);

		// gameport= new ExtendViewport(800, 600, gamecam);
		// gameport= new StretchViewport(800, 600, gamecam);
		// gameport= new ScreenViewport(gamecam);

		vController = new VirtualListener();
		stage = new Stage(gameport, this.game.batch);
		nave = new PlayerActor(Constants.PLAYER);
		centro = new SquareActor();
		shaper = new ShapeRenderer();
	}

	@Override
	public void show() {
		init();
		stage.addActor(centro.setDimensionActor(Gdx.graphics.getWidth()-20f, Gdx.graphics.getHeight()-20f).centerOrigin());
		stage.addActor(nave.centerOrigin());
	}

	/**
	 * Inicializamos todo lo necesario
	 */
	private void init() {
		configInput();
		configActors();
		configCamera();
	}

	private void configCamera() {
		gamecam.position.x = ZERO_POSITION_X;
		gamecam.position.y = ZERO_POSITION_Y;
	}

	/**
	 * Método encargado de inicializar los actores en el Stage
	 */
	private void configActors() {
		nave.centerPositionScreen();
	}

	/**
	 * Configura entrada
	 */
	private void configInput() {
		// Añadimos los listeners
		stage.addListener(new InputListenerGlobalIA(vController));
		nave.addListener(new InputListenerActor(vController));
		// Asignamos los inputMultiplexer
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gamecam.update();

		inputListener();
		shaperGrid();

		stage.act();
		stage.draw();

	}

	private void inputListener() {
		float delta = Gdx.graphics.getDeltaTime();

		// Mouse
		if (vController.isTocarActor()) {
			nave.setMouseScreenXY(vController.getScreenVectorXY().x, vController.getScreenVectorXY().y);
		}

		// Keyboard
		if (vController.isMoverArriba()) {
			nave.translateY(Constants.TRASLATE_XY * delta);
			gamecam.translate(0, Constants.TRASLATE_CAMERA_XY * delta);
		}
		if (vController.isMoverAbajo()) {
			nave.translateY(-Constants.TRASLATE_XY * delta);
			gamecam.translate(0, -Constants.TRASLATE_CAMERA_XY * delta);

		}
		if (vController.isMoverIzquierda()) {
			nave.translateX(-Constants.TRASLATE_XY * delta);
			gamecam.translate(-Constants.TRASLATE_CAMERA_XY * delta, 0);

		}
		if (vController.isMoverDerecha()) {
			nave.translateX(Constants.TRASLATE_XY * delta);
			gamecam.translate(Constants.TRASLATE_CAMERA_XY * delta, 0);

		}

		// Camera
		if (vController.isMoverCamaraArriba()) {
			gamecam.translate(0, Constants.TRASLATE_CAMERA_XY * delta);

		}
		if (vController.isMoverCamaraAbajo()) {
			gamecam.translate(0, -Constants.TRASLATE_CAMERA_XY * delta);

		}
		if (vController.isMoverCamaraIzquierda()) {
			gamecam.translate(-Constants.TRASLATE_CAMERA_XY * delta, 0);

		}
		if (vController.isMoverCamaraDerecha()) {
			gamecam.translate(Constants.TRASLATE_CAMERA_XY * delta, 0);

		}
		if (vController.isZoomIn()) {
			gamecam.zoom += Constants.ZOOM_CAMERA_XY * delta;
			nave.setColorActor(Color.YELLOW);

		}
		if (vController.isZoomOut()) {
			gamecam.zoom -= Constants.ZOOM_CAMERA_XY * delta;

		}
	}

	/**
	 * Método encargado de crear el grid de fondo
	 */
	private void shaperGrid() {
		shaper.setProjectionMatrix(gamecam.combined);

		float xScreenSize = Gdx.graphics.getWidth();
		float yScreenSize = Gdx.graphics.getHeight();

		// Genero un shaper de tipo línea
		shaper.begin(ShapeType.Line);

		// Defino la línea Horizontal
		shaper.line(-1000, 0, 1000, 0);
		// Defino la línea Verticar
		shaper.line(0, -1000, 0, 1000);

		// Vamos a duplicar la línea
		for (int i = -1000; i <= 1000; i += 50) {
			shaper.line(-1000, i, 1000, i);
			shaper.line(i, -1000, i, 1000);
		}
		shaper.end();
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
