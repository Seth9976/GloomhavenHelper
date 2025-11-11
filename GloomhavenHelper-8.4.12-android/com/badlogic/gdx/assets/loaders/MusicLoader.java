package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class MusicLoader extends AsynchronousAssetLoader {
    public static class MusicParameter extends AssetLoaderParameters {
    }

    private Music music;

    public MusicLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((MusicParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, MusicParameter musicLoader$MusicParameter0) {
        return null;
    }

    protected Music getLoadedMusic() {
        return this.music;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((MusicParameter)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, MusicParameter musicLoader$MusicParameter0) {
        this.music = Gdx.audio.newMusic(fileHandle0);
    }

    public Music loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, MusicParameter musicLoader$MusicParameter0) {
        Music music0 = this.music;
        this.music = null;
        return music0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((MusicParameter)assetLoaderParameters0));
    }
}

