package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class OrthogonalTiledMapRenderer extends BatchTiledMapRenderer {
    public OrthogonalTiledMapRenderer(TiledMap tiledMap0) {
        super(tiledMap0);
    }

    public OrthogonalTiledMapRenderer(TiledMap tiledMap0, float f) {
        super(tiledMap0, f);
    }

    public OrthogonalTiledMapRenderer(TiledMap tiledMap0, float f, Batch batch0) {
        super(tiledMap0, f, batch0);
    }

    public OrthogonalTiledMapRenderer(TiledMap tiledMap0, Batch batch0) {
        super(tiledMap0, batch0);
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer0) {
        Color color0 = this.batch.getColor();
        float f = Color.toFloatBits(color0.r, color0.g, color0.b, color0.a * tiledMapTileLayer0.getOpacity());
        float f1 = ((float)tiledMapTileLayer0.getTileWidth()) * this.unitScale;
        float f2 = ((float)tiledMapTileLayer0.getTileHeight()) * this.unitScale;
        float f3 = tiledMapTileLayer0.getRenderOffsetX() * this.unitScale;
        float f4 = -tiledMapTileLayer0.getRenderOffsetY() * this.unitScale;
        int v = Math.max(0, ((int)((this.viewBounds.x - f3) / f1)));
        int v1 = Math.min(tiledMapTileLayer0.getWidth(), ((int)((this.viewBounds.x + this.viewBounds.width + f1 - f3) / f1)));
        int v2 = Math.max(0, ((int)((this.viewBounds.y - f4) / f2)));
        int v3 = Math.min(tiledMapTileLayer0.getHeight(), ((int)((this.viewBounds.y + this.viewBounds.height + f2 - f4) / f2)));
        float f5 = ((float)v3) * f2 + f4;
        float[] arr_f = this.vertices;
        while(v3 >= v2) {
            float f6 = ((float)v) * f1 + f3;
            for(int v4 = v; v4 < v1; ++v4) {
                Cell tiledMapTileLayer$Cell0 = tiledMapTileLayer0.getCell(v4, v3);
                if(tiledMapTileLayer$Cell0 != null) {
                    TiledMapTile tiledMapTile0 = tiledMapTileLayer$Cell0.getTile();
                    if(tiledMapTile0 != null) {
                        boolean z = tiledMapTileLayer$Cell0.getFlipVertically();
                        int v5 = tiledMapTileLayer$Cell0.getRotation();
                        TextureRegion textureRegion0 = tiledMapTile0.getTextureRegion();
                        float f7 = f6 + tiledMapTile0.getOffsetX() * this.unitScale;
                        float f8 = tiledMapTile0.getOffsetY() * this.unitScale + f5;
                        float f9 = f7 + ((float)textureRegion0.getRegionWidth()) * this.unitScale;
                        float f10 = ((float)textureRegion0.getRegionHeight()) * this.unitScale + f8;
                        float f11 = textureRegion0.getU();
                        float f12 = textureRegion0.getV2();
                        float f13 = textureRegion0.getU2();
                        float f14 = textureRegion0.getV();
                        arr_f[0] = f7;
                        arr_f[1] = f8;
                        arr_f[2] = f;
                        arr_f[3] = f11;
                        arr_f[4] = f12;
                        arr_f[5] = f7;
                        arr_f[6] = f10;
                        arr_f[7] = f;
                        arr_f[8] = f11;
                        arr_f[9] = f14;
                        arr_f[10] = f9;
                        arr_f[11] = f10;
                        arr_f[12] = f;
                        arr_f[13] = f13;
                        arr_f[14] = f14;
                        arr_f[15] = f9;
                        arr_f[16] = f8;
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
                        if(v5 != 0) {
                            switch(v5) {
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
                        this.batch.draw(textureRegion0.getTexture(), arr_f, 0, 20);
                    }
                }
                f6 += f1;
            }
            f5 -= f2;
            --v3;
        }
    }
}

