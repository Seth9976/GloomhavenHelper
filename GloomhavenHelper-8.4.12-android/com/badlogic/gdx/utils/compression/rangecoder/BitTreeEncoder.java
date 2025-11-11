package com.badlogic.gdx.utils.compression.rangecoder;

import java.io.IOException;

public class BitTreeEncoder {
    short[] Models;
    int NumBitLevels;

    public BitTreeEncoder(int v) {
        this.NumBitLevels = v;
        this.Models = new short[1 << v];
    }

    public void Encode(Encoder encoder0, int v) throws IOException {
        int v1 = this.NumBitLevels;
        for(int v2 = 1; v1 != 0; v2 = v2 << 1 | v3) {
            --v1;
            int v3 = v >>> v1 & 1;
            encoder0.Encode(this.Models, v2, v3);
        }
    }

    public int GetPrice(int v) {
        int v1 = this.NumBitLevels;
        int v2 = 0;
        for(int v3 = 1; v1 != 0; v3 = (v3 << 1) + v4) {
            --v1;
            int v4 = v >>> v1 & 1;
            v2 += Encoder.GetPrice(this.Models[v3], v4);
        }
        return v2;
    }

    public void Init() {
        Decoder.InitBitModels(this.Models);
    }

    public static void ReverseEncode(short[] arr_v, int v, Encoder encoder0, int v1, int v2) throws IOException {
        int v4 = 1;
        for(int v3 = 0; v3 < v1; ++v3) {
            encoder0.Encode(arr_v, v + v4, v2 & 1);
            v4 = v4 << 1 | v2 & 1;
            v2 >>= 1;
        }
    }

    public void ReverseEncode(Encoder encoder0, int v) throws IOException {
        int v2 = 1;
        for(int v1 = 0; v1 < this.NumBitLevels; ++v1) {
            encoder0.Encode(this.Models, v2, v & 1);
            v2 = v2 << 1 | v & 1;
            v >>= 1;
        }
    }

    public static int ReverseGetPrice(short[] arr_v, int v, int v1, int v2) {
        int v3 = 0;
        int v4 = 1;
        while(v1 != 0) {
            int v5 = v2 & 1;
            v2 >>>= 1;
            v3 += Encoder.GetPrice(arr_v[v + v4], v5);
            v4 = v4 << 1 | v5;
            --v1;
        }
        return v3;
    }

    public int ReverseGetPrice(int v) {
        int v1 = this.NumBitLevels;
        int v2 = 0;
        int v3 = 1;
        while(v1 != 0) {
            int v4 = v & 1;
            v >>>= 1;
            v2 += Encoder.GetPrice(this.Models[v3], v4);
            v3 = v3 << 1 | v4;
            --v1;
        }
        return v2;
    }
}

