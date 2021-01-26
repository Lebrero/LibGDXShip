package com.lingdx.lebmorda.patrones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.lebmorda.InputListeners.InputListenerActor;
import com.libgdx.lebmorda.InputListeners.InputListenerGlobal;
import com.libgdx.lebmorda.InputListeners.VirtualListener;
import com.libgdx.lebmorda.main.BasicGame;
import com.libgdx.lebmorda.scene2D.actors.PlayerActor;
import com.libgdx.lebmorda.scene2D.actors.ShapeActor;
import com.libgdx.lebmorda.scene2D.actors.SpriteActor;
import com.libgdx.lebmorda.scene2D.shape.CircleActor;
import com.libgdx.lebmorda.scene2D.shape.SquareActor;
import com.libgdx.lebmorda.utils.Constants;

public class GamePatrones_2 extends ScreenAdapter {

	private ShapeRenderer shaper;

	private Stage stage;
	private BasicGame game;	
	
	//Bounding Box
	private BoundingBox boundCamera;
	
	//Sprite
	private SpriteActor nave;
	
	//Shape
	private SquareActor screenLimit;
	private ShapeActor circle;

	private VirtualListener vController;
	private OrthographicCamera gamecam;
	private Viewport gameport;
	private Vector2 dragOld, dragNew;

	public GamePatrones_2(BasicGame game) {
		this.game = game;

		gamecam = new OrthographicCamera();
		
		gameport = new FitViewport(
				BasicGame.GAME_WIDTH_X, 
				BasicGame.GAME_HEIGHT_Y, gamecam);

		// gameport= new ExtendViewport(800, 600, gamecam);
		// gameport= new StretchViewport(800, 600, gamecam);
		// gameport= new ScreenViewport(gamecam);

		vController = new VirtualListener();
		
		stage = new Stage(gameport, this.game.batch);

		// Actores
		nave = new PlayerActor(Constants.PLAYER);
		
		screenLimit = new SquareActor();
		circle = new CircleActor();

		// Shape
		shaper = new ShapeRenderer();
		
		boundCamera= new BoundingBox();

		init();

	}

	@Override
	public void show() {
		
		//inicializar Circle
		circle.setShapeColor(Color.RED);
		circle.setRadius(10f);
		circle.centerPositionScreen();

		//inicializar screen limit
		screenLimit.setWidth(250f);
		screenLimit.setHeight(250f);
		screenLimit.centerOrigin().centerPositionScreen();
		
		
		stage.addActor(screenLimit);
		stage.addActor(circle);
	//	stage.addActor(nave.getSquare_1());
		stage.addActor(nave);
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
		gamecam.frustum.boundsInFrustum(boundCamera) ;

	}

	/**
	 * Método encargado de inicializar los actores en el Stage
	 */
	private void configActors() {
		nave.centerPositionScreen();
		//nave.getSquare_1().setShapeColor(Color.GREEN);
	}

	/**
	 * Configura entrada
	 */
	private void configInput() {
		
		//Añadimos el listener de la nave
		nave.addListener(new InputListenerActor(vController));
		
		// Añadimos el listener de la Stage
		stage.addListener(new InputListenerGlobal(vController));
		
		// Asignamos los inputMultiplexer
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gamecam.update();
		
		inputListener();
		collision();
		shaperGrid();
		
		stage.act();
		stage.draw();
	}

	private void collision() {
	//	System.out.println("colision Nave: "+nave.getSquare_1().getBounds().overlaps(circle.getBounds()));

	}

