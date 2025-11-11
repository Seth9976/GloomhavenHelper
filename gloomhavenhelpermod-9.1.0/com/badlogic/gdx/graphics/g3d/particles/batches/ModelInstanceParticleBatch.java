package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ModelInstanceControllerRenderData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ModelInstanceParticleBatch implements ParticleBatch {
   Array controllersRenderData = new Array(false, 5);
   int bufferedParticlesCount;

   @Override
   public void getRenderables(Array renderables, Pool pool) {
      for (ModelInstanceControllerRenderData data : this.controllersRenderData) {
         int i = 0;

         for (int count = data.controller.particles.size; i < count; i++) {
            ((ModelInstance[])data.modelInstanceChannel.data)[i].getRenderables(renderables, pool);
         }
      }
   }

   public int getBufferedCount() {
      return this.bufferedParticlesCount;
   }

   @Override
   public void begin() {
      this.controllersRenderData.clear();
      this.bufferedParticlesCount = 0;
   }

   @Override
   public void end() {
   }

   public void draw(ModelInstanceControllerRenderData data) {
      this.controllersRenderData.add(data);
      this.bufferedParticlesCount = this.bufferedParticlesCount + data.controller.particles.size;
   }

   @Override
   public void save(AssetManager manager, ResourceData assetDependencyData) {
   }

   @Override
   public void load(AssetManager manager, ResourceData assetDependencyData) {
   }
}
