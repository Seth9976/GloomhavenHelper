package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Pool.Poolable;

public class FloatCounter implements Poolable {
    public float average;
    public int count;
    public float latest;
    public float max;
    public final WindowedMean mean;
    public float min;
    public float total;
    public float value;

    public FloatCounter(int v) {
        this.mean = v <= 1 ? null : new WindowedMean(v);
        this.reset();
    }

    public void put(float f) {
        this.latest = f;
        this.total += f;
        ++this.count;
        this.average = this.total / ((float)this.count);
        WindowedMean windowedMean0 = this.mean;
        if(windowedMean0 == null) {
            this.value = this.latest;
        }
        else {
            windowedMean0.addValue(f);
            this.value = this.mean.getMean();
        }
        if(this.mean == null || this.mean.hasEnoughData()) {
            float f1 = this.value;
            if(f1 < this.min) {
                this.min = f1;
            }
            float f2 = this.value;
            if(f2 > this.max) {
                this.max = f2;
            }
        }
    }

    @Override  // com.badlogic.gdx.utils.Pool$Poolable
    public void reset() {
        this.count = 0;
        this.total = 0.0f;
        this.min = 3.402823E+38f;
        this.max = -3.402823E+38f;
        this.average = 0.0f;
        this.latest = 0.0f;
        this.value = 0.0f;
        WindowedMean windowedMean0 = this.mean;
        if(windowedMean0 != null) {
            windowedMean0.clear();
        }
    }

    @Override
    public String toString() {
        return "FloatCounter{count=" + this.count + ", total=" + this.total + ", min=" + this.min + ", max=" + this.max + ", average=" + this.average + ", latest=" + this.latest + ", value=" + this.value + '}';
    }
}

