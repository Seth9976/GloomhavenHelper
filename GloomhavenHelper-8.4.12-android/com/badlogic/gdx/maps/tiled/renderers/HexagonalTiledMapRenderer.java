package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;

public class HexagonalTiledMapRenderer extends BatchTiledMapRenderer {
    private float hexSideLength;
    private boolean staggerAxisX;
    private boolean staggerIndexEven;

    public HexagonalTiledMapRenderer(TiledMap tiledMap0) {
        super(tiledMap0);
        this.staggerAxisX = true;
        this.staggerIndexEven = false;
        this.hexSideLength = 0.0f;
        this.init(tiledMap0);
    }

    public HexagonalTiledMapRenderer(TiledMap tiledMap0, float f) {
        super(tiledMap0, f);
        this.staggerAxisX = true;
        this.staggerIndexEven = false;
        this.hexSideLength = 0.0f;
        this.init(tiledMap0);
    }

    public HexagonalTiledMapRenderer(TiledMap tiledMap0, float f, Batch batch0) {
        super(tiledMap0, f, batch0);
        this.staggerAxisX = true;
        this.staggerIndexEven = false;
        this.hexSideLength = 0.0f;
        this.init(tiledMap0);
    }

    public HexagonalTiledMapRenderer(TiledMap tiledMap0, Batch batch0) {
        super(tiledMap0, batch0);
        this.staggerAxisX = true;
        this.staggerIndexEven = false;
        this.hexSideLength = 0.0f;
        this.init(tiledMap0);
    }

    private void init(TiledMap tiledMap0) {
        String s = (String)tiledMap0.getProperties().get("staggeraxis", String.class);
        if(s != null) {
            this.staggerAxisX = s.equals("x");
        }
        String s1 = (String)tiledMap0.getProperties().get("staggerindex", String.class);
        if(s1 != null) {
            this.staggerIndexEven = s1.equals("even");
        }
        Integer integer0 = (Integer)tiledMap0.getProperties().get("hexsidelength", Integer.class);
        if(integer0 != null) {
            this.hexSideLength = (float)(((int)integer0));
            return;
        }
        if(this.staggerAxisX) {
            Integer integer1 = (Integer)tiledMap0.getProperties().get("tilewidth", Integer.class);
            if(integer1 != null) {
                this.hexSideLength = ((float)(((int)integer1))) * 0.5f;
                return;
            }
            this.hexSideLength = ((float)((TiledMapTileLayer)tiledMap0.getLayers().get(0)).getTileWidth()) * 0.5f;
            return;
        }
        Integer integer2 = (Integer)tiledMap0.getProperties().get("tileheight", Integer.class);
        if(integer2 != null) {
            this.hexSideLength = ((float)(((int)integer2))) * 0.5f;
            return;
        }
        this.hexSideLength = ((float)((TiledMapTileLayer)tiledMap0.getLayers().get(0)).getTileHeight()) * 0.5f;
    }

    private void renderCell(Cell tiledMapTileLayer$Cell0, float f, float f1, float f2) {
        if(tiledMapTileLayer$Cell0 != null) {
            TiledMapTile tiledMapTile0 = tiledMapTileLayer$Cell0.getTile();
            if(tiledMapTile0 == null || tiledMapTile0 instanceof AnimatedTiledMapTile) {
                return;
            }
            boolean z = tiledMapTileLayer$Cell0.getFlipVertically();
            int v = tiledMapTileLayer$Cell0.getRotation();
            TextureRegion textureRegion0 = tiledMapTile0.getTextureRegion();
            float f3 = f + tiledMapTile0.getOffsetX() * this.unitScale;
            float f4 = f1 + tiledMapTile0.getOffsetY() * this.unitScale;
            float f5 = ((float)textureRegion0.getRegionWidth()) * this.unitScale + f3;
            float f6 = ((float)textureRegion0.getRegionHeight()) * this.unitScale + f4;
            float f7 = textureRegion0.getU();
            float f8 = textureRegion0.getV2();
            float f9 = textureRegion0.getU2();
            float f10 = textureRegion0.getV();
            this.vertices[0] = f3;
            this.vertices[1] = f4;
            this.vertices[2] = f2;
            this.vertices[3] = f7;
            this.vertices[4] = f8;
            this.vertices[5] = f3;
            this.vertices[6] = f6;
            this.vertices[7] = f2;
            this.vertices[8] = f7;
            this.vertices[9] = f10;
            this.vertices[10] = f5;
            this.vertices[11] = f6;
            this.vertices[12] = f2;
            this.vertices[13] = f9;
            this.vertices[14] = f10;
            this.vertices[15] = f5;
            this.vertices[16] = f4;
            this.vertices[17] = f2;
            this.vertices[18] = f9;
            this.vertices[19] = f8;
            if(tiledMapTileLayer$Cell0.getFlipHorizontally()) {
                float f11 = this.vertices[3];
                this.vertices[3] = this.vertices[13];
                this.vertices[13] = f11;
                float f12 = this.vertices[8];
                this.vertices[8] = this.vertices[18];
                this.vertices[18] = f12;
            }
            if(z) {
                float f13 = this.vertices[4];
                this.vertices[4] = this.vertices[14];
                this.vertices[14] = f13;
                float f14 = this.vertices[9];
                this.vertices[9] = this.vertices[19];
                this.vertices[19] = f14;
            }
            if(v == 2) {
                float f15 = this.vertices[3];
                this.vertices[3] = this.vertices[13];
                this.vertices[13] = f15;
                float f16 = this.vertices[8];
                this.vertices[8] = this.vertices[18];
                this.vertices[18] = f16;
                float f17 = this.vertices[4];
                this.vertices[4] = this.vertices[14];
                this.vertices[14] = f17;
                float f18 = this.vertices[9];
                this.vertices[9] = this.vertices[19];
                this.vertices[19] = f18;
            }
            this.batch.draw(textureRegion0.getTexture(), this.vertices, 0, 20);
        }
    }

