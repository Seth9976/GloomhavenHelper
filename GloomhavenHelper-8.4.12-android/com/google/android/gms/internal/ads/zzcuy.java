package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcuy implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfev;

    private zzcuy(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzelc = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcuw(((zzdoe)this.zzfev.get()), ((Context)this.zzelc.get()));
    }

    public static zzcuy zzas(zzeew zzeew0, zzeew zzeew1) {
        return new zzcuy(zzeew0, zzeew1);
    }
}

