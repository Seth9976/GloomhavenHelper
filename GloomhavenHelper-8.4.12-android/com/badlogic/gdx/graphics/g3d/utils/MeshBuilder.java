package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ArrowShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.CapsuleShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.CylinderShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.EllipseShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.PatchShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.SphereShapeBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.ShortArray;

public class MeshBuilder implements MeshPartBuilder {
    public static final int MAX_INDEX = 0xFFFF;
    public static final int MAX_VERTICES = 0x10000;
    private VertexAttributes attributes;
    private int biNorOffset;
    private final BoundingBox bounds;
    private int colOffset;
    private int colSize;
    private final Color color;
    private int cpOffset;
    private boolean hasColor;
    private boolean hasUVTransform;
    private ShortArray indices;
    private static IntIntMap indicesMap;
    private int istart;
    private int lastIndex;
    private int norOffset;
    private final Matrix3 normalTransform;
    private MeshPart part;
    private Array parts;
    private int posOffset;
    private int posSize;
    private final Matrix4 positionTransform;
    private int primitiveType;
    private int stride;
    private int tangentOffset;
    private final Color tempC1;
    private static final ShortArray tmpIndices;
    private final Vector3 tmpNormal;
    private static final FloatArray tmpVertices;
    private float uOffset;
    private float uScale;
    private int uvOffset;
    private float vOffset;
    private float vScale;
    private static final Vector3 vTmp;
    private final VertexInfo vertTmp1;
    private final VertexInfo vertTmp2;
    private final VertexInfo vertTmp3;
    private final VertexInfo vertTmp4;
    private float[] vertex;
    private boolean vertexTransformationEnabled;
    private FloatArray vertices;
    private int vindex;

    static {
        MeshBuilder.tmpIndices = new ShortArray();
        MeshBuilder.tmpVertices = new FloatArray();
        MeshBuilder.vTmp = new Vector3();
        MeshBuilder.indicesMap = null;
    }

