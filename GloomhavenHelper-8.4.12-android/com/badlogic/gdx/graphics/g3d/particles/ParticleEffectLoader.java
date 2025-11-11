package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.io.IOException;

public class ParticleEffectLoader extends AsynchronousAssetLoader {
    public static class ParticleEffectLoadParameter extends AssetLoaderParameters {
        Array batches;

        public ParticleEffectLoadParameter(Array array0) {
            this.batches = array0;
        }
    }

    public static class ParticleEffectSaveParameter extends AssetLoaderParameters {
        Array batches;
        FileHandle file;
        AssetManager manager;

        public ParticleEffectSaveParameter(FileHandle fileHandle0, AssetManager assetManager0, Array array0) {
            this.batches = array0;
            this.file = fileHandle0;
            this.manager = assetManager0;
        }
    }

    protected Array items;

    public ParticleEffectLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.items = new Array();
    }

    private Object find(Array array0, Class class0) {
        for(Object object0: array0) {
            if(ClassReflection.isAssignableFrom(class0, object0.getClass())) {
                return object0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((ParticleEffectLoadParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, ParticleEffectLoadParameter particleEffectLoader$ParticleEffectLoadParameter0) {
        ResourceData resourceData0 = (ResourceData)new Json().fromJson(ResourceData.class, fileHandle0);
        synchronized(this.items) {
            Entry objectMap$Entry0 = new Entry();
            objectMap$Entry0.key = s;
            objectMap$Entry0.value = resourceData0;
            this.items.add(objectMap$Entry0);
        }
        Array array1 = new Array();
        for(Object object0: resourceData0.getAssets()) {
            AssetData resourceData$AssetData0 = (AssetData)object0;
            if(!this.resolve(resourceData$AssetData0.filename).exists()) {
                resourceData$AssetData0.filename = fileHandle0.parent().child(Gdx.files.internal(resourceData$AssetData0.filename).name()).path();
            }
            if(resourceData$AssetData0.type == ParticleEffect.class) {
                array1.add(new AssetDescriptor(resourceData$AssetData0.filename, resourceData$AssetData0.type, particleEffectLoader$ParticleEffectLoadParameter0));
            }
            else {
                array1.add(new AssetDescriptor(resourceData$AssetData0.filename, resourceData$AssetData0.type));
            }
        }
        return array1;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, ParticleEffectLoadParameter particleEffectLoader$ParticleEffectLoadParameter0) {
    }

    public ParticleEffect loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, ParticleEffectLoadParameter particleEffectLoader$ParticleEffectLoadParameter0) {
        ResourceData resourceData0;
        synchronized(this.items) {
            for(int v1 = 0; true; ++v1) {
                resourceData0 = null;
                if(v1 >= this.items.size) {
                    break;
                }
                Entry objectMap$Entry0 = (Entry)this.items.get(v1);
                if(((String)objectMap$Entry0.key).equals(s)) {
                    resourceData0 = (ResourceData)objectMap$Entry0.value;
                    this.items.removeIndex(v1);
                    break;
                }
            }
        }
        ((ParticleEffect)resourceData0.resource).load(assetManager0, resourceData0);
        if(particleEffectLoader$ParticleEffectLoadParameter0 != null) {
            if(particleEffectLoader$ParticleEffectLoadParameter0.batches != null) {
                for(Object object0: particleEffectLoader$ParticleEffectLoadParameter0.batches) {
                    ((ParticleBatch)object0).load(assetManager0, resourceData0);
                }
            }
            ((ParticleEffect)resourceData0.resource).setBatch(particleEffectLoader$ParticleEffectLoadParameter0.batches);
        }
        return (ParticleEffect)resourceData0.resource;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((ParticleEffectLoadParameter)assetLoaderParameters0));
    }

    public void save(ParticleEffect particleEffect0, ParticleEffectSaveParameter particleEffectLoader$ParticleEffectSaveParameter0) throws IOException {
        ResourceData resourceData0 = new ResourceData(particleEffect0);
        particleEffect0.save(particleEffectLoader$ParticleEffectSaveParameter0.manager, resourceData0);
        if(particleEffectLoader$ParticleEffectSaveParameter0.batches != null) {
            for(Object object0: particleEffectLoader$ParticleEffectSaveParameter0.batches) {
                ParticleBatch particleBatch0 = (ParticleBatch)object0;
                boolean z = false;
                for(Object object1: particleEffect0.getControllers()) {
                    if(((ParticleController)object1).renderer.isCompatible(particleBatch0)) {
                        z = true;
                        break;
                    }
                    if(false) {
                        break;
                    }
                }
                if(z) {
                    particleBatch0.save(particleEffectLoader$ParticleEffectSaveParameter0.manager, resourceData0);
                }
            }
        }
        new Json().toJson(resourceData0, particleEffectLoader$ParticleEffectSaveParameter0.file);
    }
}

