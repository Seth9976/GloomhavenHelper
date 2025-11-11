package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public abstract class Camera {
    public final Matrix4 combined;
    public final Vector3 direction;
    public float far;
    public final Frustum frustum;
    public final Matrix4 invProjectionView;
    public float near;
    public final Vector3 position;
    public final Matrix4 projection;
    private final Ray ray;
    private final Vector3 tmpVec;
    public final Vector3 up;
    public final Matrix4 view;
    public float viewportHeight;
    public float viewportWidth;

    public Camera() {
        this.position = new Vector3();
        this.direction = new Vector3(0.0f, 0.0f, -1.0f);
        this.up = new Vector3(0.0f, 1.0f, 0.0f);
        this.projection = new Matrix4();
        this.view = new Matrix4();
        this.combined = new Matrix4();
        this.invProjectionView = new Matrix4();
        this.near = 1.0f;
        this.far = 100.0f;
        this.viewportWidth = 0.0f;
        this.viewportHeight = 0.0f;
        this.frustum = new Frustum();
        this.tmpVec = new Vector3();
        this.ray = new Ray(new Vector3(), new Vector3());
    }

    public Ray getPickRay(float f, float f1) {
        return this.getPickRay(f, f1, 0.0f, 0.0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
    }

    public Ray getPickRay(float f, float f1, float f2, float f3, float f4, float f5) {
        this.unproject(this.ray.origin.set(f, f1, 0.0f), f2, f3, f4, f5);
        this.unproject(this.ray.direction.set(f, f1, 1.0f), f2, f3, f4, f5);
        this.ray.direction.sub(this.ray.origin).nor();
        return this.ray;
    }

    public void lookAt(float f, float f1, float f2) {
        this.tmpVec.set(f, f1, f2).sub(this.position).nor();
        if(!this.tmpVec.isZero()) {
            float f3 = this.tmpVec.dot(this.up);
            if(Math.abs(f3 - 1.0f) < 1.000000E-09f) {
                this.up.set(this.direction).scl(-1.0f);
            }
            else if(Math.abs(f3 + 1.0f) < 1.000000E-09f) {
                this.up.set(this.direction);
            }
            this.direction.set(this.tmpVec);
            this.normalizeUp();
        }
    }

    public void lookAt(Vector3 vector30) {
        this.lookAt(vector30.x, vector30.y, vector30.z);
    }

    public void normalizeUp() {
        this.tmpVec.set(this.direction).crs(this.up);
        this.up.set(this.tmpVec).crs(this.direction).nor();
    }

    public Vector3 project(Vector3 vector30) {
        this.project(vector30, 0.0f, 0.0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
        return vector30;
    }

    public Vector3 project(Vector3 vector30, float f, float f1, float f2, float f3) {
        vector30.prj(this.combined);
        vector30.x = f2 * (vector30.x + 1.0f) / 2.0f + f;
        vector30.y = f3 * (vector30.y + 1.0f) / 2.0f + f1;
        vector30.z = (vector30.z + 1.0f) / 2.0f;
        return vector30;
    }

    public void rotate(float f, float f1, float f2, float f3) {
        this.direction.rotate(f, f1, f2, f3);
        this.up.rotate(f, f1, f2, f3);
    }

    public void rotate(Matrix4 matrix40) {
        this.direction.rot(matrix40);
        this.up.rot(matrix40);
    }

    public void rotate(Quaternion quaternion0) {
        quaternion0.transform(this.direction);
        quaternion0.transform(this.up);
    }

    public void rotate(Vector3 vector30, float f) {
        this.direction.rotate(vector30, f);
        this.up.rotate(vector30, f);
    }

    public void rotateAround(Vector3 vector30, Vector3 vector31, float f) {
        this.tmpVec.set(vector30);
        this.tmpVec.sub(this.position);
        this.translate(this.tmpVec);
        this.rotate(vector31, f);
        this.tmpVec.rotate(vector31, f);
        this.translate(-this.tmpVec.x, -this.tmpVec.y, -this.tmpVec.z);
    }

    public void transform(Matrix4 matrix40) {
        this.position.mul(matrix40);
        this.rotate(matrix40);
    }

    public void translate(float f, float f1, float f2) {
        this.position.add(f, f1, f2);
    }

    public void translate(Vector3 vector30) {
        this.position.add(vector30);
    }

    public Vector3 unproject(Vector3 vector30) {
        this.unproject(vector30, 0.0f, 0.0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
        return vector30;
    }

    public Vector3 unproject(Vector3 vector30, float f, float f1, float f2, float f3) {
        float f4 = vector30.y;
        float f5 = vector30.x - f;
        float f6 = ((float)Gdx.graphics.getHeight()) - f4 - f1;
        vector30.x = f5 * 2.0f / f2 - 1.0f;
        vector30.y = f6 * 2.0f / f3 - 1.0f;
        vector30.z = vector30.z * 2.0f - 1.0f;
        vector30.prj(this.invProjectionView);
        return vector30;
    }

    public abstract void update();

    public abstract void update(boolean arg1);
}

