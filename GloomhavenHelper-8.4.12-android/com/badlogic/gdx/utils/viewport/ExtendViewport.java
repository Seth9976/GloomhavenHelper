package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

public class ExtendViewport extends Viewport {
    private float maxWorldHeight;
    private float maxWorldWidth;
    private float minWorldHeight;
    private float minWorldWidth;

    public ExtendViewport(float f, float f1) {
        this(f, f1, 0.0f, 0.0f, new OrthographicCamera());
    }

    public ExtendViewport(float f, float f1, float f2, float f3) {
        this(f, f1, f2, f3, new OrthographicCamera());
    }

    public ExtendViewport(float f, float f1, float f2, float f3, Camera camera0) {
        this.minWorldWidth = f;
        this.minWorldHeight = f1;
        this.maxWorldWidth = f2;
        this.maxWorldHeight = f3;
        this.setCamera(camera0);
    }

    public ExtendViewport(float f, float f1, Camera camera0) {
        this(f, f1, 0.0f, 0.0f, camera0);
    }

    public float getMaxWorldHeight() {
        return this.maxWorldHeight;
    }

    public float getMaxWorldWidth() {
        return this.maxWorldWidth;
    }

    public float getMinWorldHeight() {
        return this.minWorldHeight;
    }

    public float getMinWorldWidth() {
        return this.minWorldWidth;
    }

    public void setMaxWorldHeight(float f) {
        this.maxWorldHeight = f;
    }

    public void setMaxWorldWidth(float f) {
        this.maxWorldWidth = f;
    }

    public void setMinWorldHeight(float f) {
        this.minWorldHeight = f;
    }

    public void setMinWorldWidth(float f) {
        this.minWorldWidth = f;
    }

    @Override  // com.badlogic.gdx.utils.viewport.Viewport
    public void update(int v, int v1, boolean z) {
        float f = this.minWorldWidth;
        float f1 = this.minWorldHeight;
        Vector2 vector20 = Scaling.fit.apply(f, f1, ((float)v), ((float)v1));
        int v2 = Math.round(vector20.x);
        int v3 = Math.round(vector20.y);
        if(v2 < v) {
            float f2 = ((float)(v - v2)) * (f1 / ((float)v3));
            float f3 = this.maxWorldWidth;
            if(f3 > 0.0f) {
                f2 = Math.min(f2, f3 - this.minWorldWidth);
            }
            f += f2;
            v2 += Math.round(f2 * (((float)v3) / f1));
        }
        else if(v3 < v1) {
            float f4 = ((float)(v1 - v3)) * (f / ((float)v2));
            float f5 = this.maxWorldHeight;
            if(f5 > 0.0f) {
                f4 = Math.min(f4, f5 - this.minWorldHeight);
            }
            f1 += f4;
            v3 += Math.round(f4 * (((float)v2) / f));
        }
        this.setWorldSize(f, f1);
        this.setScreenBounds((v - v2) / 2, (v1 - v3) / 2, v2, v3);
        this.apply(z);
    }
}

