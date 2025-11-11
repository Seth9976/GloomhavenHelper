package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class ArrowShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, int v) {
        Vector3 vector30 = ArrowShapeBuilder.obtainV3().set(f, f1, f2);
        Vector3 vector31 = ArrowShapeBuilder.obtainV3().set(f3, f4, f5);
        float f8 = vector30.dst(vector31);
        float f9 = f8 * f6;
        float f10 = ((float)(((double)f9) * 0.57735)) * 2.0f;
        float f11 = f8 - f9;
        Vector3 vector32 = ArrowShapeBuilder.obtainV3().set(vector31).sub(vector30).nor();
        Vector3 vector33 = ArrowShapeBuilder.obtainV3().set(vector32).crs(Vector3.Z);
        if(vector33.isZero()) {
            vector33.set(Vector3.X);
        }
        vector33.crs(vector32).nor();
        Vector3 vector34 = ArrowShapeBuilder.obtainV3().set(vector32).crs(vector33).nor();
        Vector3 vector35 = ArrowShapeBuilder.obtainV3().set(vector31).sub(vector30).nor();
        Matrix4 matrix40 = meshPartBuilder0.getVertexTransform(ArrowShapeBuilder.obtainM4());
        Matrix4 matrix41 = ArrowShapeBuilder.obtainM4();
        matrix41.val[0] = vector34.x;
        matrix41.val[4] = vector32.x;
        matrix41.val[8] = vector33.x;
        matrix41.val[1] = vector34.y;
        matrix41.val[5] = vector32.y;
        matrix41.val[9] = vector33.y;
        matrix41.val[2] = vector34.z;
        matrix41.val[6] = vector32.z;
        matrix41.val[10] = vector33.z;
        Matrix4 matrix42 = ArrowShapeBuilder.obtainM4();
        matrix41.setTranslation(ArrowShapeBuilder.obtainV3().set(vector35).scl(f11 / 2.0f).add(f, f1, f2));
        meshPartBuilder0.setVertexTransform(matrix42.set(matrix41).mul(matrix40));
        CylinderShapeBuilder.build(meshPartBuilder0, f10 * f7, f11, f10 * f7, v);
        matrix41.setTranslation(ArrowShapeBuilder.obtainV3().set(vector35).scl(f11).add(f, f1, f2));
        meshPartBuilder0.setVertexTransform(matrix42.set(matrix41).mul(matrix40));
        ConeShapeBuilder.build(meshPartBuilder0, f10, f9, f10, v);
        meshPartBuilder0.setVertexTransform(matrix40);
        ArrowShapeBuilder.freeAll();
    }
}

