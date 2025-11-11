package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider.AssetTextureProvider;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider.FileTextureProvider;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import java.util.Iterator;

public abstract class ModelLoader extends AsynchronousAssetLoader {
    public static class ModelParameters extends AssetLoaderParameters {
        public TextureParameter textureParameter;

        public ModelParameters() {
            this.textureParameter = new TextureParameter();
            this.textureParameter.magFilter = TextureFilter.Linear;
            this.textureParameter.minFilter = TextureFilter.Linear;
            this.textureParameter.wrapV = TextureWrap.Repeat;
            this.textureParameter.wrapU = TextureWrap.Repeat;
        }
    }

    protected ModelParameters defaultParameters;
    protected Array items;

    public ModelLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.items = new Array();
        this.defaultParameters = new ModelParameters();
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((ModelParameters)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, ModelParameters modelLoader$ModelParameters0) {
        Array array0 = new Array();
        ModelData modelData0 = this.loadModelData(fileHandle0, modelLoader$ModelParameters0);
        if(modelData0 == null) {
            return array0;
        }
        Entry objectMap$Entry0 = new Entry();
        objectMap$Entry0.key = s;
        objectMap$Entry0.value = modelData0;
        synchronized(this.items) {
            this.items.add(objectMap$Entry0);
        }
        TextureParameter textureLoader$TextureParameter0 = modelLoader$ModelParameters0 == null ? this.defaultParameters.textureParameter : modelLoader$ModelParameters0.textureParameter;
        for(Object object0: modelData0.materials) {
            ModelMaterial modelMaterial0 = (ModelMaterial)object0;
            if(modelMaterial0.textures != null) {
                for(Object object1: modelMaterial0.textures) {
                    array0.add(new AssetDescriptor(((ModelTexture)object1).fileName, Texture.class, textureLoader$TextureParameter0));
                }
            }
        }
        return array0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, ModelParameters modelLoader$ModelParameters0) {
    }

    public Model loadModel(FileHandle fileHandle0) {
        return this.loadModel(fileHandle0, new FileTextureProvider(), null);
    }

    public Model loadModel(FileHandle fileHandle0, ModelParameters modelLoader$ModelParameters0) {
        return this.loadModel(fileHandle0, new FileTextureProvider(), modelLoader$ModelParameters0);
    }

    public Model loadModel(FileHandle fileHandle0, TextureProvider textureProvider0) {
        return this.loadModel(fileHandle0, textureProvider0, null);
    }

    public Model loadModel(FileHandle fileHandle0, TextureProvider textureProvider0, ModelParameters modelLoader$ModelParameters0) {
        ModelData modelData0 = this.loadModelData(fileHandle0, modelLoader$ModelParameters0);
        return modelData0 == null ? null : new Model(modelData0, textureProvider0);
    }

    public ModelData loadModelData(FileHandle fileHandle0) {
        return this.loadModelData(fileHandle0, null);
    }

    public abstract ModelData loadModelData(FileHandle arg1, ModelParameters arg2);

    public Model loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, ModelParameters modelLoader$ModelParameters0) {
        ModelData modelData0;
        synchronized(this.items) {
            modelData0 = null;
            for(int v1 = 0; v1 < this.items.size; ++v1) {
                if(((String)((Entry)this.items.get(v1)).key).equals(s)) {
                    modelData0 = (ModelData)((Entry)this.items.get(v1)).value;
                    this.items.removeIndex(v1);
                }
            }
        }
        if(modelData0 == null) {
            return null;
        }
        Model model0 = new Model(modelData0, new AssetTextureProvider(assetManager0));
        Iterator iterator0 = model0.getManagedDisposables().iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            if(((Disposable)object0) instanceof Texture) {
                iterator0.remove();
            }
        }
        return model0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((ModelParameters)assetLoaderParameters0));
    }
}

