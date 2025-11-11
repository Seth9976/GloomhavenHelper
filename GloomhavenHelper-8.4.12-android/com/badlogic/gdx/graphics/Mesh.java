package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.IndexArray;
import com.badlogic.gdx.graphics.glutils.IndexBufferObject;
import com.badlogic.gdx.graphics.glutils.IndexBufferObjectSubData;
import com.badlogic.gdx.graphics.glutils.IndexData;
import com.badlogic.gdx.graphics.glutils.InstanceBufferObject;
import com.badlogic.gdx.graphics.glutils.InstanceData;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.VertexArray;
import com.badlogic.gdx.graphics.glutils.VertexBufferObject;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectSubData;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectWithVAO;
import com.badlogic.gdx.graphics.glutils.VertexData;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.HashMap;
import java.util.Map;

public class Mesh implements Disposable {
    public static enum VertexDataType {
        VertexArray,
        VertexBufferObject,
        VertexBufferObjectSubData,
        VertexBufferObjectWithVAO;

    }

    boolean autoBind;
    final IndexData indices;
    InstanceData instances;
    boolean isInstanced;
    final boolean isVertexArray;
    static final Map meshes;
    private final Vector3 tmpV;
    final VertexData vertices;

    static {
        Mesh.meshes = new HashMap();
    }

