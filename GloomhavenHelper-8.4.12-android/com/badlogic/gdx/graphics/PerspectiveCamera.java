package com.badlogic.gdx.graphics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class PerspectiveCamera extends Camera {
    public float fieldOfView;
    final Vector3 tmp;

    public PerspectiveCamera() {
        this.fieldOfView = 67.0f;
        this.tmp = new Vector3();
    }

    public PerspectiveCamera(float f, float f1, float f2) {
        this.fieldOfView = 67.0f;
        this.tmp = new Vector3();
        this.fieldOfView = f;
        this.viewportWidth = f1;
        this.viewportHeight = f2;
        this.update();
    }

    @Override  // com.badlogic.gdx.graphics.Camera
    public void update() {
        this.update(true);
    }

    @Override  // com.badlogic.gdx.graphics.Camera
    public void update(boolean z) {
        this.projection.setToProjection(Math.abs(this.near), Math.abs(this.far), this.fieldOfView, this.viewportWidth / this.viewportHeight);
        this.view.setToLookAt(this.position, this.tmp.set(this.position).add(this.direction), this.up);
        this.combined.set(this.projection);
        Matrix4.mul(this.combined.val, this.view.val);
        if(z) {
            this.invProjectionView.set(this.combined);
            Matrix4.inv(this.invProjectionView.val);
            this.frustum.update(this.invProjectionView);
        }
    }
}

