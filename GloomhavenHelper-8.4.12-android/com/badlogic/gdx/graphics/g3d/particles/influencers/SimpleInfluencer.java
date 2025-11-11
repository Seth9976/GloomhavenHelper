package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ChannelDescriptor;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class SimpleInfluencer extends Influencer {
    FloatChannel interpolationChannel;
    FloatChannel lifeChannel;
    public ScaledNumericValue value;
    FloatChannel valueChannel;
    ChannelDescriptor valueChannelDescriptor;

    public SimpleInfluencer() {
        this.value = new ScaledNumericValue();
        this.value.setHigh(1.0f);
    }

    public SimpleInfluencer(SimpleInfluencer simpleInfluencer0) {
        this.set(simpleInfluencer0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int v, int v1) {
        if(!this.value.isRelative()) {
            int v2 = this.valueChannel.strideSize * v;
            int v3 = v * this.interpolationChannel.strideSize;
            int v4 = v1 * this.valueChannel.strideSize + v2;
            while(v2 < v4) {
                float f = this.value.newLowValue();
                float f1 = this.value.newHighValue() - f;
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
            float f2 = this.value.newLowValue();
            float f3 = this.value.newHighValue();
            this.interpolationChannel.data[v6] = f2;
            this.interpolationChannel.data[v6 + 1] = f3;
            this.valueChannel.data[v5] = f2 + f3 * this.value.getScale(0.0f);
            v5 += this.valueChannel.strideSize;
            v6 += this.interpolationChannel.strideSize;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.valueChannel = (FloatChannel)this.controller.particles.addChannel(this.valueChannelDescriptor);
        ParticleChannels.Interpolation.id = this.controller.particleChannels.newId();
        this.interpolationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Interpolation);
        this.lifeChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void read(Json json0, JsonValue jsonValue0) {
        this.value = (ScaledNumericValue)json0.readValue("value", ScaledNumericValue.class, jsonValue0);
    }

    private void set(SimpleInfluencer simpleInfluencer0) {
        this.value.load(simpleInfluencer0.value);
        this.valueChannelDescriptor = simpleInfluencer0.valueChannelDescriptor;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        int v = 0;
        int v1 = this.controller.particles.size * this.valueChannel.strideSize;
        int v2 = 0;
        for(int v3 = 2; v < v1; v3 += this.lifeChannel.strideSize) {
            this.valueChannel.data[v] = this.interpolationChannel.data[v2] + this.interpolationChannel.data[v2 + 1] * this.value.getScale(this.lifeChannel.data[v3]);
            v += this.valueChannel.strideSize;
            v2 += this.interpolationChannel.strideSize;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void write(Json json0) {
        json0.writeValue("value", this.value);
    }
}

