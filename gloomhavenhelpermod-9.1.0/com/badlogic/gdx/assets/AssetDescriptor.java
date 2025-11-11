package com.badlogic.gdx.assets;

import com.badlogic.gdx.files.FileHandle;

public class AssetDescriptor {
   public final String fileName;
   public final Class type;
   public final AssetLoaderParameters params;
   public FileHandle file;

   public AssetDescriptor(String fileName, Class assetType) {
      this(fileName, assetType, null);
   }

   public AssetDescriptor(FileHandle file, Class assetType) {
      this(file, assetType, null);
   }

   public AssetDescriptor(String fileName, Class assetType, AssetLoaderParameters params) {
      this.fileName = fileName.replace('\\', '/');
      this.type = assetType;
      this.params = params;
   }

   public AssetDescriptor(FileHandle file, Class assetType, AssetLoaderParameters params) {
      this.fileName = file.path().replace('\\', '/');
      this.file = file;
      this.type = assetType;
      this.params = params;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.fileName);
      sb.append(", ");
      sb.append(this.type.getName());
      return sb.toString();
   }
}
