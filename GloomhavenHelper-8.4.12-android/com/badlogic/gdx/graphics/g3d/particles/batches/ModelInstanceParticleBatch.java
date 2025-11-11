package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ModelInstanceControllerRenderData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ModelInstanceParticleBatch implements ParticleBatch {
    int bufferedParticlesCount;
    Array controllersRenderData;

    public ModelInstanceParticleBatch() {
        this.controllersRenderData = new Array(false, 5);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void begin() {
        this.controllersRenderData.clear();
        this.bufferedParticlesCount = 0;
    }

    public void draw(ModelInstanceControllerRenderData modelInstanceControllerRenderData0) {
        this.controllersRenderData.add(modelInstanceControllerRenderData0);
        this.bufferedParticlesCount += modelInstanceControllerRenderData0.controller.particles.size;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void draw(ParticleControllerRenderData particleControllerRenderData0) {
        this.draw(((ModelInstanceControllerRenderData)particleControllerRenderData0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void end() {
    }

    public int getBufferedCount() {
        return this.bufferedParticlesCount;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array array0, Pool pool0) {
        for(Object object0: this.controllersRenderData) {
            ModelInstanceControllerRenderData modelInstanceControllerRenderData0 = (ModelInstanceControllerRenderData)object0;
            int v1 = modelInstanceControllerRenderData0.controller.particles.size;
            for(int v = 0; v < v1; ++v) {
                ((ModelInstance[])modelInstanceControllerRenderData0.modelInstanceChannel.data)[v].getRenderables(array0, pool0);
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
    }
}

