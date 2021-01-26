package com.libgdx.lebmorda.InputListeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputListenerGlobal extends InputListener {

	VirtualListener controller;

	public InputListenerGlobal(VirtualListener controller) {
		this.controller = controller;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		if (Input.Keys.W == keycode) {
			controller.setMoverArriba(true);
		}
		if ((Input.Keys.S == keycode)) {
			controller.setMoverAbajo(true);
		}
		if ((Input.Keys.A == keycode)) {
			controller.setMoverIzquierda(true);
		}
		if ((Input.Keys.D == keycode)) {
			controller.setMoverDerecha(true);
		}
		if (Input.Keys.UP == keycode) {
			controller.setMoverCamaraArriba(true);
		}
		if ((Input.Keys.DOWN == keycode)) {
			controller.setMoverCamaraAbajo(true);
		}
		if ((Input.Keys.LEFT == keycode)) {
			controller.setMoverCamaraIzquierda(true);
		}
		if ((Input.Keys.RIGHT == keycode)) {
			controller.setMoverCamaraDerecha(true);
		}
		if ((Input.Keys.T == keycode)) {
			controller.setZoomIn(true);
		}
		if ((Input.Keys.Y == keycode)) {
			controller.setZoomOut(true);
		}
		return true;

	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		if (Input.Keys.W == keycode) {
			controller.setMoverArriba(false);
		}
		if ((Input.Keys.S == keycode)) {
			controller.setMoverAbajo(false);
		}
		if ((Input.Keys.A == keycode)) {
			controller.setMoverIzquierda(false);
		}
		if ((Input.Keys.D == keycode)) {
			controller.setMoverDerecha(false);
		}
		if (Input.Keys.UP == keycode) {
			controller.setMoverCamaraArriba(false);
		}
		if ((Input.Keys.DOWN == keycode)) {
			controller.setMoverCamaraAbajo(false);
		}
		if ((Input.Keys.LEFT == keycode)) {
			controller.setMoverCamaraIzquierda(false);
		}
		if ((Input.Keys.RIGHT == keycode)) {
			controller.setMoverCamaraDerecha(false);
		}
		if ((Input.Keys.T == keycode)) {
			controller.setZoomIn(false);
		}
		if ((Input.Keys.Y == keycode)) {
			controller.setZoomOut(false);
		}
		return true;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		controller.setScreenVectorXY(new Vector2(x, y));
		controller.setTocarPantalla(true);
		return true;
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		controller.setScreenVectorXY(new Vector2(x, y));
		controller.setTocarPantalla(true);
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		controller.setTocarPantalla(false);
	}

	@Override
	public boolean scrolled(InputEvent event, float x, float y, int amount) {
		
		System.out.println("ScrolledX: " + x + " ScrolledY: " + y + " Amount: " + amount);

		
		if (amount > 0) {
			controller.setZoomIn(true);
			controller.setZoomOut(false);
		} else {
			controller.setZoomOut(true);
			controller.setZoomIn(false);

		}
		controller.setScroollZoom(true);

		return true;
	}

}
