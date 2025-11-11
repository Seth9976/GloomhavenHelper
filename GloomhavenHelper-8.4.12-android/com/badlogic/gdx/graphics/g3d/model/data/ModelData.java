package com.badlogic.gdx.graphics.g3d.model.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ModelData {
    public final Array animations;
    public String id;
    public final Array materials;
    public final Array meshes;
    public final Array nodes;
    public final short[] version;

    public ModelData() {
        this.version = new short[2];
        this.meshes = new Array();
        this.materials = new Array();
        this.nodes = new Array();
        this.animations = new Array();
    }

    public void addMesh(ModelMesh modelMesh0) {
        for(Object object0: this.meshes) {
            ModelMesh modelMesh1 = (ModelMesh)object0;
            if(modelMesh1.id.equals(modelMesh0.id)) {
                throw new GdxRuntimeException("Mesh with id \'" + modelMesh1.id + "\' already in model");
            }
            if(false) {
                break;
            }
        }
        this.meshes.add(modelMesh0);
    }
}

