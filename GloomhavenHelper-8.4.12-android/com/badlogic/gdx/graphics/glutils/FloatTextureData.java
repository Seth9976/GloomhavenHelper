package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.FloatBuffer;

public class FloatTextureData implements TextureData {
    FloatBuffer buffer;
    int format;
    int height;
    int internalFormat;
    boolean isGpuOnly;
    boolean isPrepared;
    int type;
    int width;

    public FloatTextureData(int v, int v1, int v2, int v3, int v4, boolean z) {
        this.isPrepared = false;
        this.width = v;
        this.height = v1;
        this.internalFormat = v2;
        this.format = v3;
        this.type = v4;
        this.isGpuOnly = z;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int v) {
        if(Gdx.app.getType() != ApplicationType.Android && Gdx.app.getType() != ApplicationType.iOS && Gdx.app.getType() != ApplicationType.WebGL) {
            if(!Gdx.graphics.isGL30Available() && !Gdx.graphics.supportsExtension("GL_ARB_texture_float")) {
                throw new GdxRuntimeException("Extension GL_ARB_texture_float not supported!");
            }
            Gdx.gl.glTexImage2D(v, 0, this.internalFormat, this.width, this.height, 0, this.format, 0x1406, this.buffer);
            return;
        }
        if(!Gdx.graphics.supportsExtension("OES_texture_float")) {
            throw new GdxRuntimeException("Extension OES_texture_float not supported!");
        }
        Gdx.gl.glTexImage2D(v, 0, 6408, this.width, this.height, 0, 6408, 0x1406, this.buffer);
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    public FloatBuffer getBuffer() {
        return this.buffer;
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
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return this.isPrepared;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
        int v1;
        int v = 4;
        if(this.isPrepared) {
            throw new GdxRuntimeException("Already prepared");
        }
        if(!this.isGpuOnly) {
            if(Gdx.graphics.getGLVersion().getType().equals(Type.OpenGL)) {
                if(this.internalFormat == 0x8815 || this.internalFormat == 0x881B) {
                    v = 3;
                }
                v1 = this.internalFormat == 0x822F || this.internalFormat == 0x8230 ? 2 : v;
                if(this.internalFormat == 0x822D || this.internalFormat == 0x822E) {
                    v1 = 1;
                }
            }
            else {
                v1 = 4;
            }
            this.buffer = BufferUtils.newFloatBuffer(this.width * this.height * v1);
        }
        this.isPrepared = true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return false;
    }
}