    @Override  // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer0) {
        Color color0 = this.batch.getColor();
        float f = Color.toFloatBits(color0.r, color0.g, color0.b, color0.a * tiledMapTileLayer0.getOpacity());
        int v = tiledMapTileLayer0.getWidth();
        int v1 = tiledMapTileLayer0.getHeight();
        float f1 = ((float)tiledMapTileLayer0.getTileWidth()) * this.unitScale;
        float f2 = ((float)tiledMapTileLayer0.getTileHeight()) * this.unitScale;
        float f3 = tiledMapTileLayer0.getRenderOffsetX() * this.unitScale;
        float f4 = -tiledMapTileLayer0.getRenderOffsetY() * this.unitScale;
        float f5 = this.hexSideLength * this.unitScale;
        boolean z = false;
        if(this.staggerAxisX) {
            float f6 = (f1 + f5) / 2.0f;
            int v2 = Math.max(0, ((int)((this.viewBounds.y - 0.5f * f2 - f3) / f2)));
            int v3 = Math.min(v1, ((int)((this.viewBounds.y + this.viewBounds.height + f2 - f3) / f2)));
            int v4 = Math.max(0, ((int)((this.viewBounds.x - (f1 - f5) / 2.0f - f4) / f6)));
            int v5 = Math.min(v, ((int)((this.viewBounds.x + this.viewBounds.width + f6 - f4) / f6)));
            boolean z1 = this.staggerIndexEven;
            if(v4 % 2 == 0) {
                z = true;
            }
            int v6 = z1 == z ? v4 + 1 : v4;
            if(this.staggerIndexEven != (v4 % 2 == 0)) {
                ++v4;
            }
            for(int v7 = v3 - 1; v7 >= v2; --v7) {
                for(int v8 = v6; v8 < v5; v8 += 2) {
                    this.renderCell(tiledMapTileLayer0.getCell(v8, v7), ((float)v8) * f6 + f3, ((float)v7) * f2 + 0.5f * f2 + f4, f);
                }
                for(int v9 = v4; v9 < v5; v9 += 2) {
                    this.renderCell(tiledMapTileLayer0.getCell(v9, v7), ((float)v9) * f6 + f3, ((float)v7) * f2 + f4, f);
                }
            }
            return;
        }
        float f7 = (f2 + f5) / 2.0f;
        int v10 = Math.max(0, ((int)((this.viewBounds.y - (f2 - f5) / 2.0f - f3) / f7)));
        int v11 = Math.min(v1, ((int)((this.viewBounds.y + this.viewBounds.height + f7 - f3) / f7)));
        int v12 = Math.max(0, ((int)((this.viewBounds.x - 0.5f * f1 - f4) / f1)));
        int v13 = Math.min(v, ((int)((this.viewBounds.x + this.viewBounds.width + f1 - f4) / f1)));
        for(int v14 = v11 - 1; v14 >= v10; --v14) {
            float f8 = v14 % 2 == 0 == this.staggerIndexEven ? 0.5f * f1 : 0.0f;
            for(int v15 = v12; v15 < v13; ++v15) {
                this.renderCell(tiledMapTileLayer0.getCell(v15, v14), ((float)v15) * f1 + f8 + f3, ((float)v14) * f7 + f4, f);
            }
        }
    }
}

