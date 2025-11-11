package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class SpawnShapeValue extends ParticleValue implements Configurable, Serializable {
    public RangedNumericValue xOffsetValue;
    public RangedNumericValue yOffsetValue;
    public RangedNumericValue zOffsetValue;

    public SpawnShapeValue() {
        this.xOffsetValue = new RangedNumericValue();
        this.yOffsetValue = new RangedNumericValue();
        this.zOffsetValue = new RangedNumericValue();
    }

    public SpawnShapeValue(SpawnShapeValue spawnShapeValue0) {
    }

    public abstract SpawnShapeValue copy();

    public void init() {
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void load(ParticleValue particleValue0) {
        super.load(particleValue0);
        this.xOffsetValue.load(((SpawnShapeValue)particleValue0).xOffsetValue);
        this.yOffsetValue.load(((SpawnShapeValue)particleValue0).yOffsetValue);
        this.zOffsetValue.load(((SpawnShapeValue)particleValue0).zOffsetValue);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json$Serializable
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.xOffsetValue = (RangedNumericValue)json0.readValue("xOffsetValue", RangedNumericValue.class, jsonValue0);
        this.yOffsetValue = (RangedNumericValue)json0.readValue("yOffsetValue", RangedNumericValue.class, jsonValue0);
        this.zOffsetValue = (RangedNumericValue)json0.readValue("zOffsetValue", RangedNumericValue.class, jsonValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
    }

    public final Vector3 spawn(Vector3 vector30, float f) {
        this.spawnAux(vector30, f);
        if(this.xOffsetValue.active) {
            vector30.x += this.xOffsetValue.newLowValue();
        }
        if(this.yOffsetValue.active) {
            vector30.y += this.yOffsetValue.newLowValue();
        }
        if(this.zOffsetValue.active) {
            vector30.z += this.zOffsetValue.newLowValue();
        }
        return vector30;
    }

    public abstract void spawnAux(Vector3 arg1, float arg2);

    public void start() {
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json$Serializable
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("xOffsetValue", this.xOffsetValue);
        json0.writeValue("yOffsetValue", this.yOffsetValue);
        json0.writeValue("zOffsetValue", this.zOffsetValue);
    }
}

