package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ObjectChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ParticleControllerControllerRenderer extends ParticleControllerRenderer {
    ObjectChannel controllerChannel;

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new ParticleControllerControllerRenderer();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        this.controllerChannel = (ObjectChannel)this.controller.particles.getChannel(ParticleChannels.ParticleController);
        if(this.controllerChannel == null) {
            throw new GdxRuntimeException("ParticleController channel not found, specify an influencer which will allocate it please.");
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public boolean isCompatible(ParticleBatch particleBatch0) {
        return false;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public void update() {
        int v = this.controller.particles.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController[])this.controllerChannel.data)[v1].draw();
        }
    }
}

