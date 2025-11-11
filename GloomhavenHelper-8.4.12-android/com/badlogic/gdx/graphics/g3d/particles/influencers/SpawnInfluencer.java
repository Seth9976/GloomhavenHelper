package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.values.PointSpawnShapeValue;
import com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class SpawnInfluencer extends Influencer {
    FloatChannel positionChannel;
    FloatChannel rotationChannel;
    public SpawnShapeValue spawnShapeValue;

    public SpawnInfluencer() {
        this.spawnShapeValue = new PointSpawnShapeValue();
    }

    public SpawnInfluencer(SpawnInfluencer spawnInfluencer0) {
        this.spawnShapeValue = spawnInfluencer0.spawnShapeValue.copy();
    }

    public SpawnInfluencer(SpawnShapeValue spawnShapeValue0) {
        this.spawnShapeValue = spawnShapeValue0;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int v, int v1) {
        int v2 = this.positionChannel.strideSize * v;
        int v3 = this.positionChannel.strideSize * v1 + v2;
        while(v2 < v3) {
            this.spawnShapeValue.spawn(SpawnInfluencer.TMP_V1, this.controller.emitter.percent);
            SpawnInfluencer.TMP_V1.mul(this.controller.transform);
            this.positionChannel.data[v2] = SpawnInfluencer.TMP_V1.x;
            this.positionChannel.data[v2 + 1] = SpawnInfluencer.TMP_V1.y;
            this.positionChannel.data[v2 + 2] = SpawnInfluencer.TMP_V1.z;
            v2 += this.positionChannel.strideSize;
        }
        int v4 = v * this.rotationChannel.strideSize;
        int v5 = v1 * this.rotationChannel.strideSize + v4;
        while(v4 < v5) {
            this.controller.transform.getRotation(SpawnInfluencer.TMP_Q, true);
            this.rotationChannel.data[v4] = SpawnInfluencer.TMP_Q.x;
            this.rotationChannel.data[v4 + 1] = SpawnInfluencer.TMP_Q.y;
            this.rotationChannel.data[v4 + 2] = SpawnInfluencer.TMP_Q.z;
            this.rotationChannel.data[v4 + 3] = SpawnInfluencer.TMP_Q.w;
            v4 += this.rotationChannel.strideSize;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.positionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Position);
        this.rotationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Rotation3D);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return this.copy();
    }

    public SpawnInfluencer copy() {
        return new SpawnInfluencer(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        this.spawnShapeValue.init();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        this.spawnShapeValue.load(assetManager0, resourceData0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void read(Json json0, JsonValue jsonValue0) {
        this.spawnShapeValue = (SpawnShapeValue)json0.readValue("spawnShape", SpawnShapeValue.class, jsonValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        this.spawnShapeValue.save(assetManager0, resourceData0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void start() {
        this.spawnShapeValue.start();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void write(Json json0) {
        json0.writeValue("spawnShape", this.spawnShapeValue, SpawnShapeValue.class);
    }
}

