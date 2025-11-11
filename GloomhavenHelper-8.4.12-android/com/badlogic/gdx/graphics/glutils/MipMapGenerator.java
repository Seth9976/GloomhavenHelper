package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class MipMapGenerator {
    private static boolean useHWMipMap = true;

    static {
    }

    public static void generateMipMap(int v, Pixmap pixmap0, int v1, int v2) {
        if(!MipMapGenerator.useHWMipMap) {
            MipMapGenerator.generateMipMapCPU(v, pixmap0, v1, v2);
            return;
        }
        if(Gdx.app.getType() != ApplicationType.Android && Gdx.app.getType() != ApplicationType.WebGL && Gdx.app.getType() != ApplicationType.iOS) {
            MipMapGenerator.generateMipMapDesktop(v, pixmap0, v1, v2);
            return;
        }
        MipMapGenerator.generateMipMapGLES20(v, pixmap0);
    }

    public static void generateMipMap(Pixmap pixmap0, int v, int v1) {
        MipMapGenerator.generateMipMap(0xDE1, pixmap0, v, v1);
    }

    private static void generateMipMapCPU(int v, Pixmap pixmap0, int v1, int v2) {
        Gdx.gl.glTexImage2D(v, 0, pixmap0.getGLInternalFormat(), pixmap0.getWidth(), pixmap0.getHeight(), 0, pixmap0.getGLFormat(), pixmap0.getGLType(), pixmap0.getPixels());
        if(Gdx.gl20 == null && v1 != v2) {
            throw new GdxRuntimeException("texture width and height must be square when using mipmapping.");
        }
        int v3 = pixmap0.getWidth() / 2;
        int v4 = pixmap0.getHeight() / 2;
        Pixmap pixmap1 = pixmap0;
        for(int v5 = 1; v3 > 0 && v4 > 0; ++v5) {
            Pixmap pixmap2 = new Pixmap(v3, v4, pixmap1.getFormat());
            pixmap2.setBlending(Blending.None);
            pixmap2.drawPixmap(pixmap1, 0, 0, pixmap1.getWidth(), pixmap1.getHeight(), 0, 0, v3, v4);
            if(v5 > 1) {
                pixmap1.dispose();
            }
            pixmap1 = pixmap2;
            Gdx.gl.glTexImage2D(v, v5, pixmap2.getGLInternalFormat(), pixmap2.getWidth(), pixmap2.getHeight(), 0, pixmap2.getGLFormat(), pixmap2.getGLType(), pixmap2.getPixels());
            v3 = pixmap1.getWidth() / 2;
            v4 = pixmap1.getHeight() / 2;
        }
    }

    private static void generateMipMapDesktop(int v, Pixmap pixmap0, int v1, int v2) {
        if(!Gdx.graphics.supportsExtension("GL_ARB_framebuffer_object") && !Gdx.graphics.supportsExtension("GL_EXT_framebuffer_object") && Gdx.gl30 == null) {
            MipMapGenerator.generateMipMapCPU(v, pixmap0, v1, v2);
            return;
        }
        Gdx.gl.glTexImage2D(v, 0, pixmap0.getGLInternalFormat(), pixmap0.getWidth(), pixmap0.getHeight(), 0, pixmap0.getGLFormat(), pixmap0.getGLType(), pixmap0.getPixels());
        Gdx.gl20.glGenerateMipmap(v);
    }

    private static void generateMipMapGLES20(int v, Pixmap pixmap0) {
        Gdx.gl.glTexImage2D(v, 0, pixmap0.getGLInternalFormat(), pixmap0.getWidth(), pixmap0.getHeight(), 0, pixmap0.getGLFormat(), pixmap0.getGLType(), pixmap0.getPixels());
        Gdx.gl20.glGenerateMipmap(v);
    }

    public static void setUseHardwareMipMap(boolean z) {
        MipMapGenerator.useHWMipMap = z;
    }
}

