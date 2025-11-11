package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.FlushablePool;

public class BaseShapeBuilder {
    protected static final Matrix4 matTmp1;
    private static final FlushablePool matrices4Pool;
    protected static final Color tmpColor0;
    protected static final Color tmpColor1;
    protected static final Color tmpColor2;
    protected static final Color tmpColor3;
    protected static final Color tmpColor4;
    protected static final Vector3 tmpV0;
    protected static final Vector3 tmpV1;
    protected static final Vector3 tmpV2;
    protected static final Vector3 tmpV3;
    protected static final Vector3 tmpV4;
    protected static final Vector3 tmpV5;
    protected static final Vector3 tmpV6;
    protected static final Vector3 tmpV7;
    private static final FlushablePool vectorPool;
    protected static final VertexInfo vertTmp0;
    protected static final VertexInfo vertTmp1;
    protected static final VertexInfo vertTmp2;
    protected static final VertexInfo vertTmp3;
    protected static final VertexInfo vertTmp4;
    protected static final VertexInfo vertTmp5;
    protected static final VertexInfo vertTmp6;
    protected static final VertexInfo vertTmp7;
    protected static final VertexInfo vertTmp8;

    static {
        BaseShapeBuilder.tmpColor0 = new Color();
        BaseShapeBuilder.tmpColor1 = new Color();
        BaseShapeBuilder.tmpColor2 = new Color();
        BaseShapeBuilder.tmpColor3 = new Color();
        BaseShapeBuilder.tmpColor4 = new Color();
        BaseShapeBuilder.tmpV0 = new Vector3();
        BaseShapeBuilder.tmpV1 = new Vector3();
        BaseShapeBuilder.tmpV2 = new Vector3();
        BaseShapeBuilder.tmpV3 = new Vector3();
        BaseShapeBuilder.tmpV4 = new Vector3();
        BaseShapeBuilder.tmpV5 = new Vector3();
        BaseShapeBuilder.tmpV6 = new Vector3();
        BaseShapeBuilder.tmpV7 = new Vector3();
        BaseShapeBuilder.vertTmp0 = new VertexInfo();
        BaseShapeBuilder.vertTmp1 = new VertexInfo();
        BaseShapeBuilder.vertTmp2 = new VertexInfo();
        BaseShapeBuilder.vertTmp3 = new VertexInfo();
        BaseShapeBuilder.vertTmp4 = new VertexInfo();
        BaseShapeBuilder.vertTmp5 = new VertexInfo();
        BaseShapeBuilder.vertTmp6 = new VertexInfo();
        BaseShapeBuilder.vertTmp7 = new VertexInfo();
        BaseShapeBuilder.vertTmp8 = new VertexInfo();
        BaseShapeBuilder.matTmp1 = new Matrix4();
        BaseShapeBuilder.vectorPool = new FlushablePool() {
            protected Vector3 newObject() {
                return new Vector3();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        BaseShapeBuilder.matrices4Pool = new FlushablePool() {
            protected Matrix4 newObject() {
                return new Matrix4();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
    }

    protected static void freeAll() {
        BaseShapeBuilder.vectorPool.flush();
        BaseShapeBuilder.matrices4Pool.flush();
    }

    protected static Matrix4 obtainM4() {
        return (Matrix4)BaseShapeBuilder.matrices4Pool.obtain();
    }

    protected static Vector3 obtainV3() {
        return (Vector3)BaseShapeBuilder.vectorPool.obtain();
    }
}

