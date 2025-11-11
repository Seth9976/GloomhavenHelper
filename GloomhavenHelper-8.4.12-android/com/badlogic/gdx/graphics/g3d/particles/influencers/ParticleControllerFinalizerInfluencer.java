package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ObjectChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ParticleControllerFinalizerInfluencer extends Influencer {
    ObjectChannel controllerChannel;
    boolean hasRotation;
    boolean hasScale;
    FloatChannel positionChannel;
    FloatChannel rotationChannel;
    FloatChannel scaleChannel;

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.positionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Position);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return this.copy();
    }

    public ParticleControllerFinalizerInfluencer copy() {
        return new ParticleControllerFinalizerInfluencer();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        this.controllerChannel = (ObjectChannel)this.controller.particles.getChannel(ParticleChannels.ParticleController);
        if(this.controllerChannel == null) {
            throw new GdxRuntimeException("ParticleController channel not found, specify an influencer which will allocate it please.");
        }
        this.scaleChannel = (FloatChannel)this.controller.particles.getChannel(ParticleChannels.Scale);
        this.rotationChannel = (FloatChannel)this.controller.particles.getChannel(ParticleChannels.Rotation3D);
        boolean z = true;
        this.hasScale = this.scaleChannel != null;
        if(this.rotationChannel == null) {
            z = false;
        }
        this.hasRotation = z;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        float f7;
        float f6;
        float f5;
        float f4;
        int v = this.controller.particles.size;
        int v1 = 0;
        for(int v2 = 0; v1 < v; v2 += this.positionChannel.strideSize) {
            ParticleController particleController0 = ((ParticleController[])this.controllerChannel.data)[v1];
            float f = this.hasScale ? this.scaleChannel.data[v1] : 1.0f;
            if(this.hasRotation) {
                int v3 = this.rotationChannel.strideSize * v1;
                float f1 = this.rotationChannel.data[v3];
                float f2 = this.rotationChannel.data[v3 + 1];
                float f3 = this.rotationChannel.data[v3 + 2];
                f4 = this.rotationChannel.data[v3 + 3];
                f5 = f1;
                f6 = f2;
                f7 = f3;
            }
            else {
                f5 = 0.0f;
                f6 = 0.0f;
                f7 = 0.0f;
                f4 = 1.0f;
            }
            particleController0.setTransform(this.positionChannel.data[v2], this.positionChannel.data[v2 + 1], this.positionChannel.data[v2 + 2], f5, f6, f7, f4, f);
            particleController0.update();
            ++v1;
        }
    }
}

