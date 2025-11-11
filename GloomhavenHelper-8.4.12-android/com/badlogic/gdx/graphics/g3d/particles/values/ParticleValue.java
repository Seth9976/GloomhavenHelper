package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ParticleValue implements Serializable {
    public boolean active;

    public ParticleValue() {
    }

    public ParticleValue(ParticleValue particleValue0) {
        this.active = particleValue0.active;
    }

    public boolean isActive() {
        return this.active;
    }

    public void load(ParticleValue particleValue0) {
        this.active = particleValue0.active;
    }

    @Override  // com.badlogic.gdx.utils.Json$Serializable
    public void read(Json json0, JsonValue jsonValue0) {
        this.active = ((Boolean)json0.readValue("active", Boolean.class, jsonValue0)).booleanValue();
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    @Override  // com.badlogic.gdx.utils.Json$Serializable
    public void write(Json json0) {
        json0.writeValue("active", Boolean.valueOf(this.active));
    }
}

