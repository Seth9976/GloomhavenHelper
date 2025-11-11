package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import java.util.Iterator;

public class OrthoCachedTiledMapRenderer implements TiledMapRenderer, Disposable {
    protected static final int NUM_VERTICES = 20;
    protected boolean blending;
    protected final Rectangle cacheBounds;
    protected boolean cached;
    protected boolean canCacheMoreE;
    protected boolean canCacheMoreN;
    protected boolean canCacheMoreS;
    protected boolean canCacheMoreW;
    protected int count;
    protected final TiledMap map;
    protected float maxTileHeight;
    protected float maxTileWidth;
    protected float overCache;
    protected final SpriteCache spriteCache;
    private static final float tolerance = 0.00001f;
    protected float unitScale;
    protected final float[] vertices;
    protected final Rectangle viewBounds;

    public OrthoCachedTiledMapRenderer(TiledMap tiledMap0) {
        this(tiledMap0, 1.0f, 2000);
    }

    public OrthoCachedTiledMapRenderer(TiledMap tiledMap0, float f) {
        this(tiledMap0, f, 2000);
    }

    public OrthoCachedTiledMapRenderer(TiledMap tiledMap0, float f, int v) {
        this.vertices = new float[20];
        this.viewBounds = new Rectangle();
        this.cacheBounds = new Rectangle();
        this.overCache = 0.5f;
        this.map = tiledMap0;
        this.unitScale = f;
        this.spriteCache = new SpriteCache(v, true);
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.spriteCache.dispose();
    }

    public SpriteCache getSpriteCache() {
        return this.spriteCache;
    }

    public void invalidateCache() {
        this.cached = false;
    }

    public boolean isCached() {
        return this.cached;
    }

