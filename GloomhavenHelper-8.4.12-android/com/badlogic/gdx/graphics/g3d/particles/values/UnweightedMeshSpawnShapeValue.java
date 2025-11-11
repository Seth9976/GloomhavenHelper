package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public final class UnweightedMeshSpawnShapeValue extends MeshSpawnShapeValue {
    private short[] indices;
    private int positionOffset;
    private int triangleCount;
    private int vertexCount;
    private int vertexSize;
    private float[] vertices;

    public UnweightedMeshSpawnShapeValue() {
    }

    public UnweightedMeshSpawnShapeValue(UnweightedMeshSpawnShapeValue unweightedMeshSpawnShapeValue0) {
        super(unweightedMeshSpawnShapeValue0);
        this.load(unweightedMeshSpawnShapeValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new UnweightedMeshSpawnShapeValue(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue
    public void setMesh(Mesh mesh0, Model model0) {
        super.setMesh(mesh0, model0);
        this.vertexSize = mesh0.getVertexSize() / 4;
        this.positionOffset = mesh0.getVertexAttribute(1).offset / 4;
        int v = mesh0.getNumIndices();
        if(v > 0) {
            this.indices = new short[v];
            mesh0.getIndices(this.indices);
            this.triangleCount = this.indices.length / 3;
        }
        else {
            this.indices = null;
        }
        this.vertexCount = mesh0.getNumVertices();
        this.vertices = new float[this.vertexCount * this.vertexSize];
        mesh0.getVertices(this.vertices);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(Vector3 vector30, float f) {
        if(this.indices == null) {
            int v = MathUtils.random(this.vertexCount - 3) * this.vertexSize + this.positionOffset;
            int v1 = v + this.vertexSize;
            int v2 = this.vertexSize + v1;
            Triangle.pick(this.vertices[v], this.vertices[v + 1], this.vertices[v + 2], this.vertices[v1], this.vertices[v1 + 1], this.vertices[v1 + 2], this.vertices[v2], this.vertices[v2 + 1], this.vertices[v2 + 2], vector30);
            return;
        }
        int v3 = MathUtils.random(this.triangleCount - 1);
        short[] arr_v = this.indices;
        int v4 = this.vertexSize;
        int v5 = this.positionOffset;
        int v6 = arr_v[v3 * 3] * v4 + v5;
        int v7 = arr_v[v3 * 3 + 1] * v4 + v5;
        int v8 = arr_v[v3 * 3 + 2] * v4 + v5;
        Triangle.pick(this.vertices[v6], this.vertices[v6 + 1], this.vertices[v6 + 2], this.vertices[v7], this.vertices[v7 + 1], this.vertices[v7 + 2], this.vertices[v8], this.vertices[v8 + 1], this.vertices[v8 + 2], vector30);
    }
}

