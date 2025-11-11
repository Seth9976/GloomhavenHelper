package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.ObjectMap;

public class SkinLoader extends AsynchronousAssetLoader {
    public static class SkinParameter extends AssetLoaderParameters {
        public final ObjectMap resources;
        public final String textureAtlasPath;

        public SkinParameter() {
            this(null, null);
        }

        public SkinParameter(ObjectMap objectMap0) {
            this(null, objectMap0);
        }

        public SkinParameter(String s) {
            this(s, null);
        }

        public SkinParameter(String s, ObjectMap objectMap0) {
            this.textureAtlasPath = s;
            this.resources = objectMap0;
        }
    }

    public SkinLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((SkinParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, SkinParameter skinLoader$SkinParameter0) {
        Array array0 = new Array();
        if(skinLoader$SkinParameter0 != null && skinLoader$SkinParameter0.textureAtlasPath != null) {
            array0.add(new AssetDescriptor(skinLoader$SkinParameter0.textureAtlasPath, TextureAtlas.class));
            return array0;
        }
        array0.add(new AssetDescriptor(fileHandle0.pathWithoutExtension() + ".atlas", TextureAtlas.class));
        return array0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, SkinParameter skinLoader$SkinParameter0) {
    }

    public Skin loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, SkinParameter skinLoader$SkinParameter0) {
        String s1 = fileHandle0.pathWithoutExtension() + ".atlas";
        ObjectMap objectMap0 = null;
        if(skinLoader$SkinParameter0 != null) {
            if(skinLoader$SkinParameter0.textureAtlasPath != null) {
                s1 = skinLoader$SkinParameter0.textureAtlasPath;
            }
            if(skinLoader$SkinParameter0.resources != null) {
                objectMap0 = skinLoader$SkinParameter0.resources;
            }
        }
        Skin skin0 = this.newSkin(((TextureAtlas)assetManager0.get(s1, TextureAtlas.class)));
        if(objectMap0 != null) {
            for(Object object0: objectMap0.entries()) {
                skin0.add(((String)((Entry)object0).key), ((Entry)object0).value);
            }
        }
        skin0.load(fileHandle0);
        return skin0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((SkinParameter)assetLoaderParameters0));
    }

    protected Skin newSkin(TextureAtlas textureAtlas0) {
        return new Skin(textureAtlas0);
    }
}

