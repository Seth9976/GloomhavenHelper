package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;

public class CompositeDrawable extends BaseDrawable implements TransformDrawable {
   private Drawable[] drawables;

   public CompositeDrawable(Drawable... drawables) {
      this.drawables = drawables;
   }

   @Override
   public void draw(Batch batch, float x, float y, float width, float height) {
      for (Drawable drawable : this.drawables) {
         drawable.draw(batch, x, y, width, height);
      }
   }

   @Override
   public void draw(Batch batch, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation) {
      for (Drawable drawable : this.drawables) {
         ((TransformDrawable)drawable).draw(batch, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
      }
   }

   @Override
   public float getMinWidth() {
      return this.drawables[0].getMinWidth();
   }

   @Override
   public float getMinHeight() {
      return this.drawables[0].getMinHeight();
   }
}
