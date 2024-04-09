package com.mygdx.snakey;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.snakey.config.SnakeyConfig;

public class Map {
    Texture lightTile;
    Texture darkTitle;
    public Map() {
        lightTile = Snakey.get().assetHandler.loadLight;
        darkTitle = Snakey.get().assetHandler.loadDark;
    }

    public void render(SpriteBatch batch) {
        for (int x = 0; x < SnakeyConfig.WINDOW_SIZE; x += SnakeyConfig.TILESIZE) {
            for (int y = 0; y < SnakeyConfig.WINDOW_SIZE; y += SnakeyConfig.TILESIZE) {
                if ((x / SnakeyConfig.TILESIZE + y / SnakeyConfig.TILESIZE) % 2 == 0) {
                    batch.draw(lightTile, x, y, SnakeyConfig.TILESIZE, SnakeyConfig.TILESIZE);
                } else {
                    batch.draw(darkTitle, x, y, SnakeyConfig.TILESIZE, SnakeyConfig.TILESIZE);
                }
            }
        }
    }
}
