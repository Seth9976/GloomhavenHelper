package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class IsometricStaggeredTiledMapRenderer extends BatchTiledMapRenderer {
    public IsometricStaggeredTiledMapRenderer(TiledMap tiledMap0) {
        super(tiledMap0);
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap tiledMap0, float f) {
        super(tiledMap0, f);
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap tiledMap0, float f, Batch batch0) {
        super(tiledMap0, f, batch0);
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap tiledMap0, Batch batch0) {
        super(tiledMap0, batch0);
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer0) {
        Color color0 = this.batch.getColor();
        float f = Color.toFloatBits(color0.r, color0.g, color0.b, color0.a * tiledMapTileLayer0.getOpacity());
        float f1 = tiledMapTileLayer0.getRenderOffsetX() * this.unitScale;
        float f2 = -tiledMapTileLayer0.getRenderOffsetY() * this.unitScale;
        float f3 = ((float)tiledMapTileLayer0.getTileWidth()) * this.unitScale;
        float f4 = ((float)tiledMapTileLayer0.getTileHeight()) * this.unitScale;
        int v = Math.max(0, ((int)((this.viewBounds.x - f3 * 0.5f - f1) / f3)));
        int v1 = Math.min(tiledMapTileLayer0.getWidth(), ((int)((this.viewBounds.x + this.viewBounds.width + f3 + f3 * 0.5f - f1) / f3)));
        int v2 = Math.max(0, ((int)((this.viewBounds.y - f4 - f2) / f4)));
        for(int v3 = Math.min(tiledMapTileLayer0.getHeight(), ((int)((this.viewBounds.y + this.viewBounds.height + f4 - f2) / (0.5f * f4)))) - 1; v3 >= v2; --v3) {
            float f5 = v3 % 2 == 1 ? f3 * 0.5f : 0.0f;
            for(int v4 = v1 - 1; v4 >= v; --v4) {
                Cell tiledMapTileLayer$Cell0 = tiledMapTileLayer0.getCell(v4, v3);
                if(tiledMapTileLayer$Cell0 != null) {
                    TiledMapTile tiledMapTile0 = tiledMapTileLayer$Cell0.getTile();
                    if(tiledMapTile0 != null) {
                        boolean z = tiledMapTileLayer$Cell0.getFlipVertically();
                        int v5 = tiledMapTileLayer$Cell0.getRotation();
                        TextureRegion textureRegion0 = tiledMapTile0.getTextureRegion();
                        float f6 = ((float)v4) * f3 - f5 + tiledMapTile0.getOffsetX() * this.unitScale + f1;
                        float f7 = ((float)v3) * (0.5f * f4) + tiledMapTile0.getOffsetY() * this.unitScale + f2;
                        float f8 = ((float)textureRegion0.getRegionWidth()) * this.unitScale + f6;
                        float f9 = ((float)textureRegion0.getRegionHeight()) * this.unitScale + f7;
                        float f10 = textureRegion0.getU();
                        float f11 = textureRegion0.getV2();
                        float f12 = textureRegion0.getU2();
                        float f13 = textureRegion0.getV();
                        this.vertices[0] = f6;
                        this.vertices[1] = f7;
                        this.vertices[2] = f;
                        this.vertices[3] = f10;
                        this.vertices[4] = f11;
                        this.vertices[5] = f6;
                        this.vertices[6] = f9;
                        this.vertices[7] = f;
                        this.vertices[8] = f10;
                        this.vertices[9] = f13;
                        this.vertices[10] = f8;
                        this.vertices[11] = f9;
                        this.vertices[12] = f;
                        this.vertices[13] = f12;
                        this.vertices[14] = f13;
                        this.vertices[15] = f8;
                        this.vertices[16] = f7;
                        this.vertices[17] = f;
                        this.vertices[18] = f12;
                        this.vertices[19] = f11;
                        if(tiledMapTileLayer$Cell0.getFlipHorizontally()) {
                            float f14 = this.vertices[3];
                            this.vertices[3] = this.vertices[13];
                            this.vertices[13] = f14;
                            float f15 = this.vertices[8];
                            this.vertices[8] = this.vertices[18];
                            this.vertices[18] = f15;
                        }
                        if(z) {
                            float f16 = this.vertices[4];
                            this.vertices[4] = this.vertices[14];
                            this.vertices[14] = f16;
                            float f17 = this.vertices[9];
                            this.vertices[9] = this.vertices[19];
                            this.vertices[19] = f17;
                        }
                        if(v5 != 0) {
                            switch(v5) {
                                case 1: {
                                    float f18 = this.vertices[4];
                                    this.vertices[4] = this.vertices[9];
                                    this.vertices[9] = this.vertices[14];
                                    this.vertices[14] = this.vertices[19];
                                    this.vertices[19] = f18;
                                    float f19 = this.vertices[3];
                                    this.vertices[3] = this.vertices[8];
                                    this.vertices[8] = this.vertices[13];
                                    this.vertices[13] = this.vertices[18];
                                    this.vertices[18] = f19;
                                    break;
                                }
                                case 2: {
                                    float f20 = this.vertices[3];
                                    this.vertices[3] = this.vertices[13];
                                    this.vertices[13] = f20;
                                    float f21 = this.vertices[8];
                                    this.vertices[8] = this.vertices[18];
                                    this.vertices[18] = f21;
                                    float f22 = this.vertices[4];
                                    this.vertices[4] = this.vertices[14];
                                    this.vertices[14] = f22;
                                    float f23 = this.vertices[9];
                                    this.vertices[9] = this.vertices[19];
                                    this.vertices[19] = f23;
                                    break;
                                }
                                case 3: {
                                    float f24 = this.vertices[4];
                                    this.vertices[4] = this.vertices[19];
                                    this.vertices[19] = this.vertices[14];
                                    this.vertices[14] = this.vertices[9];
                                    this.vertices[9] = f24;
                                    float f25 = this.vertices[3];
                                    this.vertices[3] = this.vertices[18];
                                    this.vertices[18] = this.vertices[13];
                                    this.vertices[13] = this.vertices[8];
                                    this.vertices[8] = f25;
                                }
                            }
                        }
                        this.batch.draw(textureRegion0.getTexture(), this.vertices, 0, 20);
                    }
                }
            }
        }
    }
}

