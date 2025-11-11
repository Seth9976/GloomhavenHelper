package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class ResourceData implements Json.Serializable {
   private ObjectMap uniqueData = new ObjectMap();
   private Array data = new Array(true, 3, ResourceData.SaveData.class);
   Array sharedAssets = new Array();
   private int currentLoadIndex = 0;
   public Object resource;

   public ResourceData() {
   }

   public ResourceData(Object resource) {
      this();
      this.resource = resource;
   }

   int getAssetData(String filename, Class type) {
      int i = 0;

      for (ResourceData.AssetData data : this.sharedAssets) {
         if (data.filename.equals(filename) && data.type.equals(type)) {
            return i;
         }

         i++;
      }

      return -1;
   }

   public Array getAssetDescriptors() {
      Array descriptors = new Array();

      for (ResourceData.AssetData data : this.sharedAssets) {
         descriptors.add(new AssetDescriptor(data.filename, data.type));
      }

      return descriptors;
   }

   public Array getAssets() {
      return this.sharedAssets;
   }

   public ResourceData.SaveData createSaveData() {
      ResourceData.SaveData saveData = new ResourceData.SaveData(this);
      this.data.add(saveData);
      return saveData;
   }

   public ResourceData.SaveData createSaveData(String key) {
      ResourceData.SaveData saveData = new ResourceData.SaveData(this);
      if (this.uniqueData.containsKey(key)) {
         throw new RuntimeException("Key already used, data must be unique, use a different key");
      } else {
         this.uniqueData.put(key, saveData);
         return saveData;
      }
   }

   public ResourceData.SaveData getSaveData() {
      return (ResourceData.SaveData)this.data.get(this.currentLoadIndex++);
   }

   public ResourceData.SaveData getSaveData(String key) {
      return (ResourceData.SaveData)this.uniqueData.get(key);
   }

   @Override
   public void write(Json json) {
      json.writeValue("unique", this.uniqueData, ObjectMap.class);
      json.writeValue("data", this.data, Array.class, ResourceData.SaveData.class);
      json.writeValue("assets", this.sharedAssets.toArray(ResourceData.AssetData.class), ResourceData.AssetData[].class);
      json.writeValue("resource", this.resource, null);
   }

   @Override
   public void read(Json json, JsonValue jsonData) {
      this.uniqueData = (ObjectMap)json.readValue("unique", ObjectMap.class, jsonData);

      for (ObjectMap.Entry entry : this.uniqueData.entries()) {
         ((ResourceData.SaveData)entry.value).resources = this;
      }

      this.data = (Array)json.readValue("data", Array.class, ResourceData.SaveData.class, jsonData);

      for (ResourceData.SaveData saveData : this.data) {
         saveData.resources = this;
      }

      this.sharedAssets.addAll((Array)json.readValue("assets", Array.class, ResourceData.AssetData.class, jsonData));
      this.resource = json.readValue("resource", null, jsonData);
   }

   public static class AssetData implements Json.Serializable {
      public String filename;
      public Class type;

      public AssetData() {
      }

      public AssetData(String filename, Class type) {
         this.filename = filename;
         this.type = type;
      }

      @Override
      public void write(Json json) {
         json.writeValue("filename", this.filename);
         json.writeValue("type", this.type.getName());
      }

      @Override
      public void read(Json json, JsonValue jsonData) {
         this.filename = (String)json.readValue("filename", String.class, jsonData);
         String className = (String)json.readValue("type", String.class, jsonData);

         try {
            this.type = ClassReflection.forName(className);
         } catch (ReflectionException var5) {
            throw new GdxRuntimeException("Class not found: " + className, var5);
         }
      }
   }

   public interface Configurable {
      void save(AssetManager var1, ResourceData var2);

      void load(AssetManager var1, ResourceData var2);
   }

   public static class SaveData implements Json.Serializable {
      ObjectMap data = new ObjectMap();
      IntArray assets = new IntArray();
      private int loadIndex = 0;
      protected ResourceData resources;

      public SaveData() {
      }

      public SaveData(ResourceData resources) {
         this.resources = resources;
      }

      public void saveAsset(String filename, Class type) {
         int i = this.resources.getAssetData(filename, type);
         if (i == -1) {
            this.resources.sharedAssets.add(new ResourceData.AssetData(filename, type));
            i = this.resources.sharedAssets.size - 1;
         }

         this.assets.add(i);
      }

      public void save(String key, Object value) {
         this.data.put(key, value);
      }

      public AssetDescriptor loadAsset() {
         if (this.loadIndex == this.assets.size) {
            return null;
         } else {
            ResourceData.AssetData data = (ResourceData.AssetData)this.resources.sharedAssets.get(this.assets.get(this.loadIndex++));
            return new AssetDescriptor(data.filename, data.type);
         }
      }

      public Object load(String key) {
         return this.data.get(key);
      }

      @Override
      public void write(Json json) {
         json.writeValue("data", this.data, ObjectMap.class);
         json.writeValue("indices", this.assets.toArray(), int[].class);
      }

      @Override
      public void read(Json json, JsonValue jsonData) {
         this.data = (ObjectMap)json.readValue("data", ObjectMap.class, jsonData);
         this.assets.addAll((int[])json.readValue("indices", int[].class, jsonData));
      }
   }
}
