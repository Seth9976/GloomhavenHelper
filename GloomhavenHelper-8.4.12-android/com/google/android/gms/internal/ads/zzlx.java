package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzlx implements zzmn {
    private final int track;
    private final zzls zzbab;

    public zzlx(zzls zzls0, int v) {
        this.zzbab = zzls0;
        super();
        this.track = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzmn
    public final boolean isReady() {
        return this.zzbab.zzat(this.track);
    }

    @Override  // com.google.android.gms.internal.ads.zzmn
    public final int zzb(zzhb zzhb0, zziv zziv0, boolean z) {
        return this.zzbab.zza(this.track, zzhb0, zziv0, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzmn
    public final void zzeh(long v) {
        this.zzbab.zzd(this.track, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzmn
    public final void zzhp() throws IOException {
        this.zzbab.zzhp();
    }
}

