package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ObjectChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ModelInstanceParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;

public class ModelInstanceRenderer extends ParticleControllerRenderer {
    private boolean hasColor;
    private boolean hasRotation;
    private boolean hasScale;

    public ModelInstanceRenderer() {
        super(new ModelInstanceControllerRenderData());
    }

    public ModelInstanceRenderer(ModelInstanceParticleBatch modelInstanceParticleBatch0) {
        this.setBatch(modelInstanceParticleBatch0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        ModelInstanceControllerRenderData modelInstanceControllerRenderData0 = (ModelInstanceControllerRenderData)this.renderData;
        modelInstanceControllerRenderData0.positionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Position);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new ModelInstanceRenderer(((ModelInstanceParticleBatch)this.batch));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        ModelInstanceControllerRenderData modelInstanceControllerRenderData0 = (ModelInstanceControllerRenderData)this.renderData;
        modelInstanceControllerRenderData0.modelInstanceChannel = (ObjectChannel)this.controller.particles.getChannel(ParticleChannels.ModelInstance);
        ModelInstanceControllerRenderData modelInstanceControllerRenderData1 = (ModelInstanceControllerRenderData)this.renderData;
        modelInstanceControllerRenderData1.colorChannel = (FloatChannel)this.controller.particles.getChannel(ParticleChannels.Color);
        ModelInstanceControllerRenderData modelInstanceControllerRenderData2 = (ModelInstanceControllerRenderData)this.renderData;
        modelInstanceControllerRenderData2.scaleChannel = (FloatChannel)this.controller.particles.getChannel(ParticleChannels.Scale);
        ModelInstanceControllerRenderData modelInstanceControllerRenderData3 = (ModelInstanceControllerRenderData)this.renderData;
        modelInstanceControllerRenderData3.rotationChannel = (FloatChannel)this.controller.particles.getChannel(ParticleChannels.Rotation3D);
        boolean z = true;
        this.hasColor = ((ModelInstanceControllerRenderData)this.renderData).colorChannel != null;
        this.hasScale = ((ModelInstanceControllerRenderData)this.renderData).scaleChannel != null;
        if(((ModelInstanceControllerRenderData)this.renderData).rotationChannel == null) {
            z = false;
        }
        this.hasRotation = z;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public boolean isCompatible(ParticleBatch particleBatch0) {
        return particleBatch0 instanceof ModelInstanceParticleBatch;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public void update() {
        float f7;
        float f6;
        float f5;
        float f4;
        int v = this.controller.particles.size;
        int v1 = 0;
        for(int v2 = 0; v1 < v; v2 += ((ModelInstanceControllerRenderData)this.renderData).positionChannel.strideSize) {
            ModelInstance modelInstance0 = ((ModelInstance[])((ModelInstanceControllerRenderData)this.renderData).modelInstanceChannel.data)[v1];
            float f = this.hasScale ? ((ModelInstanceControllerRenderData)this.renderData).scaleChannel.data[v1] : 1.0f;
            if(this.hasRotation) {
                int v3 = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.strideSize * v1;
                float f1 = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.data[v3];
                float f2 = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.data[v3 + 1];
                float f3 = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.data[v3 + 2];
                f4 = ((ModelInstanceControllerRenderData)this.renderData).rotationChannel.data[v3 + 3];
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
            modelInstance0.transform.set(((ModelInstanceControllerRenderData)this.renderData).positionChannel.data[v2], ((ModelInstanceControllerRenderData)this.renderData).positionChannel.data[v2 + 1], ((ModelInstanceControllerRenderData)this.renderData).positionChannel.data[v2 + 2], f5, f6, f7, f4, f, f, f);
            if(this.hasColor) {
                int v4 = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.strideSize * v1;
                ColorAttribute colorAttribute0 = (ColorAttribute)((Material)modelInstance0.materials.get(0)).get(ColorAttribute.Diffuse);
                BlendingAttribute blendingAttribute0 = (BlendingAttribute)((Material)modelInstance0.materials.get(0)).get(BlendingAttribute.Type);
                colorAttribute0.color.r = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.data[v4];
                colorAttribute0.color.g = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.data[v4 + 1];
                colorAttribute0.color.b = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.data[v4 + 2];
                if(blendingAttribute0 != null) {
                    blendingAttribute0.opacity = ((ModelInstanceControllerRenderData)this.renderData).colorChannel.data[v4 + 3];
                }
            }
            ++v1;
        }
        super.update();
    }
}

