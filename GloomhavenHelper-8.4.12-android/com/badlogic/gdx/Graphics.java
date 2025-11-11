package com.badlogic.gdx;

import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.GLVersion;

public interface Graphics {
    public static class BufferFormat {
        public final int a;
        public final int b;
        public final boolean coverageSampling;
        public final int depth;
        public final int g;
        public final int r;
        public final int samples;
        public final int stencil;

        public BufferFormat(int v, int v1, int v2, int v3, int v4, int v5, int v6, boolean z) {
            this.r = v;
            this.g = v1;
            this.b = v2;
            this.a = v3;
            this.depth = v4;
            this.stencil = v5;
            this.samples = v6;
            this.coverageSampling = z;
        }

        @Override
        public String toString() {
            return "r: " + this.r + ", g: " + this.g + ", b: " + this.b + ", a: " + this.a + ", depth: " + this.depth + ", stencil: " + this.stencil + ", num samples: " + this.samples + ", coverage sampling: " + this.coverageSampling;
        }
    }

    public static class DisplayMode {
        public final int bitsPerPixel;
        public final int height;
        public final int refreshRate;
        public final int width;

        protected DisplayMode(int v, int v1, int v2, int v3) {
            this.width = v;
            this.height = v1;
            this.refreshRate = v2;
            this.bitsPerPixel = v3;
        }

        @Override
        public String toString() {
            return this.width + "x" + this.height + ", bpp: " + this.bitsPerPixel + ", hz: " + this.refreshRate;
        }
    }

    public static enum GraphicsType {
        AndroidGL,
        LWJGL,
        WebGL,
        iOSGL,
        JGLFW,
        Mock,
        LWJGL3;

    }

    public static class Monitor {
        public final String name;
        public final int virtualX;
        public final int virtualY;

        protected Monitor(int v, int v1, String s) {
            this.virtualX = v;
            this.virtualY = v1;
            this.name = s;
        }
    }

    int getBackBufferHeight();

    float getBackBufferScale();

    int getBackBufferWidth();

    BufferFormat getBufferFormat();

    float getDeltaTime();

    float getDensity();

    DisplayMode getDisplayMode();

    DisplayMode getDisplayMode(Monitor arg1);

    DisplayMode[] getDisplayModes();

    DisplayMode[] getDisplayModes(Monitor arg1);

    long getFrameId();

    int getFramesPerSecond();

    GL20 getGL20();

    GL30 getGL30();

    GLVersion getGLVersion();

    int getHeight();

    Monitor getMonitor();

    Monitor[] getMonitors();

    float getPpcX();

    float getPpcY();

    float getPpiX();

    float getPpiY();

    Monitor getPrimaryMonitor();

    @Deprecated
    float getRawDeltaTime();

    int getSafeInsetBottom();

    int getSafeInsetLeft();

    int getSafeInsetRight();

    int getSafeInsetTop();

    GraphicsType getType();

    int getWidth();

    boolean isContinuousRendering();

    boolean isFullscreen();

    boolean isGL30Available();

    Cursor newCursor(Pixmap arg1, int arg2, int arg3);

    void requestRendering();

    void setContinuousRendering(boolean arg1);

    void setCursor(Cursor arg1);

    void setForegroundFPS(int arg1);

    boolean setFullscreenMode(DisplayMode arg1);

    void setGL20(GL20 arg1);

    void setGL30(GL30 arg1);

    void setResizable(boolean arg1);

    void setSystemCursor(SystemCursor arg1);

    void setTitle(String arg1);

    void setUndecorated(boolean arg1);

    void setVSync(boolean arg1);

    boolean setWindowedMode(int arg1, int arg2);

    boolean supportsDisplayModeChange();

    boolean supportsExtension(String arg1);
}

