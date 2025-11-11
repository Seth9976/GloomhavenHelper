package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzjy implements zzjt {
    private final zzjw zzarb;

    private zzjy(zzjw zzjw0) {
        this.zzarb = zzjw0;
        super();
    }

    zzjy(zzjw zzjw0, zzjv zzjv0) {
        this(zzjw0);
    }

    @Override  // com.google.android.gms.internal.ads.zzjt
    public final void zza(int v, double f) throws zzhc {
        this.zzarb.zza(v, f);
    }

    @Override  // com.google.android.gms.internal.ads.zzjt
    public final void zza(int v, int v1, zzjf zzjf0) throws IOException, InterruptedException {
        this.zzarb.zza(v, v1, zzjf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzjt
    public final void zza(int v, String s) throws zzhc {
        this.zzarb.zza(v, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzjt
    public final int zzah(int v) {
        return zzjw.zzah(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzjt
    public final boolean zzai(int v) {
        return zzjw.zzai(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzjt
    public final void zzaj(int v) throws zzhc {
        this.zzarb.zzaj(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzjt
    public final void zzc(int v, long v1) throws zzhc {
        this.zzarb.zzc(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzjt
    public final void zzd(int v, long v1, long v2) throws zzhc {
        this.zzarb.zzd(v, v1, v2);
    }
}

