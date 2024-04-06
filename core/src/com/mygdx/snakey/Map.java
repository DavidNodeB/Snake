package com.mygdx.snakey;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
    Texture lightTile;
    Texture darkTitle;
    public final int tileSize = 40;
    public final int width = 800; 
    public final int height = 800;
    
    public Map() {
        lightTile = Snakey.get().assetHandler.loadLight;
        darkTitle = Snakey.get().assetHandler.loadDark;
    }

    public void render(SpriteBatch batch) {
        for (int x = 0; x < width; x += tileSize) {
            for (int y = 0; y < height; y += tileSize) {
                if ((x / tileSize + y / tileSize) % 2 == 0) {
                    batch.draw(lightTile, x, y, tileSize, tileSize);
                } else {
                    batch.draw(darkTitle, x, y, tileSize, tileSize);
                }
            }
        }
    }
}
