package com.badlogic.gdx.graphics.g3d.particles.emitters;

import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class Emitter extends ParticleControllerComponent implements Serializable {
    public int maxParticleCount;
    public int minParticleCount;
    public float percent;

    public Emitter() {
        this.maxParticleCount = 4;
    }

    public Emitter(Emitter emitter0) {
        this.maxParticleCount = 4;
        this.set(emitter0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void end() {
        this.controller.particles.size = 0;
    }

    public int getMaxParticleCount() {
        return this.maxParticleCount;
    }

    public int getMinParticleCount() {
        return this.minParticleCount;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        this.controller.particles.size = 0;
    }

    public boolean isComplete() {
        return this.percent >= 1.0f;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json$Serializable
    public void read(Json json0, JsonValue jsonValue0) {
        this.minParticleCount = (int)(((Integer)json0.readValue("minParticleCount", Integer.TYPE, jsonValue0)));
        this.maxParticleCount = (int)(((Integer)json0.readValue("maxParticleCount", Integer.TYPE, jsonValue0)));
    }

    public void set(Emitter emitter0) {
        this.minParticleCount = emitter0.minParticleCount;
        this.maxParticleCount = emitter0.maxParticleCount;
    }

    public void setMaxParticleCount(int v) {
        this.maxParticleCount = v;
    }

    public void setMinParticleCount(int v) {
        this.minParticleCount = v;
    }

    public void setParticleCount(int v, int v1) {
        this.setMinParticleCount(v);
        this.setMaxParticleCount(v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json$Serializable
    public void write(Json json0) {
        json0.writeValue("minParticleCount", this.minParticleCount);
        json0.writeValue("maxParticleCount", this.maxParticleCount);
    }
}

