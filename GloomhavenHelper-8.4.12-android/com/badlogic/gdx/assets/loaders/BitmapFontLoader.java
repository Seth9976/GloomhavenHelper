package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class BitmapFontLoader extends AsynchronousAssetLoader {
    public static class BitmapFontParameter extends AssetLoaderParameters {
        public String atlasName;
        public BitmapFontData bitmapFontData;
        public boolean flip;
        public boolean genMipMaps;
        public TextureFilter magFilter;
        public TextureFilter minFilter;

        public BitmapFontParameter() {
            this.flip = false;
            this.genMipMaps = false;
            this.minFilter = TextureFilter.Nearest;
            this.magFilter = TextureFilter.Nearest;
            this.bitmapFontData = null;
            this.atlasName = null;
        }
    }

    BitmapFontData data;

    public BitmapFontLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((BitmapFontParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, BitmapFontParameter bitmapFontLoader$BitmapFontParameter0) {
        Array array0 = new Array();
        if(bitmapFontLoader$BitmapFontParameter0 != null && bitmapFontLoader$BitmapFontParameter0.bitmapFontData != null) {
            this.data = bitmapFontLoader$BitmapFontParameter0.bitmapFontData;
            return array0;
        }
        this.data = new BitmapFontData(fileHandle0, bitmapFontLoader$BitmapFontParameter0 != null && bitmapFontLoader$BitmapFontParameter0.flip);
        if(bitmapFontLoader$BitmapFontParameter0 != null && bitmapFontLoader$BitmapFontParameter0.atlasName != null) {
            array0.add(new AssetDescriptor(bitmapFontLoader$BitmapFontParameter0.atlasName, TextureAtlas.class));
            return array0;
        }
        for(int v = 0; v < this.data.getImagePaths().length; ++v) {
            FileHandle fileHandle1 = this.resolve(this.data.getImagePath(v));
            TextureParameter textureLoader$TextureParameter0 = new TextureParameter();
            if(bitmapFontLoader$BitmapFontParameter0 != null) {
                textureLoader$TextureParameter0.genMipMaps = bitmapFontLoader$BitmapFontParameter0.genMipMaps;
                textureLoader$TextureParameter0.minFilter = bitmapFontLoader$BitmapFontParameter0.minFilter;
                textureLoader$TextureParameter0.magFilter = bitmapFontLoader$BitmapFontParameter0.magFilter;
            }
            array0.add(new AssetDescriptor(fileHandle1, Texture.class, textureLoader$TextureParameter0));
        }
        return array0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, BitmapFontParameter bitmapFontLoader$BitmapFontParameter0) {
    }

    public BitmapFont loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, BitmapFontParameter bitmapFontLoader$BitmapFontParameter0) {
        if(bitmapFontLoader$BitmapFontParameter0 != null && bitmapFontLoader$BitmapFontParameter0.atlasName != null) {
            TextureAtlas textureAtlas0 = (TextureAtlas)assetManager0.get(bitmapFontLoader$BitmapFontParameter0.atlasName, TextureAtlas.class);
            String s1 = fileHandle0.sibling(this.data.imagePaths[0]).nameWithoutExtension().toString();
            AtlasRegion textureAtlas$AtlasRegion0 = textureAtlas0.findRegion(s1);
            if(textureAtlas$AtlasRegion0 == null) {
                throw new GdxRuntimeException("Could not find font region " + s1 + " in atlas " + bitmapFontLoader$BitmapFontParameter0.atlasName);
            }
            return new BitmapFont(fileHandle0, textureAtlas$AtlasRegion0);
        }
        String[] arr_s = this.data.getImagePaths();
        Array array0 = new Array(arr_s.length);
        for(int v = 0; v < arr_s.length; ++v) {
            array0.add(new TextureRegion(((Texture)assetManager0.get(this.data.getImagePath(v), Texture.class))));
        }
        return new BitmapFont(this.data, array0, true);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((BitmapFontParameter)assetLoaderParameters0));
    }
}

