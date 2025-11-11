package com.badlogic.gdx.graphics.g3d.particles.emitters;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class RegularEmitter extends Emitter implements Serializable {
    public static enum EmissionMode {
        Enabled,
        EnabledUntilCycleEnd,
        Disabled;

    }

    private boolean continuous;
    protected float delay;
    protected float delayTimer;
    public RangedNumericValue delayValue;
    protected float duration;
    protected float durationTimer;
    public RangedNumericValue durationValue;
    protected int emission;
    protected int emissionDelta;
    protected int emissionDiff;
    private EmissionMode emissionMode;
    public ScaledNumericValue emissionValue;
    protected int life;
    private FloatChannel lifeChannel;
    protected int lifeDiff;
    protected int lifeOffset;
    protected int lifeOffsetDiff;
    public ScaledNumericValue lifeOffsetValue;
    public ScaledNumericValue lifeValue;

    public RegularEmitter() {
        this.delayValue = new RangedNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeOffsetValue = new ScaledNumericValue();
        this.lifeValue = new ScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.durationValue.setActive(true);
        this.emissionValue.setActive(true);
        this.lifeValue.setActive(true);
        this.continuous = true;
        this.emissionMode = EmissionMode.Enabled;
    }

    public RegularEmitter(RegularEmitter regularEmitter0) {
        this.set(regularEmitter0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int v, int v1) {
        int v4;
        int v2 = this.life + ((int)(((float)this.lifeDiff) * this.lifeValue.getScale(this.percent)));
        int v3 = (int)(((float)this.lifeOffset) + ((float)this.lifeOffsetDiff) * this.lifeOffsetValue.getScale(this.percent));
        if(v3 > 0) {
            if(v3 >= v2) {
                v3 = v2 - 1;
            }
            v4 = v2 - v3;
        }
        else {
            v4 = v2;
        }
        int v5 = v * this.lifeChannel.strideSize;
        int v6 = v1 * this.lifeChannel.strideSize + v5;
        while(v5 < v6) {
            this.lifeChannel.data[v5] = (float)v4;
            this.lifeChannel.data[v5 + 1] = (float)v2;
            this.lifeChannel.data[v5 + 2] = 1.0f - ((float)v4) / ((float)v2);
            v5 += this.lifeChannel.strideSize;
        }
    }

    private void addParticles(int v) {
        int v1 = Math.min(v, this.maxParticleCount - this.controller.particles.size);
        if(v1 <= 0) {
            return;
        }
        this.controller.activateParticles(this.controller.particles.size, v1);
        this.controller.particles.size += v1;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.lifeChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new RegularEmitter(this);
    }

    public RangedNumericValue getDelay() {
        return this.delayValue;
    }

    public RangedNumericValue getDuration() {
        return this.durationValue;
    }

    public ScaledNumericValue getEmission() {
        return this.emissionValue;
    }

    public EmissionMode getEmissionMode() {
        return this.emissionMode;
    }

    public ScaledNumericValue getLife() {
        return this.lifeValue;
    }

    public ScaledNumericValue getLifeOffset() {
        return this.lifeOffsetValue;
    }

    public float getPercentComplete() {
        return this.delayTimer < this.delay ? 0.0f : Math.min(1.0f, this.durationTimer / this.duration);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter
    public void init() {
        super.init();
        this.emissionDelta = 0;
        this.durationTimer = this.duration;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter
    public boolean isComplete() {
        return this.delayTimer < this.delay ? false : this.durationTimer >= this.duration && this.controller.particles.size == 0;
    }

    public boolean isContinuous() {
        return this.continuous;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter, com.badlogic.gdx.utils.Json$Serializable
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.continuous = ((Boolean)json0.readValue("continous", Boolean.TYPE, jsonValue0)).booleanValue();
        this.emissionValue = (ScaledNumericValue)json0.readValue("emission", ScaledNumericValue.class, jsonValue0);
        this.delayValue = (RangedNumericValue)json0.readValue("delay", RangedNumericValue.class, jsonValue0);
        this.durationValue = (RangedNumericValue)json0.readValue("duration", RangedNumericValue.class, jsonValue0);
        this.lifeValue = (ScaledNumericValue)json0.readValue("life", ScaledNumericValue.class, jsonValue0);
        this.lifeOffsetValue = (ScaledNumericValue)json0.readValue("lifeOffset", ScaledNumericValue.class, jsonValue0);
    }

    public void set(RegularEmitter regularEmitter0) {
        super.set(regularEmitter0);
        this.delayValue.load(regularEmitter0.delayValue);
        this.durationValue.load(regularEmitter0.durationValue);
        this.lifeOffsetValue.load(regularEmitter0.lifeOffsetValue);
        this.lifeValue.load(regularEmitter0.lifeValue);
        this.emissionValue.load(regularEmitter0.emissionValue);
        this.emission = regularEmitter0.emission;
        this.emissionDiff = regularEmitter0.emissionDiff;
        this.emissionDelta = regularEmitter0.emissionDelta;
        this.lifeOffset = regularEmitter0.lifeOffset;
        this.lifeOffsetDiff = regularEmitter0.lifeOffsetDiff;
        this.life = regularEmitter0.life;
        this.lifeDiff = regularEmitter0.lifeDiff;
        this.duration = regularEmitter0.duration;
        this.delay = regularEmitter0.delay;
        this.durationTimer = regularEmitter0.durationTimer;
        this.delayTimer = regularEmitter0.delayTimer;
        this.continuous = regularEmitter0.continuous;
    }

    public void setContinuous(boolean z) {
        this.continuous = z;
    }

    public void setEmissionMode(EmissionMode regularEmitter$EmissionMode0) {
        this.emissionMode = regularEmitter$EmissionMode0;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void start() {
        this.delay = this.delayValue.active ? this.delayValue.newLowValue() : 0.0f;
        this.delayTimer = 0.0f;
        this.durationTimer = 0.0f;
        this.duration = this.durationValue.newLowValue();
        this.percent = this.durationTimer / this.duration;
        this.emission = (int)this.emissionValue.newLowValue();
        this.emissionDiff = (int)this.emissionValue.newHighValue();
        if(!this.emissionValue.isRelative()) {
            this.emissionDiff -= this.emission;
        }
        this.life = (int)this.lifeValue.newLowValue();
        this.lifeDiff = (int)this.lifeValue.newHighValue();
        if(!this.lifeValue.isRelative()) {
            this.lifeDiff -= this.life;
        }
        this.lifeOffset = this.lifeOffsetValue.active ? ((int)this.lifeOffsetValue.newLowValue()) : 0;
        this.lifeOffsetDiff = (int)this.lifeOffsetValue.newHighValue();
        if(!this.lifeOffsetValue.isRelative()) {
            this.lifeOffsetDiff -= this.lifeOffset;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        float f = this.controller.deltaTime * 1000.0f;
        float f1 = this.delayTimer;
        int v = 0;
        if(f1 < this.delay) {
            this.delayTimer = f1 + f;
        }
        else {
            boolean z = this.emissionMode != EmissionMode.Disabled;
            float f2 = this.durationTimer;
            float f3 = this.duration;
            if(f2 < f3) {
                this.durationTimer = f2 + f;
                this.percent = this.durationTimer / f3;
            }
            else if(!this.continuous || !z || this.emissionMode != EmissionMode.Enabled) {
                z = false;
            }
            else {
                this.controller.start();
            }
            if(z) {
                this.emissionDelta = (int)(((float)this.emissionDelta) + f);
                float f4 = ((float)this.emission) + ((float)this.emissionDiff) * this.emissionValue.getScale(this.percent);
                if(f4 > 0.0f) {
                    int v1 = this.emissionDelta;
                    if(((float)v1) >= 1000.0f / f4) {
                        int v2 = Math.min(((int)(((float)v1) / (1000.0f / f4))), this.maxParticleCount - this.controller.particles.size);
                        this.emissionDelta = (int)(((float)this.emissionDelta) - ((float)v2) * (1000.0f / f4));
                        this.emissionDelta = (int)(((float)this.emissionDelta) % (1000.0f / f4));
                        this.addParticles(v2);
                    }
                }
                if(this.controller.particles.size < this.minParticleCount) {
                    this.addParticles(this.minParticleCount - this.controller.particles.size);
                }
            }
        }
        int v3 = this.controller.particles.size;
        int v4 = 0;
        while(v < this.controller.particles.size) {
            float[] arr_f = this.lifeChannel.data;
            float f5 = arr_f[v4] - f;
            arr_f[v4] = f5;
            if(f5 <= 0.0f) {
                this.controller.particles.removeElement(v);
            }
            else {
                this.lifeChannel.data[v4 + 2] = 1.0f - this.lifeChannel.data[v4] / this.lifeChannel.data[v4 + 1];
                ++v;
                v4 += this.lifeChannel.strideSize;
            }
        }
        if(this.controller.particles.size < v3) {
            this.controller.killParticles(this.controller.particles.size, v3 - this.controller.particles.size);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter, com.badlogic.gdx.utils.Json$Serializable
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("continous", Boolean.valueOf(this.continuous));
        json0.writeValue("emission", this.emissionValue);
        json0.writeValue("delay", this.delayValue);
        json0.writeValue("duration", this.durationValue);
        json0.writeValue("life", this.lifeValue);
        json0.writeValue("lifeOffset", this.lifeOffsetValue);
    }
}

