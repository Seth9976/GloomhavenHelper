package com.badlogic.gdx.assets.loaders.resolvers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class LocalFileHandleResolver implements FileHandleResolver {
    @Override  // com.badlogic.gdx.assets.loaders.FileHandleResolver
    public FileHandle resolve(String s) {
        return Gdx.files.local(s);
    }
}

