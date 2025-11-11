package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Scaling;

public class StretchViewport extends ScalingViewport {
    public StretchViewport(float f, float f1) {
        super(Scaling.stretch, f, f1);
    }

    public StretchViewport(float f, float f1, Camera camera0) {
        super(Scaling.stretch, f, f1, camera0);
    }
}

