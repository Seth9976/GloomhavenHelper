package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;

public class TiledMapImageLayer extends MapLayer {
    private TextureRegion region;
    private float x;
    private float y;

    public TiledMapImageLayer(TextureRegion textureRegion0, float f, float f1) {
        this.region = textureRegion0;
        this.x = f;
        this.y = f1;
    }

    public TextureRegion getTextureRegion() {
        return this.region;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setTextureRegion(TextureRegion textureRegion0) {
        this.region = textureRegion0;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }
}

