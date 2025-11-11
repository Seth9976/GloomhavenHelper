package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ArrayMap;

public class NodePart {
    public Matrix4[] bones;
    public boolean enabled;
    public ArrayMap invBoneBindTransforms;
    public Material material;
    public MeshPart meshPart;

    public NodePart() {
        this.enabled = true;
    }

    public NodePart(MeshPart meshPart0, Material material0) {
        this.enabled = true;
        this.meshPart = meshPart0;
        this.material = material0;
    }

    public NodePart copy() {
        return new NodePart().set(this);
    }

    protected NodePart set(NodePart nodePart0) {
        this.meshPart = new MeshPart(nodePart0.meshPart);
        this.material = nodePart0.material;
        this.enabled = nodePart0.enabled;
        ArrayMap arrayMap0 = nodePart0.invBoneBindTransforms;
        if(arrayMap0 == null) {
            this.invBoneBindTransforms = null;
            this.bones = null;
            return this;
        }
        ArrayMap arrayMap1 = this.invBoneBindTransforms;
        if(arrayMap1 == null) {
            this.invBoneBindTransforms = new ArrayMap(true, arrayMap0.size, Node.class, Matrix4.class);
        }
        else {
            arrayMap1.clear();
        }
        this.invBoneBindTransforms.putAll(nodePart0.invBoneBindTransforms);
        if(this.bones == null || this.bones.length != this.invBoneBindTransforms.size) {
            this.bones = new Matrix4[this.invBoneBindTransforms.size];
        }
        for(int v = 0; true; ++v) {
            Matrix4[] arr_matrix4 = this.bones;
            if(v >= arr_matrix4.length) {
                break;
            }
            if(arr_matrix4[v] == null) {
                arr_matrix4[v] = new Matrix4();
            }
        }
        return this;
    }

    public Renderable setRenderable(Renderable renderable0) {
        renderable0.material = this.material;
        renderable0.meshPart.set(this.meshPart);
        renderable0.bones = this.bones;
        return renderable0;
    }
}

