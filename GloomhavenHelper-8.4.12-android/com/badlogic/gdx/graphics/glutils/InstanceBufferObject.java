package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class InstanceBufferObject implements InstanceData {
    private VertexAttributes attributes;
    private FloatBuffer buffer;
    private int bufferHandle;
    private ByteBuffer byteBuffer;
    boolean isBound;
    boolean isDirty;
    private boolean ownsBuffer;
    private int usage;

    public InstanceBufferObject(boolean z, int v, VertexAttributes vertexAttributes0) {
        this.isDirty = false;
        this.isBound = false;
        if(Gdx.gl30 == null) {
            throw new GdxRuntimeException("InstanceBufferObject requires a device running with GLES 3.0 compatibilty");
        }
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        ByteBuffer byteBuffer0 = BufferUtils.newUnsafeByteBuffer(vertexAttributes0.vertexSize * v);
        byteBuffer0.limit(0);
        this.setBuffer(byteBuffer0, true, vertexAttributes0);
        this.setUsage((z ? 35044 : 35048));
    }

    public InstanceBufferObject(boolean z, int v, VertexAttribute[] arr_vertexAttribute) {
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
            Gdx.gl20.glBufferData(0x8892, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void dispose() {
        GL20 gL200 = Gdx.gl20;
        gL200.glBindBuffer(0x8892, 0);
        gL200.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
        if(this.ownsBuffer) {
            BufferUtils.disposeUnsafeByteBuffer(this.byteBuffer);
        }
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

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public int getNumInstances() {
        return this.buffer.limit() * 4 / this.attributes.vertexSize;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public int getNumMaxInstances() {
        return this.byteBuffer.capacity() / this.attributes.vertexSize;
    }

    protected int getUsage() {
        return this.usage;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void invalidate() {
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        this.isDirty = true;
    }

    protected void setBuffer(Buffer buffer0, boolean z, VertexAttributes vertexAttributes0) {
        if(this.isBound) {
            throw new GdxRuntimeException("Cannot change attributes while VBO is bound");
        }
        if(this.ownsBuffer) {
            ByteBuffer byteBuffer0 = this.byteBuffer;
            if(byteBuffer0 != null) {
                BufferUtils.disposeUnsafeByteBuffer(byteBuffer0);
            }
        }
        this.attributes = vertexAttributes0;
        if(!(buffer0 instanceof ByteBuffer)) {
            throw new GdxRuntimeException("Only ByteBuffer is currently supported");
        }
        this.byteBuffer = (ByteBuffer)buffer0;
        this.ownsBuffer = z;
        int v = this.byteBuffer.limit();
        this.byteBuffer.limit(this.byteBuffer.capacity());
        this.buffer = this.byteBuffer.asFloatBuffer();
        this.byteBuffer.limit(v);
        this.buffer.limit(v / 4);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void setInstanceData(FloatBuffer floatBuffer0, int v) {
        this.isDirty = true;
        BufferUtils.copy(floatBuffer0, this.byteBuffer, v);
        this.buffer.position(0);
        this.buffer.limit(v);
        this.bufferChanged();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void setInstanceData(float[] arr_f, int v, int v1) {
        this.isDirty = true;
        BufferUtils.copy(arr_f, this.byteBuffer, v1, v);
        this.buffer.position(0);
        this.buffer.limit(v1);
        this.bufferChanged();
    }

    protected void setUsage(int v) {
        if(this.isBound) {
            throw new GdxRuntimeException("Cannot change usage while VBO is bound");
        }
        this.usage = v;
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
                    shaderProgram0.disableVertexAttribute(v4 + vertexAttribute1.unit);
                }
            }
        }
        gL200.glBindBuffer(0x8892, 0);
        this.isBound = false;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void updateInstanceData(int v, FloatBuffer floatBuffer0, int v1, int v2) {
        this.isDirty = true;
        int v3 = this.byteBuffer.position();
        this.byteBuffer.position(v * 4);
        floatBuffer0.position(v1 * 4);
        BufferUtils.copy(floatBuffer0, this.byteBuffer, v2);
        this.byteBuffer.position(v3);
        this.buffer.position(0);
        this.bufferChanged();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.InstanceData
    public void updateInstanceData(int v, float[] arr_f, int v1, int v2) {
        this.isDirty = true;
        int v3 = this.byteBuffer.position();
        this.byteBuffer.position(v * 4);
        BufferUtils.copy(arr_f, v1, v2, this.byteBuffer);
        this.byteBuffer.position(v3);
        this.buffer.position(0);
        this.bufferChanged();
    }
}

