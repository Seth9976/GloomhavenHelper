package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.math.Matrix4;

public class Renderable {
    public Matrix4[] bones;
    public Environment environment;
    public Material material;
    public final MeshPart meshPart;
    public Shader shader;
    public Object userData;
    public final Matrix4 worldTransform;

    public Renderable() {
        this.worldTransform = new Matrix4();
        this.meshPart = new MeshPart();
    }

    public Renderable set(Renderable renderable0) {
        this.worldTransform.set(renderable0.worldTransform);
        this.material = renderable0.material;
        this.meshPart.set(renderable0.meshPart);
        this.bones = renderable0.bones;
        this.environment = renderable0.environment;
        this.shader = renderable0.shader;
        this.userData = renderable0.userData;
        return this;
    }
}

