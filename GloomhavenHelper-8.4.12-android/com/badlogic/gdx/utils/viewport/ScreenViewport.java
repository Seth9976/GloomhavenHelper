package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ScreenViewport extends Viewport {
    private float unitsPerPixel;

    public ScreenViewport() {
        this(new OrthographicCamera());
    }

    public ScreenViewport(Camera camera0) {
        this.unitsPerPixel = 1.0f;
        this.setCamera(camera0);
    }

    public float getUnitsPerPixel() {
        return this.unitsPerPixel;
    }

    public void setUnitsPerPixel(float f) {
        this.unitsPerPixel = f;
    }

    @Override  // com.badlogic.gdx.utils.viewport.Viewport
    public void update(int v, int v1, boolean z) {
        this.setScreenBounds(0, 0, v, v1);
        this.setWorldSize(((float)v) * this.unitsPerPixel, ((float)v1) * this.unitsPerPixel);
        this.apply(z);
    }
}

