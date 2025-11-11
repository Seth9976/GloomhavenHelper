package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class InstanceBufferObjectSubData implements InstanceData {
    final VertexAttributes attributes;
    final FloatBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    boolean isBound;
    final boolean isDirect;
    boolean isDirty;
    final boolean isStatic;
    final int usage;

    public InstanceBufferObjectSubData(boolean z, int v, VertexAttributes vertexAttributes0) {
        this.isDirty = false;
        this.isBound = false;
        this.isStatic = z;
        this.attributes = vertexAttributes0;
        this.byteBuffer = BufferUtils.newByteBuffer(this.attributes.vertexSize * v);
        this.isDirect = true;
        this.usage = z ? 35044 : 35048;
        this.buffer = this.byteBuffer.asFloatBuffer();
        this.bufferHandle = this.createBufferObject();
        this.buffer.flip();
        this.byteBuffer.flip();
    }

    public InstanceBufferObjectSubData(boolean z, int v, VertexAttribute[] arr_vertexAttribute) {
        this(z, v, new VertexAttributes(arr_vertexAttribute));
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void bind(ShaderProgram shaderProgram0) {
        this.bind(shaderProgram0, null);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void bind(ShaderProgram shaderProgram0, int[] arr_v) {
        GL20 gL200 = Gdx.gl20;
        gL200.glBindBuffer(0x8892, this.bufferHandle);
        int v = 0;
        if(this.isDirty) {
            this.byteBuffer.limit(this.buffer.limit() * 4);
            gL200.glBufferData(0x8892, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
        int v1 = this.attributes.size();
        if(arr_v == null) {
            while(v < v1) {
                VertexAttribute vertexAttribute0 = this.attributes.get(v);
                int v2 = shaderProgram0.getAttributeLocation(vertexAttribute0.alias);
                if(v2 >= 0) {
                    int v3 = v2 + vertexAttribute0.unit;
                    shaderProgram0.enableVertexAttribute(v3);
                    shaderProgram0.setVertexAttribute(v3, vertexAttribute0.numComponents, vertexAttribute0.type, vertexAttribute0.normalized, this.attributes.vertexSize, vertexAttribute0.offset);
                    Gdx.gl30.glVertexAttribDivisor(v3, 1);
                }
                ++v;
            }
        }
        else {
            while(v < v1) {
                VertexAttribute vertexAttribute1 = this.attributes.get(v);
                int v4 = arr_v[v];
                if(v4 >= 0) {
                    int v5 = v4 + vertexAttribute1.unit;
                    shaderProgram0.enableVertexAttribute(v5);
                    shaderProgram0.setVertexAttribute(v5, vertexAttribute1.numComponents, vertexAttribute1.type, vertexAttribute1.normalized, this.attributes.vertexSize, vertexAttribute1.offset);
                    Gdx.gl30.glVertexAttribDivisor(v5, 1);
                }
                ++v;
            }
        }
        this.isBound = true;
    }

    private void bufferChanged() {
        if(this.isBound) {
            Gdx.gl20.glBufferData(0x8892, this.byteBuffer.limit(), null, this.usage);
            Gdx.gl20.glBufferSubData(0x8892, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
    }

    private int createBufferObject() {
        int v = Gdx.gl20.glGenBuffer();
        Gdx.gl20.glBindBuffer(0x8892, v);
        Gdx.gl20.glBufferData(0x8892, this.byteBuffer.capacity(), null, this.usage);
        Gdx.gl20.glBindBuffer(0x8892, 0);
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void dispose() {
        GL20 gL200 = Gdx.gl20;
        gL200.glBindBuffer(0x8892, 0);
        gL200.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public FloatBuffer getBuffer() {
        this.isDirty = true;
        return this.buffer;
    }

    public int getBufferHandle() {
        return this.bufferHandle;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public int getNumInstances() {
        return this.buffer.limit() * 4 / this.attributes.vertexSize;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public int getNumMaxInstances() {
        return this.byteBuffer.capacity() / this.attributes.vertexSize;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void invalidate() {
        this.bufferHandle = this.createBufferObject();
        this.isDirty = true;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void setInstanceData(FloatBuffer floatBuffer0, int v) {
        this.isDirty = true;
        if(this.isDirect) {
            BufferUtils.copy(floatBuffer0, this.byteBuffer, v);
            this.buffer.position(0);
            this.buffer.limit(v);
        }
        else {
            this.buffer.clear();
            this.buffer.put(floatBuffer0);
            this.buffer.flip();
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.buffer.limit() << 2);
        }
        this.bufferChanged();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void setInstanceData(float[] arr_f, int v, int v1) {
        this.isDirty = true;
        if(this.isDirect) {
            BufferUtils.copy(arr_f, this.byteBuffer, v1, v);
            this.buffer.position(0);
            this.buffer.limit(v1);
        }
        else {
            this.buffer.clear();
            this.buffer.put(arr_f, v, v1);
            this.buffer.flip();
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.buffer.limit() << 2);
        }
        this.bufferChanged();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void unbind(ShaderProgram shaderProgram0) {
        this.unbind(shaderProgram0, null);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void unbind(ShaderProgram shaderProgram0, int[] arr_v) {
        GL20 gL200 = Gdx.gl20;
        int v = this.attributes.size();
        if(arr_v == null) {
            for(int v1 = 0; v1 < v; ++v1) {
                VertexAttribute vertexAttribute0 = this.attributes.get(v1);
                int v2 = shaderProgram0.getAttributeLocation(vertexAttribute0.alias);
                if(v2 >= 0) {
                    shaderProgram0.disableVertexAttribute(v2 + vertexAttribute0.unit);
                }
            }
        }
        else {
            for(int v3 = 0; v3 < v; ++v3) {
                VertexAttribute vertexAttribute1 = this.attributes.get(v3);
                int v4 = arr_v[v3];
                if(v4 >= 0) {
                    shaderProgram0.enableVertexAttribute(v4 + vertexAttribute1.unit);
                }
            }
        }
        gL200.glBindBuffer(0x8892, 0);
        this.isBound = false;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void updateInstanceData(int v, FloatBuffer floatBuffer0, int v1, int v2) {
        this.isDirty = true;
        if(!this.isDirect) {
            throw new GdxRuntimeException("Buffer must be allocated direct.");
        }
        this.byteBuffer.position(v * 4);
        floatBuffer0.position(v1 * 4);
        BufferUtils.copy(floatBuffer0, this.byteBuffer, v2);
        this.byteBuffer.position(this.byteBuffer.position());
        this.bufferChanged();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void updateInstanceData(int v, float[] arr_f, int v1, int v2) {
        this.isDirty = true;
        if(!this.isDirect) {
            throw new GdxRuntimeException("Buffer must be allocated direct.");
        }
        this.byteBuffer.position(v * 4);
        BufferUtils.copy(arr_f, v1, v2, this.byteBuffer);
        this.byteBuffer.position(this.byteBuffer.position());
        this.bufferChanged();
    }
}

