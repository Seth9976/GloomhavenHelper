package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ShortArray;

public class SphereShapeBuilder extends BaseShapeBuilder {
    private static final Matrix3 normalTransform;
    private static final ShortArray tmpIndices;

    static {
        SphereShapeBuilder.tmpIndices = new ShortArray();
        SphereShapeBuilder.normalTransform = new Matrix3();
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, int v, int v1) {
        SphereShapeBuilder.build(meshPartBuilder0, f, f1, f2, v, v1, 0.0f, 360.0f, 0.0f, 180.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, int v, int v1, float f3, float f4, float f5, float f6) {
        SphereShapeBuilder.build(meshPartBuilder0, SphereShapeBuilder.matTmp1.idt(), f, f1, f2, v, v1, f3, f4, f5, f6);
    }

    @Deprecated
    public static void build(MeshPartBuilder meshPartBuilder0, Matrix4 matrix40, float f, float f1, float f2, int v, int v1) {
        SphereShapeBuilder.build(meshPartBuilder0, matrix40, f, f1, f2, v, v1, 0.0f, 360.0f, 0.0f, 180.0f);
    }

    @Deprecated
    public static void build(MeshPartBuilder meshPartBuilder0, Matrix4 matrix40, float f, float f1, float f2, int v, int v1, float f3, float f4, float f5, float f6) {
        float f14;
        boolean z = MathUtils.isEqual(f5, 0.0f);
        boolean z1 = MathUtils.isEqual(f6, 180.0f);
        VertexInfo meshPartBuilder$VertexInfo0 = SphereShapeBuilder.vertTmp3.set(null, null, null, null);
        meshPartBuilder$VertexInfo0.hasNormal = true;
        meshPartBuilder$VertexInfo0.hasPosition = true;
        meshPartBuilder$VertexInfo0.hasUV = true;
        SphereShapeBuilder.normalTransform.set(matrix40);
        SphereShapeBuilder.tmpIndices.clear();
        SphereShapeBuilder.tmpIndices.ensureCapacity(v * 2);
        SphereShapeBuilder.tmpIndices.size = v + 3;
        meshPartBuilder0.ensureVertices((v1 + 1) * (v + 1));
        meshPartBuilder0.ensureRectangleIndices(v);
        int v2 = 0;
        for(int v3 = 0; v2 <= v1; v3 = v5) {
            float f7 = f5 * 0.017453f + (f6 - f5) * 0.017453f / ((float)v1) * ((float)v2);
            float f8 = ((float)v2) * (1.0f / ((float)v1));
            float f9 = MathUtils.sin(f7);
            float f10 = MathUtils.cos(f7) * (f1 * 0.5f);
            int v4 = 0;
            int v5 = v3;
            while(v4 <= v) {
                float f11 = f3 * 0.017453f + (f4 - f3) * 0.017453f / ((float)v) * ((float)v4);
                float f12 = MathUtils.cos(f11);
                float f13 = MathUtils.sin(f11);
                meshPartBuilder$VertexInfo0.position.set(f12 * (f * 0.5f) * f9, f10, f13 * (f2 * 0.5f) * f9);
                meshPartBuilder$VertexInfo0.normal.set(meshPartBuilder$VertexInfo0.position).mul(SphereShapeBuilder.normalTransform).nor();
                meshPartBuilder$VertexInfo0.position.mul(matrix40);
                meshPartBuilder$VertexInfo0.uv.set((v2 == 0 && z || v2 == v1 && z1 ? 1.0f - (((float)v4) - 0.5f) * (1.0f / ((float)v)) : 1.0f - ((float)v4) * (1.0f / ((float)v))), f8);
                int v6 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo0);
                SphereShapeBuilder.tmpIndices.set(v5, ((short)v6));
                int v7 = v5 + (v + 3);
                if(v2 <= 0 || v4 <= 0) {
                    f14 = f10;
                }
                else if(v2 != 1 || !z) {
                    f14 = f10;
                    if(v2 != v1 || !z1) {
                        meshPartBuilder0.rect(SphereShapeBuilder.tmpIndices.get(v5), SphereShapeBuilder.tmpIndices.get((v7 - 1) % (v + 3)), SphereShapeBuilder.tmpIndices.get((v7 - (v + 2)) % (v + 3)), SphereShapeBuilder.tmpIndices.get((v7 - (v + 1)) % (v + 3)));
                    }
                    else {
                        meshPartBuilder0.triangle(SphereShapeBuilder.tmpIndices.get(v5), SphereShapeBuilder.tmpIndices.get((v7 - (v + 2)) % (v + 3)), SphereShapeBuilder.tmpIndices.get((v7 - (v + 1)) % (v + 3)));
                    }
                }
                else {
                    f14 = f10;
                    meshPartBuilder0.triangle(SphereShapeBuilder.tmpIndices.get(v5), SphereShapeBuilder.tmpIndices.get((v7 - 1) % (v + 3)), SphereShapeBuilder.tmpIndices.get((v7 - (v + 1)) % (v + 3)));
                }
                v5 = (v5 + 1) % SphereShapeBuilder.tmpIndices.size;
                ++v4;
                f10 = f14;
            }
            ++v2;
        }
    }
}

