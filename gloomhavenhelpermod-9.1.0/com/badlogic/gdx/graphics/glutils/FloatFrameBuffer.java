package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FloatFrameBuffer extends FrameBuffer {
   FloatFrameBuffer() {
   }

   protected FloatFrameBuffer(GLFrameBuffer.GLFrameBufferBuilder bufferBuilder) {
      super(bufferBuilder);
   }

   public FloatFrameBuffer(int width, int height, boolean hasDepth) {
      GLFrameBuffer.FloatFrameBufferBuilder bufferBuilder = new GLFrameBuffer.FloatFrameBufferBuilder(width, height);
      bufferBuilder.addFloatAttachment(34836, 6408, 5126, false);
      if (hasDepth) {
         bufferBuilder.addBasicDepthRenderBuffer();
      }

      this.bufferBuilder = bufferBuilder;
      this.build();
   }

   @Override
   protected Texture createTexture(GLFrameBuffer.FrameBufferTextureAttachmentSpec attachmentSpec) {
      FloatTextureData data = new FloatTextureData(
         this.bufferBuilder.width,
         this.bufferBuilder.height,
         attachmentSpec.internalFormat,
         attachmentSpec.format,
         attachmentSpec.type,
         attachmentSpec.isGpuOnly
      );
      Texture result = new Texture(data);
      if (Gdx.app.getType() != Application.ApplicationType.Desktop && Gdx.app.getType() != Application.ApplicationType.Applet) {
         result.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
      } else {
         result.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      }

      result.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
      return result;
   }
}
