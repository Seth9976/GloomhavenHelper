package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class MeshPart {
    private static final BoundingBox bounds;
    public final Vector3 center;
    public final Vector3 halfExtents;
    public String id;
    public Mesh mesh;
    public int offset;
    public int primitiveType;
    public float radius;
    public int size;

    static {
        MeshPart.bounds = new BoundingBox();
    }

    public MeshPart() {
        this.center = new Vector3();
        this.halfExtents = new Vector3();
        this.radius = -1.0f;
    }

    public MeshPart(MeshPart meshPart0) {
        this.center = new Vector3();
        this.halfExtents = new Vector3();
        this.radius = -1.0f;
        this.set(meshPart0);
    }

    public MeshPart(String s, Mesh mesh0, int v, int v1, int v2) {
        this.center = new Vector3();
        this.halfExtents = new Vector3();
        this.radius = -1.0f;
        this.set(s, mesh0, v, v1, v2);
    }

    public boolean equals(MeshPart meshPart0) {
        return meshPart0 == this || meshPart0 != null && meshPart0.mesh == this.mesh && meshPart0.primitiveType == this.primitiveType && meshPart0.offset == this.offset && meshPart0.size == this.size;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        return object0 instanceof MeshPart ? this.equals(((MeshPart)object0)) : false;
    }

    public void render(ShaderProgram shaderProgram0) {
        this.mesh.render(shaderProgram0, this.primitiveType, this.offset, this.size);
    }

    public void render(ShaderProgram shaderProgram0, boolean z) {
        this.mesh.render(shaderProgram0, this.primitiveType, this.offset, this.size, z);
    }

    public MeshPart set(MeshPart meshPart0) {
        this.id = meshPart0.id;
        this.mesh = meshPart0.mesh;
        this.offset = meshPart0.offset;
        this.size = meshPart0.size;
        this.primitiveType = meshPart0.primitiveType;
        this.center.set(meshPart0.center);
        this.halfExtents.set(meshPart0.halfExtents);
        this.radius = meshPart0.radius;
        return this;
    }

    public MeshPart set(String s, Mesh mesh0, int v, int v1, int v2) {
        this.id = s;
        this.mesh = mesh0;
        this.offset = v;
        this.size = v1;
        this.primitiveType = v2;
        this.center.set(0.0f, 0.0f, 0.0f);
        this.halfExtents.set(0.0f, 0.0f, 0.0f);
        this.radius = -1.0f;
        return this;
    }

    public void update() {
        this.mesh.calculateBoundingBox(MeshPart.bounds, this.offset, this.size);
        MeshPart.bounds.getCenter(this.center);
        MeshPart.bounds.getDimensions(this.halfExtents).scl(0.5f);
        this.radius = this.halfExtents.len();
    }
}

