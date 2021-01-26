package com.libgdx.lebmorda.scene2D.actors;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.libgdx.lebmorda.adapters.ActorAdapter;
import com.libgdx.lebmorda.enums.TypeFigure;
import com.libgdx.lebmorda.utils.LoaderManager;

public abstract class SpriteActor extends ActorAdapter {


	protected Texture img;
	protected Sprite spriteImg;
	protected float[] verticesSprite;
	
	protected ArrayList<ShapeActor> shapes;
	private Group group;

	public SpriteActor(String name) {
		init(name);
		shapes = new ArrayList<ShapeActor>();
		group = new Group();
	}

	private void init(String name) {
		loadTexture(name);
		setWidth(img.getWidth());
		setHeight(img.getHeight());
	}

	/**
	 * Cargamos la textura
	 * 
	 * @param name
	 */
	protected void loadTexture(String name) {
		img = LoaderManager.getAssetManager().get(name);
		if (img != null) {
			spriteImg = new Sprite(img);

		}
	}

	/**
	 * Método encargado de pintar al actor todos los atributos que le indiquemos
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {

		this.spriteImg.setPosition(getX(), getY());
		this.spriteImg.setOrigin(getOriginX(), getOriginY());
		this.spriteImg.setScale(getScaleX(), getScaleY());
		this.spriteImg.setRotation(getRotation());
		this.spriteImg.setSize(getWidth(), getHeight());
		this.spriteImg.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);

		this.spriteImg.draw(batch);

		drawShapes();
		drawSprites();
	}

	private void drawSprites() {

	}

	
	/**
	 * Método encargado de dibujar los objetos ShapeActor
	 */
	private void drawShapes() {
		for (Iterator<ShapeActor> iterator = 
				shapes.iterator(); iterator.hasNext();) {
				setPositionCenterXY(iterator.next());
		}
	}

	/**
	 * Método encargado de construir el actor con sus Shapes asociadas
	 * 
	 * @return
	 */
	public Group buildActor() {
		for (Iterator<ShapeActor> iterator = 
				shapes.iterator(); iterator.hasNext();) {
			checkActor(iterator.next(),group);
		}
		group.addActor(this);
		
		return group;
	}



	public float[] getVerticesSprite() {
		float[] vertices = spriteImg.getVertices();
		for (int i = 0; i < vertices.length; i++) {
			System.out.println(vertices[i]);
		}
		return spriteImg.getVertices();
	}
	
	

	public Sprite getSpriteImg() {
		return spriteImg;
	}

	public void setSpriteImg(Sprite spriteImg) {
		this.spriteImg = spriteImg;
	}

	public Group getGrupoActor() {
		return group;
	}

	public ArrayList<ShapeActor> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<ShapeActor> shapes) {
		this.shapes = shapes;
	}

}
