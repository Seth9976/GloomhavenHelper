package com.hm.spine.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.SkeletonBinary;
import com.hm.spine.SkeletonData;
import com.hm.spine.SkeletonJson;
import com.hm.spine.attachments.AtlasAttachmentLoader;
import com.hm.spine.attachments.AttachmentLoader;

public class SkeletonDataLoader extends AsynchronousAssetLoader {
   private SkeletonData skeletonData;

   public SkeletonDataLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   public void loadAsync(AssetManager manager, String fileName, FileHandle file, @Null SkeletonDataLoader.SkeletonDataParameter parameter) {
      AtlasAttachmentLoader atlasAttachmentLoader = null;
      float scale = 1.0F;
      AttachmentLoader attachmentLoader = null;
      if (parameter != null) {
         scale = parameter.scale;
         if (parameter.attachmentLoader != null) {
            attachmentLoader = parameter.attachmentLoader;
         } else if (parameter.atlasName != null) {
            atlasAttachmentLoader = new AtlasAttachmentLoader((TextureAtlas)manager.get(parameter.atlasName, TextureAtlas.class));
         }
      }

      if (atlasAttachmentLoader == null) {
         atlasAttachmentLoader = new AtlasAttachmentLoader((TextureAtlas)manager.get(file.pathWithoutExtension() + ".atlas", TextureAtlas.class));
      }

      if (file.extension().equalsIgnoreCase("skel")) {
         SkeletonBinary skeletonBinary = new SkeletonBinary(atlasAttachmentLoader);
         skeletonBinary.setScale(scale);
         this.skeletonData = skeletonBinary.readSkeletonData(file);
      } else {
         SkeletonJson skeletonJson = new SkeletonJson(atlasAttachmentLoader);
         skeletonJson.setScale(scale);
         this.skeletonData = skeletonJson.readSkeletonData(file);
      }
   }

   public SkeletonData loadSync(AssetManager manager, String fileName, FileHandle file, @Null SkeletonDataLoader.SkeletonDataParameter parameter) {
      SkeletonData skeletonData = this.skeletonData;
      this.skeletonData = null;
      return skeletonData;
   }

   public Array getDependencies(String fileName, FileHandle file, @Null SkeletonDataLoader.SkeletonDataParameter parameter) {
      if (parameter == null) {
         return null;
      } else if (parameter.attachmentLoader != null) {
         return null;
      } else {
         Array dependencies = new Array();
         dependencies.add(new AssetDescriptor(parameter.atlasName, TextureAtlas.class));
         return dependencies;
      }
   }

   public static class SkeletonDataParameter extends AssetLoaderParameters {
      public String atlasName;
      public AttachmentLoader attachmentLoader;
      public float scale = 1.0F;

      public SkeletonDataParameter() {
      }

      public SkeletonDataParameter(String atlasName) {
         this.atlasName = atlasName;
      }

      public SkeletonDataParameter(String atlasName, float scale) {
         this.atlasName = atlasName;
         this.scale = scale;
      }

      public SkeletonDataParameter(AttachmentLoader attachmentLoader) {
         this.attachmentLoader = attachmentLoader;
      }

      public SkeletonDataParameter(AttachmentLoader attachmentLoader, float scale) {
         this.attachmentLoader = attachmentLoader;
         this.scale = scale;
      }
   }
}
