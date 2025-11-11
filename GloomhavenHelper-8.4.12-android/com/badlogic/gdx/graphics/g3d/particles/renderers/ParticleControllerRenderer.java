package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;

public abstract class ParticleControllerRenderer extends ParticleControllerComponent {
    protected ParticleBatch batch;
    protected ParticleControllerRenderData renderData;

    protected ParticleControllerRenderer() {
    }

    protected ParticleControllerRenderer(ParticleControllerRenderData particleControllerRenderData0) {
        this.renderData = particleControllerRenderData0;
    }

    public abstract boolean isCompatible(ParticleBatch arg1);

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void set(ParticleController particleController0) {
        super.set(particleController0);
        ParticleControllerRenderData particleControllerRenderData0 = this.renderData;
        if(particleControllerRenderData0 != null) {
            particleControllerRenderData0.controller = this.controller;
        }
    }

    public boolean setBatch(ParticleBatch particleBatch0) {
        if(this.isCompatible(particleBatch0)) {
            this.batch = particleBatch0;
            return true;
        }
        return false;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        this.batch.draw(this.renderData);
    }
}

