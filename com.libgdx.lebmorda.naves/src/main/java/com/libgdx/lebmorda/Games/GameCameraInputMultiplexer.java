package com.libgdx.lebmorda.Games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.lebmorda.InputListeners.VirtualListener;
import com.libgdx.lebmorda.inputApdaters.InputAdapterKeyboard;
import com.libgdx.lebmorda.inputApdaters.InputAdapterMouse;
import com.libgdx.lebmorda.main.BasicGame;
import com.libgdx.lebmorda.scene2D.actors.SpriteActor;
import com.libgdx.lebmorda.scene2D.shape.SquareActor;
import com.libgdx.lebmorda.scene2D.actors.PlayerActor;
import com.libgdx.lebmorda.scene2D.actors.ShapeActor;
import com.libgdx.lebmorda.utils.Constants;

public class GameCameraInputMultiplexer extends ScreenAdapter {

	private ShapeRenderer shaper;
	private Stage stage;
	private BasicGame game;
	private SpriteActor nave;
	private SquareActor fondo;

	private InputAdapterMouse inputMouse;
	private InputAdapterKeyboard inputKeyboard;
	
	private VirtualListener vController;
	private OrthographicCamera gamecam;
	private Viewport gameport;

	private InputMultiplexer inputMultiplexer;

	public GameCameraInputMultiplexer(BasicGame game) {
		this.game = game;

		gamecam = new OrthographicCamera();
		gameport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);

		// gameport= new ExtendViewport(800, 600, gamecam);
		// gameport= new StretchViewport(800, 600, gamecam);
		// gameport= new ScreenViewport(gamecam);

		stage = new Stage(gameport, this.game.batch);
		nave = new PlayerActor(Constants.PLAYER);
		fondo = new SquareActor();
		shaper = new ShapeRenderer();
	}

	@Override
	public void show() {
		init();
		stage.addActor(fondo.setDimensionActor(200, 200));
		stage.addActor(nave);
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

		vController = new VirtualListener();
		inputMouse = new InputAdapterMouse(vController);
		inputKeyboard = new InputAdapterKeyboard(vController);
		inputMultiplexer = new InputMultiplexer(inputMouse, inputKeyboard);

		// Asignamos los inputMultiplexer
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gamecam.update();

		procesarEntrada();
		shaperGrid();

		stage.act();
		stage.draw();

	}

	/**
	 * Procesar la entrada
	 * 
	 * @param delta
	 */

	private void procesarEntrada() {

		float delta = Gdx.graphics.getDeltaTime();

		// Mouse
		if (vController.isTocarPantalla()) {
			nave.setMouseXY(vController.getMouseVectorXY().x, vController.getMouseVectorXY().y);
		}

		// Keyboard
		if (vController.isMoverArriba()) {
			nave.translateY(Constants.TRASLATE_XY * delta);
			nave.setColor(1, 0, 0, 1);
		}
		if (vController.isMoverAbajo()) {
			nave.translateY(-Constants.TRASLATE_XY * delta);
		}
		if (vController.isMoverIzquierda()) {
			nave.translateX(-Constants.TRASLATE_XY * delta);
		}
		if (vController.isMoverDerecha()) {
			nave.translateX(Constants.TRASLATE_XY * delta);
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
		shaper.begin(ShapeType.Line);
		shaper.line(-1000, 0, 1000, 0);
		shaper.line(0, -1000, 0, 1000);
		for (int i = -1000; i <= 1000; i += 50) {
			shaper.line(-10, i, 10, i);
			shaper.line(i, -10, i, 10);
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
