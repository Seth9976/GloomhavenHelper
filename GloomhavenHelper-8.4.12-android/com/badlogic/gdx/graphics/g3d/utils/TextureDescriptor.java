package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public class TextureDescriptor implements Comparable {
    public TextureFilter magFilter;
    public TextureFilter minFilter;
    public GLTexture texture;
    public TextureWrap uWrap;
    public TextureWrap vWrap;

    public TextureDescriptor() {
        this.texture = null;
    }

    public TextureDescriptor(GLTexture gLTexture0) {
        this(gLTexture0, null, null, null, null);
    }

    public TextureDescriptor(GLTexture gLTexture0, TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, TextureWrap texture$TextureWrap0, TextureWrap texture$TextureWrap1) {
        this.texture = null;
        this.set(gLTexture0, texture$TextureFilter0, texture$TextureFilter1, texture$TextureWrap0, texture$TextureWrap1);
    }

    public int compareTo(TextureDescriptor textureDescriptor0) {
        int v = 0;
        if(textureDescriptor0 == this) {
            return 0;
        }
        int v1 = this.texture == null ? 0 : this.texture.glTarget;
        int v2 = textureDescriptor0.texture == null ? 0 : textureDescriptor0.texture.glTarget;
        if(v1 != v2) {
            return v1 - v2;
        }
        int v3 = this.texture == null ? 0 : this.texture.getTextureObjectHandle();
        int v4 = textureDescriptor0.texture == null ? 0 : textureDescriptor0.texture.getTextureObjectHandle();
        if(v3 != v4) {
            return v3 - v4;
        }
        TextureFilter texture$TextureFilter0 = this.minFilter;
        if(texture$TextureFilter0 != textureDescriptor0.minFilter) {
            int v5 = texture$TextureFilter0 == null ? 0 : texture$TextureFilter0.getGLEnum();
            TextureFilter texture$TextureFilter1 = textureDescriptor0.minFilter;
            if(texture$TextureFilter1 != null) {
                v = texture$TextureFilter1.getGLEnum();
            }
            return v5 - v;
        }
        TextureFilter texture$TextureFilter2 = this.magFilter;
        if(texture$TextureFilter2 != textureDescriptor0.magFilter) {
            int v6 = texture$TextureFilter2 == null ? 0 : texture$TextureFilter2.getGLEnum();
            TextureFilter texture$TextureFilter3 = textureDescriptor0.magFilter;
            if(texture$TextureFilter3 != null) {
                v = texture$TextureFilter3.getGLEnum();
            }
            return v6 - v;
        }
        TextureWrap texture$TextureWrap0 = this.uWrap;
        if(texture$TextureWrap0 != textureDescriptor0.uWrap) {
            int v7 = texture$TextureWrap0 == null ? 0 : texture$TextureWrap0.getGLEnum();
            TextureWrap texture$TextureWrap1 = textureDescriptor0.uWrap;
            if(texture$TextureWrap1 != null) {
                v = texture$TextureWrap1.getGLEnum();
            }
            return v7 - v;
        }
        TextureWrap texture$TextureWrap2 = this.vWrap;
        if(texture$TextureWrap2 != textureDescriptor0.vWrap) {
            int v8 = texture$TextureWrap2 == null ? 0 : texture$TextureWrap2.getGLEnum();
            TextureWrap texture$TextureWrap3 = textureDescriptor0.vWrap;
            if(texture$TextureWrap3 != null) {
                v = texture$TextureWrap3.getGLEnum();
            }
            return v8 - v;
        }
        return 0;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((TextureDescriptor)object0));
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        return object0 instanceof TextureDescriptor ? ((TextureDescriptor)object0).texture == this.texture && ((TextureDescriptor)object0).minFilter == this.minFilter && ((TextureDescriptor)object0).magFilter == this.magFilter && ((TextureDescriptor)object0).uWrap == this.uWrap && ((TextureDescriptor)object0).vWrap == this.vWrap : false;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.texture == null ? 0 : this.texture.glTarget;
        int v2 = this.texture == null ? 0 : this.texture.getTextureObjectHandle();
        int v3 = this.minFilter == null ? 0 : this.minFilter.getGLEnum();
        int v4 = this.magFilter == null ? 0 : this.magFilter.getGLEnum();
        int v5 = this.uWrap == null ? 0 : this.uWrap.getGLEnum();
        TextureWrap texture$TextureWrap0 = this.vWrap;
        if(texture$TextureWrap0 != null) {
            v = texture$TextureWrap0.getGLEnum();
        }
        long v6 = ((((((long)v1) * 811L + ((long)v2)) * 811L + ((long)v3)) * 811L + ((long)v4)) * 811L + ((long)v5)) * 811L + ((long)v);
        return (int)(v6 >> 0x20 ^ v6);
    }

    public void set(GLTexture gLTexture0, TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, TextureWrap texture$TextureWrap0, TextureWrap texture$TextureWrap1) {
        this.texture = gLTexture0;
        this.minFilter = texture$TextureFilter0;
        this.magFilter = texture$TextureFilter1;
        this.uWrap = texture$TextureWrap0;
        this.vWrap = texture$TextureWrap1;
    }

    public void set(TextureDescriptor textureDescriptor0) {
        this.texture = textureDescriptor0.texture;
        this.minFilter = textureDescriptor0.minFilter;
        this.magFilter = textureDescriptor0.magFilter;
        this.uWrap = textureDescriptor0.uWrap;
        this.vWrap = textureDescriptor0.vWrap;
    }
}

