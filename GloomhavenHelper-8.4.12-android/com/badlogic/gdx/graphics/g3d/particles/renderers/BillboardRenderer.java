package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.ColorInitializer;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.Rotation2dInitializer;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.ScaleInitializer;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.TextureRegionInitializer;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;

public class BillboardRenderer extends ParticleControllerRenderer {
    public BillboardRenderer() {
        super(new BillboardControllerRenderData());
    }

    public BillboardRenderer(BillboardParticleBatch billboardParticleBatch0) {
        this.setBatch(billboardParticleBatch0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        BillboardControllerRenderData billboardControllerRenderData0 = (BillboardControllerRenderData)this.renderData;
        billboardControllerRenderData0.positionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Position);
        BillboardControllerRenderData billboardControllerRenderData1 = (BillboardControllerRenderData)this.renderData;
        ParallelArray parallelArray0 = this.controller.particles;
        TextureRegionInitializer particleChannels$TextureRegionInitializer0 = TextureRegionInitializer.get();
        billboardControllerRenderData1.regionChannel = (FloatChannel)parallelArray0.addChannel(ParticleChannels.TextureRegion, particleChannels$TextureRegionInitializer0);
        BillboardControllerRenderData billboardControllerRenderData2 = (BillboardControllerRenderData)this.renderData;
        ParallelArray parallelArray1 = this.controller.particles;
        ColorInitializer particleChannels$ColorInitializer0 = ColorInitializer.get();
        billboardControllerRenderData2.colorChannel = (FloatChannel)parallelArray1.addChannel(ParticleChannels.Color, particleChannels$ColorInitializer0);
        BillboardControllerRenderData billboardControllerRenderData3 = (BillboardControllerRenderData)this.renderData;
        ParallelArray parallelArray2 = this.controller.particles;
        ScaleInitializer particleChannels$ScaleInitializer0 = ScaleInitializer.get();
        billboardControllerRenderData3.scaleChannel = (FloatChannel)parallelArray2.addChannel(ParticleChannels.Scale, particleChannels$ScaleInitializer0);
        BillboardControllerRenderData billboardControllerRenderData4 = (BillboardControllerRenderData)this.renderData;
        ParallelArray parallelArray3 = this.controller.particles;
        Rotation2dInitializer particleChannels$Rotation2dInitializer0 = Rotation2dInitializer.get();
        billboardControllerRenderData4.rotationChannel = (FloatChannel)parallelArray3.addChannel(ParticleChannels.Rotation2D, particleChannels$Rotation2dInitializer0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new BillboardRenderer(((BillboardParticleBatch)this.batch));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public boolean isCompatible(ParticleBatch particleBatch0) {
        return particleBatch0 instanceof BillboardParticleBatch;
    }
}

