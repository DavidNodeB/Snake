package com.mygdx.snakey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.snakey.objects.Apple;
import com.mygdx.snakey.objects.Player;
import com.mygdx.snakey.screens.MainScreen;

public class Snakey extends Game {

	private static Snakey snakey;
	public SpriteBatch batch;
	Map map;
	Player player;

	Apple apple;
	public AssetLoader assetHandler = new AssetLoader();

	@Override
	public void create () {
		assetHandler.LoadAssets();
		batch = new SpriteBatch();
		map = new Map();
		player = new Player();
		apple = new Apple();
		Gdx.graphics.setWindowedMode(800, 800);
		setScreen(new MainScreen(map, player, apple));
	}
	@Override
	public void dispose () {
		batch.dispose();
		assetHandler.dispose();
	}
	public static Snakey get() {
		if (snakey == null) {
			snakey = new Snakey();
		}
		return snakey;
	}
}
