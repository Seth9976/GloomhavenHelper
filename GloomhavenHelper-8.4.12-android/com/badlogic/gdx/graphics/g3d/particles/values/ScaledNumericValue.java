package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ScaledNumericValue extends RangedNumericValue {
    private float highMax;
    private float highMin;
    private boolean relative;
    private float[] scaling;
    public float[] timeline;

    public ScaledNumericValue() {
        this.scaling = new float[]{1.0f};
        this.timeline = new float[]{0.0f};
        this.relative = false;
    }

    public float getHighMax() {
        return this.highMax;
    }

    public float getHighMin() {
        return this.highMin;
    }

    public float getScale(float f) {
        int v;
        for(v = 1; true; ++v) {
            if(v >= this.timeline.length) {
                v = -1;
                break;
            }
            if(this.timeline[v] > f) {
                break;
            }
        }
        if(v == -1) {
            return this.scaling[this.timeline.length - 1];
        }
        float[] arr_f = this.scaling;
        float f1 = arr_f[v - 1];
        float[] arr_f1 = this.timeline;
        float f2 = arr_f1[v - 1];
        return f1 + (arr_f[v] - f1) * ((f - f2) / (arr_f1[v] - f2));
    }

    public float[] getScaling() {
        return this.scaling;
    }

    public float[] getTimeline() {
        return this.timeline;
    }

    public boolean isRelative() {
        return this.relative;
    }

    public void load(ScaledNumericValue scaledNumericValue0) {
        super.load(scaledNumericValue0);
        this.highMax = scaledNumericValue0.highMax;
        this.highMin = scaledNumericValue0.highMin;
        this.scaling = new float[scaledNumericValue0.scaling.length];
        System.arraycopy(scaledNumericValue0.scaling, 0, this.scaling, 0, this.scaling.length);
        this.timeline = new float[scaledNumericValue0.timeline.length];
        System.arraycopy(scaledNumericValue0.timeline, 0, this.timeline, 0, this.timeline.length);
        this.relative = scaledNumericValue0.relative;
    }

    public float newHighValue() {
        return this.highMin + (this.highMax - this.highMin) * MathUtils.random();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.highMin = (float)(((Float)json0.readValue("highMin", Float.TYPE, jsonValue0)));
        this.highMax = (float)(((Float)json0.readValue("highMax", Float.TYPE, jsonValue0)));
        this.relative = ((Boolean)json0.readValue("relative", Boolean.TYPE, jsonValue0)).booleanValue();
        this.scaling = (float[])json0.readValue("scaling", float[].class, jsonValue0);
        this.timeline = (float[])json0.readValue("timeline", float[].class, jsonValue0);
    }

    public void setHigh(float f) {
        this.highMin = f;
        this.highMax = f;
    }

    public void setHigh(float f, float f1) {
        this.highMin = f;
        this.highMax = f1;
    }

    public void setHighMax(float f) {
        this.highMax = f;
    }

    public void setHighMin(float f) {
        this.highMin = f;
    }

    public void setRelative(boolean z) {
        this.relative = z;
    }

    public void setScaling(float[] arr_f) {
        this.scaling = arr_f;
    }

    public void setTimeline(float[] arr_f) {
        this.timeline = arr_f;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("highMin", this.highMin);
        json0.writeValue("highMax", this.highMax);
        json0.writeValue("relative", Boolean.valueOf(this.relative));
        json0.writeValue("scaling", this.scaling);
        json0.writeValue("timeline", this.timeline);
    }
}

