package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.MathUtils;

public class ConeShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, int v) {
        ConeShapeBuilder.build(meshPartBuilder0, f, f1, f2, v, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, int v, float f3, float f4) {
        ConeShapeBuilder.build(meshPartBuilder0, f, f1, f2, v, f3, f4, true);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, int v, float f3, float f4, boolean z) {
        meshPartBuilder0.ensureVertices(v + 2);
        meshPartBuilder0.ensureTriangleIndices(v);
        VertexInfo meshPartBuilder$VertexInfo0 = ConeShapeBuilder.vertTmp3.set(null, null, null, null);
        meshPartBuilder$VertexInfo0.hasNormal = true;
        meshPartBuilder$VertexInfo0.hasPosition = true;
        meshPartBuilder$VertexInfo0.hasUV = true;
        int v1 = meshPartBuilder0.vertex(ConeShapeBuilder.vertTmp4.set(null, null, null, null).setPos(0.0f, f1 * 0.5f, 0.0f).setNor(0.0f, 1.0f, 0.0f).setUV(0.5f, 0.0f));
        int v2 = 0;
        for(int v3 = 0; v2 <= v; v3 = v4) {
            float f5 = (f4 - f3) * 0.017453f / ((float)v) * ((float)v2) + f3 * 0.017453f;
            float f6 = MathUtils.cos(f5);
            float f7 = MathUtils.sin(f5);
            meshPartBuilder$VertexInfo0.position.set(f6 * (f * 0.5f), 0.0f, f7 * (f2 * 0.5f));
            meshPartBuilder$VertexInfo0.normal.set(meshPartBuilder$VertexInfo0.position).nor();
            meshPartBuilder$VertexInfo0.position.y = -(f1 * 0.5f);
            meshPartBuilder$VertexInfo0.uv.set(1.0f - ((float)v2) * (1.0f / ((float)v)), 1.0f);
            int v4 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo0);
            if(v2 != 0) {
                meshPartBuilder0.triangle(((short)v1), ((short)v4), ((short)v3));
            }
            ++v2;
        }
        if(z) {
            EllipseShapeBuilder.build(meshPartBuilder0, f, f2, 0.0f, 0.0f, v, 0.0f, -(f1 * 0.5f), 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 180.0f - f4, 180.0f - f3);
        }
    }
}

