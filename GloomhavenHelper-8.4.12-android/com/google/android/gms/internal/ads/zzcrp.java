package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcrp implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzgbg;

    public zzcrp(zzeew zzeew0, zzeew zzeew1) {
        this.zzelc = zzeew0;
        this.zzgbg = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcrl(((Context)this.zzelc.get()), ((zzbwt)this.zzgbg.get()));
    }
}

