package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class ResourceData implements Serializable {
    public static class AssetData implements Serializable {
        public String filename;
        public Class type;

        public AssetData() {
        }

        public AssetData(String s, Class class0) {
            this.filename = s;
            this.type = class0;
        }

        @Override  // com.badlogic.gdx.utils.Json$Serializable
        public void read(Json json0, JsonValue jsonValue0) {
            this.filename = (String)json0.readValue("filename", String.class, jsonValue0);
            String s = (String)json0.readValue("type", String.class, jsonValue0);
            try {
                this.type = ClassReflection.forName(s);
            }
            catch(ReflectionException reflectionException0) {
                throw new GdxRuntimeException("Class not found: " + s, reflectionException0);
            }
        }

        @Override  // com.badlogic.gdx.utils.Json$Serializable
        public void write(Json json0) {
            json0.writeValue("filename", this.filename);
            json0.writeValue("type", this.type.getName());
        }
    }

    public interface Configurable {
        void load(AssetManager arg1, ResourceData arg2);

        void save(AssetManager arg1, ResourceData arg2);
    }

    public static class SaveData implements Serializable {
        IntArray assets;
        ObjectMap data;
        private int loadIndex;
        protected ResourceData resources;

        public SaveData() {
            this.data = new ObjectMap();
            this.assets = new IntArray();
            this.loadIndex = 0;
        }

        public SaveData(ResourceData resourceData0) {
            this.data = new ObjectMap();
            this.assets = new IntArray();
            this.loadIndex = 0;
            this.resources = resourceData0;
        }

        public Object load(String s) {
            return this.data.get(s);
        }

        public AssetDescriptor loadAsset() {
            if(this.loadIndex == this.assets.size) {
                return null;
            }
            int v = this.loadIndex;
            this.loadIndex = v + 1;
            AssetData resourceData$AssetData0 = (AssetData)this.resources.sharedAssets.get(this.assets.get(v));
            return new AssetDescriptor(resourceData$AssetData0.filename, resourceData$AssetData0.type);
        }

        @Override  // com.badlogic.gdx.utils.Json$Serializable
        public void read(Json json0, JsonValue jsonValue0) {
            this.data = (ObjectMap)json0.readValue("data", ObjectMap.class, jsonValue0);
            this.assets.addAll(((int[])json0.readValue("indices", int[].class, jsonValue0)));
        }

        public void save(String s, Object object0) {
            this.data.put(s, object0);
        }

        public void saveAsset(String s, Class class0) {
            int v = this.resources.getAssetData(s, class0);
            if(v == -1) {
                this.resources.sharedAssets.add(new AssetData(s, class0));
                v = this.resources.sharedAssets.size - 1;
            }
            this.assets.add(v);
        }

        @Override  // com.badlogic.gdx.utils.Json$Serializable
        public void write(Json json0) {
            json0.writeValue("data", this.data, ObjectMap.class);
            json0.writeValue("indices", this.assets.toArray(), int[].class);
        }
    }

    private int currentLoadIndex;
    private Array data;
    public Object resource;
    Array sharedAssets;
    private ObjectMap uniqueData;

    public ResourceData() {
        this.uniqueData = new ObjectMap();
        this.data = new Array(true, 3, SaveData.class);
        this.sharedAssets = new Array();
        this.currentLoadIndex = 0;
    }

    public ResourceData(Object object0) {
        this.resource = object0;
    }

    public SaveData createSaveData() {
        SaveData resourceData$SaveData0 = new SaveData(this);
        this.data.add(resourceData$SaveData0);
        return resourceData$SaveData0;
    }

    public SaveData createSaveData(String s) {
        SaveData resourceData$SaveData0 = new SaveData(this);
        if(this.uniqueData.containsKey(s)) {
            throw new RuntimeException("Key already used, data must be unique, use a different key");
        }
        this.uniqueData.put(s, resourceData$SaveData0);
        return resourceData$SaveData0;
    }

    int getAssetData(String s, Class class0) {
        int v = 0;
        for(Object object0: this.sharedAssets) {
            if(((AssetData)object0).filename.equals(s) && ((AssetData)object0).type.equals(class0)) {
                return v;
            }
            ++v;
        }
        return -1;
    }

    public Array getAssetDescriptors() {
        Array array0 = new Array();
        for(Object object0: this.sharedAssets) {
            array0.add(new AssetDescriptor(((AssetData)object0).filename, ((AssetData)object0).type));
        }
        return array0;
    }

    public Array getAssets() {
        return this.sharedAssets;
    }

    public SaveData getSaveData() {
        int v = this.currentLoadIndex;
        this.currentLoadIndex = v + 1;
        return (SaveData)this.data.get(v);
    }

    public SaveData getSaveData(String s) {
        return (SaveData)this.uniqueData.get(s);
    }

    @Override  // com.badlogic.gdx.utils.Json$Serializable
    public void read(Json json0, JsonValue jsonValue0) {
        this.uniqueData = (ObjectMap)json0.readValue("unique", ObjectMap.class, jsonValue0);
        for(Object object0: this.uniqueData.entries()) {
            ((SaveData)((Entry)object0).value).resources = this;
        }
        this.data = (Array)json0.readValue("data", Array.class, SaveData.class, jsonValue0);
        for(Object object1: this.data) {
            ((SaveData)object1).resources = this;
        }
        this.sharedAssets.addAll(((Array)json0.readValue("assets", Array.class, AssetData.class, jsonValue0)));
        this.resource = json0.readValue("resource", null, jsonValue0);
    }

    @Override  // com.badlogic.gdx.utils.Json$Serializable
    public void write(Json json0) {
        json0.writeValue("unique", this.uniqueData, ObjectMap.class);
        json0.writeValue("data", this.data, Array.class, SaveData.class);
        json0.writeValue("assets", this.sharedAssets.toArray(AssetData.class), AssetData[].class);
        json0.writeValue("resource", this.resource, null);
    }
}

