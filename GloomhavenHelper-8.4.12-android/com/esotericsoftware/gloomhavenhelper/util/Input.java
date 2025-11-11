package com.esotericsoftware.gloomhavenhelper.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Input extends InputStream {
    protected byte[] buffer;
    protected int capacity;
    protected char[] chars;
    protected InputStream inputStream;
    protected int limit;
    static final int maxArraySize = 0x7FFFFFF7;
    protected int position;
    protected long total;

    public Input() {
        this.chars = new char[0x20];
    }

    public Input(int v) {
        this.chars = new char[0x20];
        this.capacity = v;
        this.buffer = new byte[v];
    }

    public Input(InputStream inputStream0) {
        this(0x1000);
        if(inputStream0 == null) {
            throw new IllegalArgumentException("inputStream cannot be null.");
        }
        this.inputStream = inputStream0;
    }

    public Input(InputStream inputStream0, int v) {
        this(v);
        if(inputStream0 == null) {
            throw new IllegalArgumentException("inputStream cannot be null.");
        }
        this.inputStream = inputStream0;
    }

    public Input(byte[] arr_b) {
        this.chars = new char[0x20];
        this.setBuffer(arr_b, 0, arr_b.length);
    }

    public Input(byte[] arr_b, int v, int v1) {
        this.chars = new char[0x20];
        this.setBuffer(arr_b, v, v1);
    }

    @Override
    public int available() throws IOException {
        int v = this.limit - this.position;
        return this.inputStream == null ? v : v + this.inputStream.available();
    }

    public boolean canReadInt() {
        if(this.limit - this.position >= 5) {
            return true;
        }
        if(this.optional(5) <= 0) {
            return false;
        }
        byte[] arr_b = this.buffer;
        int v = this.position + 1;
        if((arr_b[this.position] & 0x80) == 0) {
            return true;
        }
        int v1 = this.limit;
        if(v == v1) {
            return false;
        }
        if((arr_b[v] & 0x80) == 0) {
            return true;
        }
        if(v + 1 == v1) {
            return false;
        }
        if((arr_b[v + 1] & 0x80) == 0) {
            return true;
        }
        if(v + 2 == v1) {
            return false;
        }
        return (arr_b[v + 2] & 0x80) == 0 ? true : v + 3 != v1;
    }

    public boolean canReadLong() {
        if(this.limit - this.position >= 9) {
            return true;
        }
        if(this.optional(5) <= 0) {
            return false;
        }
        byte[] arr_b = this.buffer;
        int v = this.position + 1;
        if((arr_b[this.position] & 0x80) == 0) {
            return true;
        }
        int v1 = this.limit;
        if(v == v1) {
            return false;
        }
        if((arr_b[v] & 0x80) == 0) {
            return true;
        }
        if(v + 1 == v1) {
            return false;
        }
        if((arr_b[v + 1] & 0x80) == 0) {
            return true;
        }
        if(v + 2 == v1) {
            return false;
        }
        if((arr_b[v + 2] & 0x80) == 0) {
            return true;
        }
        if(v + 3 == v1) {
            return false;
        }
        if((arr_b[v + 3] & 0x80) == 0) {
            return true;
        }
        if(v + 4 == v1) {
            return false;
        }
        if((arr_b[v + 4] & 0x80) == 0) {
            return true;
        }
        if(v + 5 == v1) {
            return false;
        }
        if((arr_b[v + 5] & 0x80) == 0) {
            return true;
        }
        if(v + 6 == v1) {
            return false;
        }
        return (arr_b[v + 6] & 0x80) == 0 ? true : v + 7 != v1;
    }

    @Override
    public void close() {
        InputStream inputStream0 = this.inputStream;
        if(inputStream0 != null) {
            try {
                inputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
    }

    public boolean end() {
        return this.optional(1) <= 0;
    }

    protected int fill(byte[] arr_b, int v, int v1) {
        InputStream inputStream0 = this.inputStream;
        if(inputStream0 == null) {
            return -1;
        }
        try {
            return inputStream0.read(arr_b, v, v1);
        }
        catch(IOException iOException0) {
            throw new RuntimeException(iOException0);
        }
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public int limit() {
        return this.limit;
    }

    private int optional(int v) {
        int v1 = this.limit - this.position;
        if(v1 >= v) {
            return v;
        }
        int v2 = Math.min(v, this.capacity);
        int v3 = this.fill(this.buffer, this.limit, this.capacity - this.limit);
        if(v3 == -1) {
            return v1 == 0 ? -1 : Math.min(v1, v2);
        }
        int v4 = v1 + v3;
        if(v4 >= v2) {
            this.limit += v3;
            return v2;
        }
        System.arraycopy(this.buffer, this.position, this.buffer, 0, v4);
        this.total += (long)this.position;
        this.position = 0;
        do {
            int v5 = this.fill(this.buffer, v4, this.capacity - v4);
            if(v5 == -1) {
                break;
            }
            v4 += v5;
        }
        while(v4 < v2);
        this.limit = v4;
        return v4 == 0 ? -1 : Math.min(v4, v2);
    }

    public int position() {
        return this.position;
    }

    @Override
    public int read() {
        if(this.optional(1) <= 0) {
            return -1;
        }
        int v = this.position;
        this.position = v + 1;
        return this.buffer[v] & 0xFF;
    }

    @Override
    public int read(byte[] arr_b) {
        return this.read(arr_b, 0, arr_b.length);
    }

    @Override
    public int read(byte[] arr_b, int v, int v1) {
        if(arr_b == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int v2 = Math.min(this.limit - this.position, v1);
        int v3 = v1;
        do {
            System.arraycopy(this.buffer, this.position, arr_b, v, v2);
            this.position += v2;
            v3 -= v2;
            if(v3 == 0) {
                break;
            }
            v += v2;
            v2 = this.optional(v3);
            if(v2 == -1) {
                return v1 == v3 ? -1 : v1 - v3;
            }
        }
        while(this.position != this.limit);
        return v1 - v3;
    }

    private String readAscii() {
        byte[] arr_b = this.buffer;
        int v = this.position;
        int v1 = v - 1;
        int v2 = this.limit;
        while(true) {
            if(v == v2) {
                return this.readAscii_slow();
            }
            if((arr_b[v] & 0x80) != 0) {
                arr_b[v] = (byte)(arr_b[v] & 0x7F);
                try {
                    String s = new String(arr_b, v1, v + 1 - v1, "ASCII");
                    arr_b[v] = (byte)(arr_b[v] | 0x80);
                    this.position = v + 1;
                    return s;
                }
                catch(UnsupportedEncodingException unused_ex) {
                    throw new RuntimeException();
                }
            }
            ++v;
        }
    }

    private String readAscii_slow() {
        int v5;
        --this.position;
        int v = this.limit - this.position;
        if(v > this.chars.length) {
            this.chars = new char[v * 2];
        }
        char[] arr_c = this.chars;
        byte[] arr_b = this.buffer;
        int v1 = this.position;
        int v2 = this.limit;
        for(int v3 = 0; v1 < v2; ++v3) {
            arr_c[v3] = (char)arr_b[v1];
            ++v1;
        }
        this.position = this.limit;
        while(true) {
            this.require(1);
            int v4 = this.position;
            this.position = v4 + 1;
            v5 = arr_b[v4];
            if(v == arr_c.length) {
                char[] arr_c1 = new char[v * 2];
                System.arraycopy(arr_c, 0, arr_c1, 0, v);
                this.chars = arr_c1;
                arr_c = arr_c1;
            }
            if((v5 & 0x80) == 0x80) {
                break;
            }
            arr_c[v] = (char)v5;
            ++v;
        }
        arr_c[v] = (char)(v5 & 0x7F);
        return new String(arr_c, 0, v + 1);
    }

    public boolean readBoolean() {
        this.require(1);
        int v = this.position;
        this.position = v + 1;
        return this.buffer[v] == 1;
    }

    public byte readByte() {
        this.require(1);
        int v = this.position;
        this.position = v + 1;
        return this.buffer[v];
    }

    public int readByteUnsigned() {
        this.require(1);
        int v = this.position;
        this.position = v + 1;
        return this.buffer[v] & 0xFF;
    }

    public void readBytes(byte[] arr_b) {
        this.readBytes(arr_b, 0, arr_b.length);
    }

    public void readBytes(byte[] arr_b, int v, int v1) {
        if(arr_b == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int v2 = Math.min(this.limit - this.position, v1);
        while(true) {
            System.arraycopy(this.buffer, this.position, arr_b, v, v2);
            this.position += v2;
            v1 -= v2;
            if(v1 == 0) {
                break;
            }
            v += v2;
            v2 = Math.min(v1, this.capacity);
            this.require(v2);
        }
    }

    public byte[] readBytes(int v) {
        byte[] arr_b = new byte[v];
        this.readBytes(arr_b, 0, v);
        return arr_b;
    }

    public char readChar() {
        this.require(2);
        int v = this.position;
        int v1 = this.position;
        this.position = v1 + 1;
        return (char)(this.buffer[v1] & 0xFF | (this.buffer[v] & 0xFF) << 8);
    }

    public char[] readChars(int v) {
        char[] arr_c = new char[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_c[v1] = this.readChar();
        }
        return arr_c;
    }

    public double readDouble() {
        return Double.longBitsToDouble(this.readLong());
    }

    public double readDouble(double f, boolean z) {
        return ((double)this.readLong(z)) / f;
    }

    public double[] readDoubles(int v) {
        double[] arr_f = new double[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_f[v1] = this.readDouble();
        }
        return arr_f;
    }

    public float readFloat() {
        return Float.intBitsToFloat(this.readInt());
    }

    public float readFloat(float f, boolean z) {
        return ((float)this.readInt(z)) / f;
    }

    public float[] readFloats(int v) {
        float[] arr_f = new float[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_f[v1] = this.readFloat();
        }
        return arr_f;
    }

    public static int readInt(byte[] arr_b, int v) {
        return arr_b[v + 3] & 0xFF | ((arr_b[v] & 0xFF) << 24 | (arr_b[v + 1] & 0xFF) << 16 | (arr_b[v + 2] & 0xFF) << 8);
    }

    public int readInt() {
        this.require(4);
        int v = this.position;
        this.position = v + 4;
        return this.buffer[v + 3] & 0xFF | ((this.buffer[v] & 0xFF) << 24 | (this.buffer[v + 1] & 0xFF) << 16 | (this.buffer[v + 2] & 0xFF) << 8);
    }

    public int readInt(boolean z) {
        if(this.require(1) < 5) {
            return this.readInt_slow(z);
        }
        byte[] arr_b = this.buffer;
        int v = this.position;
        this.position = v + 1;
        int v1 = arr_b[v];
        int v2 = v1 & 0x7F;
        if((v1 & 0x80) != 0) {
            int v3 = this.position;
            this.position = v3 + 1;
            int v4 = arr_b[v3];
            v2 |= (v4 & 0x7F) << 7;
            if((v4 & 0x80) != 0) {
                int v5 = this.position;
                this.position = v5 + 1;
                int v6 = arr_b[v5];
                v2 |= (v6 & 0x7F) << 14;
                if((v6 & 0x80) != 0) {
                    int v7 = this.position;
                    this.position = v7 + 1;
                    int v8 = arr_b[v7];
                    v2 |= (v8 & 0x7F) << 21;
                    if((v8 & 0x80) != 0) {
                        int v9 = this.position;
                        this.position = v9 + 1;
                        v2 |= (arr_b[v9] & 0x7F) << 28;
                    }
                }
            }
        }
        return z ? v2 : v2 >>> 1 ^ -(v2 & 1);
    }

    private int readInt_slow(boolean z) {
        int v = this.position;
        this.position = v + 1;
        int v1 = this.buffer[v];
        int v2 = v1 & 0x7F;
        if((v1 & 0x80) != 0) {
            this.require(1);
            byte[] arr_b = this.buffer;
            int v3 = this.position;
            this.position = v3 + 1;
            int v4 = arr_b[v3];
            v2 |= (v4 & 0x7F) << 7;
            if((v4 & 0x80) != 0) {
                this.require(1);
                int v5 = this.position;
                this.position = v5 + 1;
                int v6 = arr_b[v5];
                v2 |= (v6 & 0x7F) << 14;
                if((v6 & 0x80) != 0) {
                    this.require(1);
                    int v7 = this.position;
                    this.position = v7 + 1;
                    int v8 = arr_b[v7];
                    v2 |= (v8 & 0x7F) << 21;
                    if((v8 & 0x80) != 0) {
                        this.require(1);
                        int v9 = this.position;
                        this.position = v9 + 1;
                        v2 |= (arr_b[v9] & 0x7F) << 28;
                    }
                }
            }
        }
        return z ? v2 : v2 >>> 1 ^ -(v2 & 1);
    }

    public int[] readInts(int v) {
        int[] arr_v = new int[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = this.readInt();
        }
        return arr_v;
    }

    public int[] readInts(int v, boolean z) {
        int[] arr_v = new int[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = this.readInt(z);
        }
        return arr_v;
    }

    public long readLong() {
        this.require(8);
        int v = this.position;
        int v1 = this.position;
        int v2 = this.position;
        int v3 = this.position;
        int v4 = this.position;
        int v5 = this.position;
        int v6 = this.position;
        int v7 = this.position;
        this.position = v7 + 1;
        return ((long)(this.buffer[v7] & 0xFF)) | (((long)this.buffer[v]) << 56 | ((long)(this.buffer[v1] & 0xFF)) << 0x30 | ((long)(this.buffer[v2] & 0xFF)) << 40 | ((long)(this.buffer[v3] & 0xFF)) << 0x20 | ((long)(this.buffer[v4] & 0xFF)) << 24 | ((long)((this.buffer[v5] & 0xFF) << 16)) | ((long)((this.buffer[v6] & 0xFF) << 8)));
    }

    public long readLong(boolean z) {
        if(this.require(1) < 9) {
            return this.readLong_slow(z);
        }
        byte[] arr_b = this.buffer;
        int v = this.position;
        this.position = v + 1;
        int v1 = arr_b[v];
        long v2 = (long)(v1 & 0x7F);
        if((v1 & 0x80) != 0) {
            int v3 = this.position;
            this.position = v3 + 1;
            int v4 = arr_b[v3];
            v2 |= (long)((v4 & 0x7F) << 7);
            if((v4 & 0x80) != 0) {
                int v5 = this.position;
                this.position = v5 + 1;
                int v6 = arr_b[v5];
                v2 |= (long)((v6 & 0x7F) << 14);
                if((v6 & 0x80) != 0) {
                    int v7 = this.position;
                    this.position = v7 + 1;
                    int v8 = arr_b[v7];
                    v2 |= (long)((v8 & 0x7F) << 21);
                    if((v8 & 0x80) != 0) {
                        int v9 = this.position;
                        this.position = v9 + 1;
                        int v10 = arr_b[v9];
                        v2 |= ((long)(v10 & 0x7F)) << 28;
                        if((v10 & 0x80) != 0) {
                            int v11 = this.position;
                            this.position = v11 + 1;
                            int v12 = arr_b[v11];
                            v2 |= ((long)(v12 & 0x7F)) << 35;
                            if((v12 & 0x80) != 0) {
                                int v13 = this.position;
                                this.position = v13 + 1;
                                int v14 = arr_b[v13];
                                v2 |= ((long)(v14 & 0x7F)) << 42;
                                if((v14 & 0x80) != 0) {
                                    int v15 = this.position;
                                    this.position = v15 + 1;
                                    int v16 = arr_b[v15];
                                    v2 |= ((long)(v16 & 0x7F)) << 49;
                                    if((v16 & 0x80) != 0) {
                                        int v17 = this.position;
                                        this.position = v17 + 1;
                                        v2 |= ((long)arr_b[v17]) << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return z ? v2 : v2 >>> 1 ^ -(v2 & 1L);
    }

    private long readLong_slow(boolean z) {
        int v = this.position;
        this.position = v + 1;
        int v1 = this.buffer[v];
        long v2 = (long)(v1 & 0x7F);
        if((v1 & 0x80) != 0) {
            this.require(1);
            byte[] arr_b = this.buffer;
            int v3 = this.position;
            this.position = v3 + 1;
            int v4 = arr_b[v3];
            v2 |= (long)((v4 & 0x7F) << 7);
            if((v4 & 0x80) != 0) {
                this.require(1);
                int v5 = this.position;
                this.position = v5 + 1;
                int v6 = arr_b[v5];
                v2 |= (long)((v6 & 0x7F) << 14);
                if((v6 & 0x80) != 0) {
                    this.require(1);
                    int v7 = this.position;
                    this.position = v7 + 1;
                    int v8 = arr_b[v7];
                    v2 |= (long)((v8 & 0x7F) << 21);
                    if((v8 & 0x80) != 0) {
                        this.require(1);
                        int v9 = this.position;
                        this.position = v9 + 1;
                        int v10 = arr_b[v9];
                        v2 |= ((long)(v10 & 0x7F)) << 28;
                        if((v10 & 0x80) != 0) {
                            this.require(1);
                            int v11 = this.position;
                            this.position = v11 + 1;
                            int v12 = arr_b[v11];
                            v2 |= ((long)(v12 & 0x7F)) << 35;
                            if((v12 & 0x80) != 0) {
                                this.require(1);
                                int v13 = this.position;
                                this.position = v13 + 1;
                                int v14 = arr_b[v13];
                                v2 |= ((long)(v14 & 0x7F)) << 42;
                                if((v14 & 0x80) != 0) {
                                    this.require(1);
                                    int v15 = this.position;
                                    this.position = v15 + 1;
                                    int v16 = arr_b[v15];
                                    v2 |= ((long)(v16 & 0x7F)) << 49;
                                    if((v16 & 0x80) != 0) {
                                        this.require(1);
                                        int v17 = this.position;
                                        this.position = v17 + 1;
                                        v2 |= ((long)arr_b[v17]) << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return z ? v2 : -(v2 & 1L) ^ v2 >>> 1;
    }

    public long[] readLongs(int v) {
        long[] arr_v = new long[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = this.readLong();
        }
        return arr_v;
    }

    public long[] readLongs(int v, boolean z) {
        long[] arr_v = new long[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = this.readLong(z);
        }
        return arr_v;
    }

    public short readShort() {
        this.require(2);
        int v = this.position;
        int v1 = this.position;
        this.position = v1 + 1;
        return (short)(this.buffer[v1] & 0xFF | (this.buffer[v] & 0xFF) << 8);
    }

    public int readShortUnsigned() {
        this.require(2);
        int v = this.position;
        int v1 = this.position;
        this.position = v1 + 1;
        return this.buffer[v1] & 0xFF | (this.buffer[v] & 0xFF) << 8;
    }

    public short[] readShorts(int v) {
        short[] arr_v = new short[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = this.readShort();
        }
        return arr_v;
    }

    public String readString() {
        int v = this.require(1);
        int v1 = this.position;
        this.position = v1 + 1;
        int v2 = this.buffer[v1];
        if((v2 & 0x80) == 0) {
            return this.readAscii();
        }
        int v3 = v < 5 ? this.readUtf8Length_slow(v2) : this.readUtf8Length(v2);
        switch(v3) {
            case 0: {
                return null;
            }
            case 1: {
                return "";
            }
            default: {
                if(this.chars.length < v3 - 1) {
                    this.chars = new char[v3 - 1];
                }
                this.readUtf8(v3 - 1);
                return new String(this.chars, 0, v3 - 1);
            }
        }
    }

    public StringBuilder readStringBuilder() {
        int v = this.require(1);
        int v1 = this.position;
        this.position = v1 + 1;
        int v2 = this.buffer[v1];
        if((v2 & 0x80) == 0) {
            return new StringBuilder(this.readAscii());
        }
        int v3 = v < 5 ? this.readUtf8Length_slow(v2) : this.readUtf8Length(v2);
        switch(v3) {
            case 0: {
                return null;
            }
            case 1: {
                return new StringBuilder("");
            }
            default: {
                if(this.chars.length < v3 - 1) {
                    this.chars = new char[v3 - 1];
                }
                this.readUtf8(v3 - 1);
                StringBuilder stringBuilder0 = new StringBuilder(v3 - 1);
                stringBuilder0.append(this.chars, 0, v3 - 1);
                return stringBuilder0;
            }
        }
    }

    private void readUtf8(int v) {
        byte[] arr_b = this.buffer;
        char[] arr_c = this.chars;
        int v1 = Math.min(this.require(1), v);
        int v2 = this.position;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            int v4 = arr_b[v2];
            if(v4 < 0) {
                break;
            }
            arr_c[v3] = (char)v4;
            ++v2;
        }
        this.position = v2;
        if(v3 < v) {
            this.readUtf8_slow(v, v3);
        }
    }

    private int readUtf8Length(int v) {
        int v1 = v & 0x3F;
        if((v & 0x40) != 0) {
            byte[] arr_b = this.buffer;
            int v2 = this.position;
            this.position = v2 + 1;
            int v3 = arr_b[v2];
            v1 |= (v3 & 0x7F) << 6;
            if((v3 & 0x80) != 0) {
                int v4 = this.position;
                this.position = v4 + 1;
                int v5 = arr_b[v4];
                v1 |= (v5 & 0x7F) << 13;
                if((v5 & 0x80) != 0) {
                    int v6 = this.position;
                    this.position = v6 + 1;
                    int v7 = arr_b[v6];
                    v1 |= (v7 & 0x7F) << 20;
                    if((v7 & 0x80) != 0) {
                        int v8 = this.position;
                        this.position = v8 + 1;
                        return v1 | (arr_b[v8] & 0x7F) << 27;
                    }
                }
            }
        }
        return v1;
    }

    private int readUtf8Length_slow(int v) {
        int v1 = v & 0x3F;
        if((v & 0x40) != 0) {
            this.require(1);
            byte[] arr_b = this.buffer;
            int v2 = this.position;
            this.position = v2 + 1;
            int v3 = arr_b[v2];
            v1 |= (v3 & 0x7F) << 6;
            if((v3 & 0x80) != 0) {
                this.require(1);
                int v4 = this.position;
                this.position = v4 + 1;
                int v5 = arr_b[v4];
                v1 |= (v5 & 0x7F) << 13;
                if((v5 & 0x80) != 0) {
                    this.require(1);
                    int v6 = this.position;
                    this.position = v6 + 1;
                    int v7 = arr_b[v6];
                    v1 |= (v7 & 0x7F) << 20;
                    if((v7 & 0x80) != 0) {
                        this.require(1);
                        int v8 = this.position;
                        this.position = v8 + 1;
                        return v1 | (arr_b[v8] & 0x7F) << 27;
                    }
                }
            }
        }
        return v1;
    }

    private void readUtf8_slow(int v, int v1) {
        char[] arr_c = this.chars;
        byte[] arr_b = this.buffer;
        while(v1 < v) {
            if(this.position == this.limit) {
                this.require(1);
            }
            int v2 = this.position;
            this.position = v2 + 1;
            int v3 = arr_b[v2] & 0xFF;
            switch(v3 >> 4) {
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: {
                    arr_c[v1] = (char)v3;
                    break;
                }
                case 12: 
                case 13: {
                    if(this.position == this.limit) {
                        this.require(1);
                    }
                    int v4 = this.position;
                    this.position = v4 + 1;
                    arr_c[v1] = (char)((v3 & 0x1F) << 6 | arr_b[v4] & 0x3F);
                    break;
                }
                case 14: {
                    this.require(2);
                    int v5 = this.position;
                    int v6 = this.position;
                    this.position = v6 + 1;
                    arr_c[v1] = (char)((v3 & 15) << 12 | (arr_b[v5] & 0x3F) << 6 | arr_b[v6] & 0x3F);
                }
            }
            ++v1;
        }
    }

    protected int require(int v) {
        int v1 = this.limit;
        int v2 = v1 - this.position;
        if(v2 >= v) {
            return v2;
        }
        int v3 = this.capacity;
        if(v > v3) {
            throw new RuntimeException("Buffer too small: capacity: " + this.capacity + ", required: " + v);
        }
        if(v2 > 0) {
            int v4 = this.fill(this.buffer, v1, v3 - v1);
            if(v4 == -1) {
                throw new RuntimeException("Buffer underflow.");
            }
            v2 += v4;
            if(v2 >= v) {
                this.limit += v4;
                return v2;
            }
        }
        System.arraycopy(this.buffer, this.position, this.buffer, 0, v2);
        this.total += (long)this.position;
        this.position = 0;
        do {
            int v5 = this.fill(this.buffer, v2, this.capacity - v2);
            if(v5 == -1) {
                if(v2 < v) {
                    throw new RuntimeException("Buffer underflow.");
                }
                break;
            }
            v2 += v5;
        }
        while(v2 < v);
        this.limit = v2;
        return v2;
    }

    public void rewind() {
        this.position = 0;
        this.total = 0L;
    }

    public void setBuffer(byte[] arr_b) {
        this.setBuffer(arr_b, 0, arr_b.length);
    }

    public void setBuffer(byte[] arr_b, int v, int v1) {
        if(arr_b == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        this.buffer = arr_b;
        this.position = v;
        this.limit = v + v1;
        this.capacity = arr_b.length;
        this.total = 0L;
        this.inputStream = null;
    }

    public void setInputStream(InputStream inputStream0) {
        this.inputStream = inputStream0;
        this.limit = 0;
        this.rewind();
    }

    public void setLimit(int v) {
        this.limit = v;
    }

    public void setPosition(int v) {
        this.position = v;
    }

    public void setTotal(long v) {
        this.total = v;
    }

    @Override
    public long skip(long v) {
        for(long v1 = v; v1 > 0L; v1 -= (long)v2) {
            int v2 = (int)Math.min(0x7FFFFFF7L, v1);
            this.skip(v2);
        }
        return v;
    }

    public void skip(int v) {
        int v1 = Math.min(this.limit - this.position, v);
        while(true) {
            this.position += v1;
            v -= v1;
            if(v == 0) {
                break;
            }
            v1 = Math.min(v, this.capacity);
            this.require(v1);
        }
    }

    public long total() {
        return this.total + ((long)this.position);
    }
}

