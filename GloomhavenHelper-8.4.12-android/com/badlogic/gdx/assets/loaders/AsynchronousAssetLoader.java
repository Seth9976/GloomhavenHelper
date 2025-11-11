package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;

public abstract class AsynchronousAssetLoader extends AssetLoader {
    public AsynchronousAssetLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    public abstract void loadAsync(AssetManager arg1, String arg2, FileHandle arg3, AssetLoaderParameters arg4);

    public abstract Object loadSync(AssetManager arg1, String arg2, FileHandle arg3, AssetLoaderParameters arg4);

    public void unloadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
    }
}

