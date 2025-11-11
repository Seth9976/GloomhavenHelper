package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import java.util.Arrays;

public class ParticleChannels {
    public static class ColorInitializer implements ChannelInitializer {
        private static ColorInitializer instance;

        public static ColorInitializer get() {
            if(ColorInitializer.instance == null) {
                ColorInitializer.instance = new ColorInitializer();
            }
            return ColorInitializer.instance;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$ChannelInitializer
        public void init(Channel parallelArray$Channel0) {
            this.init(((FloatChannel)parallelArray$Channel0));
        }

        public void init(FloatChannel parallelArray$FloatChannel0) {
            Arrays.fill(parallelArray$FloatChannel0.data, 0, parallelArray$FloatChannel0.data.length, 1.0f);
        }
    }

    public static class Rotation2dInitializer implements ChannelInitializer {
        private static Rotation2dInitializer instance;

        public static Rotation2dInitializer get() {
            if(Rotation2dInitializer.instance == null) {
                Rotation2dInitializer.instance = new Rotation2dInitializer();
            }
            return Rotation2dInitializer.instance;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$ChannelInitializer
        public void init(Channel parallelArray$Channel0) {
            this.init(((FloatChannel)parallelArray$Channel0));
        }

        public void init(FloatChannel parallelArray$FloatChannel0) {
            for(int v = 0; v < parallelArray$FloatChannel0.data.length; v += parallelArray$FloatChannel0.strideSize) {
                parallelArray$FloatChannel0.data[v] = 1.0f;
                parallelArray$FloatChannel0.data[v + 1] = 0.0f;
            }
        }
    }

    public static class Rotation3dInitializer implements ChannelInitializer {
        private static Rotation3dInitializer instance;

        public static Rotation3dInitializer get() {
            if(Rotation3dInitializer.instance == null) {
                Rotation3dInitializer.instance = new Rotation3dInitializer();
            }
            return Rotation3dInitializer.instance;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$ChannelInitializer
        public void init(Channel parallelArray$Channel0) {
            this.init(((FloatChannel)parallelArray$Channel0));
        }

        public void init(FloatChannel parallelArray$FloatChannel0) {
            for(int v = 0; v < parallelArray$FloatChannel0.data.length; v += parallelArray$FloatChannel0.strideSize) {
                float[] arr_f = parallelArray$FloatChannel0.data;
                float[] arr_f1 = parallelArray$FloatChannel0.data;
                parallelArray$FloatChannel0.data[v + 2] = 0.0f;
                arr_f1[v + 1] = 0.0f;
                arr_f[v] = 0.0f;
                parallelArray$FloatChannel0.data[v + 3] = 1.0f;
            }
        }
    }

    public static class ScaleInitializer implements ChannelInitializer {
        private static ScaleInitializer instance;

        public static ScaleInitializer get() {
            if(ScaleInitializer.instance == null) {
                ScaleInitializer.instance = new ScaleInitializer();
            }
            return ScaleInitializer.instance;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$ChannelInitializer
        public void init(Channel parallelArray$Channel0) {
            this.init(((FloatChannel)parallelArray$Channel0));
        }

        public void init(FloatChannel parallelArray$FloatChannel0) {
            Arrays.fill(parallelArray$FloatChannel0.data, 0, parallelArray$FloatChannel0.data.length, 1.0f);
        }
    }

    public static class TextureRegionInitializer implements ChannelInitializer {
        private static TextureRegionInitializer instance;

        public static TextureRegionInitializer get() {
            if(TextureRegionInitializer.instance == null) {
                TextureRegionInitializer.instance = new TextureRegionInitializer();
            }
            return TextureRegionInitializer.instance;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$ChannelInitializer
        public void init(Channel parallelArray$Channel0) {
            this.init(((FloatChannel)parallelArray$Channel0));
        }

        public void init(FloatChannel parallelArray$FloatChannel0) {
            for(int v = 0; v < parallelArray$FloatChannel0.data.length; v += parallelArray$FloatChannel0.strideSize) {
                parallelArray$FloatChannel0.data[v] = 0.0f;
                parallelArray$FloatChannel0.data[v + 1] = 0.0f;
                parallelArray$FloatChannel0.data[v + 2] = 1.0f;
                parallelArray$FloatChannel0.data[v + 3] = 1.0f;
                parallelArray$FloatChannel0.data[v + 4] = 0.5f;
                parallelArray$FloatChannel0.data[v + 5] = 0.5f;
            }
        }
    }

