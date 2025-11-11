package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public final class CylinderSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public CylinderSpawnShapeValue() {
    }

    public CylinderSpawnShapeValue(CylinderSpawnShapeValue cylinderSpawnShapeValue0) {
        super(cylinderSpawnShapeValue0);
        this.load(cylinderSpawnShapeValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new CylinderSpawnShapeValue(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(Vector3 vector30, float f) {
        float f6;
        float f5;
        float f1 = this.spawnWidth + this.spawnWidthDiff * this.spawnWidthValue.getScale(f);
        float f2 = this.spawnHeight + this.spawnHeightDiff * this.spawnHeightValue.getScale(f);
        float f3 = this.spawnDepth + this.spawnDepthDiff * this.spawnDepthValue.getScale(f);
        float f4 = MathUtils.random(f2);
        if(this.edges) {
            f5 = f1 / 2.0f;
            f6 = f3 / 2.0f;
        }
        else {
            f5 = MathUtils.random(f1) / 2.0f;
            f6 = MathUtils.random(f3) / 2.0f;
        }
        boolean z = false;
        float f7 = 0.0f;
        if(f6 == 0.0f) {
            z = true;
        }
        if(f5 != 0.0f && !z) {
            f7 = MathUtils.random(360.0f);
        }
        else if(f5 != 0.0f) {
            if(z && MathUtils.random(1) != 0) {
                f7 = 180.0f;
            }
        }
        else if(MathUtils.random(1) == 0) {
            f7 = -90.0f;
        }
        else {
            f7 = 90.0f;
        }
        vector30.set(f5 * MathUtils.cosDeg(f7), f4 - f2 / 2.0f, f6 * MathUtils.sinDeg(f7));
    }
}

