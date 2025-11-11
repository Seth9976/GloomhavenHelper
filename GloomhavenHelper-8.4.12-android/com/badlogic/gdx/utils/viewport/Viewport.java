package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public abstract class Viewport {
    private Camera camera;
    private int screenHeight;
    private int screenWidth;
    private int screenX;
    private int screenY;
    private final Vector3 tmp;
    private float worldHeight;
    private float worldWidth;

    public Viewport() {
        this.tmp = new Vector3();
    }

    public void apply() {
        this.apply(false);
    }

    public void apply(boolean z) {
        HdpiUtils.glViewport(this.screenX, this.screenY, this.screenWidth, this.screenHeight);
        Camera camera0 = this.camera;
        camera0.viewportWidth = this.worldWidth;
        camera0.viewportHeight = this.worldHeight;
        if(z) {
            camera0.position.set(this.worldWidth / 2.0f, this.worldHeight / 2.0f, 0.0f);
        }
        this.camera.update();
    }

    public void calculateScissors(Matrix4 matrix40, Rectangle rectangle0, Rectangle rectangle1) {
        ScissorStack.calculateScissors(this.camera, ((float)this.screenX), ((float)this.screenY), ((float)this.screenWidth), ((float)this.screenHeight), matrix40, rectangle0, rectangle1);
    }

    public int getBottomGutterHeight() {
        return this.screenY;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public int getLeftGutterWidth() {
        return this.screenX;
    }

    public Ray getPickRay(float f, float f1) {
        return this.camera.getPickRay(f, f1, ((float)this.screenX), ((float)this.screenY), ((float)this.screenWidth), ((float)this.screenHeight));
    }

    public int getRightGutterWidth() {
        return Gdx.graphics.getWidth() - (this.screenX + this.screenWidth);
    }

    public int getRightGutterX() {
        return this.screenX + this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

    public int getTopGutterHeight() {
        return Gdx.graphics.getHeight() - (this.screenY + this.screenHeight);
    }

    public int getTopGutterY() {
        return this.screenY + this.screenHeight;
    }

    public float getWorldHeight() {
        return this.worldHeight;
    }

    public float getWorldWidth() {
        return this.worldWidth;
    }

    public Vector2 project(Vector2 vector20) {
        this.tmp.set(vector20.x, vector20.y, 1.0f);
        this.camera.project(this.tmp, ((float)this.screenX), ((float)this.screenY), ((float)this.screenWidth), ((float)this.screenHeight));
        vector20.set(this.tmp.x, this.tmp.y);
        return vector20;
    }

    public Vector3 project(Vector3 vector30) {
        this.camera.project(vector30, ((float)this.screenX), ((float)this.screenY), ((float)this.screenWidth), ((float)this.screenHeight));
        return vector30;
    }

    public void setCamera(Camera camera0) {
        this.camera = camera0;
    }

    public void setScreenBounds(int v, int v1, int v2, int v3) {
        this.screenX = v;
        this.screenY = v1;
        this.screenWidth = v2;
        this.screenHeight = v3;
    }

    public void setScreenHeight(int v) {
        this.screenHeight = v;
    }

    public void setScreenPosition(int v, int v1) {
        this.screenX = v;
        this.screenY = v1;
    }

    public void setScreenSize(int v, int v1) {
        this.screenWidth = v;
        this.screenHeight = v1;
    }

    public void setScreenWidth(int v) {
        this.screenWidth = v;
    }

    public void setScreenX(int v) {
        this.screenX = v;
    }

    public void setScreenY(int v) {
        this.screenY = v;
    }

    public void setWorldHeight(float f) {
        this.worldHeight = f;
    }

    public void setWorldSize(float f, float f1) {
        this.worldWidth = f;
        this.worldHeight = f1;
    }

    public void setWorldWidth(float f) {
        this.worldWidth = f;
    }

    public Vector2 toScreenCoordinates(Vector2 vector20, Matrix4 matrix40) {
        this.tmp.set(vector20.x, vector20.y, 0.0f);
        this.tmp.mul(matrix40);
        this.camera.project(this.tmp, ((float)this.screenX), ((float)this.screenY), ((float)this.screenWidth), ((float)this.screenHeight));
        this.tmp.y = ((float)Gdx.graphics.getHeight()) - this.tmp.y;
        vector20.x = this.tmp.x;
        vector20.y = this.tmp.y;
        return vector20;
    }

    public Vector2 unproject(Vector2 vector20) {
        this.tmp.set(vector20.x, vector20.y, 1.0f);
        this.camera.unproject(this.tmp, ((float)this.screenX), ((float)this.screenY), ((float)this.screenWidth), ((float)this.screenHeight));
        vector20.set(this.tmp.x, this.tmp.y);
        return vector20;
    }

    public Vector3 unproject(Vector3 vector30) {
        this.camera.unproject(vector30, ((float)this.screenX), ((float)this.screenY), ((float)this.screenWidth), ((float)this.screenHeight));
        return vector30;
    }

    public final void update(int v, int v1) {
        this.update(v, v1, false);
    }

    public void update(int v, int v1, boolean z) {
        this.apply(z);
    }
}

