package com.badlogic.gdx.maps.tiled.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile.BlendMode;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;

public class AnimatedTiledMapTile implements TiledMapTile {
    private int[] animationIntervals;
    private BlendMode blendMode;
    private int frameCount;
    private StaticTiledMapTile[] frameTiles;
    private int id;
    private static final long initialTimeOffset;
    private static long lastTiledMapRenderTime;
    private int loopDuration;
    private MapObjects objects;
    private MapProperties properties;

    static {
        AnimatedTiledMapTile.initialTimeOffset = 1762855057593L;
    }

    public AnimatedTiledMapTile(float f, Array array0) {
        this.blendMode = BlendMode.ALPHA;
        this.frameCount = 0;
        this.frameTiles = new StaticTiledMapTile[array0.size];
        this.frameCount = array0.size;
        this.loopDuration = array0.size * ((int)(f * 1000.0f));
        this.animationIntervals = new int[array0.size];
        for(int v = 0; v < array0.size; ++v) {
            StaticTiledMapTile[] arr_staticTiledMapTile = this.frameTiles;
            arr_staticTiledMapTile[v] = (StaticTiledMapTile)array0.get(v);
            this.animationIntervals[v] = (int)(f * 1000.0f);
        }
    }

    public AnimatedTiledMapTile(IntArray intArray0, Array array0) {
        this.blendMode = BlendMode.ALPHA;
        this.frameCount = 0;
        this.frameTiles = new StaticTiledMapTile[array0.size];
        this.frameCount = array0.size;
        this.animationIntervals = intArray0.toArray();
        this.loopDuration = 0;
        for(int v = 0; v < intArray0.size; ++v) {
            StaticTiledMapTile[] arr_staticTiledMapTile = this.frameTiles;
            arr_staticTiledMapTile[v] = (StaticTiledMapTile)array0.get(v);
            this.loopDuration += intArray0.get(v);
        }
    }

    public int[] getAnimationIntervals() {
        return this.animationIntervals;
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public BlendMode getBlendMode() {
        return this.blendMode;
    }

    public TiledMapTile getCurrentFrame() {
        StaticTiledMapTile[] arr_staticTiledMapTile = this.frameTiles;
        return arr_staticTiledMapTile[this.getCurrentFrameIndex()];
    }

    public int getCurrentFrameIndex() {
        int v = (int)(AnimatedTiledMapTile.lastTiledMapRenderTime % ((long)this.loopDuration));
        for(int v1 = 0; true; ++v1) {
            int[] arr_v = this.animationIntervals;
            if(v1 >= arr_v.length) {
                break;
            }
            int v2 = arr_v[v1];
            if(v <= v2) {
                return v1;
            }
            v -= v2;
        }
        throw new GdxRuntimeException("Could not determine current animation frame in AnimatedTiledMapTile.  This should never happen.");
    }

    public StaticTiledMapTile[] getFrameTiles() {
        return this.frameTiles;
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
        return this.getCurrentFrame().getOffsetX();
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public float getOffsetY() {
        return this.getCurrentFrame().getOffsetY();
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
        return this.getCurrentFrame().getTextureRegion();
    }

    public void setAnimationIntervals(int[] arr_v) {
        if(arr_v.length != this.animationIntervals.length) {
            throw new GdxRuntimeException("Cannot set " + arr_v.length + " frame intervals. The given int[] must have a size of " + this.animationIntervals.length + ".");
        }
        this.animationIntervals = arr_v;
        this.loopDuration = 0;
        for(int v = 0; v < arr_v.length; ++v) {
            this.loopDuration += arr_v[v];
        }
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
        throw new GdxRuntimeException("Cannot set offset of AnimatedTiledMapTile.");
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setOffsetY(float f) {
        throw new GdxRuntimeException("Cannot set offset of AnimatedTiledMapTile.");
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setTextureRegion(TextureRegion textureRegion0) {
        throw new GdxRuntimeException("Cannot set the texture region of AnimatedTiledMapTile.");
    }

    public static void updateAnimationBaseTime() {
        AnimatedTiledMapTile.lastTiledMapRenderTime = 1762855057597L - AnimatedTiledMapTile.initialTimeOffset;
    }
}

