package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Page;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class TextureAtlasLoader extends SynchronousAssetLoader {
    public static class TextureAtlasParameter extends AssetLoaderParameters {
        public boolean flip;

        public TextureAtlasParameter() {
            this.flip = false;
        }

        public TextureAtlasParameter(boolean z) {
            this.flip = z;
        }
    }

    TextureAtlasData data;

    public TextureAtlasLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((TextureAtlasParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, TextureAtlasParameter textureAtlasLoader$TextureAtlasParameter0) {
        FileHandle fileHandle1 = fileHandle0.parent();
        this.data = textureAtlasLoader$TextureAtlasParameter0 == null ? new TextureAtlasData(fileHandle0, fileHandle1, false) : new TextureAtlasData(fileHandle0, fileHandle1, textureAtlasLoader$TextureAtlasParameter0.flip);
        Array array0 = new Array();
        for(Object object0: this.data.getPages()) {
            TextureParameter textureLoader$TextureParameter0 = new TextureParameter();
            textureLoader$TextureParameter0.format = ((Page)object0).format;
            textureLoader$TextureParameter0.genMipMaps = ((Page)object0).useMipMaps;
            textureLoader$TextureParameter0.minFilter = ((Page)object0).minFilter;
            textureLoader$TextureParameter0.magFilter = ((Page)object0).magFilter;
            array0.add(new AssetDescriptor(((Page)object0).textureFile, Texture.class, textureLoader$TextureParameter0));
        }
        return array0;
    }

    public TextureAtlas load(AssetManager assetManager0, String s, FileHandle fileHandle0, TextureAtlasParameter textureAtlasLoader$TextureAtlasParameter0) {
        for(Object object0: this.data.getPages()) {
            ((Page)object0).texture = (Texture)assetManager0.get(((Page)object0).textureFile.path().replaceAll("\\\\", "/"), Texture.class);
        }
        TextureAtlas textureAtlas0 = new TextureAtlas(this.data);
        this.data = null;
        return textureAtlas0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
    public Object load(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.load(assetManager0, s, fileHandle0, ((TextureAtlasParameter)assetLoaderParameters0));
    }
}

