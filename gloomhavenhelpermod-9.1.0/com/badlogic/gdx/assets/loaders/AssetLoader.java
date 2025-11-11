package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public abstract class AssetLoader {
   private FileHandleResolver resolver;

   public AssetLoader(FileHandleResolver resolver) {
      this.resolver = resolver;
   }

   public FileHandle resolve(String fileName) {
      return this.resolver.resolve(fileName);
   }

   public abstract Array getDependencies(String var1, FileHandle var2, AssetLoaderParameters var3);
}
