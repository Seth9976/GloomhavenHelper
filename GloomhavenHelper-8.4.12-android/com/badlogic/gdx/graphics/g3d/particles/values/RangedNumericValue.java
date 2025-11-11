package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class RangedNumericValue extends ParticleValue {
    private float lowMax;
    private float lowMin;

    public float getLowMax() {
        return this.lowMax;
    }

    public float getLowMin() {
        return this.lowMin;
    }

    public void load(RangedNumericValue rangedNumericValue0) {
        super.load(rangedNumericValue0);
        this.lowMax = rangedNumericValue0.lowMax;
        this.lowMin = rangedNumericValue0.lowMin;
    }

    public float newLowValue() {
        return this.lowMin + (this.lowMax - this.lowMin) * MathUtils.random();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.lowMin = (float)(((Float)json0.readValue("lowMin", Float.TYPE, jsonValue0)));
        this.lowMax = (float)(((Float)json0.readValue("lowMax", Float.TYPE, jsonValue0)));
    }

    public void setLow(float f) {
        this.lowMin = f;
        this.lowMax = f;
    }

    public void setLow(float f, float f1) {
        this.lowMin = f;
        this.lowMax = f1;
    }

    public void setLowMax(float f) {
        this.lowMax = f;
    }

    public void setLowMin(float f) {
        this.lowMin = f;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("lowMin", this.lowMin);
        json0.writeValue("lowMax", this.lowMax);
    }
}

