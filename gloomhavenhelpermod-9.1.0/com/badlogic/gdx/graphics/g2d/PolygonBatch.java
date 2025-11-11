package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Texture;

public interface PolygonBatch extends Batch {
   void draw(PolygonRegion var1, float var2, float var3);

   void draw(PolygonRegion var1, float var2, float var3, float var4, float var5);

   void draw(PolygonRegion var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10);

   void draw(Texture var1, float[] var2, int var3, int var4, short[] var5, int var6, int var7);
}
