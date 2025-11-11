package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.IntMap;
import java.util.Iterator;

public class TiledMapTileSet implements Iterable {
    private String name;
    private MapProperties properties;
    private IntMap tiles;

    public TiledMapTileSet() {
        this.tiles = new IntMap();
        this.properties = new MapProperties();
    }

    public String getName() {
        return this.name;
    }

    public MapProperties getProperties() {
        return this.properties;
    }

    public TiledMapTile getTile(int v) {
        return (TiledMapTile)this.tiles.get(v);
    }

    @Override
    public Iterator iterator() {
        return this.tiles.values().iterator();
    }

    public void putTile(int v, TiledMapTile tiledMapTile0) {
        this.tiles.put(v, tiledMapTile0);
    }

    public void removeTile(int v) {
        this.tiles.remove(v);
    }

    public void setName(String s) {
        this.name = s;
    }

    public int size() {
        return this.tiles.size;
    }
}

