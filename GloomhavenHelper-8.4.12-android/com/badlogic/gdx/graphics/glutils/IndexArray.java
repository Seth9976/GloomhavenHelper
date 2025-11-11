package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.utils.BufferUtils;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public class IndexArray implements IndexData {
    final ShortBuffer buffer;
    final ByteBuffer byteBuffer;
    private final boolean empty;

    public IndexArray(int v) {
        this.empty = v == 0;
        if(this.empty) {
            v = 1;
        }
        this.byteBuffer = BufferUtils.newUnsafeByteBuffer(v * 2);
        this.buffer = this.byteBuffer.asShortBuffer();
        this.buffer.flip();
        this.byteBuffer.flip();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void bind() {
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void dispose() {
        BufferUtils.disposeUnsafeByteBuffer(this.byteBuffer);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public ShortBuffer getBuffer() {
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
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void setIndices(ShortBuffer shortBuffer0) {
        this.buffer.clear();
        this.buffer.limit(shortBuffer0.remaining());
        this.buffer.put(shortBuffer0);
        this.buffer.flip();
        shortBuffer0.position(shortBuffer0.position());
        this.byteBuffer.position(0);
        this.byteBuffer.limit(this.buffer.limit() << 1);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void setIndices(short[] arr_v, int v, int v1) {
        this.buffer.clear();
        this.buffer.put(arr_v, v, v1);
        this.buffer.flip();
        this.byteBuffer.position(0);
        this.byteBuffer.limit(v1 << 1);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void unbind() {
    }

    @Override  // com.badlogic.gdx.graphics.glutils.IndexData
    public void updateIndices(int v, short[] arr_v, int v1, int v2) {
        this.byteBuffer.position(v * 2);
        BufferUtils.copy(arr_v, v1, this.byteBuffer, v2);
        this.byteBuffer.position(this.byteBuffer.position());
    }
}

