package com.mygdx.snakey.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakey.Snakey;
import com.mygdx.snakey.config.SnakeyConfig;

public class Apple {
    Player player;
    Powerup powerup;
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
        checkSpawn();
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
    public int calculateChance() {
        return MathUtils.random(0, 3);
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPowerup(Powerup powerup) {
        this.powerup = powerup;
    }
    public Powerup getPowerup() {
        return powerup;
    }
    public void render(SpriteBatch batch) {
        batch.draw(appleSprite, appleVector.x, appleVector.y);
    }
}
