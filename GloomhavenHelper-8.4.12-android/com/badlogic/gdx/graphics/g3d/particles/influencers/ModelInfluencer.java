package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ObjectChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.SaveData;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public abstract class ModelInfluencer extends Influencer {
    public static class Random extends ModelInfluencer {
        class ModelInstancePool extends Pool {
            public ModelInstance newObject() {
                return new ModelInstance(((Model)Random.this.models.random()));
            }

            @Override  // com.badlogic.gdx.utils.Pool
            public Object newObject() {
                return this.newObject();
            }
        }

        ModelInstancePool pool;

        public Random() {
            this.pool = new ModelInstancePool(this);
        }

        public Random(Random modelInfluencer$Random0) {
            super(modelInfluencer$Random0);
            this.pool = new ModelInstancePool(this);
        }

        public Random(Model[] arr_model) {
            super(arr_model);
            this.pool = new ModelInstancePool(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int v, int v1) {
            int v2 = v1 + v;
            while(v < v2) {
                ModelInstance[] arr_modelInstance = (ModelInstance[])this.modelChannel.data;
                arr_modelInstance[v] = (ModelInstance)this.pool.obtain();
                ++v;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Random copy() {
            return new Random(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            this.pool.clear();
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int v, int v1) {
            int v2 = v1 + v;
            while(v < v2) {
                this.pool.free(((ModelInstance[])this.modelChannel.data)[v]);
                ((ModelInstance[])this.modelChannel.data)[v] = null;
                ++v;
            }
        }
    }

    public static class Single extends ModelInfluencer {
        public Single() {
        }

        public Single(Single modelInfluencer$Single0) {
            super(modelInfluencer$Single0);
        }

        public Single(Model[] arr_model) {
            super(arr_model);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Single copy() {
            return new Single(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            Model model0 = (Model)this.models.first();
            int v = this.controller.emitter.maxParticleCount;
            for(int v1 = 0; v1 < v; ++v1) {
                ModelInstance[] arr_modelInstance = (ModelInstance[])this.modelChannel.data;
                arr_modelInstance[v1] = new ModelInstance(model0);
            }
        }
    }

    ObjectChannel modelChannel;
    public Array models;

    public ModelInfluencer() {
        this.models = new Array(true, 1, Model.class);
    }

    public ModelInfluencer(ModelInfluencer modelInfluencer0) {
        this(((Model[])modelInfluencer0.models.toArray(Model.class)));
    }

    public ModelInfluencer(Model[] arr_model) {
        this.models = new Array(arr_model);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.modelChannel = (ObjectChannel)this.controller.particles.addChannel(ParticleChannels.ModelInstance);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        SaveData resourceData$SaveData0 = resourceData0.getSaveData();
        AssetDescriptor assetDescriptor0;
        while((assetDescriptor0 = resourceData$SaveData0.loadAsset()) != null) {
            Model model0 = (Model)assetManager0.get(assetDescriptor0);
            if(model0 == null) {
                throw new RuntimeException("Model is null");
            }
            this.models.add(model0);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        SaveData resourceData$SaveData0 = resourceData0.createSaveData();
        for(Object object0: this.models) {
            resourceData$SaveData0.saveAsset(assetManager0.getAssetFileName(((Model)object0)), Model.class);
        }
    }
}

