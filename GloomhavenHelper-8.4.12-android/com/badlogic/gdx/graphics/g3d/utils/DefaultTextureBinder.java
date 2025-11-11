package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.IntBuffer;

public final class DefaultTextureBinder implements TextureBinder {
    public static final int LRU = 1;
    public static final int MAX_GLES_UNITS = 0x20;
    public static final int ROUNDROBIN;
    private int bindCount;
    private final int count;
    private int currentTexture;
    private final int method;
    private final int offset;
    private int reuseCount;
    private boolean reused;
    private final TextureDescriptor tempDesc;
    private final GLTexture[] textures;
    private int[] unitsLRU;

    public DefaultTextureBinder(int v) {
        this(v, 0);
    }

    public DefaultTextureBinder(int v, int v1) {
        this(v, v1, -1);
    }

    public DefaultTextureBinder(int v, int v1, int v2) {
        this.reuseCount = 0;
        this.bindCount = 0;
        this.tempDesc = new TextureDescriptor();
        this.currentTexture = 0;
        int v3 = Math.min(DefaultTextureBinder.getMaxTextureUnits(), 0x20);
        if(v2 < 0) {
            v2 = v3 - v1;
        }
        if(v1 < 0 || v2 < 0 || v1 + v2 > v3) {
            throw new GdxRuntimeException("Illegal arguments");
        }
        this.method = v;
        this.offset = v1;
        this.count = v2;
        this.textures = new GLTexture[v2];
        this.unitsLRU = v == 1 ? new int[v2] : null;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public void begin() {
        for(int v = 0; v < this.count; ++v) {
            this.textures[v] = null;
            int[] arr_v = this.unitsLRU;
            if(arr_v != null) {
                arr_v[v] = v;
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int bind(GLTexture gLTexture0) {
        this.tempDesc.set(gLTexture0, null, null, null, null);
        return this.bindTexture(this.tempDesc, false);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int bind(TextureDescriptor textureDescriptor0) {
        return this.bindTexture(textureDescriptor0, false);
    }

    private final int bindTexture(TextureDescriptor textureDescriptor0, boolean z) {
        int v1;
        GLTexture gLTexture0 = textureDescriptor0.texture;
        this.reused = false;
        switch(this.method) {
            case 0: {
                int v = this.bindTextureRoundRobin(gLTexture0);
                v1 = this.offset + v;
                break;
            }
            case 1: {
                int v2 = this.bindTextureLRU(gLTexture0);
                v1 = this.offset + v2;
                break;
            }
            default: {
                return -1;
            }
        }
        ++this.bindCount;
        gLTexture0.unsafeSetWrap(textureDescriptor0.uWrap, textureDescriptor0.vWrap);
        gLTexture0.unsafeSetFilter(textureDescriptor0.minFilter, textureDescriptor0.magFilter);
        return v1;
    }

    private final int bindTextureLRU(GLTexture gLTexture0) {
        int v;
        for(v = 0; v < this.count; ++v) {
            int v1 = this.unitsLRU[v];
            GLTexture[] arr_gLTexture = this.textures;
            if(arr_gLTexture[v1] == gLTexture0) {
                this.reused = true;
                break;
            }
            if(arr_gLTexture[v1] == null) {
                break;
            }
        }
        int v2 = this.count;
        if(v >= v2) {
            v = v2 - 1;
        }
        int v3 = this.unitsLRU[v];
        while(v > 0) {
            this.unitsLRU[v] = this.unitsLRU[v - 1];
            --v;
        }
        this.unitsLRU[0] = v3;
        if(!this.reused) {
            this.textures[v3] = gLTexture0;
            gLTexture0.bind(this.offset + v3);
        }
        return v3;
    }

    private final int bindTextureRoundRobin(GLTexture gLTexture0) {
        int v1;
        for(int v = 0; true; ++v) {
            v1 = this.count;
            if(v >= v1) {
                break;
            }
            int v2 = (this.currentTexture + v) % v1;
            if(this.textures[v2] == gLTexture0) {
                this.reused = true;
                return v2;
            }
        }
        this.currentTexture = (this.currentTexture + 1) % v1;
        int v3 = this.currentTexture;
        this.textures[v3] = gLTexture0;
        gLTexture0.bind(this.offset + v3);
        return this.currentTexture;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public void end() {
        Gdx.gl.glActiveTexture(0x84C0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int getBindCount() {
        return this.bindCount;
    }

    private static int getMaxTextureUnits() {
        IntBuffer intBuffer0 = BufferUtils.newIntBuffer(16);
        Gdx.gl.glGetIntegerv(34930, intBuffer0);
        return intBuffer0.get(0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int getReuseCount() {
        return this.reuseCount;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final void resetCounts() {
        this.reuseCount = 0;
        this.bindCount = 0;
    }
}

