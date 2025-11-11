package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec.CryptoInfo;

public final class zzir {
    public byte[] iv;
    private byte[] key;
    private int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    private int numSubSamples;
    private int zzamf;
    private int zzamg;
    private final MediaCodec.CryptoInfo zzamh;
    private final zzit zzami;

    public zzir() {
        this.zzamh = new MediaCodec.CryptoInfo();
        this.zzami = new zzit(this.zzamh, null);
    }

    public final void set(int v, int[] arr_v, int[] arr_v1, byte[] arr_b, byte[] arr_b1, int v1) {
        this.numSubSamples = v;
        this.numBytesOfClearData = arr_v;
        this.numBytesOfEncryptedData = arr_v1;
        this.key = arr_b;
        this.iv = arr_b1;
        this.mode = v1;
        this.zzamf = 0;
        this.zzamg = 0;
        this.zzamh.numSubSamples = this.numSubSamples;
        this.zzamh.numBytesOfClearData = this.numBytesOfClearData;
        this.zzamh.numBytesOfEncryptedData = this.numBytesOfEncryptedData;
        this.zzamh.key = this.key;
        this.zzamh.iv = this.iv;
        this.zzamh.mode = this.mode;
        zzit.zza(this.zzami, 0, 0);
    }

    @TargetApi(16)
    public final MediaCodec.CryptoInfo zzgh() {
        return this.zzamh;
    }
}

