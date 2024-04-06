package com.mygdx.snakey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.snakey.*;
import com.mygdx.snakey.objects.Apple;
import com.mygdx.snakey.objects.Player;

public class MainScreen implements Screen {

    Map map;

    Player player;

    Apple apple;

    OrthographicCamera camera;

    ScreenViewport viewport;

    public Stage stage;

    public static float width;

    public static float height;

    float movementTimer;

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
        Snakey.get().batch.setProjectionMatrix(camera.combined);
        Snakey.get().batch.begin();
        map.render(Snakey.get().batch);
        player.render(Snakey.get().batch);
        apple.render(Snakey.get().batch);
        Snakey.get().batch.end();
    }

    private void update(float delta) {
        handleInput();
        movementTimer += delta;
        if (movementTimer >= 0.1f) {
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
