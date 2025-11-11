package com.badlogic.gdx.backends.android.surfaceview;

import android.view.View.MeasureSpec;

public class RatioResolutionStrategy implements ResolutionStrategy {
    private final float ratio;

    public RatioResolutionStrategy(float f) {
        this.ratio = f;
    }

    public RatioResolutionStrategy(float f, float f1) {
        this.ratio = f / f1;
    }

    @Override  // com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy
    public MeasuredDimension calcMeasures(int v, int v1) {
        int v2 = View.MeasureSpec.getSize(v);
        int v3 = View.MeasureSpec.getSize(v1);
        return ((float)v2) / ((float)v3) < this.ratio ? new MeasuredDimension(v2, Math.round(((float)v2) / this.ratio)) : new MeasuredDimension(Math.round(((float)v3) * this.ratio), v3);
    }
}

