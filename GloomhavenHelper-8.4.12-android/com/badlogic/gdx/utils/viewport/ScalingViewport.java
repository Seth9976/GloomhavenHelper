package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

public class ScalingViewport extends Viewport {
    private Scaling scaling;

    public ScalingViewport(Scaling scaling0, float f, float f1) {
        this(scaling0, f, f1, new OrthographicCamera());
    }

    public ScalingViewport(Scaling scaling0, float f, float f1, Camera camera0) {
        this.scaling = scaling0;
        this.setWorldSize(f, f1);
        this.setCamera(camera0);
    }

    public Scaling getScaling() {
        return this.scaling;
    }

    public void setScaling(Scaling scaling0) {
        this.scaling = scaling0;
    }

    @Override  // com.badlogic.gdx.utils.viewport.Viewport
    public void update(int v, int v1, boolean z) {
        Vector2 vector20 = this.scaling.apply(this.getWorldWidth(), this.getWorldHeight(), ((float)v), ((float)v1));
        int v2 = Math.round(vector20.x);
        int v3 = Math.round(vector20.y);
        this.setScreenBounds((v - v2) / 2, (v1 - v3) / 2, v2, v3);
        this.apply(z);
    }
}