	private void inputListener() {
		float delta = Gdx.graphics.getDeltaTime();

		// Mouse
		if (vController.isTocarActor()) {
			nave.setMouseScreenXY(vController.getScreenVectorXY().x, vController.getScreenVectorXY().y);
			nave.getVerticesSprite();
		}

		// Keyboard
		if (vController.isMoverArriba()) {
			nave.translateY(Constants.TRASLATE_XY * delta);
			//gamecam.translate(0, Constants.TRASLATE_CAMERA_XY * delta);
		}
		if (vController.isMoverAbajo()) {
			nave.translateY(-Constants.TRASLATE_XY * delta);
			//gamecam.translate(0, -Constants.TRASLATE_CAMERA_XY * delta);

		}
		if (vController.isMoverIzquierda()) {
			nave.translateX(-Constants.TRASLATE_XY * delta);
		//	gamecam.translate(-Constants.TRASLATE_CAMERA_XY * delta, 0);

		}
		if (vController.isMoverDerecha()) {
			nave.translateX(Constants.TRASLATE_XY * delta);
			//gamecam.translate(Constants.TRASLATE_CAMERA_XY * delta, 0);

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
			if(vController.isScroollZoom()) {
				vController.setZoomIn(false);
				vController.setScroollZoom(false);

			}

		}
		if (vController.isZoomOut()) {
			gamecam.zoom -= Constants.ZOOM_CAMERA_XY * delta;
			nave.setColorActor(Color.RED);
			if(vController.isScroollZoom()) {
				vController.setZoomOut(false);
				vController.setScroollZoom(false);
			}

		}

		if (vController.isTocarPantalla()&&!vController.isTocarActor()) {
		
			
			//gamecam.translate(vController.getScreenVectorXY());
			
			//Touch Settings 
			
			//Solo se llama al principio de tocar cuando tocamos el cursor
			//En ese momento recogemos la posición del ratón y la metemos en ambas variables "dragNew" y "dragOld"		
	        if (Gdx.input.justTouched()){
	        	System.out.println("JusTouched");
	            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
	            dragOld = dragNew;
	        }
	        
	        //Se llama continuamente mientras tengamos el puntero clicado
	        //Se iran recogiendo los valores nuevos del puntero según se va desplazando y metiendo otra vez en 
	        //"Dragnew"
	        
	        //En el momento que no sean iguales (es decir arrastremos el ratón) trasladaremos la camara pero solo
	        //trasladaremos la cantidad desplazada dragOld - dragNew
	        //Finalmente la "vieja" posicion pasará a ser la "nueva"
	        
	        if (Gdx.input.isTouched()){
	        	System.out.println("isTouched");
	            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
	            if (!dragNew.equals(dragOld)){
	                gamecam.translate(dragOld.x - dragNew.x, dragNew.y - dragOld.y);
	                dragOld = dragNew;
	            }
	        }
	        
	       System.out.println("CoordenadaX: "+Gdx.input.getX()+ " CoordenadaY: "+Gdx.input.getY());
	       System.out.println("SizeWidth: "+ Gdx.graphics.getWidth()+ " Sizeheight: "+Gdx.graphics.getHeight());
	       
	       
			
		}
		
		

	}


	/**
	 * Método encargado de crear el grid de fondo
	 */
	private void shaperGrid() {
		shaper.setProjectionMatrix(gamecam.combined);

		// Genero un shaper de tipo línea
		shaper.begin(ShapeType.Line);

		// Defino la línea Horizontal
		shaper.line(BasicGame.CENTER_POSITION_X - 1000, BasicGame.CENTER_POSITION_Y, BasicGame.CENTER_POSITION_X + 1000,
				BasicGame.CENTER_POSITION_Y);
		// Defino la línea Verticar
		shaper.line(BasicGame.CENTER_POSITION_X, BasicGame.CENTER_POSITION_Y - 1000, BasicGame.CENTER_POSITION_X,
				BasicGame.CENTER_POSITION_Y + 1000);

		// Vamos a duplicar la línea
		for (int i = -1000; i <= 1000; i += 20) {

			// HORIZONTAL
			shaper.line(BasicGame.CENTER_POSITION_X - 10, BasicGame.CENTER_POSITION_Y + i,
					BasicGame.CENTER_POSITION_X + 10, BasicGame.CENTER_POSITION_Y + i);

			// VERTICAL
			shaper.line(BasicGame.CENTER_POSITION_X + i, BasicGame.CENTER_POSITION_Y - 10,
					BasicGame.CENTER_POSITION_X + i, BasicGame.CENTER_POSITION_Y + 10);
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

