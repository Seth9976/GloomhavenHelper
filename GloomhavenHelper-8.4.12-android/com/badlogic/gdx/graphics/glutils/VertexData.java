package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.Disposable;
import java.nio.FloatBuffer;

public interface VertexData extends Disposable {
    void bind(ShaderProgram arg1);

    void bind(ShaderProgram arg1, int[] arg2);

    @Override  // com.badlogic.gdx.utils.Disposable
    void dispose();

    VertexAttributes getAttributes();

    FloatBuffer getBuffer();

    int getNumMaxVertices();

    int getNumVertices();

    void invalidate();

    void setVertices(float[] arg1, int arg2, int arg3);

    void unbind(ShaderProgram arg1);

    void unbind(ShaderProgram arg1, int[] arg2);

    void updateVertices(int arg1, float[] arg2, int arg3, int arg4);
}

