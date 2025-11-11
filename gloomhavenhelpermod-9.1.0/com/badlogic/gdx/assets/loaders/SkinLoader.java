package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class SkinLoader extends AsynchronousAssetLoader {
   public SkinLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   public Array getDependencies(String fileName, FileHandle file, SkinLoader.SkinParameter parameter) {
      Array deps = new Array();
      if (parameter == null || parameter.textureAtlasPath == null) {
         deps.add(new AssetDescriptor(file.pathWithoutExtension() + ".atlas", TextureAtlas.class));
      } else if (parameter.textureAtlasPath != null) {
         deps.add(new AssetDescriptor(parameter.textureAtlasPath, TextureAtlas.class));
      }

      return deps;
   }

   public void loadAsync(AssetManager manager, String fileName, FileHandle file, SkinLoader.SkinParameter parameter) {
   }

   public Skin loadSync(AssetManager manager, String fileName, FileHandle file, SkinLoader.SkinParameter parameter) {
      String textureAtlasPath = file.pathWithoutExtension() + ".atlas";
      ObjectMap resources = null;
      if (parameter != null) {
         if (parameter.textureAtlasPath != null) {
            textureAtlasPath = parameter.textureAtlasPath;
         }

         if (parameter.resources != null) {
            resources = parameter.resources;
         }
      }

      TextureAtlas atlas = (TextureAtlas)manager.get(textureAtlasPath, TextureAtlas.class);
      Skin skin = this.newSkin(atlas);
      if (resources != null) {
         for (ObjectMap.Entry entry : resources.entries()) {
            skin.add((String)entry.key, entry.value);
         }
      }

      skin.load(file);
      return skin;
   }

   protected Skin newSkin(TextureAtlas atlas) {
      return new Skin(atlas);
   }

   public static class SkinParameter extends AssetLoaderParameters {
      public final String textureAtlasPath;
      public final ObjectMap resources;

      public SkinParameter() {
         this(null, null);
      }

      public SkinParameter(ObjectMap resources) {
         this(null, resources);
      }

      public SkinParameter(String textureAtlasPath) {
         this(textureAtlasPath, null);
      }

      public SkinParameter(String textureAtlasPath, ObjectMap resources) {
         this.textureAtlasPath = textureAtlasPath;
         this.resources = resources;
      }
   }
}
