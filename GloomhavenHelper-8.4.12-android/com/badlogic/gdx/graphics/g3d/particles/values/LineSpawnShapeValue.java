package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public final class LineSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public LineSpawnShapeValue() {
    }

    public LineSpawnShapeValue(LineSpawnShapeValue lineSpawnShapeValue0) {
        super(lineSpawnShapeValue0);
        this.load(lineSpawnShapeValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new LineSpawnShapeValue(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(Vector3 vector30, float f) {
        float f1 = this.spawnWidth;
        float f2 = this.spawnWidthDiff;
        float f3 = this.spawnWidthValue.getScale(f);
        float f4 = this.spawnHeight;
        float f5 = this.spawnHeightDiff;
        float f6 = this.spawnHeightValue.getScale(f);
        float f7 = this.spawnDepth;
        float f8 = this.spawnDepthDiff;
        float f9 = this.spawnDepthValue.getScale(f);
        float f10 = MathUtils.random();
        vector30.x = (f1 + f2 * f3) * f10;
        vector30.y = (f4 + f5 * f6) * f10;
        vector30.z = f10 * (f7 + f8 * f9);
    }
}

