package com.badlogic.gdx.math;

public class Polyline implements Shape2D {
    private boolean calculateLength;
    private boolean calculateScaledLength;
    private boolean dirty;
    private float length;
    private float[] localVertices;
    private float originX;
    private float originY;
    private float rotation;
    private float scaleX;
    private float scaleY;
    private float scaledLength;
    private float[] worldVertices;
    private float x;
    private float y;

    public Polyline() {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.calculateScaledLength = true;
        this.calculateLength = true;
        this.dirty = true;
        this.localVertices = new float[0];
    }

    public Polyline(float[] arr_f) {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.calculateScaledLength = true;
        this.calculateLength = true;
        this.dirty = true;
        if(arr_f.length < 4) {
            throw new IllegalArgumentException("polylines must contain at least 2 points.");
        }
        this.localVertices = arr_f;
    }

    public void calculateLength() {
        this.calculateLength = true;
    }

    public void calculateScaledLength() {
        this.calculateScaledLength = true;
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(float f, float f1) {
        return false;
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(Vector2 vector20) {
        return false;
    }

    public void dirty() {
        this.dirty = true;
    }

    public float getLength() {
        if(!this.calculateLength) {
            return this.length;
        }
        this.calculateLength = false;
        this.length = 0.0f;
        int v1 = this.localVertices.length - 2;
        for(int v = 0; v < v1; v += 2) {
            float[] arr_f = this.localVertices;
            float f = arr_f[v + 2] - arr_f[v];
            float f1 = arr_f[v + 1] - arr_f[v + 3];
            this.length += (float)Math.sqrt(f * f + f1 * f1);
        }
        return this.length;
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

    public float getScaledLength() {
        if(!this.calculateScaledLength) {
            return this.scaledLength;
        }
        this.calculateScaledLength = false;
        this.scaledLength = 0.0f;
        int v1 = this.localVertices.length - 2;
        for(int v = 0; v < v1; v += 2) {
            float[] arr_f = this.localVertices;
            float f = arr_f[v + 2] * this.scaleX - arr_f[v] * this.scaleX;
            float f1 = arr_f[v + 1] * this.scaleY - arr_f[v + 3] * this.scaleY;
            this.scaledLength += (float)Math.sqrt(f * f + f1 * f1);
        }
        return this.scaledLength;
    }

    public float[] getTransformedVertices() {
        if(!this.dirty) {
            return this.worldVertices;
        }
        this.dirty = false;
        float[] arr_f = this.localVertices;
        if(this.worldVertices == null || this.worldVertices.length < arr_f.length) {
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
        this.calculateScaledLength = true;
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
        this.calculateScaledLength = true;
    }

    public void setVertices(float[] arr_f) {
        if(arr_f.length < 4) {
            throw new IllegalArgumentException("polylines must contain at least 2 points.");
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

