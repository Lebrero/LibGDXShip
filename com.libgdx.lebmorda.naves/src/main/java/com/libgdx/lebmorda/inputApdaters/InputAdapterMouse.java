package com.libgdx.lebmorda.inputApdaters;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.libgdx.lebmorda.InputListeners.VirtualListener;

public class InputAdapterMouse extends InputAdapter {

	VirtualListener controller;

	public InputAdapterMouse(VirtualListener controller) {
		this.controller = controller;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		controller.setTocarPantalla(true);
		controller.setMouseVectorXY(new Vector2(screenX, screenY));
		return true;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer,int button) {
		controller.setTocarPantalla(true);
		controller.setMouseVectorXY(new Vector2(screenX, screenY));
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer,int button) {
		controller.setTocarPantalla(false);
		return true;
	}
	
	
	

}
