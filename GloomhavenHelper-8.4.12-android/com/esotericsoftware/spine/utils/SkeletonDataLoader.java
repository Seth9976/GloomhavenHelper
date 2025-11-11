package com.esotericsoftware.spine.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.SkeletonBinary;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.attachments.AtlasAttachmentLoader;
import com.esotericsoftware.spine.attachments.AttachmentLoader;

public class SkeletonDataLoader extends AsynchronousAssetLoader {
    public static class SkeletonDataParameter extends AssetLoaderParameters {
        public String atlasName;
        public AttachmentLoader attachmentLoader;
        public float scale;

        public SkeletonDataParameter() {
            this.scale = 1.0f;
        }

        public SkeletonDataParameter(AttachmentLoader attachmentLoader0) {
            this.scale = 1.0f;
            this.attachmentLoader = attachmentLoader0;
        }

        public SkeletonDataParameter(AttachmentLoader attachmentLoader0, float f) {
            this.attachmentLoader = attachmentLoader0;
            this.scale = f;
        }

        public SkeletonDataParameter(String s) {
            this.scale = 1.0f;
            this.atlasName = s;
        }

        public SkeletonDataParameter(String s, float f) {
            this.atlasName = s;
            this.scale = f;
        }
    }

    private SkeletonData skeletonData;

    public SkeletonDataLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, @Null AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((SkeletonDataParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, @Null SkeletonDataParameter skeletonDataLoader$SkeletonDataParameter0) {
        if(skeletonDataLoader$SkeletonDataParameter0 == null) {
            return null;
        }
        if(skeletonDataLoader$SkeletonDataParameter0.attachmentLoader != null) {
            return null;
        }
        Array array0 = new Array();
        array0.add(new AssetDescriptor(skeletonDataLoader$SkeletonDataParameter0.atlasName, TextureAtlas.class));
        return array0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, @Null AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((SkeletonDataParameter)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, @Null SkeletonDataParameter skeletonDataLoader$SkeletonDataParameter0) {
        float f;
        AttachmentLoader attachmentLoader0 = null;
        if(skeletonDataLoader$SkeletonDataParameter0 == null) {
            f = 1.0f;
        }
        else {
            f = skeletonDataLoader$SkeletonDataParameter0.scale;
            if(skeletonDataLoader$SkeletonDataParameter0.attachmentLoader != null) {
                attachmentLoader0 = skeletonDataLoader$SkeletonDataParameter0.attachmentLoader;
            }
            else if(skeletonDataLoader$SkeletonDataParameter0.atlasName != null) {
                attachmentLoader0 = new AtlasAttachmentLoader(((TextureAtlas)assetManager0.get(skeletonDataLoader$SkeletonDataParameter0.atlasName, TextureAtlas.class)));
            }
        }
        if(attachmentLoader0 == null) {
            attachmentLoader0 = new AtlasAttachmentLoader(((TextureAtlas)assetManager0.get(fileHandle0.pathWithoutExtension() + ".atlas", TextureAtlas.class)));
        }
        if(fileHandle0.extension().equalsIgnoreCase("skel")) {
            SkeletonBinary skeletonBinary0 = new SkeletonBinary(attachmentLoader0);
            skeletonBinary0.setScale(f);
            this.skeletonData = skeletonBinary0.readSkeletonData(fileHandle0);
            return;
        }
        SkeletonJson skeletonJson0 = new SkeletonJson(attachmentLoader0);
        skeletonJson0.setScale(f);
        this.skeletonData = skeletonJson0.readSkeletonData(fileHandle0);
    }

    public SkeletonData loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, @Null SkeletonDataParameter skeletonDataLoader$SkeletonDataParameter0) {
        SkeletonData skeletonData0 = this.skeletonData;
        this.skeletonData = null;
        return skeletonData0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, @Null AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((SkeletonDataParameter)assetLoaderParameters0));
    }
}

