package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;

public class ScaleInfluencer extends SimpleInfluencer {
    public ScaleInfluencer() {
        this.valueChannelDescriptor = ParticleChannels.Scale;
    }

    public ScaleInfluencer(ScaleInfluencer scaleInfluencer0) {
        super(scaleInfluencer0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.SimpleInfluencer
    public void activateParticles(int v, int v1) {
        if(this.value.isRelative()) {
            int v2 = this.valueChannel.strideSize * v;
            int v3 = v * this.interpolationChannel.strideSize;
            int v4 = v1 * this.valueChannel.strideSize + v2;
            while(v2 < v4) {
                float f = this.value.newLowValue() * this.controller.scale.x;
                float f1 = this.value.newHighValue() * this.controller.scale.x;
                this.interpolationChannel.data[v3] = f;
                this.interpolationChannel.data[v3 + 1] = f1;
                this.valueChannel.data[v2] = f + f1 * this.value.getScale(0.0f);
                v2 += this.valueChannel.strideSize;
                v3 += this.interpolationChannel.strideSize;
            }
            return;
        }
        int v5 = this.valueChannel.strideSize * v;
        int v6 = v * this.interpolationChannel.strideSize;
        int v7 = v1 * this.valueChannel.strideSize + v5;
        while(v5 < v7) {
            float f2 = this.value.newLowValue() * this.controller.scale.x;
            float f3 = this.value.newHighValue() * this.controller.scale.x - f2;
            this.interpolationChannel.data[v6] = f2;
            this.interpolationChannel.data[v6 + 1] = f3;
            this.valueChannel.data[v5] = f2 + f3 * this.value.getScale(0.0f);
            v5 += this.valueChannel.strideSize;
            v6 += this.interpolationChannel.strideSize;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new ScaleInfluencer(this);
    }
}

