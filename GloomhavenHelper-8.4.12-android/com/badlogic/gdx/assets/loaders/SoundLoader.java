package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class SoundLoader extends AsynchronousAssetLoader {
    public static class SoundParameter extends AssetLoaderParameters {
    }

    private Sound sound;

    public SoundLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((SoundParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, SoundParameter soundLoader$SoundParameter0) {
        return null;
    }

    protected Sound getLoadedSound() {
        return this.sound;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((SoundParameter)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, SoundParameter soundLoader$SoundParameter0) {
        this.sound = Gdx.audio.newSound(fileHandle0);
    }

    public Sound loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, SoundParameter soundLoader$SoundParameter0) {
        Sound sound0 = this.sound;
        this.sound = null;
        return sound0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((SoundParameter)assetLoaderParameters0));
    }
}

