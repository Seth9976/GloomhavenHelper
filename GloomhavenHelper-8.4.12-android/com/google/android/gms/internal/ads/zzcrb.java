package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcrb implements zzeej {
    private final zzeew zzfgm;
    private final zzeew zzfgx;
    private final zzeew zzgbg;
    private final zzeew zzger;
    private final zzeew zzges;

    public zzcrb(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzger = zzeew0;
        this.zzgbg = zzeew1;
        this.zzfgx = zzeew2;
        this.zzfgm = zzeew3;
        this.zzges = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcqv(((Context)this.zzger.get()), ((zzbmc)this.zzgbg.get()), ((zzdif)this.zzfgx.get()), ((zzdoe)this.zzfgm.get()), ((zzaaq)this.zzges.get()));
    }
}

