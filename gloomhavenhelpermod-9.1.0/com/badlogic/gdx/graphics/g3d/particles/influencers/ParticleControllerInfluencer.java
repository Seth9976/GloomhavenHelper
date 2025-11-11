package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Pool;
import java.util.Iterator;

public abstract class ParticleControllerInfluencer extends Influencer {
   public Array templates;
   ParallelArray.ObjectChannel particleControllerChannel;

   public ParticleControllerInfluencer() {
      this.templates = new Array(true, 1, ParticleController.class);
   }

   public ParticleControllerInfluencer(ParticleController... templates) {
      this.templates = new Array(templates);
   }

   public ParticleControllerInfluencer(ParticleControllerInfluencer influencer) {
      this((ParticleController[])influencer.templates.items);
   }

   @Override
   public void allocateChannels() {
      this.particleControllerChannel = (ParallelArray.ObjectChannel)this.controller.particles.addChannel(ParticleChannels.ParticleController);
   }

   @Override
   public void end() {
      for (int i = 0; i < this.controller.particles.size; i++) {
         ((ParticleController[])this.particleControllerChannel.data)[i].end();
      }
   }

   @Override
   public void dispose() {
      if (this.controller != null) {
         for (int i = 0; i < this.controller.particles.size; i++) {
            ParticleController controller = ((ParticleController[])this.particleControllerChannel.data)[i];
            if (controller != null) {
               controller.dispose();
               ((ParticleController[])this.particleControllerChannel.data)[i] = null;
            }
         }
      }
   }

   @Override
   public void save(AssetManager manager, ResourceData resources) {
      ResourceData.SaveData data = resources.createSaveData();
      Array effects = manager.getAll(ParticleEffect.class, new Array());
      Array controllers = new Array(this.templates);
      Array effectsIndices = new Array();

      for (int i = 0; i < effects.size && controllers.size > 0; i++) {
         ParticleEffect effect = (ParticleEffect)effects.get(i);
         Array effectControllers = effect.getControllers();
         Iterator iterator = controllers.iterator();
         IntArray indices = null;

         while (iterator.hasNext()) {
            ParticleController controller = (ParticleController)iterator.next();
            int index = -1;
            if ((index = effectControllers.indexOf(controller, true)) > -1) {
               if (indices == null) {
                  indices = new IntArray();
               }

               iterator.remove();
               indices.add(index);
            }
         }

         if (indices != null) {
            data.saveAsset(manager.getAssetFileName(effect), ParticleEffect.class);
            effectsIndices.add(indices);
         }
      }

      data.save("indices", effectsIndices);
   }

   @Override
   public void load(AssetManager manager, ResourceData resources) {
      ResourceData.SaveData data = resources.getSaveData();
      Array effectsIndices = (Array<IntArray>)data.load("indices");
      Iterator iterator = effectsIndices.iterator();

      AssetDescriptor descriptor;
      while ((descriptor = data.loadAsset()) != null) {
         ParticleEffect effect = (ParticleEffect)manager.get(descriptor);
         if (effect == null) {
            throw new RuntimeException("Template is null");
         }

         Array effectControllers = effect.getControllers();
         IntArray effectIndices = (IntArray)iterator.next();
         int i = 0;

         for (int n = effectIndices.size; i < n; i++) {
            this.templates.add(effectControllers.get(effectIndices.get(i)));
         }
      }
   }

   public static class Random extends ParticleControllerInfluencer {
      ParticleControllerInfluencer.Random.ParticleControllerPool pool = new ParticleControllerInfluencer.Random.ParticleControllerPool();

      public Random() {
      }

      public Random(ParticleController... templates) {
         super(templates);
      }

      public Random(ParticleControllerInfluencer.Random particleControllerRandom) {
         super(particleControllerRandom);
      }

      @Override
      public void init() {
         this.pool.clear();

         for (int i = 0; i < this.controller.emitter.maxParticleCount; i++) {
            this.pool.free(this.pool.newObject());
         }
      }

      @Override
      public void dispose() {
         this.pool.clear();
         super.dispose();
      }

      @Override
      public void activateParticles(int startIndex, int count) {
         int i = startIndex;

         for (int c = startIndex + count; i < c; i++) {
            ParticleController controller = (ParticleController)this.pool.obtain();
            controller.start();
            ((ParticleController[])this.particleControllerChannel.data)[i] = controller;
         }
      }

      @Override
      public void killParticles(int startIndex, int count) {
         int i = startIndex;

         for (int c = startIndex + count; i < c; i++) {
            ParticleController controller = ((ParticleController[])this.particleControllerChannel.data)[i];
            controller.end();
            this.pool.free(controller);
            ((ParticleController[])this.particleControllerChannel.data)[i] = null;
         }
      }

      public ParticleControllerInfluencer.Random copy() {
         return new ParticleControllerInfluencer.Random(this);
      }

      private class ParticleControllerPool extends Pool {
         public ParticleControllerPool() {
         }

         public ParticleController newObject() {
            ParticleController controller = ((ParticleController)Random.this.templates.random()).copy();
            controller.init();
            return controller;
         }

         @Override
         public void clear() {
            int i = 0;

            for (int free = Random.this.pool.getFree(); i < free; i++) {
               ((ParticleController)Random.this.pool.obtain()).dispose();
            }

            super.clear();
         }
      }
   }

   public static class Single extends ParticleControllerInfluencer {
      public Single(ParticleController... templates) {
         super(templates);
      }

      public Single() {
      }

      public Single(ParticleControllerInfluencer.Single particleControllerSingle) {
         super(particleControllerSingle);
      }

      @Override
      public void init() {
         ParticleController first = (ParticleController)this.templates.first();
         int i = 0;

         for (int c = this.controller.particles.capacity; i < c; i++) {
            ParticleController copy = first.copy();
            copy.init();
            ((ParticleController[])this.particleControllerChannel.data)[i] = copy;
         }
      }

      @Override
      public void activateParticles(int startIndex, int count) {
         int i = startIndex;

         for (int c = startIndex + count; i < c; i++) {
            ((ParticleController[])this.particleControllerChannel.data)[i].start();
         }
      }

      @Override
      public void killParticles(int startIndex, int count) {
         int i = startIndex;

         for (int c = startIndex + count; i < c; i++) {
            ((ParticleController[])this.particleControllerChannel.data)[i].end();
         }
      }

      public ParticleControllerInfluencer.Single copy() {
         return new ParticleControllerInfluencer.Single(this);
      }
   }
}
