package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.io.IOException;

public class ParticleEffectLoader extends AsynchronousAssetLoader {
   protected Array items = new Array();

   public ParticleEffectLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   public void loadAsync(AssetManager manager, String fileName, FileHandle file, ParticleEffectLoader.ParticleEffectLoadParameter parameter) {
   }

   public Array getDependencies(String fileName, FileHandle file, ParticleEffectLoader.ParticleEffectLoadParameter parameter) {
      Json json = new Json();
      ResourceData data = (ResourceData<ParticleEffect>)json.fromJson(ResourceData.class, file);
      Array assets = null;
      synchronized (this.items) {
         ObjectMap.Entry entry = new ObjectMap.Entry();
         entry.key = fileName;
         entry.value = data;
         this.items.add(entry);
         assets = data.getAssets();
      }

      Array descriptors = new Array();

      for (ResourceData.AssetData assetData : assets) {
         if (!this.resolve(assetData.filename).exists()) {
            assetData.filename = file.parent().child(Gdx.files.internal(assetData.filename).name()).path();
         }

         if (assetData.type == ParticleEffect.class) {
            descriptors.add(new AssetDescriptor(assetData.filename, assetData.type, parameter));
         } else {
            descriptors.add(new AssetDescriptor(assetData.filename, assetData.type));
         }
      }

      return descriptors;
   }

   public void save(ParticleEffect effect, ParticleEffectLoader.ParticleEffectSaveParameter parameter) throws IOException {
      ResourceData data = new ResourceData(effect);
      effect.save(parameter.manager, data);
      if (parameter.batches != null) {
         for (ParticleBatch batch : parameter.batches) {
            boolean save = false;

            for (ParticleController controller : effect.getControllers()) {
               if (controller.renderer.isCompatible(batch)) {
                  save = true;
                  break;
               }
            }

            if (save) {
               batch.save(parameter.manager, data);
            }
         }
      }

      Json json = new Json();
      json.toJson(data, parameter.file);
   }

   public ParticleEffect loadSync(AssetManager manager, String fileName, FileHandle file, ParticleEffectLoader.ParticleEffectLoadParameter parameter) {
      ResourceData effectData = null;
      synchronized (this.items) {
         for (int i = 0; i < this.items.size; i++) {
            ObjectMap.Entry entry = (ObjectMap.Entry<String, ResourceData<ParticleEffect>>)this.items.get(i);
            if (((String)entry.key).equals(fileName)) {
               effectData = (ResourceData<ParticleEffect>)entry.value;
               this.items.removeIndex(i);
               break;
            }
         }
      }

      ((ParticleEffect)effectData.resource).load(manager, effectData);
      if (parameter != null) {
         if (parameter.batches != null) {
            for (ParticleBatch batch : parameter.batches) {
               batch.load(manager, effectData);
            }
         }

         ((ParticleEffect)effectData.resource).setBatch(parameter.batches);
      }

      return (ParticleEffect)effectData.resource;
   }

   private Object find(Array array, Class type) {
      for (Object object : array) {
         if (ClassReflection.isAssignableFrom(type, object.getClass())) {
            return object;
         }
      }

      return null;
   }

   public static class ParticleEffectLoadParameter extends AssetLoaderParameters {
      Array batches;

      public ParticleEffectLoadParameter(Array batches) {
         this.batches = batches;
      }
   }

   public static class ParticleEffectSaveParameter extends AssetLoaderParameters {
      Array batches;
      FileHandle file;
      AssetManager manager;

      public ParticleEffectSaveParameter(FileHandle file, AssetManager manager, Array batches) {
         this.batches = batches;
         this.file = file;
         this.manager = manager;
      }
   }
}
