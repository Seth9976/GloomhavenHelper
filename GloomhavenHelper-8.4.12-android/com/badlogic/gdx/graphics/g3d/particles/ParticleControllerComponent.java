package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class ParticleControllerComponent implements Configurable, Disposable, Serializable {
    protected static final Matrix3 TMP_M3;
    protected static final Matrix4 TMP_M4;
    protected static final Quaternion TMP_Q;
    protected static final Quaternion TMP_Q2;
    protected static final Vector3 TMP_V1;
    protected static final Vector3 TMP_V2;
    protected static final Vector3 TMP_V3;
    protected static final Vector3 TMP_V4;
    protected static final Vector3 TMP_V5;
    protected static final Vector3 TMP_V6;
    protected ParticleController controller;

    static {
        ParticleControllerComponent.TMP_V1 = new Vector3();
        ParticleControllerComponent.TMP_V2 = new Vector3();
        ParticleControllerComponent.TMP_V3 = new Vector3();
        ParticleControllerComponent.TMP_V4 = new Vector3();
        ParticleControllerComponent.TMP_V5 = new Vector3();
        ParticleControllerComponent.TMP_V6 = new Vector3();
        ParticleControllerComponent.TMP_Q = new Quaternion();
        ParticleControllerComponent.TMP_Q2 = new Quaternion();
        ParticleControllerComponent.TMP_M3 = new Matrix3();
        ParticleControllerComponent.TMP_M4 = new Matrix4();
    }

    public void activateParticles(int v, int v1) {
    }

    public void allocateChannels() {
    }

    public abstract ParticleControllerComponent copy();

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    public void end() {
    }

    public void init() {
    }

    public void killParticles(int v, int v1) {
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
    }

    @Override  // com.badlogic.gdx.utils.Json$Serializable
    public void read(Json json0, JsonValue jsonValue0) {
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
    }

    public void set(ParticleController particleController0) {
        this.controller = particleController0;
    }

    public void start() {
    }

    public void update() {
    }

    @Override  // com.badlogic.gdx.utils.Json$Serializable
    public void write(Json json0) {
    }
}

