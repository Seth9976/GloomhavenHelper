package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class ParticleEffectLoader extends SynchronousAssetLoader {
    public static class ParticleEffectParameter extends AssetLoaderParameters {
        public String atlasFile;
        public String atlasPrefix;
        public FileHandle imagesDir;

    }

    public ParticleEffectLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((ParticleEffectParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, ParticleEffectParameter particleEffectLoader$ParticleEffectParameter0) {
        if(particleEffectLoader$ParticleEffectParameter0 != null && particleEffectLoader$ParticleEffectParameter0.atlasFile != null) {
            Array array0 = new Array();
            array0.add(new AssetDescriptor(particleEffectLoader$ParticleEffectParameter0.atlasFile, TextureAtlas.class));
            return array0;
        }
        return null;
    }

    public ParticleEffect load(AssetManager assetManager0, String s, FileHandle fileHandle0, ParticleEffectParameter particleEffectLoader$ParticleEffectParameter0) {
        ParticleEffect particleEffect0 = new ParticleEffect();
        if(particleEffectLoader$ParticleEffectParameter0 != null && particleEffectLoader$ParticleEffectParameter0.atlasFile != null) {
            particleEffect0.load(fileHandle0, ((TextureAtlas)assetManager0.get(particleEffectLoader$ParticleEffectParameter0.atlasFile, TextureAtlas.class)), particleEffectLoader$ParticleEffectParameter0.atlasPrefix);
            return particleEffect0;
        }
        if(particleEffectLoader$ParticleEffectParameter0 != null && particleEffectLoader$ParticleEffectParameter0.imagesDir != null) {
            particleEffect0.load(fileHandle0, particleEffectLoader$ParticleEffectParameter0.imagesDir);
            return particleEffect0;
        }
        particleEffect0.load(fileHandle0, fileHandle0.parent());
        return particleEffect0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
    public Object load(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.load(assetManager0, s, fileHandle0, ((ParticleEffectParameter)assetLoaderParameters0));
    }
}

