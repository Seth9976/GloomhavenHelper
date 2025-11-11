package com.badlogic.gdx.assets;

import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.async.AsyncTask;

class AssetLoadingTask implements AsyncTask {
    volatile Object asset;
    final AssetDescriptor assetDesc;
    volatile boolean asyncDone;
    volatile boolean cancel;
    volatile Array dependencies;
    volatile boolean dependenciesLoaded;
    volatile AsyncResult depsFuture;
    final AsyncExecutor executor;
    volatile AsyncResult loadFuture;
    final AssetLoader loader;
    AssetManager manager;
    final long startTime;

    public AssetLoadingTask(AssetManager assetManager0, AssetDescriptor assetDescriptor0, AssetLoader assetLoader0, AsyncExecutor asyncExecutor0) {
        this.manager = assetManager0;
        this.assetDesc = assetDescriptor0;
        this.loader = assetLoader0;
        this.executor = asyncExecutor0;
        this.startTime = assetManager0.log.getLevel() == 3 ? 37082077950700L : 0L;
    }

    @Override  // com.badlogic.gdx.utils.async.AsyncTask
    public Object call() throws Exception {
        return this.call();
    }

    public Void call() throws Exception {
        if(this.cancel) {
            return null;
        }
        AsynchronousAssetLoader asynchronousAssetLoader0 = (AsynchronousAssetLoader)this.loader;
        if(!this.dependenciesLoaded) {
            FileHandle fileHandle0 = this.resolve(this.loader, this.assetDesc);
            this.dependencies = asynchronousAssetLoader0.getDependencies(this.assetDesc.fileName, fileHandle0, this.assetDesc.params);
            if(this.dependencies != null) {
                this.removeDuplicates(this.dependencies);
                this.manager.injectDependencies(this.assetDesc.fileName, this.dependencies);
                return null;
            }
            AssetManager assetManager0 = this.manager;
            FileHandle fileHandle1 = this.resolve(this.loader, this.assetDesc);
            asynchronousAssetLoader0.loadAsync(assetManager0, this.assetDesc.fileName, fileHandle1, this.assetDesc.params);
            this.asyncDone = true;
            return null;
        }
        AssetManager assetManager1 = this.manager;
        FileHandle fileHandle2 = this.resolve(this.loader, this.assetDesc);
        asynchronousAssetLoader0.loadAsync(assetManager1, this.assetDesc.fileName, fileHandle2, this.assetDesc.params);
        this.asyncDone = true;
        return null;
    }

    private void handleAsyncLoader() {
        AsynchronousAssetLoader asynchronousAssetLoader0 = (AsynchronousAssetLoader)this.loader;
        if(this.dependenciesLoaded) {
            if(this.loadFuture == null && !this.asyncDone) {
                this.loadFuture = this.executor.submit(this);
                return;
            }
            if(this.asyncDone) {
                AssetManager assetManager1 = this.manager;
                FileHandle fileHandle1 = this.resolve(this.loader, this.assetDesc);
                this.asset = asynchronousAssetLoader0.loadSync(assetManager1, this.assetDesc.fileName, fileHandle1, this.assetDesc.params);
                return;
            }
            if(this.loadFuture.isDone()) {
                try {
                    this.loadFuture.get();
                }
                catch(Exception exception1) {
                    throw new GdxRuntimeException("Couldn\'t load asset: " + this.assetDesc.fileName, exception1);
                }
                AssetManager assetManager2 = this.manager;
                FileHandle fileHandle2 = this.resolve(this.loader, this.assetDesc);
                this.asset = asynchronousAssetLoader0.loadSync(assetManager2, this.assetDesc.fileName, fileHandle2, this.assetDesc.params);
            }
        }
        else {
            if(this.depsFuture == null) {
                this.depsFuture = this.executor.submit(this);
                return;
            }
            if(this.depsFuture.isDone()) {
                try {
                    this.depsFuture.get();
                    this.dependenciesLoaded = true;
                }
                catch(Exception exception0) {
                    throw new GdxRuntimeException("Couldn\'t load dependencies of asset: " + this.assetDesc.fileName, exception0);
                }
                if(this.asyncDone) {
                    AssetManager assetManager0 = this.manager;
                    FileHandle fileHandle0 = this.resolve(this.loader, this.assetDesc);
                    this.asset = asynchronousAssetLoader0.loadSync(assetManager0, this.assetDesc.fileName, fileHandle0, this.assetDesc.params);
                }
            }
        }
    }

    private void handleSyncLoader() {
        SynchronousAssetLoader synchronousAssetLoader0 = (SynchronousAssetLoader)this.loader;
        if(!this.dependenciesLoaded) {
            this.dependenciesLoaded = true;
            FileHandle fileHandle0 = this.resolve(this.loader, this.assetDesc);
            this.dependencies = synchronousAssetLoader0.getDependencies(this.assetDesc.fileName, fileHandle0, this.assetDesc.params);
            if(this.dependencies == null) {
                AssetManager assetManager0 = this.manager;
                FileHandle fileHandle1 = this.resolve(this.loader, this.assetDesc);
                this.asset = synchronousAssetLoader0.load(assetManager0, this.assetDesc.fileName, fileHandle1, this.assetDesc.params);
                return;
            }
            this.removeDuplicates(this.dependencies);
            this.manager.injectDependencies(this.assetDesc.fileName, this.dependencies);
            return;
        }
        AssetManager assetManager1 = this.manager;
        FileHandle fileHandle2 = this.resolve(this.loader, this.assetDesc);
        this.asset = synchronousAssetLoader0.load(assetManager1, this.assetDesc.fileName, fileHandle2, this.assetDesc.params);
    }

    private void removeDuplicates(Array array0) {
        boolean z = array0.ordered;
        array0.ordered = true;
        for(int v = 0; v < array0.size; ++v) {
            String s = ((AssetDescriptor)array0.get(v)).fileName;
            Class class0 = ((AssetDescriptor)array0.get(v)).type;
            for(int v1 = array0.size - 1; v1 > v; --v1) {
                if(class0 == ((AssetDescriptor)array0.get(v1)).type && s.equals(((AssetDescriptor)array0.get(v1)).fileName)) {
                    array0.removeIndex(v1);
                }
            }
        }
        array0.ordered = z;
    }

    private FileHandle resolve(AssetLoader assetLoader0, AssetDescriptor assetDescriptor0) {
        if(assetDescriptor0.file == null) {
            assetDescriptor0.file = assetLoader0.resolve(assetDescriptor0.fileName);
        }
        return assetDescriptor0.file;
    }

    public void unload() {
        if(this.loader instanceof AsynchronousAssetLoader) {
            this.resolve(this.loader, this.assetDesc);
        }
    }

    public boolean update() {
        if(this.loader instanceof SynchronousAssetLoader) {
            this.handleSyncLoader();
            return this.asset != null;
        }
        this.handleAsyncLoader();
        return this.asset != null;
    }
}

