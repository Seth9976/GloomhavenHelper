package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.IntArray;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class VertexBufferObjectWithVAO implements VertexData {
    final VertexAttributes attributes;
    final FloatBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    IntArray cachedLocations;
    boolean isBound;
    boolean isDirty;
    final boolean isStatic;
    final boolean ownsBuffer;
    static final IntBuffer tmpHandle;
    final int usage;
    int vaoHandle;

    static {
        VertexBufferObjectWithVAO.tmpHandle = BufferUtils.newIntBuffer(1);
    }

    public VertexBufferObjectWithVAO(boolean z, int v, VertexAttributes vertexAttributes0) {
        this.isDirty = false;
        this.isBound = false;
        this.vaoHandle = -1;
        this.cachedLocations = new IntArray();
        this.isStatic = z;
        this.attributes = vertexAttributes0;
        this.byteBuffer = BufferUtils.newUnsafeByteBuffer(this.attributes.vertexSize * v);
        this.buffer = this.byteBuffer.asFloatBuffer();
        this.ownsBuffer = true;
        this.buffer.flip();
        this.byteBuffer.flip();
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        this.usage = z ? 35044 : 35048;
        this.createVAO();
    }

    public VertexBufferObjectWithVAO(boolean z, int v, VertexAttribute[] arr_vertexAttribute) {
        this(z, v, new VertexAttributes(arr_vertexAttribute));
    }

    public VertexBufferObjectWithVAO(boolean z, ByteBuffer byteBuffer0, VertexAttributes vertexAttributes0) {
        this.isDirty = false;
        this.isBound = false;
        this.vaoHandle = -1;
        this.cachedLocations = new IntArray();
        this.isStatic = z;
        this.attributes = vertexAttributes0;
        this.byteBuffer = byteBuffer0;
        this.ownsBuffer = false;
        this.buffer = this.byteBuffer.asFloatBuffer();
        this.buffer.flip();
        this.byteBuffer.flip();
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        this.usage = z ? 35044 : 35048;
        this.createVAO();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram0) {
        this.bind(shaderProgram0, null);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram0, int[] arr_v) {
        GL30 gL300 = Gdx.gl30;
        gL300.glBindVertexArray(this.vaoHandle);
        this.bindAttributes(shaderProgram0, arr_v);
        this.bindData(gL300);
        this.isBound = true;
    }

    private void bindAttributes(ShaderProgram shaderProgram0, int[] arr_v) {
        boolean z = this.cachedLocations.size != 0;
        int v1 = this.attributes.size();
        if(z) {
            if(arr_v == null) {
                for(int v2 = 0; z && v2 < v1; ++v2) {
                    z = shaderProgram0.getAttributeLocation(this.attributes.get(v2).alias) == this.cachedLocations.get(v2);
                }
            }
            else {
                z = arr_v.length == this.cachedLocations.size;
                for(int v3 = 0; z && v3 < v1; ++v3) {
                    z = arr_v[v3] == this.cachedLocations.get(v3);
                }
            }
        }
        if(!z) {
            Gdx.gl.glBindBuffer(0x8892, this.bufferHandle);
            this.unbindAttributes(shaderProgram0);
            this.cachedLocations.clear();
            for(int v = 0; v < v1; ++v) {
                VertexAttribute vertexAttribute0 = this.attributes.get(v);
                if(arr_v == null) {
                    this.cachedLocations.add(shaderProgram0.getAttributeLocation(vertexAttribute0.alias));
                }
                else {
                    this.cachedLocations.add(arr_v[v]);
                }
                int v4 = this.cachedLocations.get(v);
                if(v4 >= 0) {
                    shaderProgram0.enableVertexAttribute(v4);
                    shaderProgram0.setVertexAttribute(v4, vertexAttribute0.numComponents, vertexAttribute0.type, vertexAttribute0.normalized, this.attributes.vertexSize, vertexAttribute0.offset);
                }
            }
        }
    }

    private void bindData(GL20 gL200) {
        if(this.isDirty) {
            gL200.glBindBuffer(0x8892, this.bufferHandle);
            this.byteBuffer.limit(this.buffer.limit() * 4);
            gL200.glBufferData(0x8892, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
    }

    private void bufferChanged() {
        if(this.isBound) {
            Gdx.gl20.glBindBuffer(0x8892, this.bufferHandle);
            Gdx.gl20.glBufferData(0x8892, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
    }

    private void createVAO() {
        VertexBufferObjectWithVAO.tmpHandle.clear();
        Gdx.gl30.glGenVertexArrays(1, VertexBufferObjectWithVAO.tmpHandle);
        this.vaoHandle = VertexBufferObjectWithVAO.tmpHandle.get();
    }

    private void deleteVAO() {
        if(this.vaoHandle != -1) {
            VertexBufferObjectWithVAO.tmpHandle.clear();
            VertexBufferObjectWithVAO.tmpHandle.put(this.vaoHandle);
            VertexBufferObjectWithVAO.tmpHandle.flip();
            Gdx.gl30.glDeleteVertexArrays(1, VertexBufferObjectWithVAO.tmpHandle);
            this.vaoHandle = -1;
        }
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void dispose() {
        GL30 gL300 = Gdx.gl30;
        gL300.glBindBuffer(0x8892, 0);
        gL300.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
        if(this.ownsBuffer) {
            BufferUtils.disposeUnsafeByteBuffer(this.byteBuffer);
        }
        this.deleteVAO();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public FloatBuffer getBuffer() {
        this.isDirty = true;
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
        this.bufferHandle = Gdx.gl30.glGenBuffer();
        this.createVAO();
        this.isDirty = true;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void setVertices(float[] arr_f, int v, int v1) {
        this.isDirty = true;
        BufferUtils.copy(arr_f, this.byteBuffer, v1, v);
        this.buffer.position(0);
        this.buffer.limit(v1);
        this.bufferChanged();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram0) {
        this.unbind(shaderProgram0, null);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram0, int[] arr_v) {
        Gdx.gl30.glBindVertexArray(0);
        this.isBound = false;
    }

    private void unbindAttributes(ShaderProgram shaderProgram0) {
        if(this.cachedLocations.size == 0) {
            return;
        }
        int v = this.attributes.size();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = this.cachedLocations.get(v1);
            if(v2 >= 0) {
                shaderProgram0.disableVertexAttribute(v2);
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.glutils.VertexData
    public void updateVertices(int v, float[] arr_f, int v1, int v2) {
        this.isDirty = true;
        this.byteBuffer.position(v * 4);
        BufferUtils.copy(arr_f, v1, v2, this.byteBuffer);
        this.byteBuffer.position(this.byteBuffer.position());
        this.buffer.position(0);
        this.bufferChanged();
    }
}

