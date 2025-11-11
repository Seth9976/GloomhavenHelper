package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hm.gloomhavenhelper.App;

public class Actor3D extends Actor {
   private final FrameBuffer fbo;
   private final PerspectiveCamera camera;
   private final Plane plane;
   private final Matrix4 projOld = new Matrix4();
   private final Matrix4 viewOld = new Matrix4();

   public Actor3D(int width, int height, float fieldOfView) {
      this.setSize(width, height);
      this.fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
      this.camera = new PerspectiveCamera();
      this.camera.fieldOfView = fieldOfView;
      this.camera.near = 1.0F;
      this.camera.far = 1000.0F;
      this.camera.viewportWidth = width;
      this.camera.viewportHeight = height;
      this.camera.position.set(0.0F, 0.0F, 400.0F);
      this.camera.lookAt(this.camera.position.x, this.camera.position.y, 0.0F);
      this.camera.update(true);
      this.plane = new Plane(App.v3.set(0.0F, 0.0F, 1.0F), this.camera.position.z);
   }

   public void update(Batch batch, Actor actor, float parentAlpha) {
      this.fbo.begin();
      Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
      Gdx.gl.glClear(16384);
      this.projOld.set(batch.getProjectionMatrix());
      this.viewOld.set(batch.getTransformMatrix());
      batch.getTransformMatrix().idt();
      batch.getProjectionMatrix().setToOrtho2D(0.0F, 0.0F, this.getWidth(), this.getHeight());
      batch.getTransformMatrix().scale(1.0F, -1.0F, 1.0F).translate(0.0F, -this.getHeight(), 0.0F);
      float x = actor.getX();
      float y = actor.getY();
      actor.setPosition(0.0F, 0.0F);
      batch.begin();
      actor.draw(batch, parentAlpha);
      batch.end();
      actor.setPosition(x, y);
      batch.setTransformMatrix(this.projOld);
      batch.setProjectionMatrix(this.viewOld);
      this.fbo.end();
      App.stage.getViewport().apply(true);
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      batch.end();
      Vector2 bottomLeft = this.localToStageCoordinates(App.v2.set(0.0F, 0.0F));
      App.stage.stageToScreenCoordinates(bottomLeft);
      Intersector.intersectRayPlane(
         this.camera
            .getPickRay(
               bottomLeft.x, bottomLeft.y, App.viewport.getScreenX(), App.viewport.getScreenY(), App.viewport.getScreenWidth(), App.viewport.getScreenHeight()
            ),
         this.plane,
         App.v3
      );
      float x = App.v3.x;
      float y = App.v3.y;
      Vector2 upperRight = this.localToStageCoordinates(App.v2.set(this.getWidth(), this.getHeight()));
      App.stage.stageToScreenCoordinates(upperRight);
      Intersector.intersectRayPlane(
         this.camera
            .getPickRay(
               upperRight.x, upperRight.y, App.viewport.getScreenX(), App.viewport.getScreenY(), App.viewport.getScreenWidth(), App.viewport.getScreenHeight()
            ),
         this.plane,
         App.v3
      );
      float w = App.v3.x - x;
      float h = App.v3.y - y;
      this.updateCamera(this.camera, x, y, w, h);
      this.projOld.set(batch.getProjectionMatrix());
      this.viewOld.set(batch.getTransformMatrix());
      batch.setTransformMatrix(this.camera.view);
      batch.setProjectionMatrix(this.camera.combined);
      batch.setColor(Color.WHITE);
      batch.begin();
      batch.draw((Texture)this.fbo.getColorBufferTexture(), x, y, w, h);
      batch.end();
      batch.setTransformMatrix(this.viewOld);
      batch.setProjectionMatrix(this.projOld);
      batch.begin();
   }

   protected void updateCamera(PerspectiveCamera camera, float x, float y, float w, float h) {
   }
}
