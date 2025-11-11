package com.badlogic.gdx.backends.android.surfaceview;

public class FixedResolutionStrategy implements ResolutionStrategy {
    private final int height;
    private final int width;

    public FixedResolutionStrategy(int v, int v1) {
        this.width = v;
        this.height = v1;
    }

    @Override  // com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy
    public MeasuredDimension calcMeasures(int v, int v1) {
        return new MeasuredDimension(this.width, this.height);
    }
}

