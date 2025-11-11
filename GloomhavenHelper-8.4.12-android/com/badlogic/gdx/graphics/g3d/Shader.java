package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.utils.Disposable;

public interface Shader extends Disposable {
    void begin(Camera arg1, RenderContext arg2);

    boolean canRender(Renderable arg1);

    int compareTo(Shader arg1);

    void end();

    void init();

    void render(Renderable arg1);
}

