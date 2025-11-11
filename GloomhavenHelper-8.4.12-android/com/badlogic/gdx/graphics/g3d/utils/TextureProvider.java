package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.Texture;

public interface TextureProvider {
    public static class AssetTextureProvider implements TextureProvider {
        public final AssetManager assetManager;

        public AssetTextureProvider(AssetManager assetManager0) {
            this.assetManager = assetManager0;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureProvider
        public Texture load(String s) {
            return (Texture)this.assetManager.get(s, Texture.class);
        }
    }

    public static class FileTextureProvider implements TextureProvider {
        private TextureFilter magFilter;
        private TextureFilter minFilter;
        private TextureWrap uWrap;
        private boolean useMipMaps;
        private TextureWrap vWrap;

        public FileTextureProvider() {
            this.magFilter = TextureFilter.Linear;
            this.minFilter = TextureFilter.Linear;
            this.vWrap = TextureWrap.Repeat;
            this.uWrap = TextureWrap.Repeat;
            this.useMipMaps = false;
        }

        public FileTextureProvider(TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, TextureWrap texture$TextureWrap0, TextureWrap texture$TextureWrap1, boolean z) {
            this.minFilter = texture$TextureFilter0;
            this.magFilter = texture$TextureFilter1;
            this.uWrap = texture$TextureWrap0;
            this.vWrap = texture$TextureWrap1;
            this.useMipMaps = z;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureProvider
        public Texture load(String s) {
            Texture texture0 = new Texture(Gdx.files.internal(s), this.useMipMaps);
            texture0.setFilter(this.minFilter, this.magFilter);
            texture0.setWrap(this.uWrap, this.vWrap);
            return texture0;
        }
    }

    Texture load(String arg1);
}

