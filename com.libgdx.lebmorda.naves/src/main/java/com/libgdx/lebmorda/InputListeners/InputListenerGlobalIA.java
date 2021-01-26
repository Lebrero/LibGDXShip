package com.libgdx.lebmorda.InputListeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputListenerGlobalIA extends InputListener {

	VirtualListener controller;
	public static final char CHAR_A = 'A';
	public static final char CHAR_S = 'S';
	public static final char CHAR_D = 'D';
	public static final char CHAR_W = 'W';

	public InputListenerGlobalIA(VirtualListener controller) {
		this.controller = controller;
	}

	/**
	 * Called when a key is typed. When true is returned, the event is
	 * {@link Event#handle() handled}.
	 * 
	 * @param character May be 0 for key typed events that don't map to a character
	 *                  (ctrl, shift, etc).
	 */
	@Override
	public boolean keyTyped(InputEvent event, char keycode) {

		if (CHAR_A == Character.toUpperCase(keycode)) {
			controller.setGirarIzquierda(true);
		}
		if (CHAR_D == Character.toUpperCase(keycode)) {
			controller.setGirarDerecha(true);
		}
		
		return true;
	}
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		if (Input.Keys.W == keycode) {
			controller.setMoverArriba(true);
		}
		if ((Input.Keys.S == keycode)) {
			controller.setMoverAbajo(true);
		}
		return true;
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {

		if ((Input.Keys.A == keycode)) {
			controller.setGirarIzquierda(false);
		}
		if ((Input.Keys.D == keycode)) {
			controller.setGirarDerecha(false);
		}

		if (Input.Keys.W == keycode) {
			controller.setMoverArriba(false);
		}
		if ((Input.Keys.S == keycode)) {
			controller.setMoverAbajo(false);
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

}
