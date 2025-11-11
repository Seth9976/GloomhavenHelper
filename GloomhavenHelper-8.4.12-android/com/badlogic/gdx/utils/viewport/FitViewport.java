package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Scaling;

public class FitViewport extends ScalingViewport {
    public FitViewport(float f, float f1) {
        super(Scaling.fit, f, f1);
    }

    public FitViewport(float f, float f1, Camera camera0) {
        super(Scaling.fit, f, f1, camera0);
    }
}

