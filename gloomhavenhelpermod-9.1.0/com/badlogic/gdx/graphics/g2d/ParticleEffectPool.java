package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ParticleEffectPool extends Pool {
   private final ParticleEffect effect;

   public ParticleEffectPool(ParticleEffect effect, int initialCapacity, int max) {
      super(initialCapacity, max);
      this.effect = effect;
   }

   protected ParticleEffectPool.PooledEffect newObject() {
      ParticleEffectPool.PooledEffect pooledEffect = new ParticleEffectPool.PooledEffect(this.effect);
      pooledEffect.start();
      return pooledEffect;
   }

   public void free(ParticleEffectPool.PooledEffect effect) {
      super.free(effect);
      effect.reset(false);
      if (effect.xSizeScale != this.effect.xSizeScale || effect.ySizeScale != this.effect.ySizeScale || effect.motionScale != this.effect.motionScale) {
         Array emitters = effect.getEmitters();
         Array templateEmitters = this.effect.getEmitters();

         for (int i = 0; i < emitters.size; i++) {
            ParticleEmitter emitter = (ParticleEmitter)emitters.get(i);
            ParticleEmitter templateEmitter = (ParticleEmitter)templateEmitters.get(i);
            emitter.matchSize(templateEmitter);
            emitter.matchMotion(templateEmitter);
         }

         effect.xSizeScale = this.effect.xSizeScale;
         effect.ySizeScale = this.effect.ySizeScale;
         effect.motionScale = this.effect.motionScale;
      }
   }

   public class PooledEffect extends ParticleEffect {
      PooledEffect(ParticleEffect effect) {
         super(effect);
      }

      public void free() {
         ParticleEffectPool.this.free(this);
      }
   }
}
