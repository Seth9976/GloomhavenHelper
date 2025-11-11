package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Vector3;

public class FrustumShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder0, Camera camera0) {
        FrustumShapeBuilder.build(meshPartBuilder0, camera0, FrustumShapeBuilder.tmpColor0.set(1.0f, 0.66f, 0.0f, 1.0f), FrustumShapeBuilder.tmpColor1.set(1.0f, 0.0f, 0.0f, 1.0f), FrustumShapeBuilder.tmpColor2.set(0.0f, 0.66f, 1.0f, 1.0f), FrustumShapeBuilder.tmpColor3.set(1.0f, 1.0f, 1.0f, 1.0f), FrustumShapeBuilder.tmpColor4.set(0.2f, 0.2f, 0.2f, 1.0f));
    }

    public static void build(MeshPartBuilder meshPartBuilder0, Camera camera0, Color color0, Color color1, Color color2, Color color3, Color color4) {
        FrustumShapeBuilder.build(meshPartBuilder0, camera0.frustum, color0, color4);
        meshPartBuilder0.line(camera0.frustum.planePoints[0], color1, camera0.position, color1);
        meshPartBuilder0.line(camera0.frustum.planePoints[1], color1, camera0.position, color1);
        meshPartBuilder0.line(camera0.frustum.planePoints[2], color1, camera0.position, color1);
        meshPartBuilder0.line(camera0.frustum.planePoints[3], color1, camera0.position, color1);
        Vector3 vector30 = FrustumShapeBuilder.centerPoint(camera0.frustum.planePoints[4], camera0.frustum.planePoints[5], camera0.frustum.planePoints[6]);
        meshPartBuilder0.line(camera0.position, color3, vector30, color3);
        float f = FrustumShapeBuilder.tmpV0.set(camera0.frustum.planePoints[1]).sub(camera0.frustum.planePoints[0]).scl(0.5f).len();
        Vector3 vector31 = FrustumShapeBuilder.centerPoint(camera0.frustum.planePoints[0], camera0.frustum.planePoints[1], camera0.frustum.planePoints[2]);
        FrustumShapeBuilder.tmpV0.set(camera0.up).scl(f * 2.0f);
        vector31.add(FrustumShapeBuilder.tmpV0);
        meshPartBuilder0.line(vector31, color2, camera0.frustum.planePoints[2], color2);
        meshPartBuilder0.line(camera0.frustum.planePoints[2], color2, camera0.frustum.planePoints[3], color2);
        meshPartBuilder0.line(camera0.frustum.planePoints[3], color2, vector31, color2);
    }

    public static void build(MeshPartBuilder meshPartBuilder0, Frustum frustum0, Color color0, Color color1) {
        meshPartBuilder0.line(frustum0.planePoints[0], color0, frustum0.planePoints[1], color0);
        meshPartBuilder0.line(frustum0.planePoints[1], color0, frustum0.planePoints[2], color0);
        meshPartBuilder0.line(frustum0.planePoints[2], color0, frustum0.planePoints[3], color0);
        meshPartBuilder0.line(frustum0.planePoints[3], color0, frustum0.planePoints[0], color0);
        meshPartBuilder0.line(frustum0.planePoints[4], color0, frustum0.planePoints[5], color0);
        meshPartBuilder0.line(frustum0.planePoints[5], color0, frustum0.planePoints[6], color0);
        meshPartBuilder0.line(frustum0.planePoints[6], color0, frustum0.planePoints[7], color0);
        meshPartBuilder0.line(frustum0.planePoints[7], color0, frustum0.planePoints[4], color0);
        meshPartBuilder0.line(frustum0.planePoints[0], color0, frustum0.planePoints[4], color0);
        meshPartBuilder0.line(frustum0.planePoints[1], color0, frustum0.planePoints[5], color0);
        meshPartBuilder0.line(frustum0.planePoints[2], color0, frustum0.planePoints[6], color0);
        meshPartBuilder0.line(frustum0.planePoints[3], color0, frustum0.planePoints[7], color0);
        meshPartBuilder0.line(FrustumShapeBuilder.middlePoint(frustum0.planePoints[1], frustum0.planePoints[0]), color1, FrustumShapeBuilder.middlePoint(frustum0.planePoints[3], frustum0.planePoints[2]), color1);
        meshPartBuilder0.line(FrustumShapeBuilder.middlePoint(frustum0.planePoints[2], frustum0.planePoints[1]), color1, FrustumShapeBuilder.middlePoint(frustum0.planePoints[3], frustum0.planePoints[0]), color1);
        meshPartBuilder0.line(FrustumShapeBuilder.middlePoint(frustum0.planePoints[5], frustum0.planePoints[4]), color1, FrustumShapeBuilder.middlePoint(frustum0.planePoints[7], frustum0.planePoints[6]), color1);
        meshPartBuilder0.line(FrustumShapeBuilder.middlePoint(frustum0.planePoints[6], frustum0.planePoints[5]), color1, FrustumShapeBuilder.middlePoint(frustum0.planePoints[7], frustum0.planePoints[4]), color1);
    }

    private static Vector3 centerPoint(Vector3 vector30, Vector3 vector31, Vector3 vector32) {
        FrustumShapeBuilder.tmpV0.set(vector31).sub(vector30).scl(0.5f);
        FrustumShapeBuilder.tmpV1.set(vector30).add(FrustumShapeBuilder.tmpV0);
        FrustumShapeBuilder.tmpV0.set(vector32).sub(vector31).scl(0.5f);
        return FrustumShapeBuilder.tmpV1.add(FrustumShapeBuilder.tmpV0);
    }

    private static Vector3 middlePoint(Vector3 vector30, Vector3 vector31) {
        FrustumShapeBuilder.tmpV0.set(vector31).sub(vector30).scl(0.5f);
        return FrustumShapeBuilder.tmpV1.set(vector30).add(FrustumShapeBuilder.tmpV0);
    }
}

