package com.mygdx.snakey.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakey.Snakey;

public class Apple {
    Sprite apple;
    private final int tileSize = 40;
    public Vector2 appleCoords;
    public Random random;
    public int randY;
    public int randX;
    public Apple() {

        apple = Snakey.get().assetHandler.apple;
        random = new Random();
        randomizeCords();
    }

    public void randomizeCords() {
        randX = random.nextInt(Gdx.graphics.getWidth() / tileSize);
        randY = random.nextInt(Gdx.graphics.getHeight() / tileSize);
        appleCoords = new Vector2(randX * tileSize, randY * tileSize);
        apple.setPosition(appleCoords.x, appleCoords.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(apple, appleCoords.x, appleCoords.y);
    }
}
