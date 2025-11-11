package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class TiledMap extends Map {
    private Array ownedResources;
    private TiledMapTileSets tilesets;

    public TiledMap() {
        this.tilesets = new TiledMapTileSets();
    }

    @Override  // com.badlogic.gdx.maps.Map
    public void dispose() {
        Array array0 = this.ownedResources;
        if(array0 != null) {
            for(Object object0: array0) {
                ((Disposable)object0).dispose();
            }
        }
    }

    public TiledMapTileSets getTileSets() {
        return this.tilesets;
    }

    public void setOwnedResources(Array array0) {
        this.ownedResources = array0;
    }
}

