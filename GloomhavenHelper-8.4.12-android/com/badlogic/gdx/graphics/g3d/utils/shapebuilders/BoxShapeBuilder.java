package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class BoxShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2) {
        BoxShapeBuilder.build(meshPartBuilder0, 0.0f, 0.0f, 0.0f, f, f1, f2);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = f - f3 * 0.5f;
        float f7 = f1 - f4 * 0.5f;
        float f8 = f2 - 0.5f * f5;
        float f9 = f + f3 * 0.5f;
        float f10 = f1 + f4 * 0.5f;
        float f11 = f2 + 0.5f * f5;
        BoxShapeBuilder.build(meshPartBuilder0, BoxShapeBuilder.obtainV3().set(f6, f7, f8), BoxShapeBuilder.obtainV3().set(f6, f10, f8), BoxShapeBuilder.obtainV3().set(f9, f7, f8), BoxShapeBuilder.obtainV3().set(f9, f10, f8), BoxShapeBuilder.obtainV3().set(f6, f7, f11), BoxShapeBuilder.obtainV3().set(f6, f10, f11), BoxShapeBuilder.obtainV3().set(f9, f7, f11), BoxShapeBuilder.obtainV3().set(f9, f10, f11));
        BoxShapeBuilder.freeAll();
    }

    public static void build(MeshPartBuilder meshPartBuilder0, VertexInfo meshPartBuilder$VertexInfo0, VertexInfo meshPartBuilder$VertexInfo1, VertexInfo meshPartBuilder$VertexInfo2, VertexInfo meshPartBuilder$VertexInfo3, VertexInfo meshPartBuilder$VertexInfo4, VertexInfo meshPartBuilder$VertexInfo5, VertexInfo meshPartBuilder$VertexInfo6, VertexInfo meshPartBuilder$VertexInfo7) {
        meshPartBuilder0.ensureVertices(8);
        int v = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo0);
        int v1 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo2);
        int v2 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo3);
        int v3 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo1);
        int v4 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo4);
        int v5 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo6);
        int v6 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo7);
        int v7 = meshPartBuilder0.vertex(meshPartBuilder$VertexInfo5);
        int v8 = meshPartBuilder0.getPrimitiveType();
        if(v8 == 1) {
            meshPartBuilder0.ensureIndices(24);
            meshPartBuilder0.rect(((short)v), ((short)v1), ((short)v2), ((short)v3));
            meshPartBuilder0.rect(((short)v5), ((short)v4), ((short)v7), ((short)v6));
            meshPartBuilder0.index(((short)v), ((short)v4), ((short)v3), ((short)v7), ((short)v2), ((short)v6), ((short)v1), ((short)v5));
            return;
        }
        if(v8 == 0) {
            meshPartBuilder0.ensureRectangleIndices(2);
            meshPartBuilder0.rect(((short)v), ((short)v1), ((short)v2), ((short)v3));
            meshPartBuilder0.rect(((short)v5), ((short)v4), ((short)v7), ((short)v6));
            return;
        }
        meshPartBuilder0.ensureRectangleIndices(6);
        meshPartBuilder0.rect(((short)v), ((short)v1), ((short)v2), ((short)v3));
        meshPartBuilder0.rect(((short)v5), ((short)v4), ((short)v7), ((short)v6));
        meshPartBuilder0.rect(((short)v), ((short)v3), ((short)v7), ((short)v4));
        meshPartBuilder0.rect(((short)v5), ((short)v6), ((short)v2), ((short)v1));
        meshPartBuilder0.rect(((short)v5), ((short)v1), ((short)v), ((short)v4));
        meshPartBuilder0.rect(((short)v2), ((short)v6), ((short)v7), ((short)v3));
    }

    public static void build(MeshPartBuilder meshPartBuilder0, Matrix4 matrix40) {
        BoxShapeBuilder.build(meshPartBuilder0, BoxShapeBuilder.obtainV3().set(-0.5f, -0.5f, -0.5f).mul(matrix40), BoxShapeBuilder.obtainV3().set(-0.5f, 0.5f, -0.5f).mul(matrix40), BoxShapeBuilder.obtainV3().set(0.5f, -0.5f, -0.5f).mul(matrix40), BoxShapeBuilder.obtainV3().set(0.5f, 0.5f, -0.5f).mul(matrix40), BoxShapeBuilder.obtainV3().set(-0.5f, -0.5f, 0.5f).mul(matrix40), BoxShapeBuilder.obtainV3().set(-0.5f, 0.5f, 0.5f).mul(matrix40), BoxShapeBuilder.obtainV3().set(0.5f, -0.5f, 0.5f).mul(matrix40), BoxShapeBuilder.obtainV3().set(0.5f, 0.5f, 0.5f).mul(matrix40));
        BoxShapeBuilder.freeAll();
    }

    public static void build(MeshPartBuilder meshPartBuilder0, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33, Vector3 vector34, Vector3 vector35, Vector3 vector36, Vector3 vector37) {
        if((meshPartBuilder0.getAttributes().getMask() & 408L) == 0L) {
            BoxShapeBuilder.build(meshPartBuilder0, BoxShapeBuilder.vertTmp1.set(vector30, null, null, null), BoxShapeBuilder.vertTmp2.set(vector31, null, null, null), BoxShapeBuilder.vertTmp3.set(vector32, null, null, null), BoxShapeBuilder.vertTmp4.set(vector33, null, null, null), BoxShapeBuilder.vertTmp5.set(vector34, null, null, null), BoxShapeBuilder.vertTmp6.set(vector35, null, null, null), BoxShapeBuilder.vertTmp7.set(vector36, null, null, null), BoxShapeBuilder.vertTmp8.set(vector37, null, null, null));
            return;
        }
        meshPartBuilder0.ensureVertices(24);
        meshPartBuilder0.ensureRectangleIndices(6);
        Vector3 vector38 = BoxShapeBuilder.tmpV1.set(vector30).lerp(vector33, 0.5f).sub(BoxShapeBuilder.tmpV2.set(vector34).lerp(vector37, 0.5f)).nor();
        meshPartBuilder0.rect(vector30, vector31, vector33, vector32, vector38);
        meshPartBuilder0.rect(vector35, vector34, vector36, vector37, vector38.scl(-1.0f));
        Vector3 vector39 = BoxShapeBuilder.tmpV1.set(vector30).lerp(vector36, 0.5f).sub(BoxShapeBuilder.tmpV2.set(vector31).lerp(vector37, 0.5f)).nor();
        meshPartBuilder0.rect(vector34, vector30, vector32, vector36, vector39);
        meshPartBuilder0.rect(vector31, vector35, vector37, vector33, vector39.scl(-1.0f));
        Vector3 vector310 = BoxShapeBuilder.tmpV1.set(vector30).lerp(vector35, 0.5f).sub(BoxShapeBuilder.tmpV2.set(vector32).lerp(vector37, 0.5f)).nor();
        meshPartBuilder0.rect(vector34, vector35, vector31, vector30, vector310);
        meshPartBuilder0.rect(vector32, vector33, vector37, vector36, vector310.scl(-1.0f));
    }

    public static void build(MeshPartBuilder meshPartBuilder0, BoundingBox boundingBox0) {
        meshPartBuilder0.box(boundingBox0.getCorner000(BoxShapeBuilder.obtainV3()), boundingBox0.getCorner010(BoxShapeBuilder.obtainV3()), boundingBox0.getCorner100(BoxShapeBuilder.obtainV3()), boundingBox0.getCorner110(BoxShapeBuilder.obtainV3()), boundingBox0.getCorner001(BoxShapeBuilder.obtainV3()), boundingBox0.getCorner011(BoxShapeBuilder.obtainV3()), boundingBox0.getCorner101(BoxShapeBuilder.obtainV3()), boundingBox0.getCorner111(BoxShapeBuilder.obtainV3()));
        BoxShapeBuilder.freeAll();
    }
}

