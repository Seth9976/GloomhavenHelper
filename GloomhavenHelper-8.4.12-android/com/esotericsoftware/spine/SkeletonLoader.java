package com.esotericsoftware.spine;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.attachments.AtlasAttachmentLoader;
import com.esotericsoftware.spine.attachments.AttachmentLoader;
import java.io.InputStream;

public abstract class SkeletonLoader {
    final AttachmentLoader attachmentLoader;
    final Array linkedMeshes;
    float scale;

    public SkeletonLoader(TextureAtlas textureAtlas0) {
        this.scale = 1.0f;
        this.linkedMeshes = new Array();
        this.attachmentLoader = new AtlasAttachmentLoader(textureAtlas0);
    }

    public SkeletonLoader(AttachmentLoader attachmentLoader0) {
        this.scale = 1.0f;
        this.linkedMeshes = new Array();
        if(attachmentLoader0 == null) {
            throw new IllegalArgumentException("attachmentLoader cannot be null.");
        }
        this.attachmentLoader = attachmentLoader0;
    }

    public float getScale() {
        return this.scale;
    }

    public abstract SkeletonData readSkeletonData(FileHandle arg1);

    public abstract SkeletonData readSkeletonData(InputStream arg1);

    public void setScale(float f) {
        if(f == 0.0f) {
            throw new IllegalArgumentException("scale cannot be 0.");
        }
        this.scale = f;
    }
}

