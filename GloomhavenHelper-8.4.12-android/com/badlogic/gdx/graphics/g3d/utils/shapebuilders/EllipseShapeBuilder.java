package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class EllipseShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, float f3, int v, float f4, float f5, float f6, float f7, float f8, float f9) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, f2, f3, v, f4, f5, f6, f7, f8, f9, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, float f3, int v, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        EllipseShapeBuilder.tmpV1.set(f7, f8, f9).crs(0.0f, 0.0f, 1.0f);
        EllipseShapeBuilder.tmpV2.set(f7, f8, f9).crs(0.0f, 1.0f, 0.0f);
        if(EllipseShapeBuilder.tmpV2.len2() > EllipseShapeBuilder.tmpV1.len2()) {
            EllipseShapeBuilder.tmpV1.set(EllipseShapeBuilder.tmpV2);
        }
        EllipseShapeBuilder.tmpV2.set(EllipseShapeBuilder.tmpV1.nor()).crs(f7, f8, f9).nor();
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, f2, f3, v, f4, f5, f6, f7, f8, f9, EllipseShapeBuilder.tmpV1.x, EllipseShapeBuilder.tmpV1.y, EllipseShapeBuilder.tmpV1.z, EllipseShapeBuilder.tmpV2.x, EllipseShapeBuilder.tmpV2.y, EllipseShapeBuilder.tmpV2.z, f10, f11);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, float f3, int v, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        int v9;
        int v8;
        int v7;
        Vector3 vector35;
        Vector3 vector34;
        if(f2 <= 0.0f || f3 <= 0.0f) {
            meshPartBuilder0.ensureVertices(v + 2);
            meshPartBuilder0.ensureTriangleIndices(v);
        }
        else if(f2 == f && f3 == f1) {
            meshPartBuilder0.ensureVertices(v + 1);
            meshPartBuilder0.ensureIndices(v + 1);
            if(meshPartBuilder0.getPrimitiveType() != 1) {
                throw new GdxRuntimeException("Incorrect primitive type : expect GL_LINES because innerWidth == width && innerHeight == height");
            }
        }
        else {
            meshPartBuilder0.ensureVertices((v + 1) * 2);
            meshPartBuilder0.ensureRectangleIndices(v + 1);
        }
        Vector3 vector30 = EllipseShapeBuilder.tmpV1.set(f10, f11, f12).scl(f * 0.5f);
        Vector3 vector31 = EllipseShapeBuilder.tmpV2.set(f13, f14, f15).scl(f1 * 0.5f);
        Vector3 vector32 = EllipseShapeBuilder.tmpV3.set(f10, f11, f12).scl(f2 * 0.5f);
        Vector3 vector33 = EllipseShapeBuilder.tmpV4.set(f13, f14, f15).scl(f3 * 0.5f);
        VertexInfo meshPartBuilder$VertexInfo0 = EllipseShapeBuilder.vertTmp3.set(null, null, null, null);
        meshPartBuilder$VertexInfo0.hasNormal = true;
        meshPartBuilder$VertexInfo0.hasPosition = true;
        meshPartBuilder$VertexInfo0.hasUV = true;
        meshPartBuilder$VertexInfo0.uv.set(0.5f, 0.5f);
        meshPartBuilder$VertexInfo0.position.set(f4, f5, f6);
        meshPartBuilder$VertexInfo0.normal.set(f7, f8, f9);
        VertexInfo meshPartBuilder$VertexInfo1 = EllipseShapeBuilder.vertTmp4.set(null, null, null, null);
        meshPartBuilder$VertexInfo1.hasNormal = true;
        meshPartBuilder$VertexInfo1.hasPosition = true;
        meshPartBuilder$VertexInfo1.hasUV = true;
        meshPartBuilder$VertexInfo1.uv.set(0.5f, 0.5f);
        meshPartBuilder$VertexInfo1.position.set(f4, f5, f6);
        meshPartBuilder$VertexInfo1.normal.set(f7, f8, f9);
        int v1 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo1);
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        while(v2 <= v) {
            float f18 = f16 * 0.017453f + ((float)v2) * ((f17 - f16) * 0.017453f / ((float)v));
            float f19 = MathUtils.cos(f18);
            float f20 = MathUtils.sin(f18);
            meshPartBuilder$VertexInfo1.position.set(f4, f5, f6).add(vector30.x * f19 + vector31.x * f20, vector30.y * f19 + vector31.y * f20, vector30.z * f19 + vector31.z * f20);
            meshPartBuilder$VertexInfo1.uv.set(f19 * 0.5f + 0.5f, f20 * 0.5f + 0.5f);
            int v6 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo1);
            if(f2 <= 0.0f || f3 <= 0.0f) {
            label_62:
                vector34 = vector33;
                vector35 = vector31;
                v7 = v4;
                v8 = v5;
                if(v2 == 0) {
                    v9 = v1;
                }
                else {
                    v9 = v1;
                    meshPartBuilder0.triangle(((short)v6), ((short)v3), ((short)v9));
                }
            }
            else {
                if(f2 != f || f3 != f1) {
                    vector34 = vector33;
                    vector35 = vector31;
                    meshPartBuilder$VertexInfo0.position.set(f4, f5, f6).add(vector32.x * f19 + vector34.x * f20, vector32.y * f19 + vector34.y * f20, vector32.z * f19 + vector34.z * f20);
                    meshPartBuilder$VertexInfo0.uv.set(f2 / f * 0.5f * f19 + 0.5f, f3 / f1 * 0.5f * f20 + 0.5f);
                    int v10 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo0);
                    if(v2 != 0) {
                        meshPartBuilder0.rect(((short)v10), ((short)v6), ((short)v5), ((short)v4));
                    }
                    v4 = v10;
                    v5 = v6;
                    v9 = v1;
                    goto label_73;
                }
                else {
                    if(v2 != 0) {
                        meshPartBuilder0.line(((short)v6), ((short)v3));
                    }
                    vector34 = vector33;
                    vector35 = vector31;
                    v7 = v4;
                    v8 = v5;
                    v9 = v1;
                    goto label_71;
                }
                goto label_62;
            }
        label_71:
            v4 = v7;
            v5 = v8;
        label_73:
            ++v2;
            v3 = v6;
            vector33 = vector34;
            v1 = v9;
            vector31 = vector35;
        }
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, float f3, int v, Vector3 vector30, Vector3 vector31) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, f2, f3, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v, float f2, float f3, float f4, float f5, float f6, float f7) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, v, f2, f3, f4, f5, f6, f7, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, 0.0f, 0.0f, v, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, v, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, 0.0f, 0.0f, v, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v, Vector3 vector30, Vector3 vector31) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v, Vector3 vector30, Vector3 vector31, float f2, float f3) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, 0.0f, 0.0f, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, f2, f3);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, float f2, float f3) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, f1, 0.0f, 0.0f, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z, f2, f3);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, int v, float f1, float f2, float f3, float f4, float f5, float f6) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, v, f1, f2, f3, f4, f5, f6, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, int v, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        EllipseShapeBuilder.build(meshPartBuilder0, f * 2.0f, f * 2.0f, v, f1, f2, f3, f4, f5, f6, f7, f8);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, int v, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, v, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, int v, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        EllipseShapeBuilder.build(meshPartBuilder0, f * 2.0f, f * 2.0f, 0.0f, 0.0f, v, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, int v, Vector3 vector30, Vector3 vector31) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, int v, Vector3 vector30, Vector3 vector31, float f1, float f2) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, f1, f2);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, int v, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, int v, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, float f1, float f2) {
        EllipseShapeBuilder.build(meshPartBuilder0, f, v, vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z, f1, f2);
    }
}

