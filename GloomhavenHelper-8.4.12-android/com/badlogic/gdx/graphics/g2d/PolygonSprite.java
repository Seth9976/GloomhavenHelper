package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class PolygonSprite {
    private Rectangle bounds;
    private final Color color;
    private boolean dirty;
    private float height;
    private float originX;
    private float originY;
    PolygonRegion region;
    private float rotation;
    private float scaleX;
    private float scaleY;
    private float[] vertices;
    private float width;
    private float x;
    private float y;

    public PolygonSprite(PolygonRegion polygonRegion0) {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.bounds = new Rectangle();
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.setRegion(polygonRegion0);
        this.setSize(((float)polygonRegion0.region.regionWidth), ((float)polygonRegion0.region.regionHeight));
        this.setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public PolygonSprite(PolygonSprite polygonSprite0) {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.bounds = new Rectangle();
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.set(polygonSprite0);
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch0) {
        PolygonRegion polygonRegion0 = this.region;
        polygonSpriteBatch0.draw(polygonRegion0.region.texture, this.getVertices(), 0, this.vertices.length, polygonRegion0.triangles, 0, polygonRegion0.triangles.length);
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch0, float f) {
        Color color0 = this.getColor();
        float f1 = color0.a;
        color0.a *= f;
        this.setColor(color0);
        this.draw(polygonSpriteBatch0);
        color0.a = f1;
        this.setColor(color0);
    }

    public Rectangle getBoundingRectangle() {
        float[] arr_f = this.getVertices();
        float f = arr_f[0];
        float f1 = arr_f[1];
        float f2 = arr_f[0];
        float f3 = arr_f[1];
        for(int v = 5; v < arr_f.length; v += 5) {
            float f4 = arr_f[v];
            float f5 = arr_f[v + 1];
            if(f > f4) {
                f = f4;
            }
            if(f2 < f4) {
                f2 = f4;
            }
            if(f1 > f5) {
                f1 = f5;
            }
            if(f3 < f5) {
                f3 = f5;
            }
        }
        this.bounds.x = f;
        this.bounds.y = f1;
        this.bounds.width = f2 - f;
        this.bounds.height = f3 - f1;
        return this.bounds;
    }

    public Color getColor() {
        return this.color;
    }

    public float getHeight() {
        return this.height;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    public Color getPackedColor() {
        Color.abgr8888ToColor(this.color, this.vertices[2]);
        return this.color;
    }

    public PolygonRegion getRegion() {
        return this.region;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public float[] getVertices() {
        if(!this.dirty) {
            return this.vertices;
        }
        int v = 0;
        this.dirty = false;
        float f = this.originX;
        float f1 = this.originY;
        float f2 = this.scaleX;
        float f3 = this.scaleY;
        PolygonRegion polygonRegion0 = this.region;
        float[] arr_f = this.vertices;
        float[] arr_f1 = polygonRegion0.vertices;
        float f4 = this.x + f;
        float f5 = this.y + f1;
        float f6 = this.width / ((float)polygonRegion0.region.getRegionWidth());
        float f7 = this.height / ((float)polygonRegion0.region.getRegionHeight());
        float f8 = MathUtils.cosDeg(this.rotation);
        float f9 = MathUtils.sinDeg(this.rotation);
        for(int v1 = 0; v < arr_f1.length; v1 += 5) {
            float f10 = (arr_f1[v] * f6 - f) * f2;
            float f11 = (arr_f1[v + 1] * f7 - f1) * f3;
            arr_f[v1] = f8 * f10 - f9 * f11 + f4;
            arr_f[v1 + 1] = f10 * f9 + f11 * f8 + f5;
            v += 2;
        }
        return arr_f;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void rotate(float f) {
        this.rotation += f;
        this.dirty = true;
    }

    public void scale(float f) {
        this.scaleX += f;
        this.scaleY += f;
        this.dirty = true;
    }

    public void set(PolygonSprite polygonSprite0) {
        if(polygonSprite0 == null) {
            throw new IllegalArgumentException("sprite cannot be null.");
        }
        this.setRegion(polygonSprite0.region);
        this.x = polygonSprite0.x;
        this.y = polygonSprite0.y;
        this.width = polygonSprite0.width;
        this.height = polygonSprite0.height;
        this.originX = polygonSprite0.originX;
        this.originY = polygonSprite0.originY;
        this.rotation = polygonSprite0.rotation;
        this.scaleX = polygonSprite0.scaleX;
        this.scaleY = polygonSprite0.scaleY;
        this.color.set(polygonSprite0.color);
    }

    public void setBounds(float f, float f1, float f2, float f3) {
        this.x = f;
        this.y = f1;
        this.width = f2;
        this.height = f3;
        this.dirty = true;
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
        float f4 = this.color.toFloatBits();
        float[] arr_f = this.vertices;
        for(int v = 2; v < arr_f.length; v += 5) {
            arr_f[v] = f4;
        }
    }

    public void setColor(Color color0) {
        this.color.set(color0);
        float f = color0.toFloatBits();
        float[] arr_f = this.vertices;
        for(int v = 2; v < arr_f.length; v += 5) {
            arr_f[v] = f;
        }
    }

    public void setOrigin(float f, float f1) {
        this.originX = f;
        this.originY = f1;
        this.dirty = true;
    }

    public void setPosition(float f, float f1) {
        this.translate(f - this.x, f1 - this.y);
    }

    public void setRegion(PolygonRegion polygonRegion0) {
        this.region = polygonRegion0;
        float[] arr_f = polygonRegion0.textureCoords;
        int v = polygonRegion0.vertices.length / 2 * 5;
        if(this.vertices == null || this.vertices.length != v) {
            this.vertices = new float[v];
        }
        float f = this.color.toFloatBits();
        float[] arr_f1 = this.vertices;
        int v2 = 0;
        for(int v1 = 2; v1 < v; v1 += 5) {
            arr_f1[v1] = f;
            arr_f1[v1 + 1] = arr_f[v2];
            arr_f1[v1 + 2] = arr_f[v2 + 1];
            v2 += 2;
        }
        this.dirty = true;
    }

    public void setRotation(float f) {
        this.rotation = f;
        this.dirty = true;
    }

    public void setScale(float f) {
        this.scaleX = f;
        this.scaleY = f;
        this.dirty = true;
    }

    public void setScale(float f, float f1) {
        this.scaleX = f;
        this.scaleY = f1;
        this.dirty = true;
    }

    public void setSize(float f, float f1) {
        this.width = f;
        this.height = f1;
        this.dirty = true;
    }

    public void setX(float f) {
        this.translateX(f - this.x);
    }

    public void setY(float f) {
        this.translateY(f - this.y);
    }

    public void translate(float f, float f1) {
        this.x += f;
        this.y += f1;
        if(this.dirty) {
            return;
        }
        float[] arr_f = this.vertices;
        for(int v = 0; v < arr_f.length; v += 5) {
            arr_f[v] += f;
            arr_f[v + 1] += f1;
        }
    }

    public void translateX(float f) {
        this.x += f;
        if(this.dirty) {
            return;
        }
        float[] arr_f = this.vertices;
        for(int v = 0; v < arr_f.length; v += 5) {
            arr_f[v] += f;
        }
    }

    public void translateY(float f) {
        this.y += f;
        if(this.dirty) {
            return;
        }
        float[] arr_f = this.vertices;
        for(int v = 1; v < arr_f.length; v += 5) {
            arr_f[v] += f;
        }
    }
}

