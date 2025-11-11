package com.badlogic.gdx.maps.tiled.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

public class TiledMapTileMapObject extends TextureMapObject {
    private boolean flipHorizontally;
    private boolean flipVertically;
    private TiledMapTile tile;

    public TiledMapTileMapObject(TiledMapTile tiledMapTile0, boolean z, boolean z1) {
        this.flipHorizontally = z;
        this.flipVertically = z1;
        this.tile = tiledMapTile0;
        TextureRegion textureRegion0 = new TextureRegion(tiledMapTile0.getTextureRegion());
        textureRegion0.flip(z, z1);
        this.setTextureRegion(textureRegion0);
    }

    public TiledMapTile getTile() {
        return this.tile;
    }

    public boolean isFlipHorizontally() {
        return this.flipHorizontally;
    }

    public boolean isFlipVertically() {
        return this.flipVertically;
    }

    public void setFlipHorizontally(boolean z) {
        this.flipHorizontally = z;
    }

    public void setFlipVertically(boolean z) {
        this.flipVertically = z;
    }

    public void setTile(TiledMapTile tiledMapTile0) {
        this.tile = tiledMapTile0;
    }
}

