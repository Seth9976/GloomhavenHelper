package com.badlogic.gdx;

public abstract class AbstractGraphics implements Graphics {
   @Override
   public float getRawDeltaTime() {
      return this.getDeltaTime();
   }

   @Override
   public float getDensity() {
      return this.getPpiX() / 160.0F;
   }

   @Override
   public float getBackBufferScale() {
      return (float)this.getBackBufferWidth() / this.getWidth();
   }
}
