package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Gdx2DPixmap implements Disposable {
    public static final int GDX2D_BLEND_NONE = 0;
    public static final int GDX2D_BLEND_SRC_OVER = 1;
    public static final int GDX2D_FORMAT_ALPHA = 1;
    public static final int GDX2D_FORMAT_LUMINANCE_ALPHA = 2;
    public static final int GDX2D_FORMAT_RGB565 = 5;
    public static final int GDX2D_FORMAT_RGB888 = 3;
    public static final int GDX2D_FORMAT_RGBA4444 = 6;
    public static final int GDX2D_FORMAT_RGBA8888 = 4;
    public static final int GDX2D_SCALE_LINEAR = 1;
    public static final int GDX2D_SCALE_NEAREST;
    long basePtr;
    int format;
    int height;
    long[] nativeData;
    ByteBuffer pixelPtr;
    int width;

    public Gdx2DPixmap(int v, int v1, int v2) throws GdxRuntimeException {
        this.nativeData = new long[4];
        this.pixelPtr = Gdx2DPixmap.newPixmap(this.nativeData, v, v1, v2);
        if(this.pixelPtr == null) {
            throw new GdxRuntimeException("Unable to allocate memory for pixmap: " + v + "x" + v1 + ", " + Gdx2DPixmap.getFormatString(v2));
        }
        long[] arr_v = this.nativeData;
        this.basePtr = arr_v[0];
        this.width = (int)arr_v[1];
        this.height = (int)arr_v[2];
        this.format = (int)arr_v[3];
    }

    public Gdx2DPixmap(InputStream inputStream0, int v) throws IOException {
        this.nativeData = new long[4];
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(0x400);
        byte[] arr_b = new byte[0x400];
        int v1;
        while((v1 = inputStream0.read(arr_b)) != -1) {
            byteArrayOutputStream0.write(arr_b, 0, v1);
        }
        byte[] arr_b1 = byteArrayOutputStream0.toByteArray();
        this.pixelPtr = Gdx2DPixmap.load(this.nativeData, arr_b1, 0, arr_b1.length);
        if(this.pixelPtr == null) {
            throw new IOException("Error loading pixmap: " + Gdx2DPixmap.getFailureReason());
        }
        long[] arr_v = this.nativeData;
        this.basePtr = arr_v[0];
        this.width = (int)arr_v[1];
        this.height = (int)arr_v[2];
        this.format = (int)arr_v[3];
        if(v != 0 && v != this.format) {
            this.convert(v);
        }
    }

    public Gdx2DPixmap(ByteBuffer byteBuffer0, long[] arr_v) {
        this.nativeData = new long[4];
        this.pixelPtr = byteBuffer0;
        this.basePtr = arr_v[0];
        this.width = (int)arr_v[1];
        this.height = (int)arr_v[2];
        this.format = (int)arr_v[3];
    }

    public Gdx2DPixmap(byte[] arr_b, int v, int v1, int v2) throws IOException {
        this.nativeData = new long[4];
        this.pixelPtr = Gdx2DPixmap.load(this.nativeData, arr_b, v, v1);
        if(this.pixelPtr == null) {
            throw new IOException("Error loading pixmap: " + Gdx2DPixmap.getFailureReason());
        }
        long[] arr_v = this.nativeData;
        this.basePtr = arr_v[0];
        this.width = (int)arr_v[1];
        this.height = (int)arr_v[2];
        this.format = (int)arr_v[3];
        if(v2 != 0 && v2 != this.format) {
            this.convert(v2);
        }
    }

    private static native void clear(long arg0, int arg1) {
    }

    public void clear(int v) {
        Gdx2DPixmap.clear(this.basePtr, v);
    }

    private void convert(int v) {
        Gdx2DPixmap gdx2DPixmap0 = new Gdx2DPixmap(this.width, this.height, v);
        gdx2DPixmap0.setBlend(0);
        gdx2DPixmap0.drawPixmap(this, 0, 0, 0, 0, this.width, this.height);
        this.dispose();
        this.basePtr = gdx2DPixmap0.basePtr;
        this.format = gdx2DPixmap0.format;
        this.height = gdx2DPixmap0.height;
        this.nativeData = gdx2DPixmap0.nativeData;
        this.pixelPtr = gdx2DPixmap0.pixelPtr;
        this.width = gdx2DPixmap0.width;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Gdx2DPixmap.free(this.basePtr);
    }

    private static native void drawCircle(long arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    public void drawCircle(int v, int v1, int v2, int v3) {
        Gdx2DPixmap.drawCircle(this.basePtr, v, v1, v2, v3);
    }

    private static native void drawLine(long arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    }

    public void drawLine(int v, int v1, int v2, int v3, int v4) {
        Gdx2DPixmap.drawLine(this.basePtr, v, v1, v2, v3, v4);
    }

    private static native void drawPixmap(long arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
    }

    public void drawPixmap(Gdx2DPixmap gdx2DPixmap0, int v, int v1, int v2, int v3, int v4, int v5) {
        Gdx2DPixmap.drawPixmap(gdx2DPixmap0.basePtr, this.basePtr, v, v1, v4, v5, v2, v3, v4, v5);
    }

    public void drawPixmap(Gdx2DPixmap gdx2DPixmap0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        Gdx2DPixmap.drawPixmap(gdx2DPixmap0.basePtr, this.basePtr, v, v1, v2, v3, v4, v5, v6, v7);
    }

    private static native void drawRect(long arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    }

    public void drawRect(int v, int v1, int v2, int v3, int v4) {
        Gdx2DPixmap.drawRect(this.basePtr, v, v1, v2, v3, v4);
    }

    private static native void fillCircle(long arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    public void fillCircle(int v, int v1, int v2, int v3) {
        Gdx2DPixmap.fillCircle(this.basePtr, v, v1, v2, v3);
    }

    private static native void fillRect(long arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    }

    public void fillRect(int v, int v1, int v2, int v3, int v4) {
        Gdx2DPixmap.fillRect(this.basePtr, v, v1, v2, v3, v4);
    }

    private static native void fillTriangle(long arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
    }

    public void fillTriangle(int v, int v1, int v2, int v3, int v4, int v5, int v6) {
        Gdx2DPixmap.fillTriangle(this.basePtr, v, v1, v2, v3, v4, v5, v6);
    }

    private static native void free(long arg0) {
    }

    public static native String getFailureReason() {
    }

    public int getFormat() {
        return this.format;
    }

    private static String getFormatString(int v) {
        switch(v) {
            case 1: {
                return "alpha";
            }
            case 2: {
                return "luminance alpha";
            }
            case 3: {
                return "rgb888";
            }
            case 4: {
                return "rgba8888";
            }
            case 5: {
                return "rgb565";
            }
            case 6: {
                return "rgba4444";
            }
            default: {
                return "unknown";
            }
        }
    }

    public String getFormatString() {
        return Gdx2DPixmap.getFormatString(this.format);
    }

    public int getGLFormat() {
        return this.getGLInternalFormat();
    }

    public int getGLInternalFormat() {
        return Gdx2DPixmap.toGlFormat(this.format);
    }

    public int getGLType() {
        return Gdx2DPixmap.toGlType(this.format);
    }

    public int getHeight() {
        return this.height;
    }

    private static native int getPixel(long arg0, int arg1, int arg2) {
    }

    public int getPixel(int v, int v1) {
        return Gdx2DPixmap.getPixel(this.basePtr, v, v1);
    }

    public ByteBuffer getPixels() {
        return this.pixelPtr;
    }

    public int getWidth() {
        return this.width;
    }

    private static native ByteBuffer load(long[] arg0, byte[] arg1, int arg2, int arg3) {
    }

    public static Gdx2DPixmap newPixmap(int v, int v1, int v2) {
        try {
            return new Gdx2DPixmap(v, v1, v2);
        }
        catch(IllegalArgumentException unused_ex) {
            return null;
        }
    }

    public static Gdx2DPixmap newPixmap(InputStream inputStream0, int v) {
        try {
            return new Gdx2DPixmap(inputStream0, v);
        }
        catch(IOException unused_ex) {
            return null;
        }
    }

    private static native ByteBuffer newPixmap(long[] arg0, int arg1, int arg2, int arg3) {
    }

    private static native void setBlend(long arg0, int arg1) {
    }

    public void setBlend(int v) {
        Gdx2DPixmap.setBlend(this.basePtr, v);
    }

    private static native void setPixel(long arg0, int arg1, int arg2, int arg3) {
    }

    public void setPixel(int v, int v1, int v2) {
        Gdx2DPixmap.setPixel(this.basePtr, v, v1, v2);
    }

    private static native void setScale(long arg0, int arg1) {
    }

    public void setScale(int v) {
        Gdx2DPixmap.setScale(this.basePtr, v);
    }

    public static int toGlFormat(int v) {
        switch(v) {
            case 1: {
                return 6406;
            }
            case 2: {
                return 6410;
            }
            case 3: 
            case 5: {
                return 6407;
            }
            case 4: 
            case 6: {
                return 6408;
            }
            default: {
                throw new GdxRuntimeException("unknown format: " + v);
            }
        }
    }

    public static int toGlType(int v) {
        switch(v) {
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                return 0x1401;
            }
            case 5: {
                return 0x8363;
            }
            case 6: {
                return 0x8033;
            }
            default: {
                throw new GdxRuntimeException("unknown format: " + v);
            }
        }
    }
}

