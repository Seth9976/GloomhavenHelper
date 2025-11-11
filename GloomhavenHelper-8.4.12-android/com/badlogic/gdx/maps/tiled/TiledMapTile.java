package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;

public interface TiledMapTile {
    public static enum BlendMode {
        NONE,
        ALPHA;

    }

    BlendMode getBlendMode();

    int getId();

    MapObjects getObjects();

    float getOffsetX();

    float getOffsetY();

    MapProperties getProperties();

    TextureRegion getTextureRegion();

    void setBlendMode(BlendMode arg1);

    void setId(int arg1);

    void setOffsetX(float arg1);

    void setOffsetY(float arg1);

    void setTextureRegion(TextureRegion arg1);
}

