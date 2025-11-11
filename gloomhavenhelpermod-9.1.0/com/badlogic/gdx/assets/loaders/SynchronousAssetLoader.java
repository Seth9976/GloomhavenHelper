package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;

public abstract class SynchronousAssetLoader extends AssetLoader {
   public SynchronousAssetLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   public abstract Object load(AssetManager var1, String var2, FileHandle var3, AssetLoaderParameters var4);
}
