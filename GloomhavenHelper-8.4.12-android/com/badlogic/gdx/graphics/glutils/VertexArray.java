package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class VertexArray implements VertexData {
    final VertexAttributes attributes;
    final FloatBuffer buffer;
    final ByteBuffer byteBuffer;
    boolean isBound;

    public VertexArray(int v, VertexAttributes vertexAttributes0) {
        this.isBound = false;
        this.attributes = vertexAttributes0;
        this.byteBuffer = BufferUtils.newUnsafeByteBuffer(this.attributes.vertexSize * v);
        this.buffer = this.byteBuffer.asFloatBuffer();
        this.buffer.flip();
        this.byteBuffer.flip();
    }

    public VertexArray(int v, VertexAttribute[] arr_vertexAttribute) {
        this(v, new VertexAttributes(arr_vertexAttribute));
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram0) {
        this.bind(shaderProgram0, null);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram0, int[] arr_v) {
        int v = this.attributes.size();
        this.byteBuffer.limit(this.buffer.limit() * 4);
        int v1 = 0;
        if(arr_v == null) {
            while(v1 < v) {
                VertexAttribute vertexAttribute0 = this.attributes.get(v1);
                int v2 = shaderProgram0.getAttributeLocation(vertexAttribute0.alias);
                if(v2 >= 0) {
                    shaderProgram0.enableVertexAttribute(v2);
                    if(vertexAttribute0.type == 0x1406) {
                        this.buffer.position(vertexAttribute0.offset / 4);
                        shaderProgram0.setVertexAttribute(v2, vertexAttribute0.numComponents, vertexAttribute0.type, vertexAttribute0.normalized, this.attributes.vertexSize, this.buffer);
                    }
                    else {
                        this.byteBuffer.position(vertexAttribute0.offset);
                        shaderProgram0.setVertexAttribute(v2, vertexAttribute0.numComponents, vertexAttribute0.type, vertexAttribute0.normalized, this.attributes.vertexSize, this.byteBuffer);
                    }
                }
                ++v1;
            }
        }
        else {
            while(v1 < v) {
                VertexAttribute vertexAttribute1 = this.attributes.get(v1);
                int v3 = arr_v[v1];
                if(v3 >= 0) {
                    shaderProgram0.enableVertexAttribute(v3);
                    if(vertexAttribute1.type == 0x1406) {
                        this.buffer.position(vertexAttribute1.offset / 4);
                        shaderProgram0.setVertexAttribute(v3, vertexAttribute1.numComponents, vertexAttribute1.type, vertexAttribute1.normalized, this.attributes.vertexSize, this.buffer);
                    }
                    else {
                        this.byteBuffer.position(vertexAttribute1.offset);
                        shaderProgram0.setVertexAttribute(v3, vertexAttribute1.numComponents, vertexAttribute1.type, vertexAttribute1.normalized, this.attributes.vertexSize, this.byteBuffer);
                    }
                }
                ++v1;
            }
        }
        this.isBound = true;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void dispose() {
        BufferUtils.disposeUnsafeByteBuffer(this.byteBuffer);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public FloatBuffer getBuffer() {
        return this.buffer;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public int getNumMaxVertices() {
        return this.byteBuffer.capacity() / this.attributes.vertexSize;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public int getNumVertices() {
        return this.buffer.limit() * 4 / this.attributes.vertexSize;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void invalidate() {
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void setVertices(float[] arr_f, int v, int v1) {
        BufferUtils.copy(arr_f, this.byteBuffer, v1, v);
        this.buffer.position(0);
        this.buffer.limit(v1);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram0) {
        this.unbind(shaderProgram0, null);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram0, int[] arr_v) {
        int v = this.attributes.size();
        if(arr_v == null) {
            for(int v1 = 0; v1 < v; ++v1) {
                shaderProgram0.disableVertexAttribute(this.attributes.get(v1).alias);
            }
        }
        else {
            for(int v2 = 0; v2 < v; ++v2) {
                int v3 = arr_v[v2];
                if(v3 >= 0) {
                    shaderProgram0.disableVertexAttribute(v3);
                }
            }
        }
        this.isBound = false;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void updateVertices(int v, float[] arr_f, int v1, int v2) {
        this.byteBuffer.position(v * 4);
        BufferUtils.copy(arr_f, v1, v2, this.byteBuffer);
        this.byteBuffer.position(this.byteBuffer.position());
    }
}

