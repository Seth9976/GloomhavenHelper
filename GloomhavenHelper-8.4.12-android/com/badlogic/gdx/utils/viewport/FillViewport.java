package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Scaling;

public class FillViewport extends ScalingViewport {
    public FillViewport(float f, float f1) {
        super(Scaling.fill, f, f1);
    }

    public FillViewport(float f, float f1, Camera camera0) {
        super(Scaling.fill, f, f1, camera0);
    }
}

