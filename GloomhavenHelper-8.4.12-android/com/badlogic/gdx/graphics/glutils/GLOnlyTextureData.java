package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class GLOnlyTextureData implements TextureData {
    int format;
    int height;
    int internalFormat;
    boolean isPrepared;
    int mipLevel;
    int type;
    int width;

    public GLOnlyTextureData(int v, int v1, int v2, int v3, int v4, int v5) {
        this.isPrepared = false;
        this.width = v;
        this.height = v1;
        this.mipLevel = v2;
        this.internalFormat = v3;
        this.format = v4;
        this.type = v5;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int v) {
        Gdx.gl.glTexImage2D(v, this.mipLevel, this.internalFormat, this.width, this.height, 0, this.format, this.type, null);
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
        return Format.RGBA8888;
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
        return false;
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
        this.isPrepared = true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return false;
    }
}

