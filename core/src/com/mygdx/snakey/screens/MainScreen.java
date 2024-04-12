package com.mygdx.snakey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.snakey.*;
import com.mygdx.snakey.config.SnakeyConfig;
import com.mygdx.snakey.objects.Apple;
import com.mygdx.snakey.objects.Player;

public class MainScreen implements Screen {

    Map map;

    Player player;

    Apple apple;

    GameState state;

    OrthographicCamera camera;

    ScreenViewport viewport;

    SpriteBatch batch;

    public Stage stage;

    public static float width;

    public static float height;

    float movementTimer;
    public static float speed = 0.1f;

    public MainScreen(Map map, Player player, Apple apple) {

        this.map = map;

        this.player = player;

        this.apple = apple;

        width = Gdx.graphics.getWidth();

        height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(width, height);

        viewport = new ScreenViewport(camera);

        camera.setToOrtho(false, width, height);

        stage = new Stage();

        stage.setViewport(viewport);

        camera.update();

        batch = Snakey.get().batch;

        state = GameState.START;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.graphics.getDeltaTime();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        map.render(batch);

        player.render(batch);

        apple.render(batch);

        gameOver();

        batch.end();
    }

    private void update(float delta) {
        handleInput();
        movementTimer += delta;
        if (movementTimer >= speed) {
            player.movePlayer();
            movementTimer = 0f;
        }
    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width / 2f, height / 2f, 0);
        viewport.update(width, height);
    }
    private void handleInput() {
        Player.Direction curDir = player.getCurrentDirection();
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W) && curDir != Player.Direction.DOWN) {
            player.setCurrentDirection(Player.Direction.UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D) && curDir != Player.Direction.LEFT) {
            player.setCurrentDirection(Player.Direction.RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S) && curDir != Player.Direction.UP) {
            player.setCurrentDirection(Player.Direction.DOWN);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)  && curDir != Player.Direction.RIGHT) {
            player.setCurrentDirection(Player.Direction.LEFT);
        }
    }
    public void gameOver() {
        if (player.getPlayerState() == GameState.GAME_OVER) {
            player.resetSnake();
        }
    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
