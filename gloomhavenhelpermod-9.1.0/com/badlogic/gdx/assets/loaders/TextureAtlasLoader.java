package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class TextureAtlasLoader extends SynchronousAssetLoader {
   TextureAtlas.TextureAtlasData data;

   public TextureAtlasLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   public TextureAtlas load(AssetManager assetManager, String fileName, FileHandle file, TextureAtlasLoader.TextureAtlasParameter parameter) {
      for (TextureAtlas.TextureAtlasData.Page page : this.data.getPages()) {
         Texture texture = (Texture)assetManager.get(page.textureFile.path().replaceAll("\\\\", "/"), Texture.class);
         page.texture = texture;
      }

      TextureAtlas atlas = new TextureAtlas(this.data);
      this.data = null;
      return atlas;
   }

   public Array getDependencies(String fileName, FileHandle atlasFile, TextureAtlasLoader.TextureAtlasParameter parameter) {
      FileHandle imgDir = atlasFile.parent();
      if (parameter != null) {
         this.data = new TextureAtlas.TextureAtlasData(atlasFile, imgDir, parameter.flip);
      } else {
         this.data = new TextureAtlas.TextureAtlasData(atlasFile, imgDir, false);
      }

      Array dependencies = new Array();

      for (TextureAtlas.TextureAtlasData.Page page : this.data.getPages()) {
         TextureLoader.TextureParameter params = new TextureLoader.TextureParameter();
         params.format = page.format;
         params.genMipMaps = page.useMipMaps;
         params.minFilter = page.minFilter;
         params.magFilter = page.magFilter;
         dependencies.add(new AssetDescriptor(page.textureFile, Texture.class, params));
      }

      return dependencies;
   }

   public static class TextureAtlasParameter extends AssetLoaderParameters {
      public boolean flip = false;

      public TextureAtlasParameter() {
      }

      public TextureAtlasParameter(boolean flip) {
         this.flip = flip;
      }
   }
}
