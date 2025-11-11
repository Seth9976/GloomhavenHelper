package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData.Factory;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.Array;

public class TextureLoader extends AsynchronousAssetLoader {
    public static class TextureLoaderInfo {
        TextureData data;
        String filename;
        Texture texture;

    }

    public static class TextureParameter extends AssetLoaderParameters {
        public Format format;
        public boolean genMipMaps;
        public TextureFilter magFilter;
        public TextureFilter minFilter;
        public Texture texture;
        public TextureData textureData;
        public TextureWrap wrapU;
        public TextureWrap wrapV;

        public TextureParameter() {
            this.format = null;
            this.genMipMaps = false;
            this.texture = null;
            this.textureData = null;
            this.minFilter = TextureFilter.Nearest;
            this.magFilter = TextureFilter.Nearest;
            this.wrapU = TextureWrap.ClampToEdge;
            this.wrapV = TextureWrap.ClampToEdge;
        }
    }

    TextureLoaderInfo info;

    public TextureLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.info = new TextureLoaderInfo();
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((TextureParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, TextureParameter textureLoader$TextureParameter0) {
        return null;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((TextureParameter)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, TextureParameter textureLoader$TextureParameter0) {
        this.info.filename = s;
        if(textureLoader$TextureParameter0 == null || textureLoader$TextureParameter0.textureData == null) {
            boolean z = false;
            Format pixmap$Format0 = null;
            this.info.texture = null;
            if(textureLoader$TextureParameter0 != null) {
                pixmap$Format0 = textureLoader$TextureParameter0.format;
                z = textureLoader$TextureParameter0.genMipMaps;
                this.info.texture = textureLoader$TextureParameter0.texture;
            }
            TextureLoaderInfo textureLoader$TextureLoaderInfo0 = this.info;
            textureLoader$TextureLoaderInfo0.data = Factory.loadFromFile(fileHandle0, pixmap$Format0, z);
        }
        else {
            this.info.data = textureLoader$TextureParameter0.textureData;
            this.info.texture = textureLoader$TextureParameter0.texture;
        }
        if(!this.info.data.isPrepared()) {
            this.info.data.prepare();
        }
    }

    public Texture loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, TextureParameter textureLoader$TextureParameter0) {
        TextureLoaderInfo textureLoader$TextureLoaderInfo0 = this.info;
        if(textureLoader$TextureLoaderInfo0 == null) {
            return null;
        }
        Texture texture0 = textureLoader$TextureLoaderInfo0.texture;
        if(texture0 == null) {
            texture0 = new Texture(this.info.data);
        }
        else {
            texture0.load(this.info.data);
        }
        if(textureLoader$TextureParameter0 != null) {
            texture0.setFilter(textureLoader$TextureParameter0.minFilter, textureLoader$TextureParameter0.magFilter);
            texture0.setWrap(textureLoader$TextureParameter0.wrapU, textureLoader$TextureParameter0.wrapV);
        }
        return texture0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((TextureParameter)assetLoaderParameters0));
    }
}

