package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;

public class HdpiUtils {
    private static HdpiMode mode;

    static {
        HdpiUtils.mode = HdpiMode.Logical;
    }

    public static void glScissor(int v, int v1, int v2, int v3) {
        if(HdpiUtils.mode == HdpiMode.Logical && (Gdx.graphics.getWidth() != Gdx.graphics.getBackBufferWidth() || Gdx.graphics.getHeight() != Gdx.graphics.getBackBufferHeight())) {
            Gdx.gl.glScissor(HdpiUtils.toBackBufferX(v), HdpiUtils.toBackBufferY(v1), HdpiUtils.toBackBufferX(v2), HdpiUtils.toBackBufferY(v3));
            return;
        }
        Gdx.gl.glScissor(v, v1, v2, v3);
    }

    public static void glViewport(int v, int v1, int v2, int v3) {
        if(HdpiUtils.mode == HdpiMode.Logical && (Gdx.graphics.getWidth() != Gdx.graphics.getBackBufferWidth() || Gdx.graphics.getHeight() != Gdx.graphics.getBackBufferHeight())) {
            Gdx.gl.glViewport(HdpiUtils.toBackBufferX(v), HdpiUtils.toBackBufferY(v1), HdpiUtils.toBackBufferX(v2), HdpiUtils.toBackBufferY(v3));
            return;
        }
        Gdx.gl.glViewport(v, v1, v2, v3);
    }

    public static void setMode(HdpiMode hdpiMode0) {
        HdpiUtils.mode = hdpiMode0;
    }

    public static int toBackBufferX(int v) {
        return (int)(((float)(v * Gdx.graphics.getBackBufferWidth())) / ((float)Gdx.graphics.getWidth()));
    }

    public static int toBackBufferY(int v) {
        return (int)(((float)(v * Gdx.graphics.getBackBufferHeight())) / ((float)Gdx.graphics.getHeight()));
    }

    public static int toLogicalX(int v) {
        return (int)(((float)(v * Gdx.graphics.getWidth())) / ((float)Gdx.graphics.getBackBufferWidth()));
    }

    public static int toLogicalY(int v) {
        return (int)(((float)(v * Gdx.graphics.getHeight())) / ((float)Gdx.graphics.getBackBufferHeight()));
    }
}

