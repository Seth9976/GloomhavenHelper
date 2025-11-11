package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.hm.gloomhavenhelper.App;

public class DesatDrawable extends CompositeDrawable {
   public DesatDrawable(Drawable... drawables) {
      super(drawables);
   }

   @Override
   public void draw(Batch batch, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation) {
      batch.setShader(App.desatShader);
      App.desatShader.setUniformf("u_desat", 1.0F);
      super.draw(batch, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
      batch.setShader(null);
   }

   @Override
   public void draw(Batch batch, float x, float y, float width, float height) {
      batch.setShader(App.desatShader);
      App.desatShader.setUniformf("u_desat", 1.0F);
      super.draw(batch, x, y, width, height);
      batch.setShader(null);
   }
}
