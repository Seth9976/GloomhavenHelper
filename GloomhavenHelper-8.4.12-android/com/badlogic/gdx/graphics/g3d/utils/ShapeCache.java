package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;

public class ShapeCache implements RenderableProvider, Disposable {
    private final MeshBuilder builder;
    private boolean building;
    private final String id;
    private final Mesh mesh;
    private final Renderable renderable;

    public ShapeCache() {
        this(5000, 5000, new VertexAttributes(new VertexAttribute[]{new VertexAttribute(1, 3, "a_position"), new VertexAttribute(4, 4, "a_color")}), 1);
    }

    public ShapeCache(int v, int v1, VertexAttributes vertexAttributes0, int v2) {
        this.id = "id";
        this.renderable = new Renderable();
        this.mesh = new Mesh(false, v, v1, vertexAttributes0);
        this.builder = new MeshBuilder();
        this.renderable.meshPart.mesh = this.mesh;
        this.renderable.meshPart.primitiveType = v2;
        this.renderable.material = new Material();
    }

    public MeshPartBuilder begin() {
        return this.begin(1);
    }

    public MeshPartBuilder begin(int v) {
        if(this.building) {
            throw new GdxRuntimeException("Call end() after calling begin()");
        }
        this.building = true;
        VertexAttributes vertexAttributes0 = this.mesh.getVertexAttributes();
        this.builder.begin(vertexAttributes0);
        this.builder.part("id", v, this.renderable.meshPart);
        return this.builder;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.mesh.dispose();
    }

    public void end() {
        if(!this.building) {
            throw new GdxRuntimeException("Call begin() prior to calling end()");
        }
        this.building = false;
        this.builder.end(this.mesh);
    }

    public Material getMaterial() {
        return this.renderable.material;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array array0, Pool pool0) {
        array0.add(this.renderable);
    }

    public Matrix4 getWorldTransform() {
        return this.renderable.worldTransform;
    }
}

