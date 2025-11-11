package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.CumulativeDistribution;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public final class WeightMeshSpawnShapeValue extends MeshSpawnShapeValue {
    private CumulativeDistribution distribution;

    public WeightMeshSpawnShapeValue() {
        this.distribution = new CumulativeDistribution();
    }

    public WeightMeshSpawnShapeValue(WeightMeshSpawnShapeValue weightMeshSpawnShapeValue0) {
        super(weightMeshSpawnShapeValue0);
        this.distribution = new CumulativeDistribution();
        this.load(weightMeshSpawnShapeValue0);
    }

    public void calculateWeights() {
        this.distribution.clear();
        VertexAttributes vertexAttributes0 = this.mesh.getVertexAttributes();
        int v = this.mesh.getNumIndices();
        int v1 = this.mesh.getNumVertices();
        int v2 = (short)(vertexAttributes0.vertexSize / 4);
        int v3 = (short)(vertexAttributes0.findByUsage(1).offset / 4);
        float[] arr_f = new float[v1 * v2];
        this.mesh.getVertices(arr_f);
        int v4 = 0;
        if(v > 0) {
            short[] arr_v = new short[v];
            this.mesh.getIndices(arr_v);
            while(v4 < v) {
                int v5 = arr_v[v4] * v2 + v3;
                int v6 = arr_v[v4 + 1] * v2 + v3;
                int v7 = arr_v[v4 + 2] * v2 + v3;
                float f = arr_f[v5];
                float f1 = arr_f[v5 + 1];
                float f2 = arr_f[v5 + 2];
                float f3 = arr_f[v6];
                float f4 = arr_f[v6 + 1];
                float f5 = arr_f[v6 + 2];
                float f6 = arr_f[v7];
                float f7 = arr_f[v7 + 1];
                this.distribution.add(new Triangle(f, f1, f2, f3, f4, f5, f6, f7, arr_f[v7 + 2]), Math.abs(((f4 - f7) * f + (f7 - f1) * f3 + (f1 - f4) * f6) / 2.0f));
                v4 += 3;
            }
        }
        else {
            while(v4 < v1) {
                int v8 = v4 + v3;
                int v9 = v8 + v2;
                int v10 = v9 + v2;
                float f8 = arr_f[v8];
                float f9 = arr_f[v8 + 1];
                float f10 = arr_f[v8 + 2];
                float f11 = arr_f[v9];
                float f12 = arr_f[v9 + 1];
                float f13 = arr_f[v9 + 2];
                float f14 = arr_f[v10];
                float f15 = arr_f[v10 + 1];
                this.distribution.add(new Triangle(f8, f9, f10, f11, f12, f13, f14, f15, arr_f[v10 + 2]), Math.abs(((f12 - f15) * f8 + (f15 - f9) * f11 + (f9 - f12) * f14) / 2.0f));
                v4 += v2;
            }
        }
        this.distribution.generateNormalized();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new WeightMeshSpawnShapeValue(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void init() {
        this.calculateWeights();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(Vector3 vector30, float f) {
        Triangle meshSpawnShapeValue$Triangle0 = (Triangle)this.distribution.value();
        float f1 = MathUtils.random();
        float f2 = MathUtils.random();
        vector30.set(meshSpawnShapeValue$Triangle0.x1 + (meshSpawnShapeValue$Triangle0.x2 - meshSpawnShapeValue$Triangle0.x1) * f1 + (meshSpawnShapeValue$Triangle0.x3 - meshSpawnShapeValue$Triangle0.x1) * f2, meshSpawnShapeValue$Triangle0.y1 + (meshSpawnShapeValue$Triangle0.y2 - meshSpawnShapeValue$Triangle0.y1) * f1 + (meshSpawnShapeValue$Triangle0.y3 - meshSpawnShapeValue$Triangle0.y1) * f2, meshSpawnShapeValue$Triangle0.z1 + f1 * (meshSpawnShapeValue$Triangle0.z2 - meshSpawnShapeValue$Triangle0.z1) + f2 * (meshSpawnShapeValue$Triangle0.z3 - meshSpawnShapeValue$Triangle0.z1));
    }
}

