package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.Vector3;

public final class PointSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public PointSpawnShapeValue() {
    }

    public PointSpawnShapeValue(PointSpawnShapeValue pointSpawnShapeValue0) {
        super(pointSpawnShapeValue0);
        this.load(pointSpawnShapeValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new PointSpawnShapeValue(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(Vector3 vector30, float f) {
        vector30.x = this.spawnWidth + this.spawnWidthDiff * this.spawnWidthValue.getScale(f);
        vector30.y = this.spawnHeight + this.spawnHeightDiff * this.spawnHeightValue.getScale(f);
        vector30.z = this.spawnDepth + this.spawnDepthDiff * this.spawnDepthValue.getScale(f);
    }
}

