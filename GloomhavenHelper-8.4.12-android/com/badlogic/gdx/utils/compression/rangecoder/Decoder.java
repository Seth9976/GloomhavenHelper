package com.badlogic.gdx.utils.compression.rangecoder;

import java.io.IOException;
import java.io.InputStream;

public class Decoder {
    int Code;
    int Range;
    InputStream Stream;
    static final int kBitModelTotal = 0x800;
    static final int kNumBitModelTotalBits = 11;
    static final int kNumMoveBits = 5;
    static final int kTopMask = 0xFF000000;

    public int DecodeBit(short[] arr_v, int v) throws IOException {
        int v1 = arr_v[v];
        int v2 = this.Range;
        int v3 = (v2 >>> 11) * v1;
        int v4 = this.Code;
        if((v4 ^ 0x80000000) < (0x80000000 ^ v3)) {
            this.Range = v3;
            arr_v[v] = (short)(v1 + (0x800 - v1 >>> 5));
            if((this.Range & 0xFF000000) == 0) {
                this.Code = v4 << 8 | this.Stream.read();
                this.Range <<= 8;
            }
            return 0;
        }
        this.Range = v2 - v3;
        this.Code = v4 - v3;
        arr_v[v] = (short)(v1 - (v1 >>> 5));
        if((this.Range & 0xFF000000) == 0) {
            this.Code = this.Code << 8 | this.Stream.read();
            this.Range <<= 8;
        }
        return 1;
    }

    public final int DecodeDirectBits(int v) throws IOException {
        int v1 = 0;
        while(v != 0) {
            this.Range >>>= 1;
            int v2 = this.Code - this.Range >>> 0x1F;
            this.Code -= v2 - 1 & this.Range;
            v1 = v1 << 1 | 1 - v2;
            if((0xFF000000 & this.Range) == 0) {
                this.Code = this.Code << 8 | this.Stream.read();
                this.Range <<= 8;
            }
            --v;
        }
        return v1;
    }

    public final void Init() throws IOException {
        this.Code = 0;
        this.Range = -1;
        for(int v = 0; v < 5; ++v) {
            this.Code = this.Code << 8 | this.Stream.read();
        }
    }

    public static void InitBitModels(short[] arr_v) {
        for(int v = 0; v < arr_v.length; ++v) {
            arr_v[v] = 0x400;
        }
    }

    public final void ReleaseStream() {
        this.Stream = null;
    }

    public final void SetStream(InputStream inputStream0) {
        this.Stream = inputStream0;
    }
}

