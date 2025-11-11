package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;

public abstract class AsynchronousAssetLoader extends AssetLoader {
   public AsynchronousAssetLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   public abstract void loadAsync(AssetManager var1, String var2, FileHandle var3, AssetLoaderParameters var4);

   public void unloadAsync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters parameter) {
   }

   public abstract Object loadSync(AssetManager var1, String var2, FileHandle var3, AssetLoaderParameters var4);
}
