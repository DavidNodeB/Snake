package com.mygdx.snakey.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakey.Snakey;
import com.mygdx.snakey.config.SnakeyConfig;

public class Apple {
    public Vector2 appleVector;
    public Sprite appleSprite;
    public int xcoord, ycoord;
    public Apple() {
        appleVector = new Vector2();
        appleSprite = Snakey.get().assetHandler.apple;
        randomizeCoords();
    }

    public void randomizeCoords() {
        Random random = new Random();
        xcoord = random.nextInt(Gdx.graphics.getWidth() / SnakeyConfig.TILESIZE);
        ycoord = random.nextInt(Gdx.graphics.getHeight() / SnakeyConfig.TILESIZE);
        appleVector.x = xcoord * SnakeyConfig.TILESIZE;
        appleVector.y = ycoord * SnakeyConfig.TILESIZE;
    }


    public void render(SpriteBatch batch) {
        batch.draw(appleSprite, appleVector.x, appleVector.y);
    }
}
