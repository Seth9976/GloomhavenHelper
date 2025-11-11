package com.badlogic.gdx.assets;

public class AssetLoaderParameters {
    public interface LoadedCallback {
        void finishedLoading(AssetManager arg1, String arg2, Class arg3);
    }

    public LoadedCallback loadedCallback;

}

