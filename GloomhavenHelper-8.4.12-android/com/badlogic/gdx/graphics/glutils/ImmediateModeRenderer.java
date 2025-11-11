package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;

public interface ImmediateModeRenderer {
    void begin(Matrix4 arg1, int arg2);

    void color(float arg1);

    void color(float arg1, float arg2, float arg3, float arg4);

    void color(Color arg1);

    void dispose();

    void end();

    void flush();

    int getMaxVertices();

    int getNumVertices();

    void normal(float arg1, float arg2, float arg3);

    void texCoord(float arg1, float arg2);

    void vertex(float arg1, float arg2, float arg3);
}

