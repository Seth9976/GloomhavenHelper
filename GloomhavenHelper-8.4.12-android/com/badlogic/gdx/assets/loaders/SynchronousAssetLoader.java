package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;

public abstract class SynchronousAssetLoader extends AssetLoader {
    public SynchronousAssetLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    public abstract Object load(AssetManager arg1, String arg2, FileHandle arg3, AssetLoaderParameters arg4);
}

