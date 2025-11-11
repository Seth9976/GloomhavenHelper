package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public class IndexBufferObject implements IndexData {
    final ShortBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    private final boolean empty;
    boolean isBound;
    final boolean isDirect;
    boolean isDirty;
    final boolean ownsBuffer;
    final int usage;

    public IndexBufferObject(int v) {
        this(true, v);
    }

    public IndexBufferObject(boolean z, int v) {
        this.isDirty = true;
        boolean z1 = false;
        this.isBound = false;
        if(v == 0) {
            z1 = true;
        }
        this.empty = z1;
        if(this.empty) {
            v = 1;
        }
        this.byteBuffer = BufferUtils.newUnsafeByteBuffer(v * 2);
        this.isDirect = true;
        this.buffer = this.byteBuffer.asShortBuffer();
        this.ownsBuffer = true;
        this.buffer.flip();
        this.byteBuffer.flip();
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        this.usage = z ? 35044 : 35048;
    }

    public IndexBufferObject(boolean z, ByteBuffer byteBuffer0) {
        this.isDirty = true;
        this.isBound = false;
        this.empty = byteBuffer0.limit() == 0;
        this.byteBuffer = byteBuffer0;
        this.isDirect = true;
        this.buffer = this.byteBuffer.asShortBuffer();
        this.ownsBuffer = false;
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        this.usage = z ? 35044 : 35048;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void bind() {
        if(this.bufferHandle == 0) {
            throw new GdxRuntimeException("No buffer allocated!");
        }
        Gdx.gl20.glBindBuffer(0x8893, this.bufferHandle);
        if(this.isDirty) {
            this.byteBuffer.limit(this.buffer.limit() * 2);
            Gdx.gl20.glBufferData(0x8893, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
        this.isBound = true;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void dispose() {
        Gdx.gl20.glBindBuffer(0x8893, 0);
        Gdx.gl20.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
        if(this.ownsBuffer) {
            BufferUtils.disposeUnsafeByteBuffer(this.byteBuffer);
        }
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public ShortBuffer getBuffer() {
        this.isDirty = true;
        return this.buffer;
    }

    // 去混淆评级： 低(20)
    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public int getNumIndices() {
        return this.empty ? 0 : this.buffer.limit();
    }

    // 去混淆评级： 低(20)
    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public int getNumMaxIndices() {
        return this.empty ? 0 : this.buffer.capacity();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void invalidate() {
        this.bufferHandle = Gdx.gl20.glGenBuffer();
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
            Gdx.gl20.glBufferData(0x8893, this.byteBuffer.limit(), this.byteBuffer, this.usage);
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
            Gdx.gl20.glBufferData(0x8893, this.byteBuffer.limit(), this.byteBuffer, this.usage);
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
            Gdx.gl20.glBufferData(0x8893, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
    }
}

