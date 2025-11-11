package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

public class TiledMapTileSets implements Iterable {
    private Array tilesets;

    public TiledMapTileSets() {
        this.tilesets = new Array();
    }

    public void addTileSet(TiledMapTileSet tiledMapTileSet0) {
        this.tilesets.add(tiledMapTileSet0);
    }

    public TiledMapTile getTile(int v) {
        for(int v1 = this.tilesets.size - 1; v1 >= 0; --v1) {
            TiledMapTile tiledMapTile0 = ((TiledMapTileSet)this.tilesets.get(v1)).getTile(v);
            if(tiledMapTile0 != null) {
                return tiledMapTile0;
            }
        }
        return null;
    }

    public TiledMapTileSet getTileSet(int v) {
        return (TiledMapTileSet)this.tilesets.get(v);
    }

    public TiledMapTileSet getTileSet(String s) {
        for(Object object0: this.tilesets) {
            TiledMapTileSet tiledMapTileSet0 = (TiledMapTileSet)object0;
            if(s.equals(tiledMapTileSet0.getName())) {
                return tiledMapTileSet0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    @Override
    public Iterator iterator() {
        return this.tilesets.iterator();
    }

    public void removeTileSet(int v) {
        this.tilesets.removeIndex(v);
    }

    public void removeTileSet(TiledMapTileSet tiledMapTileSet0) {
        this.tilesets.removeValue(tiledMapTileSet0, true);
    }
}

