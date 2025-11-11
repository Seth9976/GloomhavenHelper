package com.badlogic.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import java.nio.ByteBuffer;

public final class ScreenUtils {
    public static void clear(float f, float f1, float f2, float f3) {
        ScreenUtils.clear(f, f1, f2, f3, false);
    }

    public static void clear(float f, float f1, float f2, float f3, boolean z) {
        Gdx.gl.glClearColor(f, f1, f2, f3);
        Gdx.gl.glClear((z ? 0x4100 : 0x4000));
    }

    public static void clear(Color color0) {
        ScreenUtils.clear(color0.r, color0.g, color0.b, color0.a, false);
    }

    public static void clear(Color color0, boolean z) {
        ScreenUtils.clear(color0.r, color0.g, color0.b, color0.a, z);
    }

    public static byte[] getFrameBufferPixels(int v, int v1, int v2, int v3, boolean z) {
        Gdx.gl.glPixelStorei(0xD05, 1);
        int v4 = v2 * v3 * 4;
        ByteBuffer byteBuffer0 = BufferUtils.newByteBuffer(v4);
        Gdx.gl.glReadPixels(v, v1, v2, v3, 6408, 0x1401, byteBuffer0);
        byte[] arr_b = new byte[v4];
        if(z) {
            for(int v5 = 0; v5 < v3; ++v5) {
                byteBuffer0.position((v3 - v5 - 1) * (v2 * 4));
                byteBuffer0.get(arr_b, v5 * (v2 * 4), v2 * 4);
            }
            return arr_b;
        }
        byteBuffer0.clear();
        byteBuffer0.get(arr_b);
        return arr_b;
    }

    public static byte[] getFrameBufferPixels(boolean z) {
        return ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), z);
    }

    @Deprecated
    public static Pixmap getFrameBufferPixmap(int v, int v1, int v2, int v3) {
        return Pixmap.createFromFrameBuffer(v, v1, v2, v3);
    }

    public static TextureRegion getFrameBufferTexture() {
        return ScreenUtils.getFrameBufferTexture(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    }

    public static TextureRegion getFrameBufferTexture(int v, int v1, int v2, int v3) {
        Pixmap pixmap0 = Pixmap.createFromFrameBuffer(v, v1, v2, v3);
        Pixmap pixmap1 = new Pixmap(MathUtils.nextPowerOfTwo(v2), MathUtils.nextPowerOfTwo(v3), Format.RGBA8888);
        pixmap1.setBlending(Blending.None);
        pixmap1.drawPixmap(pixmap0, 0, 0);
        TextureRegion textureRegion0 = new TextureRegion(new Texture(pixmap1), 0, v3, v2, -v3);
        pixmap1.dispose();
        pixmap0.dispose();
        return textureRegion0;
    }
}

