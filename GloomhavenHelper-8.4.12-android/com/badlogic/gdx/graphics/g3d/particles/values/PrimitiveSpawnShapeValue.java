package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class PrimitiveSpawnShapeValue extends SpawnShapeValue {
    public static enum SpawnSide {
        both,
        top,
        bottom;

    }

    protected static final Vector3 TMP_V1;
    boolean edges;
    protected float spawnDepth;
    protected float spawnDepthDiff;
    public ScaledNumericValue spawnDepthValue;
    protected float spawnHeight;
    protected float spawnHeightDiff;
    public ScaledNumericValue spawnHeightValue;
    protected float spawnWidth;
    protected float spawnWidthDiff;
    public ScaledNumericValue spawnWidthValue;

    static {
        PrimitiveSpawnShapeValue.TMP_V1 = new Vector3();
    }

    public PrimitiveSpawnShapeValue() {
        this.edges = false;
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnDepthValue = new ScaledNumericValue();
    }

    public PrimitiveSpawnShapeValue(PrimitiveSpawnShapeValue primitiveSpawnShapeValue0) {
        super(primitiveSpawnShapeValue0);
        this.edges = false;
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnDepthValue = new ScaledNumericValue();
    }

    public ScaledNumericValue getSpawnDepth() {
        return this.spawnDepthValue;
    }

    public ScaledNumericValue getSpawnHeight() {
        return this.spawnHeightValue;
    }

    public ScaledNumericValue getSpawnWidth() {
        return this.spawnWidthValue;
    }

    public boolean isEdges() {
        return this.edges;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void load(ParticleValue particleValue0) {
        super.load(particleValue0);
        this.edges = ((PrimitiveSpawnShapeValue)particleValue0).edges;
        this.spawnWidthValue.load(((PrimitiveSpawnShapeValue)particleValue0).spawnWidthValue);
        this.spawnHeightValue.load(((PrimitiveSpawnShapeValue)particleValue0).spawnHeightValue);
        this.spawnDepthValue.load(((PrimitiveSpawnShapeValue)particleValue0).spawnDepthValue);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.spawnWidthValue = (ScaledNumericValue)json0.readValue("spawnWidthValue", ScaledNumericValue.class, jsonValue0);
        this.spawnHeightValue = (ScaledNumericValue)json0.readValue("spawnHeightValue", ScaledNumericValue.class, jsonValue0);
        this.spawnDepthValue = (ScaledNumericValue)json0.readValue("spawnDepthValue", ScaledNumericValue.class, jsonValue0);
        this.edges = ((Boolean)json0.readValue("edges", Boolean.TYPE, jsonValue0)).booleanValue();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void setActive(boolean z) {
        super.setActive(z);
        this.spawnWidthValue.setActive(true);
        this.spawnHeightValue.setActive(true);
        this.spawnDepthValue.setActive(true);
    }

    public void setDimensions(float f, float f1, float f2) {
        this.spawnWidthValue.setHigh(f);
        this.spawnHeightValue.setHigh(f1);
        this.spawnDepthValue.setHigh(f2);
    }

    public void setEdges(boolean z) {
        this.edges = z;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void start() {
        this.spawnWidth = this.spawnWidthValue.newLowValue();
        this.spawnWidthDiff = this.spawnWidthValue.newHighValue();
        if(!this.spawnWidthValue.isRelative()) {
            this.spawnWidthDiff -= this.spawnWidth;
        }
        this.spawnHeight = this.spawnHeightValue.newLowValue();
        this.spawnHeightDiff = this.spawnHeightValue.newHighValue();
        if(!this.spawnHeightValue.isRelative()) {
            this.spawnHeightDiff -= this.spawnHeight;
        }
        this.spawnDepth = this.spawnDepthValue.newLowValue();
        this.spawnDepthDiff = this.spawnDepthValue.newHighValue();
        if(!this.spawnDepthValue.isRelative()) {
            this.spawnDepthDiff -= this.spawnDepth;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("spawnWidthValue", this.spawnWidthValue);
        json0.writeValue("spawnHeightValue", this.spawnHeightValue);
        json0.writeValue("spawnDepthValue", this.spawnDepthValue);
        json0.writeValue("edges", Boolean.valueOf(this.edges));
    }
}

