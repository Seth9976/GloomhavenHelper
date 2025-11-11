package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

public final class zznl implements zzno {
    private final byte[] data;
    private Uri uri;
    private int zzber;
    private int zzbes;

    public zznl(byte[] arr_b) {
        zzob.checkNotNull(arr_b);
        zzob.checkArgument(arr_b.length > 0);
        this.data = arr_b;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final void close() throws IOException {
        this.uri = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final Uri getUri() {
        return this.uri;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final int read(byte[] arr_b, int v, int v1) throws IOException {
        if(v1 == 0) {
            return 0;
        }
        int v2 = this.zzbes;
        if(v2 == 0) {
            return -1;
        }
        int v3 = Math.min(v1, v2);
        System.arraycopy(this.data, this.zzber, arr_b, v, v3);
        this.zzber += v3;
        this.zzbes -= v3;
        return v3;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final long zza(zznp zznp0) throws IOException {
        this.uri = zznp0.uri;
        this.zzber = (int)zznp0.zzana;
        this.zzbes = (int)(zznp0.zzce == -1L ? ((long)this.data.length) - zznp0.zzana : zznp0.zzce);
        int v = this.zzbes;
        if(v <= 0 || this.zzber + v > this.data.length) {
            throw new IOException("Unsatisfiable range: [" + this.zzber + ", " + zznp0.zzce + "], length: " + this.data.length);
        }
        return (long)v;
    }
}

