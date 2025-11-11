package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class MusicLoader extends AsynchronousAssetLoader {
   private Music music;

   public MusicLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   protected Music getLoadedMusic() {
      return this.music;
   }

   public void loadAsync(AssetManager manager, String fileName, FileHandle file, MusicLoader.MusicParameter parameter) {
      this.music = Gdx.audio.newMusic(file);
   }

   public Music loadSync(AssetManager manager, String fileName, FileHandle file, MusicLoader.MusicParameter parameter) {
      Music music = this.music;
      this.music = null;
      return music;
   }

   public Array getDependencies(String fileName, FileHandle file, MusicLoader.MusicParameter parameter) {
      return null;
   }

   public static class MusicParameter extends AssetLoaderParameters {
   }
}