    public Mesh(VertexDataType mesh$VertexDataType0, boolean z, int v, int v1, VertexAttributes vertexAttributes0) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        switch(com.badlogic.gdx.graphics.Mesh.1.$SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[mesh$VertexDataType0.ordinal()]) {
            case 1: {
                this.vertices = new VertexBufferObject(z, v, vertexAttributes0);
                this.indices = new IndexBufferObject(z, v1);
                this.isVertexArray = false;
                break;
            }
            case 2: {
                this.vertices = new VertexBufferObjectSubData(z, v, vertexAttributes0);
                this.indices = new IndexBufferObjectSubData(z, v1);
                this.isVertexArray = false;
                break;
            }
            case 3: {
                this.vertices = new VertexBufferObjectWithVAO(z, v, vertexAttributes0);
                this.indices = new IndexBufferObjectSubData(z, v1);
                this.isVertexArray = false;
                break;
            }
            default: {
                this.vertices = new VertexArray(v, vertexAttributes0);
                this.indices = new IndexArray(v1);
                this.isVertexArray = true;
            }
        }
        Mesh.addManagedMesh(Gdx.app, this);
    }

    public Mesh(VertexDataType mesh$VertexDataType0, boolean z, int v, int v1, VertexAttribute[] arr_vertexAttribute) {
        this(mesh$VertexDataType0, z, v, v1, new VertexAttributes(arr_vertexAttribute));
    }

    protected Mesh(VertexData vertexData0, IndexData indexData0, boolean z) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        this.vertices = vertexData0;
        this.indices = indexData0;
        this.isVertexArray = z;
        Mesh.addManagedMesh(Gdx.app, this);
    }

    public Mesh(boolean z, int v, int v1, VertexAttributes vertexAttributes0) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        this.vertices = this.makeVertexBuffer(z, v, vertexAttributes0);
        this.indices = new IndexBufferObject(z, v1);
        this.isVertexArray = false;
        Mesh.addManagedMesh(Gdx.app, this);
    }

    public Mesh(boolean z, int v, int v1, VertexAttribute[] arr_vertexAttribute) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        this.vertices = this.makeVertexBuffer(z, v, new VertexAttributes(arr_vertexAttribute));
        this.indices = new IndexBufferObject(z, v1);
        this.isVertexArray = false;
        Mesh.addManagedMesh(Gdx.app, this);
    }

    public Mesh(boolean z, boolean z1, int v, int v1, VertexAttributes vertexAttributes0) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        this.vertices = this.makeVertexBuffer(z, v, vertexAttributes0);
        this.indices = new IndexBufferObject(z1, v1);
        this.isVertexArray = false;
        Mesh.addManagedMesh(Gdx.app, this);
    }

    private static void addManagedMesh(Application application0, Mesh mesh0) {
        Array array0 = (Array)Mesh.meshes.get(application0);
        if(array0 == null) {
            array0 = new Array();
        }
        array0.add(mesh0);
        Mesh.meshes.put(application0, array0);
    }

    public void bind(ShaderProgram shaderProgram0) {
        this.bind(shaderProgram0, null);
    }

    public void bind(ShaderProgram shaderProgram0, int[] arr_v) {
        this.vertices.bind(shaderProgram0, arr_v);
        if(this.instances != null && this.instances.getNumInstances() > 0) {
            this.instances.bind(shaderProgram0, arr_v);
        }
        if(this.indices.getNumIndices() > 0) {
            this.indices.bind();
        }
    }

    public BoundingBox calculateBoundingBox() {
        BoundingBox boundingBox0 = new BoundingBox();
        this.calculateBoundingBox(boundingBox0);
        return boundingBox0;
    }

    public BoundingBox calculateBoundingBox(BoundingBox boundingBox0, int v, int v1) {
        return this.extendBoundingBox(boundingBox0.inf(), v, v1);
    }

    public BoundingBox calculateBoundingBox(BoundingBox boundingBox0, int v, int v1, Matrix4 matrix40) {
        return this.extendBoundingBox(boundingBox0.inf(), v, v1, matrix40);
    }

    public void calculateBoundingBox(BoundingBox boundingBox0) {
        int v = this.getNumVertices();
        if(v == 0) {
            throw new GdxRuntimeException("No vertices defined");
        }
        FloatBuffer floatBuffer0 = this.vertices.getBuffer();
        boundingBox0.inf();
        VertexAttribute vertexAttribute0 = this.getVertexAttribute(1);
        int v1 = vertexAttribute0.offset / 4;
        int v2 = this.vertices.getAttributes().vertexSize / 4;
        int v3 = 0;
        switch(vertexAttribute0.numComponents) {
            case 1: {
                while(v3 < v) {
                    boundingBox0.ext(floatBuffer0.get(v1), 0.0f, 0.0f);
                    v1 += v2;
                    ++v3;
                }
                return;
            }
            case 2: {
                while(v3 < v) {
                    boundingBox0.ext(floatBuffer0.get(v1), floatBuffer0.get(v1 + 1), 0.0f);
                    v1 += v2;
                    ++v3;
                }
                return;
            }
            case 3: {
                break;
            }
            default: {
                return;
            }
        }
        while(v3 < v) {
            boundingBox0.ext(floatBuffer0.get(v1), floatBuffer0.get(v1 + 1), floatBuffer0.get(v1 + 2));
            v1 += v2;
            ++v3;
        }
    }

    public float calculateRadius(float f, float f1, float f2) {
        return this.calculateRadius(f, f1, f2, 0, this.getNumIndices(), null);
    }

    public float calculateRadius(float f, float f1, float f2, int v, int v1) {
        return this.calculateRadius(f, f1, f2, v, v1, null);
    }

    public float calculateRadius(float f, float f1, float f2, int v, int v1, Matrix4 matrix40) {
        return (float)Math.sqrt(this.calculateRadiusSquared(f, f1, f2, v, v1, matrix40));
    }

    public float calculateRadius(Vector3 vector30) {
        return this.calculateRadius(vector30.x, vector30.y, vector30.z, 0, this.getNumIndices(), null);
    }

    public float calculateRadius(Vector3 vector30, int v, int v1) {
        return this.calculateRadius(vector30.x, vector30.y, vector30.z, v, v1, null);
    }

    public float calculateRadius(Vector3 vector30, int v, int v1, Matrix4 matrix40) {
        return this.calculateRadius(vector30.x, vector30.y, vector30.z, v, v1, matrix40);
    }

    public float calculateRadiusSquared(float f, float f1, float f2, int v, int v1, Matrix4 matrix40) {
        float f3;
        int v2 = this.getNumIndices();
        if(v >= 0 && v1 >= 1) {
            int v3 = v + v1;
            if(v3 <= v2) {
                FloatBuffer floatBuffer0 = this.vertices.getBuffer();
                ShortBuffer shortBuffer0 = this.indices.getBuffer();
                VertexAttribute vertexAttribute0 = this.getVertexAttribute(1);
                int v4 = vertexAttribute0.offset / 4;
                int v5 = this.vertices.getAttributes().vertexSize / 4;
                switch(vertexAttribute0.numComponents) {
                    case 1: {
                        int v6 = v;
                        f3 = 0.0f;
                        while(v6 < v3) {
                            float f4 = floatBuffer0.get((shortBuffer0.get(v6) & 0xFFFF) * v5 + v4);
                            this.tmpV.set(f4, 0.0f, 0.0f);
                            if(matrix40 != null) {
                                this.tmpV.mul(matrix40);
                            }
                            float f5 = this.tmpV.sub(f, f1, f2).len2();
                            if(f5 > f3) {
                                f3 = f5;
                            }
                            ++v6;
                        }
                        return f3;
                    }
                    case 2: {
                        int v7 = v;
                        float f6 = 0.0f;
                        while(v7 < v3) {
                            int v8 = (shortBuffer0.get(v7) & 0xFFFF) * v5 + v4;
                            float f7 = floatBuffer0.get(v8);
                            float f8 = floatBuffer0.get(v8 + 1);
                            this.tmpV.set(f7, f8, 0.0f);
                            if(matrix40 != null) {
                                this.tmpV.mul(matrix40);
                            }
                            float f9 = this.tmpV.sub(f, f1, f2).len2();
                            if(f9 > f6) {
                                f6 = f9;
                            }
                            ++v7;
                        }
                        return f6;
                    }
                    case 3: {
                        int v9 = v;
                        f3 = 0.0f;
                        while(v9 < v3) {
                            int v10 = (shortBuffer0.get(v9) & 0xFFFF) * v5 + v4;
                            float f10 = floatBuffer0.get(v10);
                            float f11 = floatBuffer0.get(v10 + 1);
                            float f12 = floatBuffer0.get(v10 + 2);
                            this.tmpV.set(f10, f11, f12);
                            if(matrix40 != null) {
                                this.tmpV.mul(matrix40);
                            }
                            float f13 = this.tmpV.sub(f, f1, f2).len2();
                            if(f13 > f3) {
                                f3 = f13;
                            }
                            ++v9;
                        }
                        return f3;
                    }
                    default: {
                        return 0.0f;
                    }
                }
            }
        }
        throw new GdxRuntimeException("Not enough indices");
    }

    public static void clearAllMeshes(Application application0) {
        Mesh.meshes.remove(application0);
    }

    public Mesh copy(boolean z) {
        return this.copy(z, false, null);
    }

    public Mesh copy(boolean z, boolean z1, int[] arr_v) {
        short v18;
        int v7;
        short[] arr_v2;
        VertexAttribute[] arr_vertexAttribute;
        int v = this.getVertexSize();
        int v1 = this.getNumVertices();
        float[] arr_f = new float[v1 * (v / 4)];
        this.getVertices(0, arr_f.length, arr_f);
        short[] arr_v1 = null;
        if(arr_v == null) {
        label_40:
            arr_vertexAttribute = null;
            arr_v2 = null;
            v7 = 0;
        }
        else {
            int v4 = 0;
            int v5 = 0;
            for(int v3 = 0; v3 < arr_v.length; ++v3) {
                if(this.getVertexAttribute(arr_v[v3]) != null) {
                    v4 += this.getVertexAttribute(arr_v[v3]).numComponents;
                    ++v5;
                }
            }
            if(v4 > 0) {
                arr_vertexAttribute = new VertexAttribute[v5];
                arr_v2 = new short[v4];
                v7 = 0;
                int v8 = -1;
                int v9 = -1;
                for(int v6 = 0; v6 < arr_v.length; ++v6) {
                    VertexAttribute vertexAttribute0 = this.getVertexAttribute(arr_v[v6]);
                    if(vertexAttribute0 != null) {
                        int v10 = v8;
                        for(int v11 = 0; v11 < vertexAttribute0.numComponents; ++v11) {
                            ++v10;
                            arr_v2[v10] = (short)(vertexAttribute0.offset + v11);
                        }
                        ++v9;
                        arr_vertexAttribute[v9] = vertexAttribute0.copy();
                        v7 += vertexAttribute0.numComponents;
                        v8 = v10;
                    }
                }
                goto label_43;
            }
            goto label_40;
        }
    label_43:
        if(arr_v2 == null) {
            arr_v2 = new short[v / 4];
            for(short v12 = 0; v12 < v / 4; v12 = (short)(v12 + 1)) {
                arr_v2[v12] = v12;
            }
            v7 = v / 4;
        }
        int v13 = this.getNumIndices();
        if(v13 > 0) {
            arr_v1 = new short[v13];
            this.getIndices(arr_v1);
            if(z1 || v7 != v / 4) {
                float[] arr_f1 = new float[arr_f.length];
                int v14 = 0;
                int v15 = 0;
                for(int v2 = 0; v14 < v13; v2 = 0) {
                    int v16 = arr_v1[v14] * (v / 4);
                    if(z1) {
                        short v17 = 0;
                        v18 = -1;
                        while(v17 < v15 && v18 < 0) {
                            int v19 = v17 * v7;
                            boolean z2 = true;
                            while(v2 < arr_v2.length && z2) {
                                if(arr_f1[v19 + v2] != arr_f[v16 + arr_v2[v2]]) {
                                    z2 = false;
                                }
                                ++v2;
                            }
                            if(z2) {
                                v18 = v17;
                            }
                            v17 = (short)(v17 + 1);
                            v2 = 0;
                        }
                    }
                    else {
                        v18 = -1;
                    }
                    if(v18 > 0) {
                        arr_v1[v14] = v18;
                    }
                    else {
                        int v20 = v15 * v7;
                        for(int v21 = 0; v21 < arr_v2.length; ++v21) {
                            arr_f1[v20 + v21] = arr_f[arr_v2[v21] + v16];
                        }
                        arr_v1[v14] = (short)v15;
                        ++v15;
                    }
                    ++v14;
                }
                arr_f = arr_f1;
                v1 = v15;
            }
        }
        Mesh mesh0 = arr_vertexAttribute == null ? new Mesh(z, v1, (arr_v1 == null ? 0 : arr_v1.length), this.getVertexAttributes()) : new Mesh(z, v1, (arr_v1 == null ? 0 : arr_v1.length), arr_vertexAttribute);
        mesh0.setVertices(arr_f, 0, v1 * v7);
        if(arr_v1 != null) {
            mesh0.setIndices(arr_v1);
        }
        return mesh0;
    }

    public Mesh disableInstancedRendering() {
        if(this.isInstanced) {
            this.isInstanced = false;
            this.instances.dispose();
            this.instances = null;
        }
        return this;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if(Mesh.meshes.get(Gdx.app) != null) {
            ((Array)Mesh.meshes.get(Gdx.app)).removeValue(this, true);
        }
        this.vertices.dispose();
        InstanceData instanceData0 = this.instances;
        if(instanceData0 != null) {
            instanceData0.dispose();
        }
        this.indices.dispose();
    }

    public Mesh enableInstancedRendering(boolean z, int v, VertexAttribute[] arr_vertexAttribute) {
        if(this.isInstanced) {
            throw new GdxRuntimeException("Trying to enable InstancedRendering on same Mesh instance twice. Use disableInstancedRendering to clean up old InstanceData first");
        }
        this.isInstanced = true;
        this.instances = new InstanceBufferObject(z, v, arr_vertexAttribute);
        return this;
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox0, int v, int v1) {
        return this.extendBoundingBox(boundingBox0, v, v1, null);
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox0, int v, int v1, Matrix4 matrix40) {
        int v2 = this.getNumIndices();
        int v3 = this.getNumVertices();
        if(v2 != 0) {
            v3 = v2;
        }
        if(v >= 0 && v1 >= 1) {
            int v4 = v + v1;
            if(v4 <= v3) {
                FloatBuffer floatBuffer0 = this.vertices.getBuffer();
                ShortBuffer shortBuffer0 = this.indices.getBuffer();
                VertexAttribute vertexAttribute0 = this.getVertexAttribute(1);
                int v5 = vertexAttribute0.offset / 4;
                int v6 = this.vertices.getAttributes().vertexSize / 4;
                switch(vertexAttribute0.numComponents) {
                    case 1: {
                        if(v2 > 0) {
                            while(v < v4) {
                                float f = floatBuffer0.get((shortBuffer0.get(v) & 0xFFFF) * v6 + v5);
                                this.tmpV.set(f, 0.0f, 0.0f);
                                if(matrix40 != null) {
                                    this.tmpV.mul(matrix40);
                                }
                                boundingBox0.ext(this.tmpV);
                                ++v;
                            }
                            return boundingBox0;
                        }
                        while(v < v4) {
                            float f1 = floatBuffer0.get(v * v6 + v5);
                            this.tmpV.set(f1, 0.0f, 0.0f);
                            if(matrix40 != null) {
                                this.tmpV.mul(matrix40);
                            }
                            boundingBox0.ext(this.tmpV);
                            ++v;
                        }
                        return boundingBox0;
                    }
                    case 2: {
                        if(v2 > 0) {
                            while(v < v4) {
                                int v7 = (shortBuffer0.get(v) & 0xFFFF) * v6 + v5;
                                float f2 = floatBuffer0.get(v7);
                                float f3 = floatBuffer0.get(v7 + 1);
                                this.tmpV.set(f2, f3, 0.0f);
                                if(matrix40 != null) {
                                    this.tmpV.mul(matrix40);
                                }
                                boundingBox0.ext(this.tmpV);
                                ++v;
                            }
                            return boundingBox0;
                        }
                        while(v < v4) {
                            int v8 = v * v6 + v5;
                            float f4 = floatBuffer0.get(v8);
                            float f5 = floatBuffer0.get(v8 + 1);
                            this.tmpV.set(f4, f5, 0.0f);
                            if(matrix40 != null) {
                                this.tmpV.mul(matrix40);
                            }
                            boundingBox0.ext(this.tmpV);
                            ++v;
                        }
                        return boundingBox0;
                    }
                    case 3: {
                        if(v2 > 0) {
                            while(v < v4) {
                                int v9 = (shortBuffer0.get(v) & 0xFFFF) * v6 + v5;
                                float f6 = floatBuffer0.get(v9);
                                float f7 = floatBuffer0.get(v9 + 1);
                                float f8 = floatBuffer0.get(v9 + 2);
                                this.tmpV.set(f6, f7, f8);
                                if(matrix40 != null) {
                                    this.tmpV.mul(matrix40);
                                }
                                boundingBox0.ext(this.tmpV);
                                ++v;
                            }
                            return boundingBox0;
                        }
                        while(v < v4) {
                            int v10 = v * v6 + v5;
                            float f9 = floatBuffer0.get(v10);
                            float f10 = floatBuffer0.get(v10 + 1);
                            float f11 = floatBuffer0.get(v10 + 2);
                            this.tmpV.set(f9, f10, f11);
                            if(matrix40 != null) {
                                this.tmpV.mul(matrix40);
                            }
                            boundingBox0.ext(this.tmpV);
                            ++v;
                        }
                        return boundingBox0;
                    }
                    default: {
                        return boundingBox0;
                    }
                }
            }
        }
        throw new GdxRuntimeException("Invalid part specified ( offset=" + v + ", count=" + v1 + ", max=" + v3 + " )");
    }

    public void getIndices(int v, int v1, short[] arr_v, int v2) {
        int v3 = this.getNumIndices();
        if(v1 < 0) {
            v1 = v3 - v;
        }
        if(v < 0 || v >= v3 || v + v1 > v3) {
            throw new IllegalArgumentException("Invalid range specified, offset: " + v + ", count: " + v1 + ", max: " + v3);
        }
        if(arr_v.length - v2 < v1) {
            throw new IllegalArgumentException("not enough room in indices array, has " + arr_v.length + " shorts, needs " + v1);
        }
        int v4 = this.getIndicesBuffer().position();
        this.getIndicesBuffer().position(v);
        this.getIndicesBuffer().get(arr_v, v2, v1);
        this.getIndicesBuffer().position(v4);
    }

    public void getIndices(int v, short[] arr_v, int v1) {
        this.getIndices(v, -1, arr_v, v1);
    }

    public void getIndices(short[] arr_v) {
        this.getIndices(arr_v, 0);
    }

    public void getIndices(short[] arr_v, int v) {
        this.getIndices(0, arr_v, v);
    }

    public ShortBuffer getIndicesBuffer() {
        return this.indices.getBuffer();
    }

    public static String getManagedStatus() [...] // 潜在的解密器

    public int getMaxIndices() {
        return this.indices.getNumMaxIndices();
    }

    public int getMaxVertices() {
        return this.vertices.getNumMaxVertices();
    }

    public int getNumIndices() {
        return this.indices.getNumIndices();
    }

    public int getNumVertices() {
        return this.vertices.getNumVertices();
    }

    public VertexAttribute getVertexAttribute(int v) {
        VertexAttributes vertexAttributes0 = this.vertices.getAttributes();
        int v1 = vertexAttributes0.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            if(vertexAttributes0.get(v2).usage == v) {
                return vertexAttributes0.get(v2);
            }
        }
        return null;
    }

    public VertexAttributes getVertexAttributes() {
        return this.vertices.getAttributes();
    }

    public int getVertexSize() {
        return this.vertices.getAttributes().vertexSize;
    }

    public float[] getVertices(int v, int v1, float[] arr_f) {
        return this.getVertices(v, v1, arr_f, 0);
    }

    public float[] getVertices(int v, int v1, float[] arr_f, int v2) {
        int v3 = this.getNumVertices() * this.getVertexSize() / 4;
        if(v1 == -1) {
            v1 = v3 - v;
            if(v1 > arr_f.length - v2) {
                v1 = arr_f.length - v2;
            }
        }
        if(v < 0 || v1 <= 0 || v + v1 > v3 || v2 < 0 || v2 >= arr_f.length) {
            throw new IndexOutOfBoundsException();
        }
        if(arr_f.length - v2 < v1) {
            throw new IllegalArgumentException("not enough room in vertices array, has " + arr_f.length + " floats, needs " + v1);
        }
        int v4 = this.getVerticesBuffer().position();
        this.getVerticesBuffer().position(v);
        this.getVerticesBuffer().get(arr_f, v2, v1);
        this.getVerticesBuffer().position(v4);
        return arr_f;
    }

    public float[] getVertices(int v, float[] arr_f) {
        return this.getVertices(v, -1, arr_f);
    }

    public float[] getVertices(float[] arr_f) {
        return this.getVertices(0, -1, arr_f);
    }

    public FloatBuffer getVerticesBuffer() {
        return this.vertices.getBuffer();
    }

    public static void invalidateAllMeshes(Application application0) {
        Array array0 = (Array)Mesh.meshes.get(application0);
        if(array0 == null) {
            return;
        }
        for(int v = 0; v < array0.size; ++v) {
            ((Mesh)array0.get(v)).vertices.invalidate();
            ((Mesh)array0.get(v)).indices.invalidate();
        }
    }

    public boolean isInstanced() {
        return this.isInstanced;
    }

    private VertexData makeVertexBuffer(boolean z, int v, VertexAttributes vertexAttributes0) {
        return Gdx.gl30 != null ? new VertexBufferObjectWithVAO(z, v, vertexAttributes0) : new VertexBufferObject(z, v, vertexAttributes0);
    }

    public void render(ShaderProgram shaderProgram0, int v) {
        this.render(shaderProgram0, v, 0, (this.indices.getNumMaxIndices() <= 0 ? this.getNumVertices() : this.getNumIndices()), this.autoBind);
    }

    public void render(ShaderProgram shaderProgram0, int v, int v1, int v2) {
        this.render(shaderProgram0, v, v1, v2, this.autoBind);
    }

    public void render(ShaderProgram shaderProgram0, int v, int v1, int v2, boolean z) {
        if(v2 == 0) {
            return;
        }
        if(z) {
            this.bind(shaderProgram0);
        }
        if(!this.isVertexArray) {
            int v3 = this.isInstanced ? this.instances.getNumInstances() : 0;
            if(this.indices.getNumIndices() > 0) {
                if(v2 + v1 > this.indices.getNumMaxIndices()) {
                    throw new GdxRuntimeException("Mesh attempting to access memory outside of the index buffer (count: " + v2 + ", offset: " + v1 + ", max: " + this.indices.getNumMaxIndices() + ")");
                }
                if(!this.isInstanced || v3 <= 0) {
                    Gdx.gl20.glDrawElements(v, v2, 0x1403, v1 * 2);
                }
                else {
                    Gdx.gl30.glDrawElementsInstanced(v, v2, 0x1403, v1 * 2, v3);
                }
            }
            else if(this.isInstanced && v3 > 0) {
                Gdx.gl30.glDrawArraysInstanced(v, v1, v2, v3);
            }
            else {
                Gdx.gl20.glDrawArrays(v, v1, v2);
            }
        }
        else if(this.indices.getNumIndices() > 0) {
            ShortBuffer shortBuffer0 = this.indices.getBuffer();
            shortBuffer0.position(v1);
            shortBuffer0.limit(v1 + v2);
            Gdx.gl20.glDrawElements(v, v2, 0x1403, shortBuffer0);
            shortBuffer0.position(shortBuffer0.position());
            shortBuffer0.limit(shortBuffer0.limit());
        }
        else {
            Gdx.gl20.glDrawArrays(v, v1, v2);
        }
        if(z) {
            this.unbind(shaderProgram0);
        }
    }

    public void scale(float f, float f1, float f2) {
        VertexAttribute vertexAttribute0 = this.getVertexAttribute(1);
        int v = vertexAttribute0.offset / 4;
        int v1 = this.getNumVertices();
        int v2 = this.getVertexSize();
        float[] arr_f = new float[v1 * (v2 / 4)];
        this.getVertices(arr_f);
        int v3 = 0;
        switch(vertexAttribute0.numComponents) {
            case 1: {
                while(v3 < v1) {
                    arr_f[v] *= f;
                    v += v2 / 4;
                    ++v3;
                }
                break;
            }
            case 2: {
                while(v3 < v1) {
                    arr_f[v] *= f;
                    arr_f[v + 1] *= f1;
                    v += v2 / 4;
                    ++v3;
                }
                break;
            }
            case 3: {
                while(v3 < v1) {
                    arr_f[v] *= f;
                    arr_f[v + 1] *= f1;
                    arr_f[v + 2] *= f2;
                    v += v2 / 4;
                    ++v3;
                }
            }
        }
        this.setVertices(arr_f);
    }

    public void setAutoBind(boolean z) {
        this.autoBind = z;
    }

    public Mesh setIndices(short[] arr_v) {
        this.indices.setIndices(arr_v, 0, arr_v.length);
        return this;
    }

    public Mesh setIndices(short[] arr_v, int v, int v1) {
        this.indices.setIndices(arr_v, v, v1);
        return this;
    }

    public Mesh setInstanceData(FloatBuffer floatBuffer0) {
        InstanceData instanceData0 = this.instances;
        if(instanceData0 == null) {
            throw new GdxRuntimeException("An InstanceBufferObject must be set before setting instance data!");
        }
        instanceData0.setInstanceData(floatBuffer0, floatBuffer0.limit());
        return this;
    }

    public Mesh setInstanceData(FloatBuffer floatBuffer0, int v) {
        InstanceData instanceData0 = this.instances;
        if(instanceData0 == null) {
            throw new GdxRuntimeException("An InstanceBufferObject must be set before setting instance data!");
        }
        instanceData0.setInstanceData(floatBuffer0, v);
        return this;
    }

    public Mesh setInstanceData(float[] arr_f) {
        InstanceData instanceData0 = this.instances;
        if(instanceData0 == null) {
            throw new GdxRuntimeException("An InstanceBufferObject must be set before setting instance data!");
        }
        instanceData0.setInstanceData(arr_f, 0, arr_f.length);
        return this;
    }

    public Mesh setInstanceData(float[] arr_f, int v, int v1) {
        InstanceData instanceData0 = this.instances;
        if(instanceData0 == null) {
            throw new GdxRuntimeException("An InstanceBufferObject must be set before setting instance data!");
        }
        instanceData0.setInstanceData(arr_f, v, v1);
        return this;
    }

    public Mesh setVertices(float[] arr_f) {
        this.vertices.setVertices(arr_f, 0, arr_f.length);
        return this;
    }

    public Mesh setVertices(float[] arr_f, int v, int v1) {
        this.vertices.setVertices(arr_f, v, v1);
        return this;
    }

    public static void transform(Matrix4 matrix40, float[] arr_f, int v, int v1, int v2, int v3, int v4) {
        if(v1 < 0 || v2 < 1 || v1 + v2 > v) {
            throw new IndexOutOfBoundsException();
        }
        if(v3 < 0 || v4 < 1 || (v3 + v4) * v > arr_f.length) {
            throw new IndexOutOfBoundsException("start = " + v3 + ", count = " + v4 + ", vertexSize = " + v + ", length = " + arr_f.length);
        }
        Vector3 vector30 = new Vector3();
        int v5 = v1 + v3 * v;
        int v6 = 0;
        switch(v2) {
            case 1: {
                while(v6 < v4) {
                    vector30.set(arr_f[v5], 0.0f, 0.0f).mul(matrix40);
                    arr_f[v5] = vector30.x;
                    v5 += v;
                    ++v6;
                }
                return;
            }
            case 2: {
                while(v6 < v4) {
                    vector30.set(arr_f[v5], arr_f[v5 + 1], 0.0f).mul(matrix40);
                    arr_f[v5] = vector30.x;
                    arr_f[v5 + 1] = vector30.y;
                    v5 += v;
                    ++v6;
                }
                return;
            }
            case 3: {
                break;
            }
            default: {
                return;
            }
        }
        while(v6 < v4) {
            vector30.set(arr_f[v5], arr_f[v5 + 1], arr_f[v5 + 2]).mul(matrix40);
            arr_f[v5] = vector30.x;
            arr_f[v5 + 1] = vector30.y;
            arr_f[v5 + 2] = vector30.z;
            v5 += v;
            ++v6;
        }
    }

    public void transform(Matrix4 matrix40) {
        this.transform(matrix40, 0, this.getNumVertices());
    }

    public void transform(Matrix4 matrix40, int v, int v1) {
        VertexAttribute vertexAttribute0 = this.getVertexAttribute(1);
        int v2 = vertexAttribute0.offset / 4;
        int v3 = this.getVertexSize();
        this.getNumVertices();
        int v4 = v1 * (v3 / 4);
        float[] arr_f = new float[v4];
        int v5 = v * (v3 / 4);
        this.getVertices(v5, v4, arr_f);
        Mesh.transform(matrix40, arr_f, v3 / 4, v2, vertexAttribute0.numComponents, 0, v1);
        this.updateVertices(v5, arr_f);
    }

    public static void transformUV(Matrix3 matrix30, float[] arr_f, int v, int v1, int v2, int v3) {
        if(v2 < 0 || v3 < 1 || (v2 + v3) * v > arr_f.length) {
            throw new IndexOutOfBoundsException("start = " + v2 + ", count = " + v3 + ", vertexSize = " + v + ", length = " + arr_f.length);
        }
        Vector2 vector20 = new Vector2();
        int v4 = v1 + v2 * v;
        for(int v5 = 0; v5 < v3; ++v5) {
            vector20.set(arr_f[v4], arr_f[v4 + 1]).mul(matrix30);
            arr_f[v4] = vector20.x;
            arr_f[v4 + 1] = vector20.y;
            v4 += v;
        }
    }

    public void transformUV(Matrix3 matrix30) {
        this.transformUV(matrix30, 0, this.getNumVertices());
    }

    protected void transformUV(Matrix3 matrix30, int v, int v1) {
        int v2 = this.getVertexAttribute(16).offset / 4;
        int v3 = this.getVertexSize();
        float[] arr_f = new float[this.getNumVertices() * (v3 / 4)];
        this.getVertices(0, arr_f.length, arr_f);
        Mesh.transformUV(matrix30, arr_f, v3 / 4, v2, v, v1);
        this.setVertices(arr_f, 0, arr_f.length);
    }

    public void unbind(ShaderProgram shaderProgram0) {
        this.unbind(shaderProgram0, null);
    }

    public void unbind(ShaderProgram shaderProgram0, int[] arr_v) {
        this.vertices.unbind(shaderProgram0, arr_v);
        if(this.instances != null && this.instances.getNumInstances() > 0) {
            this.instances.unbind(shaderProgram0, arr_v);
        }
        if(this.indices.getNumIndices() > 0) {
            this.indices.unbind();
        }
    }

    public Mesh updateInstanceData(int v, FloatBuffer floatBuffer0) {
        return this.updateInstanceData(v, floatBuffer0, 0, floatBuffer0.limit());
    }

    public Mesh updateInstanceData(int v, FloatBuffer floatBuffer0, int v1, int v2) {
        this.instances.updateInstanceData(v, floatBuffer0, v1, v2);
        return this;
    }

    public Mesh updateInstanceData(int v, float[] arr_f) {
        return this.updateInstanceData(v, arr_f, 0, arr_f.length);
    }

    public Mesh updateInstanceData(int v, float[] arr_f, int v1, int v2) {
        this.instances.updateInstanceData(v, arr_f, v1, v2);
        return this;
    }

    public Mesh updateVertices(int v, float[] arr_f) {
        return this.updateVertices(v, arr_f, 0, arr_f.length);
    }

    public Mesh updateVertices(int v, float[] arr_f, int v1, int v2) {
        this.vertices.updateVertices(v, arr_f, v1, v2);
        return this;
    }

    class com.badlogic.gdx.graphics.Mesh.1 {
        static final int[] $SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType;

        static {
            com.badlogic.gdx.graphics.Mesh.1.$SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType = new int[VertexDataType.values().length];
            try {
                com.badlogic.gdx.graphics.Mesh.1.$SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[VertexDataType.VertexBufferObject.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.graphics.Mesh.1.$SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[VertexDataType.VertexBufferObjectSubData.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.graphics.Mesh.1.$SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[VertexDataType.VertexBufferObjectWithVAO.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.graphics.Mesh.1.$SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[VertexDataType.VertexArray.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

