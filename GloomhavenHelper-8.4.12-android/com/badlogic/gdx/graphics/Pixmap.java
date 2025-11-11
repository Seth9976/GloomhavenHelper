package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Pixmap implements Disposable {
    public static enum Blending {
        None,
        SourceOver;

    }

    public interface DownloadPixmapResponseListener {
        void downloadComplete(Pixmap arg1);

        void downloadFailed(Throwable arg1);
    }

    public static enum Filter {
        NearestNeighbour,
        BiLinear;

    }

    public static enum Format {
        Alpha,
        Intensity,
        LuminanceAlpha,
        RGB565,
        RGBA4444,
        RGB888,
        RGBA8888;

        public static Format fromGdx2DPixmapFormat(int v) {
            switch(v) {
                case 1: {
                    return Format.Alpha;
                }
                case 2: {
                    return Format.LuminanceAlpha;
                }
                case 3: {
                    return Format.RGB888;
                }
                case 4: {
                    return Format.RGBA8888;
                }
                case 5: {
                    return Format.RGB565;
                }
                case 6: {
                    return Format.RGBA4444;
                }
                default: {
                    throw new GdxRuntimeException("Unknown Gdx2DPixmap Format: " + v);
                }
            }
        }

        public static int toGdx2DPixmapFormat(Format pixmap$Format0) {
            if(pixmap$Format0 == Format.Alpha) {
                return 1;
            }
            if(pixmap$Format0 == Format.Intensity) {
                return 1;
            }
            if(pixmap$Format0 == Format.LuminanceAlpha) {
                return 2;
            }
            if(pixmap$Format0 == Format.RGB565) {
                return 5;
            }
            if(pixmap$Format0 == Format.RGBA4444) {
                return 6;
            }
            if(pixmap$Format0 == Format.RGB888) {
                return 3;
            }
            if(pixmap$Format0 != Format.RGBA8888) {
                throw new GdxRuntimeException("Unknown Format: " + pixmap$Format0);
            }
            return 4;
        }

        public static int toGlFormat(Format pixmap$Format0) {
            return Gdx2DPixmap.toGlFormat(Format.toGdx2DPixmapFormat(pixmap$Format0));
        }

        public static int toGlType(Format pixmap$Format0) {
            return Gdx2DPixmap.toGlType(Format.toGdx2DPixmapFormat(pixmap$Format0));
        }
    }

    private Blending blending;
    int color;
    private boolean disposed;
    private Filter filter;
    final Gdx2DPixmap pixmap;

    public Pixmap(int v, int v1, Format pixmap$Format0) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        this.pixmap = new Gdx2DPixmap(v, v1, Format.toGdx2DPixmapFormat(pixmap$Format0));
        this.setColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.fill();
    }

    public Pixmap(FileHandle fileHandle0) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        try {
            byte[] arr_b = fileHandle0.readBytes();
            this.pixmap = new Gdx2DPixmap(arr_b, 0, arr_b.length, 0);
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Couldn\'t load file: " + fileHandle0, exception0);
        }
    }

    public Pixmap(Gdx2DPixmap gdx2DPixmap0) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        this.pixmap = gdx2DPixmap0;
    }

    public Pixmap(byte[] arr_b, int v, int v1) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        try {
            this.pixmap = new Gdx2DPixmap(arr_b, v, v1, 0);
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Couldn\'t load pixmap from image data", iOException0);
        }
    }

    public static Pixmap createFromFrameBuffer(int v, int v1, int v2, int v3) {
        Gdx.gl.glPixelStorei(0xD05, 1);
        Pixmap pixmap0 = new Pixmap(v2, v3, Format.RGBA8888);
        ByteBuffer byteBuffer0 = pixmap0.getPixels();
        Gdx.gl.glReadPixels(v, v1, v2, v3, 6408, 0x1401, byteBuffer0);
        return pixmap0;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if(this.disposed) {
            throw new GdxRuntimeException("Pixmap already disposed!");
        }
        this.pixmap.dispose();
        this.disposed = true;
    }

    public static void downloadFromUrl(String s, DownloadPixmapResponseListener pixmap$DownloadPixmapResponseListener0) {
        HttpRequest net$HttpRequest0 = new HttpRequest("GET");
        net$HttpRequest0.setUrl(s);
        Gdx.net.sendHttpRequest(net$HttpRequest0, new HttpResponseListener() {
            @Override  // com.badlogic.gdx.Net$HttpResponseListener
            public void cancelled() {
            }

            @Override  // com.badlogic.gdx.Net$HttpResponseListener
            public void failed(Throwable throwable0) {
                pixmap$DownloadPixmapResponseListener0.downloadFailed(throwable0);
            }

            @Override  // com.badlogic.gdx.Net$HttpResponseListener
            public void handleHttpResponse(HttpResponse net$HttpResponse0) {
                byte[] arr_b = net$HttpResponse0.getResult();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Pixmap pixmap0 = new Pixmap(arr_b, 0, arr_b.length);
                            com.badlogic.gdx.graphics.Pixmap.1.this.val$responseListener.downloadComplete(pixmap0);
                        }
                        catch(Throwable throwable0) {
                            com.badlogic.gdx.graphics.Pixmap.1.this.failed(throwable0);
                        }
                    }
                });
            }
        });
    }

    public void drawCircle(int v, int v1, int v2) {
        this.pixmap.drawCircle(v, v1, v2, this.color);
    }

    public void drawLine(int v, int v1, int v2, int v3) {
        this.pixmap.drawLine(v, v1, v2, v3, this.color);
    }

    public void drawPixel(int v, int v1) {
        this.pixmap.setPixel(v, v1, this.color);
    }

    public void drawPixel(int v, int v1, int v2) {
        this.pixmap.setPixel(v, v1, v2);
    }

    public void drawPixmap(Pixmap pixmap0, int v, int v1) {
        this.drawPixmap(pixmap0, v, v1, 0, 0, pixmap0.getWidth(), pixmap0.getHeight());
    }

    public void drawPixmap(Pixmap pixmap0, int v, int v1, int v2, int v3, int v4, int v5) {
        this.pixmap.drawPixmap(pixmap0.pixmap, v2, v3, v, v1, v4, v5);
    }

    public void drawPixmap(Pixmap pixmap0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        this.pixmap.drawPixmap(pixmap0.pixmap, v, v1, v2, v3, v4, v5, v6, v7);
    }

    public void drawRectangle(int v, int v1, int v2, int v3) {
        this.pixmap.drawRect(v, v1, v2, v3, this.color);
    }

    public void fill() {
        this.pixmap.clear(this.color);
    }

    public void fillCircle(int v, int v1, int v2) {
        this.pixmap.fillCircle(v, v1, v2, this.color);
    }

    public void fillRectangle(int v, int v1, int v2, int v3) {
        this.pixmap.fillRect(v, v1, v2, v3, this.color);
    }

    public void fillTriangle(int v, int v1, int v2, int v3, int v4, int v5) {
        this.pixmap.fillTriangle(v, v1, v2, v3, v4, v5, this.color);
    }

    public Blending getBlending() {
        return this.blending;
    }

    public Filter getFilter() {
        return this.filter;
    }

    public Format getFormat() {
        return Format.fromGdx2DPixmapFormat(this.pixmap.getFormat());
    }

    public int getGLFormat() {
        return this.pixmap.getGLFormat();
    }

    public int getGLInternalFormat() {
        return this.pixmap.getGLInternalFormat();
    }

    public int getGLType() {
        return this.pixmap.getGLType();
    }

    public int getHeight() {
        return this.pixmap.getHeight();
    }

    public int getPixel(int v, int v1) {
        return this.pixmap.getPixel(v, v1);
    }

    public ByteBuffer getPixels() {
        if(this.disposed) {
            throw new GdxRuntimeException("Pixmap already disposed");
        }
        return this.pixmap.getPixels();
    }

    public int getWidth() {
        return this.pixmap.getWidth();
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public void setBlending(Blending pixmap$Blending0) {
        this.blending = pixmap$Blending0;
        this.pixmap.setBlend((pixmap$Blending0 == Blending.None ? 0 : 1));
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color = Color.rgba8888(f, f1, f2, f3);
    }

    public void setColor(int v) {
        this.color = v;
    }

    public void setColor(Color color0) {
        this.color = Color.rgba8888(color0.r, color0.g, color0.b, color0.a);
    }

    public void setFilter(Filter pixmap$Filter0) {
        this.filter = pixmap$Filter0;
        this.pixmap.setScale((pixmap$Filter0 == Filter.NearestNeighbour ? 0 : 1));
    }

    public void setPixels(ByteBuffer byteBuffer0) {
        ByteBuffer byteBuffer1 = this.pixmap.getPixels();
        BufferUtils.copy(byteBuffer0, byteBuffer1, byteBuffer1.limit());
    }
}

