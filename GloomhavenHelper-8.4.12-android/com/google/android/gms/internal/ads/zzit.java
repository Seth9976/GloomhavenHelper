package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec.CryptoInfo.Pattern;
import android.media.MediaCodec.CryptoInfo;

@TargetApi(24)
final class zzit {
    private final MediaCodec.CryptoInfo zzamh;
    private final MediaCodec.CryptoInfo.Pattern zzamq;

    private zzit(MediaCodec.CryptoInfo mediaCodec$CryptoInfo0) {
        this.zzamh = mediaCodec$CryptoInfo0;
        this.zzamq = new MediaCodec.CryptoInfo.Pattern(0, 0);
    }

    zzit(MediaCodec.CryptoInfo mediaCodec$CryptoInfo0, zziq zziq0) {
        this(mediaCodec$CryptoInfo0);
    }

    private final void set(int v, int v1) {
        this.zzamq.set(v, v1);
        this.zzamh.setPattern(this.zzamq);
    }

    static void zza(zzit zzit0, int v, int v1) {
        zzit0.set(0, 0);
    }
}

