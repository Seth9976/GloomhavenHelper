package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public final class EllipseSpawnShapeValue extends PrimitiveSpawnShapeValue {
    SpawnSide side;

    public EllipseSpawnShapeValue() {
        this.side = SpawnSide.both;
    }

    public EllipseSpawnShapeValue(EllipseSpawnShapeValue ellipseSpawnShapeValue0) {
        super(ellipseSpawnShapeValue0);
        this.side = SpawnSide.both;
        this.load(ellipseSpawnShapeValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new EllipseSpawnShapeValue(this);
    }

    public SpawnSide getSide() {
        return this.side;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue
    public void load(ParticleValue particleValue0) {
        super.load(particleValue0);
        this.side = ((EllipseSpawnShapeValue)particleValue0).side;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.side = (SpawnSide)json0.readValue("side", SpawnSide.class, jsonValue0);
    }

    public void setSide(SpawnSide primitiveSpawnShapeValue$SpawnSide0) {
        this.side = primitiveSpawnShapeValue$SpawnSide0;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(Vector3 vector30, float f) {
        float f8;
        float f7;
        float f6;
        float f4;
        float f1 = this.spawnWidth + this.spawnWidthDiff * this.spawnWidthValue.getScale(f);
        float f2 = this.spawnHeight + this.spawnHeightDiff * this.spawnHeightValue.getScale(f);
        float f3 = this.spawnDepth + this.spawnDepthDiff * this.spawnDepthValue.getScale(f);
        if(this.side == SpawnSide.top) {
            f4 = 3.141593f;
        }
        else {
            f4 = this.side == SpawnSide.bottom ? -3.141593f : 6.283185f;
        }
        float f5 = MathUtils.random(0.0f, f4);
        if(this.edges) {
            if(f1 == 0.0f) {
                vector30.set(0.0f, f2 / 2.0f * MathUtils.sin(f5), f3 / 2.0f * MathUtils.cos(f5));
                return;
            }
            if(f2 == 0.0f) {
                vector30.set(f1 / 2.0f * MathUtils.cos(f5), 0.0f, f3 / 2.0f * MathUtils.sin(f5));
                return;
            }
            if(f3 == 0.0f) {
                vector30.set(f1 / 2.0f * MathUtils.cos(f5), f2 / 2.0f * MathUtils.sin(f5), 0.0f);
                return;
            }
            f6 = f1 / 2.0f;
            f7 = f2 / 2.0f;
            f8 = f3 / 2.0f;
        }
        else {
            f6 = MathUtils.random(f1 / 2.0f);
            f7 = MathUtils.random(f2 / 2.0f);
            f8 = MathUtils.random(f3 / 2.0f);
        }
        float f9 = MathUtils.random(-1.0f, 1.0f);
        float f10 = (float)Math.sqrt(1.0f - f9 * f9);
        vector30.set(f6 * f10 * MathUtils.cos(f5), f7 * f10 * MathUtils.sin(f5), f8 * f9);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("side", this.side);
    }
}