    public static final ChannelDescriptor Acceleration = null;
    public static final int AlphaOffset = 3;
    public static final ChannelDescriptor AngularVelocity2D = null;
    public static final ChannelDescriptor AngularVelocity3D = null;
    public static final int BlueOffset = 2;
    public static final ChannelDescriptor Color = null;
    public static final int CosineOffset = 0;
    public static final int CurrentLifeOffset = 0;
    public static final int GreenOffset = 1;
    public static final int HalfHeightOffset = 5;
    public static final int HalfWidthOffset = 4;
    public static final ChannelDescriptor Interpolation = null;
    public static final ChannelDescriptor Interpolation4 = null;
    public static final ChannelDescriptor Interpolation6 = null;
    public static final int InterpolationDiffOffset = 1;
    public static final int InterpolationStartOffset = 0;
    public static final ChannelDescriptor Life = null;
    public static final int LifePercentOffset = 2;
    public static final ChannelDescriptor ModelInstance = null;
    public static final ChannelDescriptor ParticleController = null;
    public static final ChannelDescriptor Position = null;
    public static final ChannelDescriptor PreviousPosition = null;
    public static final int RedOffset = 0;
    public static final ChannelDescriptor Rotation2D = null;
    public static final ChannelDescriptor Rotation3D = null;
    public static final ChannelDescriptor Scale = null;
    public static final int SineOffset = 1;
    public static final ChannelDescriptor TextureRegion = null;
    public static final int TotalLifeOffset = 1;
    public static final int U2Offset = 2;
    public static final int UOffset = 0;
    public static final int V2Offset = 3;
    public static final int VOffset = 1;
    public static final int VelocityPhiDiffOffset = 3;
    public static final int VelocityPhiStartOffset = 2;
    public static final int VelocityStrengthDiffOffset = 1;
    public static final int VelocityStrengthStartOffset = 0;
    public static final int VelocityThetaDiffOffset = 1;
    public static final int VelocityThetaStartOffset = 0;
    public static final int WOffset = 3;
    public static final int XOffset = 0;
    public static final int YOffset = 1;
    public static final int ZOffset = 2;
    private static int currentGlobalId;
    private int currentId;

    static {
        ParticleChannels.Life = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 3);
        ParticleChannels.Position = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 3);
        ParticleChannels.PreviousPosition = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 3);
        ParticleChannels.Color = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 4);
        ParticleChannels.TextureRegion = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 6);
        ParticleChannels.Rotation2D = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 2);
        ParticleChannels.Rotation3D = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 4);
        ParticleChannels.Scale = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 1);
        ParticleChannels.ModelInstance = new ChannelDescriptor(ParticleChannels.newGlobalId(), ModelInstance.class, 1);
        ParticleChannels.ParticleController = new ChannelDescriptor(ParticleChannels.newGlobalId(), ParticleController.class, 1);
        ParticleChannels.Acceleration = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 3);
        ParticleChannels.AngularVelocity2D = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 1);
        ParticleChannels.AngularVelocity3D = new ChannelDescriptor(ParticleChannels.newGlobalId(), Float.TYPE, 3);
        ParticleChannels.Interpolation = new ChannelDescriptor(-1, Float.TYPE, 2);
        ParticleChannels.Interpolation4 = new ChannelDescriptor(-1, Float.TYPE, 4);
        ParticleChannels.Interpolation6 = new ChannelDescriptor(-1, Float.TYPE, 6);
    }

    public ParticleChannels() {
        this.resetIds();
    }

    public static int newGlobalId() {
        int v = ParticleChannels.currentGlobalId;
        ParticleChannels.currentGlobalId = v + 1;
        return v;
    }

    public int newId() {
        int v = this.currentId;
        this.currentId = v + 1;
        return v;
    }

    protected void resetIds() {
        this.currentId = ParticleChannels.currentGlobalId;
    }
}

