package com.mygdx.snakey.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakey.Snakey;
import com.mygdx.snakey.config.SnakeyConfig;

public class Apple {
    Player player;
    public Vector2 appleVector;
    public Sprite appleSprite;
    public int xcoord, ycoord;
    private Random randomChance;
    private int randomInt;
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
        checkSpawn();
        calcualteChance();
    }
    public void checkSpawn() {
        if (player != null) {
            for (int i = 1; i < getPlayer().snake.size(); i++) {
                if (getPlayer().snake.get(i).x == appleVector.x && getPlayer().snake.get(i).y == appleVector.y) {
                    randomizeCoords();
                    break;
                }
            }
        }
    }
    public int calcualteChance() {
        randomChance = new Random();
        int max = 3;
        int min = 1;
        randomInt = randomChance.nextInt((max - min) + 1) + min;
        System.out.println(randomInt);
        return randomInt;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
    public void render(SpriteBatch batch) {
        batch.draw(appleSprite, appleVector.x, appleVector.y);
    }
}
