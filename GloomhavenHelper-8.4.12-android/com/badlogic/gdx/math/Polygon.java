package com.badlogic.gdx.math;

public class Polygon implements Shape2D {
    private Rectangle bounds;
    private boolean dirty;
    private float[] localVertices;
    private float originX;
    private float originY;
    private float rotation;
    private float scaleX;
    private float scaleY;
    private float[] worldVertices;
    private float x;
    private float y;

    public Polygon() {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        this.localVertices = new float[0];
    }

    public Polygon(float[] arr_f) {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        if(arr_f.length < 6) {
            throw new IllegalArgumentException("polygons must contain at least 3 points.");
        }
        this.localVertices = arr_f;
    }

    public float area() {
        float[] arr_f = this.getTransformedVertices();
        return GeometryUtils.polygonArea(arr_f, 0, arr_f.length);
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(float f, float f1) {
        float[] arr_f = this.getTransformedVertices();
        int v1 = 0;
        for(int v = 0; v < arr_f.length; v += 2) {
            float f2 = arr_f[v];
            float f3 = arr_f[v + 1];
            float f4 = arr_f[(v + 2) % arr_f.length];
            float f5 = arr_f[(v + 3) % arr_f.length];
            if((f3 <= f1 && f1 < f5 || f5 <= f1 && f1 < f3) && f < (f4 - f2) / (f5 - f3) * (f1 - f3) + f2) {
                ++v1;
            }
        }
        return (v1 & 1) == 1;
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(Vector2 vector20) {
        return this.contains(vector20.x, vector20.y);
    }

    public void dirty() {
        this.dirty = true;
    }

    public Rectangle getBoundingRectangle() {
        float[] arr_f = this.getTransformedVertices();
        float f = arr_f[0];
        float f1 = arr_f[1];
        float f2 = arr_f[0];
        float f3 = arr_f[1];
        for(int v = 2; v < arr_f.length; v += 2) {
            if(f > arr_f[v]) {
                f = arr_f[v];
            }
            if(f1 > arr_f[v + 1]) {
                f1 = arr_f[v + 1];
            }
            if(f2 < arr_f[v]) {
                f2 = arr_f[v];
            }
            if(f3 < arr_f[v + 1]) {
                f3 = arr_f[v + 1];
            }
        }
        if(this.bounds == null) {
            this.bounds = new Rectangle();
        }
        this.bounds.x = f;
        this.bounds.y = f1;
        this.bounds.width = f2 - f;
        this.bounds.height = f3 - f1;
        return this.bounds;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
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

    public float[] getTransformedVertices() {
        if(!this.dirty) {
            return this.worldVertices;
        }
        this.dirty = false;
        float[] arr_f = this.localVertices;
        if(this.worldVertices == null || this.worldVertices.length != arr_f.length) {
            this.worldVertices = new float[arr_f.length];
        }
        float[] arr_f1 = this.worldVertices;
        float f = this.x;
        float f1 = this.y;
        float f2 = this.originX;
        float f3 = this.originY;
        float f4 = this.scaleX;
        float f5 = this.scaleY;
        float f6 = this.rotation;
        float f7 = MathUtils.cosDeg(f6);
        float f8 = MathUtils.sinDeg(f6);
        for(int v = 0; v < arr_f.length; v += 2) {
            float f9 = arr_f[v] - f2;
            float f10 = arr_f[v + 1] - f3;
            if(f4 != 1.0f || f5 != 1.0f) {
                f9 *= f4;
                f10 *= f5;
            }
            if(f6 != 0.0f) {
                float f11 = f7 * f9 - f8 * f10;
                f10 = f9 * f8 + f10 * f7;
                f9 = f11;
            }
            arr_f1[v] = f9 + f + f2;
            arr_f1[v + 1] = f1 + f10 + f3;
        }
        return arr_f1;
    }

    public float[] getVertices() {
        return this.localVertices;
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

    public void setOrigin(float f, float f1) {
        this.originX = f;
        this.originY = f1;
        this.dirty = true;
    }

    public void setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
        this.dirty = true;
    }

    public void setRotation(float f) {
        this.rotation = f;
        this.dirty = true;
    }

    public void setScale(float f, float f1) {
        this.scaleX = f;
        this.scaleY = f1;
        this.dirty = true;
    }

    public void setVertices(float[] arr_f) {
        if(arr_f.length < 6) {
            throw new IllegalArgumentException("polygons must contain at least 3 points.");
        }
        this.localVertices = arr_f;
        this.dirty = true;
    }

    public void translate(float f, float f1) {
        this.x += f;
        this.y += f1;
        this.dirty = true;
    }
}

