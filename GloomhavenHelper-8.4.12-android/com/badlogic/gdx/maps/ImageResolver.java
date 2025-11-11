package com.badlogic.gdx.maps;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public interface ImageResolver {
    public static class AssetManagerImageResolver implements ImageResolver {
        private final AssetManager assetManager;

        public AssetManagerImageResolver(AssetManager assetManager0) {
            this.assetManager = assetManager0;
        }

        @Override  // com.badlogic.gdx.maps.ImageResolver
        public TextureRegion getImage(String s) {
            return new TextureRegion(((Texture)this.assetManager.get(s, Texture.class)));
        }
    }

    public static class DirectImageResolver implements ImageResolver {
        private final ObjectMap images;

        public DirectImageResolver(ObjectMap objectMap0) {
            this.images = objectMap0;
        }

        @Override  // com.badlogic.gdx.maps.ImageResolver
        public TextureRegion getImage(String s) {
            return new TextureRegion(((Texture)this.images.get(s)));
        }
    }

    public static class TextureAtlasImageResolver implements ImageResolver {
        private final TextureAtlas atlas;

        public TextureAtlasImageResolver(TextureAtlas textureAtlas0) {
            this.atlas = textureAtlas0;
        }

        @Override  // com.badlogic.gdx.maps.ImageResolver
        public TextureRegion getImage(String s) {
            return this.atlas.findRegion(s);
        }
    }

    TextureRegion getImage(String arg1);
}

