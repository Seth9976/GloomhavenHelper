package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.Locale;

public class I18NBundleLoader extends AsynchronousAssetLoader {
    public static class I18NBundleParameter extends AssetLoaderParameters {
        public final String encoding;
        public final Locale locale;

        public I18NBundleParameter() {
            this(null, null);
        }

        public I18NBundleParameter(Locale locale0) {
            this(locale0, null);
        }

        public I18NBundleParameter(Locale locale0, String s) {
            this.locale = locale0;
            this.encoding = s;
        }
    }

    I18NBundle bundle;

    public I18NBundleLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((I18NBundleParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, I18NBundleParameter i18NBundleLoader$I18NBundleParameter0) {
        return null;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((I18NBundleParameter)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, I18NBundleParameter i18NBundleLoader$I18NBundleParameter0) {
        Locale locale0;
        String s1 = null;
        this.bundle = null;
        if(i18NBundleLoader$I18NBundleParameter0 == null) {
            locale0 = Locale.getDefault();
        }
        else {
            locale0 = i18NBundleLoader$I18NBundleParameter0.locale == null ? Locale.getDefault() : i18NBundleLoader$I18NBundleParameter0.locale;
            s1 = i18NBundleLoader$I18NBundleParameter0.encoding;
        }
        if(s1 == null) {
            this.bundle = I18NBundle.createBundle(fileHandle0, locale0);
            return;
        }
        this.bundle = I18NBundle.createBundle(fileHandle0, locale0, s1);
    }

    public I18NBundle loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, I18NBundleParameter i18NBundleLoader$I18NBundleParameter0) {
        I18NBundle i18NBundle0 = this.bundle;
        this.bundle = null;
        return i18NBundle0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((I18NBundleParameter)assetLoaderParameters0));
    }
}

