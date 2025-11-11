package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.utils.Disposable;
import java.nio.ShortBuffer;

public interface IndexData extends Disposable {
    void bind();

    @Override  // com.badlogic.gdx.utils.Disposable
    void dispose();

    ShortBuffer getBuffer();

    int getNumIndices();

    int getNumMaxIndices();

    void invalidate();

    void setIndices(ShortBuffer arg1);

    void setIndices(short[] arg1, int arg2, int arg3);

    void unbind();

    void updateIndices(int arg1, short[] arg2, int arg3, int arg4);
}