    @Override  // com.badlogic.gdx.maps.MapRenderer
    public void render() {
        if(!this.cached) {
            this.cached = true;
            this.count = 0;
            this.spriteCache.clear();
            float f = this.viewBounds.width * this.overCache;
            float f1 = this.viewBounds.height * this.overCache;
            this.cacheBounds.x = this.viewBounds.x - f;
            this.cacheBounds.y = this.viewBounds.y - f1;
            this.cacheBounds.width = this.viewBounds.width + f * 2.0f;
            this.cacheBounds.height = this.viewBounds.height + f1 * 2.0f;
            for(Object object0: this.map.getLayers()) {
                MapLayer mapLayer0 = (MapLayer)object0;
                this.spriteCache.beginCache();
                if(mapLayer0 instanceof TiledMapTileLayer) {
                    this.renderTileLayer(((TiledMapTileLayer)mapLayer0));
                }
                else if(mapLayer0 instanceof TiledMapImageLayer) {
                    this.renderImageLayer(((TiledMapImageLayer)mapLayer0));
                }
                this.spriteCache.endCache();
            }
        }
        if(this.blending) {
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 0x303);
        }
        this.spriteCache.begin();
        MapLayers mapLayers0 = this.map.getLayers();
        int v1 = mapLayers0.getCount();
        for(int v = 0; v < v1; ++v) {
            MapLayer mapLayer1 = mapLayers0.get(v);
            if(mapLayer1.isVisible()) {
                this.spriteCache.draw(v);
                this.renderObjects(mapLayer1);
            }
        }
        this.spriteCache.end();
        if(this.blending) {
            Gdx.gl.glDisable(3042);
        }
    }

    @Override  // com.badlogic.gdx.maps.MapRenderer
    public void render(int[] arr_v) {
        if(!this.cached) {
            this.cached = true;
            this.count = 0;
            this.spriteCache.clear();
            float f = this.viewBounds.width * this.overCache;
            float f1 = this.viewBounds.height * this.overCache;
            this.cacheBounds.x = this.viewBounds.x - f;
            this.cacheBounds.y = this.viewBounds.y - f1;
            this.cacheBounds.width = this.viewBounds.width + f * 2.0f;
            this.cacheBounds.height = this.viewBounds.height + f1 * 2.0f;
            for(Object object0: this.map.getLayers()) {
                MapLayer mapLayer0 = (MapLayer)object0;
                this.spriteCache.beginCache();
                if(mapLayer0 instanceof TiledMapTileLayer) {
                    this.renderTileLayer(((TiledMapTileLayer)mapLayer0));
                }
                else if(mapLayer0 instanceof TiledMapImageLayer) {
                    this.renderImageLayer(((TiledMapImageLayer)mapLayer0));
                }
                this.spriteCache.endCache();
            }
        }
        if(this.blending) {
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 0x303);
        }
        this.spriteCache.begin();
        MapLayers mapLayers0 = this.map.getLayers();
        for(int v = 0; v < arr_v.length; ++v) {
            int v1 = arr_v[v];
            MapLayer mapLayer1 = mapLayers0.get(v1);
            if(mapLayer1.isVisible()) {
                this.spriteCache.draw(v1);
                this.renderObjects(mapLayer1);
            }
        }
        this.spriteCache.end();
        if(this.blending) {
            Gdx.gl.glDisable(3042);
        }
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderImageLayer(TiledMapImageLayer tiledMapImageLayer0) {
        float f = Color.toFloatBits(1.0f, 1.0f, 1.0f, tiledMapImageLayer0.getOpacity());
        float[] arr_f = this.vertices;
        TextureRegion textureRegion0 = tiledMapImageLayer0.getTextureRegion();
        if(textureRegion0 == null) {
            return;
        }
        float f1 = tiledMapImageLayer0.getX() * this.unitScale;
        float f2 = tiledMapImageLayer0.getY() * this.unitScale;
        float f3 = ((float)textureRegion0.getRegionWidth()) * this.unitScale + f1;
        float f4 = ((float)textureRegion0.getRegionHeight()) * this.unitScale + f2;
        float f5 = textureRegion0.getU();
        float f6 = textureRegion0.getV2();
        float f7 = textureRegion0.getU2();
        float f8 = textureRegion0.getV();
        arr_f[0] = f1;
        arr_f[1] = f2;
        arr_f[2] = f;
        arr_f[3] = f5;
        arr_f[4] = f6;
        arr_f[5] = f1;
        arr_f[6] = f4;
        arr_f[7] = f;
        arr_f[8] = f5;
        arr_f[9] = f8;
        arr_f[10] = f3;
        arr_f[11] = f4;
        arr_f[12] = f;
        arr_f[13] = f7;
        arr_f[14] = f8;
        arr_f[15] = f3;
        arr_f[16] = f2;
        arr_f[17] = f;
        arr_f[18] = f7;
        arr_f[19] = f6;
        this.spriteCache.add(textureRegion0.getTexture(), arr_f, 0, 20);
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderObject(MapObject mapObject0) {
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderObjects(MapLayer mapLayer0) {
        Iterator iterator0 = mapLayer0.getObjects().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer0) {
        float f = Color.toFloatBits(1.0f, 1.0f, 1.0f, tiledMapTileLayer0.getOpacity());
        int v = tiledMapTileLayer0.getWidth();
        int v1 = tiledMapTileLayer0.getHeight();
        float f1 = ((float)tiledMapTileLayer0.getTileWidth()) * this.unitScale;
        float f2 = ((float)tiledMapTileLayer0.getTileHeight()) * this.unitScale;
        float f3 = tiledMapTileLayer0.getRenderOffsetX() * this.unitScale;
        float f4 = -tiledMapTileLayer0.getRenderOffsetY() * this.unitScale;
        int v2 = Math.max(0, ((int)((this.cacheBounds.x - f3) / f1)));
        int v3 = Math.min(v, ((int)((this.cacheBounds.x + this.cacheBounds.width + f1 - f3) / f1)));
        int v4 = Math.max(0, ((int)((this.cacheBounds.y - f4) / f2)));
        int v5 = Math.min(v1, ((int)((this.cacheBounds.y + this.cacheBounds.height + f2 - f4) / f2)));
        this.canCacheMoreN = v5 < v1;
        this.canCacheMoreE = v3 < v;
        this.canCacheMoreW = v2 > 0;
        this.canCacheMoreS = v4 > 0;
        float[] arr_f = this.vertices;
        while(v5 >= v4) {
            for(int v6 = v2; v6 < v3; ++v6) {
                Cell tiledMapTileLayer$Cell0 = tiledMapTileLayer0.getCell(v6, v5);
                if(tiledMapTileLayer$Cell0 != null) {
                    TiledMapTile tiledMapTile0 = tiledMapTileLayer$Cell0.getTile();
                    if(tiledMapTile0 != null) {
                        ++this.count;
                        boolean z = tiledMapTileLayer$Cell0.getFlipVertically();
                        int v7 = tiledMapTileLayer$Cell0.getRotation();
                        TextureRegion textureRegion0 = tiledMapTile0.getTextureRegion();
                        Texture texture0 = textureRegion0.getTexture();
                        float f5 = ((float)v6) * f1 + tiledMapTile0.getOffsetX() * this.unitScale + f3;
                        float f6 = ((float)v5) * f2 + tiledMapTile0.getOffsetY() * this.unitScale + f4;
                        float f7 = ((float)textureRegion0.getRegionWidth()) * this.unitScale + f5;
                        float f8 = ((float)textureRegion0.getRegionHeight()) * this.unitScale + f6;
                        float f9 = 0.5f / ((float)texture0.getWidth());
                        float f10 = 0.5f / ((float)texture0.getHeight());
                        float f11 = textureRegion0.getU() + f9;
                        float f12 = textureRegion0.getV2() - f10;
                        float f13 = textureRegion0.getU2() - f9;
                        float f14 = textureRegion0.getV() + f10;
                        arr_f[0] = f5;
                        arr_f[1] = f6;
                        arr_f[2] = f;
                        arr_f[3] = f11;
                        arr_f[4] = f12;
                        arr_f[5] = f5;
                        arr_f[6] = f8;
                        arr_f[7] = f;
                        arr_f[8] = f11;
                        arr_f[9] = f14;
                        arr_f[10] = f7;
                        arr_f[11] = f8;
                        arr_f[12] = f;
                        arr_f[13] = f13;
                        arr_f[14] = f14;
                        arr_f[15] = f7;
                        arr_f[16] = f6;
                        arr_f[17] = f;
                        arr_f[18] = f13;
                        arr_f[19] = f12;
                        if(tiledMapTileLayer$Cell0.getFlipHorizontally()) {
                            float f15 = arr_f[3];
                            arr_f[3] = arr_f[13];
                            arr_f[13] = f15;
                            float f16 = arr_f[8];
                            arr_f[8] = arr_f[18];
                            arr_f[18] = f16;
                        }
                        if(z) {
                            float f17 = arr_f[4];
                            arr_f[4] = arr_f[14];
                            arr_f[14] = f17;
                            float f18 = arr_f[9];
                            arr_f[9] = arr_f[19];
                            arr_f[19] = f18;
                        }
                        if(v7 != 0) {
                            switch(v7) {
                                case 1: {
                                    float f19 = arr_f[4];
                                    arr_f[4] = arr_f[9];
                                    arr_f[9] = arr_f[14];
                                    arr_f[14] = arr_f[19];
                                    arr_f[19] = f19;
                                    float f20 = arr_f[3];
                                    arr_f[3] = arr_f[8];
                                    arr_f[8] = arr_f[13];
                                    arr_f[13] = arr_f[18];
                                    arr_f[18] = f20;
                                    break;
                                }
                                case 2: {
                                    float f21 = arr_f[3];
                                    arr_f[3] = arr_f[13];
                                    arr_f[13] = f21;
                                    float f22 = arr_f[8];
                                    arr_f[8] = arr_f[18];
                                    arr_f[18] = f22;
                                    float f23 = arr_f[4];
                                    arr_f[4] = arr_f[14];
                                    arr_f[14] = f23;
                                    float f24 = arr_f[9];
                                    arr_f[9] = arr_f[19];
                                    arr_f[19] = f24;
                                    break;
                                }
                                case 3: {
                                    float f25 = arr_f[4];
                                    arr_f[4] = arr_f[19];
                                    arr_f[19] = arr_f[14];
                                    arr_f[14] = arr_f[9];
                                    arr_f[9] = f25;
                                    float f26 = arr_f[3];
                                    arr_f[3] = arr_f[18];
                                    arr_f[18] = arr_f[13];
                                    arr_f[13] = arr_f[8];
                                    arr_f[8] = f26;
                                }
                            }
                        }
                        this.spriteCache.add(texture0, arr_f, 0, 20);
                    }
                }
            }
            --v5;
        }
    }

    public void setBlending(boolean z) {
        this.blending = z;
    }

    public void setMaxTileSize(float f, float f1) {
        this.maxTileWidth = f;
        this.maxTileHeight = f1;
    }

    public void setOverCache(float f) {
        this.overCache = f;
    }

    @Override  // com.badlogic.gdx.maps.MapRenderer
    public void setView(OrthographicCamera orthographicCamera0) {
        this.spriteCache.setProjectionMatrix(orthographicCamera0.combined);
        float f = orthographicCamera0.viewportWidth * orthographicCamera0.zoom + this.maxTileWidth * 2.0f * this.unitScale;
        float f1 = orthographicCamera0.viewportHeight * orthographicCamera0.zoom + this.maxTileHeight * 2.0f * this.unitScale;
        this.viewBounds.set(orthographicCamera0.position.x - f / 2.0f, orthographicCamera0.position.y - f1 / 2.0f, f, f1);
        if(this.canCacheMoreW && this.viewBounds.x < this.cacheBounds.x - 0.00001f || this.canCacheMoreS && this.viewBounds.y < this.cacheBounds.y - 0.00001f || this.canCacheMoreE && this.viewBounds.x + this.viewBounds.width > this.cacheBounds.x + this.cacheBounds.width + 0.00001f || this.canCacheMoreN && this.viewBounds.y + this.viewBounds.height > this.cacheBounds.y + this.cacheBounds.height + 0.00001f) {
            this.cached = false;
        }
    }

    @Override  // com.badlogic.gdx.maps.MapRenderer
    public void setView(Matrix4 matrix40, float f, float f1, float f2, float f3) {
        this.spriteCache.setProjectionMatrix(matrix40);
        this.viewBounds.set(f - this.maxTileWidth * this.unitScale, f1 - this.maxTileHeight * this.unitScale, f2 + this.maxTileWidth * 2.0f * this.unitScale, f3 + this.maxTileHeight * 2.0f * this.unitScale);
        if(this.canCacheMoreW && this.viewBounds.x < this.cacheBounds.x - 0.00001f || this.canCacheMoreS && this.viewBounds.y < this.cacheBounds.y - 0.00001f || this.canCacheMoreE && this.viewBounds.x + this.viewBounds.width > this.cacheBounds.x + this.cacheBounds.width + 0.00001f || this.canCacheMoreN && this.viewBounds.y + this.viewBounds.height > this.cacheBounds.y + this.cacheBounds.height + 0.00001f) {
            this.cached = false;
        }
    }
}

