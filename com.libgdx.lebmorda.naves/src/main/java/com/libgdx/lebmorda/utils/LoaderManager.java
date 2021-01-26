package com.libgdx.lebmorda.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class LoaderManager {

	static AssetManager assetManager = new AssetManager();

	public static void load() {
        assetManager.load("img/texture_0.png", Texture.class);     
        assetManager.load("img/texture_1.png", Texture.class);  
        assetManager.finishLoading();
	}
	public static AssetManager getAssetManager() {
		return assetManager;
	}
}
