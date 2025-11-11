package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.MathUtils;

public class CylinderShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, int v) {
        CylinderShapeBuilder.build(meshPartBuilder0, f, f1, f2, v, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, int v, float f3, float f4) {
        CylinderShapeBuilder.build(meshPartBuilder0, f, f1, f2, v, f3, f4, true);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, int v, float f3, float f4, boolean z) {
        VertexInfo meshPartBuilder$VertexInfo0 = CylinderShapeBuilder.vertTmp3.set(null, null, null, null);
        meshPartBuilder$VertexInfo0.hasNormal = true;
        meshPartBuilder$VertexInfo0.hasPosition = true;
        meshPartBuilder$VertexInfo0.hasUV = true;
        VertexInfo meshPartBuilder$VertexInfo1 = CylinderShapeBuilder.vertTmp4.set(null, null, null, null);
        meshPartBuilder$VertexInfo1.hasNormal = true;
        meshPartBuilder$VertexInfo1.hasPosition = true;
        meshPartBuilder$VertexInfo1.hasUV = true;
        meshPartBuilder0.ensureVertices((v + 1) * 2);
        meshPartBuilder0.ensureRectangleIndices(v);
        int v1 = 0;
        int v3 = 0;
        for(int v2 = 0; v1 <= v; v2 = v5) {
            float f5 = (f4 - f3) * 0.017453f / ((float)v) * ((float)v1) + f3 * 0.017453f;
            float f6 = 1.0f - ((float)v1) * (1.0f / ((float)v));
            float f7 = MathUtils.cos(f5);
            float f8 = MathUtils.sin(f5);
            meshPartBuilder$VertexInfo0.position.set(f7 * (f * 0.5f), 0.0f, f8 * (0.5f * f2));
            meshPartBuilder$VertexInfo0.normal.set(meshPartBuilder$VertexInfo0.position).nor();
            meshPartBuilder$VertexInfo0.position.y = -(f1 * 0.5f);
            meshPartBuilder$VertexInfo0.uv.set(f6, 1.0f);
            meshPartBuilder$VertexInfo1.position.set(meshPartBuilder$VertexInfo0.position);
            meshPartBuilder$VertexInfo1.normal.set(meshPartBuilder$VertexInfo0.normal);
            meshPartBuilder$VertexInfo1.position.y = f1 * 0.5f;
            meshPartBuilder$VertexInfo1.uv.set(f6, 0.0f);
            int v4 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo0);
            int v5 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo1);
            if(v1 != 0) {
                meshPartBuilder0.rect(((short)v2), ((short)v5), ((short)v4), ((short)v3));
            }
            ++v1;
            v3 = v4;
        }
        if(z) {
            EllipseShapeBuilder.build(meshPartBuilder0, f, f2, 0.0f, 0.0f, v, 0.0f, f1 * 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, f3, f4);
            EllipseShapeBuilder.build(meshPartBuilder0, f, f2, 0.0f, 0.0f, v, 0.0f, -(f1 * 0.5f), 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 180.0f - f4, 180.0f - f3);
        }
    }
}

