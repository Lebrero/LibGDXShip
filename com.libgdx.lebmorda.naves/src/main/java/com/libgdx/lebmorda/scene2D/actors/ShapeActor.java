package com.libgdx.lebmorda.scene2D.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Shape2D;
import com.libgdx.lebmorda.adapters.ActorAdapter;
import com.libgdx.lebmorda.enums.TypeFigure;
import com.libgdx.lebmorda.utils.Constants;

public abstract class ShapeActor extends ActorAdapter {
	
	protected ShapeRenderer shaper;
	private boolean blocked;
	
	public ShapeActor() {
		init();
	}

	private void init() {
		this.shaper = new ShapeRenderer();
	}
	
	public abstract Shape2D getShapeBounds();
	public abstract TypeFigure getType();
	
	protected abstract void initFigure();
	protected abstract void initShapeRenderer();


	protected void initShaper(Batch batch) {
	
		this.setColor(getShapeColor());

		if (!isBlocked()) {
			this.shaper.setProjectionMatrix(batch.getProjectionMatrix());
			this.shaper.setTransformMatrix((batch.getTransformMatrix()));
		}		
		
		this.setVisible(Constants.VISIBLE_DEBUG);		
	}
	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}