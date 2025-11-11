package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;

public abstract class BaseLight {
   public final Color color = new Color(0.0F, 0.0F, 0.0F, 1.0F);

   public BaseLight setColor(float r, float g, float b, float a) {
      this.color.set(r, g, b, a);
      return this;
   }

   public BaseLight setColor(Color color) {
      this.color.set(color);
      return this;
   }
}
