package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ETC1TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.graphics.glutils.KTXTextureData;

public interface TextureData {
    public static class Factory {
        public static TextureData loadFromFile(FileHandle fileHandle0, Format pixmap$Format0, boolean z) {
            if(fileHandle0 == null) {
                return null;
            }
            if(fileHandle0.name().endsWith(".cim")) {
                return new FileTextureData(fileHandle0, PixmapIO.readCIM(fileHandle0), pixmap$Format0, z);
            }
            if(fileHandle0.name().endsWith(".etc1")) {
                return new ETC1TextureData(fileHandle0, z);
            }
            return !fileHandle0.name().endsWith(".ktx") && !fileHandle0.name().endsWith(".zktx") ? new FileTextureData(fileHandle0, new Pixmap(fileHandle0), pixmap$Format0, z) : new KTXTextureData(fileHandle0, z);
        }

        public static TextureData loadFromFile(FileHandle fileHandle0, boolean z) {
            return Factory.loadFromFile(fileHandle0, null, z);
        }
    }

    public static enum TextureDataType {
        Pixmap,
        Custom;

    }

    void consumeCustomData(int arg1);

    Pixmap consumePixmap();

    boolean disposePixmap();

    Format getFormat();

    int getHeight();

    TextureDataType getType();

    int getWidth();

    boolean isManaged();

    boolean isPrepared();

    void prepare();

    boolean useMipMaps();
}

