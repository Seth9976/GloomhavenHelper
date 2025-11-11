package com.badlogic.gdx.backends.android.surfaceview;

public interface ResolutionStrategy {
    public static class MeasuredDimension {
        public final int height;
        public final int width;

        public MeasuredDimension(int v, int v1) {
            this.width = v;
            this.height = v1;
        }
    }

    MeasuredDimension calcMeasures(int arg1, int arg2);
}

