package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ETC1TextureData implements TextureData {
    ETC1Data data;
    FileHandle file;
    int height;
    boolean isPrepared;
    boolean useMipMaps;
    int width;

    public ETC1TextureData(FileHandle fileHandle0) {
        this(fileHandle0, false);
    }

    public ETC1TextureData(FileHandle fileHandle0, boolean z) {
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.file = fileHandle0;
        this.useMipMaps = z;
    }

    public ETC1TextureData(ETC1Data eTC1$ETC1Data0, boolean z) {
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.data = eTC1$ETC1Data0;
        this.useMipMaps = z;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int v) {
        if(!this.isPrepared) {
            throw new GdxRuntimeException("Call prepare() before calling consumeCompressedData()");
        }
        if(Gdx.graphics.supportsExtension("GL_OES_compressed_ETC1_RGB8_texture")) {
            Gdx.gl.glCompressedTexImage2D(v, 0, ETC1.ETC1_RGB8_OES, this.width, this.height, 0, this.data.compressedData.capacity() - this.data.dataOffset, this.data.compressedData);
            if(this.useMipMaps()) {
                Gdx.gl20.glGenerateMipmap(0xDE1);
            }
        }
        else {
            Pixmap pixmap0 = ETC1.decodeImage(this.data, Format.RGB565);
            Gdx.gl.glTexImage2D(v, 0, pixmap0.getGLInternalFormat(), pixmap0.getWidth(), pixmap0.getHeight(), 0, pixmap0.getGLFormat(), pixmap0.getGLType(), pixmap0.getPixels());
            if(this.useMipMaps) {
                MipMapGenerator.generateMipMap(v, pixmap0, pixmap0.getWidth(), pixmap0.getHeight());
            }
            pixmap0.dispose();
            this.useMipMaps = false;
        }
        this.data.dispose();
        this.data = null;
        this.isPrepared = false;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Format getFormat() {
        return Format.RGB565;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.height;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public TextureDataType getType() {
        return TextureDataType.Custom;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public int getWidth() {
        return this.width;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean isManaged() {
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return this.isPrepared;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
        if(this.isPrepared) {
            throw new GdxRuntimeException("Already prepared");
        }
        if(this.file == null && this.data == null) {
            throw new GdxRuntimeException("Can only load once from ETC1Data");
        }
        FileHandle fileHandle0 = this.file;
        if(fileHandle0 != null) {
            this.data = new ETC1Data(fileHandle0);
        }
        this.width = this.data.width;
        this.height = this.data.height;
        this.isPrepared = true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }
}

