package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import java.util.Arrays;

public class DynamicsInfluencer extends Influencer {
    private FloatChannel accellerationChannel;
    private FloatChannel angularVelocityChannel;
    boolean has2dAngularVelocity;
    boolean has3dAngularVelocity;
    boolean hasAcceleration;
    private FloatChannel positionChannel;
    private FloatChannel previousPositionChannel;
    private FloatChannel rotationChannel;
    public Array velocities;

    public DynamicsInfluencer() {
        this.velocities = new Array(true, 3, DynamicsModifier.class);
    }

    public DynamicsInfluencer(DynamicsInfluencer dynamicsInfluencer0) {
        this(((DynamicsModifier[])dynamicsInfluencer0.velocities.toArray(DynamicsModifier.class)));
    }

    public DynamicsInfluencer(DynamicsModifier[] arr_dynamicsModifier) {
        this.velocities = new Array(true, arr_dynamicsModifier.length, DynamicsModifier.class);
        for(int v = 0; v < arr_dynamicsModifier.length; ++v) {
            this.velocities.add(((DynamicsModifier)arr_dynamicsModifier[v].copy()));
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int v, int v1) {
        if(this.hasAcceleration) {
            int v2 = this.positionChannel.strideSize * v;
            int v3 = this.positionChannel.strideSize * v1 + v2;
            while(v2 < v3) {
                this.previousPositionChannel.data[v2] = this.positionChannel.data[v2];
                this.previousPositionChannel.data[v2 + 1] = this.positionChannel.data[v2 + 1];
                this.previousPositionChannel.data[v2 + 2] = this.positionChannel.data[v2 + 2];
                v2 += this.positionChannel.strideSize;
            }
        }
        if(this.has2dAngularVelocity) {
            int v4 = this.rotationChannel.strideSize * v;
            int v5 = this.rotationChannel.strideSize * v1 + v4;
            while(v4 < v5) {
                this.rotationChannel.data[v4] = 1.0f;
                this.rotationChannel.data[v4 + 1] = 0.0f;
                v4 += this.rotationChannel.strideSize;
            }
        }
        else if(this.has3dAngularVelocity) {
            int v6 = this.rotationChannel.strideSize * v;
            int v7 = this.rotationChannel.strideSize * v1 + v6;
            while(v6 < v7) {
                this.rotationChannel.data[v6] = 0.0f;
                this.rotationChannel.data[v6 + 1] = 0.0f;
                this.rotationChannel.data[v6 + 2] = 0.0f;
                this.rotationChannel.data[v6 + 3] = 1.0f;
                v6 += this.rotationChannel.strideSize;
            }
        }
        for(int v8 = 0; v8 < this.velocities.size; ++v8) {
            ((DynamicsModifier[])this.velocities.items)[v8].activateParticles(v, v1);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        boolean z = false;
        for(int v = 0; v < this.velocities.size; ++v) {
            ((DynamicsModifier[])this.velocities.items)[v].allocateChannels();
        }
        this.accellerationChannel = (FloatChannel)this.controller.particles.getChannel(ParticleChannels.Acceleration);
        this.hasAcceleration = this.accellerationChannel != null;
        if(this.hasAcceleration) {
            this.positionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Position);
            this.previousPositionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.PreviousPosition);
        }
        this.angularVelocityChannel = (FloatChannel)this.controller.particles.getChannel(ParticleChannels.AngularVelocity2D);
        this.has2dAngularVelocity = this.angularVelocityChannel != null;
        if(this.has2dAngularVelocity) {
            this.rotationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Rotation2D);
            this.has3dAngularVelocity = false;
            return;
        }
        this.angularVelocityChannel = (FloatChannel)this.controller.particles.getChannel(ParticleChannels.AngularVelocity3D);
        if(this.angularVelocityChannel != null) {
            z = true;
        }
        this.has3dAngularVelocity = z;
        if(this.has3dAngularVelocity) {
            this.rotationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Rotation3D);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return this.copy();
    }

    public DynamicsInfluencer copy() {
        return new DynamicsInfluencer(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        for(int v = 0; v < this.velocities.size; ++v) {
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void read(Json json0, JsonValue jsonValue0) {
        this.velocities.addAll(((Array)json0.readValue("velocities", Array.class, DynamicsModifier.class, jsonValue0)));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void set(ParticleController particleController0) {
        super.set(particleController0);
        for(int v = 0; v < this.velocities.size; ++v) {
            ((DynamicsModifier[])this.velocities.items)[v].set(particleController0);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        int v = 0;
        if(this.hasAcceleration) {
            Arrays.fill(this.accellerationChannel.data, 0, this.controller.particles.size * this.accellerationChannel.strideSize, 0.0f);
        }
        if(this.has2dAngularVelocity || this.has3dAngularVelocity) {
            Arrays.fill(this.angularVelocityChannel.data, 0, this.controller.particles.size * this.angularVelocityChannel.strideSize, 0.0f);
        }
        for(int v1 = 0; v1 < this.velocities.size; ++v1) {
            ((DynamicsModifier[])this.velocities.items)[v1].update();
        }
        if(this.hasAcceleration) {
            int v2 = 0;
            for(int v3 = 0; v2 < this.controller.particles.size; v3 += this.positionChannel.strideSize) {
                float f = this.positionChannel.data[v3];
                float f1 = this.positionChannel.data[v3 + 1];
                float f2 = this.positionChannel.data[v3 + 2];
                this.positionChannel.data[v3] = f * 2.0f - this.previousPositionChannel.data[v3] + this.accellerationChannel.data[v3] * this.controller.deltaTimeSqr;
                this.positionChannel.data[v3 + 1] = f1 * 2.0f - this.previousPositionChannel.data[v3 + 1] + this.accellerationChannel.data[v3 + 1] * this.controller.deltaTimeSqr;
                this.positionChannel.data[v3 + 2] = 2.0f * f2 - this.previousPositionChannel.data[v3 + 2] + this.accellerationChannel.data[v3 + 2] * this.controller.deltaTimeSqr;
                this.previousPositionChannel.data[v3] = f;
                this.previousPositionChannel.data[v3 + 1] = f1;
                this.previousPositionChannel.data[v3 + 2] = f2;
                ++v2;
            }
        }
        if(this.has2dAngularVelocity) {
            for(int v4 = 0; v < this.controller.particles.size; v4 += this.rotationChannel.strideSize) {
                float f3 = this.angularVelocityChannel.data[v] * this.controller.deltaTime;
                if(f3 != 0.0f) {
                    float f4 = MathUtils.cosDeg(f3);
                    float f5 = MathUtils.sinDeg(f3);
                    float f6 = this.rotationChannel.data[v4];
                    float f7 = this.rotationChannel.data[v4 + 1];
                    this.rotationChannel.data[v4] = f6 * f4 - f7 * f5;
                    this.rotationChannel.data[v4 + 1] = f7 * f4 + f6 * f5;
                }
                ++v;
            }
            return;
        }
        if(this.has3dAngularVelocity) {
            int v6 = 0;
            for(int v5 = 0; v < this.controller.particles.size; v5 += this.angularVelocityChannel.strideSize) {
                float f8 = this.angularVelocityChannel.data[v5];
                float f9 = this.angularVelocityChannel.data[v5 + 1];
                float f10 = this.angularVelocityChannel.data[v5 + 2];
                float f11 = this.rotationChannel.data[v6];
                float f12 = this.rotationChannel.data[v6 + 1];
                float f13 = this.rotationChannel.data[v6 + 2];
                float f14 = this.rotationChannel.data[v6 + 3];
                DynamicsInfluencer.TMP_Q.set(f8, f9, f10, 0.0f).mul(f11, f12, f13, f14).mul(this.controller.deltaTime * 0.5f).add(f11, f12, f13, f14).nor();
                this.rotationChannel.data[v6] = DynamicsInfluencer.TMP_Q.x;
                this.rotationChannel.data[v6 + 1] = DynamicsInfluencer.TMP_Q.y;
                this.rotationChannel.data[v6 + 2] = DynamicsInfluencer.TMP_Q.z;
                this.rotationChannel.data[v6 + 3] = DynamicsInfluencer.TMP_Q.w;
                ++v;
                v6 += this.rotationChannel.strideSize;
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void write(Json json0) {
        json0.writeValue("velocities", this.velocities, Array.class, DynamicsModifier.class);
    }
}

