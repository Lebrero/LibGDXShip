package com.libgdx.lebmorda.IA;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
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

public class GameBasicFollowIA extends ScreenAdapter {

	private static final int VELOCIDAD_GIRO = 100;
	private static final int ZERO_POSITION_Y = 0;
	private static final int ZERO_POSITION_X = 0;

	private ShapeRenderer shaper;

	private World world;
	private Box2DDebugRenderer debugRenderer;

	// Varaibles estáticas propias del World
	static final float STEP_TIME = 1f / 60f;
	static final int VELOCITY_ITERATIONS = 6;
	static final int POSITION_ITERATIONS = 2;
	static final float SCALE = 0.64f;
	static final int COUNT = 20;

	MoveToAction moverDelante = new MoveToAction();

	private float accumulator = 0;
	// Body genérico
	private Body naveBody;

	private Stage stage;
	private BasicGame game;
	private SpriteActor nave;
	private SquareActor centro;

	private VirtualListener vController;
	private OrthographicCamera gamecam;
	private Viewport gameport;

	public GameBasicFollowIA(BasicGame game) {
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
		stage.addActor(centro.setDimensionActor(250, 250).centerOrigin());
		stage.addActor(nave.setDimensionActor(nave.getWidth(), nave.getHeight()).centerOrigin());

	}

	/**
	 * Inicializamos todo lo necesario
	 */
	private void init() {
		configInput();
		configActors();
		configCamera();
		configWorld();
	}

	private void configWorld() {
		// Inicializamos Box2D
		Box2D.init();
		// Instanciamos el debug
		debugRenderer = new Box2DDebugRenderer();
		// Insanciamos el World
		world = new World(new Vector2(0, 0), true);

		createBody("nave", 0, 0, 0);
	}

	/**
	 * Método que sirve para crear Bodies
	 * 
	 * @param name     Nombre del body
	 * @param x        posición del body
	 * @param y        posición del body
	 * @param rotation rotación del body
	 * @return objeto de tipo Body
	 */
	private Body createBody(String name, float x, float y, float rotation) {
		createBodyFactory(name);
		naveBody.setTransform(x, y, rotation);
		return naveBody;
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
		nave.centerOriginXY();
	}

	private BodyDef createBodyDef() {
		BodyDef def = new BodyDef();
		def.position.set(0, 0);
		def.type = BodyDef.BodyType.DynamicBody;
		return def;
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

	private Body createBodyFactory(String name) {
		// Creamos el bodyDef
		BodyDef bodyDef = createBodyDef();
		naveBody = world.createBody(bodyDef);

		PolygonShape square = new PolygonShape();

		float naveWidth = nave.getWidth() / 2;
		float naveHeight = nave.getHeight() / 2;

		square.setAsBox(nave.getWidth() / 2, nave.getHeight() / 2);
		// Creamos el fixtureDef
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = square;
		fixtureDef.density = 1f;
		// Creamos el fixture
		naveBody.createFixture(fixtureDef);

		return naveBody;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Elemento de debug Box2D
		gamecam.update();

		inputListener(delta);
		stepWorld(delta);
		shaperGrid();

		stage.act();
		stage.draw();

		debugRenderer.render(world, gamecam.combined);

	}

	/**
	 * Método stepWorld
	 * 
	 * @param delta
	 */
	private void stepWorld(float delta) {
		accumulator += Math.min(delta, 0.25f);
		if (accumulator >= STEP_TIME) {
			accumulator -= STEP_TIME;
			world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}

	private void inputListener(float delta) {
		// Keyboard
		if (vController.isMoverArriba()) {
			nave.translateY(Constants.TRASLATE_XY * delta);
			gamecam.translate(0, Constants.TRASLATE_CAMERA_XY * delta);
		}
		if (vController.isMoverAbajo()) {
			nave.translateY(-Constants.TRASLATE_XY * delta);
			gamecam.translate(0, -Constants.TRASLATE_CAMERA_XY * delta);

		}

		// Keyboard
		if (vController.isGirarDerecha()) {
			nave.rotateActor(-VELOCIDAD_GIRO * delta);
			moverDelante.setX(nave.getX() + 10);

		}
		if (vController.isGirarIzquierda()) {
			nave.rotateActor(VELOCIDAD_GIRO * delta);

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
		world.dispose();
		shaper.dispose();
		debugRenderer.dispose();
	}

	@Override
	public void resize(int width, int height) {
		gameport.update(width, height);

	}

}
