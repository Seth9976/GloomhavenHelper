package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import java.util.Iterator;

public abstract class BatchTiledMapRenderer implements TiledMapRenderer, Disposable {
    protected static final int NUM_VERTICES = 20;
    protected Batch batch;
    protected Rectangle imageBounds;
    protected TiledMap map;
    protected boolean ownsBatch;
    protected float unitScale;
    protected float[] vertices;
    protected Rectangle viewBounds;

    public BatchTiledMapRenderer(TiledMap tiledMap0) {
        this(tiledMap0, 1.0f);
    }

    public BatchTiledMapRenderer(TiledMap tiledMap0, float f) {
        this.imageBounds = new Rectangle();
        this.vertices = new float[20];
        this.map = tiledMap0;
        this.unitScale = f;
        this.viewBounds = new Rectangle();
        this.batch = new SpriteBatch();
        this.ownsBatch = true;
    }

    public BatchTiledMapRenderer(TiledMap tiledMap0, float f, Batch batch0) {
        this.imageBounds = new Rectangle();
        this.vertices = new float[20];
        this.map = tiledMap0;
        this.unitScale = f;
        this.viewBounds = new Rectangle();
        this.batch = batch0;
        this.ownsBatch = false;
    }

    public BatchTiledMapRenderer(TiledMap tiledMap0, Batch batch0) {
        this(tiledMap0, 1.0f, batch0);
    }

    protected void beginRender() {
        AnimatedTiledMapTile.updateAnimationBaseTime();
        this.batch.begin();
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if(this.ownsBatch) {
            this.batch.dispose();
        }
    }

    protected void endRender() {
        this.batch.end();
    }

    public Batch getBatch() {
        return this.batch;
    }

    public TiledMap getMap() {
        return this.map;
    }

    public float getUnitScale() {
        return this.unitScale;
    }

    public Rectangle getViewBounds() {
        return this.viewBounds;
    }

    @Override  // com.badlogic.gdx.maps.MapRenderer
    public void render() {
        this.beginRender();
        for(Object object0: this.map.getLayers()) {
            this.renderMapLayer(((MapLayer)object0));
        }
        this.endRender();
    }

    @Override  // com.badlogic.gdx.maps.MapRenderer
    public void render(int[] arr_v) {
        this.beginRender();
        for(int v = 0; v < arr_v.length; ++v) {
            int v1 = arr_v[v];
            this.renderMapLayer(this.map.getLayers().get(v1));
        }
        this.endRender();
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderImageLayer(TiledMapImageLayer tiledMapImageLayer0) {
        Color color0 = this.batch.getColor();
        float f = Color.toFloatBits(color0.r, color0.g, color0.b, color0.a * tiledMapImageLayer0.getOpacity());
        float[] arr_f = this.vertices;
        TextureRegion textureRegion0 = tiledMapImageLayer0.getTextureRegion();
        if(textureRegion0 == null) {
            return;
        }
        float f1 = tiledMapImageLayer0.getX() * this.unitScale;
        float f2 = tiledMapImageLayer0.getY() * this.unitScale;
        float f3 = ((float)textureRegion0.getRegionWidth()) * this.unitScale + f1;
        float f4 = ((float)textureRegion0.getRegionHeight()) * this.unitScale + f2;
        this.imageBounds.set(f1, f2, f3 - f1, f4 - f2);
        if(this.viewBounds.contains(this.imageBounds) || this.viewBounds.overlaps(this.imageBounds)) {
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
            this.batch.draw(textureRegion0.getTexture(), arr_f, 0, 20);
        }
    }

    protected void renderMapLayer(MapLayer mapLayer0) {
        if(!mapLayer0.isVisible()) {
            return;
        }
        if(mapLayer0 instanceof MapGroupLayer) {
            MapLayers mapLayers0 = ((MapGroupLayer)mapLayer0).getLayers();
            for(int v = 0; v < mapLayers0.size(); ++v) {
                MapLayer mapLayer1 = mapLayers0.get(v);
                if(mapLayer1.isVisible()) {
                    this.renderMapLayer(mapLayer1);
                }
            }
            return;
        }
        if(mapLayer0 instanceof TiledMapTileLayer) {
            this.renderTileLayer(((TiledMapTileLayer)mapLayer0));
            return;
        }
        if(mapLayer0 instanceof TiledMapImageLayer) {
            this.renderImageLayer(((TiledMapImageLayer)mapLayer0));
            return;
        }
        this.renderObjects(mapLayer0);
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

    public void setMap(TiledMap tiledMap0) {
        this.map = tiledMap0;
    }

    @Override  // com.badlogic.gdx.maps.MapRenderer
    public void setView(OrthographicCamera orthographicCamera0) {
        this.batch.setProjectionMatrix(orthographicCamera0.combined);
        float f = orthographicCamera0.viewportWidth * orthographicCamera0.zoom;
        float f1 = orthographicCamera0.viewportHeight * orthographicCamera0.zoom;
        float f2 = Math.abs(orthographicCamera0.up.y) * f + Math.abs(orthographicCamera0.up.x) * f1;
        float f3 = f1 * Math.abs(orthographicCamera0.up.y) + f * Math.abs(orthographicCamera0.up.x);
        this.viewBounds.set(orthographicCamera0.position.x - f2 / 2.0f, orthographicCamera0.position.y - f3 / 2.0f, f2, f3);
    }

    @Override  // com.badlogic.gdx.maps.MapRenderer
    public void setView(Matrix4 matrix40, float f, float f1, float f2, float f3) {
        this.batch.setProjectionMatrix(matrix40);
        this.viewBounds.set(f, f1, f2, f3);
    }
}

