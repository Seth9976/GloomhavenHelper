package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class PixmapTextureData implements TextureData {
    final boolean disposePixmap;
    final Format format;
    final boolean managed;
    final Pixmap pixmap;
    final boolean useMipMaps;

    public PixmapTextureData(Pixmap pixmap0, Format pixmap$Format0, boolean z, boolean z1) {
        this(pixmap0, pixmap$Format0, z, z1, false);
    }

    public PixmapTextureData(Pixmap pixmap0, Format pixmap$Format0, boolean z, boolean z1, boolean z2) {
        this.pixmap = pixmap0;
        if(pixmap$Format0 == null) {
            pixmap$Format0 = pixmap0.getFormat();
        }
        this.format = pixmap$Format0;
        this.useMipMaps = z;
        this.disposePixmap = z1;
        this.managed = z2;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int v) {
        throw new GdxRuntimeException("This TextureData implementation does not upload data itself");
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        return this.pixmap;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        return this.disposePixmap;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Format getFormat() {
        return this.format;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.pixmap.getHeight();
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public TextureDataType getType() {
        return TextureDataType.Pixmap;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public int getWidth() {
        return this.pixmap.getWidth();
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean isManaged() {
        return this.managed;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
        throw new GdxRuntimeException("prepare() must not be called on a PixmapTextureData instance as it is already prepared.");
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }
}

