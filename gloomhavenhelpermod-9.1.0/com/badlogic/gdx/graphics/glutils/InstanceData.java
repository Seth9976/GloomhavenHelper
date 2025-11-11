package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.Disposable;
import java.nio.FloatBuffer;

public interface InstanceData extends Disposable {
   int getNumInstances();

   int getNumMaxInstances();

   VertexAttributes getAttributes();

   void setInstanceData(float[] var1, int var2, int var3);

   void updateInstanceData(int var1, float[] var2, int var3, int var4);

   void setInstanceData(FloatBuffer var1, int var2);

   void updateInstanceData(int var1, FloatBuffer var2, int var3, int var4);

   FloatBuffer getBuffer();

   void bind(ShaderProgram var1);

   void bind(ShaderProgram var1, int[] var2);

   void unbind(ShaderProgram var1);

   void unbind(ShaderProgram var1, int[] var2);

   void invalidate();

   @Override
   void dispose();
}
