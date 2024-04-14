package com.mygdx.snakey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.snakey.config.SnakeyConfig;
import com.mygdx.snakey.objects.Apple;
import com.mygdx.snakey.objects.Player;
import com.mygdx.snakey.objects.Speed;
import com.mygdx.snakey.screens.MainScreen;

public class Snakey extends Game {

	private static Snakey snakey;
	public SpriteBatch batch;
	Map map;
	Player player;
	Apple apple;
	Speed speed;
	public AssetLoader assetHandler = new AssetLoader();

	@Override
	public void create () {
		assetHandler.LoadAssets();
		batch = new SpriteBatch();
		map = new Map();
		apple = new Apple();
		player = new Player();
		speed = new Speed();
		player.setApple(apple);
		apple.setPlayer(player);
		player.setLightning(speed);
		speed.setPlayer(player);
		speed.setApple(apple);
		Gdx.graphics.setWindowedMode(SnakeyConfig.WINDOW_SIZE, SnakeyConfig.WINDOW_SIZE);
		setScreen(new MainScreen(map, player, apple, speed));
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
