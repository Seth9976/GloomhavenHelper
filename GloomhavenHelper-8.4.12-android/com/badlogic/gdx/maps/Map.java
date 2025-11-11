package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Disposable;

public class Map implements Disposable {
    private MapLayers layers;
    private MapProperties properties;

    public Map() {
        this.layers = new MapLayers();
        this.properties = new MapProperties();
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    public MapLayers getLayers() {
        return this.layers;
    }

    public MapProperties getProperties() {
        return this.properties;
    }
}

