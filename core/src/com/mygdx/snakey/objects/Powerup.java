package com.mygdx.snakey.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakey.Snakey;
import com.mygdx.snakey.config.SnakeyConfig;

import java.util.Random;

public class Powerup {
    Apple apple;
    Player player;
    public Vector2 speedVector;
    public Sprite speedSprite;
    public int xcoord, ycoord;
    private Boolean isVisible;
    private Boolean isEaten;
    public Powerup() {
        speedVector = new Vector2();
        speedSprite = Snakey.get().assetHandler.speed;
        isVisible = false;
        isEaten = false;
        randomizePowerCoords();
        checkChance();
    }
    public void randomizePowerCoords() {
        Random random = new Random();
        xcoord = random.nextInt(Gdx.graphics.getWidth() / SnakeyConfig.TILESIZE);
        ycoord = random.nextInt(Gdx.graphics.getHeight() / SnakeyConfig.TILESIZE);
        speedVector.x = xcoord * SnakeyConfig.TILESIZE;
        speedVector.y = ycoord * SnakeyConfig.TILESIZE;
        checkSpawn();
    }
    // just checks if the powerup is spawned on either player or apple and if it is then randomize coords again
    public void checkSpawn() {
        if (player != null) {
            for (int i = 1; i < getPlayer().snake.size(); i++) {
                if (getPlayer().snake.get(i).x == speedVector.x && getPlayer().snake.get(i).y == speedVector.y) {
                    randomizePowerCoords();
                }
            }
        }  else if (apple != null) {
            if (getApple().appleVector.x == speedVector.x && getApple().appleVector.y == speedVector.y) {
                randomizePowerCoords();
            }
        }
    }
    public void checkChance() {
        if (apple != null) {
            if (getApple().calculateChance() == 3 && !getIsEaten()) {
                setIsVisible(true);
            }
        }
    }
    public void render(SpriteBatch batch) {
        if (getIsVisible() && !getIsEaten()) batch.draw(speedSprite, speedVector.x, speedVector.y);
    }
    public void setApple(Apple apple) {
        this.apple = apple;
    }
    public Apple getApple() {
        return apple;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
    public Boolean getIsVisible() {
        return isVisible;
    }
    public void setIsEaten(Boolean isEaten) {
        this.isEaten = isEaten;
    }
    public Boolean getIsEaten() {
        return isEaten;
    }
}
