package com.libgdx.lebmorda.launcher;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.libgdx.lebmorda.main.BasicGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();		
		config.width=800;
		config.height=800;
		new LwjglApplication(new BasicGame(), config);
	}
}
