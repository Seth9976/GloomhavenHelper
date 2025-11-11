package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ParticleEffectPool extends Pool {
    public class PooledEffect extends ParticleEffect {
        PooledEffect(ParticleEffect particleEffect0) {
            super(particleEffect0);
        }

        public void free() {
            ParticleEffectPool.this.free(this);
        }
    }

    private final ParticleEffect effect;

    public ParticleEffectPool(ParticleEffect particleEffect0, int v, int v1) {
        super(v, v1);
        this.effect = particleEffect0;
    }

    public void free(PooledEffect particleEffectPool$PooledEffect0) {
        super.free(particleEffectPool$PooledEffect0);
        particleEffectPool$PooledEffect0.reset(false);
        if(particleEffectPool$PooledEffect0.xSizeScale != this.effect.xSizeScale || particleEffectPool$PooledEffect0.ySizeScale != this.effect.ySizeScale || particleEffectPool$PooledEffect0.motionScale != this.effect.motionScale) {
            Array array0 = particleEffectPool$PooledEffect0.getEmitters();
            Array array1 = this.effect.getEmitters();
            for(int v = 0; v < array0.size; ++v) {
                ParticleEmitter particleEmitter0 = (ParticleEmitter)array0.get(v);
                ParticleEmitter particleEmitter1 = (ParticleEmitter)array1.get(v);
                particleEmitter0.matchSize(particleEmitter1);
                particleEmitter0.matchMotion(particleEmitter1);
            }
            particleEffectPool$PooledEffect0.xSizeScale = this.effect.xSizeScale;
            particleEffectPool$PooledEffect0.ySizeScale = this.effect.ySizeScale;
            particleEffectPool$PooledEffect0.motionScale = this.effect.motionScale;
        }
    }

    @Override  // com.badlogic.gdx.utils.Pool
    public void free(Object object0) {
        this.free(((PooledEffect)object0));
    }

    protected PooledEffect newObject() {
        PooledEffect particleEffectPool$PooledEffect0 = new PooledEffect(this, this.effect);
        particleEffectPool$PooledEffect0.start();
        return particleEffectPool$PooledEffect0;
    }

    @Override  // com.badlogic.gdx.utils.Pool
    protected Object newObject() {
        return this.newObject();
    }
}

