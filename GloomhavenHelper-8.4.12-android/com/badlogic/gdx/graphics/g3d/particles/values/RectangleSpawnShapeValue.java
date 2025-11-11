package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public final class RectangleSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public RectangleSpawnShapeValue() {
    }

    public RectangleSpawnShapeValue(RectangleSpawnShapeValue rectangleSpawnShapeValue0) {
        super(rectangleSpawnShapeValue0);
        this.load(rectangleSpawnShapeValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new RectangleSpawnShapeValue(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(Vector3 vector30, float f) {
        float f8;
        float f7;
        float f6;
        float f1 = this.spawnWidth + this.spawnWidthDiff * this.spawnWidthValue.getScale(f);
        float f2 = this.spawnHeight + this.spawnHeightDiff * this.spawnHeightValue.getScale(f);
        float f3 = this.spawnDepth + this.spawnDepthDiff * this.spawnDepthValue.getScale(f);
        if(this.edges) {
            int v = MathUtils.random(-1, 1);
            if(v == -1) {
                float f4 = MathUtils.random(1) == 0 ? -f1 / 2.0f : f1 / 2.0f;
                if(f4 == 0.0f) {
                    float f5 = MathUtils.random(1) == 0 ? -f2 / 2.0f : f2 / 2.0f;
                    f6 = MathUtils.random(1) == 0 ? -f3 / 2.0f : f3 / 2.0f;
                    f7 = 0.0f;
                    f8 = f5;
                }
                else {
                    float f9 = MathUtils.random(f2);
                    f6 = MathUtils.random(f3) - f3 / 2.0f;
                    f7 = f4;
                    f8 = f9 - f2 / 2.0f;
                }
            }
            else if(v == 0) {
                float f10 = MathUtils.random(1) == 0 ? -f3 / 2.0f : f3 / 2.0f;
                if(f10 == 0.0f) {
                    if(MathUtils.random(1) == 0) {
                        f2 = -f2;
                    }
                    if(MathUtils.random(1) == 0) {
                        f1 = -f1;
                    }
                    f7 = f1 / 2.0f;
                    f6 = 0.0f;
                    f8 = f2 / 2.0f;
                }
                else {
                    float f11 = MathUtils.random(f2);
                    f7 = MathUtils.random(f1) - f1 / 2.0f;
                    f6 = f10;
                    f8 = f11 - f2 / 2.0f;
                }
            }
            else {
                f8 = MathUtils.random(1) == 0 ? -f2 / 2.0f : f2 / 2.0f;
                if(f8 == 0.0f) {
                    if(MathUtils.random(1) == 0) {
                        f1 = -f1;
                    }
                    f7 = f1 / 2.0f;
                    f6 = MathUtils.random(1) == 0 ? -f3 / 2.0f : f3 / 2.0f;
                }
                else {
                    f7 = MathUtils.random(f1) - f1 / 2.0f;
                    f6 = MathUtils.random(f3) - f3 / 2.0f;
                }
            }
            vector30.x = f7;
            vector30.y = f8;
            vector30.z = f6;
            return;
        }
        vector30.x = MathUtils.random(f1) - f1 / 2.0f;
        vector30.y = MathUtils.random(f2) - f2 / 2.0f;
        vector30.z = MathUtils.random(f3) - f3 / 2.0f;
    }
}

