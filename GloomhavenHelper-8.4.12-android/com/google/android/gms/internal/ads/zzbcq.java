package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

final class zzbcq implements zzno {
    private Uri uri;
    private final zzno zzedy;
    private final long zzedz;
    private final zzno zzeea;
    private long zzeeb;

    zzbcq(zzno zzno0, int v, zzno zzno1) {
        this.zzedy = zzno0;
        this.zzedz = (long)v;
        this.zzeea = zzno1;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final void close() throws IOException {
        this.zzedy.close();
        this.zzeea.close();
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final Uri getUri() {
        return this.uri;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final int read(byte[] arr_b, int v, int v1) throws IOException {
        int v4;
        long v2 = this.zzeeb;
        long v3 = this.zzedz;
        if(v2 < v3) {
            v4 = this.zzedy.read(arr_b, v, ((int)Math.min(v1, v3 - v2)));
            this.zzeeb += (long)v4;
        }
        else {
            v4 = 0;
        }
        if(this.zzeeb >= this.zzedz) {
            int v5 = this.zzeea.read(arr_b, v + v4, v1 - v4);
            v4 += v5;
            this.zzeeb += (long)v5;
        }
        return v4;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final long zza(zznp zznp0) throws IOException {
        this.uri = zznp0.uri;
        zznp zznp1 = zznp0.zzana < this.zzedz ? new zznp(zznp0.uri, zznp0.zzana, (zznp0.zzce == -1L ? this.zzedz - zznp0.zzana : Math.min(zznp0.zzce, this.zzedz - zznp0.zzana)), null) : null;
        zznp zznp2 = zznp0.zzce == -1L || zznp0.zzana + zznp0.zzce > this.zzedz ? new zznp(zznp0.uri, Math.max(this.zzedz, zznp0.zzana), (zznp0.zzce == -1L ? -1L : Math.min(zznp0.zzce, zznp0.zzana + zznp0.zzce - this.zzedz)), null) : null;
        long v = 0L;
        long v1 = zznp1 == null ? 0L : this.zzedy.zza(zznp1);
        if(zznp2 != null) {
            v = this.zzeea.zza(zznp2);
        }
        this.zzeeb = zznp0.zzana;
        return v1 == -1L || v == -1L ? -1L : v1 + v;
    }
}

