package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public class IndexBufferObjectSubData implements IndexData {
    final ShortBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    boolean isBound;
    final boolean isDirect;
    boolean isDirty;
    final int usage;

    public IndexBufferObjectSubData(int v) {
        this.isDirty = true;
        this.isBound = false;
        this.byteBuffer = BufferUtils.newByteBuffer(v * 2);
        this.isDirect = true;
        this.usage = 35044;
        this.buffer = this.byteBuffer.asShortBuffer();
        this.buffer.flip();
        this.byteBuffer.flip();
        this.bufferHandle = this.createBufferObject();
    }

    public IndexBufferObjectSubData(boolean z, int v) {
        this.isDirty = true;
        this.isBound = false;
        this.byteBuffer = BufferUtils.newByteBuffer(v * 2);
        this.isDirect = true;
        this.usage = z ? 35044 : 35048;
        this.buffer = this.byteBuffer.asShortBuffer();
        this.buffer.flip();
        this.byteBuffer.flip();
        this.bufferHandle = this.createBufferObject();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void bind() {
        if(this.bufferHandle == 0) {
            throw new GdxRuntimeException("IndexBufferObject cannot be used after it has been disposed.");
        }
        Gdx.gl20.glBindBuffer(0x8893, this.bufferHandle);
        if(this.isDirty) {
            this.byteBuffer.limit(this.buffer.limit() * 2);
            Gdx.gl20.glBufferSubData(0x8893, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
        this.isBound = true;
    }

    private int createBufferObject() {
        int v = Gdx.gl20.glGenBuffer();
        Gdx.gl20.glBindBuffer(0x8893, v);
        Gdx.gl20.glBufferData(0x8893, this.byteBuffer.capacity(), null, this.usage);
        Gdx.gl20.glBindBuffer(0x8893, 0);
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void dispose() {
        GL20 gL200 = Gdx.gl20;
        gL200.glBindBuffer(0x8893, 0);
        gL200.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public ShortBuffer getBuffer() {
        this.isDirty = true;
        return this.buffer;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public int getNumIndices() {
        return this.buffer.limit();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public int getNumMaxIndices() {
        return this.buffer.capacity();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void invalidate() {
        this.bufferHandle = this.createBufferObject();
        this.isDirty = true;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void setIndices(ShortBuffer shortBuffer0) {
        this.isDirty = true;
        this.buffer.clear();
        this.buffer.put(shortBuffer0);
        this.buffer.flip();
        shortBuffer0.position(shortBuffer0.position());
        this.byteBuffer.position(0);
        this.byteBuffer.limit(this.buffer.limit() << 1);
        if(this.isBound) {
            Gdx.gl20.glBufferSubData(0x8893, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void setIndices(short[] arr_v, int v, int v1) {
        this.isDirty = true;
        this.buffer.clear();
        this.buffer.put(arr_v, v, v1);
        this.buffer.flip();
        this.byteBuffer.position(0);
        this.byteBuffer.limit(v1 << 1);
        if(this.isBound) {
            Gdx.gl20.glBufferSubData(0x8893, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void unbind() {
        Gdx.gl20.glBindBuffer(0x8893, 0);
        this.isBound = false;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void updateIndices(int v, short[] arr_v, int v1, int v2) {
        this.isDirty = true;
        this.byteBuffer.position(v * 2);
        BufferUtils.copy(arr_v, v1, this.byteBuffer, v2);
        this.byteBuffer.position(this.byteBuffer.position());
        this.buffer.position(0);
        if(this.isBound) {
            Gdx.gl20.glBufferSubData(0x8893, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
    }
}

