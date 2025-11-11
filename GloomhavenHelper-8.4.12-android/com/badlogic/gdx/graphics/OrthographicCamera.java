package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class OrthographicCamera extends Camera {
    private final Vector3 tmp;
    public float zoom;

    public OrthographicCamera() {
        this.zoom = 1.0f;
        this.tmp = new Vector3();
        this.near = 0.0f;
    }

    public OrthographicCamera(float f, float f1) {
        this.zoom = 1.0f;
        this.tmp = new Vector3();
        this.viewportWidth = f;
        this.viewportHeight = f1;
        this.near = 0.0f;
        this.update();
    }

    public void rotate(float f) {
        this.rotate(this.direction, f);
    }

    public void setToOrtho(boolean z) {
        this.setToOrtho(z, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
    }

    public void setToOrtho(boolean z, float f, float f1) {
        if(z) {
            this.up.set(0.0f, -1.0f, 0.0f);
            this.direction.set(0.0f, 0.0f, 1.0f);
        }
        else {
            this.up.set(0.0f, 1.0f, 0.0f);
            this.direction.set(0.0f, 0.0f, -1.0f);
        }
        this.position.set(this.zoom * f / 2.0f, this.zoom * f1 / 2.0f, 0.0f);
        this.viewportWidth = f;
        this.viewportHeight = f1;
        this.update();
    }

    public void translate(float f, float f1) {
        this.translate(f, f1, 0.0f);
    }

    public void translate(Vector2 vector20) {
        this.translate(vector20.x, vector20.y, 0.0f);
    }

    @Override  // com.badlogic.gdx.graphics.Camera
    public void update() {
        this.update(true);
    }

    @Override  // com.badlogic.gdx.graphics.Camera
    public void update(boolean z) {
        this.projection.setToOrtho(this.zoom * -this.viewportWidth / 2.0f, this.zoom * (this.viewportWidth / 2.0f), this.zoom * -(this.viewportHeight / 2.0f), this.zoom * this.viewportHeight / 2.0f, this.near, this.far);
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

