package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public final class ParticleSystem implements RenderableProvider {
    private Array batches;
    private Array effects;
    private static ParticleSystem instance;

    public ParticleSystem() {
        this.batches = new Array();
        this.effects = new Array();
    }

    public void add(ParticleEffect particleEffect0) {
        this.effects.add(particleEffect0);
    }

    public void add(ParticleBatch particleBatch0) {
        this.batches.add(particleBatch0);
    }

    public void begin() {
        for(Object object0: this.batches) {
            ((ParticleBatch)object0).begin();
        }
    }

    public void draw() {
        for(Object object0: this.effects) {
            ((ParticleEffect)object0).draw();
        }
    }

    public void end() {
        for(Object object0: this.batches) {
            ((ParticleBatch)object0).end();
        }
    }

    @Deprecated
    public static ParticleSystem get() {
        if(ParticleSystem.instance == null) {
            ParticleSystem.instance = new ParticleSystem();
        }
        return ParticleSystem.instance;
    }

    public Array getBatches() {
        return this.batches;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array array0, Pool pool0) {
        for(Object object0: this.batches) {
            ((ParticleBatch)object0).getRenderables(array0, pool0);
        }
    }

    public void remove(ParticleEffect particleEffect0) {
        this.effects.removeValue(particleEffect0, true);
    }

    public void removeAll() {
        this.effects.clear();
    }

    public void update() {
        for(Object object0: this.effects) {
            ((ParticleEffect)object0).update();
        }
    }

    public void update(float f) {
        for(Object object0: this.effects) {
            ((ParticleEffect)object0).update(f);
        }
    }

    public void updateAndDraw() {
        for(Object object0: this.effects) {
            ((ParticleEffect)object0).update();
            ((ParticleEffect)object0).draw();
        }
    }

    public void updateAndDraw(float f) {
        for(Object object0: this.effects) {
            ((ParticleEffect)object0).update(f);
            ((ParticleEffect)object0).draw();
        }
    }
}

