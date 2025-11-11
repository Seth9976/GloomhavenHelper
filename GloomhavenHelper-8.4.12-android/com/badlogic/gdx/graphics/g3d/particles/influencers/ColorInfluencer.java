package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.values.GradientColorValue;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class ColorInfluencer extends Influencer {
    public static class Random extends ColorInfluencer {
        FloatChannel colorChannel;

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int v, int v1) {
            int v2 = v * this.colorChannel.strideSize;
            int v3 = v1 * this.colorChannel.strideSize + v2;
            while(v2 < v3) {
                float[] arr_f = this.colorChannel.data;
                arr_f[v2] = MathUtils.random();
                float[] arr_f1 = this.colorChannel.data;
                arr_f1[v2 + 1] = MathUtils.random();
                float[] arr_f2 = this.colorChannel.data;
                arr_f2[v2 + 2] = MathUtils.random();
                float[] arr_f3 = this.colorChannel.data;
                arr_f3[v2 + 3] = MathUtils.random();
                v2 += this.colorChannel.strideSize;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer
        public void allocateChannels() {
            this.colorChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Color);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Random copy() {
            return new Random();
        }
    }

    public static class Single extends ColorInfluencer {
        FloatChannel alphaInterpolationChannel;
        public ScaledNumericValue alphaValue;
        public GradientColorValue colorValue;
        FloatChannel lifeChannel;

        public Single() {
            this.colorValue = new GradientColorValue();
            this.alphaValue = new ScaledNumericValue();
            this.alphaValue.setHigh(1.0f);
        }

        public Single(Single colorInfluencer$Single0) {
            this.set(colorInfluencer$Single0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int v, int v1) {
            int v2 = this.colorChannel.strideSize * v;
            int v3 = this.alphaInterpolationChannel.strideSize * v;
            int v4 = v * this.lifeChannel.strideSize + 2;
            int v5 = v1 * this.colorChannel.strideSize + v2;
            while(v2 < v5) {
                float f = this.alphaValue.newLowValue();
                float f1 = this.alphaValue.newHighValue() - f;
                this.colorValue.getColor(0.0f, this.colorChannel.data, v2);
                this.colorChannel.data[v2 + 3] = this.alphaValue.getScale(this.lifeChannel.data[v4]) * f1 + f;
                this.alphaInterpolationChannel.data[v3] = f;
                this.alphaInterpolationChannel.data[v3 + 1] = f1;
                v2 += this.colorChannel.strideSize;
                v3 += this.alphaInterpolationChannel.strideSize;
                v4 += this.lifeChannel.strideSize;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer
        public void allocateChannels() {
            super.allocateChannels();
            ParticleChannels.Interpolation.id = this.controller.particleChannels.newId();
            this.alphaInterpolationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Interpolation);
            this.lifeChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Life);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Single copy() {
            return new Single(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void read(Json json0, JsonValue jsonValue0) {
            this.alphaValue = (ScaledNumericValue)json0.readValue("alpha", ScaledNumericValue.class, jsonValue0);
            this.colorValue = (GradientColorValue)json0.readValue("color", GradientColorValue.class, jsonValue0);
        }

        public void set(Single colorInfluencer$Single0) {
            this.colorValue.load(colorInfluencer$Single0.colorValue);
            this.alphaValue.load(colorInfluencer$Single0.alphaValue);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int v = 0;
            int v1 = this.controller.particles.size * this.colorChannel.strideSize;
            int v3 = 0;
            for(int v2 = 2; v < v1; v2 += this.lifeChannel.strideSize) {
                float f = this.lifeChannel.data[v2];
                this.colorValue.getColor(f, this.colorChannel.data, v);
                this.colorChannel.data[v + 3] = this.alphaInterpolationChannel.data[v3] + this.alphaInterpolationChannel.data[v3 + 1] * this.alphaValue.getScale(f);
                v += this.colorChannel.strideSize;
                v3 += this.alphaInterpolationChannel.strideSize;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void write(Json json0) {
            json0.writeValue("alpha", this.alphaValue);
            json0.writeValue("color", this.colorValue);
        }
    }

    FloatChannel colorChannel;

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.colorChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Color);
    }
}

