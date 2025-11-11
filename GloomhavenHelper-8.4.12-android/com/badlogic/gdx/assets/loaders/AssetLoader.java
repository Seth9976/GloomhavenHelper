package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public abstract class AssetLoader {
    private FileHandleResolver resolver;

    public AssetLoader(FileHandleResolver fileHandleResolver0) {
        this.resolver = fileHandleResolver0;
    }

    public abstract Array getDependencies(String arg1, FileHandle arg2, AssetLoaderParameters arg3);

    public FileHandle resolve(String s) {
        return this.resolver.resolve(s);
    }
}

