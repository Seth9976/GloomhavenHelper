package com.badlogic.gdx.backends.android.surfaceview;

import android.view.View.MeasureSpec;

public class FillResolutionStrategy implements ResolutionStrategy {
    @Override  // com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy
    public MeasuredDimension calcMeasures(int v, int v1) {
        return new MeasuredDimension(View.MeasureSpec.getSize(v), View.MeasureSpec.getSize(v1));
    }
}

