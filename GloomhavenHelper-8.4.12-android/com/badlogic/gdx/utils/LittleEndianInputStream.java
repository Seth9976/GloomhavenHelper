package com.badlogic.gdx.utils;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LittleEndianInputStream extends FilterInputStream implements DataInput {
    private DataInputStream din;

    public LittleEndianInputStream(InputStream inputStream0) {
        super(inputStream0);
        this.din = new DataInputStream(inputStream0);
    }

    @Override
    public boolean readBoolean() throws IOException {
        return this.din.readBoolean();
    }

    @Override
    public byte readByte() throws IOException {
        return this.din.readByte();
    }

    @Override
    public char readChar() throws IOException {
        return this.din.readChar();
    }

    @Override
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(this.readLong());
    }

    @Override
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readInt());
    }

    @Override
    public void readFully(byte[] arr_b) throws IOException {
        this.din.readFully(arr_b);
    }

    @Override
    public void readFully(byte[] arr_b, int v, int v1) throws IOException {
        this.din.readFully(arr_b, v, v1);
    }

    @Override
    public int readInt() throws IOException {
        int[] arr_v = new int[4];
        for(int v = 3; v >= 0; --v) {
            arr_v[v] = this.din.read();
        }
        return arr_v[3] & 0xFF | ((arr_v[0] & 0xFF) << 24 | (arr_v[1] & 0xFF) << 16 | (arr_v[2] & 0xFF) << 8);
    }

    @Override
    public final String readLine() throws IOException {
        return this.din.readLine();
    }

    @Override
    public long readLong() throws IOException {
        int[] arr_v = new int[8];
        for(int v = 7; v >= 0; --v) {
            arr_v[v] = this.din.read();
        }
        return ((long)(arr_v[7] & 0xFF)) | (((long)(arr_v[0] & 0xFF)) << 56 | ((long)(arr_v[1] & 0xFF)) << 0x30 | ((long)(arr_v[2] & 0xFF)) << 40 | ((long)(arr_v[3] & 0xFF)) << 0x20 | ((long)(arr_v[4] & 0xFF)) << 24 | ((long)(arr_v[5] & 0xFF)) << 16 | ((long)(arr_v[6] & 0xFF)) << 8);
    }

    @Override
    public short readShort() throws IOException {
        return (short)(this.din.read() & 0xFF | this.din.read() << 8);
    }

    @Override
    public String readUTF() throws IOException {
        return this.din.readUTF();
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return this.din.readUnsignedByte();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return this.din.read() & 0xFF | (this.din.read() & 0xFF) << 8;
    }

    @Override
    public int skipBytes(int v) throws IOException {
        return this.din.skipBytes(v);
    }
}

