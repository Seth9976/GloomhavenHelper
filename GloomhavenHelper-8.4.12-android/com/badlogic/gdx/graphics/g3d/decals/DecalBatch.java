package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.SortedIntList.Node;
import com.badlogic.gdx.utils.SortedIntList;

public class DecalBatch implements Disposable {
    private static final int DEFAULT_SIZE = 1000;
    private final SortedIntList groupList;
    private final Pool groupPool;
    private GroupStrategy groupStrategy;
    private Mesh mesh;
    private final Array usedGroups;
    private float[] vertices;

    public DecalBatch(int v, GroupStrategy groupStrategy0) {
        this.groupList = new SortedIntList();
        this.groupPool = new Pool(16) {
            protected Array newObject() {
                return new Array(false, 100);
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.usedGroups = new Array(16);
        this.initialize(v);
        this.setGroupStrategy(groupStrategy0);
    }

    public DecalBatch(GroupStrategy groupStrategy0) {
        this(1000, groupStrategy0);
    }

    public void add(Decal decal0) {
        int v = this.groupStrategy.decideGroup(decal0);
        Array array0 = (Array)this.groupList.get(v);
        if(array0 == null) {
            array0 = (Array)this.groupPool.obtain();
            array0.clear();
            this.usedGroups.add(array0);
            this.groupList.insert(v, array0);
        }
        array0.add(decal0);
    }

    protected void clear() {
        this.groupList.clear();
        this.groupPool.freeAll(this.usedGroups);
        this.usedGroups.clear();
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.clear();
        this.vertices = null;
        this.mesh.dispose();
    }

    public void flush() {
        this.render();
        this.clear();
    }

    protected void flush(ShaderProgram shaderProgram0, int v) {
        this.mesh.setVertices(this.vertices, 0, v);
        this.mesh.render(shaderProgram0, 4, 0, v / 4);
    }

    public int getSize() {
        return this.vertices.length / 24;
    }

    public void initialize(int v) {
        this.vertices = new float[v * 24];
        VertexDataType mesh$VertexDataType0 = Gdx.gl30 == null ? VertexDataType.VertexArray : VertexDataType.VertexBufferObjectWithVAO;
        VertexAttribute[] arr_vertexAttribute = new VertexAttribute[3];
        int v1 = 0;
        arr_vertexAttribute[0] = new VertexAttribute(1, 3, "a_position");
        arr_vertexAttribute[1] = new VertexAttribute(4, 4, "a_color");
        arr_vertexAttribute[2] = new VertexAttribute(16, 2, "a_texCoord0");
        this.mesh = new Mesh(mesh$VertexDataType0, false, v * 4, v * 6, arr_vertexAttribute);
        short[] arr_v = new short[v * 6];
        for(int v2 = 0; v1 < arr_v.length; v2 += 4) {
            arr_v[v1] = (short)v2;
            arr_v[v1 + 1] = (short)(v2 + 2);
            arr_v[v1 + 2] = (short)(v2 + 1);
            arr_v[v1 + 3] = (short)(v2 + 1);
            arr_v[v1 + 4] = (short)(v2 + 2);
            arr_v[v1 + 5] = (short)(v2 + 3);
            v1 += 6;
        }
        this.mesh.setIndices(arr_v);
    }

    private void render(ShaderProgram shaderProgram0, Array array0) {
        DecalMaterial decalMaterial0 = null;
        int v = 0;
        for(Object object0: array0) {
            Decal decal0 = (Decal)object0;
            if(decalMaterial0 == null || !decalMaterial0.equals(decal0.getMaterial())) {
                if(v > 0) {
                    this.flush(shaderProgram0, v);
                    v = 0;
                }
                decal0.material.set();
                decalMaterial0 = decal0.material;
            }
            decal0.update();
            System.arraycopy(decal0.vertices, 0, this.vertices, v, decal0.vertices.length);
            v += decal0.vertices.length;
            if(v == this.vertices.length) {
                this.flush(shaderProgram0, v);
                v = 0;
            }
        }
        if(v > 0) {
            this.flush(shaderProgram0, v);
        }
    }

    protected void render() {
        this.groupStrategy.beforeGroups();
        for(Object object0: this.groupList) {
            this.groupStrategy.beforeGroup(((Node)object0).index, ((Array)((Node)object0).value));
            this.render(this.groupStrategy.getGroupShader(((Node)object0).index), ((Array)((Node)object0).value));
            this.groupStrategy.afterGroup(((Node)object0).index);
        }
        this.groupStrategy.afterGroups();
    }

    public void setGroupStrategy(GroupStrategy groupStrategy0) {
        this.groupStrategy = groupStrategy0;
    }
}

