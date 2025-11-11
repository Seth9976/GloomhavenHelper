package com.badlogic.gdx.math;

public final class WindowedMean {
    int added_values;
    boolean dirty;
    int last_value;
    float mean;
    float[] values;

    public WindowedMean(int v) {
        this.added_values = 0;
        this.mean = 0.0f;
        this.dirty = true;
        this.values = new float[v];
    }

    public void addValue(float f) {
        int v = this.added_values;
        if(v < this.values.length) {
            this.added_values = v + 1;
        }
        float[] arr_f = this.values;
        int v1 = this.last_value;
        this.last_value = v1 + 1;
        arr_f[v1] = f;
        if(this.last_value > arr_f.length - 1) {
            this.last_value = 0;
        }
        this.dirty = true;
    }

    public void clear() {
        this.added_values = 0;
        this.last_value = 0;
        for(int v = 0; true; ++v) {
            float[] arr_f = this.values;
            if(v >= arr_f.length) {
                break;
            }
            arr_f[v] = 0.0f;
        }
        this.dirty = true;
    }

    public float getHighest() {
        float f = 1.175494E-38f;
        for(int v = 0; true; ++v) {
            float[] arr_f = this.values;
            if(v >= arr_f.length) {
                break;
            }
            f = Math.max(f, arr_f[v]);
        }
        return f;
    }

    public float getLatest() {
        return this.values[(this.last_value - 1 == -1 ? this.values.length : this.last_value) - 1];
    }

    public float getLowest() {
        float f = 3.402823E+38f;
        for(int v = 0; true; ++v) {
            float[] arr_f = this.values;
            if(v >= arr_f.length) {
                break;
            }
            f = Math.min(f, arr_f[v]);
        }
        return f;
    }

    public float getMean() {
        float[] arr_f;
        if(this.hasEnoughData()) {
            if(this.dirty) {
                float f = 0.0f;
                for(int v = 0; true; ++v) {
                    arr_f = this.values;
                    if(v >= arr_f.length) {
                        break;
                    }
                    f += arr_f[v];
                }
                this.mean = f / ((float)arr_f.length);
                this.dirty = false;
            }
            return this.mean;
        }
        return 0.0f;
    }

    public float getOldest() {
        return this.added_values >= this.values.length ? this.values[this.last_value] : this.values[0];
    }

    public int getValueCount() {
        return this.added_values;
    }

    public int getWindowSize() {
        return this.values.length;
    }

    public float[] getWindowValues() {
        float[] arr_f = new float[this.added_values];
        if(this.hasEnoughData()) {
            for(int v = 0; v < arr_f.length; ++v) {
                arr_f[v] = this.values[(this.last_value + v) % this.values.length];
            }
            return arr_f;
        }
        System.arraycopy(this.values, 0, arr_f, 0, this.added_values);
        return arr_f;
    }

    public boolean hasEnoughData() {
        return this.added_values >= this.values.length;
    }

    public float standardDeviation() {
        float[] arr_f;
        float f = 0.0f;
        if(!this.hasEnoughData()) {
            return 0.0f;
        }
        float f1 = this.getMean();
        for(int v = 0; true; ++v) {
            arr_f = this.values;
            if(v >= arr_f.length) {
                break;
            }
            f += (arr_f[v] - f1) * (arr_f[v] - f1);
        }
        return (float)Math.sqrt(f / ((float)arr_f.length));
    }
}

