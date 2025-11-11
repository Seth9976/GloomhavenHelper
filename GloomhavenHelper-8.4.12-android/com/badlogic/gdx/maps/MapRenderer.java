package com.badlogic.gdx.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

public interface MapRenderer {
    void render();

    void render(int[] arg1);

    void setView(OrthographicCamera arg1);

    void setView(Matrix4 arg1, float arg2, float arg3, float arg4, float arg5);
}

