package com.badlogic.gdx.assets;

import com.badlogic.gdx.files.FileHandle;

public class AssetDescriptor {
    public FileHandle file;
    public final String fileName;
    public final AssetLoaderParameters params;
    public final Class type;

    public AssetDescriptor(FileHandle fileHandle0, Class class0) {
        this(fileHandle0, class0, null);
    }

    public AssetDescriptor(FileHandle fileHandle0, Class class0, AssetLoaderParameters assetLoaderParameters0) {
        this.fileName = fileHandle0.path().replace('\\', '/');
        this.file = fileHandle0;
        this.type = class0;
        this.params = assetLoaderParameters0;
    }

    public AssetDescriptor(String s, Class class0) {
        this(s, class0, null);
    }

    public AssetDescriptor(String s, Class class0, AssetLoaderParameters assetLoaderParameters0) {
        this.fileName = s.replace('\\', '/');
        this.type = class0;
        this.params = assetLoaderParameters0;
    }

    @Override
    public String toString() {
        return this.fileName + ", " + this.type.getName();
    }
}

