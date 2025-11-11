package com.badlogic.gdx.maps.tiled.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile.BlendMode;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

public class StaticTiledMapTile implements TiledMapTile {
    private BlendMode blendMode;
    private int id;
    private MapObjects objects;
    private float offsetX;
    private float offsetY;
    private MapProperties properties;
    private TextureRegion textureRegion;

    public StaticTiledMapTile(TextureRegion textureRegion0) {
        this.blendMode = BlendMode.ALPHA;
        this.textureRegion = textureRegion0;
    }

    public StaticTiledMapTile(StaticTiledMapTile staticTiledMapTile0) {
        this.blendMode = BlendMode.ALPHA;
        if(staticTiledMapTile0.properties != null) {
            this.getProperties().putAll(staticTiledMapTile0.properties);
        }
        this.objects = staticTiledMapTile0.objects;
        this.textureRegion = staticTiledMapTile0.textureRegion;
        this.id = staticTiledMapTile0.id;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public BlendMode getBlendMode() {
        return this.blendMode;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public int getId() {
        return this.id;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public MapObjects getObjects() {
        if(this.objects == null) {
            this.objects = new MapObjects();
        }
        return this.objects;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public float getOffsetX() {
        return this.offsetX;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public float getOffsetY() {
        return this.offsetY;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public MapProperties getProperties() {
        if(this.properties == null) {
            this.properties = new MapProperties();
        }
        return this.properties;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public TextureRegion getTextureRegion() {
        return this.textureRegion;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setBlendMode(BlendMode tiledMapTile$BlendMode0) {
        this.blendMode = tiledMapTile$BlendMode0;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setId(int v) {
        this.id = v;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setOffsetX(float f) {
        this.offsetX = f;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setOffsetY(float f) {
        this.offsetY = f;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setTextureRegion(TextureRegion textureRegion0) {
        this.textureRegion = textureRegion0;
    }
}

