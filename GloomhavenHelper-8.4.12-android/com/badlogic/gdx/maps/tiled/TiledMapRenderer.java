package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapRenderer;

public interface TiledMapRenderer extends MapRenderer {
    void renderImageLayer(TiledMapImageLayer arg1);

    void renderObject(MapObject arg1);

    void renderObjects(MapLayer arg1);

    void renderTileLayer(TiledMapTileLayer arg1);
}

