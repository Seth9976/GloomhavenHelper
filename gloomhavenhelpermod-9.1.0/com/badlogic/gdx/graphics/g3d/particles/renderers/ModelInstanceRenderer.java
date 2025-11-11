package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ModelInstanceParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;

public class ModelInstanceRenderer extends ParticleControllerRenderer {
   private boolean hasColor;
   private boolean hasScale;
   private boolean hasRotation;

   public ModelInstanceRenderer() {
      super(new ModelInstanceControllerRenderData());
   }

   public ModelInstanceRenderer(ModelInstanceParticleBatch batch) {
      this();
      this.setBatch(batch);
   }

   @Override
   public void allocateChannels() {
      ((ModelInstanceControllerRenderData)this.renderData).positionChannel = (ParallelArray.FloatChannel)this.controller
         .particles
         .addChannel(ParticleChannels.Position);
   }

   @Override
   public void init() {
      ((ModelInstanceControllerRenderData)this.renderData).modelInstanceChannel = (ParallelArray.ObjectChannel)this.controller
         .particles
         .getChannel(ParticleChannels.ModelInstance);
      ((ModelInstanceControllerRenderData)this.renderData).colorChannel = (ParallelArray.FloatChannel)this.controller
         .particles
         .getChannel(ParticleChannels.Color);
      ((ModelInstanceControllerRenderData)this.renderData).scaleChannel = (ParallelArray.FloatChannel)this.controller
         .particles
         .getChannel(ParticleChannels.Scale);
      ((ModelInstanceControllerRenderData)this.renderData).rotationChannel = (ParallelArray.FloatChannel)this.controller
         .particles
         .getChannel(ParticleChannels.Rotation3D);
      this.hasColor = ((ModelInstanceControllerRenderData)this.renderData).colorChannel != null;
      this.hasScale = ((ModelInstanceControllerRenderData)this.renderData).scaleChannel != null;
      this.hasRotation = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel != null;
   }

   @Override
   public void update() {
      int i = 0;
      int positionOffset = 0;

      for (int c = this.controller.particles.size; i < c; positionOffset += ((ModelInstanceControllerRenderData)this.renderData).positionChannel.strideSize) {
         ModelInstance instance = ((ModelInstance[])((ModelInstanceControllerRenderData)this.renderData).modelInstanceChannel.data)[i];
         float scale = this.hasScale ? ((ModelInstanceControllerRenderData)this.renderData).scaleChannel.data[i] : 1.0F;
         float qx = 0.0F;
         float qy = 0.0F;
         float qz = 0.0F;
         float qw = 1.0F;
         if (this.hasRotation) {
            int rotationOffset = i * ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.strideSize;
            qx = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.data[rotationOffset + 0];
            qy = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.data[rotationOffset + 1];
            qz = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.data[rotationOffset + 2];
            qw = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.data[rotationOffset + 3];
         }

         instance.transform
            .set(
               ((ModelInstanceControllerRenderData)this.renderData).positionChannel.data[positionOffset + 0],
               ((ModelInstanceControllerRenderData)this.renderData).positionChannel.data[positionOffset + 1],
               ((ModelInstanceControllerRenderData)this.renderData).positionChannel.data[positionOffset + 2],
               qx,
               qy,
               qz,
               qw,
               scale,
               scale,
               scale
            );
         if (this.hasColor) {
            int colorOffset = i * ((ModelInstanceControllerRenderData)this.renderData).colorChannel.strideSize;
            ColorAttribute colorAttribute = (ColorAttribute)((Material)instance.materials.get(0)).get(ColorAttribute.Diffuse);
            BlendingAttribute blendingAttribute = (BlendingAttribute)((Material)instance.materials.get(0)).get(BlendingAttribute.Type);
            colorAttribute.color.r = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.data[colorOffset + 0];
            colorAttribute.color.g = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.data[colorOffset + 1];
            colorAttribute.color.b = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.data[colorOffset + 2];
            if (blendingAttribute != null) {
               blendingAttribute.opacity = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.data[colorOffset + 3];
            }
         }

         i++;
      }

      super.update();
   }

   @Override
   public ParticleControllerComponent copy() {
      return new ModelInstanceRenderer((ModelInstanceParticleBatch)this.batch);
   }

   @Override
   public boolean isCompatible(ParticleBatch batch) {
      return batch instanceof ModelInstanceParticleBatch;
   }
}
