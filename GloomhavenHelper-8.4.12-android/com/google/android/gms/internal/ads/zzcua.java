package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcua implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzemh;
    private final zzeew zzfgu;
    private final zzeew zzgic;

    private zzcua(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzemh = zzeew0;
        this.zzfgu = zzeew1;
        this.zzelc = zzeew2;
        this.zzgic = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcty(((zzcvg)this.zzemh.get()), ((zzdeu)this.zzfgu.get()), ((Context)this.zzelc.get()), ((zzavr)this.zzgic.get()));
    }

    public static zzcua zzf(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        return new zzcua(zzeew0, zzeew1, zzeew2, zzeew3);
    }
}

