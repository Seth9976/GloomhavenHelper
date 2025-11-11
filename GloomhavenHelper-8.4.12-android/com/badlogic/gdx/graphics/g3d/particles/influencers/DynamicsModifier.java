package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class DynamicsModifier extends Influencer {
    public static abstract class Angular extends Strength {
        protected FloatChannel angularChannel;
        public ScaledNumericValue phiValue;
        public ScaledNumericValue thetaValue;

        public Angular() {
            this.thetaValue = new ScaledNumericValue();
            this.phiValue = new ScaledNumericValue();
        }

        public Angular(Angular dynamicsModifier$Angular0) {
            super(dynamicsModifier$Angular0);
            this.thetaValue = new ScaledNumericValue();
            this.phiValue = new ScaledNumericValue();
            this.thetaValue.load(dynamicsModifier$Angular0.thetaValue);
            this.phiValue.load(dynamicsModifier$Angular0.phiValue);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Strength
        public void activateParticles(int v, int v1) {
            super.activateParticles(v, v1);
            int v2 = v * this.angularChannel.strideSize;
            int v3 = v1 * this.angularChannel.strideSize + v2;
            while(v2 < v3) {
                float f = this.thetaValue.newLowValue();
                float f1 = this.thetaValue.newHighValue();
                if(!this.thetaValue.isRelative()) {
                    f1 -= f;
                }
                this.angularChannel.data[v2] = f;
                this.angularChannel.data[v2 + 1] = f1;
                float f2 = this.phiValue.newLowValue();
                float f3 = this.phiValue.newHighValue();
                if(!this.phiValue.isRelative()) {
                    f3 -= f2;
                }
                this.angularChannel.data[v2 + 2] = f2;
                this.angularChannel.data[v2 + 3] = f3;
                v2 += this.angularChannel.strideSize;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Strength
        public void allocateChannels() {
            super.allocateChannels();
            ParticleChannels.Interpolation4.id = this.controller.particleChannels.newId();
            this.angularChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Interpolation4);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Strength
        public void read(Json json0, JsonValue jsonValue0) {
            super.read(json0, jsonValue0);
            this.thetaValue = (ScaledNumericValue)json0.readValue("thetaValue", ScaledNumericValue.class, jsonValue0);
            this.phiValue = (ScaledNumericValue)json0.readValue("phiValue", ScaledNumericValue.class, jsonValue0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Strength
        public void write(Json json0) {
            super.write(json0);
            json0.writeValue("thetaValue", this.thetaValue);
            json0.writeValue("phiValue", this.phiValue);
        }
    }

    public static class BrownianAcceleration extends Strength {
        FloatChannel accelerationChannel;

        public BrownianAcceleration() {
        }

        public BrownianAcceleration(BrownianAcceleration dynamicsModifier$BrownianAcceleration0) {
            super(dynamicsModifier$BrownianAcceleration0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Strength
        public void allocateChannels() {
            super.allocateChannels();
            this.accelerationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public BrownianAcceleration copy() {
            return new BrownianAcceleration(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int v = this.controller.particles.size;
            int v1 = 0;
            int v2 = 0;
            int v4 = 0;
            for(int v3 = 2; v1 < v; v3 += this.lifeChannel.strideSize) {
                float f = this.strengthChannel.data[v2];
                float f1 = this.strengthChannel.data[v2 + 1];
                float f2 = this.strengthValue.getScale(this.lifeChannel.data[v3]);
                BrownianAcceleration.TMP_V3.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f)).nor().scl(f + f1 * f2);
                float[] arr_f = this.accelerationChannel.data;
                arr_f[v4] += BrownianAcceleration.TMP_V3.x;
                float[] arr_f1 = this.accelerationChannel.data;
                arr_f1[v4 + 1] += BrownianAcceleration.TMP_V3.y;
                float[] arr_f2 = this.accelerationChannel.data;
                arr_f2[v4 + 2] += BrownianAcceleration.TMP_V3.z;
                ++v1;
                v2 += this.strengthChannel.strideSize;
                v4 += this.accelerationChannel.strideSize;
            }
        }
    }

    public static class CentripetalAcceleration extends Strength {
        FloatChannel accelerationChannel;
        FloatChannel positionChannel;

        public CentripetalAcceleration() {
        }

        public CentripetalAcceleration(CentripetalAcceleration dynamicsModifier$CentripetalAcceleration0) {
            super(dynamicsModifier$CentripetalAcceleration0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Strength
        public void allocateChannels() {
            super.allocateChannels();
            this.accelerationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Acceleration);
            this.positionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Position);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public CentripetalAcceleration copy() {
            return new CentripetalAcceleration(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            float f2;
            float f1;
            float f = 0.0f;
            if(this.isGlobal) {
                f2 = 0.0f;
                f1 = 0.0f;
            }
            else {
                float[] arr_f = this.controller.transform.val;
                f = arr_f[12];
                f1 = arr_f[13];
                f2 = arr_f[14];
            }
            int v = this.controller.particles.size;
            int v1 = 0;
            int v2 = 0;
            int v4 = 0;
            int v5 = 0;
            for(int v3 = 2; v1 < v; v3 += this.lifeChannel.strideSize) {
                float f3 = this.strengthChannel.data[v2];
                float f4 = this.strengthChannel.data[v2 + 1];
                float f5 = this.strengthValue.getScale(this.lifeChannel.data[v3]);
                CentripetalAcceleration.TMP_V3.set(this.positionChannel.data[v4] - f, this.positionChannel.data[v4 + 1] - f1, this.positionChannel.data[v4 + 2] - f2).nor().scl(f3 + f4 * f5);
                float[] arr_f1 = this.accelerationChannel.data;
                arr_f1[v5] += CentripetalAcceleration.TMP_V3.x;
                float[] arr_f2 = this.accelerationChannel.data;
                arr_f2[v5 + 1] += CentripetalAcceleration.TMP_V3.y;
                float[] arr_f3 = this.accelerationChannel.data;
                arr_f3[v5 + 2] += CentripetalAcceleration.TMP_V3.z;
                ++v1;
                v4 += this.positionChannel.strideSize;
                v2 += this.strengthChannel.strideSize;
                v5 += this.accelerationChannel.strideSize;
            }
        }
    }

    public static class FaceDirection extends DynamicsModifier {
        FloatChannel accellerationChannel;
        FloatChannel rotationChannel;

        public FaceDirection() {
        }

        public FaceDirection(FaceDirection dynamicsModifier$FaceDirection0) {
            super(dynamicsModifier$FaceDirection0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier
        public void allocateChannels() {
            this.rotationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Rotation3D);
            this.accellerationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return new FaceDirection(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int v = 0;
            int v1 = this.controller.particles.size * this.rotationChannel.strideSize;
            for(int v2 = 0; v < v1; v2 += this.accellerationChannel.strideSize) {
                Vector3 vector30 = FaceDirection.TMP_V1.set(this.accellerationChannel.data[v2], this.accellerationChannel.data[v2 + 1], this.accellerationChannel.data[v2 + 2]).nor();
                Vector3 vector31 = FaceDirection.TMP_V2.set(FaceDirection.TMP_V1).crs(Vector3.Y).nor().crs(FaceDirection.TMP_V1).nor();
                Vector3 vector32 = FaceDirection.TMP_V3.set(vector31).crs(vector30).nor();
                FaceDirection.TMP_Q.setFromAxes(false, vector32.x, vector31.x, vector30.x, vector32.y, vector31.y, vector30.y, vector32.z, vector31.z, vector30.z);
                this.rotationChannel.data[v] = FaceDirection.TMP_Q.x;
                this.rotationChannel.data[v + 1] = FaceDirection.TMP_Q.y;
                this.rotationChannel.data[v + 2] = FaceDirection.TMP_Q.z;
                this.rotationChannel.data[v + 3] = FaceDirection.TMP_Q.w;
                v += this.rotationChannel.strideSize;
            }
        }
    }

    public static class PolarAcceleration extends Angular {
        FloatChannel directionalVelocityChannel;

        public PolarAcceleration() {
        }

        public PolarAcceleration(PolarAcceleration dynamicsModifier$PolarAcceleration0) {
            super(dynamicsModifier$PolarAcceleration0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Angular
        public void allocateChannels() {
            super.allocateChannels();
            this.directionalVelocityChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public PolarAcceleration copy() {
            return new PolarAcceleration(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int v = 0;
            int v1 = this.controller.particles.size * this.directionalVelocityChannel.strideSize;
            int v3 = 0;
            int v4 = 0;
            for(int v2 = 2; v < v1; v2 += this.lifeChannel.strideSize) {
                float f = this.lifeChannel.data[v2];
                float f1 = this.strengthChannel.data[v3];
                float f2 = this.strengthChannel.data[v3 + 1];
                float f3 = this.strengthValue.getScale(f);
                float f4 = this.angularChannel.data[v4 + 2] + this.angularChannel.data[v4 + 3] * this.phiValue.getScale(f);
                float f5 = this.angularChannel.data[v4] + this.angularChannel.data[v4 + 1] * this.thetaValue.getScale(f);
                float f6 = MathUtils.cosDeg(f5);
                float f7 = MathUtils.sinDeg(f5);
                float f8 = MathUtils.cosDeg(f4);
                float f9 = MathUtils.sinDeg(f4);
                PolarAcceleration.TMP_V3.set(f6 * f9, f8, f7 * f9).nor().scl(f1 + f2 * f3);
                if(!this.isGlobal) {
                    this.controller.transform.getRotation(PolarAcceleration.TMP_Q, true);
                    PolarAcceleration.TMP_V3.mul(PolarAcceleration.TMP_Q);
                }
                float[] arr_f = this.directionalVelocityChannel.data;
                arr_f[v] += PolarAcceleration.TMP_V3.x;
                float[] arr_f1 = this.directionalVelocityChannel.data;
                arr_f1[v + 1] += PolarAcceleration.TMP_V3.y;
                float[] arr_f2 = this.directionalVelocityChannel.data;
                arr_f2[v + 2] += PolarAcceleration.TMP_V3.z;
                v3 += this.strengthChannel.strideSize;
                v += this.directionalVelocityChannel.strideSize;
                v4 += this.angularChannel.strideSize;
            }
        }
    }

    public static class Rotational2D extends Strength {
        FloatChannel rotationalVelocity2dChannel;

        public Rotational2D() {
        }

        public Rotational2D(Rotational2D dynamicsModifier$Rotational2D0) {
            super(dynamicsModifier$Rotational2D0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Strength
        public void allocateChannels() {
            super.allocateChannels();
            this.rotationalVelocity2dChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.AngularVelocity2D);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Rotational2D copy() {
            return new Rotational2D(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int v = 0;
            int v1 = this.controller.particles.size * this.rotationalVelocity2dChannel.strideSize;
            int v2 = 0;
            for(int v3 = 2; v < v1; v3 += this.lifeChannel.strideSize) {
                float[] arr_f = this.rotationalVelocity2dChannel.data;
                arr_f[v] += this.strengthChannel.data[v2] + this.strengthChannel.data[v2 + 1] * this.strengthValue.getScale(this.lifeChannel.data[v3]);
                v2 += this.strengthChannel.strideSize;
                v += this.rotationalVelocity2dChannel.strideSize;
            }
        }
    }

    public static class Rotational3D extends Angular {
        FloatChannel rotationChannel;
        FloatChannel rotationalForceChannel;

        public Rotational3D() {
        }

        public Rotational3D(Rotational3D dynamicsModifier$Rotational3D0) {
            super(dynamicsModifier$Rotational3D0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Angular
        public void allocateChannels() {
            super.allocateChannels();
            this.rotationChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Rotation3D);
            this.rotationalForceChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.AngularVelocity3D);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Rotational3D copy() {
            return new Rotational3D(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int v = this.controller.particles.size * this.rotationalForceChannel.strideSize;
            int v2 = 0;
            int v3 = 0;
            int v4 = 0;
            for(int v1 = 2; v2 < v; v1 += this.lifeChannel.strideSize) {
                float f = this.lifeChannel.data[v1];
                float f1 = this.strengthChannel.data[v3];
                float f2 = this.strengthChannel.data[v3 + 1];
                float f3 = this.strengthValue.getScale(f);
                float f4 = this.angularChannel.data[v4 + 2] + this.angularChannel.data[v4 + 3] * this.phiValue.getScale(f);
                float f5 = this.angularChannel.data[v4] + this.angularChannel.data[v4 + 1] * this.thetaValue.getScale(f);
                float f6 = MathUtils.cosDeg(f5);
                float f7 = MathUtils.sinDeg(f5);
                float f8 = MathUtils.cosDeg(f4);
                float f9 = MathUtils.sinDeg(f4);
                Rotational3D.TMP_V3.set(f6 * f9, f8, f7 * f9);
                Rotational3D.TMP_V3.scl((f1 + f2 * f3) * 0.017453f);
                float[] arr_f = this.rotationalForceChannel.data;
                arr_f[v2] += Rotational3D.TMP_V3.x;
                float[] arr_f1 = this.rotationalForceChannel.data;
                arr_f1[v2 + 1] += Rotational3D.TMP_V3.y;
                float[] arr_f2 = this.rotationalForceChannel.data;
                arr_f2[v2 + 2] += Rotational3D.TMP_V3.z;
                v3 += this.strengthChannel.strideSize;
                v2 += this.rotationalForceChannel.strideSize;
                v4 += this.angularChannel.strideSize;
            }
        }
    }

    public static abstract class Strength extends DynamicsModifier {
        protected FloatChannel strengthChannel;
        public ScaledNumericValue strengthValue;

        public Strength() {
            this.strengthValue = new ScaledNumericValue();
        }

        public Strength(Strength dynamicsModifier$Strength0) {
            super(dynamicsModifier$Strength0);
            this.strengthValue = new ScaledNumericValue();
            this.strengthValue.load(dynamicsModifier$Strength0.strengthValue);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int v, int v1) {
            int v2 = v * this.strengthChannel.strideSize;
            int v3 = v1 * this.strengthChannel.strideSize + v2;
            while(v2 < v3) {
                float f = this.strengthValue.newLowValue();
                float f1 = this.strengthValue.newHighValue();
                if(!this.strengthValue.isRelative()) {
                    f1 -= f;
                }
                this.strengthChannel.data[v2] = f;
                this.strengthChannel.data[v2 + 1] = f1;
                v2 += this.strengthChannel.strideSize;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier
        public void allocateChannels() {
            super.allocateChannels();
            ParticleChannels.Interpolation.id = this.controller.particleChannels.newId();
            this.strengthChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Interpolation);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier
        public void read(Json json0, JsonValue jsonValue0) {
            super.read(json0, jsonValue0);
            this.strengthValue = (ScaledNumericValue)json0.readValue("strengthValue", ScaledNumericValue.class, jsonValue0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier
        public void write(Json json0) {
            super.write(json0);
            json0.writeValue("strengthValue", this.strengthValue);
        }
    }

    public static class TangentialAcceleration extends Angular {
        FloatChannel directionalVelocityChannel;
        FloatChannel positionChannel;

        public TangentialAcceleration() {
        }

        public TangentialAcceleration(TangentialAcceleration dynamicsModifier$TangentialAcceleration0) {
            super(dynamicsModifier$TangentialAcceleration0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier$Angular
        public void allocateChannels() {
            super.allocateChannels();
            this.directionalVelocityChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Acceleration);
            this.positionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Position);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public TangentialAcceleration copy() {
            return new TangentialAcceleration(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int v = 0;
            int v1 = this.controller.particles.size * this.directionalVelocityChannel.strideSize;
            int v2 = 2;
            int v3 = 0;
            int v4 = 0;
            for(int v5 = 0; v < v1; v5 += this.positionChannel.strideSize) {
                float f = this.lifeChannel.data[v2];
                float f1 = this.strengthChannel.data[v3];
                float f2 = this.strengthChannel.data[v3 + 1];
                float f3 = this.strengthValue.getScale(f);
                float f4 = this.angularChannel.data[v4 + 2] + this.angularChannel.data[v4 + 3] * this.phiValue.getScale(f);
                float f5 = this.angularChannel.data[v4] + this.angularChannel.data[v4 + 1] * this.thetaValue.getScale(f);
                float f6 = MathUtils.cosDeg(f5);
                float f7 = MathUtils.sinDeg(f5);
                float f8 = MathUtils.cosDeg(f4);
                float f9 = MathUtils.sinDeg(f4);
                TangentialAcceleration.TMP_V3.set(f6 * f9, f8, f7 * f9);
                TangentialAcceleration.TMP_V1.set(this.positionChannel.data[v5], this.positionChannel.data[v5 + 1], this.positionChannel.data[v5 + 2]);
                if(!this.isGlobal) {
                    this.controller.transform.getTranslation(TangentialAcceleration.TMP_V2);
                    TangentialAcceleration.TMP_V1.sub(TangentialAcceleration.TMP_V2);
                    this.controller.transform.getRotation(TangentialAcceleration.TMP_Q, true);
                    TangentialAcceleration.TMP_V3.mul(TangentialAcceleration.TMP_Q);
                }
                TangentialAcceleration.TMP_V3.crs(TangentialAcceleration.TMP_V1).nor().scl(f1 + f2 * f3);
                float[] arr_f = this.directionalVelocityChannel.data;
                arr_f[v] += TangentialAcceleration.TMP_V3.x;
                float[] arr_f1 = this.directionalVelocityChannel.data;
                arr_f1[v + 1] += TangentialAcceleration.TMP_V3.y;
                float[] arr_f2 = this.directionalVelocityChannel.data;
                arr_f2[v + 2] += TangentialAcceleration.TMP_V3.z;
                v3 += this.strengthChannel.strideSize;
                v += this.directionalVelocityChannel.strideSize;
                v4 += this.angularChannel.strideSize;
                v2 += this.lifeChannel.strideSize;
            }
        }
    }

    protected static final Quaternion TMP_Q;
    protected static final Vector3 TMP_V1;
    protected static final Vector3 TMP_V2;
    protected static final Vector3 TMP_V3;
    public boolean isGlobal;
    protected FloatChannel lifeChannel;

    static {
        DynamicsModifier.TMP_V1 = new Vector3();
        DynamicsModifier.TMP_V2 = new Vector3();
        DynamicsModifier.TMP_V3 = new Vector3();
        DynamicsModifier.TMP_Q = new Quaternion();
    }

    public DynamicsModifier() {
        this.isGlobal = false;
    }

    public DynamicsModifier(DynamicsModifier dynamicsModifier0) {
        this.isGlobal = dynamicsModifier0.isGlobal;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.lifeChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.isGlobal = ((Boolean)json0.readValue("isGlobal", Boolean.TYPE, jsonValue0)).booleanValue();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("isGlobal", Boolean.valueOf(this.isGlobal));
    }
}

