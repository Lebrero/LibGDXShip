package com.libgdx.lebmorda.inputApdaters;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.libgdx.lebmorda.InputListeners.VirtualListener;

public class InputAdapterKeyboard extends InputAdapter {

	VirtualListener controller;

	public InputAdapterKeyboard(VirtualListener controller) {
		this.controller = controller;
	}

	@Override
	public boolean keyDown(int keycode) {
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
	public boolean keyUp(int keycode) {
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

}
