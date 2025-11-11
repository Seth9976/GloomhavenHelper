package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class PatchShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, int v, int v1) {
        PatchShapeBuilder.build(meshPartBuilder0, PatchShapeBuilder.vertTmp1.set(null).setPos(f, f1, f2).setNor(f12, f13, f14).setUV(0.0f, 1.0f), PatchShapeBuilder.vertTmp2.set(null).setPos(f3, f4, f5).setNor(f12, f13, f14).setUV(1.0f, 1.0f), PatchShapeBuilder.vertTmp3.set(null).setPos(f6, f7, f8).setNor(f12, f13, f14).setUV(1.0f, 0.0f), PatchShapeBuilder.vertTmp4.set(null).setPos(f9, f10, f11).setNor(f12, f13, f14).setUV(0.0f, 0.0f), v, v1);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, VertexInfo meshPartBuilder$VertexInfo0, VertexInfo meshPartBuilder$VertexInfo1, VertexInfo meshPartBuilder$VertexInfo2, VertexInfo meshPartBuilder$VertexInfo3, int v, int v1) {
        if(v < 1 || v1 < 1) {
            throw new GdxRuntimeException("divisionsU and divisionV must be > 0, u,v: " + v + ", " + v1);
        }
        meshPartBuilder0.ensureVertices((v1 + 1) * (v + 1));
        meshPartBuilder0.ensureRectangleIndices(v1 * v);
        for(int v2 = 0; v2 <= v; ++v2) {
            float f = ((float)v2) / ((float)v);
            PatchShapeBuilder.vertTmp5.set(meshPartBuilder$VertexInfo0).lerp(meshPartBuilder$VertexInfo1, f);
            PatchShapeBuilder.vertTmp6.set(meshPartBuilder$VertexInfo3).lerp(meshPartBuilder$VertexInfo2, f);
            for(int v3 = 0; v3 <= v1; ++v3) {
                int v4 = meshPartBuilder0.vertex(PatchShapeBuilder.vertTmp7.set(PatchShapeBuilder.vertTmp5).lerp(PatchShapeBuilder.vertTmp6, ((float)v3) / ((float)v1)));
                if(v2 > 0 && v3 > 0) {
                    meshPartBuilder0.rect(((short)(v4 - v1 - 2)), ((short)(v4 - 1)), ((short)v4), ((short)(v4 - v1 - 1)));
                }
            }
        }
    }

    public static void build(MeshPartBuilder meshPartBuilder0, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, Vector3 vector34, int v, int v1) {
        PatchShapeBuilder.build(meshPartBuilder0, PatchShapeBuilder.vertTmp1.set(vector30, vector34, null, null).setUV(0.0f, 1.0f), PatchShapeBuilder.vertTmp2.set(vector31, vector34, null, null).setUV(1.0f, 1.0f), PatchShapeBuilder.vertTmp3.set(vector32, vector34, null, null).setUV(1.0f, 0.0f), PatchShapeBuilder.vertTmp4.set(vector33, vector34, null, null).setUV(0.0f, 0.0f), v, v1);
    }
}

