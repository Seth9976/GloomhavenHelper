package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.SaveData;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

public abstract class MeshSpawnShapeValue extends SpawnShapeValue {
    public static class Triangle {
        float x1;
        float x2;
        float x3;
        float y1;
        float y2;
        float y3;
        float z1;
        float z2;
        float z3;

        public Triangle(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
            this.x1 = f;
            this.y1 = f1;
            this.z1 = f2;
            this.x2 = f3;
            this.y2 = f4;
            this.z2 = f5;
            this.x3 = f6;
            this.y3 = f7;
            this.z3 = f8;
        }

        public static Vector3 pick(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Vector3 vector30) {
            float f9 = MathUtils.random();
            float f10 = MathUtils.random();
            return vector30.set((f3 - f) * f9 + f + (f6 - f) * f10, (f4 - f1) * f9 + f1 + (f7 - f1) * f10, f9 * (f5 - f2) + f2 + f10 * (f8 - f2));
        }

        public Vector3 pick(Vector3 vector30) {
            float f = MathUtils.random();
            float f1 = MathUtils.random();
            return vector30.set((this.x2 - this.x1) * f + this.x1 + (this.x3 - this.x1) * f1, (this.y2 - this.y1) * f + this.y1 + (this.y3 - this.y1) * f1, f * (this.z2 - this.z1) + this.z1 + f1 * (this.z3 - this.z1));
        }
    }

    protected Mesh mesh;
    protected Model model;

    public MeshSpawnShapeValue() {
    }

    public MeshSpawnShapeValue(MeshSpawnShapeValue meshSpawnShapeValue0) {
        super(meshSpawnShapeValue0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        SaveData resourceData$SaveData0 = resourceData0.getSaveData();
        AssetDescriptor assetDescriptor0 = resourceData$SaveData0.loadAsset();
        if(assetDescriptor0 != null) {
            Model model0 = (Model)assetManager0.get(assetDescriptor0);
            int v = (int)(((Integer)resourceData$SaveData0.load("index")));
            this.setMesh(((Mesh)model0.meshes.get(v)), model0);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void load(ParticleValue particleValue0) {
        super.load(particleValue0);
        this.setMesh(((MeshSpawnShapeValue)particleValue0).mesh, ((MeshSpawnShapeValue)particleValue0).model);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        if(this.model != null) {
            SaveData resourceData$SaveData0 = resourceData0.createSaveData();
            resourceData$SaveData0.saveAsset(assetManager0.getAssetFileName(this.model), Model.class);
            resourceData$SaveData0.save("index", this.model.meshes.indexOf(this.mesh, true));
        }
    }

    public void setMesh(Mesh mesh0) {
        this.setMesh(mesh0, null);
    }

    public void setMesh(Mesh mesh0, Model model0) {
        if(mesh0.getVertexAttribute(1) == null) {
            throw new GdxRuntimeException("Mesh vertices must have Usage.Position");
        }
        this.model = model0;
        this.mesh = mesh0;
    }
}

