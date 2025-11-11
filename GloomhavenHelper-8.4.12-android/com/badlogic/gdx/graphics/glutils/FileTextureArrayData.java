package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureArrayData;
import com.badlogic.gdx.graphics.TextureData.Factory;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class FileTextureArrayData implements TextureArrayData {
    private int depth;
    private Format format;
    private boolean prepared;
    private TextureData[] textureDatas;
    boolean useMipMaps;

    public FileTextureArrayData(Format pixmap$Format0, boolean z, FileHandle[] arr_fileHandle) {
        this.format = pixmap$Format0;
        this.useMipMaps = z;
        this.depth = arr_fileHandle.length;
        this.textureDatas = new TextureData[arr_fileHandle.length];
        for(int v = 0; v < arr_fileHandle.length; ++v) {
            TextureData[] arr_textureData = this.textureDatas;
            arr_textureData[v] = Factory.loadFromFile(arr_fileHandle[v], pixmap$Format0, z);
        }
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public void consumeTextureArrayData() {
        boolean z1;
        Pixmap pixmap2;
        for(int v = 0; true; ++v) {
            TextureData[] arr_textureData = this.textureDatas;
            if(v >= arr_textureData.length) {
                break;
            }
            if(arr_textureData[v].getType() == TextureDataType.Custom) {
                this.textureDatas[v].consumeCustomData(0x8C1A);
            }
            else {
                TextureData textureData0 = this.textureDatas[v];
                Pixmap pixmap0 = textureData0.consumePixmap();
                boolean z = textureData0.disposePixmap();
                if(textureData0.getFormat() == pixmap0.getFormat()) {
                    z1 = z;
                    pixmap2 = pixmap0;
                }
                else {
                    Pixmap pixmap1 = new Pixmap(pixmap0.getWidth(), pixmap0.getHeight(), textureData0.getFormat());
                    pixmap1.setBlending(Blending.None);
                    pixmap1.drawPixmap(pixmap0, 0, 0, 0, 0, pixmap0.getWidth(), pixmap0.getHeight());
                    if(textureData0.disposePixmap()) {
                        pixmap0.dispose();
                    }
                    pixmap2 = pixmap1;
                    z1 = true;
                }
                Gdx.gl30.glTexSubImage3D(0x8C1A, 0, 0, 0, v, pixmap2.getWidth(), pixmap2.getHeight(), 1, pixmap2.getGLInternalFormat(), pixmap2.getGLType(), pixmap2.getPixels());
                if(this.useMipMaps) {
                    Gdx.gl20.glGenerateMipmap(0x8C1A);
                }
                if(z1) {
                    pixmap2.dispose();
                }
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public int getDepth() {
        return this.depth;
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public int getGLType() {
        return Format.toGlType(this.format);
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public int getHeight() {
        return this.textureDatas[0].getHeight();
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public int getInternalFormat() {
        return Format.toGlFormat(this.format);
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public int getWidth() {
        return this.textureDatas[0].getWidth();
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public boolean isManaged() {
        TextureData[] arr_textureData = this.textureDatas;
        for(int v = 0; v < arr_textureData.length; ++v) {
            if(!arr_textureData[v].isManaged()) {
                return false;
            }
        }
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public boolean isPrepared() {
        return this.prepared;
    }

    @Override  // com.badlogic.gdx.graphics.TextureArrayData
    public void prepare() {
        TextureData[] arr_textureData = this.textureDatas;
        int v1 = -1;
        int v2 = -1;
        for(int v = 0; v < arr_textureData.length; ++v) {
            TextureData textureData0 = arr_textureData[v];
            textureData0.prepare();
            if(v1 == -1) {
                v1 = textureData0.getWidth();
                v2 = textureData0.getHeight();
            }
            else if(v1 != textureData0.getWidth() || v2 != textureData0.getHeight()) {
                throw new GdxRuntimeException("Error whilst preparing TextureArray: TextureArray Textures must have equal dimensions.");
            }
        }
        this.prepared = true;
    }
}