    public MeshBuilder() {
        this.vertTmp1 = new VertexInfo();
        this.vertTmp2 = new VertexInfo();
        this.vertTmp3 = new VertexInfo();
        this.vertTmp4 = new VertexInfo();
        this.tempC1 = new Color();
        this.vertices = new FloatArray();
        this.indices = new ShortArray();
        this.parts = new Array();
        this.color = new Color(Color.WHITE);
        this.hasColor = false;
        this.uOffset = 0.0f;
        this.uScale = 1.0f;
        this.vOffset = 0.0f;
        this.vScale = 1.0f;
        this.hasUVTransform = false;
        this.vertexTransformationEnabled = false;
        this.positionTransform = new Matrix4();
        this.normalTransform = new Matrix3();
        this.bounds = new BoundingBox();
        this.lastIndex = -1;
        this.tmpNormal = new Vector3();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(Mesh mesh0) {
        this.addMesh(mesh0, 0, mesh0.getNumIndices());
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(Mesh mesh0, int v, int v1) {
        if(!this.attributes.equals(mesh0.getVertexAttributes())) {
            throw new GdxRuntimeException("Vertex attributes do not match");
        }
        if(v1 <= 0) {
            return;
        }
        int v2 = mesh0.getNumVertices() * this.stride;
        MeshBuilder.tmpVertices.clear();
        MeshBuilder.tmpVertices.ensureCapacity(v2);
        MeshBuilder.tmpVertices.size = v2;
        mesh0.getVertices(MeshBuilder.tmpVertices.items);
        MeshBuilder.tmpIndices.clear();
        MeshBuilder.tmpIndices.ensureCapacity(v1);
        MeshBuilder.tmpIndices.size = v1;
        mesh0.getIndices(v, v1, MeshBuilder.tmpIndices.items, 0);
        this.addMesh(MeshBuilder.tmpVertices.items, MeshBuilder.tmpIndices.items, 0, v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(MeshPart meshPart0) {
        if(meshPart0.primitiveType != this.primitiveType) {
            throw new GdxRuntimeException("Primitive type doesn\'t match");
        }
        this.addMesh(meshPart0.mesh, meshPart0.offset, meshPart0.size);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(float[] arr_f, short[] arr_v) {
        int v = this.lastIndex + 1;
        this.ensureVertices(arr_f.length / this.stride);
        for(int v2 = 0; v2 < arr_f.length; v2 += this.stride) {
            this.addVertex(arr_f, v2);
        }
        this.ensureIndices(arr_v.length);
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            this.index(((short)(arr_v[v1] + v)));
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(float[] arr_f, short[] arr_v, int v, int v1) {
        IntIntMap intIntMap0 = MeshBuilder.indicesMap;
        if(intIntMap0 == null) {
            MeshBuilder.indicesMap = new IntIntMap(v1);
        }
        else {
            intIntMap0.clear();
            MeshBuilder.indicesMap.ensureCapacity(v1);
        }
        this.ensureIndices(v1);
        int v2 = arr_f.length / this.stride;
        if(v2 >= v1) {
            v2 = v1;
        }
        this.ensureVertices(v2);
        for(int v3 = 0; v3 < v1; ++v3) {
            int v4 = arr_v[v + v3] & 0xFFFF;
            int v5 = MeshBuilder.indicesMap.get(v4, -1);
            if(v5 < 0) {
                this.addVertex(arr_f, this.stride * v4);
                int v6 = this.lastIndex;
                MeshBuilder.indicesMap.put(v4, v6);
                v5 = v6;
            }
            this.index(((short)v5));
        }
    }

    private final void addVertex(float[] arr_f, int v) {
        int v1 = this.vertices.size;
        this.vertices.addAll(arr_f, v, this.stride);
        int v2 = this.vindex;
        this.vindex = v2 + 1;
        this.lastIndex = v2;
        if(this.vertexTransformationEnabled) {
            MeshBuilder.transformPosition(this.vertices.items, this.posOffset + v1, this.posSize, this.positionTransform);
            if(this.norOffset >= 0) {
                MeshBuilder.transformNormal(this.vertices.items, this.norOffset + v1, 3, this.normalTransform);
            }
            if(this.biNorOffset >= 0) {
                MeshBuilder.transformNormal(this.vertices.items, this.biNorOffset + v1, 3, this.normalTransform);
            }
            if(this.tangentOffset >= 0) {
                MeshBuilder.transformNormal(this.vertices.items, this.tangentOffset + v1, 3, this.normalTransform);
            }
        }
        float f = this.vertices.items[this.posOffset + v1];
        float f1 = 0.0f;
        float f2 = this.posSize <= 1 ? 0.0f : this.vertices.items[this.posOffset + v1 + 1];
        if(this.posSize > 2) {
            f1 = this.vertices.items[this.posOffset + v1 + 2];
        }
        this.bounds.ext(f, f2, f1);
        if(this.hasColor) {
            if(this.colOffset >= 0) {
                float[] arr_f1 = this.vertices.items;
                int v3 = this.colOffset + v1;
                arr_f1[v3] *= this.color.r;
                float[] arr_f2 = this.vertices.items;
                int v4 = this.colOffset + v1 + 1;
                arr_f2[v4] *= this.color.g;
                float[] arr_f3 = this.vertices.items;
                int v5 = this.colOffset + v1 + 2;
                arr_f3[v5] *= this.color.b;
                if(this.colSize > 3) {
                    float[] arr_f4 = this.vertices.items;
                    int v6 = this.colOffset + v1 + 3;
                    arr_f4[v6] *= this.color.a;
                }
            }
            else if(this.cpOffset >= 0) {
                Color.abgr8888ToColor(this.tempC1, this.vertices.items[this.cpOffset + v1]);
                float[] arr_f5 = this.vertices.items;
                int v7 = this.cpOffset + v1;
                arr_f5[v7] = this.tempC1.mul(this.color).toFloatBits();
            }
        }
        if(this.hasUVTransform && this.uvOffset >= 0) {
            this.vertices.items[this.uvOffset + v1] = this.uOffset + this.uScale * this.vertices.items[this.uvOffset + v1];
            this.vertices.items[this.uvOffset + v1 + 1] = this.vOffset + this.vScale * this.vertices.items[v1 + this.uvOffset + 1];
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void arrow(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, int v) {
        ArrowShapeBuilder.build(this, f, f1, f2, f3, f4, f5, f6, f7, v);
    }

    public void begin(long v) {
        this.begin(MeshBuilder.createAttributes(v), -1);
    }

    public void begin(long v, int v1) {
        this.begin(MeshBuilder.createAttributes(v), v1);
    }

    public void begin(VertexAttributes vertexAttributes0) {
        this.begin(vertexAttributes0, -1);
    }

    public void begin(VertexAttributes vertexAttributes0, int v) {
        if(this.attributes != null) {
            throw new RuntimeException("Call end() first");
        }
        this.attributes = vertexAttributes0;
        this.vertices.clear();
        this.indices.clear();
        this.parts.clear();
        int v1 = 0;
        this.vindex = 0;
        int v2 = -1;
        this.lastIndex = -1;
        this.istart = 0;
        this.part = null;
        this.stride = vertexAttributes0.vertexSize / 4;
        if(this.vertex == null || this.vertex.length < this.stride) {
            this.vertex = new float[this.stride];
        }
        VertexAttribute vertexAttribute0 = vertexAttributes0.findByUsage(1);
        if(vertexAttribute0 == null) {
            throw new GdxRuntimeException("Cannot build mesh without position attribute");
        }
        this.posOffset = vertexAttribute0.offset / 4;
        this.posSize = vertexAttribute0.numComponents;
        VertexAttribute vertexAttribute1 = vertexAttributes0.findByUsage(8);
        this.norOffset = vertexAttribute1 == null ? -1 : vertexAttribute1.offset / 4;
        VertexAttribute vertexAttribute2 = vertexAttributes0.findByUsage(0x100);
        this.biNorOffset = vertexAttribute2 == null ? -1 : vertexAttribute2.offset / 4;
        VertexAttribute vertexAttribute3 = vertexAttributes0.findByUsage(0x80);
        this.tangentOffset = vertexAttribute3 == null ? -1 : vertexAttribute3.offset / 4;
        VertexAttribute vertexAttribute4 = vertexAttributes0.findByUsage(2);
        this.colOffset = vertexAttribute4 == null ? -1 : vertexAttribute4.offset / 4;
        if(vertexAttribute4 != null) {
            v1 = vertexAttribute4.numComponents;
        }
        this.colSize = v1;
        VertexAttribute vertexAttribute5 = vertexAttributes0.findByUsage(4);
        this.cpOffset = vertexAttribute5 == null ? -1 : vertexAttribute5.offset / 4;
        VertexAttribute vertexAttribute6 = vertexAttributes0.findByUsage(16);
        if(vertexAttribute6 != null) {
            v2 = vertexAttribute6.offset / 4;
        }
        this.uvOffset = v2;
        this.setColor(null);
        this.setVertexTransform(null);
        this.setUVRange(null);
        this.primitiveType = v;
        this.bounds.inf();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(float f, float f1, float f2) {
        BoxShapeBuilder.build(this, f, f1, f2);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(float f, float f1, float f2, float f3, float f4, float f5) {
        BoxShapeBuilder.build(this, f, f1, f2, f3, f4, f5);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(VertexInfo meshPartBuilder$VertexInfo0, VertexInfo meshPartBuilder$VertexInfo1, VertexInfo meshPartBuilder$VertexInfo2, VertexInfo meshPartBuilder$VertexInfo3, VertexInfo meshPartBuilder$VertexInfo4, VertexInfo meshPartBuilder$VertexInfo5, VertexInfo meshPartBuilder$VertexInfo6, VertexInfo meshPartBuilder$VertexInfo7) {
        BoxShapeBuilder.build(this, meshPartBuilder$VertexInfo0, meshPartBuilder$VertexInfo1, meshPartBuilder$VertexInfo2, meshPartBuilder$VertexInfo3, meshPartBuilder$VertexInfo4, meshPartBuilder$VertexInfo5, meshPartBuilder$VertexInfo6, meshPartBuilder$VertexInfo7);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(Matrix4 matrix40) {
        BoxShapeBuilder.build(this, matrix40);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, Vector3 vector34, Vector3 vector35, Vector3 vector36, Vector3 vector37) {
        BoxShapeBuilder.build(this, vector30, vector31, vector32, vector33, vector34, vector35, vector36, vector37);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void capsule(float f, float f1, int v) {
        CapsuleShapeBuilder.build(this, f, f1, v);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f, int v, float f1, float f2, float f3, float f4, float f5, float f6) {
        EllipseShapeBuilder.build(this, f, v, f1, f2, f3, f4, f5, f6);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f, int v, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        EllipseShapeBuilder.build(this, f, v, f1, f2, f3, f4, f5, f6, f7, f8);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f, int v, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        EllipseShapeBuilder.build(this, f, v, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f, int v, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        EllipseShapeBuilder.build(this, f, v, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f, int v, Vector3 vector30, Vector3 vector31) {
        EllipseShapeBuilder.build(this, f, v, vector30, vector31);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f, int v, Vector3 vector30, Vector3 vector31, float f1, float f2) {
        EllipseShapeBuilder.build(this, f, v, vector30, vector31, f1, f2);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f, int v, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33) {
        EllipseShapeBuilder.build(this, f, v, vector30, vector31, vector32, vector33);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f, int v, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, float f1, float f2) {
        this.circle(f, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z, f1, f2);
    }

    public void clear() {
        this.vertices.clear();
        this.indices.clear();
        this.parts.clear();
        this.vindex = 0;
        this.lastIndex = -1;
        this.istart = 0;
        this.part = null;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cone(float f, float f1, float f2, int v) {
        this.cone(f, f1, f2, v, 0.0f, 360.0f);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cone(float f, float f1, float f2, int v, float f3, float f4) {
        ConeShapeBuilder.build(this, f, f1, f2, v, f3, f4);
    }

    public static VertexAttributes createAttributes(long v) {
        Array array0 = new Array();
        if((v & 1L) == 1L) {
            array0.add(new VertexAttribute(1, 3, "a_position"));
        }
        if((v & 2L) == 2L) {
            array0.add(new VertexAttribute(2, 4, "a_color"));
        }
        if((v & 4L) == 4L) {
            array0.add(new VertexAttribute(4, 4, "a_color"));
        }
        if((v & 8L) == 8L) {
            array0.add(new VertexAttribute(8, 3, "a_normal"));
        }
        if((v & 16L) == 16L) {
            array0.add(new VertexAttribute(16, 2, "a_texCoord0"));
        }
        VertexAttribute[] arr_vertexAttribute = new VertexAttribute[array0.size];
        for(int v1 = 0; v1 < arr_vertexAttribute.length; ++v1) {
            arr_vertexAttribute[v1] = (VertexAttribute)array0.get(v1);
        }
        return new VertexAttributes(arr_vertexAttribute);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cylinder(float f, float f1, float f2, int v) {
        CylinderShapeBuilder.build(this, f, f1, f2, v);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cylinder(float f, float f1, float f2, int v, float f3, float f4) {
        CylinderShapeBuilder.build(this, f, f1, f2, v, f3, f4);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cylinder(float f, float f1, float f2, int v, float f3, float f4, boolean z) {
        CylinderShapeBuilder.build(this, f, f1, f2, v, f3, f4, z);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, float f2, float f3, int v, float f4, float f5, float f6, float f7, float f8, float f9) {
        EllipseShapeBuilder.build(this, f, f1, f2, f3, v, f4, f5, f6, f7, f8, f9);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, float f2, float f3, int v, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        EllipseShapeBuilder.build(this, f, f1, f2, f3, v, f4, f5, f6, f7, f8, f9, f10, f11);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, float f2, float f3, int v, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        EllipseShapeBuilder.build(this, f, f1, f2, f3, v, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, float f2, float f3, int v, Vector3 vector30, Vector3 vector31) {
        EllipseShapeBuilder.build(this, f, f1, f2, f3, v, vector30, vector31);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, int v, float f2, float f3, float f4, float f5, float f6, float f7) {
        EllipseShapeBuilder.build(this, f, f1, v, f2, f3, f4, f5, f6, f7);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, int v, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        EllipseShapeBuilder.build(this, f, f1, v, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, int v, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        EllipseShapeBuilder.build(this, f, f1, v, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, int v, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        EllipseShapeBuilder.build(this, f, f1, v, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, int v, Vector3 vector30, Vector3 vector31) {
        EllipseShapeBuilder.build(this, f, f1, v, vector30, vector31);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, int v, Vector3 vector30, Vector3 vector31, float f2, float f3) {
        EllipseShapeBuilder.build(this, f, f1, v, vector30, vector31, f2, f3);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, int v, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33) {
        EllipseShapeBuilder.build(this, f, f1, v, vector30, vector31, vector32, vector33);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f, float f1, int v, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, float f2, float f3) {
        EllipseShapeBuilder.build(this, f, f1, v, vector30, vector31, vector32, vector33, f2, f3);
    }

    public Mesh end() {
        return this.end(new Mesh(true, this.vertices.size / this.stride, this.indices.size, this.attributes));
    }

    public Mesh end(Mesh mesh0) {
        this.endpart();
        VertexAttributes vertexAttributes0 = this.attributes;
        if(vertexAttributes0 == null) {
            throw new GdxRuntimeException("Call begin() first");
        }
        if(!vertexAttributes0.equals(mesh0.getVertexAttributes())) {
            throw new GdxRuntimeException("Mesh attributes don\'t match");
        }
        if(mesh0.getMaxVertices() * this.stride < this.vertices.size) {
            throw new GdxRuntimeException("Mesh can\'t hold enough vertices: " + mesh0.getMaxVertices() + " * " + this.stride + " < " + this.vertices.size);
        }
        if(mesh0.getMaxIndices() < this.indices.size) {
            throw new GdxRuntimeException("Mesh can\'t hold enough indices: " + mesh0.getMaxIndices() + " < " + this.indices.size);
        }
        mesh0.setVertices(this.vertices.items, 0, this.vertices.size);
        mesh0.setIndices(this.indices.items, 0, this.indices.size);
        for(Object object0: this.parts) {
            ((MeshPart)object0).mesh = mesh0;
        }
        this.parts.clear();
        this.attributes = null;
        this.vertices.clear();
        this.indices.clear();
        return mesh0;
    }

    private void endpart() {
        MeshPart meshPart0 = this.part;
        if(meshPart0 != null) {
            this.bounds.getCenter(meshPart0.center);
            this.bounds.getDimensions(this.part.halfExtents).scl(0.5f);
            this.part.radius = this.part.halfExtents.len();
            this.bounds.inf();
            this.part.offset = this.istart;
            this.part.size = this.indices.size - this.istart;
            this.istart = this.indices.size;
            this.part = null;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureCapacity(int v, int v1) {
        this.ensureVertices(v);
        this.ensureIndices(v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureIndices(int v) {
        this.indices.ensureCapacity(v);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureRectangleIndices(int v) {
        int v1 = this.primitiveType;
        if(v1 == 0) {
            this.ensureIndices(v * 4);
            return;
        }
        if(v1 == 1) {
            this.ensureIndices(v * 8);
            return;
        }
        this.ensureIndices(v * 6);
    }

    @Deprecated
    public void ensureRectangles(int v) {
        this.ensureVertices(v * 4);
        this.ensureRectangleIndices(v);
    }

    @Deprecated
    public void ensureRectangles(int v, int v1) {
        this.ensureVertices(v);
        this.ensureRectangleIndices(v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureTriangleIndices(int v) {
        int v1 = this.primitiveType;
        if(v1 == 1) {
            this.ensureIndices(v * 6);
            return;
        }
        if(v1 != 0 && v1 != 4) {
            throw new GdxRuntimeException("Incorrect primtive type");
        }
        this.ensureIndices(v * 3);
    }

    @Deprecated
    public void ensureTriangles(int v) {
        this.ensureVertices(v * 3);
        this.ensureTriangleIndices(v);
    }

    @Deprecated
    public void ensureTriangles(int v, int v1) {
        this.ensureVertices(v);
        this.ensureTriangleIndices(v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureVertices(int v) {
        this.vertices.ensureCapacity(this.stride * v);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    public int getFloatsPerVertex() {
        return this.stride;
    }

    public void getIndices(short[] arr_v, int v) {
        if(this.attributes == null) {
            throw new GdxRuntimeException("Must be called in between #begin and #end");
        }
        if(v < 0 || v > arr_v.length - this.indices.size) {
            throw new GdxRuntimeException("Array too small or offset out of range");
        }
        System.arraycopy(this.indices.items, 0, arr_v, v, this.indices.size);
    }

    protected short[] getIndices() {
        return this.indices.items;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public MeshPart getMeshPart() {
        return this.part;
    }

    public int getNumIndices() {
        return this.indices.size;
    }

    public int getNumVertices() {
        return this.vertices.size / this.stride;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public int getPrimitiveType() {
        return this.primitiveType;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public Matrix4 getVertexTransform(Matrix4 matrix40) {
        return matrix40.set(this.positionTransform);
    }

    public void getVertices(float[] arr_f, int v) {
        if(this.attributes == null) {
            throw new GdxRuntimeException("Must be called in between #begin and #end");
        }
        if(v < 0 || v > arr_f.length - this.vertices.size) {
            throw new GdxRuntimeException("Array too small or offset out of range");
        }
        System.arraycopy(this.vertices.items, 0, arr_f, v, this.vertices.size);
    }

    protected float[] getVertices() {
        return this.vertices.items;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short v) {
        this.indices.add(v);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short v, short v1) {
        this.ensureIndices(2);
        this.indices.add(v);
        this.indices.add(v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short v, short v1, short v2) {
        this.ensureIndices(3);
        this.indices.add(v);
        this.indices.add(v1);
        this.indices.add(v2);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short v, short v1, short v2, short v3) {
        this.ensureIndices(4);
        this.indices.add(v);
        this.indices.add(v1);
        this.indices.add(v2);
        this.indices.add(v3);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short v, short v1, short v2, short v3, short v4, short v5) {
        this.ensureIndices(6);
        this.indices.add(v);
        this.indices.add(v1);
        this.indices.add(v2);
        this.indices.add(v3);
        this.indices.add(v4);
        this.indices.add(v5);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short v, short v1, short v2, short v3, short v4, short v5, short v6, short v7) {
        this.ensureIndices(8);
        this.indices.add(v);
        this.indices.add(v1);
        this.indices.add(v2);
        this.indices.add(v3);
        this.indices.add(v4);
        this.indices.add(v5);
        this.indices.add(v6);
        this.indices.add(v7);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public boolean isVertexTransformationEnabled() {
        return this.vertexTransformationEnabled;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public short lastIndex() {
        return (short)this.lastIndex;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(float f, float f1, float f2, float f3, float f4, float f5) {
        this.line(this.vertTmp1.set(null, null, null, null).setPos(f, f1, f2), this.vertTmp2.set(null, null, null, null).setPos(f3, f4, f5));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(VertexInfo meshPartBuilder$VertexInfo0, VertexInfo meshPartBuilder$VertexInfo1) {
        this.ensureVertices(2);
        this.line(this.vertex(meshPartBuilder$VertexInfo0), this.vertex(meshPartBuilder$VertexInfo1));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(Vector3 vector30, Color color0, Vector3 vector31, Color color1) {
        this.line(this.vertTmp1.set(vector30, null, color0, null), this.vertTmp2.set(vector31, null, color1, null));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(Vector3 vector30, Vector3 vector31) {
        this.line(this.vertTmp1.set(vector30, null, null, null), this.vertTmp2.set(vector31, null, null, null));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(short v, short v1) {
        if(this.primitiveType != 1) {
            throw new GdxRuntimeException("Incorrect primitive type");
        }
        this.index(v, v1);
    }

    public MeshPart part(String s, int v) {
        return this.part(s, v, new MeshPart());
    }

    public MeshPart part(String s, int v, MeshPart meshPart0) {
        if(this.attributes == null) {
            throw new RuntimeException("Call begin() first");
        }
        this.endpart();
        this.part = meshPart0;
        this.part.id = s;
        this.part.primitiveType = v;
        this.primitiveType = v;
        this.parts.add(this.part);
        this.setColor(null);
        this.setVertexTransform(null);
        this.setUVRange(null);
        return this.part;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void patch(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, int v, int v1) {
        PatchShapeBuilder.build(this, f, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void patch(VertexInfo meshPartBuilder$VertexInfo0, VertexInfo meshPartBuilder$VertexInfo1, VertexInfo meshPartBuilder$VertexInfo2, VertexInfo meshPartBuilder$VertexInfo3, int v, int v1) {
        PatchShapeBuilder.build(this, meshPartBuilder$VertexInfo0, meshPartBuilder$VertexInfo1, meshPartBuilder$VertexInfo2, meshPartBuilder$VertexInfo3, v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void patch(Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, Vector3 vector34, int v, int v1) {
        PatchShapeBuilder.build(this, vector30, vector31, vector32, vector33, vector34, v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void rect(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        this.rect(this.vertTmp1.set(null, null, null, null).setPos(f, f1, f2).setNor(f12, f13, f14).setUV(0.0f, 1.0f), this.vertTmp2.set(null, null, null, null).setPos(f3, f4, f5).setNor(f12, f13, f14).setUV(1.0f, 1.0f), this.vertTmp3.set(null, null, null, null).setPos(f6, f7, f8).setNor(f12, f13, f14).setUV(1.0f, 0.0f), this.vertTmp4.set(null, null, null, null).setPos(f9, f10, f11).setNor(f12, f13, f14).setUV(0.0f, 0.0f));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void rect(VertexInfo meshPartBuilder$VertexInfo0, VertexInfo meshPartBuilder$VertexInfo1, VertexInfo meshPartBuilder$VertexInfo2, VertexInfo meshPartBuilder$VertexInfo3) {
        this.ensureVertices(4);
        this.rect(this.vertex(meshPartBuilder$VertexInfo0), this.vertex(meshPartBuilder$VertexInfo1), this.vertex(meshPartBuilder$VertexInfo2), this.vertex(meshPartBuilder$VertexInfo3));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void rect(Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, Vector3 vector34) {
        this.rect(this.vertTmp1.set(vector30, vector34, null, null).setUV(0.0f, 1.0f), this.vertTmp2.set(vector31, vector34, null, null).setUV(1.0f, 1.0f), this.vertTmp3.set(vector32, vector34, null, null).setUV(1.0f, 0.0f), this.vertTmp4.set(vector33, vector34, null, null).setUV(0.0f, 0.0f));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void rect(short v, short v1, short v2, short v3) {
        int v4 = this.primitiveType;
        if(v4 == 4) {
            this.index(v, v1, v2, v2, v3, v);
            return;
        }
        switch(v4) {
            case 0: {
                this.index(v, v1, v2, v3);
                return;
            }
            case 1: {
                this.index(v, v1, v1, v2, v2, v3, v3, v);
                return;
            }
            default: {
                throw new GdxRuntimeException("Incorrect primitive type");
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
        this.hasColor = !this.color.equals(Color.WHITE);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setColor(Color color0) {
        Color color1 = this.color;
        this.hasColor = color0 != null;
        if(color0 == null) {
            color0 = Color.WHITE;
        }
        color1.set(color0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setUVRange(float f, float f1, float f2, float f3) {
        this.uOffset = f;
        this.vOffset = f1;
        this.uScale = f2 - f;
        this.vScale = f3 - f1;
        this.hasUVTransform = !MathUtils.isZero(f) || !MathUtils.isZero(f1) || !MathUtils.isEqual(f2, 1.0f) || !MathUtils.isEqual(f3, 1.0f);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setUVRange(TextureRegion textureRegion0) {
        if(textureRegion0 == null) {
            this.hasUVTransform = false;
            this.vOffset = 0.0f;
            this.uOffset = 0.0f;
            this.vScale = 1.0f;
            this.uScale = 1.0f;
            return;
        }
        this.hasUVTransform = true;
        this.setUVRange(textureRegion0.getU(), textureRegion0.getV(), textureRegion0.getU2(), textureRegion0.getV2());
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setVertexTransform(Matrix4 matrix40) {
        this.vertexTransformationEnabled = matrix40 != null;
        if(this.vertexTransformationEnabled) {
            this.positionTransform.set(matrix40);
            this.normalTransform.set(matrix40).inv().transpose();
            return;
        }
        this.positionTransform.idt();
        this.normalTransform.idt();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setVertexTransformationEnabled(boolean z) {
        this.vertexTransformationEnabled = z;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void sphere(float f, float f1, float f2, int v, int v1) {
        SphereShapeBuilder.build(this, f, f1, f2, v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void sphere(float f, float f1, float f2, int v, int v1, float f3, float f4, float f5, float f6) {
        SphereShapeBuilder.build(this, f, f1, f2, v, v1, f3, f4, f5, f6);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void sphere(Matrix4 matrix40, float f, float f1, float f2, int v, int v1) {
        SphereShapeBuilder.build(this, matrix40, f, f1, f2, v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void sphere(Matrix4 matrix40, float f, float f1, float f2, int v, int v1, float f3, float f4, float f5, float f6) {
        SphereShapeBuilder.build(this, matrix40, f, f1, f2, v, v1, f3, f4, f5, f6);
    }

    private static final void transformNormal(float[] arr_f, int v, int v1, Matrix3 matrix30) {
        if(v1 > 2) {
            MeshBuilder.vTmp.set(arr_f[v], arr_f[v + 1], arr_f[v + 2]).mul(matrix30).nor();
            arr_f[v] = MeshBuilder.vTmp.x;
            arr_f[v + 1] = MeshBuilder.vTmp.y;
            arr_f[v + 2] = MeshBuilder.vTmp.z;
            return;
        }
        if(v1 > 1) {
            MeshBuilder.vTmp.set(arr_f[v], arr_f[v + 1], 0.0f).mul(matrix30).nor();
            arr_f[v] = MeshBuilder.vTmp.x;
            arr_f[v + 1] = MeshBuilder.vTmp.y;
            return;
        }
        arr_f[v] = MeshBuilder.vTmp.set(arr_f[v], 0.0f, 0.0f).mul(matrix30).nor().x;
    }

    private static final void transformPosition(float[] arr_f, int v, int v1, Matrix4 matrix40) {
        if(v1 > 2) {
            MeshBuilder.vTmp.set(arr_f[v], arr_f[v + 1], arr_f[v + 2]).mul(matrix40);
            arr_f[v] = MeshBuilder.vTmp.x;
            arr_f[v + 1] = MeshBuilder.vTmp.y;
            arr_f[v + 2] = MeshBuilder.vTmp.z;
            return;
        }
        if(v1 > 1) {
            MeshBuilder.vTmp.set(arr_f[v], arr_f[v + 1], 0.0f).mul(matrix40);
            arr_f[v] = MeshBuilder.vTmp.x;
            arr_f[v + 1] = MeshBuilder.vTmp.y;
            return;
        }
        arr_f[v] = MeshBuilder.vTmp.set(arr_f[v], 0.0f, 0.0f).mul(matrix40).x;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void triangle(VertexInfo meshPartBuilder$VertexInfo0, VertexInfo meshPartBuilder$VertexInfo1, VertexInfo meshPartBuilder$VertexInfo2) {
        this.ensureVertices(3);
        this.triangle(this.vertex(meshPartBuilder$VertexInfo0), this.vertex(meshPartBuilder$VertexInfo1), this.vertex(meshPartBuilder$VertexInfo2));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void triangle(Vector3 vector30, Color color0, Vector3 vector31, Color color1, Vector3 vector32, Color color2) {
        this.triangle(this.vertTmp1.set(vector30, null, color0, null), this.vertTmp2.set(vector31, null, color1, null), this.vertTmp3.set(vector32, null, color2, null));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void triangle(Vector3 vector30, Vector3 vector31, Vector3 vector32) {
        this.triangle(this.vertTmp1.set(vector30, null, null, null), this.vertTmp2.set(vector31, null, null, null), this.vertTmp3.set(vector32, null, null, null));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void triangle(short v, short v1, short v2) {
        int v3 = this.primitiveType;
        if(v3 != 0 && v3 != 4) {
            if(v3 != 1) {
                throw new GdxRuntimeException("Incorrect primitive type");
            }
            this.index(v, v1, v1, v2, v2, v);
            return;
        }
        this.index(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public short vertex(VertexInfo meshPartBuilder$VertexInfo0) {
        Vector2 vector20 = null;
        Vector3 vector30 = meshPartBuilder$VertexInfo0.hasPosition ? meshPartBuilder$VertexInfo0.position : null;
        Vector3 vector31 = meshPartBuilder$VertexInfo0.hasNormal ? meshPartBuilder$VertexInfo0.normal : null;
        Color color0 = meshPartBuilder$VertexInfo0.hasColor ? meshPartBuilder$VertexInfo0.color : null;
        if(meshPartBuilder$VertexInfo0.hasUV) {
            vector20 = meshPartBuilder$VertexInfo0.uv;
        }
        return this.vertex(vector30, vector31, color0, vector20);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public short vertex(Vector3 vector30, Vector3 vector31, Color color0, Vector2 vector20) {
        if(this.vindex > 0xFFFF) {
            throw new GdxRuntimeException("Too many vertices used");
        }
        this.vertex[this.posOffset] = vector30.x;
        if(this.posSize > 1) {
            this.vertex[this.posOffset + 1] = vector30.y;
        }
        if(this.posSize > 2) {
            this.vertex[this.posOffset + 2] = vector30.z;
        }
        if(this.norOffset >= 0) {
            if(vector31 == null) {
                vector31 = this.tmpNormal.set(vector30).nor();
            }
            this.vertex[this.norOffset] = vector31.x;
            this.vertex[this.norOffset + 1] = vector31.y;
            this.vertex[this.norOffset + 2] = vector31.z;
        }
        if(this.colOffset >= 0) {
            if(color0 == null) {
                color0 = Color.WHITE;
            }
            this.vertex[this.colOffset] = color0.r;
            this.vertex[this.colOffset + 1] = color0.g;
            this.vertex[this.colOffset + 2] = color0.b;
            if(this.colSize > 3) {
                this.vertex[this.colOffset + 3] = color0.a;
            }
        }
        else if(this.cpOffset > 0) {
            if(color0 == null) {
                color0 = Color.WHITE;
            }
            this.vertex[this.cpOffset] = color0.toFloatBits();
        }
        if(vector20 != null) {
            int v = this.uvOffset;
            if(v >= 0) {
                this.vertex[v] = vector20.x;
                this.vertex[this.uvOffset + 1] = vector20.y;
            }
        }
        this.addVertex(this.vertex, 0);
        return (short)this.lastIndex;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public short vertex(float[] arr_f) {
        int v = arr_f.length - this.stride;
        for(int v1 = 0; v1 <= v; v1 += this.stride) {
            this.addVertex(arr_f, v1);
        }
        return (short)this.lastIndex;
    }
}

