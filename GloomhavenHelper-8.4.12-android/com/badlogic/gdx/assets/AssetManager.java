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
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.UBJsonReader;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.ThreadUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import jeb.synthetic.FIN;

public class AssetManager implements Disposable {
    static class RefCountedContainer {
        Object object;
        int refCount;

        RefCountedContainer() {
            this.refCount = 1;
        }
    }

    final ObjectMap assetDependencies;
    final ObjectMap assetTypes;
    final ObjectMap assets;
    final AsyncExecutor executor;
    final ObjectSet injected;
    AssetErrorListener listener;
    final Array loadQueue;
    int loaded;
    final ObjectMap loaders;
    Logger log;
    int peakTasks;
    final FileHandleResolver resolver;
    final Array tasks;
    int toLoad;

    public AssetManager() {
        this(new InternalFileHandleResolver());
    }

    public AssetManager(FileHandleResolver fileHandleResolver0) {
        this(fileHandleResolver0, true);
    }

    public AssetManager(FileHandleResolver fileHandleResolver0, boolean z) {
        this.assets = new ObjectMap();
        this.assetTypes = new ObjectMap();
        this.assetDependencies = new ObjectMap();
        this.injected = new ObjectSet();
        this.loaders = new ObjectMap();
        this.loadQueue = new Array();
        this.tasks = new Array();
        this.log = new Logger("AssetManager", 0);
        this.resolver = fileHandleResolver0;
        if(z) {
            BitmapFontLoader bitmapFontLoader0 = new BitmapFontLoader(fileHandleResolver0);
            this.setLoader(BitmapFont.class, bitmapFontLoader0);
            MusicLoader musicLoader0 = new MusicLoader(fileHandleResolver0);
            this.setLoader(Music.class, musicLoader0);
            PixmapLoader pixmapLoader0 = new PixmapLoader(fileHandleResolver0);
            this.setLoader(Pixmap.class, pixmapLoader0);
            SoundLoader soundLoader0 = new SoundLoader(fileHandleResolver0);
            this.setLoader(Sound.class, soundLoader0);
            TextureAtlasLoader textureAtlasLoader0 = new TextureAtlasLoader(fileHandleResolver0);
            this.setLoader(TextureAtlas.class, textureAtlasLoader0);
            TextureLoader textureLoader0 = new TextureLoader(fileHandleResolver0);
            this.setLoader(Texture.class, textureLoader0);
            SkinLoader skinLoader0 = new SkinLoader(fileHandleResolver0);
            this.setLoader(Skin.class, skinLoader0);
            ParticleEffectLoader particleEffectLoader0 = new ParticleEffectLoader(fileHandleResolver0);
            this.setLoader(ParticleEffect.class, particleEffectLoader0);
            com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader particleEffectLoader1 = new com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader(fileHandleResolver0);
            this.setLoader(com.badlogic.gdx.graphics.g3d.particles.ParticleEffect.class, particleEffectLoader1);
            PolygonRegionLoader polygonRegionLoader0 = new PolygonRegionLoader(fileHandleResolver0);
            this.setLoader(PolygonRegion.class, polygonRegionLoader0);
            I18NBundleLoader i18NBundleLoader0 = new I18NBundleLoader(fileHandleResolver0);
            this.setLoader(I18NBundle.class, i18NBundleLoader0);
            G3dModelLoader g3dModelLoader0 = new G3dModelLoader(new JsonReader(), fileHandleResolver0);
            this.setLoader(Model.class, ".g3dj", g3dModelLoader0);
            G3dModelLoader g3dModelLoader1 = new G3dModelLoader(new UBJsonReader(), fileHandleResolver0);
            this.setLoader(Model.class, ".g3db", g3dModelLoader1);
            ObjLoader objLoader0 = new ObjLoader(fileHandleResolver0);
            this.setLoader(Model.class, ".obj", objLoader0);
            ShaderProgramLoader shaderProgramLoader0 = new ShaderProgramLoader(fileHandleResolver0);
            this.setLoader(ShaderProgram.class, shaderProgramLoader0);
            CubemapLoader cubemapLoader0 = new CubemapLoader(fileHandleResolver0);
            this.setLoader(Cubemap.class, cubemapLoader0);
        }
        this.executor = new AsyncExecutor(1, "AssetManager");
    }

