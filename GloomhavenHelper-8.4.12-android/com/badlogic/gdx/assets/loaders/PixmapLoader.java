package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;

public class PixmapLoader extends AsynchronousAssetLoader {
    public static class PixmapParameter extends AssetLoaderParameters {
    }

    Pixmap pixmap;

    public PixmapLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((PixmapParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, PixmapParameter pixmapLoader$PixmapParameter0) {
        return null;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((PixmapParameter)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, PixmapParameter pixmapLoader$PixmapParameter0) {
        this.pixmap = new Pixmap(fileHandle0);
    }

    public Pixmap loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, PixmapParameter pixmapLoader$PixmapParameter0) {
        Pixmap pixmap0 = this.pixmap;
        this.pixmap = null;
        return pixmap0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((PixmapParameter)assetLoaderParameters0));
    }
}

