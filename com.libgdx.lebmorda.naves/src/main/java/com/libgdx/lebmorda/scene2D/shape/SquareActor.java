package com.libgdx.lebmorda.scene2D.shape;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.libgdx.lebmorda.enums.TypeFigure;
import com.libgdx.lebmorda.scene2D.actors.ShapeActor;

public class SquareActor extends ShapeActor {
	
	protected Rectangle boundsRectangle;
	
	public SquareActor() {		
		initShape();
	}
	private void initShape() {
		initShapeRenderer();		
	}
	@Override
	protected void initFigure() {
		
		
		shaper.begin(ShapeType.Line);
		shaper.rect(getX(), 
				getY(), 
				getOriginX(), 
				getOriginY(), 
				getWidth(), 
				getHeight(), 
				getScaleX(), 
				getScaleY(),
				getRotation(), 
				getShapeColor(), 
				getShapeColor(), 
				getShapeColor(), 
				getShapeColor());
		shaper.end();		

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		
			initShaper(batch);
			initFigure();
			
		batch.begin();
		
		this.boundsRectangle.setX(getX());
		this.boundsRectangle.setY(getY());
		this.boundsRectangle.setWidth(getWidth());
		this.boundsRectangle.setHeight(getHeight());
		

	}
	@Override
	protected void initShapeRenderer() {
		this.boundsRectangle = new Rectangle();	
	}
	
	@Override
	public Rectangle getShapeBounds() {
		return this.boundsRectangle;
		
	}
	@Override
	public TypeFigure getType() {
		return TypeFigure.RECTANGLE;
	}
	
	
	public Rectangle getBounds() {
		return boundsRectangle;
	}

}
