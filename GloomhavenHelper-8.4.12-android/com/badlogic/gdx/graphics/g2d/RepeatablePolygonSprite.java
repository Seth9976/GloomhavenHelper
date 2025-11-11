package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ShortArray;

public class RepeatablePolygonSprite {
    private Color color;
    private int cols;
    private float density;
    private boolean dirty;
    private float gridHeight;
    private float gridWidth;
    private Array indices;
    private Vector2 offset;
    private Array parts;
    private TextureRegion region;
    private int rows;
    private Array vertices;
    public float x;
    public float y;

    public RepeatablePolygonSprite() {
        this.dirty = true;
        this.parts = new Array();
        this.vertices = new Array();
        this.indices = new Array();
        this.x = 0.0f;
        this.y = 0.0f;
        this.color = Color.WHITE;
        this.offset = new Vector2();
    }

    private void buildVertices() {
        this.vertices.clear();
        for(int v = 0; v < this.parts.size; ++v) {
            float[] arr_f = (float[])this.parts.get(v);
            if(arr_f != null) {
                float[] arr_f1 = new float[arr_f.length * 5 / 2];
                int v1 = this.rows;
                int v2 = v / v1;
                int v3 = v % v1;
                int v4 = 0;
                for(int v5 = 0; v4 < arr_f.length; v5 += 5) {
                    arr_f1[v5] = arr_f[v4] + this.offset.x + this.x;
                    arr_f1[v5 + 1] = arr_f[v4 + 1] + this.offset.y + this.y;
                    arr_f1[v5 + 2] = this.color.toFloatBits();
                    float f = this.gridWidth;
                    float f1 = arr_f[v4] % f / f;
                    float f2 = arr_f[v4 + 1] % this.gridHeight / this.gridHeight;
                    if(arr_f[v4] == ((float)v2) * f) {
                        f1 = 0.0f;
                    }
                    float f3 = 1.0f;
                    if(arr_f[v4] == ((float)(v2 + 1)) * this.gridWidth) {
                        f1 = 1.0f;
                    }
                    float f4 = arr_f[v4 + 1] == ((float)v3) * this.gridHeight ? 0.0f : f2;
                    if(arr_f[v4 + 1] != ((float)(v3 + 1)) * this.gridHeight) {
                        f3 = f4;
                    }
                    float f5 = this.region.getU();
                    float f6 = this.region.getU2();
                    float f7 = this.region.getU();
                    float f8 = this.region.getV();
                    float f9 = this.region.getV2();
                    float f10 = this.region.getV();
                    arr_f1[v5 + 3] = f5 + (f6 - f7) * f1;
                    arr_f1[v5 + 4] = f8 + (f9 - f10) * f3;
                    v4 += 2;
                }
                this.vertices.add(arr_f1);
            }
        }
        this.dirty = false;
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch0) {
        if(this.dirty) {
            this.buildVertices();
        }
        for(int v = 0; v < this.vertices.size; ++v) {
            polygonSpriteBatch0.draw(this.region.getTexture(), ((float[])this.vertices.get(v)), 0, ((float[])this.vertices.get(v)).length, ((short[])this.indices.get(v)), 0, ((short[])this.indices.get(v)).length);
        }
    }

    private float[] offset(float[] arr_f) {
        this.offset.set(arr_f[0], arr_f[1]);
        for(int v1 = 0; v1 < arr_f.length - 1; v1 += 2) {
            if(this.offset.x > arr_f[v1]) {
                this.offset.x = arr_f[v1];
            }
            if(this.offset.y > arr_f[v1 + 1]) {
                this.offset.y = arr_f[v1 + 1];
            }
        }
        for(int v = 0; v < arr_f.length; v += 2) {
            arr_f[v] -= this.offset.x;
            arr_f[v + 1] -= this.offset.y;
        }
        return arr_f;
    }

    public void setColor(Color color0) {
        this.color = color0;
        this.dirty = true;
    }

    public void setPolygon(TextureRegion textureRegion0, float[] arr_f) {
        this.setPolygon(textureRegion0, arr_f, -1.0f);
    }

    public void setPolygon(TextureRegion textureRegion0, float[] arr_f, float f) {
        this.region = textureRegion0;
        Polygon polygon0 = new Polygon(this.offset(arr_f));
        Polygon polygon1 = new Polygon();
        Polygon polygon2 = new Polygon();
        EarClippingTriangulator earClippingTriangulator0 = new EarClippingTriangulator();
        Rectangle rectangle0 = polygon0.getBoundingRectangle();
        float f1 = f == -1.0f ? rectangle0.getWidth() / ((float)textureRegion0.getRegionWidth()) : f;
        this.cols = (int)Math.ceil(f1);
        this.gridWidth = rectangle0.getWidth() / f1;
        this.gridHeight = ((float)textureRegion0.getRegionHeight()) / ((float)textureRegion0.getRegionWidth()) * this.gridWidth;
        this.rows = (int)Math.ceil(rectangle0.getHeight() / this.gridHeight);
        for(int v = 0; v < this.cols; ++v) {
            int v1 = 0;
            while(v1 < this.rows) {
                float[] arr_f1 = new float[8];
                arr_f1[0] = ((float)v) * this.gridWidth;
                float f2 = (float)v1;
                arr_f1[1] = f2 * this.gridHeight;
                arr_f1[2] = ((float)v) * this.gridWidth;
                ++v1;
                arr_f1[3] = ((float)v1) * this.gridHeight;
                arr_f1[4] = ((float)(v + 1)) * this.gridWidth;
                arr_f1[5] = ((float)v1) * this.gridHeight;
                arr_f1[6] = ((float)(v + 1)) * this.gridWidth;
                arr_f1[7] = f2 * this.gridHeight;
                polygon1.setVertices(arr_f1);
                Intersector.intersectPolygons(polygon0, polygon1, polygon2);
                float[] arr_f2 = polygon2.getVertices();
                if(arr_f2.length > 0) {
                    this.parts.add(this.snapToGrid(arr_f2));
                    ShortArray shortArray0 = earClippingTriangulator0.computeTriangles(arr_f2);
                    this.indices.add(shortArray0.toArray());
                }
                else {
                    this.parts.add(null);
                }
            }
        }
        this.buildVertices();
    }

    public void setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
        this.dirty = true;
    }

    private float[] snapToGrid(float[] arr_f) {
        for(int v = 0; v < arr_f.length; v += 2) {
            float f = arr_f[v] / this.gridWidth % 1.0f;
            float f1 = arr_f[v + 1] / this.gridHeight % 1.0f;
            if(f > 0.99f || f < 0.01f) {
                arr_f[v] = this.gridWidth * ((float)Math.round(arr_f[v] / this.gridWidth));
            }
            if(f1 > 0.99f || f1 < 0.01f) {
                arr_f[v + 1] = this.gridHeight * ((float)Math.round(arr_f[v + 1] / this.gridHeight));
            }
        }
        return arr_f;
    }
}

