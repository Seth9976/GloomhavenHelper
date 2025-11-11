package com.badlogic.gdx.utils;

public class DataBuffer extends DataOutput {
    private final OptimizedByteArrayOutputStream outStream;

    public DataBuffer() {
        this(0x20);
    }

    public DataBuffer(int v) {
        super(new OptimizedByteArrayOutputStream(v));
        this.outStream = (OptimizedByteArrayOutputStream)this.out;
    }

    public byte[] getBuffer() {
        return this.outStream.getBuffer();
    }

    public byte[] toArray() {
        return this.outStream.toByteArray();
    }
}

