package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.MipMapGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import java.nio.FloatBuffer;

public abstract class GLTexture implements Disposable {
    protected float anisotropicFilterLevel;
    protected int glHandle;
    public final int glTarget;
    protected TextureFilter magFilter;
    private static float maxAnisotropicFilterLevel;
    protected TextureFilter minFilter;
    protected TextureWrap uWrap;
    protected TextureWrap vWrap;

    static {
    }

    public GLTexture(int v) {
        this(v, Gdx.gl.glGenTexture());
    }

    public GLTexture(int v, int v1) {
        this.minFilter = TextureFilter.Nearest;
        this.magFilter = TextureFilter.Nearest;
        this.uWrap = TextureWrap.ClampToEdge;
        this.vWrap = TextureWrap.ClampToEdge;
        this.anisotropicFilterLevel = 1.0f;
        this.glTarget = v;
        this.glHandle = v1;
    }

    public void bind() {
        Gdx.gl.glBindTexture(this.glTarget, this.glHandle);
    }

    public void bind(int v) {
        Gdx.gl.glActiveTexture(v + 0x84C0);
        Gdx.gl.glBindTexture(this.glTarget, this.glHandle);
    }

    protected void delete() {
        if(this.glHandle != 0) {
            Gdx.gl.glDeleteTexture(this.glHandle);
            this.glHandle = 0;
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.delete();
    }

    public float getAnisotropicFilter() {
        return this.anisotropicFilterLevel;
    }

    public abstract int getDepth();

    public abstract int getHeight();

    public TextureFilter getMagFilter() {
        return this.magFilter;
    }

    public static float getMaxAnisotropicFilterLevel() {
        float f = GLTexture.maxAnisotropicFilterLevel;
        if(f > 0.0f) {
            return f;
        }
        if(Gdx.graphics.supportsExtension("GL_EXT_texture_filter_anisotropic")) {
            FloatBuffer floatBuffer0 = BufferUtils.newFloatBuffer(16);
            floatBuffer0.position(0);
            floatBuffer0.limit(floatBuffer0.capacity());
            Gdx.gl20.glGetFloatv(0x84FF, floatBuffer0);
            float f1 = floatBuffer0.get(0);
            GLTexture.maxAnisotropicFilterLevel = f1;
            return f1;
        }
        GLTexture.maxAnisotropicFilterLevel = 1.0f;
        return 1.0f;
    }

    public TextureFilter getMinFilter() {
        return this.minFilter;
    }

    public int getTextureObjectHandle() {
        return this.glHandle;
    }

    public TextureWrap getUWrap() {
        return this.uWrap;
    }

    public TextureWrap getVWrap() {
        return this.vWrap;
    }

    public abstract int getWidth();

    public abstract boolean isManaged();

    protected abstract void reload();

    public float setAnisotropicFilter(float f) {
        float f1 = GLTexture.getMaxAnisotropicFilterLevel();
        if(f1 == 1.0f) {
            return 1.0f;
        }
        float f2 = Math.min(f, f1);
        if(MathUtils.isEqual(f2, this.anisotropicFilterLevel, 0.1f)) {
            return f2;
        }
        this.bind();
        Gdx.gl20.glTexParameterf(0xDE1, 0x84FE, f2);
        this.anisotropicFilterLevel = f2;
        return f2;
    }

    public void setFilter(TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1) {
        this.minFilter = texture$TextureFilter0;
        this.magFilter = texture$TextureFilter1;
        this.bind();
        Gdx.gl.glTexParameteri(this.glTarget, 0x2801, texture$TextureFilter0.getGLEnum());
        Gdx.gl.glTexParameteri(this.glTarget, 0x2800, texture$TextureFilter1.getGLEnum());
    }

    public void setWrap(TextureWrap texture$TextureWrap0, TextureWrap texture$TextureWrap1) {
        this.uWrap = texture$TextureWrap0;
        this.vWrap = texture$TextureWrap1;
        this.bind();
        Gdx.gl.glTexParameteri(this.glTarget, 0x2802, texture$TextureWrap0.getGLEnum());
        Gdx.gl.glTexParameteri(this.glTarget, 0x2803, texture$TextureWrap1.getGLEnum());
    }

    public float unsafeSetAnisotropicFilter(float f) {
        return this.unsafeSetAnisotropicFilter(f, false);
    }

    public float unsafeSetAnisotropicFilter(float f, boolean z) {
        float f1 = GLTexture.getMaxAnisotropicFilterLevel();
        if(f1 == 1.0f) {
            return 1.0f;
        }
        float f2 = Math.min(f, f1);
        if(!z && MathUtils.isEqual(f2, this.anisotropicFilterLevel, 0.1f)) {
            return this.anisotropicFilterLevel;
        }
        Gdx.gl20.glTexParameterf(0xDE1, 0x84FE, f2);
        this.anisotropicFilterLevel = f2;
        return f2;
    }

    public void unsafeSetFilter(TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1) {
        this.unsafeSetFilter(texture$TextureFilter0, texture$TextureFilter1, false);
    }

    public void unsafeSetFilter(TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, boolean z) {
        if(texture$TextureFilter0 != null && (z || this.minFilter != texture$TextureFilter0)) {
            Gdx.gl.glTexParameteri(this.glTarget, 0x2801, texture$TextureFilter0.getGLEnum());
            this.minFilter = texture$TextureFilter0;
        }
        if(texture$TextureFilter1 != null && (z || this.magFilter != texture$TextureFilter1)) {
            Gdx.gl.glTexParameteri(this.glTarget, 0x2800, texture$TextureFilter1.getGLEnum());
            this.magFilter = texture$TextureFilter1;
        }
    }

    public void unsafeSetWrap(TextureWrap texture$TextureWrap0, TextureWrap texture$TextureWrap1) {
        this.unsafeSetWrap(texture$TextureWrap0, texture$TextureWrap1, false);
    }

    public void unsafeSetWrap(TextureWrap texture$TextureWrap0, TextureWrap texture$TextureWrap1, boolean z) {
        if(texture$TextureWrap0 != null && (z || this.uWrap != texture$TextureWrap0)) {
            Gdx.gl.glTexParameteri(this.glTarget, 0x2802, texture$TextureWrap0.getGLEnum());
            this.uWrap = texture$TextureWrap0;
        }
        if(texture$TextureWrap1 != null && (z || this.vWrap != texture$TextureWrap1)) {
            Gdx.gl.glTexParameteri(this.glTarget, 0x2803, texture$TextureWrap1.getGLEnum());
            this.vWrap = texture$TextureWrap1;
        }
    }

    protected static void uploadImageData(int v, TextureData textureData0) {
        GLTexture.uploadImageData(v, textureData0, 0);
    }

    public static void uploadImageData(int v, TextureData textureData0, int v1) {
        if(textureData0 == null) {
            return;
        }
        if(!textureData0.isPrepared()) {
            textureData0.prepare();
        }
        if(textureData0.getType() == TextureDataType.Custom) {
            textureData0.consumeCustomData(v);
            return;
        }
        Pixmap pixmap0 = textureData0.consumePixmap();
        boolean z = textureData0.disposePixmap();
        if(textureData0.getFormat() != pixmap0.getFormat()) {
            Pixmap pixmap1 = new Pixmap(pixmap0.getWidth(), pixmap0.getHeight(), textureData0.getFormat());
            pixmap1.setBlending(Blending.None);
            pixmap1.drawPixmap(pixmap0, 0, 0, 0, 0, pixmap0.getWidth(), pixmap0.getHeight());
            if(textureData0.disposePixmap()) {
                pixmap0.dispose();
            }
            pixmap0 = pixmap1;
            z = true;
        }
        Gdx.gl.glPixelStorei(0xCF5, 1);
        if(textureData0.useMipMaps()) {
            MipMapGenerator.generateMipMap(v, pixmap0, pixmap0.getWidth(), pixmap0.getHeight());
        }
        else {
            Gdx.gl.glTexImage2D(v, v1, pixmap0.getGLInternalFormat(), pixmap0.getWidth(), pixmap0.getHeight(), 0, pixmap0.getGLFormat(), pixmap0.getGLType(), pixmap0.getPixels());
        }
        if(z) {
            pixmap0.dispose();
        }
    }
}

