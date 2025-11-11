package com.esotericsoftware.gloomhavenhelper.util;

import java.io.IOException;
import java.io.OutputStream;

public class Output extends OutputStream {
    protected byte[] buffer;
    protected int capacity;
    protected int maxCapacity;
    protected OutputStream outputStream;
    protected int position;
    protected long total;

    public Output() {
    }

    public Output(int v) {
        this(v, v);
    }

    public Output(int v, int v1) {
        if(v > v1 && v1 != -1) {
            throw new IllegalArgumentException("bufferSize: " + v + " cannot be greater than maxBufferSize: " + v1);
        }
        if(v1 < -1) {
            throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + v1);
        }
        this.capacity = v;
        if(v1 == -1) {
            v1 = 0x7FFFFFF7;
        }
        this.maxCapacity = v1;
        this.buffer = new byte[v];
    }

    public Output(OutputStream outputStream0) {
        this(0x1000, 0x1000);
        if(outputStream0 == null) {
            throw new IllegalArgumentException("outputStream cannot be null.");
        }
        this.outputStream = outputStream0;
    }

    public Output(OutputStream outputStream0, int v) {
        this(v, v);
        if(outputStream0 == null) {
            throw new IllegalArgumentException("outputStream cannot be null.");
        }
        this.outputStream = outputStream0;
    }

    public Output(byte[] arr_b) {
        this(arr_b, arr_b.length);
    }

    public Output(byte[] arr_b, int v) {
        if(arr_b == null) {
            throw new IllegalArgumentException("buffer cannot be null.");
        }
        this.setBuffer(arr_b, v);
    }

    public void clear() {
        this.position = 0;
        this.total = 0L;
    }

    @Override
    public void close() {
        this.flush();
        OutputStream outputStream0 = this.outputStream;
        if(outputStream0 != null) {
            try {
                outputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
    }

    @Override
    public void flush() {
        OutputStream outputStream0 = this.outputStream;
        if(outputStream0 == null) {
            return;
        }
        try {
            outputStream0.write(this.buffer, 0, this.position);
            this.outputStream.flush();
            this.total += (long)this.position;
            this.position = 0;
        }
        catch(IOException iOException0) {
            throw new RuntimeException(iOException0);
        }
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    private void getBytes(String s, int v, int v1, byte[] arr_b, int v2) {
        while(v < v1) {
            arr_b[v2] = (byte)s.charAt(v);
            ++v2;
            ++v;
        }
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public static int intLength(int v, boolean z) {
        if(!z) {
            v = v >> 0x1F ^ v << 1;
        }
        if(v >>> 7 == 0) {
            return 1;
        }
        if(v >>> 14 == 0) {
            return 2;
        }
        if(v >>> 21 == 0) {
            return 3;
        }
        return v >>> 28 == 0 ? 4 : 5;
    }

    public static int longLength(long v, boolean z) {
        if(!z) {
            v = v >> 0x3F ^ v << 1;
        }
        if(v >>> 7 == 0L) {
            return 1;
        }
        if(v >>> 14 == 0L) {
            return 2;
        }
        if(v >>> 21 == 0L) {
            return 3;
        }
        if(v >>> 28 == 0L) {
            return 4;
        }
        if(v >>> 35 == 0L) {
            return 5;
        }
        if(v >>> 42 == 0L) {
            return 6;
        }
        if(v >>> 49 == 0L) {
            return 7;
        }
        return v >>> 56 == 0L ? 8 : 9;
    }

    public int position() {
        return this.position;
    }

    protected boolean require(int v) {
        if(this.capacity - this.position >= v) {
            return false;
        }
        if(v > this.maxCapacity) {
            throw new RuntimeException("Buffer overflow. Max capacity: " + this.maxCapacity + ", required: " + v);
        }
        this.flush();
        while(true) {
            int v1 = this.capacity;
            if(v1 - this.position >= v) {
                break;
            }
            if(v1 == this.maxCapacity) {
                throw new RuntimeException("Buffer overflow. Available: " + (this.capacity - this.position) + ", required: " + v);
            }
            if(v1 == 0) {
                this.capacity = 1;
            }
            this.capacity = Math.min(this.capacity * 2, this.maxCapacity);
            if(this.capacity < 0) {
                this.capacity = this.maxCapacity;
            }
            byte[] arr_b = new byte[this.capacity];
            System.arraycopy(this.buffer, 0, arr_b, 0, this.position);
            this.buffer = arr_b;
        }
        return true;
    }

    public void setBuffer(byte[] arr_b) {
        this.setBuffer(arr_b, arr_b.length);
    }

    public void setBuffer(byte[] arr_b, int v) {
        if(arr_b == null) {
            throw new IllegalArgumentException("buffer cannot be null.");
        }
        if(arr_b.length > v && v != -1) {
            throw new IllegalArgumentException("buffer has length: " + arr_b.length + " cannot be greater than maxBufferSize: " + v);
        }
        if(v < -1) {
            throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + v);
        }
        this.buffer = arr_b;
        if(v == -1) {
            v = 0x7FFFFFF7;
        }
        this.maxCapacity = v;
        this.capacity = arr_b.length;
        this.position = 0;
        this.total = 0L;
        this.outputStream = null;
    }

    public void setOutputStream(OutputStream outputStream0) {
        this.outputStream = outputStream0;
        this.position = 0;
        this.total = 0L;
    }

    public void setPosition(int v) {
        this.position = v;
    }

    public byte[] toBytes() {
        int v = this.position;
        byte[] arr_b = new byte[v];
        System.arraycopy(this.buffer, 0, arr_b, 0, v);
        return arr_b;
    }

    public long total() {
        return this.total + ((long)this.position);
    }

    @Override
    public void write(int v) {
        if(this.position == this.capacity) {
            this.require(1);
        }
        int v1 = this.position;
        this.position = v1 + 1;
        this.buffer[v1] = (byte)v;
    }

    @Override
    public void write(byte[] arr_b) {
        if(arr_b == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        this.writeBytes(arr_b, 0, arr_b.length);
    }

    @Override
    public void write(byte[] arr_b, int v, int v1) {
        this.writeBytes(arr_b, v, v1);
    }

    public void writeAscii(String s) {
        if(s == null) {
            this.writeByte(0x80);
            return;
        }
        int v = s.length();
        switch(v) {
            case 0: {
                this.writeByte(0x81);
                return;
            }
            case 1: {
                this.writeByte(130);
                this.writeByte(s.charAt(0));
                return;
            }
            default: {
                int v1 = this.position;
                if(this.capacity - v1 < v) {
                    this.writeAscii_slow(s, v);
                }
                else {
                    this.getBytes(s, 0, v, this.buffer, v1);
                    this.position += v;
                }
                int v2 = this.position - 1;
                this.buffer[v2] = (byte)(0x80 | this.buffer[v2]);
            }
        }
    }

    private void writeAscii_slow(String s, int v) {
        if(v == 0) {
            return;
        }
        if(this.capacity == 0) {
            this.require(1);
        }
        byte[] arr_b = this.buffer;
        int v1 = Math.min(v, this.capacity - this.position);
        for(int v2 = 0; v2 < v; v2 = v3) {
            int v3 = v2 + v1;
            this.getBytes(s, v2, v3, arr_b, this.position);
            this.position += v1;
            v1 = Math.min(v - v3, this.capacity);
            if(this.require(v1)) {
                arr_b = this.buffer;
            }
        }
    }

    public void writeBoolean(boolean z) {
        if(this.position == this.capacity) {
            this.require(1);
        }
        int v = this.position;
        this.position = v + 1;
        this.buffer[v] = (byte)z;
    }

    public void writeByte(byte b) {
        if(this.position == this.capacity) {
            this.require(1);
        }
        int v = this.position;
        this.position = v + 1;
        this.buffer[v] = b;
    }

    public void writeByte(int v) {
        if(this.position == this.capacity) {
            this.require(1);
        }
        int v1 = this.position;
        this.position = v1 + 1;
        this.buffer[v1] = (byte)v;
    }

    public void writeBytes(byte[] arr_b) {
        if(arr_b == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        this.writeBytes(arr_b, 0, arr_b.length);
    }

    public void writeBytes(byte[] arr_b, int v, int v1) {
        if(arr_b == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int v2 = Math.min(this.capacity - this.position, v1);
        while(true) {
            System.arraycopy(arr_b, v, this.buffer, this.position, v2);
            this.position += v2;
            v1 -= v2;
            if(v1 == 0) {
                break;
            }
            v += v2;
            v2 = Math.min(this.capacity, v1);
            this.require(v2);
        }
    }

    public void writeChar(char c) {
        this.require(2);
        byte[] arr_b = this.buffer;
        int v = this.position;
        this.position = v + 1;
        arr_b[v] = (byte)(c >>> 8);
        int v1 = this.position;
        this.position = v1 + 1;
        arr_b[v1] = (byte)c;
    }

    public void writeChars(char[] arr_c) {
        for(int v = 0; v < arr_c.length; ++v) {
            this.writeChar(arr_c[v]);
        }
    }

    public int writeDouble(double f, double f1, boolean z) {
        return this.writeLong(((long)(f * f1)), z);
    }

    public void writeDouble(double f) {
        this.writeLong(Double.doubleToLongBits(f));
    }

    public void writeDoubles(double[] arr_f) {
        for(int v = 0; v < arr_f.length; ++v) {
            this.writeDouble(arr_f[v]);
        }
    }

    public int writeFloat(float f, float f1, boolean z) {
        return this.writeInt(((int)(f * f1)), z);
    }

    public void writeFloat(float f) {
        this.writeInt(Float.floatToIntBits(f));
    }

    public void writeFloats(float[] arr_f) {
        for(int v = 0; v < arr_f.length; ++v) {
            this.writeFloat(arr_f[v]);
        }
    }

    public static void writeInt(byte[] arr_b, int v, int v1) {
        arr_b[v] = (byte)(v1 >> 24);
        arr_b[v + 1] = (byte)(v1 >> 16);
        arr_b[v + 2] = (byte)(v1 >> 8);
        arr_b[v + 3] = (byte)v1;
    }

    public int writeInt(int v, boolean z) {
        if(!z) {
            v = v >> 0x1F ^ v << 1;
        }
        if(v >>> 7 == 0) {
            this.require(1);
            int v1 = this.position;
            this.position = v1 + 1;
            this.buffer[v1] = (byte)v;
            return 1;
        }
        if(v >>> 14 == 0) {
            this.require(2);
            byte[] arr_b = this.buffer;
            int v2 = this.position;
            this.position = v2 + 1;
            arr_b[v2] = (byte)(v & 0x7F | 0x80);
            int v3 = this.position;
            this.position = v3 + 1;
            arr_b[v3] = (byte)(v >>> 7);
            return 2;
        }
        if(v >>> 21 == 0) {
            this.require(3);
            byte[] arr_b1 = this.buffer;
            int v4 = this.position;
            this.position = v4 + 1;
            arr_b1[v4] = (byte)(v & 0x7F | 0x80);
            int v5 = this.position;
            this.position = v5 + 1;
            arr_b1[v5] = (byte)(v >>> 7 | 0x80);
            int v6 = this.position;
            this.position = v6 + 1;
            arr_b1[v6] = (byte)(v >>> 14);
            return 3;
        }
        if(v >>> 28 == 0) {
            this.require(4);
            byte[] arr_b2 = this.buffer;
            int v7 = this.position;
            this.position = v7 + 1;
            arr_b2[v7] = (byte)(v & 0x7F | 0x80);
            int v8 = this.position;
            this.position = v8 + 1;
            arr_b2[v8] = (byte)(v >>> 7 | 0x80);
            int v9 = this.position;
            this.position = v9 + 1;
            arr_b2[v9] = (byte)(v >>> 14 | 0x80);
            int v10 = this.position;
            this.position = v10 + 1;
            arr_b2[v10] = (byte)(v >>> 21);
            return 4;
        }
        this.require(5);
        byte[] arr_b3 = this.buffer;
        int v11 = this.position;
        this.position = v11 + 1;
        arr_b3[v11] = (byte)(v & 0x7F | 0x80);
        int v12 = this.position;
        this.position = v12 + 1;
        arr_b3[v12] = (byte)(v >>> 7 | 0x80);
        int v13 = this.position;
        this.position = v13 + 1;
        arr_b3[v13] = (byte)(v >>> 14 | 0x80);
        int v14 = this.position;
        this.position = v14 + 1;
        arr_b3[v14] = (byte)(v >>> 21 | 0x80);
        int v15 = this.position;
        this.position = v15 + 1;
        arr_b3[v15] = (byte)(v >>> 28);
        return 5;
    }

    public void writeInt(int v) {
        this.require(4);
        byte[] arr_b = this.buffer;
        int v1 = this.position;
        this.position = v1 + 1;
        arr_b[v1] = (byte)(v >> 24);
        int v2 = this.position;
        this.position = v2 + 1;
        arr_b[v2] = (byte)(v >> 16);
        int v3 = this.position;
        this.position = v3 + 1;
        arr_b[v3] = (byte)(v >> 8);
        int v4 = this.position;
        this.position = v4 + 1;
        arr_b[v4] = (byte)v;
    }

    public void writeInts(int[] arr_v) {
        for(int v = 0; v < arr_v.length; ++v) {
            this.writeInt(arr_v[v]);
        }
    }

    public void writeInts(int[] arr_v, boolean z) {
        for(int v = 0; v < arr_v.length; ++v) {
            this.writeInt(arr_v[v], z);
        }
    }

    public int writeLong(long v, boolean z) {
        long v1 = z ? v : v << 1 ^ v >> 0x3F;
        if(v1 >>> 7 == 0L) {
            this.require(1);
            int v2 = this.position;
            this.position = v2 + 1;
            this.buffer[v2] = (byte)(((int)v1));
            return 1;
        }
        if(v1 >>> 14 == 0L) {
            this.require(2);
            byte[] arr_b = this.buffer;
            int v3 = this.position;
            this.position = v3 + 1;
            arr_b[v3] = (byte)(((int)(v1 & 0x7FL | 0x80L)));
            int v4 = this.position;
            this.position = v4 + 1;
            arr_b[v4] = (byte)(((int)(v1 >>> 7)));
            return 2;
        }
        if(v1 >>> 21 == 0L) {
            this.require(3);
            byte[] arr_b1 = this.buffer;
            int v5 = this.position;
            this.position = v5 + 1;
            arr_b1[v5] = (byte)(((int)(v1 & 0x7FL | 0x80L)));
            int v6 = this.position;
            this.position = v6 + 1;
            arr_b1[v6] = (byte)(((int)(v1 >>> 7 | 0x80L)));
            int v7 = this.position;
            this.position = v7 + 1;
            arr_b1[v7] = (byte)(((int)(v1 >>> 14)));
            return 3;
        }
        if(v1 >>> 28 == 0L) {
            this.require(4);
            byte[] arr_b2 = this.buffer;
            int v8 = this.position;
            this.position = v8 + 1;
            arr_b2[v8] = (byte)(((int)(v1 & 0x7FL | 0x80L)));
            int v9 = this.position;
            this.position = v9 + 1;
            arr_b2[v9] = (byte)(((int)(v1 >>> 7 | 0x80L)));
            int v10 = this.position;
            this.position = v10 + 1;
            arr_b2[v10] = (byte)(((int)(v1 >>> 14 | 0x80L)));
            int v11 = this.position;
            this.position = v11 + 1;
            arr_b2[v11] = (byte)(((int)(v1 >>> 21)));
            return 4;
        }
        if(v1 >>> 35 == 0L) {
            this.require(5);
            byte[] arr_b3 = this.buffer;
            int v12 = this.position;
            this.position = v12 + 1;
            arr_b3[v12] = (byte)(((int)(v1 & 0x7FL | 0x80L)));
            int v13 = this.position;
            this.position = v13 + 1;
            arr_b3[v13] = (byte)(((int)(v1 >>> 7 | 0x80L)));
            int v14 = this.position;
            this.position = v14 + 1;
            arr_b3[v14] = (byte)(((int)(v1 >>> 14 | 0x80L)));
            int v15 = this.position;
            this.position = v15 + 1;
            arr_b3[v15] = (byte)(((int)(v1 >>> 21 | 0x80L)));
            int v16 = this.position;
            this.position = v16 + 1;
            arr_b3[v16] = (byte)(((int)(v1 >>> 28)));
            return 5;
        }
        if(v1 >>> 42 == 0L) {
            this.require(6);
            byte[] arr_b4 = this.buffer;
            int v17 = this.position;
            this.position = v17 + 1;
            arr_b4[v17] = (byte)(((int)(v1 & 0x7FL | 0x80L)));
            int v18 = this.position;
            this.position = v18 + 1;
            arr_b4[v18] = (byte)(((int)(v1 >>> 7 | 0x80L)));
            int v19 = this.position;
            this.position = v19 + 1;
            arr_b4[v19] = (byte)(((int)(v1 >>> 14 | 0x80L)));
            int v20 = this.position;
            this.position = v20 + 1;
            arr_b4[v20] = (byte)(((int)(v1 >>> 21 | 0x80L)));
            int v21 = this.position;
            this.position = v21 + 1;
            arr_b4[v21] = (byte)(((int)(v1 >>> 28 | 0x80L)));
            int v22 = this.position;
            this.position = v22 + 1;
            arr_b4[v22] = (byte)(((int)(v1 >>> 35)));
            return 6;
        }
        if(v1 >>> 49 == 0L) {
            this.require(7);
            byte[] arr_b5 = this.buffer;
            int v23 = this.position;
            this.position = v23 + 1;
            arr_b5[v23] = (byte)(((int)(v1 & 0x7FL | 0x80L)));
            int v24 = this.position;
            this.position = v24 + 1;
            arr_b5[v24] = (byte)(((int)(v1 >>> 7 | 0x80L)));
            int v25 = this.position;
            this.position = v25 + 1;
            arr_b5[v25] = (byte)(((int)(v1 >>> 14 | 0x80L)));
            int v26 = this.position;
            this.position = v26 + 1;
            arr_b5[v26] = (byte)(((int)(v1 >>> 21 | 0x80L)));
            int v27 = this.position;
            this.position = v27 + 1;
            arr_b5[v27] = (byte)(((int)(v1 >>> 28 | 0x80L)));
            int v28 = this.position;
            this.position = v28 + 1;
            arr_b5[v28] = (byte)(((int)(v1 >>> 35 | 0x80L)));
            int v29 = this.position;
            this.position = v29 + 1;
            arr_b5[v29] = (byte)(((int)(v1 >>> 42)));
            return 7;
        }
        if(v1 >>> 56 == 0L) {
            this.require(8);
            byte[] arr_b6 = this.buffer;
            int v30 = this.position;
            this.position = v30 + 1;
            arr_b6[v30] = (byte)(((int)(v1 & 0x7FL | 0x80L)));
            int v31 = this.position;
            this.position = v31 + 1;
            arr_b6[v31] = (byte)(((int)(v1 >>> 7 | 0x80L)));
            int v32 = this.position;
            this.position = v32 + 1;
            arr_b6[v32] = (byte)(((int)(v1 >>> 14 | 0x80L)));
            int v33 = this.position;
            this.position = v33 + 1;
            arr_b6[v33] = (byte)(((int)(v1 >>> 21 | 0x80L)));
            int v34 = this.position;
            this.position = v34 + 1;
            arr_b6[v34] = (byte)(((int)(v1 >>> 28 | 0x80L)));
            int v35 = this.position;
            this.position = v35 + 1;
            arr_b6[v35] = (byte)(((int)(v1 >>> 35 | 0x80L)));
            int v36 = this.position;
            this.position = v36 + 1;
            arr_b6[v36] = (byte)(((int)(v1 >>> 42 | 0x80L)));
            int v37 = this.position;
            this.position = v37 + 1;
            arr_b6[v37] = (byte)(((int)(v1 >>> 49)));
            return 8;
        }
        this.require(9);
        byte[] arr_b7 = this.buffer;
        int v38 = this.position;
        this.position = v38 + 1;
        arr_b7[v38] = (byte)(((int)(v1 & 0x7FL | 0x80L)));
        int v39 = this.position;
        this.position = v39 + 1;
        arr_b7[v39] = (byte)(((int)(v1 >>> 7 | 0x80L)));
        int v40 = this.position;
        this.position = v40 + 1;
        arr_b7[v40] = (byte)(((int)(v1 >>> 14 | 0x80L)));
        int v41 = this.position;
        this.position = v41 + 1;
        arr_b7[v41] = (byte)(((int)(v1 >>> 21 | 0x80L)));
        int v42 = this.position;
        this.position = v42 + 1;
        arr_b7[v42] = (byte)(((int)(v1 >>> 28 | 0x80L)));
        int v43 = this.position;
        this.position = v43 + 1;
        arr_b7[v43] = (byte)(((int)(v1 >>> 35 | 0x80L)));
        int v44 = this.position;
        this.position = v44 + 1;
        arr_b7[v44] = (byte)(((int)(v1 >>> 42 | 0x80L)));
        int v45 = this.position;
        this.position = v45 + 1;
        arr_b7[v45] = (byte)(((int)(v1 >>> 49 | 0x80L)));
        int v46 = this.position;
        this.position = v46 + 1;
        arr_b7[v46] = (byte)(((int)(v1 >>> 56)));
        return 9;
    }

    public void writeLong(long v) {
        this.require(8);
        byte[] arr_b = this.buffer;
        int v1 = this.position;
        this.position = v1 + 1;
        arr_b[v1] = (byte)(((int)(v >>> 56)));
        int v2 = this.position;
        this.position = v2 + 1;
        arr_b[v2] = (byte)(((int)(v >>> 0x30)));
        int v3 = this.position;
        this.position = v3 + 1;
        arr_b[v3] = (byte)(((int)(v >>> 40)));
        int v4 = this.position;
        this.position = v4 + 1;
        arr_b[v4] = (byte)(((int)(v >>> 0x20)));
        int v5 = this.position;
        this.position = v5 + 1;
        arr_b[v5] = (byte)(((int)(v >>> 24)));
        int v6 = this.position;
        this.position = v6 + 1;
        arr_b[v6] = (byte)(((int)(v >>> 16)));
        int v7 = this.position;
        this.position = v7 + 1;
        arr_b[v7] = (byte)(((int)(v >>> 8)));
        int v8 = this.position;
        this.position = v8 + 1;
        arr_b[v8] = (byte)(((int)v));
    }

    public void writeLongs(long[] arr_v) {
        for(int v = 0; v < arr_v.length; ++v) {
            this.writeLong(arr_v[v]);
        }
    }

    public void writeLongs(long[] arr_v, boolean z) {
        for(int v = 0; v < arr_v.length; ++v) {
            this.writeLong(arr_v[v], z);
        }
    }

    public void writeShort(int v) {
        this.require(2);
        byte[] arr_b = this.buffer;
        int v1 = this.position;
        this.position = v1 + 1;
        arr_b[v1] = (byte)(v >>> 8);
        int v2 = this.position;
        this.position = v2 + 1;
        arr_b[v2] = (byte)v;
    }

    public void writeShorts(short[] arr_v) {
        for(int v = 0; v < arr_v.length; ++v) {
            this.writeShort(((int)arr_v[v]));
        }
    }

    public void writeString(CharSequence charSequence0) {
        if(charSequence0 == null) {
            this.writeByte(0x80);
            return;
        }
        int v = charSequence0.length();
        if(v == 0) {
            this.writeByte(0x81);
            return;
        }
        this.writeUtf8Length(v + 1);
        int v1 = 0;
        int v2 = this.position;
        if(this.capacity - v2 >= v) {
            byte[] arr_b = this.buffer;
            while(v1 < v) {
                int v3 = charSequence0.charAt(v1);
                if(v3 > 0x7F) {
                    break;
                }
                arr_b[v2] = (byte)v3;
                ++v1;
                ++v2;
            }
            this.position = v2;
        }
        if(v1 < v) {
            this.writeString_slow(charSequence0, v, v1);
        }
    }

    public void writeString(String s) {
        boolean z;
        if(s == null) {
            this.writeByte(0x80);
            return;
        }
        int v = 0;
        int v1 = s.length();
        if(v1 == 0) {
            this.writeByte(0x81);
            return;
        }
        if(v1 <= 1 || v1 >= 0x40) {
            z = false;
        }
        else {
            z = true;
            for(int v2 = 0; v2 < v1; ++v2) {
                if(s.charAt(v2) > 0x7F) {
                    z = false;
                    break;
                }
            }
        }
        if(z) {
            int v3 = this.position;
            if(this.capacity - v3 < v1) {
                this.writeAscii_slow(s, v1);
            }
            else {
                this.getBytes(s, 0, v1, this.buffer, v3);
                this.position += v1;
            }
            int v4 = this.position - 1;
            this.buffer[v4] = (byte)(0x80 | this.buffer[v4]);
            return;
        }
        this.writeUtf8Length(v1 + 1);
        int v5 = this.position;
        if(this.capacity - v5 >= v1) {
            byte[] arr_b = this.buffer;
            while(v < v1) {
                int v6 = s.charAt(v);
                if(v6 > 0x7F) {
                    break;
                }
                arr_b[v5] = (byte)v6;
                ++v;
                ++v5;
            }
            this.position = v5;
        }
        if(v < v1) {
            this.writeString_slow(s, v1, v);
        }
    }

    private void writeString_slow(CharSequence charSequence0, int v, int v1) {
        while(v1 < v) {
            int v2 = this.capacity;
            if(this.position == v2) {
                this.require(Math.min(v2, v - v1));
            }
            int v3 = charSequence0.charAt(v1);
            if(v3 <= 0x7F) {
                int v4 = this.position;
                this.position = v4 + 1;
                this.buffer[v4] = (byte)v3;
            }
            else if(v3 > 0x7FF) {
                int v5 = this.position;
                this.position = v5 + 1;
                this.buffer[v5] = (byte)(v3 >> 12 & 15 | 0xE0);
                this.require(2);
                byte[] arr_b = this.buffer;
                int v6 = this.position;
                this.position = v6 + 1;
                arr_b[v6] = (byte)(v3 >> 6 & 0x3F | 0x80);
                int v7 = this.position;
                this.position = v7 + 1;
                arr_b[v7] = (byte)(v3 & 0x3F | 0x80);
            }
            else {
                int v8 = this.position;
                this.position = v8 + 1;
                this.buffer[v8] = (byte)(v3 >> 6 & 0x1F | 0xC0);
                this.require(1);
                int v9 = this.position;
                this.position = v9 + 1;
                this.buffer[v9] = (byte)(v3 & 0x3F | 0x80);
            }
            ++v1;
        }
    }

    private void writeUtf8Length(int v) {
        if(v >>> 6 == 0) {
            this.require(1);
            int v1 = this.position;
            this.position = v1 + 1;
            this.buffer[v1] = (byte)(v | 0x80);
            return;
        }
        if(v >>> 13 == 0) {
            this.require(2);
            byte[] arr_b = this.buffer;
            int v2 = this.position;
            this.position = v2 + 1;
            arr_b[v2] = (byte)(v | 0xC0);
            int v3 = this.position;
            this.position = v3 + 1;
            arr_b[v3] = (byte)(v >>> 6);
            return;
        }
        if(v >>> 20 == 0) {
            this.require(3);
            byte[] arr_b1 = this.buffer;
            int v4 = this.position;
            this.position = v4 + 1;
            arr_b1[v4] = (byte)(v | 0xC0);
            int v5 = this.position;
            this.position = v5 + 1;
            arr_b1[v5] = (byte)(v >>> 6 | 0x80);
            int v6 = this.position;
            this.position = v6 + 1;
            arr_b1[v6] = (byte)(v >>> 13);
            return;
        }
        if(v >>> 27 == 0) {
            this.require(4);
            byte[] arr_b2 = this.buffer;
            int v7 = this.position;
            this.position = v7 + 1;
            arr_b2[v7] = (byte)(v | 0xC0);
            int v8 = this.position;
            this.position = v8 + 1;
            arr_b2[v8] = (byte)(v >>> 6 | 0x80);
            int v9 = this.position;
            this.position = v9 + 1;
            arr_b2[v9] = (byte)(v >>> 13 | 0x80);
            int v10 = this.position;
            this.position = v10 + 1;
            arr_b2[v10] = (byte)(v >>> 20);
            return;
        }
        this.require(5);
        byte[] arr_b3 = this.buffer;
        int v11 = this.position;
        this.position = v11 + 1;
        arr_b3[v11] = (byte)(v | 0xC0);
        int v12 = this.position;
        this.position = v12 + 1;
        arr_b3[v12] = (byte)(v >>> 6 | 0x80);
        int v13 = this.position;
        this.position = v13 + 1;
        arr_b3[v13] = (byte)(v >>> 13 | 0x80);
        int v14 = this.position;
        this.position = v14 + 1;
        arr_b3[v14] = (byte)(v >>> 20 | 0x80);
        int v15 = this.position;
        this.position = v15 + 1;
        arr_b3[v15] = (byte)(v >>> 27);
    }
}

