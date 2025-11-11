package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Disposable;

public interface Batch extends Disposable {
    public static final int C1 = 2;
    public static final int C2 = 7;
    public static final int C3 = 12;
    public static final int C4 = 17;
    public static final int U1 = 3;
    public static final int U2 = 8;
    public static final int U3 = 13;
    public static final int U4 = 18;
    public static final int V1 = 4;
    public static final int V2 = 9;
    public static final int V3 = 14;
    public static final int V4 = 19;
    public static final int X1 = 0;
    public static final int X2 = 5;
    public static final int X3 = 10;
    public static final int X4 = 15;
    public static final int Y1 = 1;
    public static final int Y2 = 6;
    public static final int Y3 = 11;
    public static final int Y4 = 16;

    void begin();

    void disableBlending();

    void draw(Texture arg1, float arg2, float arg3);

    void draw(Texture arg1, float arg2, float arg3, float arg4, float arg5);

    void draw(Texture arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9);

    void draw(Texture arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, int arg11, int arg12, int arg13, int arg14, boolean arg15, boolean arg16);

    void draw(Texture arg1, float arg2, float arg3, float arg4, float arg5, int arg6, int arg7, int arg8, int arg9, boolean arg10, boolean arg11);

    void draw(Texture arg1, float arg2, float arg3, int arg4, int arg5, int arg6, int arg7);

    void draw(Texture arg1, float[] arg2, int arg3, int arg4);

    void draw(TextureRegion arg1, float arg2, float arg3);

    void draw(TextureRegion arg1, float arg2, float arg3, float arg4, float arg5);

    void draw(TextureRegion arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10);

    void draw(TextureRegion arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, boolean arg11);

    void draw(TextureRegion arg1, float arg2, float arg3, Affine2 arg4);

    void enableBlending();

    void end();

    void flush();

    int getBlendDstFunc();

    int getBlendDstFuncAlpha();

    int getBlendSrcFunc();

    int getBlendSrcFuncAlpha();

    Color getColor();

    float getPackedColor();

    Matrix4 getProjectionMatrix();

    ShaderProgram getShader();

    Matrix4 getTransformMatrix();

    boolean isBlendingEnabled();

    boolean isDrawing();

    void setBlendFunction(int arg1, int arg2);

    void setBlendFunctionSeparate(int arg1, int arg2, int arg3, int arg4);

    void setColor(float arg1, float arg2, float arg3, float arg4);

    void setColor(Color arg1);

    void setPackedColor(float arg1);

    void setProjectionMatrix(Matrix4 arg1);

    void setShader(ShaderProgram arg1);

    void setTransformMatrix(Matrix4 arg1);
}

