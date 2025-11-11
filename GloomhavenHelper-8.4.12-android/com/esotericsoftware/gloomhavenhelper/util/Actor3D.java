package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.esotericsoftware.gloomhavenhelper.App;

public class Actor3D extends Actor {
    private final PerspectiveCamera camera;
    private final FrameBuffer fbo;
    private final Plane plane;
    private final Matrix4 projOld;
    private final Matrix4 viewOld;

    public Actor3D(int v, int v1, float f) {
        this.projOld = new Matrix4();
        this.viewOld = new Matrix4();
        this.setSize(((float)v), ((float)v1));
        this.fbo = new FrameBuffer(Format.RGBA8888, v, v1, false);
        this.camera = new PerspectiveCamera();
        this.camera.fieldOfView = f;
        this.camera.near = 1.0f;
        this.camera.far = 1000.0f;
        this.camera.viewportWidth = (float)v;
        this.camera.viewportHeight = (float)v1;
        this.camera.position.set(0.0f, 0.0f, 400.0f);
        this.camera.lookAt(this.camera.position.x, this.camera.position.y, 0.0f);
        this.camera.update(true);
        this.plane = new Plane(App.v3.set(0.0f, 0.0f, 1.0f), this.camera.position.z);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch0, float f) {
        batch0.end();
        Vector2 vector20 = this.localToStageCoordinates(App.v2.set(0.0f, 0.0f));
        App.stage.stageToScreenCoordinates(vector20);
        float f1 = vector20.x;
        float f2 = vector20.y;
        float f3 = (float)App.viewport.getScreenX();
        float f4 = (float)App.viewport.getScreenY();
        float f5 = (float)App.viewport.getScreenWidth();
        float f6 = (float)App.viewport.getScreenHeight();
        Intersector.intersectRayPlane(this.camera.getPickRay(f1, f2, f3, f4, f5, f6), this.plane, App.v3);
        float f7 = App.v3.x;
        float f8 = App.v3.y;
        float f9 = this.getWidth();
        float f10 = this.getHeight();
        Vector2 vector21 = this.localToStageCoordinates(App.v2.set(f9, f10));
        App.stage.stageToScreenCoordinates(vector21);
        float f11 = vector21.x;
        float f12 = vector21.y;
        float f13 = (float)App.viewport.getScreenX();
        float f14 = (float)App.viewport.getScreenY();
        float f15 = (float)App.viewport.getScreenWidth();
        float f16 = (float)App.viewport.getScreenHeight();
        Intersector.intersectRayPlane(this.camera.getPickRay(f11, f12, f13, f14, f15, f16), this.plane, App.v3);
        float f17 = App.v3.x - f7;
        float f18 = App.v3.y - f8;
        this.updateCamera(this.camera, f7, f8, f17, f18);
        Matrix4 matrix40 = batch0.getProjectionMatrix();
        this.projOld.set(matrix40);
        Matrix4 matrix41 = batch0.getTransformMatrix();
        this.viewOld.set(matrix41);
        batch0.setTransformMatrix(this.camera.view);
        batch0.setProjectionMatrix(this.camera.combined);
        batch0.setColor(Color.WHITE);
        batch0.begin();
        batch0.draw(((Texture)this.fbo.getColorBufferTexture()), f7, f8, f17, f18);
        batch0.end();
        batch0.setTransformMatrix(this.viewOld);
        batch0.setProjectionMatrix(this.projOld);
        batch0.begin();
    }

    public void update(Batch batch0, Actor actor0, float f) {
        this.fbo.begin();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(0x4000);
        Matrix4 matrix40 = batch0.getProjectionMatrix();
        this.projOld.set(matrix40);
        Matrix4 matrix41 = batch0.getTransformMatrix();
        this.viewOld.set(matrix41);
        batch0.getTransformMatrix().idt();
        batch0.getProjectionMatrix().setToOrtho2D(0.0f, 0.0f, this.getWidth(), this.getHeight());
        batch0.getTransformMatrix().scale(1.0f, -1.0f, 1.0f).translate(0.0f, -this.getHeight(), 0.0f);
        actor0.setPosition(0.0f, 0.0f);
        batch0.begin();
        actor0.draw(batch0, f);
        batch0.end();
        actor0.setPosition(actor0.getX(), actor0.getY());
        batch0.setTransformMatrix(this.projOld);
        batch0.setProjectionMatrix(this.viewOld);
        this.fbo.end();
        App.stage.getViewport().apply(true);
    }

    protected void updateCamera(PerspectiveCamera perspectiveCamera0, float f, float f1, float f2, float f3) {
    }
}