    protected void addAsset(String s, Class class0, Object object0) {
        this.assetTypes.put(s, class0);
        ObjectMap objectMap0 = (ObjectMap)this.assets.get(class0);
        if(objectMap0 == null) {
            objectMap0 = new ObjectMap();
            this.assets.put(class0, objectMap0);
        }
        RefCountedContainer assetManager$RefCountedContainer0 = new RefCountedContainer();
        assetManager$RefCountedContainer0.object = object0;
        objectMap0.put(s, assetManager$RefCountedContainer0);
    }

    private void addTask(AssetDescriptor assetDescriptor0) {
        AssetLoader assetLoader0 = this.getLoader(assetDescriptor0.type, assetDescriptor0.fileName);
        if(assetLoader0 == null) {
            throw new GdxRuntimeException("No loader for type: " + ClassReflection.getSimpleName(assetDescriptor0.type));
        }
        AssetLoadingTask assetLoadingTask0 = new AssetLoadingTask(this, assetDescriptor0, assetLoader0, this.executor);
        this.tasks.add(assetLoadingTask0);
        ++this.peakTasks;
    }

    public void clear() {
        synchronized(this) {
            this.loadQueue.clear();
            while(!this.update()) {
            }
            ObjectIntMap objectIntMap0 = new ObjectIntMap();
            while(this.assetTypes.size > 0) {
                objectIntMap0.clear();
                Array array0 = this.assetTypes.keys().toArray();
                for(Object object0: array0) {
                    Array array1 = (Array)this.assetDependencies.get(((String)object0));
                    if(array1 != null) {
                        for(Object object1: array1) {
                            objectIntMap0.getAndIncrement(((String)object1), 0, 1);
                        }
                    }
                }
                for(Object object2: array0) {
                    String s = (String)object2;
                    if(objectIntMap0.get(s, 0) == 0) {
                        this.unload(s);
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
    }

    public boolean contains(String s) {
        synchronized(this) {
            if(this.tasks.size > 0 && ((AssetLoadingTask)this.tasks.first()).assetDesc.fileName.equals(s)) {
                return true;
            }
            for(int v1 = 0; v1 < this.loadQueue.size; ++v1) {
                if(((AssetDescriptor)this.loadQueue.get(v1)).fileName.equals(s)) {
                    return true;
                }
            }
            return this.isLoaded(s);
        }
    }

    public boolean contains(String s, Class class0) {
        synchronized(this) {
            if(this.tasks.size > 0) {
                AssetDescriptor assetDescriptor0 = ((AssetLoadingTask)this.tasks.first()).assetDesc;
                if(assetDescriptor0.type == class0 && assetDescriptor0.fileName.equals(s)) {
                    return true;
                }
            }
            for(int v1 = 0; v1 < this.loadQueue.size; ++v1) {
                AssetDescriptor assetDescriptor1 = (AssetDescriptor)this.loadQueue.get(v1);
                if(assetDescriptor1.type == class0 && assetDescriptor1.fileName.equals(s)) {
                    return true;
                }
            }
            return this.isLoaded(s, class0);
        }
    }

    public boolean containsAsset(Object object0) {
        synchronized(this) {
            Class class0 = object0.getClass();
            ObjectMap objectMap0 = (ObjectMap)this.assets.get(class0);
            if(objectMap0 == null) {
                return false;
            }
            for(Object object1: objectMap0.values()) {
                if(((RefCountedContainer)object1).object == object0 || object0.equals(((RefCountedContainer)object1).object)) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
            return false;
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        synchronized(this) {
            this.log.debug("Disposing.");
            this.clear();
            this.executor.dispose();
        }
    }

    public void finishLoading() {
        this.log.debug("Waiting for loading to complete...");
        while(!this.update()) {
            ThreadUtils.yield();
        }
        this.log.debug("Loading complete.");
    }

    public Object finishLoadingAsset(AssetDescriptor assetDescriptor0) {
        return this.finishLoadingAsset(assetDescriptor0.fileName);
    }

    public Object finishLoadingAsset(String s) {
        RefCountedContainer assetManager$RefCountedContainer0;
        int v;
        this.log.debug("Waiting for asset to be loaded: " + s);
        while(true) {
            synchronized(this) {
                v = FIN.finallyOpen$NT();
                Class class0 = (Class)this.assetTypes.get(s);
                if(class0 != null) {
                    ObjectMap objectMap0 = (ObjectMap)this.assets.get(class0);
                    if(objectMap0 != null) {
                        assetManager$RefCountedContainer0 = (RefCountedContainer)objectMap0.get(s);
                        if(assetManager$RefCountedContainer0 == null) {
                            goto label_9;
                        }
                        break;
                    }
                }
            label_9:
                this.update();
                FIN.finallyCodeBegin$NT(v);
            }
            FIN.finallyCodeEnd$NT(v);
            ThreadUtils.yield();
        }
        this.log.debug("Asset loaded: " + s);
        FIN.finallyExec$NT(v);
        return assetManager$RefCountedContainer0.object;
    }

    public Object get(AssetDescriptor assetDescriptor0) {
        synchronized(this) {
            return this.get(assetDescriptor0.fileName, assetDescriptor0.type, true);
        }
    }

    public Object get(String s) {
        synchronized(this) {
            return this.get(s, true);
        }
    }

    public Object get(String s, Class class0) {
        synchronized(this) {
            return this.get(s, class0, true);
        }
    }

    @Null
    public Object get(String s, Class class0, boolean z) {
        synchronized(this) {
            ObjectMap objectMap0 = (ObjectMap)this.assets.get(class0);
            if(objectMap0 != null) {
                RefCountedContainer assetManager$RefCountedContainer0 = (RefCountedContainer)objectMap0.get(s);
                if(assetManager$RefCountedContainer0 != null) {
                    return assetManager$RefCountedContainer0.object;
                }
            }
            if(!z) {
                return null;
            }
        }
        throw new GdxRuntimeException("Asset not loaded: " + s);
    }

    @Null
    public Object get(String s, boolean z) {
        synchronized(this) {
            Class class0 = (Class)this.assetTypes.get(s);
            if(class0 != null) {
                ObjectMap objectMap0 = (ObjectMap)this.assets.get(class0);
                if(objectMap0 != null) {
                    RefCountedContainer assetManager$RefCountedContainer0 = (RefCountedContainer)objectMap0.get(s);
                    if(assetManager$RefCountedContainer0 != null) {
                        return assetManager$RefCountedContainer0.object;
                    }
                }
            }
            if(!z) {
                return null;
            }
        }
        throw new GdxRuntimeException("Asset not loaded: " + s);
    }

    public Array getAll(Class class0, Array array0) {
        synchronized(this) {
            ObjectMap objectMap0 = (ObjectMap)this.assets.get(class0);
            if(objectMap0 != null) {
                for(Object object0: objectMap0.values()) {
                    array0.add(((RefCountedContainer)object0).object);
                }
            }
            return array0;
        }
    }

    public String getAssetFileName(Object object0) {
        synchronized(this) {
            for(Object object1: this.assets.keys()) {
                for(Object object2: ((ObjectMap)this.assets.get(((Class)object1)))) {
                    Entry objectMap$Entry0 = (Entry)object2;
                    Object object3 = ((RefCountedContainer)objectMap$Entry0.value).object;
                    if(object3 == object0 || object0.equals(object3)) {
                        return (String)objectMap$Entry0.key;
                    }
                    if(false) {
                        break;
                    }
                }
                if(false) {
                    break;
                }
            }
            return null;
        }
    }

    public Array getAssetNames() {
        synchronized(this) {
            return this.assetTypes.keys().toArray();
        }
    }

    public Class getAssetType(String s) {
        synchronized(this) {
            return (Class)this.assetTypes.get(s);
        }
    }

    public Array getDependencies(String s) {
        synchronized(this) {
            return (Array)this.assetDependencies.get(s);
        }
    }

    public String getDiagnostics() {
        StringBuilder stringBuilder0;
        synchronized(this) {
            stringBuilder0 = new StringBuilder(0x100);
            for(Object object0: this.assetTypes) {
                String s = (String)((Entry)object0).key;
                Class class0 = (Class)((Entry)object0).value;
                if(stringBuilder0.length() > 0) {
                    stringBuilder0.append('\n');
                }
                stringBuilder0.append(s);
                stringBuilder0.append(", ");
                stringBuilder0.append(ClassReflection.getSimpleName(class0));
                stringBuilder0.append(", refs: ");
                stringBuilder0.append(((RefCountedContainer)((ObjectMap)this.assets.get(class0)).get(s)).refCount);
                Array array0 = (Array)this.assetDependencies.get(s);
                if(array0 != null) {
                    stringBuilder0.append(", deps: [");
                    for(Object object1: array0) {
                        stringBuilder0.append(((String)object1));
                        stringBuilder0.append(',');
                    }
                    stringBuilder0.append(']');
                }
            }
        }
        return stringBuilder0.toString();
    }

    public FileHandleResolver getFileHandleResolver() {
        return this.resolver;
    }

    public int getLoadedAssets() {
        synchronized(this) {
        }
        return this.assetTypes.size;
    }

    public AssetLoader getLoader(Class class0) {
        return this.getLoader(class0, null);
    }

    public AssetLoader getLoader(Class class0, String s) {
        ObjectMap objectMap0 = (ObjectMap)this.loaders.get(class0);
        AssetLoader assetLoader0 = null;
        if(objectMap0 != null && objectMap0.size >= 1) {
            if(s == null) {
                return (AssetLoader)objectMap0.get("");
            }
            int v = -1;
            for(Object object0: objectMap0.entries()) {
                Entry objectMap$Entry0 = (Entry)object0;
                if(((String)objectMap$Entry0.key).length() > v && s.endsWith(((String)objectMap$Entry0.key))) {
                    assetLoader0 = (AssetLoader)objectMap$Entry0.value;
                    v = ((String)objectMap$Entry0.key).length();
                }
            }
            return assetLoader0;
        }
        return null;
    }

    public Logger getLogger() {
        return this.log;
    }

    public float getProgress() {
        synchronized(this) {
            return this.toLoad == 0 ? 1.0f : Math.min(1.0f, (this.peakTasks <= 0 ? ((float)this.loaded) : ((float)this.loaded) + ((float)(this.peakTasks - this.tasks.size)) / ((float)this.peakTasks)) / ((float)this.toLoad));
        }
    }

    public int getQueuedAssets() {
        synchronized(this) {
        }
        return this.loadQueue.size + this.tasks.size;
    }

    public int getReferenceCount(String s) {
        synchronized(this) {
            Class class0 = (Class)this.assetTypes.get(s);
            if(class0 != null) {
                return ((RefCountedContainer)((ObjectMap)this.assets.get(class0)).get(s)).refCount;
            }
        }
        throw new GdxRuntimeException("Asset not loaded: " + s);
    }

    private void handleTaskError(Throwable throwable0) {
        this.log.error("Error loading asset.", throwable0);
        if(this.tasks.isEmpty()) {
            throw new GdxRuntimeException(throwable0);
        }
        AssetLoadingTask assetLoadingTask0 = (AssetLoadingTask)this.tasks.pop();
        AssetDescriptor assetDescriptor0 = assetLoadingTask0.assetDesc;
        if(assetLoadingTask0.dependenciesLoaded && assetLoadingTask0.dependencies != null) {
            for(Object object0: assetLoadingTask0.dependencies) {
                this.unload(((AssetDescriptor)object0).fileName);
            }
        }
        this.tasks.clear();
        AssetErrorListener assetErrorListener0 = this.listener;
        if(assetErrorListener0 == null) {
            throw new GdxRuntimeException(throwable0);
        }
        assetErrorListener0.error(assetDescriptor0, throwable0);
    }

    private void incrementRefCountedDependencies(String s) {
        Array array0 = (Array)this.assetDependencies.get(s);
        if(array0 == null) {
            return;
        }
        for(Object object0: array0) {
            Class class0 = (Class)this.assetTypes.get(((String)object0));
            RefCountedContainer assetManager$RefCountedContainer0 = (RefCountedContainer)((ObjectMap)this.assets.get(class0)).get(((String)object0));
            ++assetManager$RefCountedContainer0.refCount;
            this.incrementRefCountedDependencies(((String)object0));
        }
    }

    void injectDependencies(String s, Array array0) {
        synchronized(this) {
            ObjectSet objectSet0 = this.injected;
            for(Object object0: array0) {
                AssetDescriptor assetDescriptor0 = (AssetDescriptor)object0;
                if(!objectSet0.contains(assetDescriptor0.fileName)) {
                    objectSet0.add(assetDescriptor0.fileName);
                    this.injectDependency(s, assetDescriptor0);
                }
            }
            objectSet0.clear(0x20);
        }
    }

    private void injectDependency(String s, AssetDescriptor assetDescriptor0) {
        synchronized(this) {
            Array array0 = (Array)this.assetDependencies.get(s);
            if(array0 == null) {
                array0 = new Array();
                this.assetDependencies.put(s, array0);
            }
            array0.add(assetDescriptor0.fileName);
            if(this.isLoaded(assetDescriptor0.fileName)) {
                this.log.debug("Dependency already loaded: " + assetDescriptor0);
                Class class0 = (Class)this.assetTypes.get(assetDescriptor0.fileName);
                RefCountedContainer assetManager$RefCountedContainer0 = (RefCountedContainer)((ObjectMap)this.assets.get(class0)).get(assetDescriptor0.fileName);
                ++assetManager$RefCountedContainer0.refCount;
                this.incrementRefCountedDependencies(assetDescriptor0.fileName);
            }
            else {
                this.log.info("Loading dependency: " + assetDescriptor0);
                this.addTask(assetDescriptor0);
            }
        }
    }

    public boolean isFinished() {
        synchronized(this) {
        }
        return this.loadQueue.size == 0 && this.tasks.size == 0;
    }

    public boolean isLoaded(AssetDescriptor assetDescriptor0) {
        synchronized(this) {
            return this.isLoaded(assetDescriptor0.fileName);
        }
    }

    public boolean isLoaded(String s) {
        synchronized(this) {
            return s == null ? false : this.assetTypes.containsKey(s);
        }
    }

    public boolean isLoaded(String s, Class class0) {
        synchronized(this) {
            boolean z = false;
            ObjectMap objectMap0 = (ObjectMap)this.assets.get(class0);
            if(objectMap0 == null) {
                return false;
            }
            if(objectMap0.get(s) != null) {
                z = true;
            }
            return z;
        }
    }

    public void load(AssetDescriptor assetDescriptor0) {
        synchronized(this) {
            this.load(assetDescriptor0.fileName, assetDescriptor0.type, assetDescriptor0.params);
        }
    }

    public void load(String s, Class class0) {
        synchronized(this) {
            this.load(s, class0, null);
        }
    }

    public void load(String s, Class class0, AssetLoaderParameters assetLoaderParameters0) {
        synchronized(this) {
            if(this.getLoader(class0, s) != null) {
                if(this.loadQueue.size == 0) {
                    this.loaded = 0;
                    this.toLoad = 0;
                    this.peakTasks = 0;
                }
                for(int v2 = 0; v2 < this.loadQueue.size; ++v2) {
                    AssetDescriptor assetDescriptor0 = (AssetDescriptor)this.loadQueue.get(v2);
                    if(assetDescriptor0.fileName.equals(s) && !assetDescriptor0.type.equals(class0)) {
                        throw new GdxRuntimeException("Asset with name \'" + s + "\' already in preload queue, but has different type (expected: " + ClassReflection.getSimpleName(class0) + ", found: " + ClassReflection.getSimpleName(assetDescriptor0.type) + ")");
                    }
                }
                for(int v = 0; v < this.tasks.size; ++v) {
                    AssetDescriptor assetDescriptor1 = ((AssetLoadingTask)this.tasks.get(v)).assetDesc;
                    if(assetDescriptor1.fileName.equals(s) && !assetDescriptor1.type.equals(class0)) {
                        throw new GdxRuntimeException("Asset with name \'" + s + "\' already in task list, but has different type (expected: " + ClassReflection.getSimpleName(class0) + ", found: " + ClassReflection.getSimpleName(assetDescriptor1.type) + ")");
                    }
                }
                Class class1 = (Class)this.assetTypes.get(s);
                if(class1 != null && !class1.equals(class0)) {
                    throw new GdxRuntimeException("Asset with name \'" + s + "\' already loaded, but has different type (expected: " + ClassReflection.getSimpleName(class0) + ", found: " + ClassReflection.getSimpleName(class1) + ")");
                }
                ++this.toLoad;
                AssetDescriptor assetDescriptor2 = new AssetDescriptor(s, class0, assetLoaderParameters0);
                this.loadQueue.add(assetDescriptor2);
                this.log.debug("Queued: " + assetDescriptor2);
                return;
            }
        }
        throw new GdxRuntimeException("No loader for type: " + ClassReflection.getSimpleName(class0));
    }

    private void nextTask() {
        AssetDescriptor assetDescriptor0 = (AssetDescriptor)this.loadQueue.removeIndex(0);
        if(this.isLoaded(assetDescriptor0.fileName)) {
            this.log.debug("Already loaded: " + assetDescriptor0);
            Class class0 = (Class)this.assetTypes.get(assetDescriptor0.fileName);
            RefCountedContainer assetManager$RefCountedContainer0 = (RefCountedContainer)((ObjectMap)this.assets.get(class0)).get(assetDescriptor0.fileName);
            ++assetManager$RefCountedContainer0.refCount;
            this.incrementRefCountedDependencies(assetDescriptor0.fileName);
            if(assetDescriptor0.params != null && assetDescriptor0.params.loadedCallback != null) {
                assetDescriptor0.params.loadedCallback.finishedLoading(this, assetDescriptor0.fileName, assetDescriptor0.type);
            }
            ++this.loaded;
            return;
        }
        this.log.info("Loading: " + assetDescriptor0);
        this.addTask(assetDescriptor0);
    }

    public void setErrorListener(AssetErrorListener assetErrorListener0) {
        synchronized(this) {
            this.listener = assetErrorListener0;
        }
    }

    public void setLoader(Class class0, AssetLoader assetLoader0) {
        synchronized(this) {
            this.setLoader(class0, null, assetLoader0);
        }
    }

    public void setLoader(Class class0, String s, AssetLoader assetLoader0) {
        synchronized(this) {
            if(class0 != null) {
                if(assetLoader0 == null) {
                    throw new IllegalArgumentException("loader cannot be null.");
                }
                this.log.debug("Loader set: " + ClassReflection.getSimpleName(class0) + " -> " + ClassReflection.getSimpleName(assetLoader0.getClass()));
                ObjectMap objectMap0 = (ObjectMap)this.loaders.get(class0);
                if(objectMap0 == null) {
                    ObjectMap objectMap1 = new ObjectMap();
                    this.loaders.put(class0, objectMap1);
                    objectMap0 = objectMap1;
                }
                if(s == null) {
                    s = "";
                }
                objectMap0.put(s, assetLoader0);
                return;
            }
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    public void setLogger(Logger logger0) {
        this.log = logger0;
    }

    public void setReferenceCount(String s, int v) {
        synchronized(this) {
            Class class0 = (Class)this.assetTypes.get(s);
            if(class0 != null) {
                ((RefCountedContainer)((ObjectMap)this.assets.get(class0)).get(s)).refCount = v;
                return;
            }
        }
        throw new GdxRuntimeException("Asset not loaded: " + s);
    }

    protected void taskFailed(AssetDescriptor assetDescriptor0, RuntimeException runtimeException0) {
        throw runtimeException0;
    }

    public void unload(String s) {
        String s1;
        synchronized(this) {
            s1 = s.replace('\\', '/');
            if(this.tasks.size > 0) {
                AssetLoadingTask assetLoadingTask0 = (AssetLoadingTask)this.tasks.first();
                if(assetLoadingTask0.assetDesc.fileName.equals(s1)) {
                    this.log.info("Unload (from tasks): " + s1);
                    assetLoadingTask0.cancel = true;
                    assetLoadingTask0.unload();
                    return;
                }
            }
            Class class0 = (Class)this.assetTypes.get(s1);
            int v1;
            for(v1 = 0; true; ++v1) {
                if(v1 >= this.loadQueue.size) {
                    v1 = -1;
                    break;
                }
                if(((AssetDescriptor)this.loadQueue.get(v1)).fileName.equals(s1)) {
                    break;
                }
            }
            if(v1 != -1) {
                --this.toLoad;
                AssetDescriptor assetDescriptor0 = (AssetDescriptor)this.loadQueue.removeIndex(v1);
                this.log.info("Unload (from queue): " + s1);
                if(class0 != null && assetDescriptor0.params != null && assetDescriptor0.params.loadedCallback != null) {
                    assetDescriptor0.params.loadedCallback.finishedLoading(this, assetDescriptor0.fileName, assetDescriptor0.type);
                }
                return;
            }
            if(class0 != null) {
                RefCountedContainer assetManager$RefCountedContainer0 = (RefCountedContainer)((ObjectMap)this.assets.get(class0)).get(s1);
                --assetManager$RefCountedContainer0.refCount;
                if(assetManager$RefCountedContainer0.refCount <= 0) {
                    this.log.info("Unload (dispose): " + s1);
                    if(assetManager$RefCountedContainer0.object instanceof Disposable) {
                        ((Disposable)assetManager$RefCountedContainer0.object).dispose();
                    }
                    this.assetTypes.remove(s1);
                    ((ObjectMap)this.assets.get(class0)).remove(s1);
                }
                else {
                    this.log.info("Unload (decrement): " + s1);
                }
                Array array0 = (Array)this.assetDependencies.get(s1);
                if(array0 != null) {
                    for(Object object0: array0) {
                        String s2 = (String)object0;
                        if(this.isLoaded(s2)) {
                            this.unload(s2);
                        }
                    }
                }
                if(assetManager$RefCountedContainer0.refCount <= 0) {
                    this.assetDependencies.remove(s1);
                }
                return;
            }
        }
        throw new GdxRuntimeException("Asset not loaded: " + s1);
    }

    public boolean update() {
        boolean z = false;
        synchronized(this) {
            try {
                if(this.tasks.size == 0) {
                    while(this.loadQueue.size != 0 && this.tasks.size == 0) {
                        this.nextTask();
                    }
                    if(this.tasks.size == 0) {
                        return true;
                    }
                }
                if(this.updateTask() && this.loadQueue.size == 0 && this.tasks.size == 0) {
                    z = true;
                }
            }
            catch(Throwable throwable0) {
                this.handleTaskError(throwable0);
                if(this.loadQueue.size == 0) {
                    z = true;
                }
            }
            return z;
        }
    }

    public boolean update(int v) {
        boolean z;
        while(!z = this.update() && 1762855044098L <= 1762855044098L + ((long)v)) {
            ThreadUtils.yield();
        }
        return z;
    }

    private boolean updateTask() {
        boolean z;
        AssetLoadingTask assetLoadingTask0 = (AssetLoadingTask)this.tasks.peek();
        try {
            if(assetLoadingTask0.cancel || assetLoadingTask0.update()) {
                goto label_10;
            }
            else {
                z = false;
            }
        }
        catch(RuntimeException runtimeException0) {
            assetLoadingTask0.cancel = true;
            this.taskFailed(assetLoadingTask0.assetDesc, runtimeException0);
            z = true;
        }
        goto label_11;
    label_10:
        z = true;
    label_11:
        if(z) {
            if(this.tasks.size == 1) {
                ++this.loaded;
                this.peakTasks = 0;
            }
            this.tasks.pop();
            if(assetLoadingTask0.cancel) {
                return true;
            }
            this.addAsset(assetLoadingTask0.assetDesc.fileName, assetLoadingTask0.assetDesc.type, assetLoadingTask0.asset);
            if(assetLoadingTask0.assetDesc.params != null && assetLoadingTask0.assetDesc.params.loadedCallback != null) {
                assetLoadingTask0.assetDesc.params.loadedCallback.finishedLoading(this, assetLoadingTask0.assetDesc.fileName, assetLoadingTask0.assetDesc.type);
            }
            this.log.debug("Loaded: " + ((float)(37082257000100L - assetLoadingTask0.startTime)) / 1000000.0f + "ms " + assetLoadingTask0.assetDesc);
            return true;
        }
        return false;
    }
}

