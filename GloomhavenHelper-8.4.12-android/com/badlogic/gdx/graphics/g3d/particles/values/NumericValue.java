package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class NumericValue extends ParticleValue {
    private float value;

    public float getValue() {
        return this.value;
    }

    public void load(NumericValue numericValue0) {
        super.load(numericValue0);
        this.value = numericValue0.value;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.value = (float)(((Float)json0.readValue("value", Float.TYPE, jsonValue0)));
    }

    public void setValue(float f) {
        this.value = f;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("value", this.value);
    }
}

