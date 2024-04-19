package com.mygdx.snakey;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetLoader {
    public AssetManager manager;
    public TextureAtlas atlas;
    public Sprite redapple;
    public Sprite goldenapple;
    public Sprite greenapple;
    public Sprite body_topleft;
    public Sprite body_vertical;
    public Sprite head_right;
    public Sprite tail_left;
    public Sprite speed;
    public Texture loadLight;
    public Texture loadDark;


    public AssetLoader() {
        manager = new AssetManager();
    }

    public void LoadAssets() {
        LoadTiles();
        manager.finishLoading();
        getTiles();
        atlas = new TextureAtlas(Gdx.files.internal("snakegame/snakegame.txt"));
        CreateSprite();
    }

    public void CreateSprite() {
        redapple = atlas.createSprite("red");
        body_topleft = atlas.createSprite("body_topleft");
        body_vertical = atlas.createSprite("body_vertical");
        head_right = atlas.createSprite("head_right");
        tail_left = atlas.createSprite("tail_left");
        speed = atlas.createSprite("speed");
    }

    public void LoadTiles() {
        manager.load("tiles/light.png", Texture.class);
        manager.load("tiles/dark.png", Texture.class);
    }

    public void getTiles() {
        loadLight = manager.get("tiles/light.png", Texture.class);
        loadDark = manager.get("tiles/dark.png", Texture.class);
    }

    public void dispose() {
        manager.dispose();
        atlas.dispose();
    }
}
