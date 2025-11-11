package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class IsometricTiledMapRenderer extends BatchTiledMapRenderer {
    private Vector2 bottomLeft;
    private Vector2 bottomRight;
    private Matrix4 invIsotransform;
    private Matrix4 isoTransform;
    private Vector3 screenPos;
    private Vector2 topLeft;
    private Vector2 topRight;

    public IsometricTiledMapRenderer(TiledMap tiledMap0) {
        super(tiledMap0);
        this.screenPos = new Vector3();
        this.topRight = new Vector2();
        this.bottomLeft = new Vector2();
        this.topLeft = new Vector2();
        this.bottomRight = new Vector2();
        this.init();
    }

    public IsometricTiledMapRenderer(TiledMap tiledMap0, float f) {
        super(tiledMap0, f);
        this.screenPos = new Vector3();
        this.topRight = new Vector2();
        this.bottomLeft = new Vector2();
        this.topLeft = new Vector2();
        this.bottomRight = new Vector2();
        this.init();
    }

    public IsometricTiledMapRenderer(TiledMap tiledMap0, float f, Batch batch0) {
        super(tiledMap0, f, batch0);
        this.screenPos = new Vector3();
        this.topRight = new Vector2();
        this.bottomLeft = new Vector2();
        this.topLeft = new Vector2();
        this.bottomRight = new Vector2();
        this.init();
    }

    public IsometricTiledMapRenderer(TiledMap tiledMap0, Batch batch0) {
        super(tiledMap0, batch0);
        this.screenPos = new Vector3();
        this.topRight = new Vector2();
        this.bottomLeft = new Vector2();
        this.topLeft = new Vector2();
        this.bottomRight = new Vector2();
        this.init();
    }

    private void init() {
        this.isoTransform = new Matrix4();
        this.isoTransform.idt();
        this.isoTransform.scale(0.707107f, 0.353553f, 1.0f);
        this.isoTransform.rotate(0.0f, 0.0f, 1.0f, -45.0f);
        this.invIsotransform = new Matrix4(this.isoTransform);
        this.invIsotransform.inv();
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer0) {
        Color color0 = this.batch.getColor();
        float f = Color.toFloatBits(color0.r, color0.g, color0.b, color0.a * tiledMapTileLayer0.getOpacity());
        float f1 = ((float)tiledMapTileLayer0.getTileWidth()) * this.unitScale;
        float f2 = ((float)tiledMapTileLayer0.getTileHeight()) * this.unitScale;
        float f3 = tiledMapTileLayer0.getRenderOffsetX() * this.unitScale;
        float f4 = -tiledMapTileLayer0.getRenderOffsetY() * this.unitScale;
        this.topRight.set(this.viewBounds.x + this.viewBounds.width - f3, this.viewBounds.y - f4);
        this.bottomLeft.set(this.viewBounds.x - f3, this.viewBounds.y + this.viewBounds.height - f4);
        this.topLeft.set(this.viewBounds.x - f3, this.viewBounds.y - f4);
        this.bottomRight.set(this.viewBounds.x + this.viewBounds.width - f3, this.viewBounds.y + this.viewBounds.height - f4);
        int v = ((int)(this.translateScreenToIso(this.topLeft).y / f1)) - 2;
        int v1 = ((int)(this.translateScreenToIso(this.bottomRight).y / f1)) + 2;
        int v2 = ((int)(this.translateScreenToIso(this.bottomLeft).x / f1)) - 2;
        int v3 = ((int)(this.translateScreenToIso(this.topRight).x / f1)) + 2;
        while(v1 >= v) {
            for(int v4 = v2; v4 <= v3; ++v4) {
                Cell tiledMapTileLayer$Cell0 = tiledMapTileLayer0.getCell(v4, v1);
                if(tiledMapTileLayer$Cell0 != null) {
                    TiledMapTile tiledMapTile0 = tiledMapTileLayer$Cell0.getTile();
                    if(tiledMapTile0 != null) {
                        boolean z = tiledMapTileLayer$Cell0.getFlipVertically();
                        int v5 = tiledMapTileLayer$Cell0.getRotation();
                        TextureRegion textureRegion0 = tiledMapTile0.getTextureRegion();
                        float f5 = ((float)v4) * (f1 * 0.5f) + ((float)v1) * (f1 * 0.5f) + tiledMapTile0.getOffsetX() * this.unitScale + f3;
                        float f6 = ((float)v1) * (f2 * 0.5f) - ((float)v4) * (f2 * 0.5f) + tiledMapTile0.getOffsetY() * this.unitScale + f4;
                        float f7 = ((float)textureRegion0.getRegionWidth()) * this.unitScale + f5;
                        float f8 = ((float)textureRegion0.getRegionHeight()) * this.unitScale + f6;
                        float f9 = textureRegion0.getU();
                        float f10 = textureRegion0.getV2();
                        float f11 = textureRegion0.getU2();
                        float f12 = textureRegion0.getV();
                        this.vertices[0] = f5;
                        this.vertices[1] = f6;
                        this.vertices[2] = f;
                        this.vertices[3] = f9;
                        this.vertices[4] = f10;
                        this.vertices[5] = f5;
                        this.vertices[6] = f8;
                        this.vertices[7] = f;
                        this.vertices[8] = f9;
                        this.vertices[9] = f12;
                        this.vertices[10] = f7;
                        this.vertices[11] = f8;
                        this.vertices[12] = f;
                        this.vertices[13] = f11;
                        this.vertices[14] = f12;
                        this.vertices[15] = f7;
                        this.vertices[16] = f6;
                        this.vertices[17] = f;
                        this.vertices[18] = f11;
                        this.vertices[19] = f10;
                        if(tiledMapTileLayer$Cell0.getFlipHorizontally()) {
                            float f13 = this.vertices[3];
                            this.vertices[3] = this.vertices[13];
                            this.vertices[13] = f13;
                            float f14 = this.vertices[8];
                            this.vertices[8] = this.vertices[18];
                            this.vertices[18] = f14;
                        }
                        if(z) {
                            float f15 = this.vertices[4];
                            this.vertices[4] = this.vertices[14];
                            this.vertices[14] = f15;
                            float f16 = this.vertices[9];
                            this.vertices[9] = this.vertices[19];
                            this.vertices[19] = f16;
                        }
                        if(v5 != 0) {
                            switch(v5) {
                                case 1: {
                                    float f17 = this.vertices[4];
                                    this.vertices[4] = this.vertices[9];
                                    this.vertices[9] = this.vertices[14];
                                    this.vertices[14] = this.vertices[19];
                                    this.vertices[19] = f17;
                                    float f18 = this.vertices[3];
                                    this.vertices[3] = this.vertices[8];
                                    this.vertices[8] = this.vertices[13];
                                    this.vertices[13] = this.vertices[18];
                                    this.vertices[18] = f18;
                                    break;
                                }
                                case 2: {
                                    float f19 = this.vertices[3];
                                    this.vertices[3] = this.vertices[13];
                                    this.vertices[13] = f19;
                                    float f20 = this.vertices[8];
                                    this.vertices[8] = this.vertices[18];
                                    this.vertices[18] = f20;
                                    float f21 = this.vertices[4];
                                    this.vertices[4] = this.vertices[14];
                                    this.vertices[14] = f21;
                                    float f22 = this.vertices[9];
                                    this.vertices[9] = this.vertices[19];
                                    this.vertices[19] = f22;
                                    break;
                                }
                                case 3: {
                                    float f23 = this.vertices[4];
                                    this.vertices[4] = this.vertices[19];
                                    this.vertices[19] = this.vertices[14];
                                    this.vertices[14] = this.vertices[9];
                                    this.vertices[9] = f23;
                                    float f24 = this.vertices[3];
                                    this.vertices[3] = this.vertices[18];
                                    this.vertices[18] = this.vertices[13];
                                    this.vertices[13] = this.vertices[8];
                                    this.vertices[8] = f24;
                                }
                            }
                        }
                        this.batch.draw(textureRegion0.getTexture(), this.vertices, 0, 20);
                    }
                }
            }
            --v1;
        }
    }

    private Vector3 translateScreenToIso(Vector2 vector20) {
        this.screenPos.set(vector20.x, vector20.y, 0.0f);
        this.screenPos.mul(this.invIsotransform);
        return this.screenPos;
    }
}

