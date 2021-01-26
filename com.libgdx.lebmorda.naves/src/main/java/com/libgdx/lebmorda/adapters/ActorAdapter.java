package com.libgdx.lebmorda.adapters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.libgdx.lebmorda.enums.TypeFigure;
import com.libgdx.lebmorda.scene2D.actors.ShapeActor;

public class ActorAdapter extends Actor {

	private float radius;
	private Color color;
	
	
	private float  xPosition;
	private float  yPosition;

	/**
	 * Establece la posición del actor en el centro de la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter centerPositionScreen() {
		setX(Gdx.graphics.getWidth() / 2 - getWidth() / 2);
		setY(Gdx.graphics.getHeight() / 2 - getHeight() / 2);
		return this;
	}
	

	/**
	 * Centra el pivote del actor
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter centerOrigin() { 
		setX(-getWidth() / 2);
		setY(-getHeight() / 2);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter setPositionActor(float x, float y) {
		setPosition(x, y);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla respecto a la posicion del
	 * mouse
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter setPositionActorMouse() {
		float x = Gdx.input.getX() - (getWidth() / 2);
		float y = Gdx.graphics.getHeight() - Gdx.input.getY() - (getHeight() / 2);
		setPosition(x, y);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter setMouseXY(float x, float y) {

		setPosition(x - (getWidth() / 2), Gdx.graphics.getHeight() - y - (getHeight() / 2));

		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter setMouseScreenXY(float x, float y) {
		setPosition(x - (getWidth() / 2), y - getHeight() / 2);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter translateX(float x) {
		setPosition(getX() + x, getY());
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter translateY(float y) {
		setPosition(getX(), getY() + y);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter translateXY(float x, float y) {
		setPosition(getX() + x, getY() + y);
		return this;
	}

	/**
	 * Establece las dimensiones
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter setDimensionActor(float width, float height) {
		setWidth(width);
		setHeight(height);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter setRotationActor(float rotate) {
		setRotation(rotate);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter rotateActor(float value) {
		rotateBy(value);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter setColorActor(Color color) {
		setColor(color);
		return this;
	}

	/**
	 * Establece la posición del actor en la pantalla
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter setDebugRendered(ShapeRenderer shape) {
		setDebugRendered(shape);
		setDebug(true);
		return this;
	}

	/**
	 * Establece el origen del pivot del actor. Solo afecta a las rotation y Scale
	 * 
	 * @return GameBuilder
	 */
	public ActorAdapter centerOriginXY() {
		setOrigin(getWidth() / 2, getHeight() / 2);
		return this;
	}

	public float getRadius() {
		return radius;
	}

	public ActorAdapter setRadius(float radius) {
		this.radius = radius;
		return this;
	}

	public Color getShapeColor() {
		if (this.color != null) {
			return color;
		}else {
			return Color.RED;
		}
	}

	public ActorAdapter setShapeColor(Color color) {
		this.color = color;
		return this;
	}
	
	
	public ActorAdapter setPositionActorXY(float x,float y) {		
		this.xPosition = x;
		this.yPosition =y;
		return this;
		
	}
	
	public ActorAdapter setPositionShapeX(float x) {		
		this.xPosition = x;
		return this;
	}
	
	public ActorAdapter setPositionShapeY(float y) {		
		this.yPosition = y;
		return this;
		
	}
	
	public float getPositionShapeX() {	
		return xPosition;		
	}
	
	public float getPositionShapeY() {	
		return yPosition;		
	}
	
	public ActorAdapter setNameActor(String name) {	
		setName(name);
		return this;	
	}
	
	
	public void setPositionCenterXY(ShapeActor shapeActor) {
		
		float shx = getX() + (getWidth() / 2);
		float shy = getY() + (getHeight() / 2);
		
		if (shapeActor.getType() == TypeFigure.RECTANGLE) {
			shapeActor.setX((shx- shapeActor.getWidth() / 2) 
					+ shapeActor.getPositionShapeX());
			shapeActor.setY((shy - shapeActor.getHeight() / 2) 
					+ shapeActor.getPositionShapeY());
			
		} else if (shapeActor.getType() == TypeFigure.CIRCLE) {
			shapeActor.setX((getX() 
					+ (getWidth() / 2)) 
					+ shapeActor.getPositionShapeX());
			shapeActor.setY((getY() 
					+ (getHeight() / 2)) 
					+ shapeActor.getPositionShapeY());			
		}

	}
	
	public void checkActor(ShapeActor shapeActor, Group group) {

		if (shapeActor.getType().equals(TypeFigure.RECTANGLE)) {
			if (shapeActor.getWidth() == 0) {
				shapeActor.setWidth(getWidth());
			}
			if (shapeActor.getHeight() == 0) {
				shapeActor.setHeight(getHeight());
			}

		} else if (shapeActor.getType().equals(TypeFigure.CIRCLE)) {
			if (shapeActor.getRadius() == 0) {
				shapeActor.setRadius(getWidth() / 2);
			}
		}
		group.addActor(shapeActor);
	}

}
