package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public abstract class BaseShaderProvider implements ShaderProvider {
   protected Array shaders = new Array();

   @Override
   public Shader getShader(Renderable renderable) {
      Shader suggestedShader = renderable.shader;
      if (suggestedShader != null && suggestedShader.canRender(renderable)) {
         return suggestedShader;
      } else {
         for (Shader shader : this.shaders) {
            if (shader.canRender(renderable)) {
               return shader;
            }
         }

         Shader shaderx = this.createShader(renderable);
         if (!shaderx.canRender(renderable)) {
            throw new GdxRuntimeException("unable to provide a shader for this renderable");
         } else {
            shaderx.init();
            this.shaders.add(shaderx);
            return shaderx;
         }
      }
   }

   protected abstract Shader createShader(Renderable var1);

   @Override
   public void dispose() {
      for (Shader shader : this.shaders) {
         shader.dispose();
      }

      this.shaders.clear();
   }
}
