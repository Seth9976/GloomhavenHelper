package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;

public abstract class ParticleControllerRenderer extends ParticleControllerComponent {
   protected ParticleBatch batch;
   protected ParticleControllerRenderData renderData;

   protected ParticleControllerRenderer() {
   }

   protected ParticleControllerRenderer(ParticleControllerRenderData renderData) {
      this.renderData = renderData;
   }

   @Override
   public void update() {
      this.batch.draw(this.renderData);
   }

   public boolean setBatch(ParticleBatch batch) {
      if (this.isCompatible(batch)) {
         this.batch = batch;
         return true;
      } else {
         return false;
      }
   }

   public abstract boolean isCompatible(ParticleBatch var1);

   @Override
   public void set(ParticleController particleController) {
      super.set(particleController);
      if (this.renderData != null) {
         this.renderData.controller = this.controller;
      }
   }
}
