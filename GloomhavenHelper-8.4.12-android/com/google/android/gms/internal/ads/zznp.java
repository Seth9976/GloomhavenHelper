package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.util.Arrays;

public final class zznp {
    public final int flags;
    public final Uri uri;
    public final long zzana;
    public final byte[] zzbet;
    public final long zzbeu;
    public final String zzcc;
    public final long zzce;

    public zznp(Uri uri0) {
        this(uri0, 0);
    }

    private zznp(Uri uri0, int v) {
        this(uri0, 0L, -1L, null, 0);
    }

    private zznp(Uri uri0, long v, long v1, long v2, String s, int v3) {
        this(uri0, null, v, v1, v2, s, v3);
    }

    public zznp(Uri uri0, long v, long v1, String s) {
        this(uri0, v, v, v1, s, 0);
    }

    private zznp(Uri uri0, long v, long v1, String s, int v2) {
        this(uri0, 0L, 0L, -1L, null, 0);
    }

    public zznp(Uri uri0, byte[] arr_b, long v, long v1, long v2, String s, int v3) {
        boolean z = true;
        zzob.checkArgument(v >= 0L);
        zzob.checkArgument(v1 >= 0L);
        if(v2 <= 0L && v2 != -1L) {
            z = false;
        }
        zzob.checkArgument(z);
        this.uri = uri0;
        this.zzbet = arr_b;
        this.zzbeu = v;
        this.zzana = v1;
        this.zzce = v2;
        this.zzcc = s;
        this.flags = v3;
    }

    @Override
    public final String toString() {
        String s = Arrays.toString(this.zzbet);
        return "DataSpec[" + this.uri + ", " + s + ", " + this.zzbeu + ", " + this.zzana + ", " + this.zzce + ", " + this.zzcc + ", " + this.flags + "]";
    }

    public final boolean zzba(int v) {
        return (this.flags & 1) == 1;
    }
}

