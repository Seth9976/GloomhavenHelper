package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ObjectChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.SaveData;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Pool;

public abstract class ParticleControllerInfluencer extends Influencer {
    public static class Random extends ParticleControllerInfluencer {
        class ParticleControllerPool extends Pool {
            @Override  // com.badlogic.gdx.utils.Pool
            public void clear() {
                int v = Random.this.pool.getFree();
                for(int v1 = 0; v1 < v; ++v1) {
                    ((ParticleController)Random.this.pool.obtain()).dispose();
                }
                super.clear();
            }

            public ParticleController newObject() {
                ParticleController particleController0 = ((ParticleController)Random.this.templates.random()).copy();
                particleController0.init();
                return particleController0;
            }

            @Override  // com.badlogic.gdx.utils.Pool
            public Object newObject() {
                return this.newObject();
            }
        }

        ParticleControllerPool pool;

        public Random() {
            this.pool = new ParticleControllerPool(this);
        }

        public Random(Random particleControllerInfluencer$Random0) {
            super(particleControllerInfluencer$Random0);
            this.pool = new ParticleControllerPool(this);
        }

        public Random(ParticleController[] arr_particleController) {
            super(arr_particleController);
            this.pool = new ParticleControllerPool(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int v, int v1) {
            int v2 = v1 + v;
            while(v < v2) {
                ParticleController particleController0 = (ParticleController)this.pool.obtain();
                particleController0.start();
                ((ParticleController[])this.particleControllerChannel.data)[v] = particleController0;
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

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.ParticleControllerInfluencer
        public void dispose() {
            this.pool.clear();
            super.dispose();
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            this.pool.clear();
            for(int v = 0; v < this.controller.emitter.maxParticleCount; ++v) {
                this.pool.free(this.pool.newObject());
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int v, int v1) {
            int v2 = v1 + v;
            while(v < v2) {
                ParticleController particleController0 = ((ParticleController[])this.particleControllerChannel.data)[v];
                particleController0.end();
                this.pool.free(particleController0);
                ((ParticleController[])this.particleControllerChannel.data)[v] = null;
                ++v;
            }
        }
    }

    public static class Single extends ParticleControllerInfluencer {
        public Single() {
        }

        public Single(Single particleControllerInfluencer$Single0) {
            super(particleControllerInfluencer$Single0);
        }

        public Single(ParticleController[] arr_particleController) {
            super(arr_particleController);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int v, int v1) {
            int v2 = v1 + v;
            while(v < v2) {
                ((ParticleController[])this.particleControllerChannel.data)[v].start();
                ++v;
            }
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
            ParticleController particleController0 = (ParticleController)this.templates.first();
            int v = this.controller.particles.capacity;
            for(int v1 = 0; v1 < v; ++v1) {
                ParticleController particleController1 = particleController0.copy();
                particleController1.init();
                ((ParticleController[])this.particleControllerChannel.data)[v1] = particleController1;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int v, int v1) {
            int v2 = v1 + v;
            while(v < v2) {
                ((ParticleController[])this.particleControllerChannel.data)[v].end();
                ++v;
            }
        }
    }

    ObjectChannel particleControllerChannel;
    public Array templates;

    public ParticleControllerInfluencer() {
        this.templates = new Array(true, 1, ParticleController.class);
    }

    public ParticleControllerInfluencer(ParticleControllerInfluencer particleControllerInfluencer0) {
        this(((ParticleController[])particleControllerInfluencer0.templates.items));
    }

    public ParticleControllerInfluencer(ParticleController[] arr_particleController) {
        this.templates = new Array(arr_particleController);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.particleControllerChannel = (ObjectChannel)this.controller.particles.addChannel(ParticleChannels.ParticleController);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void dispose() {
        if(this.controller != null) {
            for(int v = 0; v < this.controller.particles.size; ++v) {
                ParticleController particleController0 = ((ParticleController[])this.particleControllerChannel.data)[v];
                if(particleController0 != null) {
                    particleController0.dispose();
                    ((ParticleController[])this.particleControllerChannel.data)[v] = null;
                }
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void end() {
        for(int v = 0; v < this.controller.particles.size; ++v) {
            ((ParticleController[])this.particleControllerChannel.data)[v].end();
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        int v;
        IntArray intArray0;
        Array array0;
        SaveData resourceData$SaveData0 = resourceData0.getSaveData();
        ArrayIterator array$ArrayIterator0 = ((Array)resourceData$SaveData0.load("indices")).iterator();
        do {
            AssetDescriptor assetDescriptor0 = resourceData$SaveData0.loadAsset();
            if(assetDescriptor0 == null) {
                return;
            }
            ParticleEffect particleEffect0 = (ParticleEffect)assetManager0.get(assetDescriptor0);
            if(particleEffect0 == null) {
                throw new RuntimeException("Template is null");
            }
            array0 = particleEffect0.getControllers();
            Object object0 = array$ArrayIterator0.next();
            intArray0 = (IntArray)object0;
            v = 0;
            int v1 = intArray0.size;
        label_11:
        }
        while(v >= v1);
        this.templates.add(array0.get(intArray0.get(v)));
        ++v;
        goto label_11;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        SaveData resourceData$SaveData0 = resourceData0.createSaveData();
        Array array0 = new Array();
        Array array1 = assetManager0.getAll(ParticleEffect.class, array0);
        Array array2 = new Array(this.templates);
        Array array3 = new Array();
        for(int v = 0; v < array1.size && array2.size > 0; ++v) {
            ParticleEffect particleEffect0 = (ParticleEffect)array1.get(v);
            Array array4 = particleEffect0.getControllers();
            IntArray intArray0 = null;
            ArrayIterator array$ArrayIterator0 = array2.iterator();
            while(array$ArrayIterator0.hasNext()) {
                Object object0 = array$ArrayIterator0.next();
                int v1 = array4.indexOf(((ParticleController)object0), true);
                if(v1 > -1) {
                    if(intArray0 == null) {
                        intArray0 = new IntArray();
                    }
                    array$ArrayIterator0.remove();
                    intArray0.add(v1);
                }
            }
            if(intArray0 != null) {
                resourceData$SaveData0.saveAsset(assetManager0.getAssetFileName(particleEffect0), ParticleEffect.class);
                array3.add(intArray0);
            }
        }
        resourceData$SaveData0.save("indices", array3);
    }
}

