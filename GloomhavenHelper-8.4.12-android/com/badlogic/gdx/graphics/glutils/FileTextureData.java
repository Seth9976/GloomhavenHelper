package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class FileTextureData implements TextureData {
    final FileHandle file;
    Format format;
    int height;
    boolean isPrepared;
    Pixmap pixmap;
    boolean useMipMaps;
    int width;

    public FileTextureData(FileHandle fileHandle0, Pixmap pixmap0, Format pixmap$Format0, boolean z) {
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.file = fileHandle0;
        this.pixmap = pixmap0;
        this.format = pixmap$Format0;
        this.useMipMaps = z;
        Pixmap pixmap1 = this.pixmap;
        if(pixmap1 != null) {
            this.width = pixmap1.getWidth();
            this.height = this.pixmap.getHeight();
            if(pixmap$Format0 == null) {
                this.format = this.pixmap.getFormat();
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int v) {
        throw new GdxRuntimeException("This TextureData implementation does not upload data itself");
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        if(!this.isPrepared) {
            throw new GdxRuntimeException("Call prepare() before calling getPixmap()");
        }
        this.isPrepared = false;
        Pixmap pixmap0 = this.pixmap;
        this.pixmap = null;
        return pixmap0;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        return true;
    }

    public FileHandle getFileHandle() {
        return this.file;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Format getFormat() {
        return this.format;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.height;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public TextureDataType getType() {
        return TextureDataType.Pixmap;
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
        if(this.pixmap == null) {
            this.pixmap = this.file.extension().equals("cim") ? PixmapIO.readCIM(this.file) : new Pixmap(this.file);
            this.width = this.pixmap.getWidth();
            this.height = this.pixmap.getHeight();
            if(this.format == null) {
                this.format = this.pixmap.getFormat();
            }
        }
        this.isPrepared = true;
    }

    @Override
    public String toString() {
        return this.file.toString();
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }
}

