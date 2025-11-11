package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class CapsuleShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder0, float f, float f1, int v) {
        if(f1 < 2.0f * f) {
            throw new GdxRuntimeException("Height must be at least twice the radius");
        }
        float f2 = f1 - 2.0f * f;
        CylinderShapeBuilder.build(meshPartBuilder0, 2.0f * f, f2, 2.0f * f, v, 0.0f, 360.0f, false);
        SphereShapeBuilder.build(meshPartBuilder0, CapsuleShapeBuilder.matTmp1.setToTranslation(0.0f, 0.5f * f2, 0.0f), 2.0f * f, 2.0f * f, 2.0f * f, v, v, 0.0f, 360.0f, 0.0f, 90.0f);
        SphereShapeBuilder.build(meshPartBuilder0, CapsuleShapeBuilder.matTmp1.setToTranslation(0.0f, f2 * -0.5f, 0.0f), 2.0f * f, 2.0f * f, 2.0f * f, v, v, 0.0f, 360.0f, 90.0f, 180.0f);
    }
}

