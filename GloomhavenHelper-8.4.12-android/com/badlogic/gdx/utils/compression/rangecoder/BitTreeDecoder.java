package com.badlogic.gdx.utils.compression.rangecoder;

import java.io.IOException;

public class BitTreeDecoder {
    short[] Models;
    int NumBitLevels;

    public BitTreeDecoder(int v) {
        this.NumBitLevels = v;
        this.Models = new short[1 << v];
    }

    public int Decode(Decoder decoder0) throws IOException {
        int v = this.NumBitLevels;
        int v1 = 1;
        while(v != 0) {
            v1 = decoder0.DecodeBit(this.Models, v1) + (v1 << 1);
            --v;
        }
        return v1 - (1 << this.NumBitLevels);
    }

    public void Init() {
        Decoder.InitBitModels(this.Models);
    }

    public static int ReverseDecode(short[] arr_v, int v, Decoder decoder0, int v1) throws IOException {
        int v3 = 0;
        int v4 = 1;
        for(int v2 = 0; v2 < v1; ++v2) {
            int v5 = decoder0.DecodeBit(arr_v, v + v4);
            v4 = (v4 << 1) + v5;
            v3 |= v5 << v2;
        }
        return v3;
    }

    public int ReverseDecode(Decoder decoder0) throws IOException {
        int v1 = 0;
        int v2 = 1;
        for(int v = 0; v < this.NumBitLevels; ++v) {
            int v3 = decoder0.DecodeBit(this.Models, v2);
            v2 = (v2 << 1) + v3;
            v1 |= v3 << v;
        }
        return v1;
    }
}

