package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.glutils.KTXTextureData;
import com.badlogic.gdx.utils.Array;

public class CubemapLoader extends AsynchronousAssetLoader {
    public static class CubemapLoaderInfo {
        Cubemap cubemap;
        CubemapData data;
        String filename;

    }

    public static class CubemapParameter extends AssetLoaderParameters {
        public Cubemap cubemap;
        public CubemapData cubemapData;
        public Format format;
        public TextureFilter magFilter;
        public TextureFilter minFilter;
        public TextureWrap wrapU;
        public TextureWrap wrapV;

        public CubemapParameter() {
            this.format = null;
            this.cubemap = null;
            this.cubemapData = null;
            this.minFilter = TextureFilter.Nearest;
            this.magFilter = TextureFilter.Nearest;
            this.wrapU = TextureWrap.ClampToEdge;
            this.wrapV = TextureWrap.ClampToEdge;
        }
    }

    CubemapLoaderInfo info;

    public CubemapLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.info = new CubemapLoaderInfo();
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((CubemapParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, CubemapParameter cubemapLoader$CubemapParameter0) {
        return null;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((CubemapParameter)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, CubemapParameter cubemapLoader$CubemapParameter0) {
        this.info.filename = s;
        if(cubemapLoader$CubemapParameter0 == null || cubemapLoader$CubemapParameter0.cubemapData == null) {
            this.info.cubemap = null;
            if(cubemapLoader$CubemapParameter0 != null) {
                this.info.cubemap = cubemapLoader$CubemapParameter0.cubemap;
            }
            if(s.contains(".ktx") || s.contains(".zktx")) {
                CubemapLoaderInfo cubemapLoader$CubemapLoaderInfo0 = this.info;
                cubemapLoader$CubemapLoaderInfo0.data = new KTXTextureData(fileHandle0, false);
            }
        }
        else {
            this.info.data = cubemapLoader$CubemapParameter0.cubemapData;
            this.info.cubemap = cubemapLoader$CubemapParameter0.cubemap;
        }
        if(!this.info.data.isPrepared()) {
            this.info.data.prepare();
        }
    }

    public Cubemap loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, CubemapParameter cubemapLoader$CubemapParameter0) {
        CubemapLoaderInfo cubemapLoader$CubemapLoaderInfo0 = this.info;
        if(cubemapLoader$CubemapLoaderInfo0 == null) {
            return null;
        }
        Cubemap cubemap0 = cubemapLoader$CubemapLoaderInfo0.cubemap;
        if(cubemap0 == null) {
            cubemap0 = new Cubemap(this.info.data);
        }
        else {
            cubemap0.load(this.info.data);
        }
        if(cubemapLoader$CubemapParameter0 != null) {
            cubemap0.setFilter(cubemapLoader$CubemapParameter0.minFilter, cubemapLoader$CubemapParameter0.magFilter);
            cubemap0.setWrap(cubemapLoader$CubemapParameter0.wrapU, cubemapLoader$CubemapParameter0.wrapV);
        }
        return cubemap0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((CubemapParameter)assetLoaderParameters0));
    }
}

