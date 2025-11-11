package com.badlogic.gdx.assets;

import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.CubemapLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.PixmapLoader;
import com.badlogic.gdx.assets.loaders.ShaderProgramLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonRegionLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.UBJsonReader;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.ThreadUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;

public class AssetManager implements Disposable {
   final ObjectMap assets = new ObjectMap();
   final ObjectMap assetTypes = new ObjectMap();
   final ObjectMap assetDependencies = new ObjectMap();
   final ObjectSet injected = new ObjectSet();
   final ObjectMap loaders = new ObjectMap();
   final Array loadQueue = new Array();
   final AsyncExecutor executor;
   final Array tasks = new Array();
   AssetErrorListener listener;
   int loaded;
   int toLoad;
   int peakTasks;
   final FileHandleResolver resolver;
   Logger log = new Logger("AssetManager", 0);

   public AssetManager() {
      this(new InternalFileHandleResolver());
   }

   public AssetManager(FileHandleResolver resolver) {
      this(resolver, true);
   }

   public AssetManager(FileHandleResolver resolver, boolean defaultLoaders) {
      this.resolver = resolver;
      if (defaultLoaders) {
         this.setLoader(BitmapFont.class, new BitmapFontLoader(resolver));
         this.setLoader(Music.class, new MusicLoader(resolver));
         this.setLoader(Pixmap.class, new PixmapLoader(resolver));
         this.setLoader(Sound.class, new SoundLoader(resolver));
         this.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
         this.setLoader(Texture.class, new TextureLoader(resolver));
         this.setLoader(Skin.class, new SkinLoader(resolver));
         this.setLoader(ParticleEffect.class, new ParticleEffectLoader(resolver));
         this.setLoader(
            com.badlogic.gdx.graphics.g3d.particles.ParticleEffect.class, new com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader(resolver)
         );
         this.setLoader(PolygonRegion.class, new PolygonRegionLoader(resolver));
         this.setLoader(I18NBundle.class, new I18NBundleLoader(resolver));
         this.setLoader(Model.class, ".g3dj", new G3dModelLoader(new JsonReader(), resolver));
         this.setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(), resolver));
         this.setLoader(Model.class, ".obj", new ObjLoader(resolver));
         this.setLoader(ShaderProgram.class, new ShaderProgramLoader(resolver));
         this.setLoader(Cubemap.class, new CubemapLoader(resolver));
      }

      this.executor = new AsyncExecutor(1, "AssetManager");
   }

   public FileHandleResolver getFileHandleResolver() {
      return this.resolver;
   }

   public synchronized Object get(String fileName) {
      return this.get(fileName, true);
   }

   public synchronized Object get(String fileName, Class type) {
      return this.get(fileName, type, true);
   }

   @Null
   public synchronized Object get(String fileName, boolean required) {
      Class type = (Class<T>)this.assetTypes.get(fileName);
      if (type != null) {
         ObjectMap assetsByType = (ObjectMap<String, AssetManager.RefCountedContainer>)this.assets.get(type);
         if (assetsByType != null) {
            AssetManager.RefCountedContainer assetContainer = (AssetManager.RefCountedContainer)assetsByType.get(fileName);
            if (assetContainer != null) {
               return assetContainer.object;
            }
         }
      }

      if (required) {
         throw new GdxRuntimeException("Asset not loaded: " + fileName);
      } else {
         return null;
      }
   }

   @Null
   public synchronized Object get(String fileName, Class type, boolean required) {
      ObjectMap assetsByType = (ObjectMap<String, AssetManager.RefCountedContainer>)this.assets.get(type);
      if (assetsByType != null) {
         AssetManager.RefCountedContainer assetContainer = (AssetManager.RefCountedContainer)assetsByType.get(fileName);
         if (assetContainer != null) {
            return assetContainer.object;
         }
      }

      if (required) {
         throw new GdxRuntimeException("Asset not loaded: " + fileName);
      } else {
         return null;
      }
   }

   public synchronized Object get(AssetDescriptor assetDescriptor) {
      return this.get(assetDescriptor.fileName, assetDescriptor.type, true);
   }

   public synchronized Array getAll(Class type, Array out) {
      ObjectMap assetsByType = (ObjectMap<String, AssetManager.RefCountedContainer>)this.assets.get(type);
      if (assetsByType != null) {
         for (AssetManager.RefCountedContainer assetRef : assetsByType.values()) {
            out.add(assetRef.object);
         }
      }

      return out;
   }

   public synchronized boolean contains(String fileName) {
      if (this.tasks.size > 0 && ((AssetLoadingTask)this.tasks.first()).assetDesc.fileName.equals(fileName)) {
         return true;
      } else {
         for (int i = 0; i < this.loadQueue.size; i++) {
            if (((AssetDescriptor)this.loadQueue.get(i)).fileName.equals(fileName)) {
               return true;
            }
         }

         return this.isLoaded(fileName);
      }
   }

   public synchronized boolean contains(String fileName, Class type) {
      if (this.tasks.size > 0) {
         AssetDescriptor assetDesc = ((AssetLoadingTask)this.tasks.first()).assetDesc;
         if (assetDesc.type == type && assetDesc.fileName.equals(fileName)) {
            return true;
         }
      }

      for (int i = 0; i < this.loadQueue.size; i++) {
         AssetDescriptor assetDesc = (AssetDescriptor)this.loadQueue.get(i);
         if (assetDesc.type == type && assetDesc.fileName.equals(fileName)) {
            return true;
         }
      }

      return this.isLoaded(fileName, type);
   }

   public synchronized void unload(String fileName) {
      fileName = fileName.replace('\\', '/');
      if (this.tasks.size > 0) {
         AssetLoadingTask currentTask = (AssetLoadingTask)this.tasks.first();
         if (currentTask.assetDesc.fileName.equals(fileName)) {
            this.log.info("Unload (from tasks): " + fileName);
            currentTask.cancel = true;
            currentTask.unload();
            return;
         }
      }

      Class type = (Class)this.assetTypes.get(fileName);
      int foundIndex = -1;

      for (int i = 0; i < this.loadQueue.size; i++) {
         if (((AssetDescriptor)this.loadQueue.get(i)).fileName.equals(fileName)) {
            foundIndex = i;
            break;
         }
      }

      if (foundIndex != -1) {
         this.toLoad--;
         AssetDescriptor desc = (AssetDescriptor)this.loadQueue.removeIndex(foundIndex);
         this.log.info("Unload (from queue): " + fileName);
         if (type != null && desc.params != null && desc.params.loadedCallback != null) {
            desc.params.loadedCallback.finishedLoading(this, desc.fileName, desc.type);
         }
      } else if (type == null) {
         throw new GdxRuntimeException("Asset not loaded: " + fileName);
      } else {
         AssetManager.RefCountedContainer assetRef = (AssetManager.RefCountedContainer)((ObjectMap)this.assets.get(type)).get(fileName);
         assetRef.refCount--;
         if (assetRef.refCount <= 0) {
            this.log.info("Unload (dispose): " + fileName);
            if (assetRef.object instanceof Disposable) {
               ((Disposable)assetRef.object).dispose();
            }

            this.assetTypes.remove(fileName);
            ((ObjectMap)this.assets.get(type)).remove(fileName);
         } else {
            this.log.info("Unload (decrement): " + fileName);
         }

         Array dependencies = (Array<String>)this.assetDependencies.get(fileName);
         if (dependencies != null) {
            for (String dependency : dependencies) {
               if (this.isLoaded(dependency)) {
                  this.unload(dependency);
               }
            }
         }

         if (assetRef.refCount <= 0) {
            this.assetDependencies.remove(fileName);
         }
      }
   }

   public synchronized boolean containsAsset(Object asset) {
      ObjectMap assetsByType = (ObjectMap<String, AssetManager.RefCountedContainer>)this.assets.get(asset.getClass());
      if (assetsByType == null) {
         return false;
      } else {
         for (AssetManager.RefCountedContainer assetRef : assetsByType.values()) {
            if (assetRef.object == asset || asset.equals(assetRef.object)) {
               return true;
            }
         }

         return false;
      }
   }

   public synchronized String getAssetFileName(Object asset) {
      for (Class assetType : this.assets.keys()) {
         for (ObjectMap.Entry entry : (ObjectMap)this.assets.get(assetType)) {
            Object object = ((AssetManager.RefCountedContainer)entry.value).object;
            if (object == asset || asset.equals(object)) {
               return (String)entry.key;
            }
         }
      }

      return null;
   }

   public synchronized boolean isLoaded(AssetDescriptor assetDesc) {
      return this.isLoaded(assetDesc.fileName);
   }

   public synchronized boolean isLoaded(String fileName) {
      return fileName == null ? false : this.assetTypes.containsKey(fileName);
   }

   public synchronized boolean isLoaded(String fileName, Class type) {
      ObjectMap assetsByType = (ObjectMap<String, AssetManager.RefCountedContainer>)this.assets.get(type);
      return assetsByType == null ? false : assetsByType.get(fileName) != null;
   }

   public AssetLoader getLoader(Class type) {
      return this.getLoader(type, null);
   }

   public AssetLoader getLoader(Class type, String fileName) {
      ObjectMap loaders = (ObjectMap<String, AssetLoader>)this.loaders.get(type);
      if (loaders != null && loaders.size >= 1) {
         if (fileName == null) {
            return (AssetLoader)loaders.get("");
         } else {
            AssetLoader result = null;
            int length = -1;

            for (ObjectMap.Entry entry : loaders.entries()) {
               if (((String)entry.key).length() > length && fileName.endsWith((String)entry.key)) {
                  result = (AssetLoader)entry.value;
                  length = ((String)entry.key).length();
               }
            }

            return result;
         }
      } else {
         return null;
      }
   }

   public synchronized void load(String fileName, Class type) {
      this.load(fileName, type, null);
   }

   public synchronized void load(String fileName, Class type, AssetLoaderParameters parameter) {
      AssetLoader loader = this.getLoader(type, fileName);
      if (loader == null) {
         throw new GdxRuntimeException("No loader for type: " + ClassReflection.getSimpleName(type));
      } else {
         if (this.loadQueue.size == 0) {
            this.loaded = 0;
            this.toLoad = 0;
            this.peakTasks = 0;
         }

         for (int i = 0; i < this.loadQueue.size; i++) {
            AssetDescriptor desc = (AssetDescriptor)this.loadQueue.get(i);
            if (desc.fileName.equals(fileName) && !desc.type.equals(type)) {
               throw new GdxRuntimeException(
                  "Asset with name '"
                     + fileName
                     + "' already in preload queue, but has different type (expected: "
                     + ClassReflection.getSimpleName(type)
                     + ", found: "
                     + ClassReflection.getSimpleName(desc.type)
                     + ")"
               );
            }
         }

         for (int ix = 0; ix < this.tasks.size; ix++) {
            AssetDescriptor desc = ((AssetLoadingTask)this.tasks.get(ix)).assetDesc;
            if (desc.fileName.equals(fileName) && !desc.type.equals(type)) {
               throw new GdxRuntimeException(
                  "Asset with name '"
                     + fileName
                     + "' already in task list, but has different type (expected: "
                     + ClassReflection.getSimpleName(type)
                     + ", found: "
                     + ClassReflection.getSimpleName(desc.type)
                     + ")"
               );
            }
         }

         Class otherType = (Class)this.assetTypes.get(fileName);
         if (otherType != null && !otherType.equals(type)) {
            throw new GdxRuntimeException(
               "Asset with name '"
                  + fileName
                  + "' already loaded, but has different type (expected: "
                  + ClassReflection.getSimpleName(type)
                  + ", found: "
                  + ClassReflection.getSimpleName(otherType)
                  + ")"
            );
         } else {
            this.toLoad++;
            AssetDescriptor assetDesc = new AssetDescriptor(fileName, type, parameter);
            this.loadQueue.add(assetDesc);
            this.log.debug("Queued: " + assetDesc);
         }
      }
   }

   public synchronized void load(AssetDescriptor desc) {
      this.load(desc.fileName, desc.type, desc.params);
   }

   public synchronized boolean update() {
      try {
         if (this.tasks.size == 0) {
            while (this.loadQueue.size != 0 && this.tasks.size == 0) {
               this.nextTask();
            }

            if (this.tasks.size == 0) {
               return true;
            }
         }

         return this.updateTask() && this.loadQueue.size == 0 && this.tasks.size == 0;
      } catch (Throwable var2) {
         this.handleTaskError(var2);
         return this.loadQueue.size == 0;
      }
   }

   public boolean update(int millis) {
      long endTime = TimeUtils.millis() + millis;

      while (true) {
         boolean done = this.update();
         if (done || TimeUtils.millis() > endTime) {
            return done;
         }

         ThreadUtils.yield();
      }
   }

   public synchronized boolean isFinished() {
      return this.loadQueue.size == 0 && this.tasks.size == 0;
   }

   public void finishLoading() {
      this.log.debug("Waiting for loading to complete...");

      while (!this.update()) {
         ThreadUtils.yield();
      }

      this.log.debug("Loading complete.");
   }

   public Object finishLoadingAsset(AssetDescriptor assetDesc) {
      return this.finishLoadingAsset(assetDesc.fileName);
   }

   public Object finishLoadingAsset(String fileName) {
      this.log.debug("Waiting for asset to be loaded: " + fileName);

      while (true) {
         synchronized (this) {
            Class type = (Class<T>)this.assetTypes.get(fileName);
            if (type != null) {
               ObjectMap assetsByType = (ObjectMap<String, AssetManager.RefCountedContainer>)this.assets.get(type);
               if (assetsByType != null) {
                  AssetManager.RefCountedContainer assetContainer = (AssetManager.RefCountedContainer)assetsByType.get(fileName);
                  if (assetContainer != null) {
                     this.log.debug("Asset loaded: " + fileName);
                     return assetContainer.object;
                  }
               }
            }

            this.update();
         }

         ThreadUtils.yield();
      }
   }

   synchronized void injectDependencies(String parentAssetFilename, Array dependendAssetDescs) {
      ObjectSet injected = this.injected;

      for (AssetDescriptor desc : dependendAssetDescs) {
         if (!injected.contains(desc.fileName)) {
            injected.add(desc.fileName);
            this.injectDependency(parentAssetFilename, desc);
         }
      }

      injected.clear(32);
   }

   private synchronized void injectDependency(String parentAssetFilename, AssetDescriptor dependendAssetDesc) {
      Array dependencies = (Array<String>)this.assetDependencies.get(parentAssetFilename);
      if (dependencies == null) {
         dependencies = new Array();
         this.assetDependencies.put(parentAssetFilename, dependencies);
      }

      dependencies.add(dependendAssetDesc.fileName);
      if (this.isLoaded(dependendAssetDesc.fileName)) {
         this.log.debug("Dependency already loaded: " + dependendAssetDesc);
         Class type = (Class)this.assetTypes.get(dependendAssetDesc.fileName);
         AssetManager.RefCountedContainer assetRef = (AssetManager.RefCountedContainer)((ObjectMap)this.assets.get(type)).get(dependendAssetDesc.fileName);
         assetRef.refCount++;
         this.incrementRefCountedDependencies(dependendAssetDesc.fileName);
      } else {
         this.log.info("Loading dependency: " + dependendAssetDesc);
         this.addTask(dependendAssetDesc);
      }
   }

   private void nextTask() {
      AssetDescriptor assetDesc = (AssetDescriptor)this.loadQueue.removeIndex(0);
      if (this.isLoaded(assetDesc.fileName)) {
         this.log.debug("Already loaded: " + assetDesc);
         Class type = (Class)this.assetTypes.get(assetDesc.fileName);
         AssetManager.RefCountedContainer assetRef = (AssetManager.RefCountedContainer)((ObjectMap)this.assets.get(type)).get(assetDesc.fileName);
         assetRef.refCount++;
         this.incrementRefCountedDependencies(assetDesc.fileName);
         if (assetDesc.params != null && assetDesc.params.loadedCallback != null) {
            assetDesc.params.loadedCallback.finishedLoading(this, assetDesc.fileName, assetDesc.type);
         }

         this.loaded++;
      } else {
         this.log.info("Loading: " + assetDesc);
         this.addTask(assetDesc);
      }
   }

   private void addTask(AssetDescriptor assetDesc) {
      AssetLoader loader = this.getLoader(assetDesc.type, assetDesc.fileName);
      if (loader == null) {
         throw new GdxRuntimeException("No loader for type: " + ClassReflection.getSimpleName(assetDesc.type));
      } else {
         this.tasks.add(new AssetLoadingTask(this, assetDesc, loader, this.executor));
         this.peakTasks++;
      }
   }

   protected void addAsset(String fileName, Class type, Object asset) {
      this.assetTypes.put(fileName, type);
      ObjectMap typeToAssets = (ObjectMap<String, AssetManager.RefCountedContainer>)this.assets.get(type);
      if (typeToAssets == null) {
         typeToAssets = new ObjectMap();
         this.assets.put(type, typeToAssets);
      }

      AssetManager.RefCountedContainer assetRef = new AssetManager.RefCountedContainer();
      assetRef.object = asset;
      typeToAssets.put(fileName, assetRef);
   }

   private boolean updateTask() {
      AssetLoadingTask task = (AssetLoadingTask)this.tasks.peek();
      boolean complete = true;

      try {
         complete = task.cancel || task.update();
      } catch (RuntimeException var5) {
         task.cancel = true;
         this.taskFailed(task.assetDesc, var5);
      }

      if (complete) {
         if (this.tasks.size == 1) {
            this.loaded++;
            this.peakTasks = 0;
         }

         this.tasks.pop();
         if (task.cancel) {
            return true;
         } else {
            this.addAsset(task.assetDesc.fileName, task.assetDesc.type, task.asset);
            if (task.assetDesc.params != null && task.assetDesc.params.loadedCallback != null) {
               task.assetDesc.params.loadedCallback.finishedLoading(this, task.assetDesc.fileName, task.assetDesc.type);
            }

            long endTime = TimeUtils.nanoTime();
            this.log.debug("Loaded: " + (float)(endTime - task.startTime) / 1000000.0F + "ms " + task.assetDesc);
            return true;
         }
      } else {
         return false;
      }
   }

   protected void taskFailed(AssetDescriptor assetDesc, RuntimeException ex) {
      throw ex;
   }

   private void incrementRefCountedDependencies(String parent) {
      Array dependencies = (Array<String>)this.assetDependencies.get(parent);
      if (dependencies != null) {
         for (String dependency : dependencies) {
            Class type = (Class)this.assetTypes.get(dependency);
            AssetManager.RefCountedContainer assetRef = (AssetManager.RefCountedContainer)((ObjectMap)this.assets.get(type)).get(dependency);
            assetRef.refCount++;
            this.incrementRefCountedDependencies(dependency);
         }
      }
   }

   private void handleTaskError(Throwable t) {
      this.log.error("Error loading asset.", t);
      if (this.tasks.isEmpty()) {
         throw new GdxRuntimeException(t);
      } else {
         AssetLoadingTask task = (AssetLoadingTask)this.tasks.pop();
         AssetDescriptor assetDesc = task.assetDesc;
         if (task.dependenciesLoaded && task.dependencies != null) {
            for (AssetDescriptor desc : task.dependencies) {
               this.unload(desc.fileName);
            }
         }

         this.tasks.clear();
         if (this.listener != null) {
            this.listener.error(assetDesc, t);
         } else {
            throw new GdxRuntimeException(t);
         }
      }
   }

   public synchronized void setLoader(Class type, AssetLoader loader) {
      this.setLoader(type, null, loader);
   }

   public synchronized void setLoader(Class type, String suffix, AssetLoader loader) {
      if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else if (loader == null) {
         throw new IllegalArgumentException("loader cannot be null.");
      } else {
         this.log.debug("Loader set: " + ClassReflection.getSimpleName(type) + " -> " + ClassReflection.getSimpleName(loader.getClass()));
         ObjectMap loaders = (ObjectMap<String, AssetLoader>)this.loaders.get(type);
         if (loaders == null) {
            this.loaders.put(type, loaders = new ObjectMap());
         }

         loaders.put(suffix == null ? "" : suffix, loader);
      }
   }

   public synchronized int getLoadedAssets() {
      return this.assetTypes.size;
   }

   public synchronized int getQueuedAssets() {
      return this.loadQueue.size + this.tasks.size;
   }

   public synchronized float getProgress() {
      if (this.toLoad == 0) {
         return 1.0F;
      } else {
         float fractionalLoaded = this.loaded;
         if (this.peakTasks > 0) {
            fractionalLoaded += (float)(this.peakTasks - this.tasks.size) / this.peakTasks;
         }

         return Math.min(1.0F, fractionalLoaded / this.toLoad);
      }
   }

   public synchronized void setErrorListener(AssetErrorListener listener) {
      this.listener = listener;
   }

   @Override
   public synchronized void dispose() {
      this.log.debug("Disposing.");
      this.clear();
      this.executor.dispose();
   }

   public synchronized void clear() {
      this.loadQueue.clear();

      while (!this.update()) {
      }

      ObjectIntMap dependencyCount = new ObjectIntMap();

      while (this.assetTypes.size > 0) {
         dependencyCount.clear();
         Array assets = this.assetTypes.keys().toArray();

         for (String asset : assets) {
            Array dependencies = (Array<String>)this.assetDependencies.get(asset);
            if (dependencies != null) {
               for (String dependency : dependencies) {
                  dependencyCount.getAndIncrement(dependency, 0, 1);
               }
            }
         }

         for (String assetx : assets) {
            if (dependencyCount.get(assetx, 0) == 0) {
               this.unload(assetx);
            }
         }
      }

      this.assets.clear();
      this.assetTypes.clear();
      this.assetDependencies.clear();
      this.loaded = 0;
      this.toLoad = 0;
      this.peakTasks = 0;
      this.loadQueue.clear();
      this.tasks.clear();
   }

   public Logger getLogger() {
      return this.log;
   }

   public void setLogger(Logger logger) {
      this.log = logger;
   }

   public synchronized int getReferenceCount(String fileName) {
      Class type = (Class)this.assetTypes.get(fileName);
      if (type == null) {
         throw new GdxRuntimeException("Asset not loaded: " + fileName);
      } else {
         return ((AssetManager.RefCountedContainer)((ObjectMap)this.assets.get(type)).get(fileName)).refCount;
      }
   }

   public synchronized void setReferenceCount(String fileName, int refCount) {
      Class type = (Class)this.assetTypes.get(fileName);
      if (type == null) {
         throw new GdxRuntimeException("Asset not loaded: " + fileName);
      } else {
         ((AssetManager.RefCountedContainer)((ObjectMap)this.assets.get(type)).get(fileName)).refCount = refCount;
      }
   }

   public synchronized String getDiagnostics() {
      StringBuilder buffer = new StringBuilder(256);

      for (ObjectMap.Entry entry : this.assetTypes) {
         String fileName = (String)entry.key;
         Class type = (Class)entry.value;
         if (buffer.length() > 0) {
            buffer.append('\n');
         }

         buffer.append(fileName);
         buffer.append(", ");
         buffer.append(ClassReflection.getSimpleName(type));
         buffer.append(", refs: ");
         buffer.append(((AssetManager.RefCountedContainer)((ObjectMap)this.assets.get(type)).get(fileName)).refCount);
         Array dependencies = (Array<String>)this.assetDependencies.get(fileName);
         if (dependencies != null) {
            buffer.append(", deps: [");

            for (String dep : dependencies) {
               buffer.append(dep);
               buffer.append(',');
            }

            buffer.append(']');
         }
      }

      return buffer.toString();
   }

   public synchronized Array getAssetNames() {
      return this.assetTypes.keys().toArray();
   }

   public synchronized Array getDependencies(String fileName) {
      return (Array)this.assetDependencies.get(fileName);
   }

   public synchronized Class getAssetType(String fileName) {
      return (Class)this.assetTypes.get(fileName);
   }

   static class RefCountedContainer {
      Object object;
      int refCount = 1;
   }
}
