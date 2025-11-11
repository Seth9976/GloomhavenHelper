package com.google.android.gms.internal.ads;

import android.media.MediaCodec.CodecException;

public final class zzky extends Exception {
    private final String mimeType;
    private final boolean zzazl;
    private final String zzazm;
    private final String zzazn;

    public zzky(zzgz zzgz0, Throwable throwable0, boolean z, int v) {
        super("Decoder init failed: [" + v + "], " + zzgz0, throwable0);
        this.mimeType = zzgz0.zzafn;
        this.zzazl = false;
        this.zzazm = null;
        this.zzazn = "com.google.android.exoplayer.MediaCodecTrackRenderer_" + (v >= 0 ? "" : "neg_") + Math.abs(v);
    }

    public zzky(zzgz zzgz0, Throwable throwable0, boolean z, String s) {
        super("Decoder init failed: " + s + ", " + zzgz0, throwable0);
        this.mimeType = zzgz0.zzafn;
        this.zzazl = false;
        this.zzazm = s;
        this.zzazn = throwable0 instanceof MediaCodec.CodecException ? ((MediaCodec.CodecException)throwable0).getDiagnosticInfo() : null;
    }
}

