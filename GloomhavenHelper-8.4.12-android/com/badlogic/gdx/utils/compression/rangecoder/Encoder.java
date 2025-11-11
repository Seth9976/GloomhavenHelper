package com.badlogic.gdx.utils.compression.rangecoder;

import java.io.IOException;
import java.io.OutputStream;

public class Encoder {
    long Low;
    private static int[] ProbPrices = null;
    int Range;
    OutputStream Stream;
    int _cache;
    int _cacheSize;
    long _position;
    static final int kBitModelTotal = 0x800;
    static final int kNumBitModelTotalBits = 11;
    public static final int kNumBitPriceShiftBits = 6;
    static final int kNumMoveBits = 5;
    static final int kNumMoveReducingBits = 2;
    static final int kTopMask = 0xFF000000;

    static {
        Encoder.ProbPrices = new int[0x200];
        for(int v = 8; v >= 0; --v) {
            int v1 = 8 - v;
            int v2 = 1 << v1;
            int v3 = 1 << 9 - v;
            while(v2 < v3) {
                Encoder.ProbPrices[v2] = (v << 6) + (v3 - v2 << 6 >>> v1);
                ++v2;
            }
        }
    }

    public void Encode(short[] arr_v, int v, int v1) throws IOException {
        int v2 = arr_v[v];
        int v3 = this.Range;
        int v4 = (v3 >>> 11) * v2;
        if(v1 == 0) {
            this.Range = v4;
            arr_v[v] = (short)(v2 + (0x800 - v2 >>> 5));
        }
        else {
            this.Low += ((long)v4) & 0xFFFFFFFFL;
            this.Range = v3 - v4;
            arr_v[v] = (short)(v2 - (v2 >>> 5));
        }
        int v5 = this.Range;
        if((0xFF000000 & v5) == 0) {
            this.Range = v5 << 8;
            this.ShiftLow();
        }
    }

    public void EncodeDirectBits(int v, int v1) throws IOException {
        for(int v2 = v1 - 1; v2 >= 0; --v2) {
            this.Range >>>= 1;
            if((v >>> v2 & 1) == 1) {
                this.Low += (long)this.Range;
            }
            int v3 = this.Range;
            if((0xFF000000 & v3) == 0) {
                this.Range = v3 << 8;
                this.ShiftLow();
            }
        }
    }

    public void FlushData() throws IOException {
        for(int v = 0; v < 5; ++v) {
            this.ShiftLow();
        }
    }

    public void FlushStream() throws IOException {
        this.Stream.flush();
    }

    public static int GetPrice(int v, int v1) {
        return Encoder.ProbPrices[((v - v1 ^ -v1) & 0x7FF) >>> 2];
    }

    public static int GetPrice0(int v) {
        return Encoder.ProbPrices[v >>> 2];
    }

    public static int GetPrice1(int v) {
        return Encoder.ProbPrices[0x800 - v >>> 2];
    }

    public long GetProcessedSizeAdd() {
        return ((long)this._cacheSize) + this._position + 4L;
    }

    public void Init() {
        this._position = 0L;
        this.Low = 0L;
        this.Range = -1;
        this._cacheSize = 1;
        this._cache = 0;
    }

    public static void InitBitModels(short[] arr_v) {
        for(int v = 0; v < arr_v.length; ++v) {
            arr_v[v] = 0x400;
        }
    }

    public void ReleaseStream() {
        this.Stream = null;
    }

    public void SetStream(OutputStream outputStream0) {
        this.Stream = outputStream0;
    }

    public void ShiftLow() throws IOException {
        long v = this.Low;
        if(((int)(v >>> 0x20)) != 0 || v < 0xFF000000L) {
            this._position += (long)this._cacheSize;
            int v1 = this._cache;
            do {
                this.Stream.write(v1 + ((int)(v >>> 0x20)));
                v1 = 0xFF;
                int v2 = this._cacheSize - 1;
                this._cacheSize = v2;
            }
            while(v2 != 0);
            this._cache = ((int)this.Low) >>> 24;
        }
        ++this._cacheSize;
        this.Low = (this.Low & 0xFFFFFFL) << 8;
    }
}

