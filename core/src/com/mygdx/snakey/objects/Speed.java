package com.mygdx.snakey.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakey.Snakey;
import com.mygdx.snakey.config.SnakeyConfig;

import java.util.Random;

public class Speed {
    Player player;
    Apple apple;
    public Vector2 speedVector;
    public Sprite speedSprite;
    public int xcoord, ycoord;
    private Boolean isVisible;
    public Speed() {
        speedVector = new Vector2();
        speedSprite = Snakey.get().assetHandler.speed;
        isVisible = false;
        randomizeSpeed();
    }
    public void randomizeSpeed() {
            if (apple != null && getApple().calcualteChance() == 3) {
                Random random = new Random();
                xcoord = random.nextInt(Gdx.graphics.getWidth() / SnakeyConfig.TILESIZE);
                ycoord = random.nextInt(Gdx.graphics.getHeight() / SnakeyConfig.TILESIZE);
                speedVector.x = xcoord * SnakeyConfig.TILESIZE;
                speedVector.y = ycoord * SnakeyConfig.TILESIZE;
                checkSpawn();
                isVisible = true;
            }
    }
    public void checkSpawn() {
        if (player != null) {
            for (int i = 1; i < getPlayer().snake.size(); i++) {
                if (getPlayer().snake.get(i).x == speedVector.x && getPlayer().snake.get(i).y == speedVector.y) {
                    randomizeSpeed();
                    break;
                }
            }
        }  else if (apple != null) {
            if (getApple().appleVector.x == speedVector.x && getApple().appleVector.y == speedVector.y) {
                randomizeSpeed();
            }
        }
    }
    public void render(SpriteBatch batch) {
        if (isVisible != null && isVisible) {
            batch.draw(speedSprite, speedVector.x, speedVector.y);
        }
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
    public void setApple(Apple apple) {
        this.apple = apple;
    }
    public Apple getApple() {
        return apple;
    }
}
