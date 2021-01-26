package com.libgdx.lebmorda.InputListeners;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputListenerActor extends InputListener {

	VirtualListener controller;

	public InputListenerActor(VirtualListener controller) {
		this.controller = controller;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		controller.setMouseVectorXY(new Vector2(x, y));
		controller.setTocarActor(true);
		return true;
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		controller.setMouseVectorXY(new Vector2(x, y));
		controller.setTocarPantalla(true);
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		controller.setTocarActor(false);
	}

}
