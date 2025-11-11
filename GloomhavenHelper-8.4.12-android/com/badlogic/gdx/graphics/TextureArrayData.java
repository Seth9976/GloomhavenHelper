package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.FileTextureArrayData;

public interface TextureArrayData {
    public static class Factory {
        public static TextureArrayData loadFromFiles(Format pixmap$Format0, boolean z, FileHandle[] arr_fileHandle) {
            return new FileTextureArrayData(pixmap$Format0, z, arr_fileHandle);
        }
    }

    void consumeTextureArrayData();

    int getDepth();

    int getGLType();

    int getHeight();

    int getInternalFormat();

    int getWidth();

    boolean isManaged();

    boolean isPrepared();

    void prepare();
}

