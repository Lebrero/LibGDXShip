package com.libgdx.lebmorda.scene2D.shape;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.libgdx.lebmorda.enums.TypeFigure;
import com.libgdx.lebmorda.scene2D.actors.ShapeActor;

public class CircleActor extends ShapeActor {
	
	protected Circle boundsCircle;

	public CircleActor() {		
		initShape();
	}

	private void initShape() {
		initShapeRenderer();	

	}

	@Override
	protected void initFigure() {		
		shaper.begin(ShapeType.Line);
		shaper.setColor(getShapeColor());
		shaper.circle(getX(), getY(), getRadius());		
		shaper.end();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {

		batch.end();

			initShaper(batch);
			initFigure();

		batch.begin();
		
		this.boundsCircle.setX(getX());
		this.boundsCircle.setY(getY());	
		this.boundsCircle.setRadius(getRadius());
	}

	@Override
	protected void initShapeRenderer() {
		boundsCircle = new Circle();

	}

	@Override
	public Circle getShapeBounds() {
		return this.boundsCircle;
	}
	
	@Override
	public TypeFigure getType() {
		return TypeFigure.CIRCLE;
	}
	
	public Circle getBounds() {
		return boundsCircle;
	}

}
