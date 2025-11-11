package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FlushablePool;

public class RenderableShapeBuilder extends BaseShapeBuilder {
    static class RenderablePool extends FlushablePool {
        protected Renderable newObject() {
            return new Renderable();
        }

        @Override  // com.badlogic.gdx.utils.Pool
        protected Object newObject() {
            return this.newObject();
        }

        public Renderable obtain() {
            Renderable renderable0 = (Renderable)super.obtain();
            renderable0.environment = null;
            renderable0.material = null;
            renderable0.meshPart.set("", null, 0, 0, 0);
            renderable0.shader = null;
            renderable0.userData = null;
            return renderable0;
        }

        @Override  // com.badlogic.gdx.utils.FlushablePool
        public Object obtain() {
            return this.obtain();
        }
    }

    private static final int FLOAT_BYTES = 4;
    private static short[] indices;
    private static final Array renderables;
    private static final RenderablePool renderablesPool;
    private static float[] vertices;

    static {
        RenderableShapeBuilder.renderablesPool = new RenderablePool();
        RenderableShapeBuilder.renderables = new Array();
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder0, Renderable renderable0, float f, Color color0, Color color1, Color color2) {
        int v6;
        int v5;
        Mesh mesh0 = renderable0.meshPart.mesh;
        int v = mesh0.getVertexAttribute(1) == null ? -1 : mesh0.getVertexAttribute(1).offset / 4;
        int v1 = mesh0.getVertexAttribute(8) == null ? -1 : mesh0.getVertexAttribute(8).offset / 4;
        int v2 = mesh0.getVertexAttribute(0x80) == null ? -1 : mesh0.getVertexAttribute(0x80).offset / 4;
        int v3 = mesh0.getVertexAttribute(0x100) == null ? -1 : mesh0.getVertexAttribute(0x100).offset / 4;
        int v4 = mesh0.getVertexSize();
        if(mesh0.getNumIndices() > 0) {
            RenderableShapeBuilder.ensureIndicesCapacity(mesh0.getNumIndices());
            mesh0.getIndices(renderable0.meshPart.offset, renderable0.meshPart.size, RenderableShapeBuilder.indices, 0);
            v5 = RenderableShapeBuilder.minVerticeInIndices();
            v6 = RenderableShapeBuilder.maxVerticeInIndices() - v5;
        }
        else {
            v5 = renderable0.meshPart.offset;
            v6 = renderable0.meshPart.size;
        }
        int v7 = v6 * (v4 / 4);
        RenderableShapeBuilder.ensureVerticesCapacity(v7);
        mesh0.getVertices(v5 * (v4 / 4), v7, RenderableShapeBuilder.vertices, 0);
        while(v5 < v6) {
            int v8 = v5 * (v4 / 4);
            int v9 = v8 + v;
            RenderableShapeBuilder.tmpV0.set(RenderableShapeBuilder.vertices[v9], RenderableShapeBuilder.vertices[v9 + 1], RenderableShapeBuilder.vertices[v9 + 2]);
            if(v1 != -1) {
                int v10 = v8 + v1;
                RenderableShapeBuilder.tmpV1.set(RenderableShapeBuilder.vertices[v10], RenderableShapeBuilder.vertices[v10 + 1], RenderableShapeBuilder.vertices[v10 + 2]);
                RenderableShapeBuilder.tmpV2.set(RenderableShapeBuilder.tmpV0).add(RenderableShapeBuilder.tmpV1.scl(f));
            }
            if(v2 != -1) {
                int v11 = v8 + v2;
                RenderableShapeBuilder.tmpV3.set(RenderableShapeBuilder.vertices[v11], RenderableShapeBuilder.vertices[v11 + 1], RenderableShapeBuilder.vertices[v11 + 2]);
                RenderableShapeBuilder.tmpV4.set(RenderableShapeBuilder.tmpV0).add(RenderableShapeBuilder.tmpV3.scl(f));
            }
            if(v3 != -1) {
                int v12 = v8 + v3;
                RenderableShapeBuilder.tmpV5.set(RenderableShapeBuilder.vertices[v12], RenderableShapeBuilder.vertices[v12 + 1], RenderableShapeBuilder.vertices[v12 + 2]);
                RenderableShapeBuilder.tmpV6.set(RenderableShapeBuilder.tmpV0).add(RenderableShapeBuilder.tmpV5.scl(f));
            }
            RenderableShapeBuilder.tmpV0.mul(renderable0.worldTransform);
            RenderableShapeBuilder.tmpV2.mul(renderable0.worldTransform);
            RenderableShapeBuilder.tmpV4.mul(renderable0.worldTransform);
            RenderableShapeBuilder.tmpV6.mul(renderable0.worldTransform);
            if(v1 != -1) {
                meshPartBuilder0.setColor(color0);
                meshPartBuilder0.line(RenderableShapeBuilder.tmpV0, RenderableShapeBuilder.tmpV2);
            }
            if(v2 != -1) {
                meshPartBuilder0.setColor(color1);
                meshPartBuilder0.line(RenderableShapeBuilder.tmpV0, RenderableShapeBuilder.tmpV4);
            }
            if(v3 != -1) {
                meshPartBuilder0.setColor(color2);
                meshPartBuilder0.line(RenderableShapeBuilder.tmpV0, RenderableShapeBuilder.tmpV6);
            }
            ++v5;
        }
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder0, RenderableProvider renderableProvider0, float f) {
        RenderableShapeBuilder.buildNormals(meshPartBuilder0, renderableProvider0, f, RenderableShapeBuilder.tmpColor0.set(0.0f, 0.0f, 1.0f, 1.0f), RenderableShapeBuilder.tmpColor1.set(1.0f, 0.0f, 0.0f, 1.0f), RenderableShapeBuilder.tmpColor2.set(0.0f, 1.0f, 0.0f, 1.0f));
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder0, RenderableProvider renderableProvider0, float f, Color color0, Color color1, Color color2) {
        renderableProvider0.getRenderables(RenderableShapeBuilder.renderables, RenderableShapeBuilder.renderablesPool);
        for(Object object0: RenderableShapeBuilder.renderables) {
            RenderableShapeBuilder.buildNormals(meshPartBuilder0, ((Renderable)object0), f, color0, color1, color2);
        }
        RenderableShapeBuilder.renderablesPool.flush();
        RenderableShapeBuilder.renderables.clear();
    }

    private static void ensureIndicesCapacity(int v) {
        if(RenderableShapeBuilder.indices == null || RenderableShapeBuilder.indices.length < v) {
            RenderableShapeBuilder.indices = new short[v];
        }
    }

    private static void ensureVerticesCapacity(int v) {
        if(RenderableShapeBuilder.vertices == null || RenderableShapeBuilder.vertices.length < v) {
            RenderableShapeBuilder.vertices = new float[v];
        }
    }

    private static short maxVerticeInIndices() {
        short v = (short)0x8000;
        for(int v1 = 0; true; ++v1) {
            short[] arr_v = RenderableShapeBuilder.indices;
            if(v1 >= arr_v.length) {
                break;
            }
            if(arr_v[v1] > v) {
                v = arr_v[v1];
            }
        }
        return v;
    }

    private static short minVerticeInIndices() {
        short v = 0x7FFF;
        for(int v1 = 0; true; ++v1) {
            short[] arr_v = RenderableShapeBuilder.indices;
            if(v1 >= arr_v.length) {
                break;
            }
            if(arr_v[v1] < v) {
                v = arr_v[v1];
            }
        }
        return v;
    }
}

