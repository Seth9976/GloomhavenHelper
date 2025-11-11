package com.badlogic.gdx.assets.loaders.resolvers;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class PrefixFileHandleResolver implements FileHandleResolver {
    private FileHandleResolver baseResolver;
    private String prefix;

    public PrefixFileHandleResolver(FileHandleResolver fileHandleResolver0, String s) {
        this.baseResolver = fileHandleResolver0;
        this.prefix = s;
    }

    public FileHandleResolver getBaseResolver() {
        return this.baseResolver;
    }

    public String getPrefix() {
        return this.prefix;
    }

    @Override  // com.badlogic.gdx.assets.loaders.FileHandleResolver
    public FileHandle resolve(String s) {
        return this.baseResolver.resolve(this.prefix + s);
    }

    public void setBaseResolver(FileHandleResolver fileHandleResolver0) {
        this.baseResolver = fileHandleResolver0;
    }

    public void setPrefix(String s) {
        this.prefix = s;
    }
}

