package com.mygdx.snakey.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakey.GameState;
import com.mygdx.snakey.Snakey;
import com.mygdx.snakey.config.SnakeyConfig;
import com.mygdx.snakey.screens.MainScreen;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    private Direction currentDirection;
    Sprite head, body, curvedBody, tail;
    Apple apple;
    GameState state;
    ArrayList<Vector2> snake;
    float speed;
    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }
    public Player(Apple apple) {
        snake = new ArrayList<>();
        head = Snakey.get().assetHandler.head_right;
        body = Snakey.get().assetHandler.body_vertical;
        curvedBody = Snakey.get().assetHandler.body_topleft;
        tail = Snakey.get().assetHandler.tail_left;
        this.apple = apple;

        setCurrentDirection(Direction.RIGHT);
        snake.add(new Vector2(SnakeyConfig.TILESIZE * 0f,  Gdx.graphics.getHeight() / 2f));
        snake.add(new Vector2(SnakeyConfig.TILESIZE * 1f,  Gdx.graphics.getHeight() / 2f));
        snake.add(new Vector2(SnakeyConfig.TILESIZE * 2f,  Gdx.graphics.getHeight() / 2f));
        Collections.reverse(snake);
    }

    public void render(SpriteBatch batch) {
        // head
        float rotateHead = 0;
        if (currentDirection == Direction.LEFT) rotateHead = 180;
        if (!(currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT)) rotateHead = currentDirection.ordinal() * 90f + 90f;
        batch.draw(head, snake.get(0).x, snake.get(0).y, SnakeyConfig.TILESIZE / 2f, SnakeyConfig.TILESIZE / 2f, SnakeyConfig.TILESIZE, SnakeyConfig.TILESIZE, 1f, 1f, rotateHead);
        // body
        for (int i = 1; i < snake.size() - 1; i++) {
            Vector2 prev = snake.get(i - 1);
            Vector2 next = snake.get(i + 1);
            Vector2 cur = snake.get(i);

            Sprite sprite = null;
            float rotation = 0;
            if(prev.x == next.x) { // if x is straight
                sprite = body;
            } else if(prev.y == next.y) { // if y is straight
                sprite = body;
                rotation = 90; // correct
            } else { // if x and y are not straight
                sprite = curvedBody;
                if (cur.x > prev.x) {
                    if (next.y > cur.y) rotation = 0;
                    if (next.y < cur.y) rotation = 90; // correct
                }
                if (cur.x < prev.x) {
                    if (next.y > cur.y) rotation = 270; // correct
                    if (next.y < cur.y) rotation = 180; // correct
                }
                if (cur.y < prev.y) {
                    if (next.x > cur.x) rotation = 270; // correct
                    if (next.x < cur.x) rotation = 0;
                }
                if (cur.y > prev.y) {
                    if (next.x > cur.x) rotation = 180; // correct
                    if (next.x < cur.x) rotation = 90; // correct
                }
            }
            batch.draw(sprite, snake.get(i).x, snake.get(i).y, SnakeyConfig.TILESIZE / 2f, SnakeyConfig.TILESIZE / 2f, SnakeyConfig.TILESIZE, SnakeyConfig.TILESIZE, 1f, 1f, rotation);
        }
        // tail part
        float tailRotation = 0;
        Vector2 prevPart = snake.get(snake.size() - 2);
        Vector2 tailPart = snake.get(snake.size() - 1);
        if (prevPart.x == tailPart.x) {
            if (prevPart.y > tailPart.y) tailRotation = 90; // correct
            if (prevPart.y < tailPart.y) tailRotation = 270; // correct
            batch.draw(tail, snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y, SnakeyConfig.TILESIZE / 2f, SnakeyConfig.TILESIZE / 2f, SnakeyConfig.TILESIZE, SnakeyConfig.TILESIZE, 1f, 1f, tailRotation);
        } else if (prevPart.y == tailPart.y) {
            if (prevPart.x > tailPart.x) tailRotation = 0; // correct
            if (prevPart.x < tailPart.x) tailRotation = 180; // correct
            batch.draw(tail, snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y, SnakeyConfig.TILESIZE / 2f, SnakeyConfig.TILESIZE / 2f, SnakeyConfig.TILESIZE, SnakeyConfig.TILESIZE, 1f, 1f, tailRotation);
        }
    }
    public void moveSnake(float xAmount, float yAmount) {
        // add head
        snake.add(0, new Vector2(snake.get(0).x + xAmount, snake.get(0).y + yAmount));
        Vector2 headCoord = snake.get(0);

        // set game state when head collides with window
        if (headCoord.y >= Gdx.graphics.getWidth() || headCoord.y < 0 || headCoord.x >= Gdx.graphics.getHeight() || headCoord.x < 0) {
            state = GameState.GAME_OVER;
        } else {
            state = GameState.START;
        }
        // check if the head collides with apple and if it does exit the method
        if (headCoord.x == apple.appleVector.x && headCoord.y == apple.appleVector.y) {
            apple.randomizeCoords();
            return;
        }
        // if it does not remove the tail
        snake.remove(snake.size() - 1);


        /*little summary so what this does is add the head and removes the tail
        * to give it the effect of moving but if the head collides with an apple
        * then we exit the method before it can remove the tail, so it just adds one on to the body
        * */
    }
    public void movePlayer() {
        switch (currentDirection) {
            case UP:
                moveSnake(0f, SnakeyConfig.TILESIZE);
                break;
            case DOWN:
                moveSnake(0f, -SnakeyConfig.TILESIZE);
                break;
            case LEFT:
                moveSnake(-SnakeyConfig.TILESIZE, 0f);
                break;
            case RIGHT:
                moveSnake(SnakeyConfig.TILESIZE, 0f);
                break;
        }
    }
    public Direction getCurrentDirection() {
        return currentDirection;
    }
    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
    public GameState getPlayerState() {
        return state;
    }
    public void resetSnake() {
        if (state == GameState.GAME_OVER) {
            snake.clear();
            snake.add(new Vector2(SnakeyConfig.TILESIZE * 0f,  Gdx.graphics.getHeight() / 2f));
            snake.add(new Vector2(SnakeyConfig.TILESIZE * 1f,  Gdx.graphics.getHeight() / 2f));
            snake.add(new Vector2(SnakeyConfig.TILESIZE * 2f,  Gdx.graphics.getHeight() / 2f));
            apple.randomizeCoords();
            currentDirection = Direction.RIGHT;
            Collections.reverse(snake);
        }
    }
}
