package androidx.interpolator.view.animation;

import android.view.animation.Interpolator;

abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize;
    private final float[] mValues;

    protected LookupTableInterpolator(float[] arr_f) {
        this.mValues = arr_f;
        this.mStepSize = 1.0f / ((float)(this.mValues.length - 1));
    }

    @Override  // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if(f >= 1.0f) {
            return 1.0f;
        }
        if(f <= 0.0f) {
            return 0.0f;
        }
        int v = Math.min(((int)(((float)(this.mValues.length - 1)) * f)), this.mValues.length - 2);
        return this.mValues[v] + (f - ((float)v) * this.mStepSize) / this.mStepSize * (this.mValues[v + 1] - this.mValues[v]);
    }
}

