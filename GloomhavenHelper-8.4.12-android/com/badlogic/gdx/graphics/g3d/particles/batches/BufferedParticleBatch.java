package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter.Distance;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;
import com.badlogic.gdx.utils.Array;

public abstract class BufferedParticleBatch implements ParticleBatch {
    protected int bufferedParticlesCount;
    protected Camera camera;
    protected int currentCapacity;
    protected Array renderData;
    protected ParticleSorter sorter;

    protected BufferedParticleBatch(Class class0) {
        this.currentCapacity = 0;
        this.sorter = new Distance();
        this.renderData = new Array(false, 10, class0);
    }

    protected abstract void allocParticlesData(int arg1);

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void begin() {
        this.renderData.clear();
        this.bufferedParticlesCount = 0;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void draw(ParticleControllerRenderData particleControllerRenderData0) {
        if(particleControllerRenderData0.controller.particles.size > 0) {
            this.renderData.add(particleControllerRenderData0);
            this.bufferedParticlesCount += particleControllerRenderData0.controller.particles.size;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void end() {
        int v = this.bufferedParticlesCount;
        if(v > 0) {
            this.ensureCapacity(v);
            this.flush(this.sorter.sort(this.renderData));
        }
    }

    public void ensureCapacity(int v) {
        if(this.currentCapacity >= v) {
            return;
        }
        this.sorter.ensureCapacity(v);
        this.allocParticlesData(v);
        this.currentCapacity = v;
    }

    protected abstract void flush(int[] arg1);

    public int getBufferedCount() {
        return this.bufferedParticlesCount;
    }

    public ParticleSorter getSorter() {
        return this.sorter;
    }

    public void resetCapacity() {
        this.bufferedParticlesCount = 0;
        this.currentCapacity = 0;
    }

    public void setCamera(Camera camera0) {
        this.camera = camera0;
        this.sorter.setCamera(camera0);
    }

    public void setSorter(ParticleSorter particleSorter0) {
        this.sorter = particleSorter0;
        particleSorter0.setCamera(this.camera);
        particleSorter0.ensureCapacity(this.currentCapacity);
    }
}

