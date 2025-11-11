package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.MapLayer;

public class TiledMapTileLayer extends MapLayer {
    public static class Cell {
        public static final int ROTATE_0 = 0;
        public static final int ROTATE_180 = 2;
        public static final int ROTATE_270 = 3;
        public static final int ROTATE_90 = 1;
        private boolean flipHorizontally;
        private boolean flipVertically;
        private int rotation;
        private TiledMapTile tile;

        public boolean getFlipHorizontally() {
            return this.flipHorizontally;
        }

        public boolean getFlipVertically() {
            return this.flipVertically;
        }

        public int getRotation() {
            return this.rotation;
        }

        public TiledMapTile getTile() {
            return this.tile;
        }

        public Cell setFlipHorizontally(boolean z) {
            this.flipHorizontally = z;
            return this;
        }

        public Cell setFlipVertically(boolean z) {
            this.flipVertically = z;
            return this;
        }

        public Cell setRotation(int v) {
            this.rotation = v;
            return this;
        }

        public Cell setTile(TiledMapTile tiledMapTile0) {
            this.tile = tiledMapTile0;
            return this;
        }
    }

    private Cell[][] cells;
    private int height;
    private int tileHeight;
    private int tileWidth;
    private int width;

    public TiledMapTileLayer(int v, int v1, int v2, int v3) {
        this.width = v;
        this.height = v1;
        this.tileWidth = v2;
        this.tileHeight = v3;
        this.cells = new Cell[v][v1];
    }

    public Cell getCell(int v, int v1) {
        return v < 0 || v >= this.width || v1 < 0 || v1 >= this.height ? null : this.cells[v][v1];
    }

    public int getHeight() {
        return this.height;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getWidth() {
        return this.width;
    }

    public void setCell(int v, int v1, Cell tiledMapTileLayer$Cell0) {
        if(v >= 0 && v < this.width && v1 >= 0 && v1 < this.height) {
            this.cells[v][v1] = tiledMapTileLayer$Cell0;
        }
    }
}

