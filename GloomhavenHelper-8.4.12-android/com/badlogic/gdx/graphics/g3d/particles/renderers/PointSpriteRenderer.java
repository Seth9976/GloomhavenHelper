package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.ColorInitializer;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.Rotation2dInitializer;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.ScaleInitializer;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.TextureRegionInitializer;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.PointSpriteParticleBatch;

public class PointSpriteRenderer extends ParticleControllerRenderer {
    public PointSpriteRenderer() {
        super(new PointSpriteControllerRenderData());
    }

    public PointSpriteRenderer(PointSpriteParticleBatch pointSpriteParticleBatch0) {
        this.setBatch(pointSpriteParticleBatch0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        PointSpriteControllerRenderData pointSpriteControllerRenderData0 = (PointSpriteControllerRenderData)this.renderData;
        pointSpriteControllerRenderData0.positionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Position);
        PointSpriteControllerRenderData pointSpriteControllerRenderData1 = (PointSpriteControllerRenderData)this.renderData;
        ParallelArray parallelArray0 = this.controller.particles;
        TextureRegionInitializer particleChannels$TextureRegionInitializer0 = TextureRegionInitializer.get();
        pointSpriteControllerRenderData1.regionChannel = (FloatChannel)parallelArray0.addChannel(ParticleChannels.TextureRegion, particleChannels$TextureRegionInitializer0);
        PointSpriteControllerRenderData pointSpriteControllerRenderData2 = (PointSpriteControllerRenderData)this.renderData;
        ParallelArray parallelArray1 = this.controller.particles;
        ColorInitializer particleChannels$ColorInitializer0 = ColorInitializer.get();
        pointSpriteControllerRenderData2.colorChannel = (FloatChannel)parallelArray1.addChannel(ParticleChannels.Color, particleChannels$ColorInitializer0);
        PointSpriteControllerRenderData pointSpriteControllerRenderData3 = (PointSpriteControllerRenderData)this.renderData;
        ParallelArray parallelArray2 = this.controller.particles;
        ScaleInitializer particleChannels$ScaleInitializer0 = ScaleInitializer.get();
        pointSpriteControllerRenderData3.scaleChannel = (FloatChannel)parallelArray2.addChannel(ParticleChannels.Scale, particleChannels$ScaleInitializer0);
        PointSpriteControllerRenderData pointSpriteControllerRenderData4 = (PointSpriteControllerRenderData)this.renderData;
        ParallelArray parallelArray3 = this.controller.particles;
        Rotation2dInitializer particleChannels$Rotation2dInitializer0 = Rotation2dInitializer.get();
        pointSpriteControllerRenderData4.rotationChannel = (FloatChannel)parallelArray3.addChannel(ParticleChannels.Rotation2D, particleChannels$Rotation2dInitializer0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new PointSpriteRenderer(((PointSpriteParticleBatch)this.batch));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public boolean isCompatible(ParticleBatch particleBatch0) {
        return particleBatch0 instanceof PointSpriteParticleBatch;
    }
}

