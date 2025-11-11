package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import java.util.Comparator;

public class DefaultRenderableSorter implements RenderableSorter, Comparator {
    private Camera camera;
    private final Vector3 tmpV1;
    private final Vector3 tmpV2;

    public DefaultRenderableSorter() {
        this.tmpV1 = new Vector3();
        this.tmpV2 = new Vector3();
    }

    public int compare(Renderable renderable0, Renderable renderable1) {
        int v = 0;
        int v1 = !renderable0.material.has(BlendingAttribute.Type) || !((BlendingAttribute)renderable0.material.get(BlendingAttribute.Type)).blended ? 0 : 1;
        if(v1 != (!renderable1.material.has(BlendingAttribute.Type) || !((BlendingAttribute)renderable1.material.get(BlendingAttribute.Type)).blended ? 0 : 1)) {
            return v1 == 0 ? -1 : 1;
        }
        this.getTranslation(renderable0.worldTransform, renderable0.meshPart.center, this.tmpV1);
        this.getTranslation(renderable1.worldTransform, renderable1.meshPart.center, this.tmpV2);
        float f = (float)(((int)(this.camera.position.dst2(this.tmpV1) * 1000.0f)) - ((int)(this.camera.position.dst2(this.tmpV2) * 1000.0f)));
        if(f < 0.0f) {
            return v1 == 0 ? -1 : 1;
        }
        if(f > 0.0f) {
            v = 1;
        }
        return v1 == 0 ? v : -v;
    }

    @Override
    public int compare(Object object0, Object object1) {
        return this.compare(((Renderable)object0), ((Renderable)object1));
    }

    private Vector3 getTranslation(Matrix4 matrix40, Vector3 vector30, Vector3 vector31) {
        if(vector30.isZero()) {
            matrix40.getTranslation(vector31);
            return vector31;
        }
        if(!matrix40.hasRotationOrScaling()) {
            matrix40.getTranslation(vector31).add(vector30);
            return vector31;
        }
        vector31.set(vector30).mul(matrix40);
        return vector31;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.RenderableSorter
    public void sort(Camera camera0, Array array0) {
        this.camera = camera0;
        array0.sort(this);
    }
}

