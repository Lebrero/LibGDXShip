package com.libgdx.lebmorda.scene2D.actors;

import com.badlogic.gdx.graphics.Color;
import com.libgdx.lebmorda.scene2D.shape.CircleActor;
import com.libgdx.lebmorda.scene2D.shape.SquareActor;

public class PlayerActor extends SpriteActor {

	public PlayerActor(String name) {
		super(name);
		setShapes();
	}
	
	public void setShapes() {
		shapes.add((ShapeActor) new SquareActor().
				setPositionActorXY(0, 100).
				setDimensionActor(300, 10).
				setShapeColor(Color.GREEN).
				setNameActor("Cuadrado nave"));	
		
		shapes.add((ShapeActor) new CircleActor().
				setPositionActorXY(0, 0).setRadius(20)
				.setShapeColor(Color.ORANGE)
				.setNameActor("Circulo nave"));
	}
	
/**
 * Atributos propios de una nave
 * 
 * 
 * - Vida
 * - State
 * - Disparo
 * 
 */
	
}
